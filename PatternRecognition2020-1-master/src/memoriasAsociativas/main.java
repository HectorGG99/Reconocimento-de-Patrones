/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriasAsociativas;

import clasificadores.herramientasclasificadores.Patron;
import java.util.ArrayList;

/**
 *
 * @author Hector GG
 */

public class main {
    public static void Lernmatrix() {
        int[][] x = new int[5][6];
        int[][] y = new int[3][3];
        x[0]= new int[]{1,0,1,0,1,0};
        x[1]= new int[]{1,1,0,0,1,1};
        x[2]= new int[]{1,0,1,1,0,2};
        x[3]= new int[]{0,1,0,1,1,0};
        x[4]= new int[]{0,0,1,0,1,2};
        y[0]= new int[]{1,0,0};
        y[1]= new int[]{0,1,0};
        y[2]= new int[]{0,0,1};


        LernMatrix lm = new LernMatrix(x,y,1);
        lm.calcularVectorMedio(x);
        lm.trasladarPatrones(x);
        lm.aprendizaje();
        lm.mostrarM();
        System.out.println("Efectividad: "+lm.recuperacion(x));
    }
    
    public static void cap(){
        ArrayList<Patron> conjFundamental = new ArrayList<>();
       conjFundamental.add(new Patron(new double[]{2.0,3.0,6.0},"uno"));
        conjFundamental.add(new Patron(new double[]{6.0,8.0,10.0},"dos"));
        conjFundamental.add(new Patron(new double[]{1.9,3.8,5.5},"uno"));
        conjFundamental.add(new Patron(new double[]{6.4,7.2,9.7},"dos"));
        CAP cap = new CAP(conjFundamental);
        cap.learning();
        cap.showMemory();
        System.out.println();
        cap.recovery(conjFundamental);
    }
    
    public static void main(String[] args) {
        main.cap();


    }
}
