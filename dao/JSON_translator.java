package dao;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JSON_translator {
	
    private JSONParser parser;
    private FileSystem fs = FileSystems.getDefault();
    private int index;
    private String[] processed;
    private ArrayList<String> actors;
    private ArrayList<String> real;
    private ArrayList<String> movieGenres;
    private ArrayList<String> descripteurs;
    private ArrayList<String> imageURL ;
    
    public JSON_translator(int i) {
    	parser = new JSONParser();
    	this.index = i;
    	actors= new ArrayList<>();
    	real = new ArrayList<>();
    	movieGenres = new ArrayList<>();
    	descripteurs = new ArrayList<>();
    	imageURL = new ArrayList<>();
    }
    
    public Tuple2 translateTitleIdDate(String s) throws Exception {
    	Object obj = parser.parse(s);
		JSONObject obj1 = (JSONObject) obj;
		byte[] title = ((String)obj1.get("title")).getBytes(StandardCharsets.UTF_8);
		long id = (long) (obj1.get("id"));
		byte[] date = ((String)obj1.get("release_date")).getBytes(StandardCharsets.UTF_8);
		byte[] description = ((String)obj1.get("overview")).getBytes(StandardCharsets.UTF_8);
		
		JSONArray obj2 = (JSONArray)obj1.get("genres");
		for (Object object : obj2) {		
			String s3 = (String)((HashMap) object).get("name");
			byte [] genres = s3.getBytes(StandardCharsets.UTF_8);
			String genresUTF8 = new String(genres, StandardCharsets.UTF_8);
			movieGenres.add(genresUTF8);
		}
		
		String utf8EncodedStringTitle = new String(title, StandardCharsets.UTF_8);
		String utf8EncodedStringDate = new String(date, StandardCharsets.UTF_8);
		String utf8EncodedStringDesc = new String(description, StandardCharsets.UTF_8);
		
		Path path = fs.getPath("C:\\Users\\Kilian\\Documents\\"+ this.index +".txt");
		File file = new File(path.toString());
		FileOutputStream fos = new FileOutputStream(file);
		OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
		BufferedWriter bw = new BufferedWriter(osw);
		bw.write(utf8EncodedStringTitle);
		bw.newLine();
		bw.write(Integer.toString((int) id));
		bw.newLine();
		bw.write(utf8EncodedStringDesc);
		bw.newLine();
		bw.write(utf8EncodedStringDate);
		bw.newLine();
		for (String s8 : movieGenres) {
			bw.write(s8);
			bw.newLine();
		}
		bw.close();
		
		this.processed = new String[4];
		this.processed[0] = (Integer.toString((int) id));
		this.processed[1] = utf8EncodedStringTitle;
		this.processed[2] = utf8EncodedStringDesc;
		this.processed[3] = utf8EncodedStringDate;
		Tuple2 tuple = new Tuple2(this.processed, this.movieGenres);
		
		return tuple;
    }
    
	public Tuple2 translateCredits(String s) throws Exception {
		Object obj = JSONValue.parse(s); 
		JSONObject obj1 = (JSONObject)obj;
		JSONArray obj2 = (JSONArray)obj1.get("cast");
		for (Object object : obj2) {		
			String s3 = (String)((HashMap) object).get("name");
			byte [] cast = s3.getBytes(StandardCharsets.UTF_8);
			String castUTF8 = new String(cast, StandardCharsets.UTF_8);
			actors.add(castUTF8);		
		}
		JSONArray obj3 = (JSONArray)obj1.get("crew");
		for (Object object : obj3) {
			if(((String)((HashMap) object).get("job")).equals("director")||((String)((HashMap) object).get("job")).equals("Director")) {
				String s3 = (String)((HashMap) object).get("name");
				byte [] crew = s3.getBytes(StandardCharsets.UTF_8);
				String crewUTF8 = new String(crew, StandardCharsets.UTF_8);
				real.add(crewUTF8);	
			}
		}
		
		Tuple2 tuple = new Tuple2(this.actors, this.real);
		return tuple;
	}
	
	public ArrayList<String> translateKeywords(String s) throws Exception {
		Object obj = JSONValue.parse(s); 
		JSONObject obj1 = (JSONObject)obj;
		JSONArray obj2 = (JSONArray)obj1.get("keywords");
		for (Object object : obj2) {		
			String s3 = (String)((HashMap) object).get("name");
			byte [] keywords = s3.getBytes(StandardCharsets.UTF_8);
			String keywordsUTF8 = new String(keywords, StandardCharsets.UTF_8);
			this.descripteurs.add(keywordsUTF8);	
		}
		return this.descripteurs;
	}
	
	public String translateImages(String s) throws Exception {
		Object obj = JSONValue.parse(s);
		JSONObject obj1 = (JSONObject)obj;
		JSONArray obj2 = (JSONArray)obj1.get("posters");
		imageURL.add(0, "https://www.publicdomainpictures.net/pictures/280000/velka/not-found-image-15383864787lu.jpg");
		for (Object object : obj2) {
			try {
				String s3 = "https://image.tmdb.org/t/p/w500" + (String)((HashMap) object).get("file_path");
				byte[] file_path = s3.getBytes(StandardCharsets.UTF_8);
				String file_pathUTF8 = new String(file_path, StandardCharsets.UTF_8);
				this.imageURL.add(file_pathUTF8);
			}
			catch (Exception e) {
				continue;
			}
		}
		if (imageURL.size()<2) {
			return imageURL.get(0);
			
		}
		else
			return imageURL.get(1);
	}
}
