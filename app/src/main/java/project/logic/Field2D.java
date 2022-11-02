package project.logic;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.*;
public class Field2D {
    private Vector2D[][] grid;
    private int width;
    private int height;

    private List<Particle> particles;
    double t = 0;


    final double e0 = 8.854187e-12;
    Vector2D E(Vector2D pos){
        Vector2D res = null;
        for (Particle p: particles){
            Vector2D r = pos.sub(p.getPos());
            double rLen = r.len();
            if(p.getQ()<0) return Vector2D.nullVector();
            Vector2D ur = r.div(rLen);
            res = res.add(ur.mul(p.getQ()/(rLen*rLen)));
        }
        return res.div(4*Math.PI*e0);
    }
    public Field2D(int w, int h){
        width = w;
        height = h;
        grid = new Vector2D[h][w];
        Random rand = new Random();
        particles = new LinkedList<>();
        
        particles.add(new Particle(Vector2D.fromCoords(50, 50), 5));
        for(int y=0; y<getHeight(); y++){
            for(int x=0; x<getWidth(); x++){
                int x_ = x-round(getWidth()/2);
                int y_ = y-round(getHeight()/2);
                Vector2D vec = E(Vector2D.fromCoords(x_, y_));//Vector2D.fromAngle(Math.toRadians(Math.random() * 360), 0.1);
                set(x, y, vec);
                System.out.println(vec.toString());
            }
        }
        
    }
    public Vector2D get(int x, int y){
        return grid[y][x];
    }
    public void set(int x, int y, Vector2D vec){
        grid[y][x]=vec;
    }

    public Vector2D[][] getGrid(){
        return grid;
    }

    public int getHeight(){
        return height;
    }

    public int getWidth(){
        return width;
    }

    public void update(){

    }
}
