package Controleur;

import java.util.Random;
import Modele.*;

/**
 * Joueur IA aleatoire.
 * @author Petrulionyte Ieva
 * @version 1.0
 */
public class AleatoireIA implements IA {
    AireJeu aire_jeu;

    public AleatoireIA(AireJeu a) {
        aire_jeu = a;
    }

    /**
     * Genere un entier aleatoire entre les entiers min et max
	 * @param l'entier min
	 * @param l'entier max
     * @return un entier aleatoire entre min et max
     */
    public int randomInRange(int min, int max) {
    	Random r = new Random();
        return r.nextInt((max-min)+1)+min; 
    }
    
    /**
     * Genere un coup aleatoire
     * @return un Coup valide
     */
    public Coup donneCoup() {
    	int l = -1;
    	int c = -1;
    	while (!aire_jeu.coupValide(l, c) && !(l==aire_jeu.getPoisonLigne() && c==aire_jeu.getPoisonColonne())) {
    		l = randomInRange(0, aire_jeu.getNbLignes());
    		c = randomInRange(0, aire_jeu.getNbColonnes());
    	}
		return new Coup(l,c); 

    }

}