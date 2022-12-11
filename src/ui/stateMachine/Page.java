package ui.stateMachine;

import ui.UiRepresentation;

public interface Page {
	public void update(String EVENT,UiRepresentation representation);// on doit prendre des choses en parametre ici
	public void repaint();
	public void addStateMachine(StateMachine stateMachine);
	public void changeState(String EVENT);
}
