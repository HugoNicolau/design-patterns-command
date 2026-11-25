package padroescomportamentais.command;

// Guarda o total anterior para conseguir desfazer o desconto.
public class AplicarDescontoComando implements Comando {

    private Comanda comanda;
    private float percentual;
    private float totalAnterior;

    public AplicarDescontoComando(Comanda comanda, float percentual) {
        this.comanda = comanda;
        this.percentual = percentual;
    }

    public void executar() {
        this.totalAnterior = this.comanda.getTotal();
        this.comanda.setTotal(this.totalAnterior - (this.totalAnterior * this.percentual / 100));
    }

    public void desfazer() {
        this.comanda.setTotal(this.totalAnterior);
    }
}
