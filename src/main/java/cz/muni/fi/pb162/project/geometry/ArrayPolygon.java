package cz.muni.fi.pb162.project.geometry;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Class representing array polygon.
 *
 * @author Marek Radiměřský
 */
public class ArrayPolygon extends SimplePolygon implements Inverter {

    private Vertex2D[] vertices;

    /**
     * Constructor of array polygon
     * @param vertices to construct
     */
    public ArrayPolygon(Vertex2D[] vertices) {
        super(vertices);

        this.vertices = Arrays.copyOf(vertices, vertices.length);
    }

    @Override
    public Vertex2D getVertex(int index) throws IllegalArgumentException {
        if (index < 0) {
            throw new IllegalArgumentException("index is out of range");
        }
        return vertices[index % getNumVertices()];
    }

    @Override
    public int getNumVertices() {
        return vertices.length;
    }

    @Override
    public Collection<Vertex2D> getVertices() {
        return Arrays.asList(this.vertices);
    }

    @Override
    public Polygon invert() {
        List<Vertex2D> verticesList = Arrays.asList(vertices.clone());
        Collections.reverse(verticesList);

        return new ArrayPolygon(verticesList.toArray(new Vertex2D[0]));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ArrayPolygon that = (ArrayPolygon) o;
        if (this.vertices.equals(that.vertices)) return true;
        if (this.vertices.equals(that.invert())) return true;
        if (compareWithShiftedArray(vertices, Arrays.asList(that.vertices))) return true;
        return (compareWithShiftedArray(vertices, that.invert().getVertices()));
    }

    private boolean compareWithShiftedArray(Vertex2D[] array1, Collection<Vertex2D> array2Collection) {
        int indexOfFirstEqualVertex = 0;
        boolean isTrue = true;
        ArrayPolygon array2Polygon = new ArrayPolygon(array2Collection.toArray(new Vertex2D[0]));

        //Searches for index of array2 vertex that matches array1 vertex
        for (int x = 0; x < array1.length; x++) {
            if (array1[0].equals(array2Polygon.getVertex(x))) {
                indexOfFirstEqualVertex = x;
            }
        }
        //Compares array values
        for (int x = 1; x < array1.length; x++) {
            if (!array1[x].equals(array2Polygon.getVertex(indexOfFirstEqualVertex + x))) {
                isTrue = false;
            }
        }
        return isTrue;
    }

        @Override
        public int hashCode () {
        int result = 0;
            for (int z = 0; z < vertices.length; z++) {
                result = result + Double.hashCode(z);

                //result +=z.hashCode();
            }
        return result;
        }

}
