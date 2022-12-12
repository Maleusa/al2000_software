package ui;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MovieButton extends JButton{
	private URL url;
	private ImageIcon icon;
	private BufferedImage image;
	private BufferedImage imageResize;
	private int height, width;
	
	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    return outputImage;
	}
	
	public MovieButton(int width, int height, String imageUrl) {
	this.height = height;
	this.width = width;
		try {
			this.url = new URL(imageUrl); //"https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg"
			image = ImageIO.read(url);
			imageResize = resizeImage(image,width,height);
			icon = new ImageIcon(imageResize);
			this.setIcon(icon);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setUrl(String imageUrl) {
		try {
			this.url=new URL(imageUrl);
			image = ImageIO.read(url);
			imageResize = resizeImage(image,width,height);
			icon = new ImageIcon(imageResize);
			this.setIcon(icon);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
