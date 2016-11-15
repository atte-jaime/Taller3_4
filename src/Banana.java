import processing.core.PApplet;
import processing.core.PImage;

public class Banana extends Fruta {
	private float angulo;
	
	public Banana(PApplet app, PImage fruta) {
		super(app, fruta);
		angulo=360f;
	}

	@Override
	public void ejecutarCronometro() {
		angulo -= 1;

	}

	@Override
	public void pintarFruta() {
		app.image(fruta, app.width / 2, app.height / 2, 1920, 1080);
	}

	@Override
	public void pintarCronometro() {
		app.stroke(255);
		app.strokeCap(PApplet.ROUND);
		app.strokeWeight(150);
		app.noFill();
		app.pushMatrix();
		app.translate(app.width / 2, app.height / 2);
		//app.rotate(PApplet.radians(90));
		app.arc(0, 0, 790, 790, 0, PApplet.radians(angulo));
		app.popMatrix();
	}

	@Override
	public float getAngulo() {
		// TODO Auto-generated method stub
		return angulo;
	}

	
}
