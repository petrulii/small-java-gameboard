package Controleur;
import Modele.AireJeu;
import Vue.AireGraphique;

public class ControleurMediateur {
	AireJeu aire_jeu;
	AireGraphique aire_graphique;

	public ControleurMediateur(AireJeu a, AireGraphique a_graphique) {
		aire_jeu = a;
		aire_graphique = a_graphique;
	}

	public void toucheSouris(int x, int y) {
		int ligne = y/aire_graphique.getCaseHeight();
		int colonne = x/aire_graphique.getCaseWidth();
		System.out.println("Appel mange() (" + ligne + "," + colonne + ")");
		aire_jeu.mange(ligne, colonne);
		aire_graphique.repaint();
	}
}