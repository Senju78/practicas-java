package parte2;
import java.util.*;

import parte2.Insumo;
public class ListaInsumos {
    private ArrayList<Insumo> insumos;
    
    public ListaInsumos() {
    	this.insumos= new ArrayList<>();
    }
    public boolean agregarInsumo (Insumo insumo) {
    	boolean inserto=true;
    	if (buscarInsumoPorId(insumo.getIdProducto())==null)
    		insumos.add(insumo);
    	else inserto = false;
		return inserto;
    }
    
    public boolean eliminarInsumoPorId(String id) {
    	boolean eliminar = true;
    	Insumo insumo = buscarInsumoPorId(id);
    	if(insumo != null) {
    		insumos.remove(insumo);
    	}
    	else {
    		eliminar = false;
    	}
    	return eliminar;
    }
    
    public String toString() {
    	String resultado = "";
    	for(Insumo insumo : insumos) {
    		resultado += insumo.toString() + "\n";
    	}
    	return resultado;
    }
    
	private Insumo buscarInsumoPorId(String id) {
		for(Insumo insumo : insumos) {
			if(insumo.getIdProducto().equals(id)) {
				return insumo;
			}
		}
		return null;
	}
	
	public String buscarInsumo(String id) {
		for(Insumo insumo : insumos) {
			if(insumo.getIdProducto().equals(id)) {
				return insumo.getProducto();
			}
		}
		return null;
	}
	
	public String[] idInsumos() {
		int pos = -1;
		String[] datos = new String[this.insumos.size()];
		for (Insumo nodo : this.insumos) {
			pos++;
			datos[pos] = nodo.getIdProducto();
		}
		return datos;
	}
}
