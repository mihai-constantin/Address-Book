package proiectFinal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class Contact implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8864818420649600085L;
	private String nume;
	private String prenume;
	private int zi;
	private int luna;
	private int an;
	NrTel nrTelefon;
	
	public boolean validareNume(String nume) {
		
		if(nume.length() < 2)
			return false;
		
		// verific daca numele este format numai din litere
		
		for(int i = 0; i < nume.length(); i++) {
			if(!Character.isLetter(nume.charAt(i)))
				return false;
		}
		
		return true;
	}
	
	public boolean validarePrenume(String prenume) {
		
		if(prenume.length() < 2)
			return false;
		
		// verific daca prenumele este format numai din litere
		
		for(int i = 0; i < prenume.length(); i++) {
			if(!Character.isLetter(prenume.charAt(i)))
				return false;
		}
		
		return true;
	}
	
	public boolean anBisect(int an) {
		if((an % 4 == 0 && an % 100 != 0) || an % 400 == 0)
			return true;
		
		return false;
	}
	
	public boolean validareData(int zi, int luna, int an) {
		
		if(luna < 1 || luna > 12) {
			System.out.println("Luna invalida !");
			return false;
		}
			
		// determin valoarea maxima a zilei din luna respectiva
		
		int maxZi;
		
		switch (luna) {
			
			case 4:
			case 6:
			case 9:
			case 11:
				maxZi = 30;
				break;
			
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				maxZi = 31;
				break;
	
			default:
				maxZi = anBisect(an) ? 29 : 28;
				break;
		}
		
		if(zi < 1 || zi > maxZi) {
			System.out.println("Zi invalida !");
			return false;
		}
		
		int ziCurenta = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int lunaCurenta = 1 + Calendar.getInstance().get(Calendar.MONTH);
		int anCurent = Calendar.getInstance().get(Calendar.YEAR);
		
		if(an < 0 || an > anCurent) {
			System.out.println("An invalid !");
			return false;
		}
		
		//DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate date1 = LocalDate.of(anCurent, lunaCurenta, ziCurenta);
		LocalDate date2 = LocalDate.of(an, luna, zi);
		
		//System.out.println("date1 : " + dateFormat.format(date1));
		//System.out.println("date2 : " + dateFormat.format(date2));
		
		if(date2.compareTo(date1) > 0) {
			System.out.println("Vii din viitor ?");
			return false;
		}
		
		return true;
	}
	
	public Contact(String nume, String prenume, int zi, int luna, int an, NrTel nrTelefon) {
		
		if(!validareNume(nume)) {
			System.out.println("Nume invalid !");
			return;
		}
		
		this.nume = nume.substring(0, 1).toUpperCase() + nume.substring(1).toLowerCase();
		
		if(!validarePrenume(prenume)) {
			System.out.println("Prenume invalid ! ");
			return;
		}
		
		this.prenume = prenume.substring(0, 1).toUpperCase() + prenume.substring(1).toLowerCase();
		
		if(!validareData(zi, luna, an)) {
			System.out.println("Data invalida ! ");
			return;
		}
		
		this.zi = zi;
		this.luna = luna;
		this.an = an;
		
		if(!nrTelefon.validareNumar()) {
			
			if(nrTelefon instanceof NrFix) {
				System.out.println("Numar de telefon fix invalid !");
			}
			
			if(nrTelefon instanceof NrMobil) {
				System.out.println("Numar de telefon mobil invalid !");
			}
			
			return;
		}
		
		this.nrTelefon = nrTelefon;
	}
	
	public String getNume() {
		return nume;
	}
	
	public String getPrenume() {
		return prenume;
	}
	
	public int getZi() {
		return zi;
	}
	
	public int getLuna() {
		return luna;
	}
	
	public int getAn() {
		return an;
	}
	
	public String getData() {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.of(an, luna, zi);
		
		return dateFormat.format(date);
	}
	
	public String getNrTelefon() {
		return nrTelefon.nrTelefon;
	}
	
	public NrTel getTelefon() {
		return nrTelefon;
	}
	
	
	public void setNume(String nume) {
		this.nume = nume;
	}
	
	public void setPrenume(String prenume) {
		this.prenume = prenume;
	}
	
	public void setZi(int zi) {
		this.zi = zi;
	}
	
	public void setLuna(int luna) {
		this.luna = luna;
	}
	
	public void setAn(int an) {
		this.an = an;
	}
	
	public void setNrTelefon(NrTel nrTelefon) {
		this.nrTelefon = nrTelefon;
	}
	
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof Contact))
			return false;
		
		Contact c = (Contact) o;
		
		boolean numeComun, prenumeComun, telefonComun;
		
		try {
			numeComun = nume.equals(c.nume);
		} catch (Exception e) {
			// TODO: handle exception
			numeComun = false;
		}
		
		try {
			prenumeComun = prenume.equals(c.prenume);
		} catch (Exception e) {
			// TODO: handle exception
			prenumeComun = false;
		}
		
		boolean ziComuna = (zi == c.zi);
		boolean lunaComuna = (luna == c.luna);
		boolean anComun = (an == c.an);
		
		try {
			telefonComun = nrTelefon.equals(c.nrTelefon);
		} catch (Exception e) {
			// TODO: handle exception
			telefonComun = false;
		}
		
		return numeComun && prenumeComun && ziComuna && lunaComuna && anComun && telefonComun;
	}
	
	public String toString() {
		
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate date = LocalDate.of(this.an, this.luna, this.zi);
		
		return this.nume + " " + this.prenume + ", " + dateFormat.format(date) + ", " + this.nrTelefon.toString();
	}

	public static void main(String[] args) {
		
		Contact c1 = new Contact("CONSTANTIN", "MIHAI", 5, 3, 1998, new NrMobil("0729020160"));
		try {
			System.out.println(c1);
		} catch (Exception e) {
			System.out.println("Contactul nu este valid !");
		}
		
		System.out.println("");
		
		Contact c2 = new Contact("conStaNtin", "mIhAi", 5, 3, 1998, new NrMobil("0729020160"));
		try {
			System.out.println(c2);
		} catch (Exception e) {
			System.out.println("Contactul nu este valid !");
		}
		
		System.out.println("");
		
		System.out.println("Contacte identice? : " + c1.equals(c2));
		

	}

}
