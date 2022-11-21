package initialisationBDD;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import javax.swing.SwingWorker;


public class Downloader extends SwingWorker<String,String> {
	public static final int CHUNK_SIZE = 1024;
	
	URL url;
	int content_length;
	BufferedInputStream in;
	
	String filename;
	File temp;
	FileOutputStream out;
	
	boolean isRunning;
		
	public Downloader(String uri) {
		try {
			url = new URL(uri);
			
			URLConnection connection = url.openConnection();
			content_length = connection.getContentLength();
			
			in = new BufferedInputStream(connection.getInputStream());
			
			String[] path = url.getFile().split("/");
			String[] pathcorrected = path[3].split("\\?");
			filename = pathcorrected[0] + "Aprocess";
			System.out.println(pathcorrected[0]);
			temp = File.createTempFile(filename, ".part");
			out = new FileOutputStream(temp);
		}
		catch(MalformedURLException e) { throw new RuntimeException(e); }
		catch(IOException e) { throw new RuntimeException(e); }
	}
	
	public String toString() {
		return url.toString();
	}
	
	public String download() throws Exception {
		String filename = doInBackground();
		return filename;
	}
	
	public void setIsRunning(boolean newValue) {
		this.isRunning = newValue;
	}

	@Override
	protected String doInBackground() throws Exception {
		byte buffer[] = new byte[CHUNK_SIZE];
		int size = 0;
		int count = 0;
		isRunning = true;
		
		while(true) {
			while(!isRunning) {
				Thread.sleep(100);
			}
			try { count = in.read(buffer, 0, CHUNK_SIZE); }
			catch(IOException e) { continue; }

			if(count < 0) { break; }
			
			try { out.write(buffer, 0, count); }
			catch(IOException e) { continue; }
			
			size += count;
			setProgress(100*size/content_length);
			Thread.sleep(1000);
		}
		
		if(size < content_length) {
			temp.delete();
			throw new InterruptedException();
		}
		
		try {
			Files.copy(temp.toPath(), new File(filename).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
			temp.delete();
		}
		catch(IOException e){
			throw new InterruptedException();
		}
		return filename;
	}
	
	public String urlFile(String nature) throws IOException {
		Init_URL_download Aprocess = new Init_URL_download(nature);
		Path path = Aprocess.creerFichier(nature);
        File file = new File(path.toString());
 
        BufferedReader br = new BufferedReader(new FileReader(file));
        String url;
		while ((url = br.readLine()) != null) {
        	Downloader thread_dl = new Downloader(url);
        	thread_dl.execute();
		}
		br.close();
		String[] foldernameScattered = path.toString().split("/");
		String foldername = "";
		for (int i = 0 ; i < foldernameScattered.length - 1 ; i++) {
			foldername += foldernameScattered[i];
		}
		
		return foldername;
	}
}
