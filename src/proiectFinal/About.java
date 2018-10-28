package proiectFinal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

public class About extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5008621931330621644L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					About frame = new About();
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
	public About() {
		
		setTitle("Descriere implementare");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(150, 150, 639, 409);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrAplicatiaAFost = new JTextArea();
		txtrAplicatiaAFost.setText("    Aplicatia a fost realizata in intregime de Constantin Mihai.\n    \n    La pornirea acesteia se va afisa timp de 3 secunde un ecran de intampinare. Fereastra principala \ncontine lista de contacte din agenda, butoane pentru adaugare, stergere si editare, dar si optiuni \npentru filtrare si ordonare.\n    \n    In partea de jos a ferestrei ruleaza reclame care, la apasare pe acestea, ne vor redirectiona la pagina \nweb aferenta.\n    \n    Dupa ce utilizatorul introduce codul de inregistrare corect, reclamele vor disparea si se vor activa \noptiunile de incarcare si salvare a agendei de contacte. \n    Dupa prima salvare a contactelor acestea se vor importa automat la interval de un minut \nin fisierul ales.\n    \n    La apasarea pe butonul de adaugare contact, va aparea o fereastra in care user-ul introduce date \ncare vor fi analizate spre validare. In caz de esec, va aparea o fereastra pop-up. Analog pentru editare.\n\n    La aplicarea unui filtru, este necesara bifarea check box-ului. Listra filtrata va fi afisata pe ecran. \nUtilizatorul poate ordona in continuare lista filtrata. La anularea acestuia, se va afisa lista integrala de \ncontacte.\n\n\nCOD VALIDARE : 444");
		txtrAplicatiaAFost.setBounds(0, 0, 639, 387);
		txtrAplicatiaAFost.setEditable(false);
		contentPane.add(txtrAplicatiaAFost);
	}
}
