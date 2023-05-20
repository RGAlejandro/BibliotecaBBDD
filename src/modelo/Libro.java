package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Libro {
	String titulo,autor,editorial,isbn;
	private boolean prestado;
	private LocalDate fechaPrestamo,fechaDevolucion;
	private LocalDateTime fechaAlta;
	

	public Libro() {
		// TODO Auto-generated constructor stub
	}
	

	public Libro(String titulo, String autor, String editorial, String prestado, String fechaPrestamo,
			String fechaDevolucion,String isbn) {
		/*
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.editorial = editorial;
		this.isbn = isbn;
		this.prestado = prestado;
		this.fechaPrestamo = fechaPrestamo;
		this.fechaDevolucion = fechaDevolucion;
		this.fechaAlta = fechaAlta;
		*/
		this.setTitulo(titulo);
		this.setAutor(autor);
		this.setEditorial(editorial);
		this.setPrestado(prestado);
		this.setFechaPrestamo(fechaPrestamo);
		this.setFechaDevolucion(fechaDevolucion);
		this.setIsbn(isbn);
		this.fechaAlta=LocalDateTime.now();
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		if(titulo.isEmpty()) {
			
		}
		else {
			this.titulo = titulo;
		}
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		if(autor.isEmpty()) {
			
		}
		else {
			this.autor = autor;
		}
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		if(editorial.isEmpty()) {
			
		}
		else {
			this.editorial = editorial;
		}
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		if(isbn.isEmpty()) {
			
		}
		if(compruebaIsbn(isbn)) {
			this.isbn = isbn;
		}
		else {
			
		}
		
	}


	private boolean compruebaIsbn(String isbn) {
		// TODO Auto-generated method stub
		boolean esValido=true;
		isbn=isbn.replace("-", "");
		if(isbn.length()!=13) {
			esValido=false;
			return esValido;
		}
		double compruebaNum=0;
		try {
			compruebaNum=Double.parseDouble(isbn);
		}catch (Exception e) {
			// TODO: handle exception
			esValido=false;
			return esValido;
		}
		String parteCalcular=isbn.substring(0, 12);
		String digPuesto=isbn.substring(12);
		char [] calcular=parteCalcular.toCharArray();
		
		int pos=1;
		int acumulador=0;
		int suma=0;
		for(int x=0;x<calcular.length;x++) {
			acumulador=Integer.parseInt(Character.toString(calcular[x]));
			if(pos%2==0) {
				suma+=acumulador*3;
			}
			else {
				suma+=acumulador*1;
			}
			pos++;
		}
		int dig=10-(suma%10);
		if(dig==10) {
			dig=0;
		}
		String digCalc=String.valueOf(dig);
		if(!digCalc.equals(digPuesto)) {
			esValido=false;
			return esValido;
		}
		
		return esValido;
	}


	public boolean isPrestado() {
		return prestado;
	}


	public void setPrestado(String prestado) {
		this.prestado = Boolean.parseBoolean(prestado);
	}


	public LocalDate getFechaPrestamo() {
		return fechaPrestamo;
	}


	public void setFechaPrestamo(String fechaPrestamo) {
		this.fechaPrestamo = LocalDate.parse(fechaPrestamo);
	}


	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}


	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = LocalDate.parse(fechaDevolucion);
	}


	public LocalDateTime getFechaAlta() {
		return fechaAlta;
	}


	public void setFechaAlta(LocalDateTime fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	
}