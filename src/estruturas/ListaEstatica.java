
package estruturas;

public class ListaEstatica {

    private int inicio, fim, quantidade;
    private boolean lista[];

    public ListaEstatica(int tamanho) {
        lista = new boolean[tamanho];
        inicio = fim = -1;
        quantidade = 0;
    }

    public int size() {
        return quantidade;
    }

    public boolean isEmpty() {
        //return inicio == -1;
        return quantidade == 0;
    }

    public boolean isFull() {
        //return fim == lista.length - 1;
        return quantidade == lista.length;
    }

    public void add(boolean elemento, int indice) {
        if (!isFull() && indice >= 0 && indice <= quantidade) {
            if (quantidade == 0) {
                inicio = 0;
            } else {
                for (int i = fim; i >= indice; i--) {
                    lista[i + 1] = lista[i];
                }
            }
            lista[indice] = elemento;
            quantidade++;
            fim++;
        }
    }

    public int remove(int indice) {
        if (!isEmpty() && indice >= 0 && indice < quantidade) {
            boolean aux = lista[indice];
            if (quantidade == 1) {
                inicio--;
            } else {
                for (int i = indice; i < fim; i++) {
                    lista[i] = lista[i + 1];
                }
            }
            fim--;
            quantidade--;
            return indice;
        }
        return -1;
    }

    public int set(boolean elemento, int indice) {
        if (!isEmpty() && indice >= 0 && indice < quantidade) {
            lista[indice] = elemento;
            return indice;
        }
        return -1;
    }

    public int get(int indice) {
        if (!isEmpty() && indice >= 0 && indice < quantidade) {
            return lista[indice]? 1 : 0;            
        }
        return -1;
    }
}
