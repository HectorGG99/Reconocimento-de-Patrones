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
import clasificadores.herramientasclasificadores.Patron;
import java.util.ArrayList;

public interface memoria {
    void learning();
    void recovery(ArrayList<Patron> patrones);
}