package Controleur;

import java.util.ArrayList;

import Modele.*;

/**
 * Joueur IA base sur l'exploration de l'arbre et/ou.
 * @author Petrulionyte Ieva
 * @version 1.0
 */
public class EtOuIA implements IA {
    AireJeu aire_jeu;
    int nb_lignes;
    int nb_colonnes;

    public EtOuIA(AireJeu a) {
        aire_jeu = a;
        nb_lignes = aire_jeu.getNbLignes();
        nb_colonnes = aire_jeu.getNbColonnes();
    }
    
    /**
     * Correspond a <Calcul_Joueur_A(configuration)> dans les slides ici : https://prog6.gricad-pages.univ-grenoble-alpes.fr/prog6_projet_2020-2021/Amphis-IA.pdf
     * @param un grille qui represente un configuration d'un plateau de jeu
     */
    public boolean donneCoupRec(int[][] configuration) {
		System.out.println("Coup A ici.");
		boolean valeur = false;
   		if (configuration[0][0] == 0) {
   			System.out.println("Dans A poison est mange.");
   			return true;
   		} else {
   			ArrayList<Coup> coups_jouables = coupsJouables(configuration);
   			for (Coup c : coups_jouables) {
   				int[][] configuration_coup = copyConfiguration(configuration);
   				jouerCoup(configuration_coup, c);
   				valeur = (false || donneCoupRecOpponent(configuration_coup));
   			}
   		}
		return valeur;
    }
    
    /**
     * Correspond a <Calcul_Joueur_B(configuration)> dans les slides ici : https://prog6.gricad-pages.univ-grenoble-alpes.fr/prog6_projet_2020-2021/Amphis-IA.pdf
     * @param un grille qui represente un configuration d'un plateau de jeu
     */
    public boolean donneCoupRecOpponent(int[][] configuration) {
		System.out.println("Coup B ici.");
		boolean valeur = false;
   		if (configuration[0][0] == 0) {
   			return false;
   		} else {
   			ArrayList<Coup> coups_jouables = coupsJouables(configuration);
   			for (Coup c : coups_jouables) {
   				int[][] configuration_coup = copyConfiguration(configuration);
   				jouerCoup(configuration_coup, c);
   				valeur = (false || donneCoupRec(configuration_coup));
   			}
   		}
		return valeur;
    }
    
    /**
     * Joue/place un coup joauble dans un grille qui represente un configuration d'un plateau de jeu ("mange" une partie de gaufre)
     * @param un grille qui represente un configuration d'un plateau de jeu
     * @param un coup a jouer
     */
    private void jouerCoup(int[][] configuration_coup, Coup c) {
		int ligne = c.getLigne();
		int colonne = c.getColonne();
    	if ((((ligne < nb_lignes) && (ligne >= 0)) && ((colonne < nb_colonnes) && (colonne >= 0))) && configuration_coup[ligne][colonne] != 0) {
			for (int i = ligne; i < nb_lignes; i++) {
				for (int j = colonne; j < nb_colonnes; j++) {
					configuration_coup[i][j] = 0;
				}
			}
    	}
	}

    /**
     * Donne un liste des coups joaubles a partir d'une configuration de jeu
     * @param un grille qui represente un configuration d'un plateau de jeu
     * @return un liste des coups joaubles
     */
    private ArrayList<Coup> coupsJouables(int[][] configuration) {
		ArrayList<Coup> coups = new ArrayList<Coup>();
		for (int i = 0; i < nb_lignes; i++) {
			for (int j = 0; j < nb_colonnes; j++) {
				// 0 - case vide, 1 - case gaufre, 2 - case poison
				if (configuration[i][j] != 0) {
					coups.add(new Coup(i,j));
				}
			}
		}
		return coups;
	}
    
	/**
     * Copy d'un grille qui represente un configuration d'un plateau de jeu
     * @param un grille qui represente un configuration d'un plateau de jeu
     * @return un copie profond d'un grille qui represente un configuration d'un plateau de jeu
     */
    public int[][] copyConfiguration(int[][] configuration) {
    	int[][] configuration_copie = new int[nb_lignes][nb_colonnes];
		for (int i = 0; i < nb_lignes; i++) {
			for (int j = 0; j < nb_colonnes; j++) {
				configuration_copie[i][j] = configuration[i][j];
			}
		}
		return configuration_copie;
    }

	/**
     * Genere un coup
     * @return un Coup valide
     */
    public Coup donneCoup() {
    	Coup coup = null;
        int[][] configuration = copyConfiguration(aire_jeu.getGrille());
		ArrayList<Coup> coups_jouables = coupsJouables(configuration);
		// pour chaque coup joauable on explore toute branche d'un arbre et/ou
		for (Coup c : coups_jouables) {
			// il faut faire les copies de la grille afin de ne pas modifier le plateau de jeu principal
			int[][] configuration_coup = copyConfiguration(configuration);
			// on jeu un des coups jouable
			jouerCoup(configuration_coup, c);
			// on explore une branche d'un arbre et/ou qui commence par ce coup
	    	if (configuration_coup[0][0]!=0 && donneCoupRecOpponent(configuration_coup)) {
				// si le coup est gagnant
	    		coup = new Coup(c.getLigne(), c.getColonne());
				System.out.println("Coup IA EtOu: "+coup);
	    	}
		}
		// s'il existe pas de coup gagnant, on cree un coup aleatoire
		if (coup == null) {
			AleatoireIA i = new AleatoireIA(aire_jeu);
			coup = i.donneCoup();
			System.out.println("Coup IA Aleatoire (pas du coup gagnant: "+coup);
		}
		return coup;
    }

}