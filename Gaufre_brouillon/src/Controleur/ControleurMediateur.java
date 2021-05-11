package Controleur;
import java.util.ArrayList;
import Modele.AireJeu;
import Modele.Coup;
import Vue.AireGraphique;

/**
 * La classe principale de controleur qui recoit les notifications de la vue et decide qoui faire.
 * @author Petrulionyte Ieva
 * @version 1.0
 */
public class ControleurMediateur {
	AireJeu aire_jeu;
	AireGraphique aire_graphique;
	EtOuIA ia;
	int active_IA = 0;		// 0 - desactiver, 1 - joue premier joueur, 2 - joue deuxieme joueur
	int joueur;				// 1 - premier joueur, 2 - deuxieme joueur

	public ControleurMediateur(AireJeu a, AireGraphique a_graphique) {
		aire_jeu = a;
		aire_graphique = a_graphique;
		joueur = 1;
	}

    /**
     * Effectue une instruction donne apres un click souris.
	 * @param l'instruction a effectuer
	 * @param l'entier coordone x de fenetre graphique
	 * @param l'entier coordone y de fenetre graphique
     */
    public void instructionSouris(String instruction, int x, int y) {
		switch (instruction) {							// "Jouer", ...
			case "Jouer":		// Joue un coup dans la case ou joueur a clicke.
				int ligne = y/aire_graphique.getCaseHeight();
				int colonne = x/aire_graphique.getCaseWidth();
				if (!aire_jeu.coupValide(ligne, colonne)) {
					System.out.println("Le coup de joueur "+joueur+" n'est pas valide, rejoue!");
				} else {
					aire_jeu.creerCoup(ligne, colonne);
					System.out.println("Joueur "+joueur+" viens de jouer.");
					aire_graphique.repaint();
					if (aire_jeu.gameOver()) {
						System.out.println("Game Over! Le joueur "+joueur+" a perdu.");
						System.exit(0);
					}
					if (joueur == 1) { joueur = 2; } else joueur = 1;		// on change de joueur
				}
				if (ia != null && active_IA == joueur) {					// on lance le coup d'IA
					Coup coup_ia = ia.donneCoup();
					CoupLentIA l = new CoupLentIA(this, "Jouer", coup_ia.getColonne()*aire_graphique.getCaseWidth(), coup_ia.getLigne()*aire_graphique.getCaseHeight());
				}
				break;
			default:
				System.out.println("Le controleur ne connait pas cette instruction souris.");
		}		
	}

    /**
     * Effectue une instruction donne apres un click d'un touche clavier.
	 * @param l'instruction a effectuer
     */
    public void instructionClavier(String instruction) {
		switch (instruction) {							// "Refaire", "Annuler", "Activer IA"
			case "Refaire":		// Refait un coup.
				if (aire_jeu.refaireCoupPossible() && active_IA == 0) {
					aire_jeu.refaireCoup();
					System.out.println("On refait le coup de joueur "+joueur+".");
					if (joueur == 1) { joueur = 2; } else joueur = 1;		// on change de joueur
					aire_graphique.repaint();
				} else {
					System.out.println("Il y a pas de coup a refaire.");
				}
				break;
			case "Annuler":		// Annule un coup.
				if (aire_jeu.annulationCoupPossible() && active_IA == 0) {
					aire_jeu.annulerCoup();
					if (joueur == 1) { joueur = 2; } else joueur = 1;		// on change de joueur
					System.out.println("On annule le coup de joueur "+joueur+".");
					aire_graphique.repaint();
				} else {
					System.out.println("Annule coup impossible.");
				}
				break;
			case "Activer IA":		// Active le joueur IA.
				ia = new EtOuIA(aire_jeu);//new AleatoireIA(aire_jeu);
				active_IA = joueur;
				Coup coup_ia = ia.donneCoup();
				instructionSouris("Jouer", coup_ia.getColonne()*aire_graphique.getCaseWidth(), coup_ia.getLigne()*aire_graphique.getCaseHeight());
				break;
			case "Exporter":		// Exporter historique coups.
				aire_jeu.sauvegarderHistoriqueCoups();
				break;
			case "Imorter":			// Importer historique coups.
				ArrayList<Coup> historique = aire_jeu.chargeHistoriqueCoups(null);
				break;
			default:
				System.out.println("Le controleur ne connait pas cette instruction clavier.");
		}
	}
    
}