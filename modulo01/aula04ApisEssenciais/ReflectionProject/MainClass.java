package modulo01.aula04ApisEssenciais.ReflectionProject;

public class MainClass {
    static void main() throws Exception {
        Produto p = new Produto(1, "Computador", 1000.0);
        Cliente c = new Cliente(1, "Eduardo", "039484512", "eduardo@edu.com.br");
        ClassExplorer.exploreMetadata(c);
    }
}
