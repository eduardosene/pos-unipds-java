package mx.florinda.leitor;

import mx.florinda.modelo.CategoriaCardapio;
import mx.florinda.modelo.ItemCardapio;
import mx.florinda.modelo.isento.ItemCardapioIsento;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeitorItensCardapioJSON implements LeitorItensCardapio{

    @Override
    public ItemCardapio[] processaArquivo(String nomeArquivo) throws IOException {
        Path arquivo = Path.of("00", "cardapio", nomeArquivo);
        String conteudoArquivo = Files.readString(arquivo);
        String[] linhaArquivo = conteudoArquivo.split("\n");
       ItemCardapio[] itens = new ItemCardapio[linhaArquivo.length];
        for(int i = 0; i < linhaArquivo.length; i++){
            String linha = linhaArquivo[i];
                linha = linha.replace("[", "");
                linha = linha.replace("]", "");
                linha = linha.replace("{", "");
                linha = linha.replace("}", "");
                linha = linha.replace("\"", "");
                String[] partes = linha.split(",");
                String parteId = partes[0];
                String[] propriedadeEValorId = parteId.split(":");
                String valorId = propriedadeEValorId[1].trim();
                IO.println(valorId);
                long id = Long.parseLong(valorId);

                String parteNome = partes[1];
                String[] propriedadeEValorNome = parteNome.split(":");
                String nome = propriedadeEValorNome[1].trim();

                String parteDescricao = partes[2];
                String[] propriedadeEValorDescricao = parteDescricao.split(":");
                String descricao = propriedadeEValorDescricao[1].trim();

                String partePreco = partes[3];
                String[] propriedadeEValorPreco = partePreco.split(":");
                String valorPreco = propriedadeEValorPreco[1].trim();
                double preco = Double.parseDouble(valorPreco);

                String parteCategoria = partes[4];
                String[] propriedadeEValorCategoria = parteCategoria.split(":");
                String valorCategoria = propriedadeEValorCategoria[1].trim();
                CategoriaCardapio categoria = CategoriaCardapio.valueOf(valorCategoria);

                ItemCardapio item;
                String parteImpostoIsento = partes[7];
                String[] propriedadeEValorImpostoIsento = parteImpostoIsento.split(":");
                String valorImpostoIsento = propriedadeEValorImpostoIsento[1].trim();
                boolean impostoIsento = Boolean.parseBoolean(valorImpostoIsento);
                if(impostoIsento){
                    item = new ItemCardapioIsento(id, nome, descricao, preco, categoria);
                } else{
                    item = new ItemCardapio(id, nome, descricao, preco, categoria);
                }
                String parteEmPromocao = partes[5];
                String[] propriedadeEValorEmPromocao = parteEmPromocao.split(":");
                String valorEmPromocao = propriedadeEValorEmPromocao[1].trim();
                boolean emPromocao = Boolean.parseBoolean(valorEmPromocao);
                if(emPromocao){
                    String partePrecoDesconto = partes[6];
                    String[] propriedadeEValorPrecoDesconto = partePrecoDesconto.split(":");
                    String valorPrecoDesconto = propriedadeEValorPrecoDesconto[1].trim();
                    double precoDesconto =  Double.parseDouble(valorPrecoDesconto);
                    item.setPromocao(precoDesconto);
                }
                itens[i] = item;
            }
        return itens;
        }

    }
