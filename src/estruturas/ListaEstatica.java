
package estruturas;

public class ListaEstatica {

    private int inicio, fim, quantidade;
    private int lista[];

    public ListaEstatica(int tamanho) {
        lista = new int[tamanho];
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

    public void add(int elemento, int indice) {
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
            int aux = lista[indice];
            if (quantidade == 1) {
                inicio--;
            } else {
                for (int i = indice; i < fim; i++) {
                    lista[i] = lista[i + 1];
                }
            }
            fim--;
            quantidade--;
            return aux;
        }
        return -1;
    }

    public int set(int elemento, int indice) {
        if (!isEmpty() && indice >= 0 && indice < quantidade) {
            int aux = lista[indice];
            lista[indice] = elemento;
            return aux;
        }
        return -1;
    }

    public int get(int indice) {
        if (!isEmpty() && indice >= 0 && indice < quantidade) {
            return lista[indice];
        }
        return -1;
    }
}
