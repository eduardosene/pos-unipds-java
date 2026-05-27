package modulo01.aula07Streams;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainClass {
    static void main() {
        List<Veiculo> lista = new ArrayList<>() {{
            add(new Veiculo("Corsa", "Cinza", 25000, 1998, 160));
            add(new Veiculo("Corolla", "Prata", 70000, 2020, 200));
            add(new Veiculo("Corolla", "Preto", 100000, 2025, 210));
            add(new Veiculo("X1", "Branco", 250000, 2023, 250));
            add(new Veiculo("GLA200", "Prata", 300000, 2025, 280));
        }};

       List<Veiculo> ordenada = lista.stream()
               .sorted(Comparator.comparing( Veiculo::getMarca)
               .reversed())
               .collect(Collectors.toList());
        System.out.println(ordenada);
        ordenada.add(new Veiculo("gol", "azul", 18000, 1995, 190));
    }
}
