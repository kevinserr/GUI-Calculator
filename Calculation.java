package calculator;

import javafx.scene.control.TextArea;

public class Calculation {
	
	public String add(double a, double b) {
		return String.valueOf(a+b);
	}
	
	public String subtract(double a, double b) {
		return String.valueOf(a-b);
	}
	
	public String multiply(double a, double b) {
		return String.valueOf(a*b);
	}
	
	public String divide(double a, double b) {
		return String.valueOf(a/b);
	}
	
	public void clearAllText(TextArea tx) {
		tx.setText("");
	}
	
	public void deleteLastCharacter(TextArea screen) {
		screen.setText(screen.getText().substring(0, screen.getText().length()-1));
	}
	
	public static void main(String[] args) {
		System.out.println(subtract(9,9));
	}
	
}
