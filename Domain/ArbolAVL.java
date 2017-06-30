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
public class ArbolAVL {

    public NodoAVL raiz;
    private int posicion;
    
    public ArbolAVL() {  
        this.raiz = null;
        this.posicion = 0;
           
    }//const

    public int getNodo(NodoAVL x) {   
        if (x == null) {
            return -1;
        } else {
            return x.posicion;
        }
    }//getBF

    public NodoAVL rotacionIzquierda(NodoAVL nodo) {
        
        NodoAVL aux = nodo.hijoDerecho;
        nodo.hijoDerecho = aux.hijoIzquierdo;
        aux.hijoIzquierdo = nodo;
        nodo.posicion = Math.max(getNodo(nodo.hijoIzquierdo), getNodo(nodo.hijoDerecho)) + 1;
        aux.posicion = Math.max(getNodo(aux.hijoIzquierdo), getNodo(aux.hijoDerecho));

        return aux;
    }// rotacionIzquierda

    public NodoAVL rotacionDerecha(NodoAVL nodo) {
        
        NodoAVL aux = nodo.hijoIzquierdo;
        nodo.hijoIzquierdo = aux.hijoDerecho;
        aux.hijoDerecho = nodo;

        nodo.posicion = Math.max(getNodo(nodo.hijoIzquierdo), getNodo(nodo.hijoDerecho));
        aux.posicion = Math.max(getNodo(aux.hijoIzquierdo), getNodo(aux.hijoDerecho));

        return aux;
    }//rotacionDerecha

   
    public NodoAVL rotacionIzquierdaDerecha(NodoAVL nodo) {
        
        NodoAVL temp;
        nodo.hijoIzquierdo = rotacionIzquierda(nodo.hijoIzquierdo);
        temp = rotacionDerecha(nodo);
        return temp;
    }// rotacionIzquierdaDerecha

    public NodoAVL rotacionDerechaIzquierda(NodoAVL nodo) {
        
        NodoAVL temp;
        nodo.hijoDerecho = rotacionDerecha(nodo.hijoDerecho);
        temp = rotacionIzquierda(nodo);

        return temp;

    }//rotacionderechaIzquierda

    public NodoAVL insertarAVL(NodoAVL actualNodo, NodoAVL subArbol) {
        NodoAVL nuevoPariente = subArbol;

        if (actualNodo.getConsecutivo().compareTo(subArbol.getConsecutivo()) < 0) {
            if (subArbol.hijoIzquierdo == null) {
                subArbol.hijoIzquierdo = actualNodo;
                subArbol.hijoIzquierdo.setPosicion(subArbol.hijoIzquierdo.getPosicion() + this.posicion + "-");
                this.posicion++;
            } else {
                subArbol.hijoIzquierdo = insertarAVL(actualNodo, subArbol.hijoIzquierdo);

                if ((getNodo(subArbol.hijoIzquierdo) - getNodo(subArbol.hijoDerecho) == 2)) {
                    if (actualNodo.getConsecutivo().compareTo(subArbol.hijoIzquierdo.getConsecutivo()) < 0) {
                        nuevoPariente = rotacionDerecha(subArbol);
                    } else {

                        nuevoPariente = rotacionIzquierdaDerecha(subArbol);

                    }
                }
            }
        } else if (actualNodo.getConsecutivo().compareTo(subArbol.getConsecutivo()) > 0) {
            if (subArbol.hijoDerecho == null) {
                subArbol.hijoDerecho = actualNodo;
                subArbol.hijoDerecho.setPosicion(subArbol.hijoDerecho.getPosicion() + this.posicion + "-");
                this.posicion++;
            } else {
                subArbol.hijoDerecho = insertarAVL(actualNodo, subArbol.hijoDerecho);

                if ((getNodo(subArbol.hijoIzquierdo) - getNodo(subArbol.hijoDerecho) == -2)) {
                    if (actualNodo.getConsecutivo().compareTo(subArbol.hijoDerecho.getConsecutivo()) > 0) {
                        nuevoPariente = rotacionIzquierda(subArbol);
                    } else {

                        nuevoPariente = rotacionDerechaIzquierda(subArbol);

                    }
                }
            }
        } else {
            subArbol.setPosicion(subArbol.getPosicion() + this.posicion + "-");
            this.posicion++;
        }
        //updata bf
        if ((subArbol.hijoIzquierdo == null) && (subArbol.hijoDerecho != null)) {
            subArbol.posicion = subArbol.hijoDerecho.posicion + 1;
        } else if ((subArbol.hijoDerecho == null) && (subArbol.hijoIzquierdo != null)) {
            subArbol.posicion = subArbol.hijoIzquierdo.posicion + 1;
        } else {
            subArbol.posicion = Math.max(getNodo(subArbol.hijoIzquierdo), getNodo(subArbol.hijoDerecho)) + 1;
        }

        return nuevoPariente;
    }//insertAVL

    public void insertar(String nodo) {
        NodoAVL nuevoNodo = new NodoAVL(nodo);
        if (this.raiz == null) {
            this.raiz = nuevoNodo;
            this.raiz.setPosicion(this.raiz.getPosicion() + this.posicion + "-");
            this.posicion++;
        } else {
            this.raiz = insertarAVL(nuevoNodo, raiz);

        }//else-if
    }//insertar
}