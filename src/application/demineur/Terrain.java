package application.demineur;

import java.util.Observable;
import java.util.Observer;

/**
 * Class Terrain
 * @author JRIBEYRE
 *
 */
public class Terrain implements Observer {

	private Cellule[][] listCellule;
	private static final int NB_LIGNE = 10;
	private static final int NB_COLONNE = 10;
	private static final int NB_MINE = 10;

	/**
	 * Construtor
	 */
	public Terrain(){}

	/**
	 * Getter
	 * @return
	 */
	public Cellule[][] getListCellule() {
		return listCellule;
	}

	/**
	 * Setter
	 * @param listCellule
	 */
	public void setListCellule(Cellule[][] listCellule) {
		this.listCellule = listCellule;
	}
	
	/**
	 * Getter
	 * @return
	 */
	public static int getNbLigne() {
		return NB_LIGNE;
	}

	/**
	 * Getter
	 * @return
	 */
	public static int getNbColonne() {
		return NB_COLONNE;
	}

	/**
	 * Getter
	 * @return
	 */
	public static int getNbMine() {
		return NB_MINE;
	}
	
	/**
	 * initTerrain
	 */
	public void initTerrain(){
		if (listCellule == null){
			listCellule = new Cellule[NB_LIGNE][NB_COLONNE];
		}
		for (int i = 0; i < NB_LIGNE; i++){
			for (int j = 0; j < NB_COLONNE; j++){
				if (listCellule[i][j] != null && listCellule[i][j].getButton() != null){
					listCellule[i][j] = new Cellule(0, listCellule[i][j].getButton());
				}else{
					listCellule[i][j] = new Cellule(0);
				}
			}
		}
		int ligne;
		int colonne;
		for (int i = 0; i < NB_MINE; i++){
			ligne = random(NB_LIGNE);
			colonne = random(NB_COLONNE);
			while (listCellule[ligne][colonne].equals(new Cellule(-1))){
				ligne = random(NB_LIGNE);
				colonne = random(NB_COLONNE);
			}
			if (listCellule[ligne][colonne] != null && listCellule[ligne][colonne].getButton() != null){
				listCellule[ligne][colonne] = new Cellule(-1, listCellule[ligne][colonne].getButton());
			}else{
				listCellule[ligne][colonne] = new Cellule(-1);
			}
		}
		for (int i = 0; i < NB_LIGNE; i++){
			for (int j = 0; j < NB_COLONNE; j++){
				if (listCellule[i][j].getValue() == -1){
					calculNbMine(i, j);
				}
			}
		}
		
	}
	
	/**
	 * Random
	 * @param limite
	 * @return int
	 */
	public int random(int limite){
		int val = (int) (Math.random()*limite);
		while (val < 0 && val > limite){
			val = (int) (Math.random()*limite);
		}
		return val;
	}
	
	/**
	 * calcul Nombre Mine
	 * @param x
	 * @param y
	 */
	private void calculNbMine(int x, int y){
		if (testCalculateCoord(x-1, y)){
			listCellule[x-1][y].setValue(listCellule[x-1][y].getValue() + 1);
		}
		if (testCalculateCoord(x-1, y+1)){
			listCellule[x-1][y+1].setValue(listCellule[x-1][y+1].getValue() + 1);
		}
		if (testCalculateCoord(x-1, y-1)){
			listCellule[x-1][y-1].setValue(listCellule[x-1][y-1].getValue() + 1);
		}
		if (testCalculateCoord(x, y+1)){
			listCellule[x][y+1].setValue(listCellule[x][y+1].getValue() + 1);
		}
		if (testCalculateCoord(x, y-1)){
			listCellule[x][y-1].setValue(listCellule[x][y-1].getValue() + 1);
		}
		if (testCalculateCoord(x+1, y+1)){
			listCellule[x+1][y+1].setValue(listCellule[x+1][y+1].getValue() + 1);
		}
		if (testCalculateCoord(x+1, y)){
			listCellule[x+1][y].setValue(listCellule[x+1][y].getValue() + 1);
		}
		if (testCalculateCoord(x+1, y-1)){
			listCellule[x+1][y-1].setValue(listCellule[x+1][y-1].getValue() + 1);
		}
	}
		
