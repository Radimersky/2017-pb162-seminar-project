package cz.muni.fi.pb162.project.geometry;

/**
 * Creates object triangle made of 3 vertexes.
 *
 * @author Marek Radiměřský
 */
public class Triangle extends ArrayPolygon implements Measurable {

    private final Triangle[] subTriangles;
    public static final double EPSILON = 0.001;

    /**
     * Constructs new triangle.
     * @param a vertex
     * @param b vertex
     * @param c vertex
     */
    public Triangle(Vertex2D a, Vertex2D b, Vertex2D c) {
        super(new Vertex2D[] {a, b, c});
        subTriangles = new Triangle[3];
    }

    /**
     * Gives info if triangle is equilateral or not.
     * @return true if is equilateral, false if is not equilateral
     */
    public boolean isEquilateral() {

        //d = delka
        double d1 = getVertex(0).distance(getVertex(1));
        double d2 = getVertex(1).distance(getVertex(2));
        double d3 = getVertex(0).distance(getVertex(2));

        return Math.abs(d1 - d2) < EPSILON && Math.abs(d1 - d3) < EPSILON;
    }

    /**
     * String information of triangle.
     * @return information about triangle
     */
    @Override
    public String toString() {
        return "Polygon: vertices = " + getVertex(0) + " " + getVertex(1) + " " + getVertex(2);
    }

    /**
     * Boolean information whether triangle is divided.
     * @return true if is divided false if not
     */
    public boolean isDivided() {
        return subTriangles[0] != null;
    }

    /**
     * Creates 3 subtriangles from triangle.
     * @param index is between 0 and 2, determines subTriangle
     * @return subtriangle
     */
    public Triangle getSubTriangle(int index) {
        if (!isDivided()) {
            return null;
        }
        if (index < 0 || index > 2) {
            return null;
        }
        return subTriangles[index];
    }

    /**
     * Devides triangle into three subtriangles.
     * @return true if created successfully
     */
    public boolean divide() {
        if (isDivided()) {
            return false;
        }

        Vertex2D vertexAB = getMiddleVertex(getVertex(0), getVertex(1));
        Vertex2D vertexAC = getMiddleVertex(getVertex(0), getVertex(2));
        Vertex2D vertexBC = getMiddleVertex(getVertex(1), getVertex(2));

        Triangle subTriangle1 = new Triangle(getVertex(0), vertexAB, vertexAC);
        Triangle subTriangle2 = new Triangle(getVertex(1), vertexAB, vertexBC);
        Triangle subTriangle3 = new Triangle(getVertex(2), vertexAC, vertexBC);

        subTriangles[0] = subTriangle1;
        subTriangles[1] = subTriangle2;
        subTriangles[2] = subTriangle3;

        return true;
    }

    /**
     * Gets middle of vertex.
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return middle vertex of two given vertexes
     */
    private Vertex2D getMiddleVertex(Vertex2D v1, Vertex2D v2) {
        double x = (v1.getX() + v2.getX()) / 2;
        double y = (v1.getY() + v2.getY()) / 2;
        Vertex2D vertex = new Vertex2D(x, y);
        return vertex;
    }

    /**
     * Creates Sierpinski triangle.
     * @param depth determines to how many triangles it should divide
     * @return Sierpinski triangle
     */
    public boolean divide(int depth) {
        if (depth <= 0) {
            return false;
        }

        this.divide();
        for (Triangle subTriangle : subTriangles) {
            subTriangle.divide(depth - 1);
        }

        return true;
    }

 }
