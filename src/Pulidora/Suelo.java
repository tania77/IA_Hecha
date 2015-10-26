package Pulidora;

//import prueba.Transicion;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;


public class Suelo extends JFrame implements ActionListener{
        
    private final static int ANCHO_VENTANA = 650;
    private final static int ALTO_VENTANA = 650;
    private int anchoVentana,altoVentana, fila, columna, posPulidora[], posFinal[], count[][], nObst, cntObst, size;
    private JButton button[][];
    private boolean casilla[][], posObst[][], aleatorio;
    Random rnd = new Random();
   // private JButton continuar;
   // Transicion t;
    JPanel principal, principal1;
    private BufferedImage bfondo, bpulidora, bfin, bobst;
    private ImageIcon fondo, pulidora, fin, banco;
    
    public Suelo(int a, int b, int c, boolean d){
        super("Tablero Aeropuerto");
        principal = new JPanel();
        principal1 = new JPanel();
        fila = a;
        columna = b;
        aleatorio = d;
        if(aleatorio==true)
            nObst = c;
        posObst = new boolean[a][b];
        posFinal = new int[2];
        posPulidora = new int[2];
        cntObst = 0;
        count = new int[fila][columna];
        casilla = new boolean[a][b];
        principal.setLayout(new GridLayout(a,b));
        //principal1.setLayout(new FlowLayout(1,1));
        setSize(ANCHO_VENTANA+5,ALTO_VENTANA+75);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.Redimensionar();
        this.imagenes();
        this.DefinirSuelo();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
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
        Image newimg1 = imagen1.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH );
        fondo = new ImageIcon( newimg1 );
        
        bpulidora = null;
        try {
            bpulidora = ImageIO.read(new File("src/GUI/pulidora.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        pulidora = new ImageIcon(bpulidora);
        Image imagen2 = pulidora.getImage() ;  
        Image newimg2 = imagen2.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH );
        pulidora = new ImageIcon( newimg2 );
        
        bfin = null;
        try {
            bfin = ImageIO.read(new File("src/GUI/final.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        fin = new ImageIcon(bfin);
        Image imagen3 = fin.getImage() ;  
        Image newimg3 = imagen3.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH );
        fin = new ImageIcon( newimg3 );
        
        bobst = null;
        try {
            bobst = ImageIO.read(new File("src/GUI/banco.jpg"));
        } catch (IOException e) {
                System.out.println("nfre");
        }
        banco = new ImageIcon(bobst);
        Image imagen4 = banco.getImage() ;  
        Image newimg4 = imagen4.getScaledInstance(size, size,  java.awt.Image.SCALE_SMOOTH );
        banco = new ImageIcon( newimg4 );
    }
    
    public void DefinirSuelo(){  //
        button = new JButton[fila][columna];
        for(int x=0; x<fila; x++){
            for(int y=0; y<columna; y++){
                button[x][y] = new JButton();
                button[x][y].setIcon(fondo);
                button[x][y].addActionListener(this); 
                principal.add(button[x][y]);
            }
        }
        
       // continuar = new JButton("Continuar");
       // continuar.addActionListener(this);
        //add(continuar);
        if(aleatorio==true){
            for(int k=0; k<nObst; k++){
                int q,w,buf;
                buf = (int)(rnd.nextDouble() * (fila*columna));
                q = (buf%fila);
                w = ((buf-q)/columna);
                button[w][q].setIcon(banco);
                posObst[w][q] = true;
                cntObst++;
                casilla[w][q]=true;
            }
        }
    }
    public void Redimensionar(){
        double ancho, alto;
        ancho = fila;
        alto = columna;
        double resultado = (ancho/alto);
        if( resultado == 1){
            principal.setSize(ANCHO_VENTANA, ALTO_VENTANA);
            size = (ANCHO_VENTANA/fila);
        }
        else if(resultado > 1){
            anchoVentana = (int)(ANCHO_VENTANA/(ancho/alto));
            altoVentana = ALTO_VENTANA;
            principal.setSize(anchoVentana, ALTO_VENTANA);
            size = ((anchoVentana)/(columna));
        }
        else{
            altoVentana = (int)(ANCHO_VENTANA *(ancho/alto));
            anchoVentana =  ANCHO_VENTANA;
            principal.setSize(ANCHO_VENTANA, altoVentana);
            size = ((altoVentana)/(fila));
        }     
        add(principal);
        principal1.setSize(1, 1);
        add(principal1);
    }

    @Override
    public void actionPerformed(ActionEvent e){ //FUNCION EVENTO PARA DEFINIR PULIDORA, OBSTACULOS Y PUNTO FINAL
        for(int x=0; x<fila; x++){
            for(int y=0; y<columna; y++){
                if(button[x][y]==e.getSource()){
                    if(aleatorio==false)
                        if(count[x][y]==0){
                            button[x][y].setIcon(pulidora);
                            count[x][y]++;
                        }
                        else if(count[x][y]==1){
                            button[x][y].setIcon(banco);
                            count[x][y]++;
                            nObst++;
                        }
                        else if(count[x][y]==2){
                            button[x][y].setIcon(fin);
                            count[x][y]++;
                            nObst--;
                        }
                        else{
                            button[x][y].setIcon(fondo);
                            count[x][y]=0;
                        }
                    else if(aleatorio==true && casilla[x][y]==false)
                        if(count[x][y]==0){
                            button[x][y].setIcon(pulidora);
                            count[x][y]++;
                        }
                        else if(count[x][y]==1){
                            button[x][y].setIcon(fin);
                            count[x][y]++;
                        }
                        else if(count[x][y]==2){
                            button[x][y].setIcon(fondo);
                            count[x][y]=0;
                        }
                }
            }            
        }
      /*  if(continuar==e.getSource()){
            for(int i=0; i<fila; i++)
                for(int j=0; j<columna; j++){
                    if(aleatorio==false){
                        if(count[i][j]==1){
                            posPulidora[0] = i;
                            posPulidora[1] = j;
                            casilla[i][j]=true;
                        }
                        else if(count[i][j]==2){
                            posObst[i][j] = true;
                            casilla[i][j]=true;
                            cntObst++;
                        }
                        else if(count[i][j]==3){
                            posFinal[0] = i;
                            posFinal[1] = j;
                            //casilla[i][j]=true;
                        }
                    }
                    else if(aleatorio==true){
                        if(count[i][j]==1){
                            posPulidora[0] = i;
                            posPulidora[1] = j;
                            casilla[i][j]=true;
                        }
                        else if(count[i][j]==2){
                            posFinal[0] = i;
                            posFinal[1] = j;
                            //casilla[i][j]=true;
                        }
                    }
                }
            t = new Transicion(posPulidora,posObst,posFinal,fila,columna,nObst,aleatorio,casilla);
            this.dispose();
        }
    */
    }
}
