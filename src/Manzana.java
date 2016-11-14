import processing.core.PApplet;
import processing.core.PImage;

public class Manzana extends Fruta {
	public float angulo;

	public Manzana(PApplet app) {
		super(app);
		angulo = 360f;
	}

	@Override
	public void ejecutarCronometro() {
		angulo-=1;

	}

	@Override
	public void pintarFruta() {
		//app.image(fruta, 0, 0);
	}

	@Override
	public void pintarCronometro() {
		app.stroke(0,255,255);
		app. strokeCap(PApplet.ROUND);
		app.strokeWeight(100);
		app.noFill();
		app.arc(app.width / 2, app.height / 2, 500, 500, 0, PApplet.radians(angulo));
	}

}
