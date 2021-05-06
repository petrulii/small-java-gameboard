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
		System.out.println("Appel creerCoup() (" + ligne + "," + colonne + ")");
		if (aire_jeu.coupValide(ligne, colonne)) {
			aire_jeu.creerCoup(ligne, colonne);
		}
		aire_graphique.repaint();
		if (aire_jeu.gameOver()) {
			System.out.println("Bye!");
			System.exit(0);
		}
	}

	public void toucheR() {
		aire_jeu.refaireCoup();
		aire_graphique.repaint();
	}

	public void toucheA() {
		aire_jeu.annulerCoup();
		aire_graphique.repaint();
	}

	public void toucheE() {
		// exporter historique coups
		aire_jeu.sauvegarderHistoriqueCoups();
	}

	public void toucheM() {
		// importer historique coups
	}
}