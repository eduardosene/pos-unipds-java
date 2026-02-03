package mx.florinda.leitor;
import com.google.gson.Gson;
import mx.florinda.modelo.ItemCardapio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeitorItensCardapioGSON implements LeitorItensCardapio {
    private final String nomeArquivo;

    public LeitorItensCardapioGSON(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }
    @Override
    public ItemCardapio[] processaArquivo() {
        try {
            Path arquivo = Path.of("00", "cardapio", nomeArquivo);
            String conteudoArquivo = Files.readString(arquivo);
            return new Gson().fromJson(conteudoArquivo, ItemCardapio[].class);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

    }
}
