package modulo01.aula04ApisEssenciais;

public class MainClass {
    static void main() {
        ProdutoRepo repo = new ProdutoRepo();
        System.out.println(repo.findById(2));
    }

}
