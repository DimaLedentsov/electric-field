package project.logic;

import java.lang.Math;
import java.util.Objects;
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

    public Vector2D rotate(double angle){
        return Vector2D.fromCoords(Math.cos(angle)*x-Math.sin(angle)*y, Math.sin(angle)*x+Math.cos(angle)*y);
    }

    public void limit(double len){

        if(module()>=len){
            x = Math.cos(getAngle())*len;
            y = Math.sin(getAngle())*len;
        }
    }

    public Vector2D normalize(){
        return div(len());
    }
    public double angleBetween(Vector2D v){
    
        return Math.atan2(v.getY(),v.getX()) - Math.atan2(y,x);
    }
    public Vector2D unit(double len){
        return Vector2D.fromCoords(Math.cos(getAngle())*len,Math.sin(getAngle())*len);
    }

    public double distance(Vector2D v){
        return Math.sqrt(((v.getY() - y) * (v.getY() - y) + (v.getX() - x) * (v.getX() - x)));
    }
    public double distanceSquared(Vector2D v){
        return distance(v)*distance(v);
    }

    public double crossScalar(Vector2D v){
        return len()*v.len()*Math.cos(angleBetween(v));
    }
    @Override
    public String toString() {
        return "{" +
            " x='" + getX() + "'" +
            ", y='" + getY() + "'" +
            "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Vector2D)) {
            return false;
        }
        Vector2D vector2D = (Vector2D) o;
        return x == vector2D.x && y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    
}
