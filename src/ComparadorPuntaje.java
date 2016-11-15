import java.util.Comparator;

public class ComparadorPuntaje implements Comparator<Usuario>{

	public int compare(Usuario o1, Usuario o2) {
		return o2.getPuntaje()-o1.getPuntaje();
	}

}
