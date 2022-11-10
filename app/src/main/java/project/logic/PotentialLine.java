package project.logic;

import java.util.List;
import java.util.Objects;

import javafx.scene.paint.Color;

public class PotentialLine {
    private Color color;
    private Vector2D pos;
    private List<Vector2D> points;
    private double potential;
    public PotentialLine() {
    }

    public PotentialLine(Color color, Vector2D pos, double potential, List<Vector2D> points) {
        this.color = color;
        this.pos = pos;
        this.points = points;
        this.potential = potential;
    }

    public double getPotential(){
        return potential;
    }
    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Vector2D getPos() {
        return this.pos;
    }

    public void setPos(Vector2D pos) {
        this.pos = pos;
    }

    public List<Vector2D> getPoints() {
        return this.points;
    }

    public void setPoints(List<Vector2D> points) {
        this.points = points;
    }

    public PotentialLine color(Color color) {
        setColor(color);
        return this;
    }

    public PotentialLine pos(Vector2D pos) {
        setPos(pos);
        return this;
    }

    public PotentialLine points(List<Vector2D> points) {
        setPoints(points);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PotentialLine)) {
            return false;
        }
        PotentialLine potentialLine = (PotentialLine) o;
        return Objects.equals(color, potentialLine.color) && Objects.equals(pos, potentialLine.pos) && Objects.equals(points, potentialLine.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, pos, points);
    }

    @Override
    public String toString() {
        return "{" +
            " color='" + getColor() + "'" +
            ", pos='" + getPos() + "'" +
            ", points='" + getPoints() + "'" +
            "}";
    }

}
