/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Fam. Alvarez Mora
 */
import Domain.NodoAVL;
import Domain.ArbolAVL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class PintarArbol extends JPanel {

    private ArbolAVL arbol;
    private NodoAVL nodo;
    private Graphics2D graphics2D;
    private BufferedImage bufferedImage;
    private int x;
    private int y;
    
    public PintarArbol(ArbolAVL arbol) {
        super();
        this.arbol = arbol;
        this.nodo = this.arbol.raiz;
        try {
            this.bufferedImage = ImageIO.read(getClass().getResourceAsStream("/assets/ph.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(PintarArbol.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.graphics2D = (Graphics2D) this.bufferedImage.getGraphics();
        
    }//constructor

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(bufferedImage, 0, 0, 4500, 4500, null);
        dibujar(g, nodo, 700, 20, 170, 5, 0);
    }//paintComponent

    private void dibujar(Graphics g, NodoAVL nodo, int x, int y, int xArista, int yArista, int nivelHijo) {
        if (nodo == null) {
            return;
        }
        g.setColor(Color.getHSBColor(95, 44, 72));
        if (nodo.hijoIzquierdo != null) {
            g.drawLine(x, y, x - xArista + (nivelHijo * 2) - 40, (y + yArista) + 100);
        }//leftSon
        if (nodo.hijoDerecho != null) {
            g.drawLine(x, y, x + xArista - (nivelHijo * 2) + 10, (y + yArista) + 100);
        }//RightSon
        g.fillRoundRect(x - 70, y - 15, 100,40, 40,40);
        g.setColor(Color.MAGENTA);
        String primerNodo = nodo.getPosicion();
        primerNodo = primerNodo.substring(0, primerNodo.length() - 1);
        g.drawString(nodo.getConsecutivo() + " { " + primerNodo + " } ", x - 50, y + 15);
        dibujar(g, nodo.hijoIzquierdo, (int) (x - xArista) + 20, (y + yArista) + 100, ((xArista+20)+ nivelHijo * 2)-20 , yArista+77, nivelHijo + 1);
        dibujar(g, nodo.hijoDerecho, (int) (x + xArista) - 20, (y + yArista) + 100, ((xArista+20) - nivelHijo * 2) - 20, yArista+15, nivelHijo + 1);
    }//pintarArbol

}//class