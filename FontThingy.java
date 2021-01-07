
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;


public class FontThingy extends Application {
  public void start(final Stage primaryStage) {
    Group rootGroup = new Group();

    // create a label to show some text
    Label label = new Label("Demo Text");
    try { 
      // load a custom font from a specific location (change path!)
      // 12 is the size to use
      final Font f = Font.loadFont(new FileInputStream(new File("fonts/emulogic.TTF")), 50);
      label.setFont(f); // use this font with our label
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }

    rootGroup.getChildren().add(label); 

    // create scene, add root group and show stage
    Scene scene = new Scene(rootGroup, 640, 480, Color.WHITE);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}