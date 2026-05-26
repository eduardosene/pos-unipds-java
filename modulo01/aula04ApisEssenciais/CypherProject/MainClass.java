package modulo01.aula04ApisEssenciais.CypherProject;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class MainClass {
    static void main() throws Exception {
        String texto =  "Hello World!!";
        System.out.println("Criptografando...");
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey secretKey = keyGen.generateKey();
        System.out.println("Chave: " + secretKey);

        String textoCriptografado = criptografar(texto, secretKey);
        System.out.println(textoCriptografado);

        String textoDecriptografado = decriptografar(textoCriptografado, secretKey);
        System.out.println(textoDecriptografado);

    }
    public static String criptografar(String textoOriginal, SecretKey chave) throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, chave);
        byte[] textoByte = cipher.doFinal(textoOriginal.getBytes());
        return Base64.getEncoder().encodeToString(textoByte);
    }
    public static String decriptografar(String textoCripto, SecretKey chave) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, chave);
        byte[] cryptoByte = Base64.getDecoder().decode(textoCripto.getBytes());
        byte[] textoByte = cipher.doFinal(cryptoByte);
        return new String(textoByte);
    }

}
