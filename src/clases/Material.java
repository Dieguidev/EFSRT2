package clases;

public class Material {
	
	private int idMaterial;
	private String codigoMaterial;
	private String nombreMaterial;
	
	private String tipoMaterial;
	private String color;
	private String unidadMedida;
	private String fecha;
	private String hora;
	
	private int cantidad;
	private String proveedor;
	
	public Material(String codigoMaterial, String nombreMaterial, String tipoMaterial, String color,String unidadMedida, int cantidad, String proveedor,String fecha, String hora){
		this.codigoMaterial = codigoMaterial;
		this.nombreMaterial = nombreMaterial;
		this.tipoMaterial = tipoMaterial;
		this.proveedor = proveedor;
		this.color = color;
		this.unidadMedida = unidadMedida;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.hora = hora;
	}
	
	//SETTERS
	
	public void setIdMaterial(int idMaterial) {
		this.idMaterial = idMaterial;
	}

	public void setCodigoMaterial(String codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public void setNombreMaterial(String nombreMaterial) {
		this.nombreMaterial = nombreMaterial;
	}

	public void setTipoMaterial(String tipoMaterial) {
		this.tipoMaterial = tipoMaterial;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public void setHora(String hora) {
		this.hora = hora; 
	}
	
	//GETTERS
	
	public int getIdMaterial() {
		return idMaterial;
	}

	public String getCodigoMaterial() {
		return codigoMaterial;
	}

	public String getNombreMaterial() {
		return nombreMaterial;
	}

	public String getTipoMaterial() {
		return tipoMaterial;
	}

	public String getColor() {
		return color;
	}

	public String getUnidadMedida() {
		return unidadMedida;
	}

	public int getCantidad() {
		return cantidad;
	}

	public String getProveedor() {
		return proveedor;
	}
	public String getFecha() {
		return fecha;
	}
	public String getHora() {
		return hora;
	}
	
	
}


 