package project.logic;

import java.lang.Math;
public class Vector2D {
    private double x;
    private double y;
    private Vector2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public void setX(double x){
        this.x = x;
    }
    public void setY(double y){
        this.y = y;
    }
    public static Vector2D fromCoords(double x, double y){
        return new Vector2D(x,y);
    }
    public static Vector2D fromAngle(double angle, double len){
        return new Vector2D(Math.cos(angle) * len,Math.sin(angle) * len);
    }

    public static Vector2D nullVector(){
        return new Vector2D(0,0);
    }


    /*public Vector2D(double angle, int len){
        this.x = Math.cos(angle) * len;
        this.y = Math.sin(angle) * len;
    }*/
    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
    public Vector2D add(Vector2D other){
        return new Vector2D(x+other.x, y+other.y);
    }

    public double len(){
        return Math.sqrt(x*x+y*y);
    }

    public Vector2D sub(Vector2D other){
        return new Vector2D(x-other.x, y-other.y);
    }
    public double module(){
        return Math.sqrt(x*x + y*y);
    }
    public Vector2D mul(double a){
        return new Vector2D(x*a, y*a);
    }

    public Vector2D div(double a){
        return new Vector2D(x/a, y/a);
    }

    public Vector2D neg(){
        return new Vector2D(-x, -y);
    }

    public Vector2D clone(){
        return new Vector2D(x, y);
    }
    public double getAngle(){
        double angle = Math.atan(y/x);
        if(x<0) angle += Math.PI;
        return angle;
    }

    public void limit(double len){

        if(module()>=len){
            x = Math.cos(getAngle())*len;
            y = Math.sin(getAngle())*len;
        }
    }


    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            "}";
    }
    
}