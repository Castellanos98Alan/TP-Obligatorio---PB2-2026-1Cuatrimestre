package ar.edu.unlam.centroDeBienEstar;

import ar.edu.unlam.pbii.Descuento;

public class DescuentoPorBloques implements Descuento {


    public DescuentoPorBloques() {}

    @Override
    public Double aplicarDescuento(Double total, Integer cantidadDeClases) {
        if (cantidadDeClases >= 9) {
            return total * 0.50;
        } else if (cantidadDeClases >= 6) {
            return total * 0.70;
        } else if (cantidadDeClases >= 3) {
            return total * 0.80;
        }
        return total;
    }
}

