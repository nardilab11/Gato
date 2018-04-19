/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejemplotanque;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Estudiante
 * Jpanel dado que voy a pintar en el Canvas
 * ActionListener: Para poder ejecutar el escenario cada ciertos milisegundos
 */
public class Tablero extends JPanel implements ActionListener{
    private Timer timer ;
    private int x;
    private int y;
    private int xt;
    private int sx1;
    private int sy1;
    private int sx2;
    private int sy2;
    private int dx1;
    private int dy1;
    private int dx2;
    private int dy2;
    private int fx1;
    private int fy1;
    private int fx2;
    private int fy2;
    private int secuencia;
    private int secuenciaFondo;

    public Tablero(){
        //Lanza un evento de tipo ActionListener cada 25 Milisegundo
        //Se hace referencia a this porque la misma clase (Tablero) procesa el evento
        this.timer = new Timer(25, this);
        this.x = 0;
        this.y = 20;
        this.xt = 0;
        this.sx1 = 10;
        this.sy1 = 10;
        this.sx2 = 142;
        this.sy2 = 90;
        this.dx1 = 30;
        this.dy1 = 350;
        this.dx2 = 162;
        this.dy2 = 430;
        this.fx1 = 0;
        this.fy1 = 0;
        this.fx2 = 1024;
        this.fy2 = 512;
        this.secuencia = 0;
        this.secuenciaFondo = 0;
        //Registrar evento del Teclado
        setFocusable(true); //Debe estar en modo Focus para que puede detectar el evento
        addKeyListener(new EventosTeclado()); //Inner class que procesa los eventos del teclado
        this.timer.start(); //Inicio con el escenario
    }
        
    //Inner class Que captura los eventos del teclado
     private class EventosTeclado extends KeyAdapter {
        //Cuando se suelta una tecla
         @Override
        public void keyReleased(KeyEvent e) {
           int key = e.getKeyCode();
           if (key == KeyEvent.VK_SPACE) {
            System.out.println("VK_SPACE"); //Se  va usar posteriormente 
           }
        }
        //Cuando se presiona una tecla
        @Override
        public void keyPressed(KeyEvent e) {
           
        }
    }
    
    //Metodo donde se pintan los objetos 
     @Override
    public void paintComponent(Graphics g){
       super.paintComponent(g);   
       Image fondo = this.loadImage("fondo.png");
       g.drawImage(fondo, fx1, fy1, fx2, fy2, 0, 0, 1024, 512, this);
       
       Image tanque = this.loadImage("1.png");
       g.drawImage(tanque, this.xt, 230, this);
       
       g.drawRect(this.xt, 310, 182, 130);
       
       Image gatos = this.loadImage("cats.gif");
       g.drawImage(gatos, dx1, dy1, dx2, dy2, 132*this.secuencia, 0, 132*(this.secuencia)+132, 80, this);

       
    }

    //Metodo que se ejecuta cada vez que se lanza un ActionListener
    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Repaint");
        x++;
        xt++;
        if(secuencia == 5){
            secuencia = 0;
        }
        secuencia++;
        dx1++;
        dx2++;
        repaint();
        
    }
    
    public Image loadImage (String imageName){
        ImageIcon ii = new ImageIcon(imageName);
        Image image = ii.getImage();
        return image;
    }
}
