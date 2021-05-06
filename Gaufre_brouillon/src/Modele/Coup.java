package Modele;

/**
 * Un coup est un position ligne, colonne dans la grille de jeu.
 * @author Petrulionyte Ieva, Yu Ran
 * @version 1.0
 */
public class Coup {
	int ligne;
	int colonne;
	
	public Coup(int l, int c) {
		ligne = l;
		colonne = c;
	}
	
	/**
	 * Affichage d'un coup
	 * @return une chaine de caracteres decrivant le coup
	 */
	public String toString() {
		return "( "+ligne+","+colonne+" )";
	}
	
	/**
	 * Renvoie la postion ligne de coup
	 * @return la postion ligne
	 */
	public int getLigne() {	return ligne; }
	/**
	 * Renvoie la postion colonne de coup
	 * @return la postion colonne
	 */
	public int getColonne() { return colonne; }
}
