package mx.florinda.modelo;

import mx.florinda.leitor.LeitorItensCardapio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class LeitorItensCardapioBase implements LeitorItensCardapio {
    private final String nomeArquivo;

    public LeitorItensCardapioBase(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public ItemCardapio[] processaArquivo() {
        try {
            Path arquivo = Path.of("00", "cardapio", nomeArquivo);
            String conteudoArquivo = Files.readString(arquivo);
            String[] linhaArquivo = conteudoArquivo.split("\n");
            ItemCardapio[] itens = new ItemCardapio[linhaArquivo.length-1];
            for(int i = 1; i < linhaArquivo.length; i++) {
                String linha = linhaArquivo[i];
                ItemCardapio item = processaLinha(linha);
                itens[i-1] = item;
            }
            return itens;
        } catch (IOException ex) {
            throw  new RuntimeException(ex);
        }

    }

    protected abstract ItemCardapio processaLinha(String linha);

}
