package calculator;

/*
 * This is where most of the GUI components will live, all of the calculations will be 
 * done in a different class 
*/

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CalculatorApplication extends Application {
	
	// Strings used for math
	String number1; 
	String number2;
	
	// used to keep track of the screen's display
	boolean resultIsShown = false;

	// Object for calculations 
	Calculation calc = new Calculation();
	
	// new grid pane
	GridPane pane = new GridPane();
	
	// screen is where the output and input will be shown
	TextArea screen = new TextArea();
	
	// buttons used that are not numbers
	Button clear = new Button("C");
	Button delete = new Button("<");
	Button divide = new Button("/");
	Button multiply = new Button("x");
	Button minus = new Button("-");
	Button plus = new Button("+");
	Button equals = new Button("=");
	Button plusMinus = new Button("+/-");
	Button decimal = new Button(".");
	
	// Used to set the prefer width and height of the buttons
	VBox vBox = new VBox();	
	
	// array of buttons used for the numbers
	Button[] numberButtons;
	
	Button[] deleteButtons = {
			clear, delete
	};
	
	Button[] symbolButtons = {
		plus, minus, multiply, divide
	};
	
	boolean isAddition = false, isSubstraction = false,
			isMultiplication =false, isDivision = false; 
	
	// array of booleans containing the operations 
	// to make my life easier
	boolean[] operations = {
			isAddition, isSubstraction, 
			isMultiplication, isDivision
	};
	
	String[] names = {"Addition", "Subtraction", 
			"Multiplication", "Divison"};
	
	public void start(Stage primaryStage) throws Exception {	
			
		Scene scene = new Scene(pane, 300,450);
		
		vBox.setPrefWidth(75);
		vBox.setPrefHeight(75);
		
		// Set the screen so it cannot be edited
		screen.setEditable(false);
		screen.setMouseTransparent(true);
		screen.setFocusTraversable(false);
		
		// add the screen to the scene
		pane.add(screen, 0, 0, 4, 1);
		
		screen.setPrefWidth(34*4); screen.setPrefHeight(vBox.getPrefHeight());
		screen.setStyle("-fx-font: 24 arial;");
			
		primaryStage.setTitle("Calculator");
	
		// calls methods populates the buttons
		populateButtons();
		
		// calls method that adds the buttons to the scene
		addButtonsToScene();
		
		// calls method to set the prefered width and height
		setMinWidthAndHeight();
		
		// calls method to add event listeners
		addEventListeners();
	
		// add the scene to the stage in order to see
		primaryStage.setScene(scene);

		primaryStage.show();
		
	}
	
	/**
	 *	Makes the buttons each with a number from 0-9	
	*/
	private void populateButtons() {
		String numbers= "0123456789";
		
		numberButtons = new Button[numbers.length()];
		
		for (int i = 0; i < numbers.length(); i++) {
			numberButtons[i] = new Button(String.valueOf(numbers.charAt(i)));
			numberButtons[i].setStyle("-fx-font: 24 arial;");
		}
		
	}
	
	/*
	 *	Adds the buttons to the screen in order for the user to use them	 
	*/
	private void addButtonsToScene() {	
		int row = 2;
		int col =2;
		for(int i =numberButtons.length-1; i>=0; i--) {
			if(i >=7 ) {
				pane.add(numberButtons[i], col, row);
				col--;
			}
			if(i == 6) {
				row++;
				col = 2;
			}
			if(i <=6 && i >=4 ) {
				pane.add(numberButtons[i], col, row);
				col--;
			}
			if(i == 3) {
				row++;
				col = 2;
			}
			if(i <=3 && i >=1 ) {
				pane.add(numberButtons[i], col, row);
				col--;
			}
				
		}
		pane.add(numberButtons[0], 1, 5);
		
		pane.add(multiply, 3, 1);
		pane.add(minus, 3, 2);
		pane.add(plus, 3, 3);
		pane.add(equals, 3,4, 3,5);
		pane.add(plusMinus, 0,5);
		pane.add(decimal, 2, 5);
		
		pane.add(clear, 0, 1);
		pane.add(delete, 1, 1);
		pane.add(divide, 2, 1);
		
	}
	
	/*
	 *	Sets the min width and height for each button used
	 *	Makes them all the same size
	 *	Method also sets the style for each of them to a font size of 24 and font 
	 *	family of arial
	*/
	private void setMinWidthAndHeight() {
		for (int i = 0; i < numberButtons.length; i++) {
			numberButtons[i].setMinWidth(vBox.getPrefWidth());
			numberButtons[i].setMinHeight(vBox.getPrefHeight());
		}
		
		clear.setMinWidth(vBox.getPrefWidth()); clear.setMinHeight(vBox.getPrefHeight());
		delete.setMinWidth(vBox.getPrefWidth()); delete.setMinHeight(vBox.getPrefHeight());
		divide.setMinWidth(vBox.getPrefWidth()); divide.setMinHeight(vBox.getPrefHeight());
		multiply.setMinWidth(vBox.getPrefWidth()); multiply.setMinHeight(vBox.getPrefHeight());
		minus.setMinWidth(vBox.getPrefWidth()); minus.setMinHeight(vBox.getPrefHeight());
		plus.setMinWidth(vBox.getPrefWidth()); plus.setMinHeight(vBox.getPrefHeight());
		equals.setMinWidth(vBox.getPrefWidth()); equals.setMinHeight(vBox.getPrefHeight()*2);
		decimal.setMinWidth(vBox.getPrefWidth()); decimal.setMinHeight(vBox.getPrefHeight());
		
		plusMinus.setMinWidth(vBox.getPrefWidth()); plusMinus.setMinHeight(vBox.getPrefHeight());

		
		clear.setStyle("-fx-font: 24 arial;");
		delete.setStyle("-fx-font: 24 arial;");
		divide.setStyle("-fx-font: 24 arial;");
		multiply.setStyle("-fx-font: 24 arial;");
		minus.setStyle("-fx-font: 24 arial;");
		plus.setStyle("-fx-font: 24 arial;");
		equals.setStyle("-fx-font: 24 arial;");
		decimal.setStyle("-fx-font: 24 arial;");
		plusMinus.setStyle("-fx-font: 24 arial;");
		
		

	}
	
	/*
	 *	Adds the event listeners to each button
	 *	This allows the program to receive the value of the button the user pressed 
	*/
	private void addEventListeners() {
		clear.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		        calc.clearAllText(screen);
		    }
		});
		
		decimal.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	screen.appendText(".");
		    }
		});
		
		delete.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	if(!screen.getText().equals("")) {
		    		calc.deleteLastCharacter(screen);
		    	}
		    }
		});
		
		plusMinus.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	if(resultIsShown) {
		    		screen.setText("");
		    		resultIsShown = false;
		    		System.out.println("Result within: "+resultIsShown);
		    	}
		        screen.appendText("-");
		    }
		});
		
		for (int i = 0; i < numberButtons.length; i++) {
			String number = numberButtons[i].getText();
			numberButtons[i].setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			    	if(resultIsShown) {
			    		screen.setText("");
			    		resultIsShown = false;
			    		System.out.println("Result within if statement"+resultIsShown);
			    	}
			    	screen.appendText(number);
			    }
			});
		}
		
		
		for (int i = 0; i < symbolButtons.length; i++) {
			int index = i;
			symbolButtons[i].setOnAction(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			    	if(!screen.getText().equals("")) {
			    		number1 = screen.getText();
				        System.out.println(number1);
				        screen.setText("");
				        operations[index] = true;
				        for (int j = 0; j < operations.length; j++) {
							if(j!=index) {
								operations[j] = false;
							}
						}
				        //System.out.println(operations[index]);
			    	}
			    	else {
			    		for (int j = 0; j < operations.length; j++) {
							operations[j] = false;
							
						}
			    	}
			    	//printBooleanValues();
			    }
			});
		}
		
		equals.setOnAction(new EventHandler<ActionEvent>() {
		    @Override
		    public void handle(ActionEvent event) {
		    	// checks to see if screen is not empty 
		    	if(!screen.getText().equals("")) {
			    	number2 = screen.getText();
			    	screen.setText("");
			    	double num1 = Double.parseDouble(number1);
			    	double num2 = Double.parseDouble(number2);
			    	for (int i = 0; i < operations.length; i++) {
						if(operations[i]) 
						{
							if(names[i].equals("Addition")) {
								String sum = calc.add(num1, num2);
								//System.out.println(sum);
								screen.setText(sum);
							}
							else if(names[i].equals("Subtraction")) {
								System.out.println(calc.subtract(num1, num2));
								screen.setText(calc.subtract(num1, num2));
							}
							else if(names[i].equals("Multiplication")) {
								System.out.println(calc.multiply(num1, num2));
								screen.setText(calc.multiply(num1, num2));
							}
							else if(names[i].equals("Divison")) {
								System.out.println(calc.divide(num1, num2));
								screen.setText(calc.divide(num1, num2));
							}
							resultIsShown = true;
							
						}
					}
		    	}
		    }
		});
		
		
	}
	
	// main method launches the application
	public static void main(String[] args) {
		Application.launch(args);

	}

}
