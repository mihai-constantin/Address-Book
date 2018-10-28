package proiectFinal;

public class NrMobil extends NrTel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1681555178051593427L;

	public NrMobil(String nrTelefonMobil) {
		super(nrTelefonMobil);
	}

	public boolean validareNumar() {
		
		if(nrTelefon.length() != 10)
			return false;
		
		for(int i = 0; i < nrTelefon.length(); i++) {
			if(!Character.isDigit(nrTelefon.charAt(i)))
				return false;
		}
		
		if(!nrTelefon.substring(0, 2).equals("07"))
			return false;
		
		return true;
	}
}
