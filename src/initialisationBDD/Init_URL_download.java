package initialisationBDD;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class Init_URL_download {
	
	private String url;
	private PrintWriter fis;
	private Path path;
	
	public Init_URL_download(String nature) throws IOException {	
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
		fis.close();
		return this.path;
	}

}
