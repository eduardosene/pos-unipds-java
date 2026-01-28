package mx.florinda.cli;

import mx.florinda.modelo.CategoriaCardapio;
import mx.florinda.modelo.ItemCardapio;

public class TesteObjetos extends Object {
    static void main() {
        Object[] lista = new Object[5];

        lista[0] = new Integer(1);
        lista[1] = new Double(32.5);
        lista[2] = "Guacamole";
        lista[3] = true;
        lista[4] = new ItemCardapio(10L, "Item 10", "Um item bem legal", 10.99, CategoriaCardapio.BEBIDAS);


        for(Object item : lista) {
            IO.println(item);
        }
        IO.println("----------------");
        ItemCardapio item = new ItemCardapio(10L, "Item 10", "Um item bem legal", 10.99, CategoriaCardapio.BEBIDAS);
        IO.println(item);
        IO.println("----------------");
        IO.println(lista[4] == item);
        IO.println(lista[4].equals(item));
    }
}
