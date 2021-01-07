
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.scene.effect.InnerShadow;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class Calculator extends Application {
    private char[] theReadoutCharacters = {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '};
    private Text readoutChanger;
    private boolean isFunctionChosen = false;
    private boolean hasDot = false;
    double theAnswer;
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create the base pane
        GridPane baseGridPane = new GridPane();
        baseGridPane.setGridLinesVisible(false);
        baseGridPane.setVgap(20);
        baseGridPane.setHgap(20);
        baseGridPane.setPadding(new Insets(25));

        //create the calculations pane
        Pane calculationBaseGridPane = new Pane();
        //create the calculation label
        Text readout = new Text(new String(theReadoutCharacters));

        //global reference to readout
        readoutChanger = readout;

        Rectangle readoutBackground = new Rectangle(422, 130, Color.rgb(68, 82, 103));
        baseGridPane.setConstraints(calculationBaseGridPane, 0, 0, 2, 1);
        baseGridPane.setConstraints(readout, 0, 0, 2, 1);
        calculationBaseGridPane.getChildren().add(readoutBackground);
        calculationBaseGridPane.getChildren().add(readout);

        //readout.setFont(Font.font ("Verdana", 60));
        readout.setFill(Color.rgb(255, 255, 255));
        readout.setTextAlignment(TextAlignment.RIGHT);
        readout.setX(25);
        readout.setY(80);

        //create inner shadow for the calculation pane
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setRadius(30.0);
        innerShadow.setOffsetX(0);
        innerShadow.setOffsetY(0);
        innerShadow.setColor(Color.BLACK);
        calculationBaseGridPane.setEffect(innerShadow);

        //create the functions panel
        GridPane functionsPane = new GridPane();
        functionsPane.setGridLinesVisible(false);
        baseGridPane.setConstraints(functionsPane, 1, 1);

        //create the function buttons
        Button addButton = new Button("+");
        Button minusButton = new Button("-");
        Button multiplyButton = new Button("*");
        Button divideButton = new Button("/");
        //Button toBinButton = new Button("2Bin");
        Button toDecButton = new Button("2Dec");
        toDecButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        Button clearButton = new Button("C");
        clearButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        //Button offButton = new Button("PWR");

        //assign the function buttons to the cells
        functionsPane.setConstraints(addButton, 0, 0);
        functionsPane.setConstraints(minusButton, 1, 0);
        functionsPane.setConstraints(multiplyButton, 0, 1);
        functionsPane.setConstraints(divideButton, 1, 1);
        //functionsPane.setConstraints(toBinButton, 0, 2);
        functionsPane.setConstraints(toDecButton, 0, 2, 2, 1);
        functionsPane.setConstraints(clearButton, 0, 3, 2, 1);
        //functionsPane.setConstraints(offButton, 1, 3);

        //style the 3 function buttons because the text is too big
        //toBinButton.setStyle("-fx-font-size: 20px;");
        toDecButton.setStyle("-fx-font-size: 20px;");
        //offButton.setStyle("-fx-font-size: 20px;");

        //add the function buttons to the grid
        functionsPane.getChildren().addAll(
            addButton, minusButton,
            multiplyButton, divideButton,
            /*toBinButton,*/ toDecButton,
            clearButton/*, offButton*/);

        //create the numbers panel
        GridPane numbersPane = new GridPane();
        numbersPane.setGridLinesVisible(false);
        baseGridPane.setConstraints(numbersPane, 0, 1);

        //create the number buttons
        Button button7 = new Button("7");
        Button button8 = new Button("8");
        Button button9 = new Button("9");
        Button button4 = new Button("4");
        Button button5 = new Button("5");
        Button button6 = new Button("6");
        Button button1 = new Button("1");
        Button button2 = new Button("2");
        Button button3 = new Button("3");
        Button button0 = new Button("0");
        Button buttonDec = new Button(".");
        Button buttonEq = new Button("=");

        //assign the number buttons to the cells
        numbersPane.setConstraints(button7, 0, 0);
        numbersPane.setConstraints(button8, 1, 0);
        numbersPane.setConstraints(button9, 2, 0);
        numbersPane.setConstraints(button4, 0, 1);
        numbersPane.setConstraints(button5, 1, 1);
        numbersPane.setConstraints(button6, 2, 1);
        numbersPane.setConstraints(button1, 0, 2);
        numbersPane.setConstraints(button2, 1, 2);
        numbersPane.setConstraints(button3, 2, 2);
        numbersPane.setConstraints(button0, 0, 3);
        numbersPane.setConstraints(buttonDec, 1, 3);
        numbersPane.setConstraints(buttonEq, 2, 3);

        //add the number buttons to the numbersPane
        numbersPane.getChildren().addAll(
            button7, button8, button9,
            button4, button5, button6,
            button1, button2, button3,
            button0, buttonDec, buttonEq);

        baseGridPane.getChildren().addAll(calculationBaseGridPane, numbersPane, functionsPane);
        // Create a scene and place it in the stage
        Scene scene = new Scene(baseGridPane);
        scene.getStylesheets().add("styles/styles.css");
        primaryStage.setTitle("La Calculatrice de Charlie"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.setResizable(false);

        //wire up the events of all the buttons
        addButton.setOnAction(new ButtonHandler());
        minusButton.setOnAction(new ButtonHandler());
        multiplyButton.setOnAction(new ButtonHandler());
        divideButton.setOnAction(new ButtonHandler());
        //toBinButton.setOnAction(new ButtonHandler());
        toDecButton.setOnAction(new ButtonHandler());
        clearButton.setOnAction(new ButtonHandler());
        //offButton.setOnAction(new ButtonHandler());

        button7.setOnAction(new ButtonHandler());
        button8.setOnAction(new ButtonHandler());
        button9.setOnAction(new ButtonHandler());
        button4.setOnAction(new ButtonHandler());
        button5.setOnAction(new ButtonHandler());
        button6.setOnAction(new ButtonHandler());
        button1.setOnAction(new ButtonHandler());
        button2.setOnAction(new ButtonHandler());
        button3.setOnAction(new ButtonHandler());
        button0.setOnAction(new ButtonHandler());
        buttonDec.setOnAction(new ButtonHandler());
        buttonEq.setOnAction(new ButtonHandler());

        //load custom font
        try { 
            final Font f = Font.loadFont(new FileInputStream(new File("fonts/emulogic.TTF")), 40);
            readout.setFont(f); // use this font with our label
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        primaryStage.show(); // Display the stage
    }

    //make inner class to handle events
    public class ButtonHandler implements EventHandler<ActionEvent>
    {

        @Override
        public void handle(ActionEvent e)
        {

            Button sourceButton = (Button)e.getSource(); //type cast the source into a button object
            String theButtonPressed = sourceButton.getText();

            switch(theButtonPressed) {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "0":
                {
                    for(int a = 0; a < theReadoutCharacters.length; a++) {
                        if (theReadoutCharacters[a] == ' ') {
                            theReadoutCharacters[a] = theButtonPressed.charAt(0);
                            break;
                        }
                    }
                    readoutChanger.setText(new String(theReadoutCharacters));
                    break; 
                }
                case ".":
                {
                    for(int a = 0; a < theReadoutCharacters.length; a++) {
                        if (theReadoutCharacters[a] == ' ' && hasDot == false) {
                            theReadoutCharacters[a] = theButtonPressed.charAt(0);
                            hasDot = true;
                            break;
                        }
                    }
                    readoutChanger.setText(new String(theReadoutCharacters));
                    break; 
                }
                case "+":
                case "-":
                case "*":
                case "/":
                {
                    for(int a = 0; a < theReadoutCharacters.length; a++) {
                        if ((theReadoutCharacters[a] == ' ') && (isFunctionChosen == false)) {
                            if (a < 1) break;
                            theReadoutCharacters[a] = theButtonPressed.charAt(0);
                            isFunctionChosen = true;
                            break;
                        }
                    }
                    hasDot = false;
                    readoutChanger.setText(new String(theReadoutCharacters));
                    break; 
                }
                case "=":
                {
                    String firstNumber = "";
                    String secondNumber = "";
                    String theFunction = "";

                    try {
                        for (int a = 0; a < theReadoutCharacters.length; a++) {
                            if (Character.isDigit(theReadoutCharacters[a]) || theReadoutCharacters[a] == '.' || theReadoutCharacters[a] == ' ') {
                                if (theFunction == "") {
                                    firstNumber = firstNumber + Character.toString(theReadoutCharacters[a]);
                                } else {
                                    secondNumber = secondNumber + Character.toString(theReadoutCharacters[a]);
                                }
                            } else {
                                theFunction = Character.toString(theReadoutCharacters[a]);
                            }
                        }

                        if (theFunction.equals("+")) {
                            theAnswer = (Double.parseDouble(firstNumber)) + (Double.parseDouble(secondNumber));
                        } else if (theFunction.equals("-")) {
                            theAnswer = (Double.parseDouble(firstNumber)) - (Double.parseDouble(secondNumber));
                        } else if (theFunction.equals("*")) {
                            theAnswer = (Double.parseDouble(firstNumber)) * (Double.parseDouble(secondNumber));
                        } else if (theFunction.equals("/")) {
                            theAnswer = (Double.parseDouble(firstNumber)) / (Double.parseDouble(secondNumber));
                        }

                        readoutChanger.setText(Double.toString(theAnswer));
                    } catch (Exception x) {
                        readoutChanger.setText("ERROR");
                    }
                    break;
                }

                case "2Dec":
                {
                    theAnswer = 0;
                    int theAnswerInt = 0;
                    int binVal = 1;
                    boolean calcError = false;
                    for (int a = theReadoutCharacters.length - 1; a > - 1; a--) {
                        if (theReadoutCharacters[a] == '1') {
                            theAnswer = theAnswer + binVal;
                            binVal = binVal * 2;
                        } else if (theReadoutCharacters[a] == '0') {
                            binVal = binVal * 2;
                        } else if (theReadoutCharacters[a] == ' '){
                            //do nothing
                        } else {
                            calcError = true;
                            break;
                        }
                    }
                    theAnswerInt = (int)theAnswer;
                    if (!calcError) {
                        readoutChanger.setText(Integer.toString(theAnswerInt));
                    } else {
                        readoutChanger.setText("ERROR");
                    }
                    break;
                }

                case "C":
                {
                    for (int a = 0; a < theReadoutCharacters.length; a++) {
                        theReadoutCharacters[a] = ' ';
                        readoutChanger.setText(new String(theReadoutCharacters));
                        isFunctionChosen = false;
                        hasDot = false;
                    }
                    theAnswer = 0;
                }

            }
        }
    }
        /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

}

