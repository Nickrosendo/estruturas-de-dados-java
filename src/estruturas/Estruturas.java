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
public class Estruturas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Elevador teste = new Elevador(-1, 10);
        teste.chamar(0);
        teste.chamar(5);
        teste.chamar(1);
        teste.chamar(7);
        teste.chamar(6);
        teste.chamar(4);
        teste.chamar(3);
    }
    
}
