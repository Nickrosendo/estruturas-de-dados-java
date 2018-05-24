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

    public boolean emMovimento() {
        return !pilha.isEmpty();
    }

    public boolean isPushed(int andar) {
        if (lista.get(andar) == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void valor(String key) {
        System.out.println(key + lista.toString());
    }

    public void mover() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (emMovimento()) {
                    if (direcao.equalsIgnoreCase("up")) {
                        andarAtual++;
                    } else {
                        andarAtual--;
                    }
                    if (andarAtual == pilha.top()) {
//                        valor("ValorMove: ");
                        lista.set(false, pilha.top());
                        pilha.pop();
                    }
                    System.out.println("AndarAtualElevador: " + andarAtual);
                } else {
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

    private void handlePush(int andar) {
        pilha.push(andar);
        lista.set(true, andar);
        mover();
    }

    public void chamar(int andar) {
        if (andar != andarAtual && !isPushed(andar)) {
            if (pilha.isEmpty()) {
                handlePush(andar);
                System.out.println("chamouVazio");
                if (andar > andarAtual) {
                    direcao = "up";
                } else {
                    direcao = "down";
                }
            } else {
                switch (direcao) {
                    case "down":
                        if (andar > pilha.top()) {
                            handlePush(andar);
                            System.out.println("chamouAndarMaiorDescendo");
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
                            System.out.println("chamouAndarMaiorDescendoDesempilhado");
                            lista.set(true, andar);
                            if (desempilhou) {
                                for (int i = count; i > 0; i--) {
                                    pilha.push(auxStack[i - 1]);
                                    System.out.println("chamouAndarMaiorDescendoempilhado");
                                    lista.set(true, auxStack[i - 1]);
                                }
                            }
                        }

                        valor("FinalDown: ");
                        break;
                    case "up":
                        if (andar < pilha.top()) {
                            handlePush(andar);
                            System.out.println("chamouAndarMaiorSubindo");
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
                            System.out.println("chamouAndarMaiorSubindoDesempilhado");
                            lista.set(true, andar);
                            if (desempilhou) {
                                for (int i = count; i > 0; i--) {
                                    pilha.push(auxStack[i - 1]);
                                    System.out.println("chamouAndarMaiorSubindoempilhado");
                                    lista.set(true, auxStack[i - 1]);
                                }
                            }
                            mover();
                        }
                        valor("FinalUp: ");
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
