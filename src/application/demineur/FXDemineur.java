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
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

/**
 * FX Demineur Class
 * @author JRIBEYRE
 */
public class FXDemineur extends Application {
	
	public static Terrain t = new Terrain();
	
	public final static int TAILLE_CELLULE = 40;
	
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
            for(int i = 0; i < Terrain.getNbColonne(); i++){
    			for(int j = 0; j < Terrain.getNbLigne(); j++){
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
        for (int i = 0; i < Terrain.getNbColonne(); i++){
        	gd.getColumnConstraints().add(new ColumnConstraints(TAILLE_CELLULE));
        }
        for (int i = 0; i < Terrain.getNbLigne(); i++){
        	gd.getRowConstraints().add(new RowConstraints(TAILLE_CELLULE));
        }

        gd.setMaxWidth(TAILLE_CELLULE * Terrain.getNbColonne());
        gd.setMaxHeight(TAILLE_CELLULE * Terrain.getNbLigne());
        
        Button button;
        Cellule c;
		for(int i = 0; i < Terrain.getNbColonne(); i++){
			for(int j = 0; j < Terrain.getNbLigne(); j++){
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
		
		Scene scene = new Scene(root, TAILLE_CELLULE * Terrain.getNbColonne(), TAILLE_CELLULE * Terrain.getNbLigne() + 25);
		
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
