package petadoption.view;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.List;

import petadoption.controller.AdoptionController;
import petadoption.model.Pet;

import java.awt.Color;

import javax.swing.JButton;

public class AdoptFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private Object[] Col = {"ID" , "Name" , "Type", "Species", "Age"}; 
	private JTable availablePets;
	private AdoptionController controller;

	
	 
	public AdoptFrame(AdoptionController controller) {
		setVisible(true);
	    this.controller = controller;

	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 733, 829);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JLabel lblNewLabel = new JLabel("List of All Pets");
	    lblNewLabel.setBounds(82, 17, 209, 46);
	    lblNewLabel.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 24));
	    contentPane.add(lblNewLabel);

	    JComboBox<String> cbSortBy = new JComboBox<>();
	    cbSortBy.setModel(new DefaultComboBoxModel<>(new String[] {"Sort by...", "Sort by name", "Sort by age", "Sort by species"}));
	    cbSortBy.setSelectedItem("Sort by...");
	    cbSortBy.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
	    cbSortBy.setToolTipText("");
	    cbSortBy.setBounds(92, 64, 157, 27);
	    contentPane.add(cbSortBy);

	    cbSortBy.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            String selected = cbSortBy.getSelectedItem().toString();
	            if (controller != null) {
	                controller.sortPets(selected);
	            }
	        }
	    });

	    availablePets = new JTable();
	    availablePets.setFont(new Font("Noto Sans Batak", Font.BOLD, 10));
	    DefaultTableModel model = new DefaultTableModel();
	    model.setColumnIdentifiers(Col);
	    
	    availablePets.setModel(model);
	    availablePets.setRowHeight(50);
	    availablePets.setAutoCreateRowSorter(true);

	    JScrollPane scrollPane = new JScrollPane(availablePets);
	    scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
	    scrollPane.setBounds(30, 103, 319, 511);
	    contentPane.add(scrollPane);

	    JButton btnadoptAPet = new JButton("I'd Like to Adopt!");
	    btnadoptAPet.setFont(new Font("Rockwell", Font.PLAIN, 15));
	    btnadoptAPet.setBounds(458, 243, 236, 59);
	    btnadoptAPet.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dispose();
	            try {
	                AdoptAPet adoptPetFrame = new AdoptAPet(controller);
	                adoptPetFrame.loadTable(controller.getNonAdoptedPets()); // This will require implementation by your partner
	                adoptPetFrame.setVisible(true);
	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }
	        }
	    });
	    contentPane.add(btnadoptAPet);

	    JButton btnBack = new JButton("Back");
	    btnBack.setFont(new Font("Rockwell", Font.PLAIN, 15));
	    btnBack.setBounds(458, 377, 236, 59);
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
	        model.addRow(new Object[] {
	            pet.getId(),
	            pet.getName(),
	            pet.getType(),
	            pet.getSpecies(),
	            pet.getAge()
	        });
	    }
	}




public void setController(AdoptionController controller) {
	this.controller = controller;
}
}