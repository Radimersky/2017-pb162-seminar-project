package cz.muni.fi.pb162.project.geometry;

/**
 * Creates vertexes, moves them, gives distance of 2 vertexes.
 *
 * @author Marek Radiměřský
 */
public class Vertex2D implements Comparable {
    private final double x;
    private final double y;

    /**
     * Constructs new vertex.
     * @param x value
     * @param y value
     */
    public Vertex2D(double x, double y) {
        this.x = x;
        this.y = y;
    }
    /**
     * Prints info about vertex.
     * @return vertex coordinates
     */
    @Override
    public String toString() {
        return ("[" + x + ", " + y + "]");
    }

    /**
     * Sums coordinate.
     * @return sum of coordinates
     */
    public double sumCoordinates() {
        return x + y;
    }

    /**
     * Moves vertex.
     * @param vertex to interact with
     * @return moved vertex as a new object
     */
    public Vertex2D move(Vertex2D vertex) {
       return new Vertex2D(this.getX() + vertex.getX(), y + vertex.getY()); //obe moznosti jsou funkcni
    }

    /**
     * Distance between 2 vertexes.
     * @param vertex to measure distance with
     * @return distance value
     */
    public double distance(Vertex2D vertex) {
        if (vertex == null) {
            return -1.0;
        } else {
            return Math.sqrt(Math.pow(x - vertex.getX(), 2) + Math.pow(y - vertex.getY(), 2));
        }
    }

    /**
     * x value.
     * @return x value
     */
    public double getX() {
        return x;
    }

    /**
     * y value.
     * @return y value
     */
    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vertex2D vertex2D = (Vertex2D) o;

        if (Double.compare(vertex2D.x, x) != 0) return false;
        return Double.compare(vertex2D.y, y) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    //Kdyz jsem v konstruktoru mel parametr Vertex2D, tak mi test "getSortedVertices" házel chybu "classcastoException"
    @Override
    public int compareTo(Object o1) {
        Vertex2D o = (Vertex2D) o1;
        int diffX = Double.compare(this.x, o.getX());

        if (diffX != 0) return diffX;

        return Double.compare(this.y, o.getY());
    }
}
