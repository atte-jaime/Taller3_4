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
	}

	public void draw(){
		background(0);
		app.pintar();
	}

	public void keyPressed(){
		app.teclas();
	}

}
