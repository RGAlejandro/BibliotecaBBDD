package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import excepciones.CampoVacioException;
import excepciones.IsbnException;
import modelo.Libro;

public class LibroDao {
	private Connection conn;
	public LibroDao(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
	}
	public List<Libro> getConsulta(String sql) throws SQLException, CampoVacioException, IsbnException{
		List <Libro> biblio=new ArrayList<Libro>();
		String titulo,autor,editorial,isbn;
		boolean prestado;
		LocalDate fechaPrestamo,fechaDevolucion;
		LocalDateTime fechaAlta;
		PreparedStatement pst=conn.prepareStatement(sql);
		ResultSet rs=pst.executeQuery();
		Libro librito=null;
		while(rs.next()) {
			titulo=rs.getString("titulo");
			autor=rs.getString("autor");
			editorial=rs.getString("editorial");
			prestado=rs.getBoolean("prestado");
			fechaPrestamo=rs.getDate("fechaPrestamo").toLocalDate();
			fechaDevolucion=rs.getDate("fechaDevolucion").toLocalDate();
			isbn=rs.getString("isbn");
			fechaAlta=rs.getTimestamp("fechaAlta").toLocalDateTime();
			librito=new Libro(titulo, autor, editorial, prestado, fechaPrestamo, fechaDevolucion,isbn, fechaAlta);
			biblio.add(librito);
			librito=null;
		}
		return biblio;
		
	}
}
