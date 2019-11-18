/**
 *
 * @author HectorGG
 */
package clustering;

import clasificadores.herramientasclasificadores.Patron;
import clasificadores.herramientasclasificadores.PatronRepresentativo;

import java.awt.*;
import java.util.ArrayList;

public class ImagenesMain {
    public static ArrayList<PatronRepresentativo> arrayParse( PatronRepresentativo[] centroides) {
        ArrayList<PatronRepresentativo> aux = new ArrayList<>();
        for (int i = 0; i < centroides.length; i++) {
            aux.add(centroides[i]);
        }
        return aux;
    }

    public static void main(String[] args){
        Image io = ImageManager.openImage();
        JFrameImagen jFrameImage = new JFrameImagen(io);
        jFrameImage.setTitle("ORIGINAL");
        ArrayList<Patron> instancias = ImageAdapter.obtenerInstancias(io);
        CMeans cMeans = new CMeans(200);
        cMeans.entrenar(instancias);
        System.out.println("Entrenamiento Finalizado");
        cMeans.clasificar(instancias);
        System.out.println("Clasificacion finalizada");
        ArrayList<PatronRepresentativo> centroides = ImagenesMain.arrayParse(cMeans.getCentroides());
        System.out.println("Generando imagen");
        Image nueva = ImageAdapter.generarImagenClusterizada(centroides,instancias,new Dimension(io.getWidth(null),io.getHeight(null)));
        System.out.println("Imagen generada");
        JFrameImagen jFrameImageNuevo = new JFrameImagen(nueva);
        jFrameImageNuevo.setTitle("CLUSTERIZADA");
        System.out.println();
    }

}