	/**
	 * testCalculateCoord
	 * @param p1
	 * @param p2
	 * @return
	 */
	private boolean testCalculateCoord(int p1, int p2){
		return p1 >= 0 && p2 >= 0 && p1 < NB_LIGNE && p2 < NB_COLONNE && listCellule[p1][p2].getValue() != -1;
	}
	
	/**
	 * testSelectedCoord
	 * @param p1
	 * @param p2
	 * @return
	 */
	private boolean testSelectedCoord(int p1, int p2){
		return p1 >= 0 && p2 >= 0 && p1 < NB_LIGNE && p2 < NB_COLONNE && !listCellule[p1][p2].isSelected() && listCellule[p1][p2].getValue() >= 0 && listCellule[p1][p2].getValue() != -1;
	}

	@Override
	public void update(Observable o, Object arg) {
		Cellule c = (Cellule) o;
		c.setButtonColor();
		openAllCase(c);
	}
	
	/**
	 * openAllCase
	 * @param c
	 */
	private void openAllCase(Cellule c){
		Coordonnees coord = findCelluleInTerrain(c);
		if (listCellule[coord.getLigne()][coord.getColonne()].getValue() == 0){
			if (testSelectedCoord(coord.getLigne()-1, coord.getColonne())){
				listCellule[coord.getLigne()-1][coord.getColonne()].setSelected(true);
			}
			if (testSelectedCoord(coord.getLigne()-1, coord.getColonne()+1)){
				listCellule[coord.getLigne()-1][coord.getColonne()+1].setSelected(true);
			}
			if (testSelectedCoord(coord.getLigne()-1, coord.getColonne()-1)){
				listCellule[coord.getLigne()-1][coord.getColonne()-1].setSelected(true);
			}
			if (testSelectedCoord(coord.getLigne(), coord.getColonne()+1)){
				listCellule[coord.getLigne()][coord.getColonne()+1].setSelected(true);
			}
			if (testSelectedCoord(coord.getLigne(), coord.getColonne()-1)){
				listCellule[coord.getLigne()][coord.getColonne()-1].setSelected(true);
			}
			if (testSelectedCoord(coord.getLigne()+1, coord.getColonne()+1)){
				listCellule[coord.getLigne()+1][coord.getColonne()+1].setSelected(true);
			}
			if (testSelectedCoord(coord.getLigne()+1, coord.getColonne())){
				listCellule[coord.getLigne()+1][coord.getColonne()].setSelected(true);
			}
			if (testSelectedCoord(coord.getLigne()+1, coord.getColonne()-1)){
				listCellule[coord.getLigne()+1][coord.getColonne()-1].setSelected(true);
			}	
		}
	}
	
	/**
	 * Find Cell In Terrain
	 * @param c
	 * @return
	 */
	private Coordonnees findCelluleInTerrain(Cellule c){
		Coordonnees coord = new Coordonnees();
		if (listCellule != null){
			for (int i = 0; i < NB_LIGNE; i++){
				for (int j = 0; j < NB_COLONNE; j++){
					if (listCellule[i][j] == c){
						coord.setLigne(i);
						coord.setColonne(j);
						break;
					}
				}
			}
		}
		return coord;
	}
	
	/**
	 * Inner Class Coordonnees
	 * @author JRIBEYRE
	 *
	 */
	public class Coordonnees {
		/** attributs */
		private int ligne = 0;
		private int colonne = 0;
		
		/**
		 * Get Ligne
		 * @return
		 */
		public int getLigne() {
			return ligne;
		}
		
		/**
		 * Set Ligne
		 * @param ligne
		 */
		public void setLigne(int ligne) {
			this.ligne = ligne;
		}
		
		/**
		 * Get colonne
		 * @return
		 */
		public int getColonne() {
			return colonne;
		}
		
		/**
		 * Set colonne
		 * @param colonne
		 */
		public void setColonne(int colonne) {
			this.colonne = colonne;
		}
	}
}
