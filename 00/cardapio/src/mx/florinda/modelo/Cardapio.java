package mx.florinda.modelo;
import mx.florinda.modelo.isento.ItemCardapioIsento;

public class Cardapio {
    private final ItemCardapio[] itens;

    public Cardapio(String nomeArquivo) {
        IO.println(nomeArquivo);
        final ItemCardapio item1 = new ItemCardapio(1L, "Refresco do Chaves", "Suco de limão que parece de tamarindo e tem gosto de groselha.", 2.99, CategoriaCardapio.BEBIDAS);
        item1.setEmPromocao(false);

        final ItemCardapio item2 = new ItemCardapio(2L, "Sanduíche de Presunto do Chaves", "Sanduíche de presunto simples, mas feito com muito amor.", 3.50, CategoriaCardapio.PRATOS_PRINCIPAIS);
        item2.setPromocao(2.99);

        final  ItemCardapio item3 = new ItemCardapio(3L, "Torta de Frango da Dona Florinda", "Torta de frango com recheio cremoso e massa crocante.", 12.99, CategoriaCardapio.PRATOS_PRINCIPAIS);
        item3.setPromocao(10.99);

        final ItemCardapio item4 = new ItemCardapioIsento(4L, "Pipoca do Quico", "Balde de pipoca preparado com carinho pelo Quico.", 4.99, CategoriaCardapio.PRATOS_PRINCIPAIS);
        item4.setPromocao(3.99);

        final ItemCardapio item5 = new ItemCardapio(5L, "Água de Jamaica", "Água aromatizada com hibisco e toque de açúcar.", 2.50, CategoriaCardapio.BEBIDAS);
        item5.setPromocao(2.0);

        final ItemCardapio item6 = new ItemCardapioIsento(6L, "Churros do Chaves", "Recheados com doce de leite, clássicos e irresistíveis.", 4.99, CategoriaCardapio.SOBREMESAS);
        item6.setPromocao(3.99);

        final ItemCardapio item7 = new ItemCardapioIsento(7L, "Tacos de Carnitas", "Tacos recheados com carne tenra.", 25.9, CategoriaCardapio.PRATOS_PRINCIPAIS);

        this.itens = new ItemCardapio[]{item1, item2, item3, item4, item5, item6, item7};
    }

    public double getSomaDosPrecos() {
        double totalDePrecos = 0.0;
        for (ItemCardapio item : itens) {
            totalDePrecos += item.getPreco();
        }
        return totalDePrecos;
    }

    public int getTotalDeItensEmPromocao() {
        int totalItensEmPromocao = 0;

        for (ItemCardapio item : itens) {
            if (item.isEmPromocao()) {
                totalItensEmPromocao++;
            }
        }
        return totalItensEmPromocao;
    }

    public double getPrimeiroPrecoMaiorQueLimite(double precoLimite) {
        double precoMaiorQueLimite = -1.0;

        for (ItemCardapio item : itens) {
            if (item.getPreco() > precoLimite) {
                precoMaiorQueLimite = item.getPreco();
                break;
            }
        }
        return precoMaiorQueLimite;
    }

    public mx.florinda.modelo.ItemCardapio getItemPorId(long idSelecionado) {
       return this.itens[((int) idSelecionado) - 1];
    }

    public mx.florinda.modelo.ItemCardapio[] getItens() {
        return this.itens;
    }
}
