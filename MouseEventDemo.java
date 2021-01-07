
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class MouseEventDemo extends Application
{


    private Stage primaryStage;
    @Override
    public void start(Stage stage) throws Exception
    {
        stage.setTitle("Close Me");

        primaryStage = stage;
        
        //size stage
        stage.setWidth(300);
        stage.setHeight(300);
        
        //make scene
        Scene scene = new Scene(new Pane());
        stage.setScene(scene);

        //wire up events
        scene.setOnMouseMoved(e -> handleMouse(e));
        scene.setOnMouseEntered(e -> handleMouse(e));
        
        stage.setOnCloseRequest(e -> e.consume()); //keeps close button from working
        
        
        
        stage.show();
    }
   
    private void handleMouse(MouseEvent e)
    {
        Scene s = (Scene) e.getSource();
        double cx, cy; //center of scene
        double dx, dy; // distance from center
        
        //computer the center
        cx = s.getWidth() / 2;
        cy = s.getHeight() / 2;
        
        //computer the offset
        dx = cx - e.getX();
        dy = cy - e.getY();
        
        //move the stage
        primaryStage.setX(primaryStage.getX() - dx);
        primaryStage.setY(primaryStage.getY() - dy);

    }
}
