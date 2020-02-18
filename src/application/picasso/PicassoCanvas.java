package application.picasso;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class PicassoCanvas extends Canvas  {
	
	private volatile WritableImage image = null;
	private volatile boolean jeCalcule = false;
	public static final int MAX = 500000;
	private double widthTemp = 0;
	private double heightTemp = 0;
	private boolean bloque = false;

	PicassoCanvas() {
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
		if (widthTemp == 0 && heightTemp == 0){
			widthTemp = width;
			heightTemp = height;
		}
		super.setWidth(width);
		super.setHeight(height);
		if (width != widthTemp && height != heightTemp){
			widthTemp = width;
			heightTemp = height;
			peindre();   
		}
		paint();
	}

	public void paint() {
		if (!bloque) {
			GraphicsContext gc = getGraphicsContext2D();
			gc.setStroke(Color.BLACK);
			gc.clearRect(0, 0, getWidth(), getHeight());
			
			if (image!=null)
		    	gc.drawImage(image, 0, 0, getWidth(), getHeight());
		    else if (!jeCalcule) gc.strokeText("Rien à afficher. lancer le calcul par le menu \"Art Abstrait\"", 10, getHeight()/2);
		    if (jeCalcule) gc.strokeText("Calcul en cours...", 10, getHeight()/2);
		} else {
			GraphicsContext gc = this.getGraphicsContext2D();
			if (jeCalcule) {	
				gc.setFill(Color.WHITE);
				gc.fillRect(0,0, getWidth(), getHeight());

				for(int i=0; i < MAX; ++i) {
					gc.setFill(new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random()));
					gc.fillRect((int)(Math.random()*getWidth()), (int)(Math.random()*getHeight()), (int)(Math.random()*100), (int)(Math.random()*100));
				}
		    }
		}
	}
    
    public void peindre(){
    	if (!bloque) {
	    	Task<Void> task = new Task<Void>() {
	            @Override 
	            protected Void call() throws Exception {
	            	Canvas canvas = new Canvas(getWidth(), getHeight());
	                jeCalcule = true;
	        		paint(); 
		        		
	        		GraphicsContext gc = canvas.getGraphicsContext2D();
	        		gc.setFill(Color.WHITE);
	        		gc.fillRect(0,0, canvas.getWidth(), canvas.getHeight());
	
	        		for(int i=0; i < MAX; ++i) {
	        			gc.setFill(new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), (float)Math.random()));
	        			gc.fillRect((int)(Math.random()*canvas.getWidth()), (int)(Math.random()*canvas.getHeight()), (int)(Math.random()*100), (int)(Math.random()*100));
	        		}
	        		
	        		SnapshotParameters parameters = new SnapshotParameters();
	        		parameters.setFill(Color.TRANSPARENT);
	
		        	Platform.runLater(() -> {
	        			image = canvas.snapshot(parameters, image);
	        			jeCalcule = false;
	    	    		paint();
	        		});
	                return null;
	            }
	
	            @Override protected void succeeded() {
	                super.succeeded();
	                updateMessage("Done!");
	                System.out.println("Done !");
	            }
	
	            @Override protected void cancelled() {
	                super.cancelled();
	                updateMessage("Cancelled!");
	                System.out.println("Cancelled !");
	            }
	
	            @Override protected void failed() {
	                super.failed();
	                updateMessage("Failed!");
	                System.out.println("Failed !");
	            }
	        };
	    	new Thread(task).start();
    	} else {
    		jeCalcule = true;
    		paint();
    		jeCalcule = false;
    	}
	}
}
