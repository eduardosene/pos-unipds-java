package mx.florinda.leitor;

import mx.florinda.modelo.ItemCardapio;

public class FabricaLeitorItensCardapio {
    public LeitorItensCardapio criaLeitor(String nomeArquivo){
        LeitorItensCardapio leitor = null;
        if(nomeArquivo.endsWith(".csv")){
            leitor = new LeitorItensCardapioCSV();
        } else if (nomeArquivo.endsWith(".json")){
           leitor = new LeitorItensCardapioJSON();
        }
        return leitor;
    }
}
