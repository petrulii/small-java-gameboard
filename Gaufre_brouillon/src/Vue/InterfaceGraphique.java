package Vue;
import javax.swing.*;
import Controleur.ControleurMediateur;
import Modele.AireJeu;

public class InterfaceGraphique implements Runnable {
	AireJeu aire_jeu;
	JFrame frame;
	
	public InterfaceGraphique(AireJeu a) {
		aire_jeu = a;
	}
	
	public void run() {
		frame = new JFrame("Jeu");
		
		AireGraphique aire_graphique = new AireGraphique(aire_jeu);
		frame.add(aire_graphique);
				
		ControleurMediateur control = new ControleurMediateur(aire_jeu, aire_graphique);
		aire_graphique.setFocusable(true);
		aire_graphique.addMouseListener(new EcouteurSourisAire(control));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 600);
		frame.setResizable(false);
		frame.setVisible(true);
	}

	public static void demarrer(AireJeu a) {
		SwingUtilities.invokeLater(new InterfaceGraphique(a));
	}
}