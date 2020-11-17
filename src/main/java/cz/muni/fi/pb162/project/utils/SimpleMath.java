package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

/**
 * Static class providing simple math.
 *
 * @author Marek Radimersky
 */
public class SimpleMath {

    /**
     * Gives lowest x vertex from given array of vertexes.
     * @param vertices array of vertexes
     * @return lowest x value
     */
    public static Vertex2D minX(Vertex2D[] vertices) {

        double minXValue = vertices[0].getX();
        Vertex2D minXVertex = vertices[0];

        for (Vertex2D vertex : vertices) {
            if (vertex.getX() < minXValue) {
                minXValue = vertex.getX();
                minXVertex = vertex;
            }
        }
        return minXVertex;
    }

    /**
     * Gives lowest y vertex from given array of vertexes.
     * @param vertices array of vertexes
     * @return lowest y value
     */
    public static Vertex2D minY(Vertex2D[] vertices) {
        double minYValue = vertices[0].getY();
        Vertex2D minYVertex = vertices[0];

        for (Vertex2D vertex : vertices) {
            if (vertex.getY() < minYValue) {
                minYValue = vertex.getY();
                minYVertex = vertex;
            }
        }
        return minYVertex;
    }

    /**
     * Gives highest x vertex from given array of vertexes.
     * @param vertices array of vertexes
     * @return highest x value
     */
    public static Vertex2D maxX(Vertex2D[] vertices) {
        double maxXValue = vertices[0].getX();
        Vertex2D maxXVertex = vertices[0];

        for (Vertex2D vertex : vertices) {
            if (vertex.getX() > maxXValue) {
                maxXValue = vertex.getX();
                maxXVertex = vertex;
            }
        }
        return maxXVertex;
    }

    /**
     * Gives highest y vertex from given array of vertexes.
     * @param vertices array of vertexes
     * @return highest y value
     */
    public static Vertex2D maxY(Vertex2D[] vertices) {
        double maxYValue = vertices[0].getY();
        Vertex2D maxYVertex = vertices[0];

        for (Vertex2D vertex : vertices) {
            if (vertex.getY() > maxYValue) {
                maxYValue = vertex.getY();
                maxYVertex = vertex;
            }
        }
        return maxYVertex;
    }
}
