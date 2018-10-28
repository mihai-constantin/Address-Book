package proiectFinal;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import java.awt.Toolkit;
import javax.swing.JCheckBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JSeparator;
import java.awt.Rectangle;

public class Fereastra extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 885202928355594892L;

	private JPanel contentPane;
	
	private JMenuBar menuBar;
	private JMenu mnFisiere;
	private JMenuItem mntmDeschidere;
	private JMenuItem mntmSalvare;
	private JMenuItem mntmIesire;
	private JMenu mnAjutor;
	private JMenuItem mntmInregistrare;
	private JMenuItem mntmDespre;
	
	public static DefaultListModel<Contact> modelLista = new DefaultListModel<Contact>();
	public static Agenda agenda = new Agenda();
	
	public static JList<Contact> list;
	
	private  int COD = 444;
	
	private int position;
	
	public static int idx;
	
	static File fileSave;
	
	static public JLabel lblCeva;
	private JButton btnStergeContactSelectat;
	private JButton btnEditeazaContactSelectat;
	private JButton btnOrdoneaza;
	private JButton btnFiltreaza;
	private JTextField textFieldFiltru;
	private JComboBox<CriteriuOrdonare> comboBox_1;
	private JLabel lblNewLabel;
	
	private JFileChooser fileChooser;
	
	private Map<File, URI> map = new LinkedHashMap<File, URI>();

	/**
	 * Create the frame.
	 * @throws URISyntaxException 
	 */
	public Fereastra() throws IOException, URISyntaxException {
		
		setTitle("Proiect Final");
		setIcon();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 827, 625);
		
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		mnFisiere = new JMenu("Fisiere");
		menuBar.add(mnFisiere);
		
		fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		
		mntmDeschidere = new JMenuItem("Deschidere");
		mntmDeschidere.setEnabled(false);
		mntmDeschidere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
					
					File file = fileChooser.getSelectedFile();
					
					//System.out.println("LENGTH : " + file.length());
					
					if(file.length() == 0) {
						JOptionPane.showMessageDialog(contentPane, "Fisier gol !", "Eroare", JOptionPane.ERROR_MESSAGE);
						return;
					}
	
					ObjectInputStream ois = null;
					try {
						ois = new ObjectInputStream(new FileInputStream(file));
					} catch (java.io.EOFException e2) {
						e2.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
					
					List<Contact> listaDinFisier = null;
					
					try {
						listaDinFisier = (List<Contact>)(ois.readObject());
					} catch (Exception e1) {
						
					}
					
					try {
						ois.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					catch(NullPointerException e2){
						JOptionPane.showMessageDialog(contentPane, "Fisier gol !", "Eroare", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					// afisam intr-o fereastra contactele care isi sarbatoreaza ziua de nastere astazi
					
					String sarbatoriti = new String("=====   SARBATORITII ZILEI   =====\n");
					
					int ziCurenta = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
					int lunaCurenta = 1 + Calendar.getInstance().get(Calendar.MONTH);
					
					int ok = 0;
					
					for(Contact c : listaDinFisier) {
						if(c.getZi() == ziCurenta && c.getLuna() == lunaCurenta) {
							ok = 1;
							sarbatoriti = sarbatoriti + "\n" + c.getNume() + " " + c.getPrenume() + ", " + c.getNrTelefon();
						}
					}
					
					if(ok == 1) {
						JOptionPane.showMessageDialog(contentPane, sarbatoriti, "Notificare", JOptionPane.INFORMATION_MESSAGE);
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Niciun contact nu isi sarbatoreste ziua de nastere astazi", "Notificare", JOptionPane.INFORMATION_MESSAGE);
					}
					
					
					modelLista.clear();
					agenda.contacte.clear();
					for(Contact c : listaDinFisier) {
						modelLista.addElement(c);
						agenda.adaugare(c);
					}
					
					lblCeva.setText(Integer.toString(modelLista.size()));
					
					agenda.printList();
				}
				
			}
		});
		
		ImageIcon openIcon = new ImageIcon(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "open.png");
		Image openImg = openIcon.getImage();
		Image openNewImg = openImg.getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH);
		openIcon = new ImageIcon(openNewImg);
		mntmDeschidere.setIcon(openIcon);
		
		mnFisiere.add(mntmDeschidere);
		
		mntmSalvare = new JMenuItem("Salvare");
		mntmSalvare.setEnabled(false);
		
		ImageIcon saveIcon = new ImageIcon(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "save.png");
		Image saveImg = saveIcon.getImage();
		Image saveNewImg = saveImg.getScaledInstance(18, 18, java.awt.Image.SCALE_SMOOTH);
		saveIcon = new ImageIcon(saveNewImg);
		mntmSalvare.setIcon(saveIcon);
		
		
		
		mntmSalvare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fileChooser.showOpenDialog(contentPane) == JFileChooser.APPROVE_OPTION) {
					
					fileSave = fileChooser.getSelectedFile();
					ObjectOutputStream oos;
					
					try {
						System.out.println("INTRA O SINGURA DATA");
						oos = new ObjectOutputStream(new FileOutputStream(fileSave));
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
//						e1.printStackTrace();
						return;
					}
					try {
						oos.writeObject(agenda.contacte);
						
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						//e1.printStackTrace();
						return;
					}
					
					try {
						oos.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
					java.util.Timer t = new java.util.Timer();				
					t.schedule(new Save(), 0, 10000);
	
				}
			}
			
		});
		
		
		
		JSeparator separator = new JSeparator();
		mnFisiere.add(separator);
		mnFisiere.add(mntmSalvare);
		
		mntmIesire = new JMenuItem("Iesire");
		
		ImageIcon exitIcon = new ImageIcon(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "exit.png");
		Image exitImg = exitIcon.getImage();
		Image exitNewImg = exitImg.getScaledInstance(17, 17, java.awt.Image.SCALE_SMOOTH);
		exitIcon = new ImageIcon(exitNewImg);
		mntmIesire.setIcon(exitIcon);
		
		mntmIesire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ImageIcon icon = new ImageIcon(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "contact.png");
				Image contactImg = icon.getImage();
				Image contactNewImg = contactImg.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
				icon = new ImageIcon(contactNewImg);
				
				int ans = JOptionPane.showConfirmDialog(contentPane, "Doriti sa parasiti aplicatia ?", "Confirmare", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, icon);
				
				if(ans == JOptionPane.YES_OPTION) {
					System.exit(JFrame.EXIT_ON_CLOSE);
				}
			}
		});
		
		JSeparator separator_1 = new JSeparator();
		mnFisiere.add(separator_1);
		mnFisiere.add(mntmIesire);
		
		mnAjutor = new JMenu("Ajutor");
		menuBar.add(mnAjutor);
		
		mntmDespre = new JMenuItem("Despre");
		mntmDespre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				About about = new About();
				about.setVisible(true);
			}
		});
		ImageIcon aboutIcon = new ImageIcon(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "about.png");
		Image aboutImg = aboutIcon.getImage();
		Image aboutNewImg = aboutImg.getScaledInstance(19, 19, java.awt.Image.SCALE_SMOOTH);
		aboutIcon = new ImageIcon(aboutNewImg);
		mntmDespre.setIcon(aboutIcon);
		mnAjutor.add(mntmDespre);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnAdd = new JButton("Adauga contact");
		btnAdd.setBounds(56, 155, 220, 29);
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				AddContact add = new AddContact();
				add.setVisible(true);
			}
		});
		
		lblCeva = new JLabel("0");
		lblCeva.setBounds(133, 356, 81, 50);
		lblCeva.setFont(new Font("Lucida Grande", Font.PLAIN, 17));
		lblCeva.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnStergeContactSelectat = new JButton("Sterge contact selectat");
		btnStergeContactSelectat.setBounds(56, 202, 220, 29);
		btnStergeContactSelectat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int nrContacte = modelLista.size();
				
				if(nrContacte == 0) {
					JOptionPane.showMessageDialog(contentPane, "Lista nu contine niciun contact !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				idx = list.getSelectedIndex();
				
				if(idx >= 0) {
					
					int ans = JOptionPane.showConfirmDialog(contentPane, "Doriti sa stergeti contactul selectat ?", 
							"Confirmare", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

					if(ans == JOptionPane.YES_OPTION) {
					
						Contact c = modelLista.getElementAt(idx);
						System.out.println("contactul pe care il sterg : " + c);
						
						modelLista.removeElementAt(idx);
						
						System.out.println("size contacte : " + agenda.contacte.size());
						
						for(int i = 0; i < agenda.contacte.size(); i++) {
							
							Contact contactCurent = agenda.contacte.get(i);
							
							// aici trebuie sa caut in agenda contactul pe care l am sters din modelLista si sa l sterg din agenda.contacte -> size--
							
							if(contactCurent.equals(c) == true) {
								// elimin elementul
								
								agenda.contacte.remove(i);
								break;
							}
						}
						
						System.out.println("size contacte : " + agenda.contacte.size());
						
						lblCeva.setText(Integer.toString(agenda.contacte.size()));	
					}
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Selectati mai intai un contact !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		String[] elemente = {"contacte cu numar fix", "contacte cu numar mobil", "contacte cu ziua de nastere astazi", 
				"contacte cu ziua de nastere in luna curenta", "personalizata"};

		JComboBox comboBox = new JComboBox(elemente);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int idx = comboBox.getSelectedIndex();
				
				if(idx != 4) {
					textFieldFiltru.setText("");
				}
			}
		});
		comboBox.setBounds(139, 49, 236, 27);
		
		btnEditeazaContactSelectat = new JButton("Editeaza contact selectat");
		btnEditeazaContactSelectat.setBounds(56, 249, 220, 29);
		btnEditeazaContactSelectat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int nrContacte = modelLista.size();
				
				if(nrContacte == 0) {
					JOptionPane.showMessageDialog(contentPane, "Lista nu contine niciun contact !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				idx = list.getSelectedIndex();
				
				if(idx >= 0) {
					
					EditContact edit = new EditContact();
					
					Contact c = modelLista.getElementAt(idx);
					
					edit.textFieldNume.setText(c.getNume());
					edit.textFieldPrenume.setText(c.getPrenume());
					edit.textFieldTelefon.setText(c.getNrTelefon());
					
					if(c.getNrTelefon().substring(0, 2).equals("07") == false) {
						edit.comboBox.setSelectedIndex(1);
					}
					
					edit.formattedTextFieldData.setText(c.getData().replace("-", "."));
					
					edit.setVisible(true);
					
				}
				else {
					JOptionPane.showMessageDialog(contentPane, "Selectati mai intai un contact !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		
		JLabel lblNumarContacte = new JLabel("Numar contacte");
		lblNumarContacte.setBounds(111, 330, 124, 20);
		lblNumarContacte.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		
		JLabel lblFiltrare = new JLabel("Filtrare");
		lblFiltrare.setBounds(56, 50, 57, 20);
		lblFiltrare.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		JLabel lblFiltru = new JLabel("Filtru:");
		lblFiltru.setBounds(393, 53, 37, 16);
		
		JLabel lblOrdonare = new JLabel("Ordonare");
		lblOrdonare.setBounds(56, 95, 58, 16);
		
		
		
		textFieldFiltru = new JTextField();
		textFieldFiltru.setBounds(442, 48, 130, 26);
		textFieldFiltru.setColumns(10);
		
		JCheckBox checkBoxFiltrare = new JCheckBox("");
		checkBoxFiltrare.setBounds(23, 42, 27, 35);
		
		btnFiltreaza = new JButton("Filtreaza");
		btnFiltreaza.setBounds(584, 48, 162, 29);
		btnFiltreaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(!checkBoxFiltrare.isSelected()) {
					JOptionPane.showMessageDialog(contentPane, "Bifati optiunea de filtrare !", "Eroare", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				List<Contact> lista = new ArrayList<Contact>();
				
				
				switch (comboBox.getSelectedIndex()) {
					case 0:
						agenda.filtreazaNrFix();
						lista = agenda.contacte();
						break;
						
					case 1:
						agenda.filtreazaNrMobil();
						lista = agenda.contacte();
						break;
						
					case 2:
						agenda.filtreazaNascutiAstazi();
						lista = agenda.contacte();
						break;
					case 3:
						agenda.filtreazaNascutiLunaCurenta();
						lista = agenda.contacte();
						break;
	
					default:
						
						int i = 1;
						
						String[] arrOfStr = textFieldFiltru.getText().split(" ");
						
						for(String a : arrOfStr) {
							
							agenda.filtreazaPersonalizat(a);	
							
							if(i == 1) {
								lista = agenda.contacte();
							}
							else {
								lista = agenda.contactePersonalizate(lista);
							}
							
							i = 0;
						}
					
						
						break;
				}
				
				modelLista.clear();
				
				for(Contact  c : lista) {
					modelLista.addElement(c);
				}
				
				agenda.printList();   
			}
		});
		
		comboBox_1 = new JComboBox<>();
		comboBox_1.setBounds(139, 96, 433, 27);
		comboBox_1.setModel(new DefaultComboBoxModel<>(CriteriuOrdonare.values()));
		
		checkBoxFiltrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				modelLista.clear();
				
				for(Contact c : agenda.contacte) {
					modelLista.addElement(c);
				}
			}
		});
		
		btnOrdoneaza = new JButton("Ordoneaza");
		btnOrdoneaza.setBounds(584, 95, 162, 29);
		btnOrdoneaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				switch (comboBox_1.getSelectedIndex()) {
					case 0:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_NUME);
						break;
						
					case 1:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_PRENUME);
						break;
						
					case 2:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_TELEFON);
						break;
	
					default:
						agenda.ordoneaza(CriteriuOrdonare.DUPA_DATA);
						break;
				}
				
				List<Contact> lista = new ArrayList<Contact>();
				
				if(!checkBoxFiltrare.isSelected()) {
					agenda.filtruDefault();
				}
				
				
				if(comboBox.getSelectedIndex() != 4 || !checkBoxFiltrare.isSelected()) {
					lista = agenda.contacte();
				}
				else {
					
					if(checkBoxFiltrare.isSelected() == true) {
					
						System.out.println("intra aiciii");
						int i = 1;
						
						String[] arrOfStr = textFieldFiltru.getText().split(" ");
						
						for(String a : arrOfStr) {
							
							agenda.filtreazaPersonalizat(a);	
							
							if(i == 1) {
								lista = agenda.contacte();
							}
							else {
								lista = agenda.contactePersonalizate(lista);
							}
							
							i = 0;
						}
					
					}
				}
				
				modelLista.clear();
				
				for(Contact  c : lista) {
					modelLista.addElement(c);
				}
				
				agenda.printList();
			}
		});
		
		lblNewLabel = new JLabel("Reclama");
		lblNewLabel.setBounds(11, 464, 805, 112);

		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		lblNewLabel.setText("");
		
		String dir = new File("").getAbsoluteFile() + File.separator + "reclame";
		
		File directory = new File(dir);
		File[] fisiere = directory.listFiles();
		
		String[] uri = {"https://www.jetbrains.com", "https://azure.microsoft.com", "https://stackoverflow.com", 
				"https://stackoverflow.com/jobs", "https://kotlinconf.com"};
		
		for(int i = 0; i < fisiere.length; i++) {
			map.put(fisiere[i], new URI(uri[i]));
		}
		
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (Desktop.isDesktopSupported()) {
				      try {
				    	  Desktop.getDesktop().browse(map.get(fisiere[position - 1]));
				      } catch (IOException e1) { /* TODO: error handling */ }
				    } else { /* TODO: error handling */ }
				
			}
		});
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				BufferedImage imgReclama = null;
				try {
					if(position == fisiere.length) {
						position = 0;
					}
				    imgReclama = ImageIO.read(new File(fisiere[position++].getAbsolutePath()));
				} catch (IOException e1) {
				    e1.printStackTrace();
				}
				
				Image dimg = imgReclama.getScaledInstance(800, 100,Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				
				lblNewLabel.setIcon(imageIcon);
				
//				System.out.println("X");
			}
		};
		
		Timer t = new Timer(0, listener);
		t.setDelay(4000);
		t.start();
		
		mntmInregistrare = new JMenuItem("Inregistrare");
		mntmInregistrare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int codDeInregistrare;
				
				try {
					codDeInregistrare = Integer.parseInt(JOptionPane.showInputDialog("Cod de inregistrare : "));
				} catch (Exception e2) {
					//JOptionPane.showMessageDialog(null, "Cod invalid !");
					return;
				}
				
				if(codDeInregistrare == COD) {
					mntmDeschidere.setEnabled(true);
					mntmSalvare.setEnabled(true);
					mntmInregistrare.setEnabled(false);
					lblNewLabel.setVisible(false);
					t.stop();
					
					setBounds(100, 100, 827, 500);
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Cod invalid !");
					return;
				}
				
			}
		});
		
		ImageIcon registerIcon = new ImageIcon(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "register.png");
		Image registerImg = registerIcon.getImage();
		Image registerNewImg = registerImg.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);
		registerIcon = new ImageIcon(registerNewImg);
		
		JSeparator separator_2 = new JSeparator();
		mnAjutor.add(separator_2);
		mntmInregistrare.setIcon(registerIcon);
		mnAjutor.add(mntmInregistrare);
		contentPane.setLayout(null);
		contentPane.add(checkBoxFiltrare);
		contentPane.add(btnStergeContactSelectat);
		contentPane.add(btnAdd);
		contentPane.add(btnEditeazaContactSelectat);
		contentPane.add(lblNumarContacte);
		contentPane.add(lblCeva);
		contentPane.add(lblOrdonare);
		contentPane.add(lblFiltrare);
		contentPane.add(comboBox);
		contentPane.add(lblFiltru);
		contentPane.add(textFieldFiltru);
		contentPane.add(comboBox_1);
		contentPane.add(btnFiltreaza);
		contentPane.add(btnOrdoneaza);
		contentPane.add(lblNewLabel);
		
		list = new JList<Contact>();
		//contentPane.add(list);
		list.setBounds(new Rectangle(374, 160, 421, 239));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(new Rectangle(374, 160, 421, 239));
		scrollPane.setViewportView(list);
		
		contentPane.add(scrollPane);
		
		list.setModel(modelLista);
	}
	
	private void setIcon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(new File("").getAbsolutePath() + File.separator + "icons" + File.separator + "contact.png"));
	}
	
	public static void splashScreen() {
		
		SplashScreen splash = new SplashScreen();
		
		splash.setVisible(true);
		
		try {
			Thread.sleep(3500);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		splash.setVisible(false);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		splashScreen();
		
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Fereastra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Fereastra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Fereastra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Fereastra.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fereastra frame = new Fereastra();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
