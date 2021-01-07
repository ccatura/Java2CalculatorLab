
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * A simple app to demo event handling
 */
public class ButtonClick2 extends Application
{
    private Button button;

    @Override
    public void start(Stage stage) throws Exception
    {
        //create button
        button = new Button("Click Me");

        //make scene
        Scene scene = new Scene(button, 640, 480);

        //set stage
        stage.setScene(scene);
        stage.setTitle("click the button");

        //wire up the events
        button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent e) {
            
             Button sourceButton = (Button)e.getSource(); //type cast the source into a button object
            System.out.println("thanks: " + sourceButton.getText()); // gets the text from the sourceButton that is written on the button that is clicked
        }
        });

        stage.show();
    }


}
