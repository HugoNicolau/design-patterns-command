package padroescomportamentais.command;

import java.util.ArrayDeque;
import java.util.Deque;

// Invocador: executa os comandos e guarda o histórico numa pilha para desfazer.
public class Garcom {

    private Deque<Comando> historico = new ArrayDeque<Comando>();

    public void executar(Comando comando) {
        comando.executar();
        this.historico.push(comando);
    }

    public void desfazerUltimo() {
        if (!this.historico.isEmpty()) {
            this.historico.pop().desfazer();
        }
    }
}
