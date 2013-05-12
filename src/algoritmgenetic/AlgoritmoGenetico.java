/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package algoritmgenetic;

/**
 *
 * @author Lea
 */
import java.util.*;

public class AlgoritmoGenetico {
    private Individuo[] poblacion=new Individuo[10] ;
    private Individuo objetivo;

    /**
     * @return the poblacion
     */
    public Individuo[] getPoblacion() {
        return poblacion;
    }

    /**
     * @param poblacion the poblacion to set
     */
    public void setPoblacion(Individuo[] pPoblacion) {
   
        this.poblacion = pPoblacion;
    }

/**
 * Genera Aleatoriamente la poblacion inicial
 *
 */
public void generaPoblacionInicial(){
    java.util.Random rObj= new Random();
    for(int i = 0; i<this.getPoblacion().length;i++){
        Individuo in = new Individuo();
        this.poblacion[i]=in;
        for(int j = 0 ; j<this.poblacion[i].getCromosomas().length; j++){
            if(rObj.nextDouble()<0.5)
                this.poblacion[i].cromosomas[j]=0;
            else
                this.poblacion[i].cromosomas[j]=1;
        }
    }
}

    /**
     * @return the objetivo
     */
    public Individuo getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(Individuo objetivo) {
        this.objetivo = objetivo;
    }

    public double calculaAdaptacion(Individuo pInd){
        double aux=0;
        for(int i =0;i<pInd.cromosomas.length;i++){
            if(pInd.cromosomas[i] == objetivo.cromosomas[i])
                aux+=1;
        }
        return aux * Math.pow(objetivo.cromosomas.length, -1);
    }
    /**
     * realiza el proceso de seleccion
     * @return una nueva poblacion
     */
    public Individuo[] seleccion(){
      
        Individuo aux;
        for(int i=0;i<poblacion.length;i++){
           for(int j=0;j<poblacion.length-1;j++){
               if(calculaAdaptacion(poblacion[j])<calculaAdaptacion(poblacion[j+1])){
                 aux=poblacion[j];
                 poblacion[j]=poblacion[j+1];
                 poblacion[j+1]=aux;
               }
           }
        }
        Individuo aux1[]=new Individuo[2];
        Individuo nPoblacion[]=new Individuo[poblacion.length];
        for(int i=0;i<poblacion.length;i+=2){
            aux1=cruce(poblacion[i],poblacion[i]);
            nPoblacion[i]=aux1[0];
            nPoblacion[i+1]=aux1[1];
        }
        
        return nPoblacion;

    }
    /**
     * Realiza el proceso de cruce entre dos individuos
     * @param pInd1
     * @param pInd2
     * @return
     */
    public Individuo[] cruce(Individuo pInd1,Individuo pInd2){
        Individuo[] aux1=new Individuo[2];
        aux1[0]=new Individuo();
        aux1[1]=new Individuo();

        Random oRan = new Random();
        int desde = oRan.nextInt(pInd1.cromosomas.length);
        for (int i=0;i<pInd1.cromosomas.length;i++){
            if(i<desde){
                aux1[0].cromosomas[i]=pInd1.cromosomas[i];
                aux1[1].cromosomas[i]=pInd2.cromosomas[i];
            }
            else{
                aux1[0].cromosomas[i]=pInd2.cromosomas[i];
                aux1[1].cromosomas[i]=pInd1.cromosomas[i];
            }
        }
        return aux1;
    }

    /**
     * Muta un cromosoma de un idividuo aleatorio de la poblacion
     */

    public void mutar(){
        Random oRan=new Random();
        int ind,cro;
       //ind=oRan.nextInt(poblacion.length);
        //cro=(int)oRan.nextDouble()*objetivo.cromosomas.length;
        //for(int i=0;i<ind;i++){
            int indi=oRan.nextInt(poblacion.length);
            cro=oRan.nextInt(objetivo.cromosomas.length);
            if (poblacion[indi].cromosomas[cro]==1)
                poblacion[indi].cromosomas[cro]=0;
            else
                poblacion[indi].cromosomas[cro]=1;
        //}
    }

}
