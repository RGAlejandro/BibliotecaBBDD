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
	public List<Libro> getConsulta(String sql) throws SQLException, CampoVacioException, IsbnException{
		
		LibroDao dao=new LibroDao(conn);
		
		return dao.getConsulta(sql);
		
	}

}
