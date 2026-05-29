package m.florinda;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static void main() {
        Database database = new Database();
        List<ItemCardapio> itens = database.listaDeItensCardapio();

        Comparator<ItemCardapio.CategoriaCardapio> comparingPorNome = Comparator.comparing(ItemCardapio.CategoriaCardapio::name);

        Set<ItemCardapio.CategoriaCardapio> categorias = new TreeSet<>(comparingPorNome);
       for (ItemCardapio item : itens) {
          ItemCardapio.CategoriaCardapio categoria = item.categoria();
          categorias.add(categoria);
       }
        categorias.forEach(System.out::println);
        System.out.println("-------------");
        itens.stream().map(ItemCardapio::categoria)
                .collect(Collectors.toCollection(() -> new TreeSet<>(comparingPorNome)))
                .forEach(System.out:: println);

        Map<ItemCardapio.CategoriaCardapio, Integer> itensPorCategoria = new LinkedHashMap<>();

        for (ItemCardapio item: itens) {
            ItemCardapio.CategoriaCardapio categoria = item.categoria();
            if (!itensPorCategoria.containsKey(categoria)) {
                itensPorCategoria.put(categoria, 1);
            } else {
                Integer quantidadeAnterior = itensPorCategoria.get(categoria);
                itensPorCategoria.put(categoria, quantidadeAnterior + 1);
            }
        }
        System.out.println(itensPorCategoria);
        System.out.println("----------");
        itens.stream()
                .collect(Collectors.groupingBy(
                        ItemCardapio::categoria,
                        LinkedHashMap::new,
                        Collectors.counting()))
                .forEach( (chave, valor) -> System.out.println(chave + "=>" + valor) );
    }
}
