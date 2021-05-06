import Modele.AireJeu;
import Vue.InterfaceGraphique;

public class Jeu {
	
	public static void main(String[] str) {
		AireJeu aire_jeu = new AireJeu(6,7);
		InterfaceGraphique.demarrer(aire_jeu);
	}
}