package modulo01.aula04ApisEssenciais;

public class MainClass {
    static void main() {
        ProdutoRepo repo = new ProdutoRepo();
        //Produto p = repo.findById(10).orElse(new Produto(-1, "Produto inexistente",0));
        Produto p = repo.findById(10).orElseThrow(() -> new RuntimeException("Produto inexistente"));
        System.out.println(p.getNome());
    }

}
