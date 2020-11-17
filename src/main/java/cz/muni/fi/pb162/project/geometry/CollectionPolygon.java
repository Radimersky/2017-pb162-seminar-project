package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.utils.SimpleMath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents collection polygon.
 *
 * @author Marek Radiměřský
 */
public class CollectionPolygon extends SimplePolygon implements Inverter {

    private List<Vertex2D> vertices;

    /**
     * Constructs cellection polygon.
     * @param vertices to create object from
     */
    public CollectionPolygon(Vertex2D[] vertices){
        super(vertices);

        if (vertices == null) {
            throw new IllegalArgumentException("Vertices is null");
        }

        this.vertices = new ArrayList<>(Arrays.asList(vertices));
    }

    /**
     * Returns vertex at given index modulo number of indices.
     *
     * @param index vertex index, not negative number
     * @return vertex at given index modulo number of indices
     * @throws IllegalArgumentException if index is negative
     */
    @Override
    public Vertex2D getVertex(int index) throws IllegalArgumentException {
        if (index < 0) {
            throw new IllegalArgumentException("index is negative.");
        }

        return vertices.get(index % getNumVertices());
    }

    /**
     * Returns number of vertices of the polygon.
     *
     * @return number of vertices
     */
    @Override
    public int getNumVertices() {
        return vertices.size();
    }

    /**
     * Returns unmodifiable collection of vertices.
     *
     * @return unmodifiable collection of vertices
     */
    @Override
    public Collection<Vertex2D> getVertices() {
        return Collections.unmodifiableList(vertices);
    }

    /**
     * Inverts the ordering of the vertices.
     * For example, if polygon has vertices A-B-C-D, it returns polygon with vertices D-C-B-A.
     *
     * @return inverted polygon
     */
    @Override
    public Polygon invert() {
        List<Vertex2D> verticesList = new ArrayList<>(getVertices());
        Collections.reverse(verticesList);

        return new CollectionPolygon(verticesList.toArray(new Vertex2D[0]));
    }

    /**
     * Removes leftmost vertices.
     * @return removed vertices
     */
    public Collection<Vertex2D> removeLeftmostVertices() {
        if (vertices.size() == 0) {
            return vertices;
        }

        List<Vertex2D> leftMostVertices = new ArrayList();
        double smallestX = SimpleMath.minX(vertices.toArray(new Vertex2D[0])).getX();

        leftMostVertices = vertices.stream()
                .filter(vertex -> Double.compare(vertex.getX(), smallestX) == 0)
                .collect(Collectors.toList());

        vertices.removeAll(leftMostVertices);
        return leftMostVertices;
    }
}
