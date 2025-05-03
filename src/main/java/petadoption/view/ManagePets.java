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

            // Buttons for add, remove, view details
            JButton btnAddAnimal = new JButton("Add Animal");
            btnAddAnimal.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnAddAnimal.setBounds(620, 360, 180, 40);
            contentPane.add(btnAddAnimal);
            
            // ENOBONG PARTNER WILL IMPLEMENT THIS
            btnAddAnimal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String id = txtId.getText().trim();
                    String name = txtName.getText().trim();
                    String type = cbType.getSelectedItem().toString();
                    String species = txtSpecies.getText().trim();
                    String ageStr = txtAge.getText().trim();

                    if (id.isEmpty() || name.isEmpty() || species.isEmpty() || ageStr.isEmpty()) {
                        JOptionPane.showMessageDialog(contentPane, "All fields are required", 
                                "Validation Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                  // TODO (partner): Parse the age input and validate it.
                 // Then attempt to add a new animal using the controller.
                 // If successful: show a success message, clear fields, and refresh the table:
                 // call loadTable(controller.getAllPets());
                 // If failure: show an appropriate error message (e.g., duplicate ID).

                    JOptionPane.showMessageDialog(contentPane, "Add animal logic will be implemented by ENOBONG",
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            
            JButton btnRemoveAnimal = new JButton("Remove Selected");
            btnRemoveAnimal.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnRemoveAnimal.setBounds(620, 420, 180, 40);
            contentPane.add(btnRemoveAnimal);
            
            // ENOBONG WILL IMPLEMENT THIS
            btnRemoveAnimal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = animalTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(contentPane, "Please select an animal to remove",
                                "Selection Required", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                 // TODO (partner): Retrieve the selected animal ID.
                 // Use the controller to remove the animal.
                 // If removal is successful, show a message and refresh the table:
                 // call loadTable(controller.getAllPets());
                 // Otherwise, show an error dialog.

                    JOptionPane.showMessageDialog(contentPane, "Remove animal logic will be implemented by ENOBONG",
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            
            JButton btnViewDetails = new JButton("View Details");
            btnViewDetails.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnViewDetails.setBounds(620, 480, 180, 40);
            contentPane.add(btnViewDetails);
            
            // ENOBONG WILL IMPLEMENT THIS
            btnViewDetails.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    int selectedRow = animalTable.getSelectedRow();
                    if (selectedRow < 0) {
                        JOptionPane.showMessageDialog(contentPane, "Please select an animal to view details",
                                "Selection Required", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    // TODO (partner): Get the ID of the selected animal.
                    // Use the controller to fetch the full pet object.
                    // Display the pet's details in a dialog box.

                    JOptionPane.showMessageDialog(contentPane, "View details logic will be implemented by ENOBONG",
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            
            JButton btnSave = new JButton("Save My Changes");
            btnSave.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnSave.setBounds(620, 540, 180, 40);
            contentPane.add(btnSave);
            
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
                	// TODO (partner): Save current list of all pets to a JSON file.
                    // Use controller.getAllPets() to get the data.
                    // Use SimpleDateFormat to format the file name like: "20250503_184522_pets.json"
                    // Save the file under: src/main/resources/saved/

                    JOptionPane.showMessageDialog(contentPane, "Save logic will be implemented by ENOBONG",
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            });

            
            btnBack.setFont(new Font("Rockwell", Font.PLAIN, 15));
            btnBack.setBounds(781, 715, 113, 40);
            contentPane.add(btnBack);
        }
       
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



