package server;

public class Tarea {
	
	String descripcion;
	String estado;
	
	public Tarea(String descripcion, String estado) {
		super();
		this.descripcion = descripcion;
		this.estado = estado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "[descripcion: " + descripcion + ", estado: " + estado + "]";
	}
	
}
