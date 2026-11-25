package padroescomportamentais.command;

public class AdicionarItemComando implements Comando {

    private Comanda comanda;
    private String nome;
    private float preco;

    public AdicionarItemComando(Comanda comanda, String nome, float preco) {
        this.comanda = comanda;
        this.nome = nome;
        this.preco = preco;
    }

    public void executar() {
        this.comanda.adicionarItem(this.nome, this.preco);
    }

    public void desfazer() {
        this.comanda.removerItem(this.nome, this.preco);
    }
}
