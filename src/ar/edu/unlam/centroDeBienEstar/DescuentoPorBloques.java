package ar.edu.unlam.centroDeBienEstar;

import ar.edu.unlam.pbii.Descuento;

public class DescuentoPorBloques implements Descuento {

	private Double precioFinal;
	
	public DescuentoPorBloques() {
		this.precioFinal = 0.0;
	}

	@Override
	public Double aplicarDescuento(Double total, Integer cantidadDeClases) {
		    if(cantidadDeClases>=3)
		    {
		    	if(cantidadDeClases>=6)
		    	{
		    		
		    		if(cantidadDeClases>=9)
		    		{
		    			precioFinal= total*0.50;
		    			
		    		}
		    			
		    			precioFinal= total*0.70;
		    		
		    	}
		    	
		    	
		    	precioFinal= total*0.80;

		    }
		    
		    return precioFinal = total;
		
	}
	

}
