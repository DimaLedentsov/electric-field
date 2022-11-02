package project.logic;

import java.util.Objects;

public class Particle{
    private Vector2D pos;
    private double Q;

    public Particle(Vector2D pos, double Q) {
        this.pos = pos;
        this.Q = Q;
    }


    public Particle() {
    }

    public Vector2D getPos() {
        return this.pos;
    }

    public void setPos(Vector2D pos) {
        this.pos = pos;
    }

    public double getQ() {
        return this.Q;
    }

    public void setQ(double Q) {
        this.Q = Q;
    }

    public Particle pos(Vector2D pos) {
        setPos(pos);
        return this;
    }

    public Particle Q(double Q) {
        setQ(Q);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Particle)) {
            return false;
        }
        Particle particle = (Particle) o;
        return Objects.equals(pos, particle.pos) && Q == particle.Q;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pos, Q);
    }

    @Override
    public String toString() {
        return "{" +
            " pos='" + getPos() + "'" +
            ", Q='" + getQ() + "'" +
            "}";
    }
}
