package dao;

import java.net.URL;
import java.net.URLConnection;
import java.net.MalformedURLException;

import java.io.File;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import javax.swing.SwingWorker;


public class Downloader extends SwingWorker<String,String> {
	public static final int CHUNK_SIZE = 1024;
	
	URL url;
	int content_length;
	BufferedInputStream in;
	
	String filename;
	File temp;
	FileOutputStream out;
	Path dir;
	boolean isRunning;
	
	public Downloader () {
		
	}
		
	public Downloader(String uri, Path dir) {
		try {
			url = new URL(uri);
			
			URLConnection connection = url.openConnection();
			content_length = connection.getContentLength();
			
			in = new BufferedInputStream(connection.getInputStream());
			
			String[] path = url.getFile().split("/");
			String[] pathcorrected = path[3].split("\\?");
			filename = pathcorrected[0] + "a.JSON";
			temp = File.createTempFile(filename, ".part");
			this.dir = dir;
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
				Thread.sleep(10);
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
			Files.copy(temp.toPath(), new File(dir.toString()+ "\\" + filename).toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
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
        try {
            Path path2 = Paths.get("C:\\Users\\Kilian\\Documents\\Films" + nature);
            dir = Files.createDirectories(path2);

        } 
        catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage());
        }
		/*while ((url = br.readLine()) != null) {
			try {
	        	Downloader thread_dl = new Downloader(url, dir);
	        	thread_dl.download();
			}
	        catch(Exception e) {
	        	continue;
	        }
		}*/
		br.close();
		return (dir.toString()+ "\\");
	}
}