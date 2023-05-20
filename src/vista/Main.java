package vista;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import controler.LibroControler;
import dao.DbConnection;
import excepciones.CampoVacioException;
import excepciones.IsbnException;
import modelo.Libro;

public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//String titulo="El fubo ha muerto",autor="Alejandro Gemes",editorial="AGR Studio",prestado="true",fechaPrestamo="2023-05-12",fechaDevolucion="2023-05-19",isbn="978-84-95354-15-0";
		/*
		try {
			Libro libroEjemplo=new Libro(titulo, autor, editorial, prestado, fechaPrestamo, fechaDevolucion, isbn);
			System.out.println(libroEjemplo);
		} catch (CampoVacioException | IsbnException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		*/
		Scanner leer=new Scanner(System.in);
		boolean sigue=true;
		String opcion="";
		do {
			System.out.println("1.-CONSULTA A TODA LA BBDD");
			System.out.println("2.-AÑADIR A LA BBDD");
			System.out.println("3.-ELIMINAR A LA BBDD");
			System.out.println("4.-FILTRADO A LA BBDD");
			System.out.println("5.-PRESTAR LIBRO EN LA BBDD");
			System.out.println("6.-APAGAR");
			System.out.println("");
			
			opcion=leer.next();
			
			switch(opcion) {
			case "1":
				Connection conn;
				DbConnection dbc=null;
				dbc = new DbConnection();
				conn=dbc.getConnection();
				LibroControler controler=new LibroControler(conn);
				String sql="select * from libros";
				try {
					List <Libro> biblio=controler.getConsulta(sql);
					mostrar(biblio);
				} catch (SQLException | CampoVacioException | IsbnException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				dbc.disconect();
			break;
			
			case "2":
				String titulo="El fubo ha muerto",autor="Alejandro Gemes",editorial="AGR Studio",isbn="978-84-204-4290-7";
				dbc = new DbConnection();
				conn=dbc.getConnection();
				controler=new LibroControler(conn);
				try {
					if(controler.añadirLibro(titulo,autor,editorial,isbn)) {
						//List <Libro> biblio=controler.getBiblio();
						//mostrar(biblio);
						System.out.println("Libro Añadido...");
					}
				} catch (CampoVacioException | IsbnException | SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			break;
				
			case "3":
				dbc = new DbConnection();
				conn=dbc.getConnection();
				controler=new LibroControler(conn);
				isbn="978-84-204-4290-7";
				try {
					if(controler.eliminarLibro(isbn)) {
						System.out.println("Libro Eliminado...");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
			break;
			
			case "4":
				dbc = new DbConnection();
				conn=dbc.getConnection();
				controler=new LibroControler(conn);
				String campo="prestado";
				String campoBuscado="1";
				sql="select * from libros where "+campo+" ='"+campoBuscado+"'";
				try {
					List <Libro> biblio=controler.getConsulta(sql);
					mostrar(biblio);
				} catch (SQLException | CampoVacioException | IsbnException e) {
					// TODO Auto-generated catch block
					System.out.println(e.getMessage());
				}
				dbc.disconect();
			break;
			
			case "5":
				
			break;
			
			case "6":
				sigue=false;
			break;
			}
		}while(sigue);
	}

	private static void mostrar(List<Libro> biblio) {
		// TODO Auto-generated method stub
		for (Libro l : biblio) {
			System.out.println(l);
		}
	}

}
