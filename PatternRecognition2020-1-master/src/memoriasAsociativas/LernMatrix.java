/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package memoriasAsociativas;

/**
 *
 * @author Hector GG
 */
import clasificadores.herramientasclasificadores.Herramientas;

public class LernMatrix {
    private int[][] x;
    private int[][] y; 
    private int[][] m; 
    private int E; // epsilon

    public LernMatrix(int[][] x, int[][] y, int e) {
        this.x = x;
        this.y = y;
        this.E = e;
        m = new int[y.length][x[0].length -1];
        for (int i = 0; i < y.length ; i++) {
            for (int j = 0; j < x[i].length -1 ; j++) {
                m[i][j] = 0;
            }
        }
    }

    public void aprendizaje(){
        for (int i = 0; i < x.length; i++) {
            // Recorre patrones
            int clase= x[i][x[i].length -1];
            for (int j = 0; j < x[i].length -1; j++) {
                // Recorre caracteristicas de patrones
                if(x[i][j] == 1 && y[clase][clase] == 1){
                    m[clase][j]+=E;
                }
                if(x[i][j] == 0 && y[clase][clase] == 1){
                    m[clase][j]-=E;
                }
            }
        }
    }

    public double recuperacion(int[][] p){
        double efectividad = 0.0;
        int[][] trans = transpuesta(p,true);
        int[][] result = Herramientas.multiplicadorMatrices(this.m,trans);
        
        int[][] claseResult = transformarAPatron(result);
        for (int i = 0; i < y.length ; i++) {
            int aux = 0;
            for (int j = 0; j < y. length ; j++) {
                if (y[i][j] == claseResult[i][j]){
                    aux+=1;
                }
            }
            if (aux == y.length)
                efectividad+=1;
        }
        return  efectividad / y.length * 100;
    }

    public  int[][] transpuesta(int[][] p, boolean clase){
        int[][] trans;
        if(clase){
            trans =  new int[p[0].length-1][p.length];
            for (int i = 0; i < trans.length; i++) {
                for (int j = 0; j < trans[i].length; j++) {
                    trans[i][j] = p[j][i];
                }
            }
        }
        else {
            trans = new int[p[0].length][p.length];
            for (int i = 0; i < trans.length; i++) {
                for (int j = 0; j < trans[i].length; j++) {
                    trans[i][j] = p[j][i];

                }
            }
        }
        return trans;
    }

    public int[][] transformarAPatron(int[][] a){
        int[][] patron = transpuesta(a,false);
        for (int i = 0; i < patron.length ; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < patron[i].length; j++) {
                if (max < patron[i][j])
                    max = patron[i][j];
            }
            for (int j = 0; j < patron[i].length; j++) {
                if (max == patron[i][j])
                    patron[i][j] = 1;
                else
                    patron[i][j] = 0;
            }
        }

        return patron;
    }

    public int[][] calcularVectorMedio(int[][] a){
    double[] prom = null;
    for(int j=0; j<a.length;j++){
        for(int i =0;i < a.length;i++){
            prom[j] += a[i][j];
        }    
    }
    
    return a;
    }

    public int[][] trasladarPatrones(int[][] a){
    return a;
    }


    
    
    
    
    
    
    public void mostrarM(){
        for (int i = 0; i < m.length ; i++) {
            for (int j = 0; j < m[i].length; j++) {
                System.out.print(String.format("%3d",m[i][j]));
            }
            System.out.println();
        }
    }

    public int[][] getX() {
        return x;
    }

    public void setX(int[][] x) {
        this.x = x;
    }

    public int[][] getY() {
        return y;
    }

    public void setY(int[][] y) {
        this.y = y;
    }

    public int[][] getM() {
        return m;
    }

    public void setM(int[][] m) {
        this.m = m;
    }

    public int getE() {
        return E;
    }

    public void setE(int e) {
        E = e;
    }
}
