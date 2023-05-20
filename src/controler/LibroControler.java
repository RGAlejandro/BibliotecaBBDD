package controler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.LibroDao;
import excepciones.CampoVacioException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibroControler {
	private List <Libro> biblio=new ArrayList<Libro>();
	private Connection conn;
	public LibroControler(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}
	
	public List<Libro> getBiblio() {
		return biblio;
	}

	public List<Libro> getConsulta(String sql) throws SQLException, CampoVacioException, IsbnException{
		
		LibroDao dao=new LibroDao(conn);
		return dao.getConsulta(sql);
	}
	public boolean añadirLibro(String titulo, String autor, String editorial, String isbn) throws CampoVacioException, IsbnException, SQLException {
		boolean agregado=false;
		Libro librito=new Libro(titulo, autor, editorial,isbn);
		LibroDao dao=new LibroDao(conn);
		agregado=dao.añadirLibro(librito);
		return agregado;
		
	}
	public boolean eliminarLibro(String isbn) throws SQLException {
		boolean eliminado=false;
		LibroDao dao=new LibroDao(conn);
		eliminado=dao.eliminarLibro(isbn);
		
		
		return eliminado;
		
	}

}
