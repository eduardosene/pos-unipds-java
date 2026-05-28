package fudamentosDoJava.src.modulo01.aula04ApisEssenciais.RegexProject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainClass {
    static void main() {
        String email = "eduardo@edu.com";
        System.out.println(email.matches("^[\\w._-]+@[\\w]+\\.[a-zA-Z0-9]{2,6}$"));

        String texto =  "O Cep da residencia é 01234-567";
        Pattern pattern = Pattern.compile("\\d{5}-\\d{3}");
        Matcher matcher = pattern.matcher(texto);

        if(matcher.find()){
            System.out.println(matcher.group());
        }
    }
}
