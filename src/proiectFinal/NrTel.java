package proiectFinal;

import java.io.Serializable;

abstract class NrTel implements Comparable<Object>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6698667023310060036L;
	String nrTelefon;
	
	public NrTel(String nrTelefon) {
		this.nrTelefon = nrTelefon;
	}

	public abstract boolean validareNumar();
	
	public boolean equals(Object o) {
		
		if(o == null || !(o instanceof NrTel)) {
			return false;
		}
		
		NrTel t = (NrTel) o;
		
		return nrTelefon.equals(t.nrTelefon);
	}
	
	public int compareTo(Object o) {
		
		NrTel t = (NrTel) o;
		return nrTelefon.compareTo(t.nrTelefon);
	}
	
	public String toString() {
		return nrTelefon;
	}
}
