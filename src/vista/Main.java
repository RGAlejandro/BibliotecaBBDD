package vista;

import excepciones.CampoVacioException;
import excepciones.IsbnException;
import modelo.Libro;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String titulo="El fubo ha muerto",autor="Alejandro Gemes",editorial="AGR Studio",prestado="true",fechaPrestamo="2023-05-12",fechaDevolucion="2023-05-19",isbn="978-84-95354-15-0";
		try {
			Libro libroEjemplo=new Libro(titulo, autor, editorial, prestado, fechaPrestamo, fechaDevolucion, isbn);
			System.out.println(libroEjemplo);
		} catch (CampoVacioException | IsbnException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
