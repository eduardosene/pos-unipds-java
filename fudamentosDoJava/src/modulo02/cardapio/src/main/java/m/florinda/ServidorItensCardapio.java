package m.florinda;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServidorItensCardapio {

    public static void main(String[] args) throws IOException {

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8000);
        HttpServer httpServer = HttpServer.create(inetSocketAddress, 0);

        httpServer.createContext("/itensCardapio.json", exchange -> {

            Path path = Path.of("itensCardapio.json");
            String json = Files.readString(path);

            byte[] bytes = json.getBytes(StandardCharsets.UTF_8);

            exchange.getResponseHeaders()
                    .add("Content-Type", "application/json; charset=UTF-8");

            exchange.sendResponseHeaders(200, bytes.length);

            try (OutputStream responseBody = exchange.getResponseBody()) {
                responseBody.write(bytes);
            }
        });

        System.out.println("Subiu servidor http!");
        httpServer.start();
    }
}
