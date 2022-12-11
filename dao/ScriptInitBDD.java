package dao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.sql.PreparedStatement;
import org.apache.ibatis.jdbc.ScriptRunner;

public class ScriptInitBDD {

	public static void main(String[] args) throws Exception {
		LocalDBconnection aaa = LocalDBconnection.getInstance();
		aaa.connect();
		ScriptRunner sr = new ScriptRunner(aaa.getBase());
		Reader reader = new BufferedReader(new FileReader("C:\\Users\\Kilian\\Documents\\al2000.sql"));
		sr.runScript(reader);
		
		//get all films id, titles, desc, release date
		Downloader downloader = new Downloader();
		String foldernametitle = downloader.urlFile("title");
		ToPushBDD titles = new ToPushBDD(foldernametitle);		
		File[] titlefiles = titles.getListOfFiles();
		
		//get all films credits
		String foldernamecredits = downloader.urlFile("credits");
		ToPushBDD credits = new ToPushBDD(foldernamecredits);
		File[] creditsfiles = credits.getListOfFiles();
		//get all films keywords
		String foldernamekeywords = downloader.urlFile("tags");
		
		ToPushBDD keywords = new ToPushBDD(foldernamekeywords);
		
		File[] keywordsfiles = keywords.getListOfFiles();
		
		
		//get URL for movie image
		String foldernameimages = downloader.urlFile("image");
		ToPushBDD images = new ToPushBDD(foldernameimages);
		File[] imagesfiles = images.getListOfFiles();
		
		PreparedStatement init = aaa.getBase().prepareStatement("INSERT INTO AL2000S VALUES (?, ?)");
		init.setInt(1, 1);
		init.setString(2, "Grenoble");
		init.executeUpdate();
		aaa.getBase().commit();
		init.clearParameters();
		
		PreparedStatement pstmt;
		PreparedStatement stockFilmsPhysiques;
		PreparedStatement filmPhysique;
		int i = 0;
		for (File file : titlefiles) {//titlefiles
			JSON_translator json_trs = new JSON_translator(i);
			Tuple2 detailsAndGenres = 
					new Tuple2(json_trs.translateTitleIdDate(titles.preparerProprietes(i)));
			
			String [] generalInformations = (String[]) detailsAndGenres.getFirst();
			java.sql.Date date = Date.valueOf(generalInformations[3]);
			int anneeDate = 1900 + date.getYear();
				
			ArrayList<String> genres = (ArrayList<String>)detailsAndGenres.getSecond();
			Tuple2 creditsDetails = 
					new Tuple2(json_trs.translateCredits(credits.preparerProprietes(i)));
			
			ArrayList<String> actors = (ArrayList<String>)creditsDetails.getFirst();
			ArrayList<String> real = (ArrayList<String>)creditsDetails.getSecond();
			String actorsList = Arrays.toString(actors.toArray()).replace("[", "").replace("]", "");
			String realList = Arrays.toString(real.toArray()).replace("[", "").replace("]", "");
			
			ArrayList<String> tags = 
					new ArrayList<>(json_trs.translateKeywords(keywords.preparerProprietes(i)));
			
			String imageURL = json_trs.translateImages(images.preparerProprietes(i));
			
			//push � la base de donn�es
			System.out.println("J'ins�re le film " + generalInformations[1] + " dont l'ID est " + generalInformations[0]);;
			pstmt = aaa.getBase().prepareStatement("INSERT INTO LESFILMS VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, Integer.valueOf(generalInformations[0]));
		    pstmt.setString(2, generalInformations[1]);
		    pstmt.setString(3, realList);
		    pstmt.setString(4, actorsList);
		    pstmt.setString(5, generalInformations[2]);
		    pstmt.setInt(6, anneeDate);
		    pstmt.setString(7, imageURL);
		    int compt = 0;
		    for (String s1 : genres) {
		    	compt++;
		    	pstmt.setString(7+compt, s1);
		    }
		    if (compt<7) {
		    	compt++;
		    	for (; compt <=7; compt++ ) {
		    		pstmt.setString(7+compt, "NULL");
		    	}
		    }
		    pstmt.executeUpdate();
		    aaa.getBase().commit();
			pstmt.clearParameters();
		    if (i<101) {
		    	filmPhysique = aaa.getBase().prepareStatement("INSERT INTO FILMPHYSIQUE VALUES (?,?,?)");
		    	filmPhysique.setInt(1, i+1);
		    	filmPhysique.setString(2, "RENTABLE");
		    	filmPhysique.setInt(3, Integer.valueOf(generalInformations[0]));
		    	filmPhysique.executeUpdate();
			    aaa.getBase().commit();
				filmPhysique.clearParameters();
				
		    	stockFilmsPhysiques = aaa.getBase().prepareStatement("INSERT INTO STOCKFILMSPHYSIQUES VALUES (?,?)");
		    	stockFilmsPhysiques.setInt(1, 1);
		    	stockFilmsPhysiques.setInt(2, i+1);
		    	stockFilmsPhysiques.executeUpdate();
			    aaa.getBase().commit();
				stockFilmsPhysiques.clearParameters();
		    }
		    
			i++;
		}
		aaa.disconnect();
	}
	
}
