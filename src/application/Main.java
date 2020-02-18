package application;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			// BorderLayout
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			Tooltip.install(root, new Tooltip("The panel may change button size"));
			
			Button btn = new Button("CENTRE");
			btn.setOnAction(e -> { 
				System.out.println("Clic sur le bouton CENTRE"); 
				launchPopin("Clic sur le bouton CENTRE"); 
			});
			maximize(btn);
			root.setCenter(btn);
			
			btn = new Button("HAUT");
			btn.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("Clic sur le bouton HAUT");
					launchPopin("Clic sur le bouton HAUT");
				}
			});
			maximize(btn);
			root.setTop(btn);
			
			EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					System.out.println("Clic sur le bouton " + ((Button) event.getSource()).getText());
					launchPopin("Clic sur le bouton " + ((Button) event.getSource()).getText());
				}
			};
			btn = new Button("BAS");
			btn.setOnAction(handler);
			maximize(btn);
			root.setBottom(btn);
			
			btn = new Button("GAUCHE");
			btn.setOnAction(handler);
			maximize(btn);
			root.setLeft(btn);
			
			btn = new Button("DROIT");
			btn.setOnAction(handler::handle);
			maximize(btn);
			root.setRight(btn);
			
			setStage(primaryStage, scene, "JavaFX Border Layout");
			
			
			// Flow Layout
			/*FlowPane root2 = new FlowPane();
			Scene scene2 = new Scene(root2);
			Tooltip.install(root2, new Tooltip("The panel cannot change button size"));
			
			for (int i = 0; i<10; ++i) {
				btn = new Button(Math.random()<0.5?"Bouton":"BouBouton");
				// maximize(btn); // ne fait rien
				root2.getChildren().add(btn);
			}
			
			setStage(new Stage(), scene2, "JavaFX Flow Layout");
			
			// Tile Layout
			TilePane root3 = new TilePane();
			Scene scene3 = new Scene(root3);
			Tooltip.install(root3, new Tooltip("The panel may change button size"));
			
			for (int i = 0; i<10; ++i) {
				btn = new Button(Math.random()<0.5?"Bouton":"BouBouton");
				 maximize(btn); // ne fait rien
				root3.getChildren().add(btn);
			}
			
			setStage(new Stage(), scene3, "JavaFX Tile Layout");
			
			// HBox
			HBox root4 = new HBox();
			Scene scene4 = new Scene(root4);
			Tooltip.install(root4, new Tooltip("The panel may change button vertical size"));
			
			for (int i = 0; i<5; ++i) {
				btn = new Button(Math.random()<0.5?"Bouton":"BouBouton");
				 //maximize(btn); // ne fait rien
				root4.getChildren().add(btn);
			}
			
			setStage(new Stage(), scene4, "JavaFX HBox Layout");
			
			
			// VBox
			VBox root5 = new VBox();
			Scene scene5 = new Scene(root5);
			Tooltip.install(root5, new Tooltip("The panel may change button horizontal size"));

			for (int i = 0; i<5; ++i) {
				btn = new Button(Math.random()<0.5?"Bouton":"BouBouton");
				//maximize(btn); // ne fait rien
				root5.getChildren().add(btn);
			}

			setStage(new Stage(), scene5, "JavaFX VBox Layout");
			
			*/
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void setStage(Stage stage, Scene scene, String title) {
		stage.setScene(scene);
		stage.setTitle(title);
		stage.show();
	}
	
	private void maximize(Region region) {
		region.setMaxWidth(Double.MAX_VALUE);
		region.setMaxHeight(Double.MAX_VALUE);
	}
	
	private void launchPopin(String message) {
		Alert alert = new Alert(AlertType.INFORMATION, message, ButtonType.OK);
		alert.showAndWait();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
