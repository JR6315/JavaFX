import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
 
class MyButton extends Button implements EventHandler<ActionEvent> { 
	private int number;
	
	public MyButton() { 
		setOnAction(this);
		number = 0;
		setText("0");
	}
	
	@Override
    public void handle(ActionEvent event) {
        ++number;  
        setText(""+number);
    }
	
}

public class FXPustules extends Application {
	int attempt;
	
    public static void main(String[] args) {
        launch(args);
    }
    
 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("FX Pustules");
        
        HBox root = new HBox();
        for (int i =0; i< 10; ++i) {  
    		MyButton btn = new MyButton();
    		root.getChildren().add(btn);
    		HBox.setHgrow(btn, Priority.ALWAYS);
    		btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        }
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(windowEvent -> {
        	Alert alert = new Alert(AlertType.ERROR, "Etes-vous de vouloir quitter ?", ButtonType.YES, ButtonType.NO);
    		alert.showAndWait();

    		if (alert.getResult() != ButtonType.YES) {
    			windowEvent.consume();
    		}      
        	/*if (attempt < 2) {
    	    	// no close
    	    	windowEvent.consume();
    	    	++attempt;
        	}*/
        });

    }
}