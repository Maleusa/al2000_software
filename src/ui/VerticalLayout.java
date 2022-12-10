package ui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.Hashtable;

public class VerticalLayout implements LayoutManager{

/**
* The horizontal alignment constant that designates centering. Also used to designate center anchoring.
*/
  public final static int CENTER=0;
/**
* The horizontal alignment constant that designates right justification.
*/
  public final static int RIGHT=1;
/**
* The horizontal alignment constant that designates left justification.
*/
  public final static int LEFT=2;
/**
* The horizontal alignment constant that designates stretching the component horizontally.
*/
  public final static int BOTH=3;

/**
* The anchoring constant that designates anchoring to the top of the display area
*/
  public final static int TOP=1;
/**
* The anchoring constant that designates anchoring to the bottom of the display area
*/
  public final static int BOTTOM=2;
  private int vgap; //the vertical vgap between components...defaults to 5
  private int alignment; //LEFT, RIGHT, CENTER or BOTH...how the components are justified
  private int anchor; //TOP, BOTTOM or CENTER ...where are the components positioned in an overlarge space
  private Hashtable comps;

//Constructors
/**
* Constructs an instance of VerticalLayout with a vertical vgap of 5 pixels, horizontal centering and anchored to
* the top of the display area.
*/
   public VerticalLayout(){
	   this(5,CENTER,TOP);
   }
   public VerticalLayout(int vgap,int alignment,int anchor){
	   this.vgap=vgap; this.alignment=alignment; this.anchor=anchor;
   }
@Override
public void addLayoutComponent(String name, Component comp) {
	// TODO Auto-generated method stub
	
}
@Override
public void removeLayoutComponent(Component comp) {
	// TODO Auto-generated method stub
	
}
@Override
public Dimension preferredLayoutSize(Container parent) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public Dimension minimumLayoutSize(Container parent) {
	// TODO Auto-generated method stub
	return null;
}
@Override
public void layoutContainer(Container parent) {
	// TODO Auto-generated method stub
	
}
}
