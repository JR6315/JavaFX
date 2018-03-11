package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;

class MyCanvas extends Canvas {

	MyCanvas() {
		super(640, 480);
	}

	@Override
	public double minHeight(double width)
	{
		return 64;
	}

	@Override
	public double maxHeight(double width)
	{
		return 1000;
	}

	@Override
	public double prefHeight(double width)
	{
		return minHeight(width);
	}

	@Override
	public double minWidth(double height)
	{
		return 0;
	}

	@Override
	public double maxWidth(double height)
	{
		return 10000;
	}

	@Override
	public boolean isResizable()
	{
		return true;
	}

	@Override
	public void resize(double width, double height)
	{
		//System.out.println("width = " + width + " height = " + height);
		super.setWidth(width);
		super.setHeight(height);
		paint();   

	}

	public void paint() {
		GraphicsContext gc = getGraphicsContext2D();
		gc.setFill(Color.WHITE);
		gc.fillRect(0, 0, getWidth(), getHeight());
		gc.setStroke(Color.BLUE);
		gc.setLineWidth(2);

		int pas = 2;
		int x = 0;
		for(int i= 0; i< getWidth(); ) {
			gc.strokeLine(i, x, i+=pas, x=(int)(Math.random()*getHeight()));
		}
	}

}

public class FXOscillo extends Application {
	MyCanvas canvas;

	@Override
	public void start(Stage primaryStage) {
		try {

			canvas = new MyCanvas();
			BorderPane root = new BorderPane();
			root.setCenter(canvas);
			Label label = new Label("Appuyer sur le bouton pour mettre à jour l'affichage");
			label.setMaxHeight(Double.MAX_VALUE);
			label.setMaxWidth(Double.MAX_VALUE);
			root.setBottom(label);


			Button btn = new Button("RAFRAICHIR");
			btn.setOnAction(actionEvent -> { canvas.paint(); });
			btn.setMaxHeight(Double.MAX_VALUE);
			btn.setMaxWidth(Double.MAX_VALUE);

			root.setRight(btn);

			Scene scene = new Scene(root, 640, 480);

			primaryStage.setOnCloseRequest(e -> { 
				Alert alert = new Alert(AlertType.ERROR, "Etes-vous de vouloir quitter ?", ButtonType.YES, ButtonType.NO);
				alert.showAndWait();

				if (alert.getResult() != ButtonType.YES) {
					e.consume();
				}
			});

			primaryStage.setTitle("FXOscilleSimple 0.1");
			primaryStage.setScene(scene);

			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
