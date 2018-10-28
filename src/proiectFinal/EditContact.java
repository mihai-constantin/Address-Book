package proiectFinal;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JFormattedTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class EditContact extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -639365025683443269L;

	private JPanel contentPane;
	
	public JTextField textFieldNume;
	public JTextField textFieldPrenume;
	public JTextField textFieldTelefon;
	
	JLabel lblNume;
	JLabel lblPrenume;
	JLabel lblDataNasterii;
	JLabel lblNumar;
	JLabel lblDeTelefon;
	
	JFormattedTextField formattedTextFieldData;
	
	JComboBox<String> comboBox;

	/**
	 * Create the frame.
	 */
	public EditContact() {
		
		setTitle("Editare contact");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 450, 307);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		lblNume = new JLabel("Nume");
		
		lblPrenume = new JLabel("Prenume");
		
		lblDataNasterii = new JLabel("Data nasterii");
		
		lblNumar = new JLabel("Numar");
		
		lblDeTelefon = new JLabel("de telefon");
		
		String[] elemente = {"mobil", "fix"};
		comboBox = new JComboBox (elemente);
		
		textFieldNume = new JTextField();
		textFieldNume.setColumns(10);
		
		textFieldPrenume = new JTextField();
		textFieldPrenume.setColumns(10);
		
		DateFormat format = new SimpleDateFormat("yyyy.MM.dd");
		formattedTextFieldData = new JFormattedTextField(format);
		
		textFieldTelefon = new JTextField();
		textFieldTelefon.setColumns(10);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = Fereastra.list.getSelectedIndex();
				Contact c;
				
				try {
					
					c = Fereastra.modelLista.getElementAt(index);
					Fereastra.modelLista.removeElementAt(index);
					System.out.println(c);
					
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(contentPane, "Selectati un contact pentru editare !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String numeContact = textFieldNume.getText();
				String prenumeContact = textFieldPrenume.getText();
				String data = formattedTextFieldData.getText();
				
				if(data.length() != 10) {
					Fereastra.modelLista.insertElementAt(c, index); // il punem la loc
					JOptionPane.showMessageDialog(contentPane, "Data introdusa este invalida !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				int ziDeNastere = Integer.parseInt(data.substring(8));
				int lunaDeNastere = Integer.parseInt(data.substring(5, 7));
				int anDeNastere = Integer.parseInt(data.substring(0, 4));
				
				String numarDeTelefon = textFieldTelefon.getText();
				
				String tipTelefon = (String)comboBox.getSelectedItem();
				Contact contactNou;
				
				if(tipTelefon.equals("fix")) {
					contactNou = new Contact(numeContact, prenumeContact, ziDeNastere, lunaDeNastere, anDeNastere, new NrFix(numarDeTelefon));
				}
				else {
					contactNou = new Contact(numeContact, prenumeContact, ziDeNastere, lunaDeNastere, anDeNastere, new NrMobil(numarDeTelefon));
				}
				
				if(contactNou.getNume() == null || contactNou.getPrenume() == null || contactNou.getZi() == 0 || contactNou.nrTelefon == null) {
					// contactul contactNou este invalid !!!
					
					Fereastra.modelLista.insertElementAt(c, index); // il punem la loc
					
					JOptionPane.showMessageDialog(contentPane, "Contact invalid !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				
				Fereastra.modelLista.insertElementAt(contactNou, index);
				
				// caut indexul contactului in agenda.contacte
				for(Contact contact : Fereastra.agenda.contacte) {
					if(contact.equals(c)) {
						int idx = Fereastra.agenda.contacte.indexOf(c);
						Fereastra.agenda.contacte.remove(c);
						Fereastra.agenda.contacte.add(idx, contactNou);
						break;
					}
				}
				
//				Fereastra.agenda.contacte.remove(index);
//				Fereastra.agenda.contacte.add(index, contactNou);
				
				setVisible(false);
				
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPrenume)
						.addComponent(lblNume)
						.addComponent(lblDataNasterii)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNumar)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(21)
							.addComponent(lblDeTelefon)))
					.addGap(27)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(formattedTextFieldData, 146, 146, 146)
						.addComponent(textFieldNume, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(textFieldPrenume, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
						.addComponent(textFieldTelefon, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
					.addContainerGap(21, GroupLayout.PREFERRED_SIZE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(160)
					.addComponent(btnEdit, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(161, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNume)
						.addComponent(textFieldNume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPrenume)
						.addComponent(textFieldPrenume, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblDataNasterii)
						.addComponent(formattedTextFieldData, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNumar)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDeTelefon)
						.addComponent(textFieldTelefon, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(40)
					.addComponent(btnEdit)
					.addGap(28))
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
					EditContact frame = new EditContact();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
