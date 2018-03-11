import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
 
class Handle implements EventHandler<ActionEvent> {
	 
    @Override
    public void handle(ActionEvent event) {
        System.out.println("Hello World!");  
    }
}

public class Tuto1 extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        //azzzzs ""primaryStage.initStyle(StageStyle.UTILITY);
        System.out.println(primaryStage.getStyle());
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        /*btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");  
            }
        });*/
        
        //btn.setOnAction((ActionEvent e) -> { System.out.println("Hello World!"); });
        Handle handle = new Handle();
        btn.setOnAction(handle::handle);
        //btn.setOnAction(handle);
        // 
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        Button otherButton = new Button("autre essai");
        root.getChildren().add(otherButton);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }
}