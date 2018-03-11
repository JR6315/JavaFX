package application.demineur;

import java.util.Observable;

import javafx.scene.control.Button;

/**
 * Cellule Class
 * @author JRIBEYRE
 *
 */
public class Cellule extends Observable {

	private int value;
	private Button button;
	private boolean selected = false;
	
	/**
	 * Constructor
	 */
	public Cellule(){}
	
	/**
	 * Constructor
	 * @param value
	 */
	public Cellule(int value){
		this.value = value;
	}
	
	/**
	 * Constructor
	 * @param value
	 * @param button
	 */
	public Cellule(int value, Button button){
		this.value = value;
		this.button = button;
		setEventOnButton();
	}

	/**
	 * Getter Value
	 * @return
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Setter Value
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Getter Button
	 * @return
	 */
	public Button getButton() {
		return button;
	}
	
	/**
	 * Setter Button
	 * @param button
	 */
	public void setButton(Button button) {
		this.button = button;
		
	}
	
	/**
	 * Getter Selected
	 * @return
	 */
	public boolean isSelected() {
		return selected;
	}
	
	/**
	 * Setter Selected
	 * @param selected
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
		setChanged();
		notifyObservers();
	}
	
	/**
	 * setEventOnButton
	 */
	public void setEventOnButton(){
		if (button != null){
			button.setOnAction(actionEvent -> {
				setSelected(true);
			});
		}
	}
	
	/**
	 * setButtonColor
	 */
	public void setButtonColor(){
		if (button != null){
			if (selected){
				switch (value) {
				case -1 : 
					button.setStyle("-fx-background-color: #ff0000;-fx-border-color: #000000;");
					break;
				case 0 : 
					button.setStyle("-fx-background-color: #ffffff;-fx-border-color: #000000;");
					break;
				default  : 
					button.setStyle("-fx-background-color: #00ff00;-fx-border-color: #000000;");
					break;
				}
				button.setText(String.valueOf(value));
			}else{
				button.setStyle("-fx-background-color: #f4f4f4;-fx-border-color: #000000;");
				button.setText("");
			}
		}
	}
}
