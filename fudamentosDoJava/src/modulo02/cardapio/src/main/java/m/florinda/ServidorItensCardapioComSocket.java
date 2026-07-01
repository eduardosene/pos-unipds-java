package m.florinda;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServidorItensCardapioComSocket {
    private static final Logger logger = Logger.getLogger(ServidorItensCardapioComSocket.class.getName());
    private static final Database database = new Database();
    static void main() throws IOException, InterruptedException {
        try(ExecutorService executorService = Executors.newFixedThreadPool(50);){
            try(ServerSocket serverSocket = new ServerSocket(8000)) {
                logger.info("Subiu servidor!");
                while(true) {
                    Socket clientSocket = serverSocket.accept();
                    executorService.execute(() -> trataRequisicao(clientSocket));
                }
            }
        }
    }

    private static void trataRequisicao(Socket clientSocket)  {
        try (clientSocket) {
            InputStream clientIS = clientSocket.getInputStream();
            StringBuilder requestBuilder = new StringBuilder();

            int data;
            do {
                data = clientIS.read();
                requestBuilder.append((char) data);
            } while (clientIS.available() > 0);

            String request = requestBuilder.toString();
            System.out.println("---------------------");
            logger.finest(request);
            logger.fine("\n\nChegou um novo request");

            Thread.sleep(250);

            String[] requestChunks = request.split("\r\n\r\n");
            String requestLineAndHeaders = requestChunks[0];
            String[] requestLineAndHeadersChuncks = requestLineAndHeaders.split("\r\n");
            String requestLine = requestLineAndHeadersChuncks[0];
            String[] requestLineChunks = requestLine.split(" ");

            String method = requestLineChunks[0];
            String requestURI = requestLineChunks[1];
            String httpVersion = requestLineChunks[2];

            logger.finer(() -> "Method: " + method);
            logger.finer(() -> "Request URI: " + requestURI);
            logger.finer(() -> "HTTP Version: " + httpVersion);

            OutputStream clientOS = clientSocket.getOutputStream();
            PrintStream clientOut = new PrintStream(clientOS);
            try {
                if (method.equalsIgnoreCase("GET") &&
                        requestURI.equalsIgnoreCase("/itensCardapio.json")) {
                    logger.fine("Chamou arquivo JSON");
                    Path path = Path.of("itensCardapio.json");
                    String json = Files.readString(path);

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(json);
                } else if (method.equalsIgnoreCase("GET") &&
                        requestURI.equalsIgnoreCase("/itensCardapio")) {
                    logger.fine("Chamou listagem de itens de cardapio");
                    List<ItemCardapio> listaDeItensCardapio = database.listaDeItensCardapio();

                    Gson gson = new Gson();
                    String json = gson.toJson(listaDeItensCardapio);

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(json);
                } else if (method.equalsIgnoreCase("GET") &&
                        requestURI.equalsIgnoreCase("/itensCardapio/total")) {
                    logger.fine("Chamou total de itens de cardapio");
                    List<ItemCardapio> listaDeItensCardapio = database.listaDeItensCardapio();

                    clientOut.println("HTTP/1.1 200 OK");
                    clientOut.println("Content-type: application/json; charset=UTF-8");
                    clientOut.println();
                    clientOut.println(listaDeItensCardapio.size());
                } else if (method.equalsIgnoreCase("POST") &&
                        requestURI.equalsIgnoreCase("/itensCardapio")) {
                    logger.fine("Chamou adicao de itens de cardapio");

                    if (requestChunks.length == 1) {
                        clientOut.println("HTTP/1.1 400 Bad Request");
                        return;
                    }
                    String body = requestChunks[1];

                    Gson gson = new Gson();
                    ItemCardapio novoItemCardapio = gson.fromJson(body, ItemCardapio.class);
                    System.out.println(novoItemCardapio);
                    database.adicionaItemCardapio(novoItemCardapio);

                    clientOut.println("HTTP/1.1 201 Created");
                }else if (method.equalsIgnoreCase("GET") &&
                        requestURI.equalsIgnoreCase("/") || "/en".equalsIgnoreCase(requestURI)) {
                    List<ItemCardapio> listaItensCardapio = database.listaDeItensCardapio();
                    Locale locale = "/en".equalsIgnoreCase(requestURI) ? Locale.US : Locale.of("pt", "BR");
                    NumberFormat formatadorMoeda = NumberFormat.getCurrencyInstance(locale);
                    ResourceBundle mensagens = ResourceBundle.getBundle("mensagens", locale);
                    DateTimeFormatter formatterDataHora = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG).withLocale(locale);
                    DateTimeFormatter formatterMesAno = DateTimeFormatter.ofPattern("MMMM/yyyy").withLocale(locale);

                    StringBuilder htmlTodosItens = new StringBuilder();
                    for (ItemCardapio item : listaItensCardapio){
                        String htmlPrecoItem;
                        if(item.precoComDesconto() == null){
                            htmlPrecoItem = "<strong>" + formatadorMoeda.format(item.preco())  + "</strong>";
                        } else  {
                            htmlPrecoItem = " <mark>Em promoção</mark> <strong>" + formatadorMoeda.format(item.precoComDesconto()) +"</strong> <s>"+formatadorMoeda.format(item.preco())+"</s>";
                        }
                        String categoria = mensagens.getString("categoria.cardapio." + item.categoria().name().toLowerCase());

                        String htmlItem = """
                                  <article>
                                        <kbd>%s</kbd>
                                        <h3>%s</h3>
                                        <p>%s</p>
                                       %s
                                    </article>
                                """.formatted(categoria, item.nome(), item.descricao(), htmlPrecoItem);
                        htmlTodosItens.append(htmlItem);
                    }

                    String html = """
                            <!DOCTYPE html>
                            <html lang="en">
                            <head>
                                <meta charset="UTF-8">
                                <title>Florinda Eats - Cardápio</title>
                                <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@picocss/pico@2.1.1/css/pico.min.css">
                            </head>
                            <body>
                            
                                <header class="container">
                                    <hgroup>
                                      <h1>Florinda Eats</h1>
                                      <p>O sabor da Vila direto pra você</p>
                                    </hgroup>
                                </header>
                            
                                <main class="container">
                                    <h2>Cardápio</h2>
                                  %s
                                </main>
                            
                                <footer class="container">
                                    <p><small><em>Preços de acordo com %s</em></small></p>
                                    <p><strong>Florinda Eats</strong> Todos os direitos reservados - %s</p>
                                </footer>
                            </body>
                            </html>
                            """.formatted(htmlTodosItens.toString(), formatterDataHora.format(ZonedDateTime.now()), formatterMesAno.format(ZonedDateTime.now()));

                    clientOut.print("HTTP/1.1 200 OK\r\n");
                    clientOut.print("Content-type: text/html; charset=UTF-8\r\n\r\n");
                    clientOut.print(html);
                    clientOut.print("\r\n");
                }
                else {
                    logger.warning(() -> "URI nao encontrada: " + requestURI);
                    clientOut.println("HTTP/1.1 404 NOT FOUND");
                }
            } catch (Exception e) {
                logger.log(Level.SEVERE,e , () -> "Erro ao tratar " + method + " " + requestURI);
                clientOut.println("HTTP/1.1 500 Internal Server Error");
                clientOut.println();
                clientOut.println(e.getMessage());
            }

        } catch (IOException | InterruptedException e) {
            //logger.severe("Erro no servidor");
            logger.log(Level.SEVERE, "Erro no servidor", e);
            throw new RuntimeException(e);
        }
    }
}
