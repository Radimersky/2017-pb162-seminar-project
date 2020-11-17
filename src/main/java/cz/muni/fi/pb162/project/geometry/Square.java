package cz.muni.fi.pb162.project.geometry;

/**
 * Object representing a square.
 *
 * @author Marek Radiměřský
 */


public class Square extends GeneralRegularPolygon {

    /**
     * Constructor.
     * @param circumcircle to create square from
     */
    public Square(Circumcircle circumcircle) {
        super(circumcircle.getCenter(), 4, circumcircle.getRadius());
    }

    /**
     * Returns vertex representing apex of square.
     * @param index wanted apex
     * @return vertex of square apex
     */
    public Vertex2D getVertex(int index) {
        Vertex2D center = getCenter();
        double halfEdge = getEdgeLength() / 2;
        switch (index) {
            case 0: return new Vertex2D(center.getX() - halfEdge, center.getY() - halfEdge);
            case 1: return new Vertex2D(center.getX() + halfEdge, center.getY() - halfEdge);
            case 2: return new Vertex2D(center.getX() + halfEdge, center.getY() + halfEdge);
            case 3: return new Vertex2D(center.getX() - halfEdge, center.getY() + halfEdge);
            default: return null;
        }
    }

    /**
     * Gives string information of square.
     * @return string information of square
     */
    @Override
    public String toString() {
        return "Square: vertices=" + getVertex(0) + " " + getVertex(1) + " " + getVertex(2) + " " + getVertex(3);
        //"Square: vertices=[ax, ay] [bx, by] [cx, cy] [dx, dy]"
    }


}
