import processing.core.PApplet;

public class MainApp extends PApplet{

	public static void main(String[] args) {
		MainApp.main("MainApp");
	}
	
	Logica app;
	
	public void settings(){
		fullScreen();
		
	}
	
	public void setup(){
		app = new Logica(this);
		imageMode(CENTER);

	}

	public void draw(){
		background(255);
		app.pintar();
	}

	public void keyPressed(){
		if (exitCalled) {
			exit();
		}
		app.teclas();
	}
	
	public void mousePressed(){
		app.pressed();
	}

}
