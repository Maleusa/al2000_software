package initialisationBDD;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ToPushBDD {
	
	private File foldername;
	private File[] listOfFiles;
	private BufferedReader br;

	public ToPushBDD(String folder) throws FileNotFoundException {
		foldername = new File(folder);
		listOfFiles = foldername.listFiles();
		
	}
	public void fileBrowser() throws IOException {
		for (File file : listOfFiles) {
		    if (file.isFile()) {
		        br = new BufferedReader(new FileReader(file));
		        preparerProprietes(br);
		    }
		}
	}
	public void preparerProprietes(BufferedReader br) throws IOException {
		String Aprocess = br.readLine();
		System.out.println(""+ Aprocess);
	}	
}
