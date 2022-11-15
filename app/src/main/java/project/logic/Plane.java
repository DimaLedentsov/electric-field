package project.logic;

import java.util.Objects;

public class Plane {
    private Vector2D startPoint;
    private Vector2D endPoint;
    private double density;
    private boolean negative;
    public Plane() {
    }

    public Plane(Vector2D startPoint, Vector2D endPoint, double density, boolean neg) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.density = density;
        negative = neg;
    }

    public int sideOfPoint(Vector2D p){
        Vector2D p3 = p.clone();
        Vector2D p1 = startPoint.clone();
        Vector2D p2 = endPoint.clone();
        double D = (p3.getX()-p1.getX())*(p2.getY()-p1.getY()) - (p3.getY()-p1.getY())*(p2.getX()-p1.getX());
        if(D==0) return 0;
        return (D>0? 1: -1);

    }
    public double distanceToPoint(Vector2D p){
        //https://ru.wikipedia.org/wiki/%D0%A0%D0%B0%D1%81%D1%81%D1%82%D0%BE%D1%8F%D0%BD%D0%B8%D0%B5_%D0%BE%D1%82_%D1%82%D0%BE%D1%87%D0%BA%D0%B8_%D0%B4%D0%BE_%D0%BF%D1%80%D1%8F%D0%BC%D0%BE%D0%B9_%D0%BD%D0%B0_%D0%BF%D0%BB%D0%BE%D1%81%D0%BA%D0%BE%D1%81%D1%82%D0%B8
        Vector2D p2 = endPoint;
        Vector2D p1 = startPoint;
        return Math.abs((p2.getY()-p1.getY())*p.getX()-(p2.getX()-p1.getX())*p.getY()+p2.getX()*p1.getY()-p2.getY()*p1.getX())
        /p2.distance(p1);

    }
    public Vector2D getDirection(){
        return endPoint.sub(startPoint).normalize();
    }
    public boolean isNegative(){
        return negative;
    }
    public Vector2D getStartPoint() {
        return this.startPoint;
    }

    public void setStartPoint(Vector2D startPoint) {
        this.startPoint = startPoint;
    }

    public Vector2D getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(Vector2D endPoint) {
        this.endPoint = endPoint;
    }

    public double getDensity() {
        return this.density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public Plane startPoint(Vector2D startPoint) {
        setStartPoint(startPoint);
        return this;
    }

    public Plane endPoint(Vector2D endPoint) {
        setEndPoint(endPoint);
        return this;
    }

    public Plane density(double density) {
        setDensity(density);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Plane)) {
            return false;
        }
        Plane plane = (Plane) o;
        return Objects.equals(startPoint, plane.startPoint) && Objects.equals(endPoint, plane.endPoint) && density == plane.density;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPoint, endPoint, density);
    }

    @Override
    public String toString() {
        return "{" +
            " startPoint='" + getStartPoint() + "'" +
            ", endPoint='" + getEndPoint() + "'" +
            ", density='" + getDensity() + "'" +
            "}";
    }

}
