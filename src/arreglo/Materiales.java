package arreglo;

import java.util.ArrayList;

import clases.Material;

public class Materiales {
	private ArrayList<Material> material = new ArrayList<>();
	
	//CONSTRUCTOR
	public Materiales() {
		material = new ArrayList<Material>();
		
	}
	
	//METODOS
	public int tamaño() {
		return material.size();
	}
	public Material obtener(int i) {
		return material.get(i);
	}
	public void añadir(Material e) {
		material.add(e);
	}
	public String codigoMaterial() {
	    if (tamaño() == 0) {
	        return "MAT001";
	    } else {
	        String ultimoCodigo = obtener(tamaño() - 1).getCodigoMaterial(); 
	        int numero = Integer.parseInt(ultimoCodigo.substring(3));         
	        numero++;                                                         
	        return String.format("MAT%03d", numero);                         
	    }
	}
	public void eliminar(Material x) {
		material.remove(x);
	}
	public Material buscarMaterial(int codigoMaterial) {
		Material m;
		for(int i = 0; i<tamaño(); i++) {
			m = obtener(i);
			if(m.getCodigoMaterial().equals(codigoMaterial)){
				return m;
			}
		}
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
