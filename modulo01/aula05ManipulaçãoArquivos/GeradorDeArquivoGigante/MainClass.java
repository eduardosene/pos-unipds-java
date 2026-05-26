package modulo01.aula05ManipulaçãoArquivos.GeradorDeArquivoGigante;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class MainClass {
    static void main() {
        String fileName = "benchmark.txt";
        String linhaBase = "Esta é uma linha de exemplo de benchmark de leitura de arquivo";
        long tamanho = 100L * 1024 * 1024;
        try{
            long tamanhoAtual = 0;
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
            while (tamanhoAtual < tamanho){
                writer.write(linhaBase);
                tamanhoAtual += linhaBase.getBytes().length;
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
