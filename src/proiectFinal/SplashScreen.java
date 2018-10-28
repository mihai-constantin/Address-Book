package proiectFinal;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class SplashScreen extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7801656562705904312L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SplashScreen() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setUndecorated(true);
		setBounds(450, 250, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 109, 178));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 105, 450, 111);
		
		ImageIcon icon = new ImageIcon(new File("").getAbsolutePath() + File.separator + 
				"icons" + File.separator + "loading.gif");
		
		lblNewLabel.setText("");
		lblNewLabel.setIcon(icon);
		
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Created by Mihai Constantin");
		lblNewLabel_1.setForeground(new Color(233, 233, 233));
		lblNewLabel_1.setFont(new Font("Apple Chancery", Font.PLAIN, 25));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(6, 47, 444, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Copyright © 2018 Mihai Constantin. All rights reserved.");
		lblNewLabel_2.setForeground(new Color(233, 233, 233));
		lblNewLabel_2.setFont(new Font("Apple Chancery", Font.PLAIN, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 250, 450, 16);
		contentPane.add(lblNewLabel_2);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SplashScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SplashScreen frame = new SplashScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
