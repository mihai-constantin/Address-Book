package proiectFinal;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.TimerTask;

public class Save extends TimerTask {

	@Override
	public void run() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(Fereastra.fileSave));
			
			for(Contact c : Fereastra.agenda.contacte) {
				System.out.println(c);
			}
			System.out.println("");
			
			oos.writeObject(Fereastra.agenda.contacte);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
