package mx.florinda.leitor;

import mx.florinda.modelo.ItemCardapio;

import java.io.IOException;

public interface LeitorItensCardapio {
    ItemCardapio[] processaArquivo() throws IOException;
    static LeitorItensCardapio criaLeitor(String nomeArquivo){
        LeitorItensCardapio leitor = null;
        if(nomeArquivo.endsWith(".csv")){
            leitor = new LeitorItensCardapioCSV(nomeArquivo);
        } else if (nomeArquivo.endsWith(".json")){
            leitor = new LeitorItensCardapioJSON(nomeArquivo);
        }
        return leitor;
    }
}
