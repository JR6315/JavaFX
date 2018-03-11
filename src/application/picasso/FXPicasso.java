package application.picasso;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination.ModifierValue;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FXPicasso  extends Application {
	
	private PicassoCanvas canvas;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			canvas = new PicassoCanvas();
			BorderPane root = new BorderPane();
			root.setCenter(canvas);
			
			MenuBar menuBar = new MenuBar();
			root.setTop(menuBar);
			
			Menu menu = new Menu("Art Abstrait");
			menuBar.getMenus().add(menu);

			MenuItem item = new MenuItem("_Rafraichir");
			item.setMnemonicParsing(true);
			item.setAccelerator(new KeyCodeCombination(KeyCode.R, ModifierValue.ANY, ModifierValue.DOWN, ModifierValue.ANY, ModifierValue.ANY, ModifierValue.ANY));
			item.setOnAction(actionEvent -> { 
				canvas.peindre(); 
			});
			menu.getItems().add(item);
			
			MenuItem itemClose = new MenuItem("_Fermer");
			itemClose.setMnemonicParsing(true);
			itemClose.setAccelerator(new KeyCodeCombination(KeyCode.Q, ModifierValue.ANY, ModifierValue.DOWN, ModifierValue.ANY, ModifierValue.ANY, ModifierValue.ANY));
			itemClose.setOnAction(actionEvent -> 
				close(actionEvent)
			);
			menu.getItems().add(itemClose);

			Scene scene = new Scene(root, 640, 480);

			primaryStage.setOnCloseRequest(e -> 
				close(e)
			);

			primaryStage.setTitle("FXPicasso 0.1");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}		
	}
	
	private void close(Event e){
		Alert alert = new Alert(AlertType.ERROR, "Etes-vous de vouloir quitter ?", ButtonType.YES, ButtonType.NO);
		alert.showAndWait();

		if (alert.getResult() != ButtonType.YES) {
			e.consume();
		}else{
			if (e instanceof ActionEvent){
				Platform.exit();
			}
		}
	}

	public static void main(String[] args) {
		launch(args);
	}	
}
