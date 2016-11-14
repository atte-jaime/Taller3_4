import java.util.LinkedList;

import processing.core.PApplet;

public class Logica {

	private PApplet app;
	private LinkedList<Fruta> frutas;
	private Manzana man;

	public Logica(PApplet app) {
		this.app = app;
		frutas = new LinkedList<Fruta>();
		man = new Manzana(app);
		man.start();
	}

	public void pintar() {
		if (man.isEjecutar() == true) {
			man.pintarCronometro();

		}
	}

	public void teclas() {
		if (app.key == ' ') {
			man.setEjecutar(true);
		}

		if (app.key == 'w') {

			app.fill(255);
			app.ellipse(50, 50, 50, 50);
			man.setVel(5);
		}
		
		if (app.key == 's') {
			app.fill(255);
			app.ellipse(50, 50, 50, 50);
			man.setVel(-5);
		}

	}

}
