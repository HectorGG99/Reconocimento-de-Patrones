package memoriasAsociativas;
/**
 *
 * @author Hector GG
 */
import clasificadores.herramientasclasificadores.Patron;
import java.util.ArrayList;

public class CAP implements memoria {
    private ArrayList<Patron> x;   
    private ArrayList<String> y; 
    private double[][] memory;    

    public CAP(ArrayList<Patron> x) {
        this.x = x;
        y = new ArrayList<>();
        y.add(x.get(0).getClase());
        for (Patron i: this.x ) {
            if(!y.contains(i.getClase()))
                y.add(i.getClase());
        }
        this.memory = new double[y.size()][x.get(0).getVector().length];
    }

    @Override
    public void learning() {
        Patron centroide = calcularcentroide();
        ArrayList<Patron> xDesplazados = displacePatterns(centroide);
        for (Patron i: xDesplazados) {
            int iClase = this.y.indexOf(i.getClase());
            for (int j = 0; j < i.getVector().length; j++) {
                this.memory[iClase][j]+= this.memory[iClase][j] + i.getVector()[j];
            }
        }
    }

    @Override
    public void recovery(ArrayList<Patron> patrones) {
        Patron centroide = calcularcentroide(patrones);
        for (Patron patron : patrones) {
            patron.desplazar(centroide);
        }
        for (Patron patron : patrones) {
            int indiceClase = Integer.MIN_VALUE;
            Patron aux = new Patron(patron.getVector().length);
            for (int i = 0; i < memory.length; i++) {
                for (int j = 0; j < memory[i].length; j++) {
                    aux.getVector()[i] += memory[i][j] * patron.getVector()[j];
                }
                if (indiceClase< aux.getVector()[i])
                    indiceClase= i;
            }
            patron.setClaseResultante(y.get(indiceClase));
            System.out.println(patron.toString());
        }
    }

    private ArrayList<Patron> displacePatterns(Patron centroide) {
        ArrayList<Patron> aux = new ArrayList<>();
        //double[] vector  = new double[this.x.get(0).getVector().length];
        for (Patron i: this.x) {
            double[] vector  = new double[this.x.get(0).getVector().length];
            for (int j = 0; j < i.getVector().length ; j++) {
                vector[j] = i.getVector()[j] - centroide.getVector()[j];
            }
            aux.add(new Patron(vector,i.getClase()));
        }
        return aux;
    }
    private Patron calcularcentroide() {
        Patron aux = new Patron(this.x.get(0).getVector().length);
        for (int i = 0; i < this.x.get(0).getVector().length; i++) {
            for (int j = 0; j < this.x.size(); j++) {
                aux.getVector()[i] = aux.getVector()[i] + x.get(j).getVector()[i] ;
            }
            aux.getVector()[i]/= x.size();
        }
        return  aux;
    }

    private Patron calcularcentroide(ArrayList<Patron> patrons) {
        Patron aux = new Patron(patrons.get(0).getVector().length);
        for (int i = 0; i < patrons.get(0).getVector().length; i++) {
            for (int j = 0; j < patrons.size(); j++) {
                aux.getVector()[i] = aux.getVector()[i] + patrons.get(j).getVector()[i] ;
            }
            aux.getVector()[i]/= patrons.size();
        }
        return  aux;
    }
    public void showMemory(){
        System.out.println("Memoria\n");
        for (int i = 0; i < this.memory.length; i++){
            for (int j = 0; j < this.memory[i].length; j++)
                System.out.print(String.format("%5.1f",memory[i][j]));
            System.out.println();
        }
    }
}
