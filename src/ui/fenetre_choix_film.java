package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.net.URL;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class fenetre_choix_film extends JPanel{
	JPanel panel_parametre = new JPanel();
	JPanel panel_account = new JPanel();
	JPanel panel_film = new JPanel();
	JLabel label_id = new JLabel("Bienvenue Thibaud");
	JTextArea jRecherche = new JTextArea();
	URL urlImage = getClass().getResource("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.istockphoto.com%2Ffr%2Fvectoriel%2Fic%25C3%25B4ne-de-signe-de-loupe-dans-le-style-transparent-illustration-vectorielle-de-gm1151843591-312287164&psig=AOvVaw1W3afp449S0X3c0uttQDH-&ust=1669125617056000&source=images&cd=vfe&ved=2ahUKEwj7s5jNt7_7AhUEFFkFHYDyAkkQjRx6BAgAEAo");
	Icon icone = new ImageIcon(urlImage);
	JButton button_recherche = new JButton(icone);
	
	public fenetre_choix_film() {
		panel_account.add(label_id);
		label_id.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_id.setForeground(Color.WHITE);
		panel_parametre.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel_parametre.add(button_recherche);
		panel_parametre.add(jRecherche);
		jRecherche.setPreferredSize(new Dimension(100,20));
		super.setLayout(new BorderLayout());
		super.add(panel_account, BorderLayout.WEST);
		panel_account.setBackground(Color.red);
		super.add(panel_parametre, BorderLayout.NORTH);
		panel_parametre.setBackground(Color.BLUE);
		super.add(panel_film, BorderLayout.CENTER);
		panel_film.setBackground(Color.GREEN);
	}
}
