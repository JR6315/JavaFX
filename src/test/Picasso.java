package test;

import java.util.Random;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Picasso extends Application{ 
    
    class Tache implements Runnable
    {
        private int nbRectangle;
        private GraphicsContext graph;
        private Canvas can;
        private Random rand ;
        private Image image;
        private boolean ready;
        
        /*public int getReady()
        {
            return ready;
        }*/
        
        /*public Image getImage()
        {
            return image;
        }*/
        
        /*public void setReady(boolean val)
        {
            ready = val;
        }*/
        
        public Tache(int nbRectangle, GraphicsContext graph)
        {
            this.graph = graph;
            this.nbRectangle = nbRectangle;
            can = new Canvas(640, 500);
            rand = new Random();
        }
        
        /*public GraphicsContext getGraph()
        {
            return can.getGraphicsContext2D();
        }*/
        
        @Override
        public void run() {
            for(int i = 0; i < nbRectangle; i++)
            {
                can.getGraphicsContext2D().setFill(Color.rgb(randomBetween(0, 255), randomBetween(0, 255), randomBetween(0, 255), rand.nextDouble()));
                can.getGraphicsContext2D().fillRect(randomBetween(0, 500), randomBetween(0, 500), randomBetween(1, 500), randomBetween(1, 500));
            }
            
            
            
            Platform.runLater(() -> {
                SnapshotParameters parameters = new SnapshotParameters();
                parameters.setFill(Color.TRANSPARENT);
                image = can.snapshot(parameters, null);
            
                if(image != null)
                {
                    graph.drawImage(image, 0, 0, 640, 500);
                }
                else
                {
                    graph.strokeText("Pas d'image", 10, 10);
                }
            });
        }

    }
    
    public static void main(String[] args) {
        launch(args);
    }
    
    public int randomBetween(int min, int max)
    {
        return min + (int)(Math.random() * ((max - min) + 1));
    }
    
    @Override
    public void start(Stage primaryStage)
    {
        int largeur = 500;
        int hauteur = 500;
        int nbRectangle = 100000;
        Canvas can = new Canvas( largeur, hauteur);
        BorderPane bp = new BorderPane();
        GraphicsContext graph = can.getGraphicsContext2D();
        Tache tache = new Tache(nbRectangle, graph);
        
        //Creation menu
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Affichage");
    
        MenuItem item1 = new MenuItem("Rafraichir");
        MenuItem item2 = new MenuItem("Quitter");
    
        menu.getItems().add(item1);
        menu.getItems().add(item2);
        menuBar.getMenus().add(menu);
        bp.setTop(menuBar);
        bp.setCenter(can);
        primaryStage.setResizable(false);
        
        item1.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event){
                (new Thread(tache)).start();
            }
        });
        
        primaryStage.setTitle("Jolie tableau enfin si on veut");
        primaryStage.setScene(new Scene(bp, largeur, hauteur));
        primaryStage.show();

    }

}
