import java.util.LinkedList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private int pantalla = 0;
	private PImage[] interfaz = new PImage[11];
	private LinkedList<Fruta> frutas;

	public Logica(PApplet app) {
		this.app = app;

		for (int i = 1; i < 12; i++) {
			interfaz[i - 1] = app.loadImage("../data/pag_" + i + ".png");
		}

		frutas = new LinkedList<Fruta>();

		agregarIniciales();

	}

	public void pintar() {
		pantallas();
	}

	public void teclas() {

		if (app.key == 'c' && frutas.getFirst() instanceof Carambolo) {
			definirVel();
			frutas.removeFirst();
		}

		if (app.key == 'b' && frutas.getFirst() instanceof Banana) {
			definirVel();
			frutas.removeFirst();
		}

		if (app.key == 'z' && frutas.getFirst() instanceof Cereza) {
			definirVel();
			frutas.removeFirst();
		}

		if (app.key == 'm' && frutas.getFirst() instanceof Mora) {
			definirVel();
			frutas.removeFirst();
		}

		if (app.key == 'l' && frutas.getFirst() instanceof Limon) {
			definirVel();
			frutas.removeFirst();
		}

		if (app.key == 's' && frutas.getFirst() instanceof Sandia) {
			definirVel();
			frutas.removeFirst();
		}

		if (app.keyCode == PApplet.RIGHT && pantalla <= 5) {
			pantalla++;
		}

		if (app.keyCode == PApplet.LEFT && pantalla > 0) {
			pantalla--;
		}

	}

	public void definirVel() {
		Fruta primero = frutas.getFirst();
		Fruta siguiente = frutas.get(1);

		System.out.println(primero.getVel());

		if (primero.getVel() >= 10) {
			siguiente.setVel(primero.getVel() - 3);
		}

		else if (primero.getVel() > 3 && primero.getVel() < 10) {
			siguiente.setVel(primero.getVel() - 1);
		}

		else if (primero.getVel() == 3) {
			siguiente.setVel(primero.getVel());
		}

		siguiente.setEjecutar(true);
	}

	public void pantallas() {
		switch (pantalla) {
		case 0:
			app.image(interfaz[0], app.width / 2, app.height / 2);
			break;

		case 1:
			app.image(interfaz[1], app.width / 2, app.height / 2);

			break;

		case 2:
			app.image(interfaz[8], app.width / 2, app.height / 2);
			break;

		case 3:
			ejecutar();
			break;

		case 4:
			app.image(interfaz[9], app.width / 2, app.height / 2);
			break;

		case 5:
			app.image(interfaz[10], app.width / 2, app.height / 2);
			break;

		}

	}

	public void ejecutar() {
		Fruta primero = frutas.getFirst();

		primero.pintarFruta();
		primero.pintarCronometro();
		primero.setEjecutar(true);

		if (frutas.size() < 2) {
			agregarNuevos();
		}
	}

	public void agregarNuevos() {
		for (int i = 0; i < 5; i++) {
			int x = (int) app.random(0, 6);
			switch (x) {
			case 0:
				Banana ban = new Banana(app, interfaz[4]);
				ban.start();
				frutas.add(ban);
				break;

			case 1:
				Cereza cer = new Cereza(app, interfaz[6]);
				cer.start();
				frutas.add(cer);
				break;

			case 2:
				Mora mor = new Mora(app, interfaz[7]);
				mor.start();
				frutas.add(mor);
				break;

			case 3:
				Carambolo car = new Carambolo(app, interfaz[5]);
				car.start();
				frutas.add(car);
				break;

			case 4:
				Limon lim = new Limon(app, interfaz[2]);
				lim.start();
				frutas.add(lim);
				break;

			case 5:
				Sandia san = new Sandia(app, interfaz[3]);
				san.start();
				frutas.add(san);
				break;

			}
		}
	}

	public void agregarIniciales() {

		for (int i = 0; i < 5; i++) {
			int x = (int) app.random(0, 6);
			switch (x) {
			case 0:
				Banana ban = new Banana(app, interfaz[4]);
				frutas.add(ban);
				break;

			case 1:
				Cereza cer = new Cereza(app, interfaz[6]);
				frutas.add(cer);
				break;

			case 2:
				Mora mor = new Mora(app, interfaz[7]);
				frutas.add(mor);
				break;

			case 3:
				Carambolo car = new Carambolo(app, interfaz[5]);
				frutas.add(car);
				break;

			case 4:
				Limon lim = new Limon(app, interfaz[2]);
				frutas.add(lim);
				break;

			case 5:
				Sandia san = new Sandia(app, interfaz[3]);
				frutas.add(san);
				break;

			}
		}

		for (int i = 0; i < frutas.size(); i++) {
			Fruta temp = frutas.get(i);
			temp.start();
		}
	}

}
