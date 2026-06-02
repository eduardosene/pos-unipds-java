package m.florinda;

import java.math.BigDecimal;
import java.util.*;

import static m.florinda.ItemCardapio.CategoriaCardapio.*;

public class Database {
    private Map<Long, ItemCardapio> itensPorIds= new HashMap<>();
    private final Map<ItemCardapio, BigDecimal> auditoriaPrecos = new IdentityHashMap<>();

    public Database() {
        ItemCardapio refrescoDoChaves = new ItemCardapio(1L, "Refresco do Chaves", """
        Suco de limão que parece de tamarindo e tem gosto de groselha.
        """, BEBIDAS, new BigDecimal("2.99"), null);
        itensPorIds.put(1L, refrescoDoChaves);

        ItemCardapio sanduicheDePresuntoDoChaves = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves", """
        Sanduíche de presunto simples, mas feito com muito amor.
        """, PRATOS_PRINCIPAIS, new BigDecimal("3.50"), new BigDecimal("2.99"));
        itensPorIds.put(2L, sanduicheDePresuntoDoChaves);

        ItemCardapio tortaDeFrangoDaDonaFlorinda = new ItemCardapio(3L, "Torta de Frango da Dona Florinda", """
        Torta de frango com recheio cremoso e massa crocante.
        """, PRATOS_PRINCIPAIS, new BigDecimal("12.99"), new BigDecimal("10.99"));
        itensPorIds.put(3L, tortaDeFrangoDaDonaFlorinda);

        ItemCardapio pipocaDoQuico = new ItemCardapio(4L, "Pipoca do Quico", """
        Balde de pipoca preparado com carinho pelo Quico.
        """, PRATOS_PRINCIPAIS, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itensPorIds.put(4L, pipocaDoQuico);

        ItemCardapio aguaDeJamaica = new ItemCardapio(5L, "Água de Jamaica", """
        Água aromatizada com hibisco e toque de açúcar.
        """, BEBIDAS, new BigDecimal("2.50"), new BigDecimal("2.00"));
        itensPorIds.put(5L, aguaDeJamaica);

        ItemCardapio churrosDoChaves = new ItemCardapio(6L, "Churros do Chaves", """
        Churros recheados com doce de leite, clássicos e irresistíveis.
        """, SOBREMESAS, new BigDecimal("4.99"), new BigDecimal("3.99"));
        itensPorIds.put(6L, churrosDoChaves);

        ItemCardapio tacosDeCarnitas = new ItemCardapio(7L, "Tacos de Carnitas", """
        Tacos recheados com carne tenra.
        """, PRATOS_PRINCIPAIS, new BigDecimal("25.90"), null);
        itensPorIds.put(7L, tacosDeCarnitas);
    }

    public List<ItemCardapio> listaDeItensCardapio(){
        return new ArrayList<>(itensPorIds.values());
    }
    public Optional<ItemCardapio> itemCardapioPorId(Long itemId) {
        return Optional.ofNullable(itensPorIds.get(itemId));
    }

    public boolean removerItemCardapio(Long idParaRemover) {
        ItemCardapio remove = itensPorIds.remove(idParaRemover);
        return remove != null;

    }

    public boolean alterarPrecoItemCardapio(Long itemId, BigDecimal novoPreco) {
        ItemCardapio itemAntigo = itensPorIds.get(itemId);
        if (itemAntigo == null) return false;
        ItemCardapio itemComPrecoAlterado = itemAntigo.alterarPreco(novoPreco);
        itensPorIds.put(itemId, itemComPrecoAlterado);
        auditoriaPrecos.put(itemAntigo, novoPreco);
        return true;
        
    }

    public void imprimirRastroAuditoriaPreco() {
        System.out.println("\nAuditoria de preços:");
        auditoriaPrecos.forEach((itemAntigo, novoPreco) -> {
            System.out.printf(" - %s: %s => %s", itemAntigo.nome(), itemAntigo.preco(), novoPreco);
            System.out.println();
        });
    }
}
