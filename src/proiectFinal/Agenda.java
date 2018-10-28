package proiectFinal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Agenda {
	
	List<Contact> contacte = new ArrayList<Contact>();
	Map<CriteriuOrdonare, Comparator<Object>> map = new LinkedHashMap<CriteriuOrdonare, Comparator<Object>>();
	
	Predicate<Contact> filtruCurent;
	CriteriuOrdonare criteriuCurent = CriteriuOrdonare.DUPA_NUME;
	
	private boolean contactDuplicat;
	
	public Agenda() {
		
		map.put(CriteriuOrdonare.DUPA_NUME, new Comparator<Object>() {
				
			public int compare(Object o1, Object o2) {
				Contact c1 = (Contact) o1;
				Contact c2 = (Contact) o2;
				
				return c1.getNume().compareTo(c2.getNume());
			}
			
		});
		
		map.put(CriteriuOrdonare.DUPA_PRENUME, new Comparator<Object>() {
			
			public int compare(Object o1, Object o2) {
				Contact c1 = (Contact) o1;
				Contact c2 = (Contact) o2;
				
				return c1.getPrenume().compareTo(c2.getPrenume());
			}
			
		});
		
		map.put(CriteriuOrdonare.DUPA_TELEFON, new Comparator<Object>() {
			
			public int compare(Object o1, Object o2) {
				Contact c1 = (Contact) o1;
				Contact c2 = (Contact) o2;
				
				return c1.getTelefon().compareTo(c2.getTelefon());
			}
		});
		
		map.put(CriteriuOrdonare.DUPA_DATA, new Comparator<Object>() {
			
			public int compare(Object o1, Object o2) {
				Contact c1 = (Contact) o1;
				Contact c2 = (Contact) o2;
				
				return c1.getData().compareTo(c2.getData());
			}
		
		});
		
		filtruCurent = p -> p.getNume().length() > 1;
		
	}
	
	public void adaugare(Contact contact) {
		
		boolean add = true;
		
		for(int i = 0; i < contacte.size(); i++) { 
			
			if(contacte.get(i).equals(contact)) {
				System.out.println("contactul duplicat : " + contact);
				add = false;
				contactDuplicat = true;
			}
		}
		
		if(add) {
			contactDuplicat = false;
			contacte.add(contact);
		}
		
	}
	
	public void filtruDefault() {
		filtruCurent = p -> p.getNume().length() > 1;
	}
	
	public void filtreazaNrFix() {
		filtruCurent = p -> p.nrTelefon.nrTelefon.substring(0, 2).equals("02") || p.nrTelefon.nrTelefon.substring(0, 2).equals("03");
	}
	
	public void filtreazaNrMobil() {
		filtruCurent = p -> p.nrTelefon.nrTelefon.substring(0, 2).equals("07");
		
	}
	
	public void filtreazaNascutiAstazi() {
		
		int ziCurenta = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		int lunaCurenta = 1 + Calendar.getInstance().get(Calendar.MONTH);
		
		filtruCurent = p -> p.getLuna() == lunaCurenta && p.getZi() == ziCurenta;
		
	}
	
	public void filtreazaNascutiLunaCurenta() {
		
		int lunaCurenta = 1 + Calendar.getInstance().get(Calendar.MONTH);
		
		filtruCurent = p -> p.getLuna() == lunaCurenta;
	}
	
	public void filtreazaPersonalizat(String s) {
		
		filtruCurent = p -> p.getNume().toLowerCase().contains(s) || p.getPrenume().toLowerCase().contains(s) || p.getNrTelefon().contains(s);
	}
	
	public void ordoneaza(CriteriuOrdonare criteriuOrdonare) {
		
		criteriuCurent = criteriuOrdonare;
	}
	
	public List<Contact> contactePersonalizate(List<Contact> l) {
		
		return l.stream()
					   .filter(filtruCurent)
					   .sorted(map.get(criteriuCurent))
					   .collect(Collectors.toList());
	}
	
	public List<Contact> contacte() {
		
		return contacte.stream()
					   .filter(filtruCurent)
					   .sorted(map.get(criteriuCurent))
					   .collect(Collectors.toList());
	}
	
	public void printList() {
		
		System.out.println("==========     Lista de contacte din Agenda     ==========");
		for(int i = 0; i < contacte.size(); i++) {
			
			Contact contactDinAgenda = contacte.get(i);
			
			System.out.println(contactDinAgenda);
		}
		
		System.out.println("");
	}
	
	public boolean isContactDuplicat() {
		return contactDuplicat;
	}
	
	public static boolean contactValid(Contact contact) {
		
		if(contact.getNume() == null || contact.getPrenume() == null || contact.getZi() == 0 || contact.nrTelefon == null) {
			return false;
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		
	}

}
