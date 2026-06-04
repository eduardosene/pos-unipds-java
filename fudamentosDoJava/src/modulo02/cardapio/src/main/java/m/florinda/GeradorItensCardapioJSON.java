package m.florinda;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GeradorItensCardapioJSON {
    static void main() throws IOException {
        Database database = new Database();
        List<ItemCardapio> listaDeItensCardapio = database.listaDeItensCardapio();

        Gson gson = new Gson();
        String json = gson.toJson(listaDeItensCardapio);
        Path path = Path.of("itensCardapio.json");
        Files.writeString(path, json);
    }
}
