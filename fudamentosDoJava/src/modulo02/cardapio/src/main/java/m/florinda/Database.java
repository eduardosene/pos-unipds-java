package m.florinda;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

import static m.florinda.ItemCardapio.CategoriaCardapio.*;

public class Database {
    public List<ItemCardapio> listaDeItensCardapio(){
        List<ItemCardapio> itens = new LinkedList<>();
        ItemCardapio refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves", """
        Suco de limão que parece de tamarindo e tem gosto de groselha.
        """, BEBIDAS, new BigDecimal("2.99"), null);
        itens.add(refrescoDoChaves);

        ItemCardapio sanduicheDePresuntoDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves", """
        Sanduíche de presunto simples, mas feito com muito amor.
        """, PRATOS_PRINCIPAIS, new BigDecimal("3.50"), new BigDecimal("2.99"));
        itens.add(sanduicheDePresuntoDoChaves);

        ItemCardapio tortaDeFrangoDaDonaFlorinda = new ItemCardapio(3L, "Torta de Frango da Dona Florinda", """
        Torta de frango com recheio cremoso e massa crocante.
        """, PRATOS_PRINCIPAIS, new BigDecimal("12.99"), new BigDecimal("10.99"));
        itens.add(tortaDeFrangoDaDonaFlorinda);

        ItemCardapio pipocaDoQuico = new ItemCardapio(4L, "Pipoca do Quico", """
        Balde de pipoca preparado com carinho pelo Quico.
        """, PRATOS_PRINCIPAIS, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itens.add(pipocaDoQuico);

        ItemCardapio aguaDeJamaica = new ItemCardapio(5L, "Água de Jamaica", """
        Água aromatizada com hibisco e toque de açúcar.
        """, BEBIDAS, new BigDecimal("2.50"), new BigDecimal("2.00"));
        itens.add(aguaDeJamaica);

        ItemCardapio churrosDoChaves = new ItemCardapio(6L, "Churros do Chaves", """
        Churros recheados com doce de leite, clássicos e irresistíveis.
        """, SOBREMESAS, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itens.add(churrosDoChaves);

        ItemCardapio tacosDeCarnitas = new ItemCardapio(7L, "Tacos de Carnitas", """
        Tacos recheados com carne tenra.
        """, PRATOS_PRINCIPAIS, new BigDecimal("25.90"), null);
        itens.add(tacosDeCarnitas);
        return itens;
    }
}
