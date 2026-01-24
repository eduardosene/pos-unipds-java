public class TesteString {
    void main() {
        String nomeItem1 = "Refresco do Chaves";
        IO.println(nomeItem1.toLowerCase());
        IO.println(nomeItem1.toUpperCase());
        IO.println(nomeItem1.length());
        IO.println(nomeItem1.charAt(0));
        IO.println(nomeItem1.charAt(1));
        IO.println(nomeItem1.toLowerCase().replace(" ", "-"));
        IO.println(nomeItem1.contains("Chaves"));
        IO.println(nomeItem1.contains("chaves"));
        IO.println("startsWith: ".concat("" + nomeItem1.startsWith("Chaves")));
        IO.println("endsWith: ".concat("" + nomeItem1.endsWith("Chaves")));
        IO.println("concat: "+ nomeItem1.concat("teste"));
        IO.println("substring: "+ nomeItem1.substring(0, 8));
        IO.println("substring: "+ nomeItem1.substring(12));

        String[] pedacos = nomeItem1.split(" ");
        IO.println(pedacos.length);
        for(String pedaco: pedacos){
            IO.println(pedaco);
        }

        IO.println(nomeItem1 == "Refresco do Chaves");
       // String nomeItemDiigitado = IO.readln("Digite: ");
       // IO.println(nomeItem1.equalsIgnoreCase(nomeItemDiigitado));

        String nomeItem1Maiusculas  = nomeItem1.toUpperCase();
        IO.println(nomeItem1Maiusculas);

        long inicio = System.currentTimeMillis();
        String teste = "";
        for(int i = 0; i < 1_000; i++){
            teste += i + ", ";
        }
        long fim = System.currentTimeMillis();
        IO.println("Tempo String: " + (fim - inicio));
        IO.println(teste);

        long inicioSB = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < 1_000; i++){
           builder.append(i).append(", ");
        }
        long fimSB = System.currentTimeMillis();
        IO.println("Tempo StringBuilder: " + (fimSB - inicioSB));
        IO.println(builder);
    }
}
