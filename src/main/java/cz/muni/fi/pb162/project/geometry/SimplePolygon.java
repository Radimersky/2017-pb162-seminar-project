package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

/**
 * Class represents simple polygon
 *
 * @author Marek Radiměřský
 */
public abstract class SimplePolygon implements Polygon {

    /**
     * Contstructs simple polygon
     * @param vertices array of vertices
     */
    public SimplePolygon(Vertex2D[] vertices) {
        if (vertices == null) {
            throw new IllegalArgumentException("Parameter vertices is null");
        }


        for (int i=0; i<=vertices.length-1; i++) {
            if (vertices[i] == null) {
                throw new IllegalArgumentException("Parameter vertices is null");
            }
        }
    }

    /**
     * Returns height of polygon
     * @return height of polygon
     */
    public double getHeight() {
        Vertex2D[] vertices = getVertices().toArray(new Vertex2D[0]);

        return SimpleMath.maxY(vertices).getY() - SimpleMath.minY(vertices).getY();
    }

    /**
     * Returns width of polygon
     * @return width of polygon
     */

    public double getWidth() {
        Vertex2D[] vertices = getVertices().toArray(new Vertex2D[0]);

        return SimpleMath.maxX(vertices).getX() - SimpleMath.minX(vertices).getX();
    }

    /**
     * Returns string information
     * @return string information
     */
    @Override
    public String toString() {
        StringBuilder result =  new StringBuilder("Polygon: vertices =");
        for (int x = 0; x < this.getNumVertices(); x++) {
            result.append(" " +getVertex(x));
        }

        return  result.toString();
    }

}
