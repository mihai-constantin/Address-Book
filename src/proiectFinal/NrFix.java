package proiectFinal;

public class NrFix extends NrTel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -745630705729433694L;

	public NrFix(String nrTelefonFix) {
		super(nrTelefonFix);
	}
	
	public boolean validareNumar() {
		
		if(nrTelefon.length() != 10)
			return false;
		
		for(int i = 0; i < nrTelefon.length(); i++) {
			if(!Character.isDigit(nrTelefon.charAt(i)))
				return false;
		}
		
		if(!nrTelefon.substring(0, 2).equals("02") && !nrTelefon.substring(0, 2).equals("03"))
			return false;
		
		return true;
	}

}
