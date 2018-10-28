package proiectFinal;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class AddContact extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1146094258373308919L;
	private JPanel contentPane;
	public static JTextField textFieldNume;
	public static JTextField textFieldPrenume;
	public static JTextField textFieldTelefon;
	
	JButton btnAdd;
	
	JLabel lblNume;
	JLabel lblPrenume;
	JLabel lblDataNasterii;
	JLabel lblNumarDeTelefon;
	
	JFormattedTextField formattedTextFieldDta;
	
	JComboBox<String> comboBox;
	
	Contact c;
	private JLabel lblDeTelefon;

	/**
	 * Create the frame.
	 */
	public AddContact() {
		
		setTitle("Adaugare contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 519, 310);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblNume = new JLabel("Nume");
		
		lblPrenume = new JLabel("Prenume");
		
		lblDataNasterii = new JLabel("Data nasterii");
		
		lblNumarDeTelefon = new JLabel("Numar");
		
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		formattedTextFieldDta = new JFormattedTextField(format);
		
		textFieldNume = new JTextField();
		textFieldNume.setColumns(10);
		
		textFieldPrenume = new JTextField();
		textFieldPrenume.setColumns(10);
		
		textFieldTelefon = new JTextField();
		textFieldTelefon.setColumns(10);
		
		String[] elemente = {"mobil", "fix"};
		comboBox = new JComboBox(elemente);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String numeContact = textFieldNume.getText();
				String prenumeContact = textFieldPrenume.getText();
				String data = formattedTextFieldDta.getText();
				
				if(data.length() != 10) {
					JOptionPane.showMessageDialog(contentPane, "Data invalida !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int ziDeNastere = Integer.parseInt(data.substring(8));
				int lunaDeNastere = Integer.parseInt(data.substring(5, 7));
				int anDeNastere = Integer.parseInt(data.substring(0, 4));
				String numarDeTelefon = textFieldTelefon.getText();
	
				String tipTelefon = (String)comboBox.getSelectedItem();
				Contact c;
				
				if(tipTelefon.equals("fix")) {
					c = new Contact(numeContact, prenumeContact, ziDeNastere, lunaDeNastere, anDeNastere, new NrFix(numarDeTelefon));
				}
				else {
					c = new Contact(numeContact, prenumeContact, ziDeNastere, lunaDeNastere, anDeNastere, new NrMobil(numarDeTelefon));
				}
				
				if(c.getNume() == null) {
					JOptionPane.showMessageDialog(contentPane, "Nume invalid !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(c.getPrenume() == null) {
					JOptionPane.showMessageDialog(contentPane, "Prenume invalid !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(c.getZi() == 0) {
					JOptionPane.showMessageDialog(contentPane, "Data invalida !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				if(c.nrTelefon == null) {
					JOptionPane.showMessageDialog(contentPane, "Numar de telefon invalid !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				// verific daca nu este deja in lista
					
				boolean ok = true;
				
				for(int i = 0; i < Fereastra.agenda.contacte.size(); i++) {
					
					Contact contactCurent = Fereastra.agenda.contacte.get(i);
					
					if(contactCurent.equals(c) == true) {
						ok = false;
					}
				}
					
				if(ok == true) { // adaugare contact
					Fereastra.agenda.adaugare(c);
					Fereastra.modelLista.addElement(c);
					
					int ziCurenta = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					int lunaCurenta = 1 + Calendar.getInstance().get(Calendar.MONTH);
					
					if(ziCurenta == ziDeNastere && lunaCurenta == lunaDeNastere) {
						
						ImageIcon iconLma = new ImageIcon(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "lma.png");
						Image lmaImg = iconLma.getImage();
						Image lmaNewImg = lmaImg.getScaledInstance(200, 150, java.awt.Image.SCALE_SMOOTH);
						iconLma = new ImageIcon(lmaNewImg);
						
						JLabel labelLma = new JLabel("La muuuulti aniii !!!");
						labelLma.setFont(new Font("Monospaced", Font.ITALIC, 20));
						JOptionPane.showMessageDialog(contentPane, labelLma, "Notificare", JOptionPane.INFORMATION_MESSAGE,
								iconLma);
					}
					
					textFieldNume.setText("");
					textFieldPrenume.setText("");
					formattedTextFieldDta.setText("");
					textFieldTelefon.setText("");
					
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Contact duplicat !", "Eroare", JOptionPane.ERROR_MESSAGE);
				}
				
				Fereastra.lblCeva.setText(Integer.toString(Fereastra.agenda.contacte.size()));
			}
		});
		
		lblDeTelefon = new JLabel("de telefon");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(43)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNume)
						.addComponent(lblPrenume)
						.addComponent(lblDataNasterii)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNumarDeTelefon)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(9)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)
								.addComponent(lblDeTelefon))))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(textFieldNume, Alignment.LEADING, 139, 236, Short.MAX_VALUE)
						.addComponent(textFieldPrenume, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addComponent(formattedTextFieldDta, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
						.addComponent(textFieldTelefon, GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNumarDeTelefon)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblDeTelefon))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldNume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNume))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textFieldPrenume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPrenume))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(formattedTextFieldDta, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblDataNasterii))
							.addGap(18)
							.addComponent(textFieldTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
					.addComponent(btnAdd)
					.addGap(34))
		);
		
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContact frame = new AddContact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
