package modulo01.aula04ApisEssenciais;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepo {
    private List<Produto> database;

    public ProdutoRepo() {
        this.database =  new ArrayList<>() {{
            add(new Produto(1, "computador", 1000.0));
            add(new Produto(2, "mouse", 50.0));
            add(new Produto(3, "teclado", 100.0));
        }};
    }
    public Produto findById(int id) {
        for( Produto p : database ){
            if(p.getId() == id) return p;
        }
        return null;
    };


}

