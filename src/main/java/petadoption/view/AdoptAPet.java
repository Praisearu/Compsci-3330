
package petadoption.view;

/**
 * 
 */


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

import petadoption.controller.AdoptionController;
import petadoption.model.Pet;



import javax.swing.DefaultComboBoxModel;

public class AdoptAPet extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtAnimalID;
    private JTextField txtAnimalName;
    private Object[] Col = {"ID", "Name", "Type", "Species", "Age"};
    private JTable availablePets;
    private AdoptionController controller;

    public AdoptAPet(AdoptionController controller) {
        setTitle("Adopt A Pet");
        this.controller = controller;
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 733, 829);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("Available Pets For Adoption");
        lblTitle.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 24));
        lblTitle.setBounds(16, 16, 357, 49);
        contentPane.add(lblTitle);

        JComboBox<String> cbSortBy = new JComboBox<>();
        cbSortBy.setModel(new DefaultComboBoxModel<>(new String[] {"Sort by...", "Sort by name", "Sort by age", "Sort by species"}));
        cbSortBy.setSelectedItem("Sort by...");
        cbSortBy.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
        cbSortBy.setBounds(92, 64, 157, 27);
        contentPane.add(cbSortBy);

        cbSortBy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = cbSortBy.getSelectedItem().toString();
                if (controller != null) {
                    controller.sortPets(selected);
                    loadTable(controller.getNonAdoptedPets()); // Reload updated list into this frame
                }
            }
        });


        availablePets = new JTable();
        availablePets.setFont(new Font("Noto Sans Batak", Font.BOLD, 10));
        availablePets.setRowHeight(50);
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(Col);
        availablePets.setModel(model);
        availablePets.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(availablePets);
        scrollPane.setViewportBorder(null);
        scrollPane.setBounds(30, 103, 319, 511);
        contentPane.add(scrollPane);

        // Labels and Text fields for adoption
        JLabel lblAnimalID = new JLabel("Animal ID:");
        lblAnimalID.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAnimalID.setBounds(371, 233, 123, 40);
        contentPane.add(lblAnimalID);

        txtAnimalID = new JTextField();
        txtAnimalID.setBounds(506, 233, 197, 40);
        contentPane.add(txtAnimalID);
        txtAnimalID.setColumns(10);

        JLabel lblAnimalName = new JLabel("Animal Name:");
        lblAnimalName.setFont(new Font("Arial", Font.PLAIN, 14));
        lblAnimalName.setBounds(371, 329, 123, 41);
        contentPane.add(lblAnimalName);

        txtAnimalName = new JTextField();
        txtAnimalName.setBounds(506, 329, 197, 40);
        contentPane.add(txtAnimalName);
        txtAnimalName.setColumns(10);

        // Save button for adoption
        JButton btnSaveAdoption = new JButton("Adopt this Pet");
        btnSaveAdoption.setFont(new Font("Rockwell", Font.PLAIN, 15));
        btnSaveAdoption.setBounds(472, 461, 180, 40);
        contentPane.add(btnSaveAdoption);
        
        // Action listener for Adopt button
        // ENOBONG WILL IMPLEMENT THE ACTUAL ADOPTION LOGIC HERE
        btnSaveAdoption.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String animalId = txtAnimalID.getText().trim();
                String animalName = txtAnimalName.getText().trim();

                if (animalId.isEmpty() || animalName.isEmpty()) {
                    JOptionPane.showMessageDialog(contentPane, "Please enter both Animal ID and Name", 
                            "Validation Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // TODO (partner): Check if animal exists and is not already adopted.
                // Then perform the adoption, save updated data to file, and refresh the table.
                // Also reset the input fields and show a success message.
                // Otherwise, show an error message.

                JOptionPane.showMessageDialog(contentPane, "Adoption logic will be implemented by ENOBONG",
                        "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        JButton btnBack = new JButton("Back");
        btnBack.setFont(new Font("Rockwell", Font.PLAIN, 15));
        btnBack.setBounds(612, 668, 115, 40);
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
        contentPane.add(btnBack);
    }
    
    public void loadTable(List<Pet> petList) {
        if (petList == null) return;
        DefaultTableModel model = (DefaultTableModel) availablePets.getModel();
        model.setRowCount(0); 

        for (Pet pet : petList) {
            // Only show pets that are not adopted
            if (!pet.isAdopted()) {
                model.addRow(new Object[] {
                    pet.getId(),
                    pet.getName(),
                    pet.getType(),
                    pet.getSpecies(),
                    pet.getAge()
                });
            }
        }
    }

    public void setController(AdoptionController controller) {
        this.controller = controller;
    }
}