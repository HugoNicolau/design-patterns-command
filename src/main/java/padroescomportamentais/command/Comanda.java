package padroescomportamentais.command;

import java.util.ArrayList;
import java.util.List;

// Receptor: quem de fato sabe adicionar, remover e alterar o total.
public class Comanda {

    private List<String> itens = new ArrayList<String>();
    private float total = 0;

    public void adicionarItem(String nome, float preco) {
        this.itens.add(nome);
        this.total += preco;
    }

    public void removerItem(String nome, float preco) {
        this.itens.remove(this.itens.lastIndexOf(nome));
        this.total -= preco;
    }

    public List<String> getItens() {
        return itens;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
