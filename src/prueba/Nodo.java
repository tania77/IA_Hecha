package prueba;


public class Nodo {
    private int x, y, G, F, H;
    private Nodo padre;
    
    public Nodo(int a, int b, int c, int d){
        x = a;
        y = b;
        recalcularG();
        recalcularH(c, d);
        recalcularF();
    }
    
    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public int getG(){
        return G;
    }
    
    public int getF(){
        return F;
    }
    
    public void setNodoPadre(Nodo nodoPadre){
        this.padre = nodoPadre;
        recalcularG();
    }
    
    private void recalcularG(){
        if(padre!=null){
            G = padre.G;
            G += 10;
            recalcularF();
        }
    }
    
    private void recalcularH(int a, int b){
        H = (Math.abs(x-a) + Math.abs(y-b))*10;
        recalcularF();
    }
    
    private void recalcularF(){
        F = G + H;
    }
    
    public Nodo getNodoPadre(){
        return padre;
    }
}
