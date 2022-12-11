package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChoiceReturn extends JPanel{
	public ChoiceReturn(JFrame jF) {
		JButton ReturnMovie, ReturnDamagedMovie;
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JLabel label = new JLabel("Merci pour votre confiance.");
		Font font = new Font("TimesRoman", Font.BOLD, 18);
		label.setFont(font);
		
		
		this.setLayout(new BorderLayout());
		this.add(northPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(label);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerPanel.add(ReturnDamagedMovie = new JButton("Return Damaged Movie"));
		centerPanel.add(ReturnMovie = new JButton("Return Movie"));
		
		ReturnMovie.addActionListener(new ActionListener() {
       	 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(ReturnMovie)) {
					JOptionPane.showMessageDialog(jF, "Id cb : \nId Abo :");
		        }
			}
  	});
		
		ReturnDamagedMovie.addActionListener(new ActionListener() {
	       	 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(ReturnDamagedMovie)) {
					JOptionPane.showMessageDialog(jF, "Id cb : \nId Abo :");
		        }
			}
  	});
		
	}
}
