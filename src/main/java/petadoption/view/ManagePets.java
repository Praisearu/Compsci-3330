package petadoption.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;


import petadoption.controller.AdoptionController;
import petadoption.model.Pet;
import petadoption.model.Dog;
import petadoption.model.Cat;
import petadoption.model.Rabbit;
import petadoption.model.ExoticAnimal;

/**
 * ManagePets is the interface used  to add, remove, view, and save adoptable animals.
 * It connects to the AdoptionController to update the pet list.
 */
public class ManagePets extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtSpecies;
    private JTextField txtAge;
    private JComboBox<String> cbType;
    private Object[] Col = {"ID", "Name", "Type", "Species", "Age", "Adopted"};
    private JTable animalTable;
    private AdoptionController controller;
    
    
    /**
     * Constructs the ManagePets window with form inputs, animal table, and control buttons.
     * @param controller the adoption controller used for handling logic
     */
       public ManagePets(AdoptionController controller) {
            setTitle("Manage Animal List");
            this.controller = controller;
            
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setBounds(100, 100, 900, 829);
            contentPane = new JPanel();
            contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
            setContentPane(contentPane);
            contentPane.setLayout(null);

            JLabel lblTitle = new JLabel("Edit Animal List");
            lblTitle.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 24));
            lblTitle.setBounds(161, 18, 212, 46);
            contentPane.add(lblTitle);

            // Table to display all animals
            animalTable = new JTable();
            animalTable.setFont(new Font("Arial", Font.PLAIN, 12));
            animalTable.setRowHeight(30);
            DefaultTableModel model = new DefaultTableModel();
            model.setColumnIdentifiers(Col);
            animalTable.setModel(model);
            animalTable.setAutoCreateRowSorter(true);

            JScrollPane scrollPane = new JScrollPane(animalTable);
            scrollPane.setViewportBorder(null);
            scrollPane.setBounds(30, 103, 492, 652);
            contentPane.add(scrollPane);

            // Form fields for adding new animals
            JLabel lblAddAnimal = new JLabel("Animal Description");
            lblAddAnimal.setFont(new Font("Arial", Font.BOLD, 18));
            lblAddAnimal.setBounds(570, 103, 200, 30);
            contentPane.add(lblAddAnimal);
            
            JLabel lblId = new JLabel("ID:");
            lblId.setFont(new Font("Arial", Font.PLAIN, 14));
            lblId.setBounds(570, 150, 100, 25);
            contentPane.add(lblId);

            txtId = new JTextField();
            txtId.setBounds(680, 150, 170, 25);
            contentPane.add(txtId);
            txtId.setColumns(10);

            JLabel lblName = new JLabel("Name:");
            lblName.setFont(new Font("Arial", Font.PLAIN, 14));
            lblName.setBounds(570, 190, 100, 25);
            contentPane.add(lblName);

            txtName = new JTextField();
            txtName.setBounds(680, 190, 170, 25);
            contentPane.add(txtName);
            txtName.setColumns(10);
            
            JLabel lblType = new JLabel("Type:");
            lblType.setFont(new Font("Arial", Font.PLAIN, 14));
            lblType.setBounds(570, 230, 100, 25);
            contentPane.add(lblType);

            cbType = new JComboBox<>();
            cbType.setModel(new DefaultComboBoxModel<>(new String[] {"Dog", "Cat", "Rabbit", "ExoticAnimal"}));
            cbType.setBounds(680, 230, 170, 25);
            contentPane.add(cbType);
            
            JLabel lblSpecies = new JLabel("Species:");
            lblSpecies.setFont(new Font("Arial", Font.PLAIN, 14));
            lblSpecies.setBounds(570, 270, 100, 25);
            contentPane.add(lblSpecies);

            txtSpecies = new JTextField();
            txtSpecies.setBounds(680, 270, 170, 25);
            contentPane.add(txtSpecies);
            txtSpecies.setColumns(10);
            
            JLabel lblAge = new JLabel("Age:");
            lblAge.setFont(new Font("Arial", Font.PLAIN, 14));
            lblAge.setBounds(570, 310, 100, 25);
            contentPane.add(lblAge);

            txtAge = new JTextField();
            txtAge.setBounds(680, 310, 170, 25);
            contentPane.add(txtAge);
            txtAge.setColumns(10);

            // Add Animal
            JButton btnAddAnimal = new JButton("Add Animal");
            btnAddAnimal.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnAddAnimal.setBounds(620, 360, 180, 40);
            contentPane.add(btnAddAnimal);
            
            btnAddAnimal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id = txtId.getText().trim();
                    String name = txtName.getText().trim();
                    String type = cbType.getSelectedItem().toString();
                    String species = txtSpecies.getText().trim();
                    String ageStr = txtAge.getText().trim();

                    if (id.isEmpty() || name.isEmpty() || species.isEmpty() || ageStr.isEmpty()) {
                        JOptionPane.showMessageDialog(contentPane, "All fields are required", "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    try {
                        int age = Integer.parseInt(ageStr);
                        Pet newPet;
                        switch (type) {
        case "Dog":
            newPet = new Dog();
            break;
        case "Cat":
            newPet = new Cat();
            break;
        case "Rabbit":
            newPet = new Rabbit();
            break;
        case "ExoticAnimal":
            newPet = new ExoticAnimal();
            break;
        default:
            throw new IllegalArgumentException("Invalid type");
    }
    newPet.setId(id);
    newPet.setName(name);
    newPet.setType(Pet.Type.valueOf(type.trim()));
    newPet.setSpecies(species);
    newPet.setAge(age);
    newPet.setAdopted(false);

                        boolean added = controller.addPet(newPet);
                        loadTable(controller.getAllPets());
                        if (added) {
                            JOptionPane.showMessageDialog(contentPane, "Animal added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            txtId.setText(""); txtName.setText(""); txtSpecies.setText(""); txtAge.setText("");
                            loadTable(controller.getAllPets());
                        } else {
                            JOptionPane.showMessageDialog(contentPane, "Pet with this ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(contentPane, "Invalid age format. Please enter a number.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(contentPane, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


         // Remove Animal
            JButton btnRemoveAnimal = new JButton("Remove Selected");
            btnRemoveAnimal.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnRemoveAnimal.setBounds(620, 420, 180, 40);
            contentPane.add(btnRemoveAnimal);
            
            
            btnRemoveAnimal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = animalTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(contentPane, "Please select an animal to remove",
                                "Selection Required", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String petId = animalTable.getValueAt(selectedRow, 0).toString();
                    boolean removed = controller.removePetById(petId);
                    loadTable(controller.getAllPets());
                    if (removed) {
                        JOptionPane.showMessageDialog(contentPane, "Animal removed successfully.",
                                "Removed", JOptionPane.INFORMATION_MESSAGE);
                        loadTable(controller.getAllPets());
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Failed to remove pet.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // View Details
            JButton btnViewDetails = new JButton("View Details");
            btnViewDetails.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnViewDetails.setBounds(620, 480, 180, 40);
            contentPane.add(btnViewDetails);
            
            
            btnViewDetails.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = animalTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(contentPane, "Please select an animal to view details",
                                "Selection Required", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    String id = animalTable.getValueAt(selectedRow, 0).toString();
                    Pet pet = controller.getPetById(id);
                    if (pet != null) {
                        JOptionPane.showMessageDialog(contentPane,
                            String.format("ID: %s\nName: %s\nType: %s\nSpecies: %s\nAge: %d\nAdopted: %s",
                                    pet.getId(), pet.getName(), pet.getType(), pet.getSpecies(),
                                    pet.getAge(), pet.isAdopted() ? "Yes" : "No"),
                            "Pet Details", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Pet not found.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Save Changes
            JButton btnSave = new JButton("Save My Changes");
            btnSave.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnSave.setBounds(620, 540, 180, 40);
            contentPane.add(btnSave);
            
         // Back Button
            JButton btnBack = new JButton("Back");
            btnBack.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	            dispose();
    	            try {
    	                MainFrame frame = new MainFrame();
    	                frame.setVisible(true);
    	            } catch (Exception e1) {
    	                e1.printStackTrace();
    	            }
    	        }
    	        
    	    }); 
            btnSave.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                    
                    String fileName = "src/main/resources/" + timestamp + "_pets.json";
                    boolean success = controller.savePetsToFile(fileName);
                    
                    if (success) {
                        JOptionPane.showMessageDialog(contentPane, "Changes saved to file successfully!",
                                "Saved", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(contentPane, "Failed to save changes.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            
            btnBack.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnBack.setBounds(781, 715, 113, 40);
            contentPane.add(btnBack);
        }
       
       /**
        * Loads pet data into the JTable from the provided pet list.
        * @param petList List of Pet objects to display
        */
       public void loadTable(List<Pet> petList) {
           if (petList == null) return;
           DefaultTableModel model = (DefaultTableModel) animalTable.getModel();
           model.setRowCount(0);
           for (Pet pet : petList) {
               model.addRow(new Object[] {
                   pet.getId(),
                   pet.getName(),
                   pet.getType(),
                   pet.getSpecies(),
                   pet.getAge(),
                   pet.isAdopted() ? "Yes" : "No"
               });
    	    }
    	}

}



