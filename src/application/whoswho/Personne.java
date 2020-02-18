package application.whoswho;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Classe Personne
 * @author JRIBEYRE
 */
public class Personne implements Serializable {

	
	
	/** nom */
	private transient SimpleStringProperty nom;
	
	/** prenom */
	private transient SimpleStringProperty prenom;
	
	/** language */
	private transient SimpleStringProperty language;
	
	/** annee */
	private transient SimpleIntegerProperty annee;
	
	/**
	 * Default Constructor
	 */
	public Personne() {
		
	}
	
	/**
	 * Custom Constructor
	 * @param nom
	 * @param prenom
	 * @param language
	 * @param annee
	 */
	public Personne (String nom, String prenom, String language, Integer annee){
		this.nom = new SimpleStringProperty(nom);
		this.prenom = new SimpleStringProperty(prenom);
		this.language = new SimpleStringProperty(language);
		this.annee = new SimpleIntegerProperty(annee);
	}

	/**
	 * Getter Nom
	 * @return String
	 */
	public String getNom() {
		return nom.get();
	}

	/**
	 * Setter Nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom.set(nom);
	}

	/**
	 * Getter Prenom
	 * @return String
	 */
	public String getPrenom() {
		return prenom.get();
	}

	/**
	 * Setter prenom
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom.set(prenom);
	}

	/**
	 * Getter Language
	 * @return String
	 */
	public String getLanguage() {
		return language.get();
	}

	/**
	 * Setter language
	 * @param language
	 */
	public void setLanguage(String language) {
		this.language.set(language);
	}

	/**
	 * Getter annee
	 * @return annee
	 */ 
	public Integer getAnnee() {
		return annee.get();
	}

	/**
	 * Setter annee
	 * @param annee
	 */
	public void setAnnee(Integer annee) {
		this.annee.set(annee);
	}
	
	/**
	 * Write Objet into File
	 * @param s
	 * @throws IOException
	 */
	private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getNom());
        s.writeUTF(getPrenom());
        s.writeUTF(getLanguage());
        s.writeInt(getAnnee());
    }
	
	/**
	 * Read Object from File
	 * @param s
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        nom = new SimpleStringProperty(s.readUTF());
        prenom = new SimpleStringProperty(s.readUTF());
        language = new SimpleStringProperty(s.readUTF());
        annee = new SimpleIntegerProperty(s.readInt());
    }
	
	/**
	 * toString method
	 */
	@Override
	public String toString() {
		return "Personne [nom=" + nom + ", prenom=" + prenom + ", language="
				+ language + ", annee=" + annee + "]";
	}
}
