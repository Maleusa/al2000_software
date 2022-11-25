package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class liste_film_pref extends JPanel{
	public URL url;
	public JButton jb= new JButton();
	public liste_film_pref() {
	
	ImageIcon icon;
	BufferedImage image;
	this.setLayout(new BorderLayout());
		try {
			url = new URL("https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg");
			image = ImageIO.read(url);
			icon = new ImageIcon(image);
			JButton image1 = new JButton(icon);
			this.add(image1, BorderLayout.CENTER);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//image1.setIcon(icon);
		
		
		
	}
}
