import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import ddf.minim.AudioPlayer;
import ddf.minim.Minim;
import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PImage;

public class Logica {

	private PApplet app;
	private int puntaje;
	private int pantalla = 0;
	private String nombre = "";
	private PFont fuente;
	private PImage[] interfaz = new PImage[11];
	private String[] puntajes;
	private ArrayList<Usuario> usuarios;
	private LinkedList<Fruta> frutas;
	private Minim minim;
	private AudioPlayer fondo;

	public Logica(PApplet app) {
		this.app = app;
		fuente = app.createFont("../data/Fuentes/BebasNeue.otf", 32);
		app.textFont(fuente);
		for (int i = 1; i < 12; i++) {
			interfaz[i - 1] = app.loadImage("../data/Img/pag_" + i + ".png");
		}
		usuarios = new ArrayList<Usuario>();
		frutas = new LinkedList<Fruta>();
		minim = new Minim(app);
		fondo = minim.loadFile("../data/Sonidos/fondo.mp3");

		agregarIniciales();
		agregarPuntajesIniciales();
	}

	public void pintar() {
		sonidoFondo();
		pantallas();
	}

	public void teclas() {

		if (app.keyCode == PApplet.RIGHT && pantalla < 5 && pantalla != 2) {
			pantalla++;
		}

		if (pantalla == 2 && app.key == PApplet.ENTER) {
			pantalla++;
		}

		if (app.keyCode == PApplet.LEFT && pantalla > 0) {
			pantalla--;
		}
		
		if((app.keyCode == PApplet.BACKSPACE)){
			System.out.println("HOLI");
			reiniciar();
		}

		interaccion();
		entradaNombre();

	}
	
	public void reiniciar(){
		nombre="";
		puntaje=0;
		pantalla=2;
		frutas.removeAll(frutas);
		agregarNuevos();
	}

	public void interaccion() {
		if (pantalla == 3) {

			if ((app.key == 'c' | app.key == 'C') && frutas.getFirst() instanceof Carambolo) {
				definirVel();
				puntaje += (int) (frutas.getFirst().getAngulo() / 2);
				frutas.removeFirst();
			}

			if ((app.key == 'b' | app.key == 'B') && frutas.getFirst() instanceof Banana) {
				definirVel();
				puntaje += (int) (frutas.getFirst().getAngulo() / 2);
				frutas.removeFirst();
			}

			if ((app.key == 'z' | app.key == 'Z') && frutas.getFirst() instanceof Cereza) {
				definirVel();
				puntaje += (int) (frutas.getFirst().getAngulo() / 2);
				frutas.removeFirst();
			}

			if ((app.key == 'm' | app.key == 'M') && frutas.getFirst() instanceof Mora) {
				definirVel();
				puntaje += (int) (frutas.getFirst().getAngulo() / 2);
				frutas.removeFirst();
			}

			if ((app.key == 'l' | app.key == 'L') && frutas.getFirst() instanceof Limon) {
				definirVel();
				puntaje += (int) (frutas.getFirst().getAngulo() / 2);
				frutas.removeFirst();
			}

			if ((app.key == 's' | app.key == 'S') && frutas.getFirst() instanceof Sandia) {
				definirVel();
				puntaje += (int) (frutas.getFirst().getAngulo() / 2);
				frutas.removeFirst();
			}
		}
	}

	public void definirVel() {
		Fruta primero = frutas.getFirst();
		Fruta siguiente = frutas.get(1);


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
			app.textAlign(PApplet.LEFT, PApplet.TOP);
			app.textSize(160);
			app.fill(0);
			app.text(nombre, 520, 460);
			break;

		case 3:
			ejecutar();
			break;

		case 4:
			app.image(interfaz[9], app.width / 2, app.height / 2);
			app.textAlign(PApplet.CENTER, PApplet.CENTER);
			app.fill(255);
			app.textSize(200);
			app.text(puntaje, app.width / 2, app.height / 2 - 100);
			app.textSize(120);
			app.text("PTS", app.width / 2, app.height / 2 + 100);
			app.noFill();
			break;

		case 5:
			app.image(interfaz[10], app.width / 2, app.height / 2);
			pintarPuntajes();
			break;

		}

	}

	public void ejecutar() {
		Fruta primero = frutas.getFirst();

		primero.pintarFruta();
		primero.pintarCronometro();
		primero.setEjecutar(true);

		if (frutas.size() < 3) {
			agregarNuevos();
		}

		if (primero.getAngulo() <= 0) {
			agregarLista(nombre, puntaje);
			agregarPuntajeNuevo();
			pantalla = 4;
		}
	}

	public void pintarPuntajes() {
		Collections.sort(usuarios, new ComparadorPuntaje());
		for (int i = 0; i < 6; i++) {
			Usuario temp = usuarios.get(i);
			int pos = i+1;
			if (i==0) app.textSize(120);
			else app.textSize(80);
			app.fill(0);
			app.textAlign(PApplet.LEFT, PApplet.CENTER);
			app.text(pos+"   "+temp.getNombre(), 480, 220 + i * 150);
			app.text(temp.getPuntaje(), 1200, 220 + i * 150);
		}
	}

	public void agregarPuntajesIniciales() {
		puntajes = app.loadStrings("puntajes.txt");

		for (int i = 0; i < puntajes.length; i++) {
			Usuario temp = new Usuario(puntajes[i]);
			usuarios.add(temp);
		}
	}

	public void agregarPuntajeNuevo() {
		String[] nuevo;

		nuevo = app.loadStrings("puntajes.txt");
		usuarios.add(new Usuario(nuevo[nuevo.length - 1]));
	}

	public void entradaNombre() {

		if (pantalla == 2 && app.key != PApplet.ENTER && app.key != PApplet.BACKSPACE && app.keyCode != PApplet.RIGHT
				&& app.keyCode != PApplet.LEFT && app.keyCode != 20 && app.key != ' ') {
			nombre += app.key;
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

	public void agregarLista(String nombre, int puntaje) {
		try {
			String content = nombre + " " + puntaje;

			File file = new File("puntajes.txt");
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(content);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void sonidoFondo(){
		if ( fondo.position() == fondo.length() )
		  {
		    fondo.rewind();
		    fondo.play();
		  }
		  else
		  {
		    fondo.play();
		}
	}
}
