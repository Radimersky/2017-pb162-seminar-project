package cz.muni.fi.pb162.project.geometry;

/**
 * Represents general regular polygon.
 *
 * @author Radek Oslejsek
 */
public class GeneralRegularPolygon implements RegularPolygon, Colored {
    private Vertex2D center;
    private int numEdges;
    private double radius;
    private Color color = Color.BLACK;

    /**
     * Constructor of general regular polygon.
     * @param center vertex
     * @param numEdges number of edges
     * @param radius length
     */
    public GeneralRegularPolygon(Vertex2D center, int numEdges, double radius) {
        this.center = center;
        this.numEdges = numEdges;
        this.radius = radius;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public int getNumEdges() {
        return this.numEdges;
    }

    @Override
    public double getEdgeLength() {
        return 2 * radius * Math.sin(Math.PI / numEdges);
    }

    @Override
    public Vertex2D getCenter() {
        return this.center;
    }

    @Override
    public double getRadius() {
        return this.radius;
    }

    @Override
    public double getWidth() {
        return this.radius * 2;
    }

    @Override
    public double getHeight() {
        return this.radius * 2;
    }

    @Override
    public String toString(){
        //"<n>-gon: center=<center>, radius=<radius>, color=<color>"
        return getNumEdges() + "-gon: center=" + getCenter() + ", radius=" + getRadius() + ", color=" + getColor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GeneralRegularPolygon that = (GeneralRegularPolygon) o;

        if (Double.compare(this.numEdges, that.numEdges) != 0) return false;
        if (Double.compare(this.radius, that.radius) != 0) return false;
        if ((that.center == null && this.center != null) || (that.center != null && this.center == null)) return false;
        if (that.center != null && this.center != null) {
            if (!(center.equals(that.center))) return false;
        }
        return color == that.color;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        if (center != null) {
            result = Double.hashCode(center.getX());
            result = result * 31 + Double.hashCode(center.getY());
            result = 31 * result + numEdges;
        } else result = Double.hashCode(numEdges);
        temp = Double.doubleToLongBits(radius);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + color.hashCode();
        return result;
    }
}
