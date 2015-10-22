package Pulidora;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Transicion extends javax.swing.JFrame {
    
    private int pulidora[];
    private int obst[];
    private int fin[];
    private int fila,columna,nObst,posPulidora[],posFinal[], tamboton;
    private boolean casilla[][], posObst[][];
    private boolean rand;
    Tablero tr;
    Final fn;

    public Transicion(int a[], boolean b[][], int c[], int d, int f, int l, boolean r, boolean ca[][]) {
        super("Transicion");
        fila = d;
        columna = f;
        nObst = l;
        rand = r;
        posPulidora = new int[2];
        posFinal = new int[2];
        pulidora = new int[2];
        obst = new int[nObst*2];
        fin = new int[2];
        posObst = new boolean[fila][columna];
        casilla = new boolean[fila][columna];
        for(int q=0; q<fila; q++)
            for(int w=0; w<columna; w++)
                casilla[q][w] = ca[q][w];
        for(int i=0; i<fila; i++)
            for(int j=0; j<columna; j++)
                posObst[i][j] = b[i][j];
        posPulidora[0] = a[0];
        posPulidora[1] = a[1];
        for(int i=0; i<fila; i+=2)
            for(int j=0; j<columna; j++)
                posObst[i][j] = b[i][j];
        posFinal[0] = c[0];
        posFinal[1] = c[1];
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        initComponents();
    }

    private Transicion() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("¿Desea seguir adelante con estos parámetros?");

        jLabel2.setText("Posicion Pulidora:");

        jLabel4.setText("Pos. Final:");

        jLabel5.setText(String.valueOf(posPulidora[0]) + "," + String.valueOf(posPulidora[1]));

        jLabel7.setText(String.valueOf(posFinal[0]) + "," + String.valueOf(posFinal[1])
        );

        jButton1.setText("Si");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("No");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel5)))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel7)))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(jButton1)
                        .addGap(60, 60, 60)
                        .addComponent(jButton3)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if(evt.getSource()==jButton3){
            tr = new Tablero(fila,columna,nObst,rand);
            this.dispose();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if(evt.getSource()==jButton1){
            try {
                fn = new Final(fila,columna,nObst,rand,posPulidora,posObst,posFinal,casilla);
            } catch (InterruptedException ex) {
                Logger.getLogger(Transicion.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.dispose();
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    // End of variables declaration//GEN-END:variables

}
