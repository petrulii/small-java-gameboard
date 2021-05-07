package Vue;
import Modele.AireJeu;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.InputStream;

public class AireGraphique extends JComponent {
	AireJeu aire;
	Image gaufre, poison;

	private Image chargeImage(String nom) {
		Image img = null;
		InputStream in = ClassLoader.getSystemClassLoader().getResourceAsStream("Images" + File.separator + nom + ".png");
		try {
			// Chargement d'une image utilisable dans Swing
			img = ImageIO.read(in);
		} catch (Exception e) {
			System.out.println("J'arrive pas a construire les images." + "Images" + File.separator + nom + ".png");
			System.exit(1);
		}
		//System.out.println("Construceur aire ici.");
		return img;
	}

	public AireGraphique(AireJeu aire) {
		this.aire = aire;
		gaufre = chargeImage("Gaufre");
		poison = chargeImage("Poison");
	}

	@Override
	public void paintComponent(Graphics g) {
		// Graphics 2D est le vrai type de l'objet passé en paramètre
		// Le cast permet d'avoir acces a un peu plus de primitives de dessin
		Graphics2D drawable = (Graphics2D) g;

		// On reccupere quelques infos provenant de la partie JComponent
		int width_case = getCaseWidth();
		int height_case = getCaseHeight();

		// On efface tout
		drawable.clearRect(0, 0, getWidth(), getHeight());
		
		int[][] grille = aire.getGrille();
		for (int j = 0; j < aire.getNbColonnes(); j++) {
			for (int i = 0; i < aire.getNbLignes(); i++) {
				switch(grille[i][j]) {
				    case 1:
						drawable.drawImage(gaufre, j*width_case, i*height_case, width_case, height_case, null);
						break;
					case 2:
						drawable.drawImage(poison, aire.getPoisonColonne(), aire.getPoisonLigne(), width_case, height_case, null);
						break;
					case 0:
						drawable.clearRect(j*width_case, i*height_case, width_case, height_case);//getWidth()-j*width_case, getHeight()-i*height_case);
						break;
				}
			}
		}
	}
	
	public int getWidth() {
		return getSize().width;
	}
	
	public int getHeight() {
		return getSize().height;
	}

	public int getCaseWidth() {
		return getWidth()/aire.getNbColonnes();
	}

	public int getCaseHeight() {
		return getHeight()/aire.getNbLignes();
	}

}