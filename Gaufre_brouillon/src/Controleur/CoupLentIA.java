package Controleur;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class CoupLentIA implements ActionListener {
		ControleurMediateur control;
		String instruction;
		int x, y;
		Timer t;
		
		public CoupLentIA(ControleurMediateur c, String i, int x, int y) {
			control = c;
			instruction = i;
			this.x = x;
			this.y = y;
			// On donne a Timer l'objet meme comme l'objet d'ecoute
			t = new Timer(500, this);
			t.start();
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			control.instructionSouris(instruction, x, y);
			t.stop();
		}
	}