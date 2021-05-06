package Modele;

public class AireJeu {
	int nb_lignes = 0;
	int nb_colonnes = 0;
	public int[][] grille;		// 0 - vide, 1 - gaufre, 2 - poison
	int positionX = 0;
	int positionY = 0;

	public AireJeu(int lignes, int colonnes) {
		this.nb_colonnes = colonnes;
		this.nb_lignes = lignes;
		this.grille = new int[lignes][colonnes];
		for (int i = 0; i < nb_lignes; i++) {
			for (int j = 0; j < nb_colonnes; j++) {
				grille[i][j] = 1;
			}
		}
		grille[positionX][positionY] = 2;
	}

	public void mange(int ligne, int colonne) {
		if (((ligne <= nb_lignes) && (ligne >= 0)) && ((colonne <= nb_colonnes) && (colonne >= 0))) {
			for (int i = ligne; i < nb_lignes; i++) {
				for (int j = colonne; j < nb_colonnes; j++) {
					grille[i][j] = 0;
				}
			}
		}
	}
	
	public boolean gameOver() {
		return false;
	}
	
	public int[][] getGrille() {
		return grille;
	}
	
	public int getNbLignes() {
		return nb_lignes;
	}
	
	public int getNbColonnes() {
		return nb_colonnes;
	}
	
	public int getPositionX() {
		return positionX;
	}
	
	public int getPositionY() {
		return positionY;
	}

}