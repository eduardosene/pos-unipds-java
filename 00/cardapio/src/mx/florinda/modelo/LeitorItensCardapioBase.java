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

    public ItemCardapio[] processaArquivo() throws IOException {
        Path arquivo = Path.of("00", "cardapio", nomeArquivo);
        String conteudoArquivo = Files.readString(arquivo);
        String[] linhaArquivo = conteudoArquivo.split("\n");
        ItemCardapio[] itens = new ItemCardapio[linhaArquivo.length];
        for(int i = 0; i < linhaArquivo.length; i++) {
            String linha = linhaArquivo[i];
            ItemCardapio item = processaLinha(linha);
            itens[i] = item;
        }
        return itens;
    }

    protected abstract ItemCardapio processaLinha(String linha);

}
