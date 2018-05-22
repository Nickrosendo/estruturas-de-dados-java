package estruturas;

import java.util.Arrays;


public class Pilha {

    private int pilha[];
    private int topo;

    public Pilha(int tamanho) {
        pilha = new int[tamanho];
        topo = -1;
    }

    public Pilha() {
        pilha = new int[10];
        topo = -1;
    }

    public boolean isEmpty() {
//        if (topo == -1) {
//            return true;
//        } else {
//            return false;
//        }
        return topo == -1;
    }

    public boolean isFull() {
        return topo == pilha.length - 1;
    }

    public int size() {
        return topo + 1;
    }

    public void push(int elemento) {
        if (!isFull()) {
            topo++;
            pilha[topo] = elemento;
            //pilha[++topo] = elemento;
        } else {
            System.out.println("Está cheio!");
        }
    }

    public int pop() {
        if (!isEmpty()) {
            int retorno = pilha[topo];
            topo--;
            return retorno;
        }
        return -1;
    }
    
    public int top() {
        if (!isEmpty()) {
            return pilha[topo];
        }
        return -1;
    }
    
    @Override
    public String toString(){
        return Arrays.toString(this.pilha);
    }
            
}
