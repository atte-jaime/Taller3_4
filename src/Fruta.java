import processing.core.PApplet;
import processing.core.PImage;

public abstract class Fruta extends Thread {

	public PApplet app;
	// public PImage fruta;
	public int vel = 33;
	public boolean ejecutar;

	public Fruta(PApplet app
	// ,PImage fruta
	) {
		this.app = app;
		// this.fruta = fruta;
	}

	public void run() {
		while (true) {
			if (ejecutar == true) {
				ejecutarCronometro();
			}

			try {
				Thread.sleep(vel);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public abstract void ejecutarCronometro();

	public abstract void pintarFruta();

	public abstract void pintarCronometro();

	public int getVel() {
		return vel;
	}

	public boolean isEjecutar() {
		return ejecutar;
	}

	public void setVel(int vel) {
		if ((this.vel + vel) > 3) {
			this.vel += vel;
		}

	}

	public void setEjecutar(boolean ejecutar) {
		this.ejecutar = ejecutar;
	}

}
