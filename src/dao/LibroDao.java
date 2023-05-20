package dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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
	
	public boolean añadirLibro(Libro librito) throws SQLException {
		boolean agregado=false;
		String sql="insert into libros values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst=conn.prepareStatement(sql);
		
		
		String titulo=librito.getTitulo();
		String autor=librito.getAutor();
		String editorial=librito.getEditorial();
		String isbn=librito.getIsbn();
		boolean prestado=librito.isPrestado();
		LocalDate fechaPrestamoLD=librito.getFechaPrestamo();
		Date fechaPrestamo=Date.valueOf(fechaPrestamoLD);
		LocalDate fechaDevolucionLD=librito.getFechaDevolucion();
		Date fechaDevolucion=Date.valueOf(fechaDevolucionLD);
		LocalDateTime fechaAltaLDT=librito.getFechaAlta();
		Timestamp fechaAlta=Timestamp.valueOf(fechaAltaLDT);
		
		pst.setInt(1, 0);
		pst.setString(2, titulo);
		pst.setString(3, autor);
		pst.setString(4, editorial);
		pst.setBoolean(5, prestado);
		pst.setDate(6, fechaPrestamo);
		pst.setDate(7, fechaDevolucion);
		pst.setString(8, isbn);
		pst.setTimestamp(9, fechaAlta);
		
		pst.executeUpdate();
		
		agregado=true;
		return agregado;
		
	}
	public boolean eliminarLibro(String isbn) throws SQLException {
		boolean eliminado=false;
		String campo="isbn";
		String cadenaBuscada=isbn;
		String sql="delete from libros where "+campo+"= '"+cadenaBuscada+"'";
		PreparedStatement pst=conn.prepareStatement(sql);
		pst.executeUpdate();
		
		eliminado=true;
		return eliminado;
		
	}
	public boolean prestarLibro(String isbn) throws SQLException {
		boolean cambiado=false;
		String sql="Update libros Set prestado=? , fechaPrestamo=? , fechaDevolucion=? where isbn=?";
		PreparedStatement pst=conn.prepareStatement(sql);
		LocalDate fechaPrestamoLD=LocalDate.now();
		LocalDate fechaDevolucionLD=LocalDate.now().plusDays(10);
		Date fechaPrestamo=Date.valueOf(fechaPrestamoLD);
		Date fechaDevolucion=Date.valueOf(fechaDevolucionLD);

		pst.setBoolean(1, true);
		pst.setDate(2, fechaPrestamo);
		pst.setDate(3, fechaDevolucion);
		pst.setString(4, isbn);
		pst.executeUpdate();
		
		cambiado=true;
		return cambiado;
		
	}
}
