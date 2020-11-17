package cz.muni.fi.pb162.project.geometry;

/**
 * Represents regular octagon.
 *
 * @author Radek Oslejsek
 */
public class RegularOctagon extends GeneralRegularPolygon {

    /**
     * Constructor of regular octagon.
     * @param center vertex
     * @param radius length
     */
    public RegularOctagon(Vertex2D center, double radius) {
        super(center, 8, radius);
    }
}
