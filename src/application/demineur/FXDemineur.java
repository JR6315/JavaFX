package application.demineur;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FX Demineur Class
 * @author JRIBEYRE
 */
public class FXDemineur extends Application {
	
	public static Terrain t = new Terrain();
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		t.initTerrain();
		BorderPane root = new BorderPane();
		
		MenuBar bar = new MenuBar();
		root.setTop(bar);
		
		Menu menu = new Menu("Jeu");
        bar.getMenus().add(menu);
        
        MenuItem item = new MenuItem("Rejouer");
        
        item.setOnAction(actionEvent -> { 
        	t.initTerrain();
        	Cellule c;
            for(int i = 0; i < Terrain.getNbLigne(); i++){
    			for(int j = 0; j < Terrain.getNbColonne(); j++){
    				c = t.getListCellule()[i][j];
    				c.setButtonColor();
    				c.addObserver(t);
    			}
            }
		});
        menu.getItems().add(item);
        menu.getItems().add(new SeparatorMenuItem());
        
        MenuItem item2 = new MenuItem("Quitter"); 
        item2.setOnAction(actionEvent -> { 
        	System.exit(0);
        });
        menu.getItems().add(item2);
        
        GridPane gd = new GridPane();
        gd.setMaxWidth(310);
        gd.setMaxHeight(400);
        
        Button button;
        Cellule c;
		for(int i = 0; i < Terrain.getNbLigne(); i++){
			for(int j = 0; j < Terrain.getNbColonne(); j++){
				c = t.getListCellule()[i][j];
				button = new Button();
				button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
				button.setPadding(new Insets(10));
				c.setButton(button);
				c.setButtonColor();
				c.setEventOnButton();
				c.addObserver(t);
				gd.add(button, i, j);
				
			}
		}
        
		root.setCenter(gd);
		
		Scene scene = new Scene(root, 320, 420);
		
		primaryStage.setTitle("FX Demineur 0.1");
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	/**
	 * Main method
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
