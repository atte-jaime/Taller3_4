
public class Usuario {
	
	private String[] datos;
	private String nombre;
	private int puntaje;
	
	public Usuario(String linea) {
		datos = linea.split(" ");
		nombre = datos[0];
		puntaje = Integer.parseInt(datos[1]);
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}
	
	
	
	

}
