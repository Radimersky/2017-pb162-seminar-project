package cz.muni.fi.pb162.project.comparator;

import cz.muni.fi.pb162.project.geometry.Vertex2D;

import java.util.Comparator;

/**
 * Class representing vertex inverse comparator
 */
public class VertexInverseComparator implements Comparator<Vertex2D> {
    /**
     * Comparator.
     * @param o1 first object
     * @param o2 second object
     * @return compared objects
     */
    public int compare(Vertex2D o1, Vertex2D o2) {
        int diffX = Double.compare(o1.getX(), o2.getX());

        if (diffX != 0) return -diffX;

        return -o1.compareTo(o2);
    }
}
