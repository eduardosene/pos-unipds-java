package mx.florinda.leitor;

import mx.florinda.modelo.CategoriaCardapio;
import mx.florinda.modelo.ItemCardapio;
import mx.florinda.modelo.LeitorItensCardapioBase;
import mx.florinda.modelo.isento.ItemCardapioIsento;

public class LeitorItensCardapioCSV extends LeitorItensCardapioBase {
    private static final String SEPARADOR = ";"; //constante

    public LeitorItensCardapioCSV(String nomeArquivo) {
        super(nomeArquivo);
    }

    @Override
    public ItemCardapio processaLinha(String linha){
                String[] partes = linha.split(SEPARADOR);
                long id = Long.parseLong(partes[0]);
                String nome = partes[1];
                String descricao = partes[2];
                double preco = Double.parseDouble(partes[3]);
                CategoriaCardapio categoria = CategoriaCardapio.valueOf(partes[4]);

                ItemCardapio item;
                boolean impostoIsento = Boolean.parseBoolean(partes[7]);
                if(impostoIsento){
                    item = new ItemCardapioIsento(id, nome, descricao, preco, categoria);
                } else{
                    item = new ItemCardapio(id, nome, descricao, preco, categoria);
                }

                boolean emPromocao = Boolean.parseBoolean(partes[5]);
                if(emPromocao){
                    double precoDesconto;
                    precoDesconto = Double.parseDouble(partes[6]);
                    item.setPromocao(precoDesconto);
                }
                return item;

    }

}
