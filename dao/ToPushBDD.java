package dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class ToPushBDD {
	
	File foldername;
	File[] listOfFiles;
	BufferedReader br;

	public ToPushBDD(String folder) throws FileNotFoundException {
		foldername = new File(folder);
		this.listOfFiles = foldername.listFiles();	
	}
	
	public File[] getListOfFiles() {
		return this.listOfFiles;
	}
	
	public void initFile(int i) throws IOException {
		File file = listOfFiles[i];
		if (file.isFile()) {
			FileInputStream fis = new FileInputStream(file.getAbsolutePath());
		   	InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
	        br = new BufferedReader(isr);
		}
	}
	
	public String preparerProprietes(int i) throws IOException {
		this.initFile(i);
		String Aprocess = br.readLine();
		return Aprocess;
	}	
}
