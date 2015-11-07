package Pulidora;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.swing.*;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Final extends JFrame implements ActionListener{
    
    private final static int ANCHO_VENTANA = 700;
    private final static int ALTO_VENTANA = 700;
    private int anchoVentana,altoVentana, fila, columna, posPulidora[], posFinal[], cntObst, count, nObst, velocidad, tamboton;
    private JLabel label[][];
    private boolean casilla[][], posObst[][], rojo[][], alea, encontrado, esquina[][];
    Random rnd = new Random();
    private ArrayList<Nodo> nodosAdyacentes, ListaAbierta, ListaCerrada, camino;
    private Nodo nodoady;
    private BufferedImage bfondo, bpulidora, bfin, bobst, bfd, bfi, bfa, bfb, bead, beai, bebd, bebi, beda, bedb, beia, beib;
    private ImageIcon fondo, pulidora, fin, pared, fd, fi, fa, fb, ead, eai, ebd, ebi, eda, edb, eia, eib;
 
    public Final(int a, int b, int c, boolean d, int pr[], boolean po[][], int pf[], boolean ca[][]) throws InterruptedException{
        super("Aeropuerto");
        rojo = new boolean[a][b];
        esquina = new boolean[a][b];
        fila = a;
        columna = b;
        alea = d;
        if((fila*columna)<=100)
            velocidad = 200;
        else if((fila*columna)<=400)
            velocidad = 100;
        else
            velocidad = 50;
        posObst = new boolean[fila][columna];
        posPulidora = new int[2];
        posFinal = new int[2];
        posPulidora[0] = pr[0];
        posPulidora[1] = pr[1];
        posFinal[0] = pf[0];
        posFinal[1] = pf[1];
        nObst = c;
        casilla = new boolean[fila][columna];
        for(int i=0; i<fila; i++)
            for(int j=0; j<columna; j++)
                casilla[i][j] = ca[i][j];
        for(int i=0; i<fila; i++)
            for(int j=0; j<columna; j++)
                posObst[i][j] = po[i][j];
        setLayout(new GridLayout(a,b,0,0));
        Redimensionar();
        imagenes();
        DefinirTablero();
        
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.aestrella();
    }
    
    public void imagenes(){
        bfondo = null;
        try {
            bfondo = ImageIO.read(new File("src/GUI/fondo.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        fondo = new ImageIcon(bfondo);
        Image imagen1 = fondo.getImage() ;  
        Image newimg1 = imagen1.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        fondo = new ImageIcon( newimg1 );
        
        bpulidora = null;
        try {
            bpulidora = ImageIO.read(new File("src/GUI/pulidora.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        pulidora = new ImageIcon(bpulidora);
        Image imagen2 = pulidora.getImage() ;  
        Image newimg2 = imagen2.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        pulidora = new ImageIcon( newimg2 );
        
        bfin = null;
        try {
            bfin = ImageIO.read(new File("src/GUI/final.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        fin = new ImageIcon(bfin);
        Image imagen3 = fin.getImage() ;  
        Image newimg3 = imagen3.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        fin = new ImageIcon( newimg3 );
        
        bobst = null;
        try {
            bobst = ImageIO.read(new File("src/GUI/banco.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        pared = new ImageIcon(bobst);
        Image imagen4 = pared.getImage() ;  
        Image newimg4 = imagen4.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        pared = new ImageIcon( newimg4 );
        
        bfd = null;
        try {
            bfd = ImageIO.read(new File("src/GUI/flecha1.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        fd = new ImageIcon(bfd);
        Image imagen5 = fd.getImage() ;  
        Image newimg5 = imagen5.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        fd = new ImageIcon( newimg5 );
        
        bfi = null;
        try {
            bfi = ImageIO.read(new File("src/GUI/flecha3.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        fi = new ImageIcon(bfi);
        Image imagen6 = fi.getImage() ;  
        Image newimg6 = imagen6.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        fi = new ImageIcon( newimg6 );
        
        bfa = null;
        try {
            bfa = ImageIO.read(new File("src/GUI/flecha4.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        fa = new ImageIcon(bfa);
        Image imagen7 = fa.getImage() ;  
        Image newimg7 = imagen7.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        fa = new ImageIcon( newimg7 );
        
        bfb = null;
        try {
            bfb = ImageIO.read(new File("src/GUI/flecha2.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        fb = new ImageIcon(bfb);
        Image imagen8 = fb.getImage() ;  
        Image newimg8 = imagen8.getScaledInstance(tamboton, tamboton,  java.awt.Image.SCALE_SMOOTH );
        fb = new ImageIcon( newimg8 );  
        
    }

    public void DefinirTablero(){    
        label = new JLabel[fila][columna];
        for(int x=0; x<fila; x++){
            for(int y=0; y<columna; y++){
                if(x==posPulidora[0] && y==posPulidora[1]){
                    label[x][y] = new JLabel();
                    label[x][y].setIcon(pulidora);
                }
                else if(x==posFinal[0] && y==posFinal[1]){
                    label[x][y] = new JLabel();
                    label[x][y].setIcon(fin);
                }
                else{
                    label[x][y] = new JLabel();
                    label[x][y].setIcon(fondo);
                }
                if(posObst[x][y]==true){
                    label[x][y] = new JLabel();
                    label[x][y].setIcon(pared);
                }
                add(label[x][y]);
            }
        }
    }
    
    public void Redimensionar(){
        double ancho, alto;
        ancho = fila;
        alto = columna;
        double resultado = (ancho/alto);
        if( resultado == 1){
            setSize(ANCHO_VENTANA, ALTO_VENTANA);
            tamboton = (ANCHO_VENTANA/fila);
        }
        else if(resultado > 1){
            anchoVentana = (int)(ANCHO_VENTANA/(ancho/alto));
            altoVentana = ALTO_VENTANA;
            setSize(anchoVentana, ALTO_VENTANA);
            tamboton = ((anchoVentana)/(columna));
        }
        else{
            altoVentana = (int)(ANCHO_VENTANA *(ancho/alto));
            anchoVentana =  ANCHO_VENTANA;
            setSize(ANCHO_VENTANA, altoVentana);
            tamboton = ((altoVentana)/(fila));
        }        
    }
    
    public void IncluirObjetos1(int a, int b, int c, int d, int f, int g){
        for(int x=0; x<fila; x++)
            for(int y=0; y<columna; y++){
                if(x==c && y==d && esquina[x][y]==false){
                    if(a>c && b==d)
                        label[x][y].setIcon(fb);
                    else if(a<c && b==d)
                        label[x][y].setIcon(fa);
                    else if(a==c && b<d)
                        label[x][y].setIcon(fi);
                    else if(a==c && b>d)
                        label[x][y].setIcon(fd);
                    rojo[x][y]=true;
                }
                if(x==a && y==b)
                    label[x][y].setIcon(pulidora);
                else if(x==posFinal[0] && y==posFinal[1] && rojo[x][y]!=true)
                    label[x][y].setIcon(fin);
                else if(x!=posFinal[0] && y!=posFinal[1] && x!=c && y!=d && x!=a && y!=b && rojo[x][y]!=true)
                    label[x][y].setIcon(fondo);
                if(posObst[x][y]==true)
                    label[x][y].setIcon(pared);
            }
    }
    
    public boolean validarPos(int x, int y){
        if((x<0) || (y<0) || (x>fila-1) || (y>columna-1)){
            return false;
        }
        else if(casilla[x][y]==true){
            return false;
        }
        else 
            return true; 
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

      public void aestrella() throws InterruptedException{
        ListaAbierta = new ArrayList<Nodo>();
        ListaCerrada = new ArrayList<Nodo>();
        Nodo entra = new Nodo(posPulidora[0],posPulidora[1], posFinal[0], posFinal[1]);
        Nodo nodoact = null;
        ListaAbierta.add(entra);
        boolean encontrado = false;
        while(!encontrado && !ListaAbierta.isEmpty()){
            
            Nodo aux1 = null;
            Nodo aux2 = null;
            Nodo aux3 = null;
            Nodo aux4 = null;
            
            nodoact = (Nodo) ListaAbierta.get(0);
            ListaAbierta.remove(0);
            ListaCerrada.add(nodoact);
            
            nodosAdyacentes = new ArrayList<Nodo>();
            
            if(validarPos(nodoact.getX()+1,nodoact.getY())==true){
                aux1 = new Nodo(nodoact.getX()+1,nodoact.getY(), posFinal[0], posFinal[1]);
                nodosAdyacentes.add(aux1);
            }
            if(validarPos(nodoact.getX(),nodoact.getY()+1)==true){
                aux2 = new Nodo(nodoact.getX(),nodoact.getY()+1, posFinal[0], posFinal[1]);
                nodosAdyacentes.add(aux2);
            }
            if(validarPos(nodoact.getX()-1,nodoact.getY())==true){
                aux3 = new Nodo(nodoact.getX()-1,nodoact.getY(), posFinal[0], posFinal[1]);
                nodosAdyacentes.add(aux3);
            }
            if(validarPos(nodoact.getX(),nodoact.getY()-1)==true){
                aux4 = new Nodo(nodoact.getX(),nodoact.getY()-1, posFinal[0], posFinal[1]);
                nodosAdyacentes.add(aux4);
            }
            while (!nodosAdyacentes.isEmpty() && !encontrado){
                nodoady = new Nodo(nodosAdyacentes.get(0).getX(), nodosAdyacentes.get(0).getY(), posFinal[0], posFinal[1]);
                nodosAdyacentes.remove(0);
                boolean contLC = false, contLA = false;
                for(int i=0; i<ListaCerrada.size(); i++)
                    if(ListaCerrada.get(i).getX()==nodoady.getX() && ListaCerrada.get(i).getY()==nodoady.getY())
                        contLC=true;
                for(int i=0; i<ListaAbierta.size(); i++)
                    if(ListaAbierta.get(i).getX()==nodoady.getX() && ListaAbierta.get(i).getY()==nodoady.getY())
                        contLA=true;
                if (!contLC)
                    if (!contLA){
                        nodoady.setNodoPadre(nodoact);
                        if(ListaAbierta.isEmpty())
                            ListaAbierta.add(nodoady);
                        else{
                            int cnt = 0;
                            while(cnt<ListaAbierta.size() && nodoady.getF() > ListaAbierta.get(cnt).getF())
                                cnt++;
                            ListaAbierta.add(cnt, nodoady);
                        }
                        if (posFinal[0] == nodoady.getX() && posFinal[1] == nodoady.getY()){
                            encontrado = true;
                        }
                    }
                    else{
                        int nuevoG = nodoact.getG();
                        nuevoG += 10;
                        if (nuevoG < nodoady.getG()){
                            ListaAbierta.remove(nodoady);
                            nodoady.setNodoPadre(nodoact);
                            int cnt = 0;
                            while(nodoady.getF() < ListaAbierta.get(cnt).getF())
                                cnt++;
                            ListaAbierta.add(cnt, nodoady);
                        }
                    }
            }
        }
        if (encontrado){
            camino = new ArrayList<Nodo>();
            Nodo nodoAuxiliar = nodoady;
            while (nodoAuxiliar != null){
                camino.add(0, nodoAuxiliar);
                nodoAuxiliar = nodoAuxiliar.getNodoPadre();
            }
            for(int i=0; i<camino.size(); i++){
                if(i==0)
                    this.IncluirObjetos1(camino.get(i).getX(), camino.get(i).getY(), camino.get(i).getX(), camino.get(i).getY(), 0, 0);
                else if (i==1)
                    this.IncluirObjetos1(camino.get(i).getX(), camino.get(i).getY(), camino.get(i-1).getX(), camino.get(i-1).getY(), 0, 0);
                else
                    this.IncluirObjetos1(camino.get(i).getX(), camino.get(i).getY(), camino.get(i-1).getX(), camino.get(i-1).getY(), camino.get(i-2).getX(), camino.get(i-2).getY());
                this.update(this.getGraphics());
                Thread.sleep(velocidad);
            }
        }
        else
            JOptionPane.showMessageDialog(null, "Imposible llegar");
    }

    }