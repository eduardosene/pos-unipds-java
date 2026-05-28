package fudamentosDoJava.src.modulo01.aula07Streams;

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

        List<Veiculo> corolas = lista.stream()
                .filter( v -> v.getMarca().equalsIgnoreCase("corolla")).toList();

        double precoMedio = lista.stream().mapToDouble(Veiculo::getPreco).average().orElse(0.0);
        System.out.println("Preco Medio = "+ precoMedio);

        double precoMaximo = lista.stream().mapToDouble(Veiculo::getPreco).max().orElse(0.0);
        System.out.println("Preco Maximo = "+ precoMaximo);

        double precoMinimo = lista.stream().mapToDouble(Veiculo::getPreco).min().orElse(0.0);
        System.out.println("Preco Minimo = "+ precoMinimo);

        double mediaDosCorollas = lista.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("corolla"))
                .mapToDouble(Veiculo::getPreco)
                .average()
                .orElse(0.0);
        System.out.println("Media dos corollas = "+mediaDosCorollas);

        lista.stream()
                .filter(v -> v.getMarca().equalsIgnoreCase("corolla"))
                .map(MainClass::converterParaMaiusculo).forEach(System.out::println);
    }
    public static Veiculo converterParaMaiusculo(Veiculo v ){
        return new Veiculo(v.getMarca().toUpperCase(),
                v.getCor().toUpperCase(),
                v.getPreco(),
                v.getAno(),
                v.getVelMaxima());
    }
}
