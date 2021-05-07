package Vue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Controleur.ControleurMediateur;

public class EcouteurClavier extends KeyAdapter {
	ControleurMediateur control;

	public EcouteurClavier(ControleurMediateur c) {
		control = c;
	}

	@Override
	public void keyPressed(KeyEvent event) {
		switch (event.getKeyCode()) {
			case KeyEvent.VK_A:
				control.toucheA();
				break;
			case KeyEvent.VK_R:
				control.toucheR();
				break;
			case KeyEvent.VK_E:
				control.toucheE();
				break;
			case KeyEvent.VK_I:
				control.toucheI();
				break;
		}
	}
}