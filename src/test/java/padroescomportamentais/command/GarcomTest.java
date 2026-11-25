package padroescomportamentais.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class GarcomTest {

    Garcom garcom;
    Comanda comanda;

    @BeforeEach
    void setUp() {
        garcom = new Garcom();
        comanda = new Comanda();
    }

    @Test
    void deveAdicionarItens() {
        garcom.executar(new AdicionarItemComando(comanda, "X-Burguer", 25.0f));
        garcom.executar(new AdicionarItemComando(comanda, "Suco", 6.0f));

        assertEquals(Arrays.asList("X-Burguer", "Suco"), comanda.getItens());
        assertEquals(31.0f, comanda.getTotal(), 0.01f);
    }

    @Test
    void deveDesfazerUltimoItem() {
        garcom.executar(new AdicionarItemComando(comanda, "X-Burguer", 25.0f));
        garcom.executar(new AdicionarItemComando(comanda, "Suco", 6.0f));

        garcom.desfazerUltimo();

        assertEquals(Arrays.asList("X-Burguer"), comanda.getItens());
        assertEquals(25.0f, comanda.getTotal(), 0.01f);
    }

    @Test
    void deveAplicarDesconto() {
        garcom.executar(new AdicionarItemComando(comanda, "X-Burguer", 25.0f));
        garcom.executar(new AdicionarItemComando(comanda, "Combo Kids", 25.0f));
        garcom.executar(new AplicarDescontoComando(comanda, 10.0f));

        assertEquals(45.0f, comanda.getTotal(), 0.01f);
    }

    @Test
    void deveDesfazerDesconto() {
        garcom.executar(new AdicionarItemComando(comanda, "X-Burguer", 25.0f));
        garcom.executar(new AdicionarItemComando(comanda, "Combo Kids", 25.0f));
        garcom.executar(new AplicarDescontoComando(comanda, 10.0f));

        garcom.desfazerUltimo();

        assertEquals(50.0f, comanda.getTotal(), 0.01f);
        assertEquals(Arrays.asList("X-Burguer", "Combo Kids"), comanda.getItens());
    }

    @Test
    void deveDesfazerOperacoesEmOrdemInversa() {
        garcom.executar(new AdicionarItemComando(comanda, "X-Burguer", 25.0f));
        garcom.executar(new AdicionarItemComando(comanda, "Combo Kids", 25.0f));
        garcom.executar(new AplicarDescontoComando(comanda, 10.0f));

        garcom.desfazerUltimo();
        assertEquals(50.0f, comanda.getTotal(), 0.01f);

        garcom.desfazerUltimo();
        assertEquals(Arrays.asList("X-Burguer"), comanda.getItens());
        assertEquals(25.0f, comanda.getTotal(), 0.01f);

        garcom.desfazerUltimo();
        assertEquals(Collections.emptyList(), comanda.getItens());
        assertEquals(0.0f, comanda.getTotal(), 0.01f);
    }

    @Test
    void naoDeveAlterarComandaAoDesfazerSemHistorico() {
        garcom.desfazerUltimo();

        assertEquals(Collections.emptyList(), comanda.getItens());
        assertEquals(0.0f, comanda.getTotal(), 0.01f);
    }

}
