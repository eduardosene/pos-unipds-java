package m.florinda;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.IO.println;
import static m.florinda.ItemCardapio.CategoriaCardapio.*;

public class Main {
    static void main() {
        Database database = new Database();
        List<ItemCardapio> itens = database.listaDeItensCardapio();
        itens.forEach(System.out::println);

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
                itensPorCategoria.compute(categoria, (k, quantidadeAnterior) -> quantidadeAnterior + 1);
            }
        }
        System.out.println(itensPorCategoria);
        System.out.println("-------------");
        itens.stream()
                .collect(Collectors.groupingBy(
                        ItemCardapio::categoria,
                        LinkedHashMap::new,
                        Collectors.counting()))
                .forEach( (chave, valor) -> System.out.println(chave + "=>" + valor) );

        System.out.println("-------------");

        Optional<ItemCardapio> optionalItem = database.itemCardapioPorId(1L);
        String mensagem = optionalItem.map(ItemCardapio::toString).orElse("Nao encontrado");
        println(mensagem);

        System.out.println("-------------");
        // PRECISA MANTER AS CATEGORIAS QUE ESTAO EM PROMOCAO
        //Consegue adionar e está na ordem de criação
        Set<ItemCardapio.CategoriaCardapio> categoriasPromocao = new TreeSet<>();
        categoriasPromocao.add(SOBREMESAS);
        categoriasPromocao.add(ENTRADAS);
        categoriasPromocao.forEach(System.out::println);

        System.out.println("-------------");
        //Serve para coisas imuntavel
        // nao consegue adicionar e nao tem ordem
        Set<ItemCardapio.CategoriaCardapio> categoriaCardapios2 = Set.of(SOBREMESAS, ENTRADAS);
        categoriaCardapios2.forEach(System.out::println);

        System.out.println("-------------");
        //melhor forma, mais performatico
        EnumSet<ItemCardapio.CategoriaCardapio> categoriaCardapio = EnumSet.of(SOBREMESAS, ENTRADAS);
        categoriaCardapio.add(PRATOS_PRINCIPAIS);
        categoriaCardapio.forEach(System.out::println);
        System.out.println("-------------");
        //Preciso das descricoes associadas as categorias em promocao
        Map<ItemCardapio.CategoriaCardapio, String> promocoes = new EnumMap<>(ItemCardapio.CategoriaCardapio.class);
        promocoes.put(SOBREMESAS, "O doce perfeito para você!");
        promocoes.put(ENTRADAS, "Comece sua refeição com um toque de sabor!");
        System.out.println(promocoes);

        System.out.println("=============");
        // PRECISO DE UM HUSTORICO DE VISUALIZACAO DO CARDAPIO
        HistoricoVisualizacao historicoVisualizacao = new HistoricoVisualizacao(database);
        historicoVisualizacao.registrarVisualizacao(1L);
        historicoVisualizacao.registrarVisualizacao(2L);
        historicoVisualizacao.registrarVisualizacao(5L);
        historicoVisualizacao.registrarVisualizacao(6L);

        historicoVisualizacao.mostrarTotalItensVisualizados();
        historicoVisualizacao.listarVisualizacoes();

        //PRECISO DE REMOVER UM ITEM DE CARDAPIO

        Long idParaRemover = 1L;
        boolean removido = database.removerItemCardapio(idParaRemover);

        if(removido) System.out.println("Item removido: " + idParaRemover);
        else System.out.println("Item nao encontrado: " + idParaRemover);

        database.listaDeItensCardapio().forEach(System.out::println);

        // PRECISO ALTERAR O PRECO DE UM ITEM DO CARDAPIO
        database.alterarPrecoItemCardapio(2L, new BigDecimal("3.99"));
        database.alterarPrecoItemCardapio(2L, new BigDecimal("2.99"));
        database.alterarPrecoItemCardapio(2L, new BigDecimal("4.99"));

        // PRECISO AUDITAR AS MUDANCAS DE PRECO DOS ITENS DO CARDAPIO
        database.imprimirRastroAuditoriaPreco();
    }
}
