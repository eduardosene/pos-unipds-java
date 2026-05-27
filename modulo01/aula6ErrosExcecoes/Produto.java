package modulo01.aula6ErrosExcecoes;

public class Produto {
    private int id;
    private String nome;
    private double preco;

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id)  throws MyException{
        if(id <= 0 ) throw new MyException("ID nao pode ser menor ou igual a zero");
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws MyException{
        if (nome==null || nome.isEmpty()) throw new MyException("Nome nao pode ser nulo ou vazio");
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
