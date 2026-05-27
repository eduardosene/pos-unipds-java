package modulo01.aula07Streams;

public class Veiculo {
    private String marca;
    private String cor;
    private double preco;
    private int ano;
    private int velMaxima;

    public Veiculo(String marca, String cor, double preco, int ano, int velMaxima) {
        this.marca = marca;
        this.cor = cor;
        this.preco = preco;
        this.ano = ano;
        this.velMaxima = velMaxima;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "marca='" + marca + '\'' +
                ", cor='" + cor + '\'' +
                ", preco=" + preco +
                ", ano=" + ano +
                ", velMaxima=" + velMaxima +
                '}';
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getVelMaxima() {
        return velMaxima;
    }

    public void setVelMaxima(int velMaxima) {
        this.velMaxima = velMaxima;
    }
}
