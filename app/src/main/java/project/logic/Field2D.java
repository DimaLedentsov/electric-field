package project.logic;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedDeque;

import static java.lang.Math.*;
public class Field2D {
    private Vector2D[][] grid;
    private int gridWidth;
    private int gridHeight;

    private double width;
    private double height;

    private List<Particle> particles;

    private List<PotentialLine> lines;
    double t = 0;


   
    public Field2D(int w, int h){
        gridWidth = w;
        gridHeight = h;
        width = 200;
        height = 100;
        grid = new Vector2D[h][w];
        Random rand = new Random();
        particles = new LinkedList<>();
        
        lines = new LinkedList<PotentialLine>();
        lines = Collections.synchronizedList(lines);
                
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

    public int getGridHeight(){
        return gridHeight;
    }

    public int getGridWidth(){
        return gridWidth;
    }

    public double getWidth(){
        return width;
    }

    public double getHeight(){
        return height;
    }

    public List<Particle> getParticles(){
        return particles;
    }

    public List<PotentialLine> getLines(){
        return lines;
    }

    public boolean containsPoint(Vector2D point){
        return(Math.abs(point.getX())<getWidth()/2 || Math.abs(point.getY())<getHeight()/2);
    }

    public void update(){

    }
}
