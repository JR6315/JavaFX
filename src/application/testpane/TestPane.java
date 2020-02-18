package application.testpane;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

public class TestPane extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("FX TestPane - BorderPane");
		
		BorderPane root = new BorderPane();
		
		Button btn = new Button("TOP");
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setMaxHeight(Double.MAX_VALUE);
		
		Button btn8 = new Button("TOP2");
		btn8.setMaxWidth(Double.MAX_VALUE);
		btn8.setMaxHeight(Double.MAX_VALUE);
		
		GridPane grid = new GridPane();
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(50);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(50);
		 
		grid.setRowIndex(btn, 0);
		grid.setRowIndex(btn8, 0);
		grid.setColumnIndex(btn, 0);
		grid.setColumnIndex(btn8, 1);
		
		grid.getColumnConstraints().add(column1);
		grid.getColumnConstraints().add(column2);
		
		grid.getChildren().add(btn);
		grid.getChildren().add(btn8);
		
		root.setTop(grid);
		
		Button btn2 = new Button("LEFT");
		btn2.setMaxWidth(Double.MAX_VALUE);
		btn2.setMaxHeight(Double.MAX_VALUE);
		
		root.setLeft(btn2);
		
		Button btn3 = new Button("CENTER");
		btn3.setMaxWidth(Double.MAX_VALUE);
		btn3.setMaxHeight(Double.MAX_VALUE);
		
		root.setCenter(btn3);
		
		Button btn4 = new Button("RIGHT");
		btn4.setMaxWidth(Double.MAX_VALUE);
		btn4.setMaxHeight(Double.MAX_VALUE);
		
		root.setRight(btn4);
		
		Button btn5 = new Button("BOTTOM");
		btn5.setMaxWidth(Double.MAX_VALUE);
		btn5.setMaxHeight(Double.MAX_VALUE);
		
		root.setBottom(btn5);
		
		primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
        
        Stage s = new Stage();
        s.setTitle("FX TestPane - FlowPane");
        
        FlowPane pane = new FlowPane();
        
        Button btn6 = null;
        for(int i = 0; i <= 10; i++) {
        	btn6 = new Button("" + i);
        	btn6.setMaxWidth(Double.MAX_VALUE);
    		btn6.setMaxHeight(Double.MAX_VALUE);
        	pane.getChildren().add(btn6);        
        }
        
        s.setScene(new Scene(pane, 400, 300));
        s.show();
        
        Stage s2 = new Stage();
        s2.setTitle("FX TestPane - TilePane");
        
        TilePane pane2 = new TilePane();
        
        Button btn7 = null;
        for(int i = 0; i <= 10; i++) {
        	btn7 = new Button("" + i);
        	btn7.setMaxWidth(Double.MAX_VALUE);
    		btn7.setMaxHeight(Double.MAX_VALUE);
        	pane2.getChildren().add(btn7);        
        }
        
        s2.setScene(new Scene(pane2, 400, 300));
        s2.show();
        
	}
	
	public static void main(String[] args) {
        launch(args);
    }
}
