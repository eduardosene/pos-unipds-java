package mx.florinda.cli;

import mx.florinda.modelo.Cardapio;
import mx.florinda.modelo.ItemCardapio;

public class Teste {
    public static void main(String[] args) {
        String nomeArquivo = IO.readln("Digite um nome de arquivo de itens de cardápio: ");
        Cardapio cardapio = new Cardapio(nomeArquivo);
        String linha = IO.readln("Digite um id de um item de cardápio: ");
        long idSelecionado = Long.parseLong(linha);

        ItemCardapio itemSelecionado = cardapio.getItemPorId(idSelecionado);

        IO.println("== Item do Cardápio ==");
        IO.println("Id: " + itemSelecionado.getId());
        IO.println("Nome: " + itemSelecionado.getNome());
        IO.println("Descrição: " + itemSelecionado.getDescricao());
        if (itemSelecionado.isEmPromocao()) {
            IO.println("Item em promoção!");
            double porcentagemDesconto = itemSelecionado.getPorcentagemDesconto();
            IO.println("Preco de " + itemSelecionado.getPreco() + " por " + itemSelecionado.getPrecoComDesconto());
            IO.println("Porcentagem de desconto: " + porcentagemDesconto);
        } else {
            IO.println("Preco: " + itemSelecionado.getPreco());
        }
        IO.println("Categoria: " + itemSelecionado.getNomeCategoria());
        IO.println("Imposto: " + itemSelecionado.getImposto());

        IO.println("--------");
        IO.println("Soma dos precos: " + cardapio.getSomaDosPrecos());
        IO.println("Total de itens em promoção: " + cardapio.getTotalDeItensEmPromocao());

        double precoLimite = 10.0;
        IO.println("O primeiro preço que é maior que " + precoLimite + ": " + cardapio.getPrimeiroPrecoMaiorQueLimite(precoLimite));
        for (ItemCardapio item : cardapio.getItens()) {
            if (item.getPreco() <= precoLimite) {
                IO.println("Preço menor que " + precoLimite + ": " + item.getPreco());
            }
        }
    }
}





