package calculator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextArea;

public class ButtonEvents implements EventHandler<ActionEvent> {
	
	EventHandler<ActionEvent> del;
	Calculation calc = new Calculation();
	TextArea txt; 
	
	ButtonEvents(TextArea txt){
		
	}
	
	public void handle(ActionEvent event) {
		
	}
	

}
