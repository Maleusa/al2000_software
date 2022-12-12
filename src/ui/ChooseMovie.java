package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

import fc.machine.BluRay;
import fc.machine.BluRayStock;
import fc.machine.Movie;
import fc.machine.QRCode;
import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

/*
 * 
 * Window where user choose movie
 * 
 * 
 * 
 * 
 * 
 */

public class ChooseMovie extends Page{
	
	StateMachine stateMachine;
	
	JButton button_delete;
	JButton button_goBack;
	JLabel label_research;
	JLabel label_QRCODE;
	JLabel label_CD;
	MoviePanel movie_QRCODE;
	MoviePanel movie_CD;
	JPanel jPanel_north = new JPanel();
	JPanel jPanel_north_east = new JPanel();
	JPanel jPanel_north_west = new JPanel();
	JPanel jPanel_center = new JPanel();
	JPanel jPanel_center_top = new JPanel();
	JPanel jPanel_center_down = new JPanel();
	ArrayList<Movie> myListBluRay= new ArrayList<>();
	ArrayList<Movie> myListQRCode= new ArrayList<>();
	JFrame jF;
	public ChooseMovie(JFrame jFrame, StateMachine stateM) {	
		this.jF=jFrame;
		this.stateMachine=stateM;
		/*
		 * North Panel : exit button / GoBack button / MyAccountButton 
		 */
		
		this.setLayout(new BorderLayout());
		this.add(jPanel_north,BorderLayout.NORTH);
		jPanel_north.setLayout(new BorderLayout());
		jPanel_north.add(jPanel_north_east, BorderLayout.EAST);
		jPanel_north_east.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jPanel_north_east.add(button_delete = new JButton("X"));
		jPanel_north.add(jPanel_north_west, BorderLayout.WEST);
		jPanel_north_west.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanel_north_west.add(button_goBack= new JButton("<--"));
		jPanel_north_west.add(label_research = new JLabel("Your research : "));
		
		
		
		this.add(jPanel_center);
		jPanel_center.setLayout(new GridLayout(2,1));
		jPanel_center.add(jPanel_center_top);
		jPanel_center.add(jPanel_center_down);
		jPanel_center_top.setBackground(Color.ORANGE);
		jPanel_center_down.setBackground(Color.ORANGE);											
		jPanel_center_top.setLayout(new BorderLayout());
		jPanel_center_down.setLayout(new BorderLayout());
		jPanel_center_top.add(label_QRCODE = new JLabel("QRCODE Movie"),BorderLayout.NORTH);
		MoviePanel movie_QRCODE = new MoviePanel(myListQRCode,jF);
		jPanel_center_top.add(movie_QRCODE,BorderLayout.CENTER);
		jPanel_center_down.add(label_CD = new JLabel("CD Movie"),BorderLayout.NORTH);
		MoviePanel movie_BLURAY = new MoviePanel(myListBluRay,jF);
		jPanel_center_down.add(movie_BLURAY,BorderLayout.CENTER);
		
		ArrayList<QRCode> qrCodes = (ArrayList<QRCode>) stateMachine.getSearchResult().getRentableMovies();
		BluRayStock stock = (BluRayStock) stateMachine.getBluRayStock();
		ArrayList<BluRay> blurays = stock.getSearchStock();
		
		for(QRCode qr : qrCodes) myListQRCode.add(qr.getMovie());
		for(BluRay bluray : blurays) myListQRCode.add(bluray.getMovie());
		
		button_delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> data = new ArrayList<>();
				data.add(""+stateMachine.getUserModel().getId());
				stateMachine.notifyObserver("SIGN_OUT_EVENT_TYPE", data);
				stateMachine.changeState("TO_WELCOME_PAGE");
			}
		});
		
		button_goBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Page previous = stateMachine.returnToPrevious();
				jF.setContentPane(previous);
				jF.repaint();
				jF.revalidate();	
			}
		});
		
		

	}


	@Override
	public void addStateMachine(StateMachine stateMachine) {
		this.stateMachine=stateMachine;
	}

	@Override
	public void changeState(String EVENT) {
		switch(EVENT) {
		case"TO_WELCOME_PAGE":
			Welcome welPage = new Welcome(jF, stateMachine);
			stateMachine.setCurrentPage(welPage);
			stateMachine.reboot();
			jF.setContentPane(welPage);
			jF.repaint();
			jF.revalidate();
			break;
		}
		
	}
}
