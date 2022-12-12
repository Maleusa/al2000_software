package ui.stateMachine;

import javax.swing.JPanel;


public abstract class Page  extends JPanel {
	public abstract void addStateMachine(StateMachine stateMachine);
	public abstract void changeState(String EVENT);
}
