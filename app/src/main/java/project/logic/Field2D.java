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


   
    public Field2D(int w, int h){
        width = w;
        height = h;
        grid = new Vector2D[h][w];
        Random rand = new Random();
        particles = new LinkedList<>();
        
        particles.add(new Particle(Vector2D.fromCoords(100, 100), 5));
        particles.add(new Particle(Vector2D.fromCoords(200, 200), -5));


        
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

    public List<Particle> getParticles(){
        return particles;
    }

    public void update(){

    }
}
