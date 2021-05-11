import Modele.AireJeu;
import Vue.InterfaceGraphique;

public class Jeu {
	
	public static void main(String[] str) {
		int lignes = 5;
		int colonnes = 6;
		AireJeu aire_jeu = new AireJeu(lignes, colonnes);
		InterfaceGraphique.demarrer(aire_jeu, colonnes*100, lignes*100);
	}
}