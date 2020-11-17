package cz.muni.fi.pb162.project.geometry;

import cz.muni.fi.pb162.project.exceptions.EmptyDrawableException;
import cz.muni.fi.pb162.project.exceptions.MissingVerticesException;
import cz.muni.fi.pb162.project.exceptions.TransparentColorException;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents a drawing paper.
 *
 * @author Marek Radiměřský
 */
public class Paper implements Drawable, PolygonFactory {

    private Set<ColoredPolygon> polygons;
    private Paper paper;
    private Color color = Color.BLACK;

    /**
     * Constructs empty paper.
     */
    public Paper() {
        this.polygons = new HashSet<>();
    }

    /**
     * Constructs paper with polygons.
     * @param paper to create object from
     */
    public Paper(Paper paper) {
        polygons = new HashSet<>(paper.getAllDrawnPolygons());
    }

    /**
     * Changes pencil color.
     *
     * @param color the color which the drawn objects are going to have
     */
    @Override
    public void changeColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the polygon on drawable thing.
     *
     * @param polygon polygon to be drawn
     */
    @Override
    public void drawPolygon(Polygon polygon) throws TransparentColorException {
        if (color.equals(Color.WHITE)) {
            throw new TransparentColorException("Drawing with white pen on white paper");
        }
        polygons.add(new ColoredPolygon(polygon, color));
    }

    /**
     * Erases the polygon.
     *
     * @param polygon polygon to be erased
     */
    @Override
    public void erasePolygon(ColoredPolygon polygon) {
        polygons.remove(polygon);
    }

    /**
     * Erases the whole paper. The color is not changed.
     */
    @Override
    public void eraseAll() throws EmptyDrawableException {
        if (this.polygons.size() == 0) {
            throw new EmptyDrawableException("Erasing empty paper");
        }
        polygons.removeAll(polygons);
    }

    /**
     * Returns unmodifiable collection of drawn polygons.
     *
     * @return unmodifiable collection of drawn polygons
     */
    @Override
    public Collection<ColoredPolygon> getAllDrawnPolygons() {
        return Collections.unmodifiableSet(polygons);
    }

    /**
     * Finds and returns polygons which contain input vertex.
     * If vertex is null, it returns empty collection.
     *
     * @param vertex vertex to be searched for
     * @return every polygon containing the vertex
     */
    @Override
    public Collection<ColoredPolygon> findPolygonsWithVertex(Vertex2D vertex) {
        Set<ColoredPolygon> matchedPolygons = new HashSet<>();

        for (ColoredPolygon polygon : polygons) {
            Polygon pol = polygon.getPolygon();
            List<Vertex2D> vertices = new ArrayList<>(pol.getVertices());
            for (Vertex2D vertexOfPolygon : vertices) {
                if (vertexOfPolygon.equals(vertex)) {
                    matchedPolygons.add(polygon);
                    break;
                }
            }
        }
        return matchedPolygons;
    }

    /**
     * Creates polygon from the collection.
     * @param vertices collection which the polygon can be built from
     * @returncreated polygon
     * @throws MissingVerticesException if there is not enough not null vertices in the array
     */
    @Override
    public Polygon tryToCreatePolygon(List<Vertex2D> vertices) throws MissingVerticesException {
        if (vertices == null) {
            throw new NullPointerException("Parameter is null");
        }

        if (vertices.size() < 3) {
            throw new MissingVerticesException("Not enough vertices");
        }

        List<Vertex2D> verticesCopy = new ArrayList<>(vertices);
        CollectionPolygon verticesPolygon = new CollectionPolygon(new Vertex2D[0]);
        boolean polygonCreated = false;

        while (!polygonCreated) {
            try {
                verticesPolygon = new CollectionPolygon(verticesCopy.toArray(new Vertex2D[0]));
                polygonCreated = true;
            } catch (IllegalArgumentException ex) {
                removeFirstNull(verticesCopy);
                if (verticesCopy.size() < 3 ) {
                    throw new MissingVerticesException("Not enough vertices", ex);
                }
            }
        }
       return verticesPolygon;
    }

    private List removeFirstNull(List<Vertex2D> vertices) {
        for (Vertex2D vertex : vertices) {
            if (vertex == null) {
                vertices.remove(vertex);
                return vertices;
            }
        }
        return vertices;
    }

    /**
     * Creates collection of polygons from the input, removing all null vertices and then it draws all the polygons.
     * @param collectionPolygons collection of polygons (every polygon is collection of vertices)
     * @throws EmptyDrawableException if nothing has been drawn
     */
    @Override
    public void tryToDrawPolygons(List<List<Vertex2D>> collectionPolygons) throws EmptyDrawableException {
        Iterator<List<Vertex2D>> iterator = collectionPolygons.iterator();
        int createdPolygonsCount = 0;

        while (iterator.hasNext()) {
            try {
                List<Vertex2D> vertices = iterator.next();
                drawPolygon(tryToCreatePolygon(vertices));
                createdPolygonsCount++;

            } catch (NullPointerException | MissingVerticesException | TransparentColorException ex) {
                if (!iterator.hasNext() && createdPolygonsCount == 0) {
                    throw new EmptyDrawableException("No polygons created", ex);
                }
            }
        }
    }

    /**
     * Gives polygons with color.
     * @param color of polygons to get
     * @return collection of polygons matching given color
     */
    public Collection<Polygon> getPolygonsWithColor(Color color) {
        return this.polygons.stream()
                .filter(coloredPolygon -> coloredPolygon.getColor().equals(color))
                .map(coloredPolygon -> coloredPolygon.getPolygon())
                .collect(Collectors.toList());
    }

}
