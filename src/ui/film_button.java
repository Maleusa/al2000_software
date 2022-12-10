package ui;


import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.net.URL;
import java.net.URLConnection;

public class film_button extends JButton{
	private URL url;
	
	BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
	    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
	    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
	    return outputImage;
	}
	
	public film_button(int width, int height, String imageUrl) {
	
	ImageIcon icon;
	BufferedImage image;
	BufferedImage imageResize;
	
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
}
