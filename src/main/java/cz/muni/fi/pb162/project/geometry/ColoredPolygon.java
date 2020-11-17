package cz.muni.fi.pb162.project.geometry;

/**
 * Represents colored polygon.
 *
 * @author Marek Radiměřský
 */
public class ColoredPolygon {

    private Polygon polygon;
    private Color color;

    /**
     * Contstructs colored polygon.
     * @param polygon to construct
     * @param color to use
     */
    public ColoredPolygon(Polygon polygon, Color color) {
        this.polygon = polygon;
        this.color = color;
    }

    public Polygon getPolygon() {
        return polygon;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ColoredPolygon)) return false;

        ColoredPolygon that = (ColoredPolygon) o;

        if ((this.getPolygon() == null && that.getPolygon() != null)
                || this.getPolygon() != null && that.getPolygon() == null) return false;

        if (this.getPolygon() == null && that.getPolygon() == null) return true;
        return polygon.equals(that.polygon);
    }

    @Override
    public int hashCode() {
        return polygon != null ? polygon.hashCode() : 0;
    }
}
