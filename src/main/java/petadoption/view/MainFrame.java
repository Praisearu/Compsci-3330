package petadoption.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import petadoption.controller.AdoptionController;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

/**
 * MainFrame is the homepage of the Pet Adoption System.
 * From here, users can choose to adopt a pet or view the list of available pets.
 */
public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Creates the main window frame with two buttons: Adopt A Pet and View Animal List.
	 */
	public MainFrame() {
		setTitle("Adoption Management");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 829);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnAdopt = new JButton("Adopt A Pet");
		btnAdopt.setForeground(new Color(32, 51, 129));
		btnAdopt.setFont(new Font("Rockwell", Font.PLAIN, 23));
		btnAdopt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		        try {
		        	AdoptionController controller = new AdoptionController(); 
		        	AdoptFrame adoptFrame = new AdoptFrame(controller);       
		        	controller.setView(adoptFrame); 
		        	adoptFrame.setVisible(true);
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		
		// Button: View Animal List
		JButton btnViewAnimalList = new JButton("View Animal List");
		btnViewAnimalList.setForeground(new Color(32, 51, 129));
		btnViewAnimalList.setFont(new Font("Rockwell", Font.PLAIN, 23));
		btnViewAnimalList.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        dispose();
		        try {
		        	AdoptionController controller = new AdoptionController(); 
		        	ManagePets manageListFrame = new ManagePets(controller);       
		        	controller.setManageView(manageListFrame); 
		        	manageListFrame.loadTable(controller.getAllPets());
		        	manageListFrame.setVisible(true);
		        } catch (Exception e1) {
		            e1.printStackTrace();
		        }
		    }
		});
		
		btnAdopt.setBounds(235, 451, 251, 71);
		contentPane.add(btnAdopt);
		btnViewAnimalList.setBounds(235, 550, 251, 71);
		contentPane.add(btnViewAnimalList);
		
		JLabel lblNewLabel = new JLabel("  Welcome to Adopt Me!");
		lblNewLabel.setFont(new Font("Rockwell", Font.BOLD, 26));
		lblNewLabel.setBounds(189, 140, 341, 94);
		contentPane.add(lblNewLabel);
	}
}
