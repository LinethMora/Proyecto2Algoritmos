/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template archivo, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

/**
 *
 * @author Fam. Alvarez Mora
 */

import Domain.ArbolAVL;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaPrincipal extends JFrame implements ActionListener {

   
    private JButton jbtAbrir;
    private JScrollPane scrollPane;
    private PintarArbol jPanelPintar;
    private ArbolAVL arbol;
    private JFileChooser fileChooser;

    public VentanaPrincipal() {

        super("Proyecto Lineth Mora- Catherine Orozco");
        Dimension dim= super.getToolkit().getScreenSize();
        super.setSize(dim);
        this.setResizable(false);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        iniciar();
    }//construtor

    private void iniciar() {
        arbol = new ArbolAVL();
        this.jbtAbrir = new JButton("Abrir archivo");
        this.jbtAbrir.setBounds(900, 400, 120, 40);
        this.jbtAbrir.addActionListener(this);
        this.add(this.jbtAbrir);
    }//iniciar

    public void pintar() {
        this.jPanelPintar = new PintarArbol(this.arbol);
        this.scrollPane = new JScrollPane();
        this.scrollPane.setBounds(new Rectangle(0, 0, 2000, 2000));
        this.scrollPane.setViewportView(this.jPanelPintar);
        this.scrollPane.getViewport().setView(this.jPanelPintar);
        this.jPanelPintar.setPreferredSize(new Dimension(4500, 4500));
        this.jPanelPintar.repaint();
        this.jPanelPintar.revalidate();
        SwingUtilities.updateComponentTreeUI(this);
        this.add(this.scrollPane);
    }//pintar

    public void crear(String mensaje) {
        String[] crearArreglo = mensaje.split("[ \n]");
        for (int i = 0; i < crearArreglo.length; i++) {
            this.arbol.insertar(crearArreglo[i]);
            this.jbtAbrir.setVisible(false);
        }//for i
    }//crearYpintarArbol

    
    public String abrirArchivo() throws IOException {
        String s = "";
        fileChooser = new JFileChooser();
        String ruta;
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            FileReader fileReader = null;
            try {
                ruta = fileChooser.getSelectedFile().getAbsolutePath();
                File archivo = new File(ruta);
                fileReader = new FileReader(archivo);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String linea;
                while ((linea = bufferedReader.readLine()) != null) {
                    s += linea + "\n";
                }//while
            } catch (FileNotFoundException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }//try-catch//try-catch//try-catch//try-catch
        } else {
    }
        return s;
    }//abrirArchivo

    
    public String abrirRuta() throws IOException {
        fileChooser = new JFileChooser();
        String ruta = "";
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            ruta += fileChooser.getSelectedFile().getAbsolutePath();
        } else {
    }
        return ruta;
    }//abrirRuta

    
    private void guardarFileChooser() {
        String ruta;
        fileChooser = new JFileChooser();
        FileNameExtensionFilter extension = new FileNameExtensionFilter("txt", "TXT");
        fileChooser.setFileFilter(extension);
        try {
            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                ruta = fileChooser.getSelectedFile().getAbsolutePath();
            }//if
        } catch (Exception ex) {
        }//try-catch
    }//guardarFileChooser

    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.jbtAbrir) {
            try {
                crear(abrirArchivo());
                pintar();
            } catch (IOException ex) {
                Logger.getLogger(VentanaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//action
    
}
