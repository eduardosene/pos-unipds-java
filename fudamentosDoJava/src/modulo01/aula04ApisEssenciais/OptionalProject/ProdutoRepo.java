package fudamentosDoJava.src.modulo01.aula04ApisEssenciais.OptionalProject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProdutoRepo {
    private List<Produto> database;

    public ProdutoRepo() {
        this.database =  new ArrayList<>() {{
            add(new Produto(1, "computador", 1000.0));
            add(new Produto(2, "mouse", 50.0));
            add(new Produto(3, "teclado", 100.0));
        }};
    }
    public Optional<Produto> findById(int id) {
        for( Produto p : database ){
            if(p.getId() == id) return Optional.of(p);
        }
        return Optional.ofNullable(null);
    };


}

