package dao;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Iterator;

public class DaoInitUrlDownload {
	
	private String url;
	private PrintWriter fis;
	private Path path;
	
	public DaoInitUrlDownload(String nature) throws IOException {	
		FileSystem fs = FileSystems.getDefault();
		path = fs.getPath("C:\\Users\\Kilian\\Documents\\"+ nature +".txt");
		File file = new File(path.toString());
		fis = new PrintWriter(file, StandardCharsets.UTF_8);
	}
	
	
	public Path creerFichier(String nature) {
		if (nature.equals("title")) {
			for (int i = 4; i<1004; i++) {
				url = ("https://api.themoviedb.org/3/movie/" + i + "?api_key=a40a810bf3954492b1f2e2db6f438935&language=fr-FR").toString();
				fis.println(url);
			}	
		}
		if (nature.equals("credits")) {
			for (int i = 4; i < 1004; i++) {
				url = ("https://api.themoviedb.org/3/movie/" + i + "/credits?api_key=a40a810bf3954492b1f2e2db6f438935&language=fr-FR").toString();
				fis.println(url);
			}
		}
		
		if (nature.equals("tags")) {
			for (int i = 4; i < 1004; i++) {
				url = "https://api.themoviedb.org/3/movie/" + i + "/keywords?api_key=a40a810bf3954492b1f2e2db6f438935".toString();
				fis.println(url);
			}
		}
		
		if (nature.equals("image")) {
			for (int i = 4; i < 1004; i++) {
				url = ("https://api.themoviedb.org/3/movie/"+ i +"/images?api_key=a40a810bf3954492b1f2e2db6f438935&language=fr").toString();
				fis.println(url);			
			}
		}
		fis.close();
		return this.path;
	}

}