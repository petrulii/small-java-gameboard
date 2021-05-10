package Modele;

import java.util.ArrayList;

/**
 * Classe principale de modele de jeu qui characterise l'aire de jeu.
 * @author Petrulionyte Ieva
 * @version 1.0
 */
public class AireJeu {
	int nb_lignes = 0;
	int nb_colonnes = 0;
	public int[][] grille;		// 0 - case vide, 1 - case gaufre, 2 - case poison
	int poison_colonne = 0;
	int poison_ligne = 0;
	/**
	* liste pour enregistrer les coups
	*/
	ArrayList<Coup> coups;
	/**
	* liste pour se souvenir des coups ennules en cours d'annulation
	*/
	ArrayList<Coup> coups_annules;

	public AireJeu(int lignes, int colonnes) {
		this.nb_colonnes = colonnes;
		this.nb_lignes = lignes;
		coups = new ArrayList<Coup>();
		coups_annules = new ArrayList<Coup>();
		this.grille = new int[lignes][colonnes];
		for (int i = 0; i < nb_lignes; i++) {
			for (int j = 0; j < nb_colonnes; j++) {
				grille[i][j] = 1;
			}
		}
		grille[poison_ligne][poison_colonne] = 2;
	}

	/**
	 * Execution d'un coup
	 * @param coup : le coup a jouer
	 */
	public void joueCoup(Coup coup) {
		int ligne = coup.getLigne();
		int colonne = coup.getColonne();
		for (int i = ligne; i < nb_lignes; i++) {
			for (int j = colonne; j < nb_colonnes; j++) {
				grille[i][j] = 0;
			}
		}
	}
	
	/**
	 * Creaction d'un coup
	 * @param ligne : la position de ligne
	 * @param ccolonne : la position de colonne
	 */
	public void creerCoup(int ligne, int colonne) {
		// Si le joueur etait en train d'annuler les coups et recommence a jouer.
		if (!coups_annules.isEmpty()) { coups_annules = new ArrayList<Coup>(); }
		// On verifie si le coup est valide.
		if (coupValide(ligne, colonne)) {
			Coup coup = new Coup(ligne, colonne);
			coups.add(coup);
			joueCoup(coup);
		}
	}
	
	/**
	 * Annulation d'un coup
	 */
	public void annulerCoup() {
		if (!coups.isEmpty()) {
			Coup coup = coups.remove((coups.size() - 1));
			coups_annules.add(coup);
			System.out.println("Coup annule: "+coup);
			for (int i = coup.getLigne(); i < nb_lignes; i++) {
				for (int j = coup.getColonne(); j < nb_colonnes; j++) {
					grille[i][j] = 1;
				}
			}
			for(Coup c : coups) {
				for (int i = c.getLigne(); i < nb_lignes; i++) {
					for (int j = c.getColonne(); j < nb_colonnes; j++) {
						grille[i][j] = 0;
					}
				}
			}
		}
	}
	
	/**
	 * Execution d'un coup qui etait le dernier a etre annule
	 */
	public void refaireCoup() {
		if (!coups_annules.isEmpty()) {
			Coup coup = coups_annules.remove((coups_annules.size() - 1));
			System.out.println("Coup refait: "+coup);
			coups.add(coup);
			joueCoup(coup);
		}
	}
	
	/**
	 * Verifier si le coups peut etre jouer
	 * @param l : la position de ligne
	 * @param c : la position de colonne
	 * @return vrai si le coup est valide, faux sinon
	 */
	public boolean coupValide(int ligne, int colonne) {
		return (((ligne < nb_lignes) && (ligne >= 0)) && ((colonne < nb_colonnes) && (colonne >= 0))) && grille[ligne][colonne] != 0;
	}
	
	/**
	 * Le jeu se termine si que le poison reste a manger
	 * @return vrai si le jeu est termine, faux sinon
	 */
	public boolean gameOver() {
		/*boolean res = true;
		for (int i = 0; i < nb_lignes; i++) {
			for (int j = 0; j < nb_colonnes; j++) {
				if (grille[i][j] != 0 && !((i == poison_ligne) && (j == poison_colonne))) {
					//System.out.println("Grille[i][j] : "+grille[i][j]+", i : "+i+", j : "+j+".");
					res = false;
				}
			}
		}*/
		return (grille[poison_ligne][poison_colonne] == 0);
	}
	
	/**
	 * Lit un liste des coups dans un fichier
	 * @param nom_fichier : fichier qui contien un liste des coups
	 */
	public ArrayList<Coup> chargeHistoriqueCoups(String nom_fichier) {
		return HistoriqueCoups.importer(nom_fichier);
	}
	
	/**
	 * Ecrit la liste des coups dans un fichier
	 */
	public void sauvegarderHistoriqueCoups() {
		HistoriqueCoups.exporter(coups);
	}
	
	/**
	 * Renvoie la grille de jeu
	 * @return grille de jeu
	 */
	public int[][] getGrille() { return grille; }
	/**
	 * Renvoie le nombre de lignes dans la grille de jeu
	 * @return le nombre de lignes
	 */
	public int getNbLignes() { return nb_lignes; }
	/**
	 * Renvoie le nombre de colonnes dans la grille de jeu
	 * @return le nombre de colonnes
	 */
	public int getNbColonnes() { return nb_colonnes; }
	/**
	 * Renvoie la position colonne de poison sur la grille de jeu
	 * @return la position colonne de poison
	 */
	public int getPoisonColonne() { return poison_colonne; }
	/**
	 * Renvoie la position ligne de poison sur la grille de jeu
	 * @return la position ligne de poison
	 */
	public int getPoisonLigne() { return poison_ligne; }
	
	/**
	 * Affiche les coups
	 */
	public void afficheCoups() {
		System.out.print("( ");
		for(Coup c : coups) {
			System.out.print(c.toString() + " ");
		}
		System.out.println(")");
	}
	
	/**
	 * Affiche les coups annules
	 */
	public void afficheCoupsAnnules() {
		System.out.print("( ");
		for(Coup c : coups_annules) {
			System.out.print(c.toString() + " ");
		}
		System.out.println(")");
	}
	
	/**
	 * Dit si l'annulation d'un coup est possible coups annules
	 * @return vrai s'il y a des coups a annuler, faux sinon
	 */
	public boolean annulationCoupPossible() {
		return (!coups.isEmpty());
	}
	
	/**
	 * Dit si c'est possible de refaire un coup
	 * @return vrai s'il y a des coups annules, faux sinon
	 */
	public boolean refaireCoupPossible() {
		return (!coups_annules.isEmpty());
	}

}