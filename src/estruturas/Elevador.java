package estruturas;

import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author Nicolas
 */
public class Elevador {

    private final ListaEstatica lista;
    private final Pilha pilha;
    private final Timer timer = new Timer();
    private final ElevadorMain component;
    private int andarAtual;
    private String direcao;

    public Elevador(ElevadorMain component) {
        this.component = component;
        this.andarAtual = 0;
        this.lista = new ListaEstatica(12);
        this.pilha = new Pilha(12);
        for (int i = 0; i < 12; i++) {
            this.lista.add(false, i);
        }
        this.direcao = "up";
    }

    public boolean emMovimento() {
        return !pilha.isEmpty();
    }

    public boolean isPushed(int andar) {
        return lista.get(andar) == 1;
    }

    public void handleArrival(int andar) {
        switch (andar) {
            case 0:
                component.getBtnTerreo().setBackground(Color.white);
                break;
            case 1:
                component.getBtn1().setBackground(Color.white);
                break;
            case 2:
                component.getBtn2().setBackground(Color.white);
                break;
            case 3:
                component.getBtn3().setBackground(Color.white);
                break;
            case 4:
                component.getBtn4().setBackground(Color.white);
                break;
            case 5:
                component.getBtn5().setBackground(Color.white);
                break;
            case 6:
                component.getBtn6().setBackground(Color.white);
                break;
            case 7:
                component.getBtn7().setBackground(Color.white);
                break;
            case 8:
                component.getBtn8().setBackground(Color.white);
                break;
            case 9:
                component.getBtn9().setBackground(Color.white);
                break;
            case 10:
                component.getBtn10().setBackground(Color.white);
                break;
            case 11:
                component.getBtn11().setBackground(Color.white);
                break;
        }
    }

    public void mover() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (emMovimento()) {
                    if (andarAtual < pilha.top()) {
                        andarAtual++;
                        direcao = "up";
                        component.getLblDirection().setText("▲");
                    } else if (andarAtual > pilha.top()) {
                        andarAtual--;
                        direcao = "down";
                        component.getLblDirection().setText("▼");
                    }
                    if (andarAtual == 0) {
                        component.getLblAndarAtual().setText("T");
                    } else {
                        component.getLblAndarAtual().setText("" + andarAtual);
                    }
                    if (andarAtual == pilha.top()) {
                        lista.set(false, pilha.top());
                        handleArrival(pilha.pop());
                    }
                } else {
                    this.cancel();
                }
            }
        };
        timer.scheduleAtFixedRate(timerTask, 1000, 1000);
    }

    private void handlePush(int andar) {
        pilha.push(andar);
        lista.set(true, andar);
    }

    public void chamar(int andar) {
        if (andar != andarAtual && !isPushed(andar)) {
            if (pilha.isEmpty()) {
                handlePush(andar);
                mover();
            } else {
                switch (direcao) {
                    case "down":
                        if (andar > pilha.top() && andar < andarAtual) {
                            handlePush(andar);
                        } else if (andar > andarAtual) {
                            int[] auxStack = new int[pilha.size()];
                            int count = 0;
                            boolean desempilhou = false;
                            while (andar > pilha.top() && !pilha.isEmpty()) {
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
                        if (andar < pilha.top() && andar > andarAtual) {
                            handlePush(andar);
                        } else if (andar < andarAtual) {
                            int[] auxStack = new int[pilha.size()];
                            int count = 0;
                            boolean desempilhou = false;
                            while (andar < pilha.top() && !pilha.isEmpty()) {
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
