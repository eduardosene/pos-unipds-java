package mx.florinda.modelo;
import mx.florinda.leitor.LeitorItensCardapio;

import java.io.IOException;

public class Cardapio {
    private final ItemCardapio[] itens;

    public Cardapio(String nomeArquivo) {
        LeitorItensCardapio leitor = LeitorItensCardapio.criaLeitor(nomeArquivo);
        itens = leitor.processaArquivo();
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
