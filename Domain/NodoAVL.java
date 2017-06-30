/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 *
 * @author Fam. Alvarez Mora
 */
public class NodoAVL {
    
    public NodoAVL hijoIzquierdo;
    public NodoAVL hijoDerecho;
    private String consecutivo;
    private String palabra;
    int posicion;

    public NodoAVL(String consecutivo) {
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.consecutivo = consecutivo;
        this.palabra = "";
        this.posicion = 0;
    }//constructor

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public String getPosicion() {
        return palabra;
    }

    public void setPosicion(String posicion) {
        this.palabra = posicion;
    }

}//class
