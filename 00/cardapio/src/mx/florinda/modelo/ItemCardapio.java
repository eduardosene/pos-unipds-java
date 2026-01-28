package mx.florinda.modelo;

public class ItemCardapio {
    private final long id;
    private final String nome;
    private final String descricao;
    private boolean emPromocao;
    private final double preco;
    private double precoComDesconto;

    CategoriaCardapio categoria;

    //construtor
    public ItemCardapio(long id, String nome, String descricao, double preco, CategoriaCardapio categoria) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
    }

    //metodos
    public double getPorcentagemDesconto() {
        return (preco - precoComDesconto) / preco * 100;
    }

    public CategoriaCardapio getNomeCategoria() {
        return categoria;
    }

    public void setPromocao(double precoComDesconto) {
        this.emPromocao = true;
        this.precoComDesconto = precoComDesconto;

    }
    public double getImposto() {
        if(this.emPromocao) {
           return precoComDesconto * 0.1;
        }
        return preco * 0.1;
    }

    public long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isEmPromocao() {
        return emPromocao;
    }

    public void setEmPromocao(boolean emPromocao) {
        this.emPromocao = emPromocao;
    }

    public double getPreco() {
        return preco;
    }

    public double getPrecoComDesconto() {
        return precoComDesconto;
    }

    public CategoriaCardapio getCategoria() {
        return categoria;
    }
}
