package calculator;

import java.text.DecimalFormat;

import javafx.scene.control.TextArea;

public class Calculation {
	
	//static Calculation calc = new Calculation();
	
	private DecimalFormat df = new DecimalFormat("0.0");
	
	public String add(double a, double b) {
		return String.valueOf(df.format(a+b));
	}
	
	public String subtract(double a, double b) {
		return String.valueOf(df.format(a-b));
	}
	
	public String multiply(double a, double b) {
		return String.valueOf(df.format(a*b));
	}
	
	public String divide(double a, double b) {
		return String.valueOf(df.format(a/b));
	}
	
	public void clearAllText(TextArea tx) {
		tx.setText("");
	}
	
	public void deleteLastCharacter(TextArea screen) {
		screen.setText(screen.getText().substring(0, screen.getText().length()-1));
	}
	
	public static void main(String[] args) {
		//System.out.println(calc.add(9.89,9));
	}
	
}
