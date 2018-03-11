package application.whoswho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Who's Who App
 * @author JRIBEYRE
 */
public class FXWhosWhoApp extends Application {
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = new BorderPane();
		
		File file = new File("data.dat");
		List<Personne> l = null;
		if (!file.exists()){
			l = createFile();
		}else{
			l = readFile();
		}
		ObservableList<Personne> listObservable = FXCollections.observableList(l);
		
		TableView<Personne> tabView = new TableView<Personne>(listObservable);
		
		TableColumn<Personne, String> firstNameCol = new TableColumn<Personne, String>("Nom");
        firstNameCol.setMinWidth(100);
        firstNameCol.setCellValueFactory(
                new PropertyValueFactory<Personne, String>("nom"));
 
        TableColumn<Personne, String> lastNameCol = new TableColumn<Personne, String>("Prénom");
        lastNameCol.setMinWidth(100);
        lastNameCol.setCellValueFactory(
                new PropertyValueFactory<Personne, String>("prenom"));
 
        TableColumn<Personne, String> languageCol = new TableColumn<Personne, String>("Langage");
        languageCol.setMinWidth(100);
        languageCol.setCellValueFactory(
                new PropertyValueFactory<Personne, String>("language"));
        
        TableColumn<Personne, Integer> anneeCol = new TableColumn<Personne, Integer>("Année");
        anneeCol.setMinWidth(100);
        anneeCol.setCellValueFactory(
                new PropertyValueFactory<Personne, Integer>("annee"));
 
        tabView.getColumns().addAll(Arrays.asList(firstNameCol, lastNameCol, languageCol, anneeCol));
		
		root.setCenter(tabView);
		
		Scene scene = new Scene(root, 400, 400);
		
		primaryStage.setTitle("FX Who's Who 0.1");
		primaryStage.setScene(scene);

		primaryStage.show();
	}
	
	/**
	 * Méthode Main
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Create File
	 * @param l
	 * @throws IOException
	 */
	public static List<Personne> createFile() throws IOException{
		FileOutputStream f = new FileOutputStream("data.dat");
		List<Personne> l = new ArrayList<Personne>();
		l.add(new Personne("TEST01","TEST 01","JAVA",2018));
		l.add(new Personne("TEST02","TEST 02","JAVA",2018));
		l.add(new Personne("TEST03","TEST 03","JAVA",2018));
		l.add(new Personne("TEST04","TEST 04","JAVA",2018));
		l.add(new Personne("TEST05","TEST 05","JAVA",2018));
		l.add(new Personne("TEST06","TEST 06","JAVA",2018));
		l.add(new Personne("TEST07","TEST 07","JAVA",2018));
		l.add(new Personne("TEST08","TEST 08","JAVA",2018));
		l.add(new Personne("TEST09","TEST 09","JAVA",2018));
		l.add(new Personne("TEST10","TEST 10","JAVA",2018));
		l.add(new Personne("TEST11","TEST 11","JAVA",2018));
		l.add(new Personne("TEST12","TEST 12","JAVA",2018));
		l.add(new Personne("TEST13","TEST 13","JAVA",2018));
		l.add(new Personne("TEST14","TEST 14","JAVA",2018));
		l.add(new Personne("TEST15","TEST 15","JAVA",2018));
		l.add(new Personne("TEST16","TEST 16","JAVA",2018));
		l.add(new Personne("TEST17","TEST 17","JAVA",2018));
		l.add(new Personne("TEST18","TEST 18","JAVA",2018));
		ObjectOutputStream out = new ObjectOutputStream(f);
		out.writeObject(l);
		out.close();
		f.close();
		return l;
	}
	
	/**
	 * Read File
	 * @param l
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings("unchecked")
	public static List<Personne> readFile() throws IOException, ClassNotFoundException{
		List<Personne> l = null;
		FileInputStream fin = new FileInputStream("data.dat");
		ObjectInputStream in = new ObjectInputStream(fin);
		l = (List<Personne>) in.readObject();
		in.close();
		fin.close();
		return l;
	}

}
