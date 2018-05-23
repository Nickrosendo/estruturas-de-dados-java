package estruturas;

import java.util.HashSet;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Nicolas
 */
public class Elevador {

    private ListaEstatica lista;
    private Pilha pilha;
    private int andaresCount, andarAtual;
    private String direcao;

    public Elevador(int andaresCount) {
        this.andarAtual = 0;
        this.andaresCount = andaresCount;
        this.lista = new ListaEstatica(andaresCount);
        this.pilha = new Pilha(andaresCount);
        for (int i = 0; i < andaresCount; i++) {
            this.lista.add(false, i);
        }
        this.direcao = "up";
    }

    public boolean isPushed(int andar) {
        if (lista.get(andar) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void valor(String key) {
        System.out.println(key + pilha.toString());
    }

    public void mover(int andar) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                while (!pilha.isEmpty()) {
                    if (direcao.equalsIgnoreCase("up")) {
                        andarAtual++;
                    } else {
                        andarAtual--;
                    }
                    if (andarAtual == pilha.top()) {
                        lista.set(false, pilha.top());
                        pilha.pop();
                    }
                }
                timer.cancel();
            }
        }, 3000, 3000);
    }

    public void chamar(int andar) {
        if (andar != andarAtual && !isPushed(andar)) {
            if (pilha.isEmpty()) {
                pilha.push(andar);
                lista.set(true, andar);
                if (andar > andarAtual) {
                    direcao = "up";
                } else {
                    direcao = "down";
                }
                mover(andar);
            } else {
                switch (direcao) {
                    case "down":
                        if (andar > pilha.top()) {
                            pilha.push(andar);
                            lista.set(true, andar);
                            mover(andar);
                        } else {
                            int[] auxStack = new int[pilha.size()];
                            int count = 0;
                            boolean desempilhou = false;
                            while (pilha.top() < andarAtual && pilha.top() > andar && !pilha.isEmpty()) {
                                auxStack[count] = pilha.pop();
                                count++;
                                desempilhou = true;
                            }
                            pilha.push(andar);
                            lista.set(true, andar);
                            if (desempilhou) {
                                for (int i = count; i > 0; i--) {
                                    pilha.push(auxStack[i - 1]);
                                    lista.set(true, auxStack[i - 1]);
                                }
                            }
                        }
                        break;
                    case "up":
                        if (andar < pilha.top()) {
                            pilha.push(andar);
                            lista.set(true, andar);
                            mover(andar);
                        } else {
                            int[] auxStack = new int[pilha.size()];
                            int count = 0;
                            boolean desempilhou = false;
                            while (pilha.top() > andarAtual && pilha.top() < andar && !pilha.isEmpty()) {
                                auxStack[count] = pilha.pop();
                                count++;
                                desempilhou = true;
                            }
                            pilha.push(andar);
                            lista.set(true, andar);
                            if (desempilhou) {
                                for (int i = count; i > 0; i--) {
                                    pilha.push(auxStack[i - 1]);
                                    lista.set(true, auxStack[i - 1]);
                                }
                            }
                        }
//                        valor("meio ");
//                        pilha.push(andar);
//                        valor("depois meio ");
//                        for (int i = 0; i < auxStack.length - 1; i++) {
//                            pilha.push(auxStack[i]);
//                        }
//                        valor("final4 ");
                        valor("finalfINAL ");
                        break;
                }
            }
        }
    }

    public int getAndarInicio() {
        return 0;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public String getDirecao() {
        return direcao;
    }

}
