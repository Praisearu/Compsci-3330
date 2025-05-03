package petadoption.view;

import java.awt.EventQueue;
import java.util.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdoptFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdoptFrame frame = new AdoptFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AdoptFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 575, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Available Pets For Adoption");
		lblNewLabel.setBounds(17, 16, 251, 23);
		lblNewLabel.setFont(new Font("Georgia", Font.BOLD | Font.ITALIC, 17));
		contentPane.add(lblNewLabel);
		
		JComboBox<String> cbSortBy = new JComboBox<>();
		
		cbSortBy.setModel(new DefaultComboBoxModel<>(new String[] {"Sort by...", "Sort by name", "Sort by age", "Sort by species"}));
		
		cbSortBy.setSelectedItem("Sort by...");
		cbSortBy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String p = "";
				p = cbSortBy.getSelectedItem().toString();
				
				
				
				System.out.println(p);
				
			}
		});
		
		
		
		cbSortBy.setFont(new Font("Arial Unicode MS", Font.PLAIN, 13));
		cbSortBy.setToolTipText("");
		cbSortBy.setBounds(37, 51, 157, 27);
		contentPane.add(cbSortBy);
		
		
	}
}
