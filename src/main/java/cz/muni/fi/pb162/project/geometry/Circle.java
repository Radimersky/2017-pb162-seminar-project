package cz.muni.fi.pb162.project.geometry;

/**
 * Creates object circle with given center and radius.
 *
 * @author Marek Radiměřský
 */
public class Circle extends GeneralRegularPolygon {

    /**
     * Constructs new vertex.
     * @param center vertex
     * @param radius radius
     */
    public Circle(Vertex2D center, double radius) {
        super(center, Integer.MAX_VALUE, radius);
        super.setColor(Color.RED);
    }

    /**
     * Constructs new default vertex.
     */
    public Circle() {
        this(new Vertex2D(0, 0), 1);
    }

    /**
     * Gets length of edge.
     * @return edge length
     */
    @Override
    public double getEdgeLength() {
        return 0;
    }

    /**
     * String information of the object.
     * @return object information
     */
    @Override
    public String toString() {
        return "Circle: " + "center=[" + getCenter().getX() + ", " + getCenter().getY() + "]," +
                " " + "radius=" + getRadius();
        //"Circle: center=[<x>, <y>], radius=<radius>"
    }
}
