/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

/**
 *
 * @author Nicolas
 */
public class Elevador {

    private ListaEstatica lista;
    private Pilha pilha;
    private int andarInicio, andaresCount, andarAtual;
    private String direcao;

    public Elevador(int andarInicio, int andaresCount) {

        this.andarInicio = andarInicio;
        this.andarAtual = 2;
        this.andaresCount = andaresCount;
        this.lista = new ListaEstatica(andaresCount);
        this.pilha = new Pilha(andaresCount);
        int auxInit = andarInicio;
        for (int i = 0; i < andaresCount; i++) {
            this.lista.add(auxInit, i);
            auxInit++;
        }
        this.direcao = "up";
    }

    public void valor(String key) {
        System.out.println(key + pilha.toString());
    }

    public void chamar(int andar) {

        if (pilha.isEmpty()) {
            pilha.push(andar);
        } else {
            switch (direcao) {
                case "up":
                    if (andar > andarAtual) {
                        if (andar < pilha.top()) {
                            pilha.push(andar);
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
                            if (desempilhou) {
                                for (int i = count; i > 0; i--) {
                                    pilha.push(auxStack[i - 1]);
                                }
                            }
                        }
                    } else {
//                        int[] auxStack = new int[pilha.size()];
//                        int count = 0;
//                        while (andar < pilha.top() && !pilha.isEmpty()) {
//                            auxStack[count] = pilha.pop();
//                            count++;
//                        }
//                        valor("meio ");
//                        pilha.push(andar);
//                        valor("depois meio ");
//                        for (int i = 0; i < auxStack.length - 1; i++) {
//                            pilha.push(auxStack[i]);
//                        }
//                        valor("final4 ");
                    }
                    valor("finalfINAL ");
                    break;
            }
        }
    }

}
