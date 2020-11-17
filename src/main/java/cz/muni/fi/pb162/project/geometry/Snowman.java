package cz.muni.fi.pb162.project.geometry;

/**
 * Class representing snowman as @BALLS circles.
 *
 * @author Marek Radimersky
 */
public class Snowman {
    private RegularPolygon[] polygons = new RegularPolygon[BALLS];
    private double shrinkingFactor;
    private static final double FACTOR = 0.8;
    private static final int BALLS = 3;

    /**
     * Constructs new snowman.
     * @param regularPolygon polygon
     * @param shrinkingFactor factor by which balls gets smaller
     */
    public Snowman(RegularPolygon regularPolygon, double shrinkingFactor) {
        this.polygons[0] = regularPolygon;

        if (shrinkingFactor > 0 && shrinkingFactor <= 1) {
            this.shrinkingFactor = shrinkingFactor;
        } else {
            this.shrinkingFactor = FACTOR;
        }

        for (int i = 1; i < BALLS; i++) {
            this.polygons[i] = new GeneralRegularPolygon(new Vertex2D(getUpperBallX(polygons[i - 1]),
                    getUpperBallY(polygons[i - 1])),
                    regularPolygon.getNumEdges(), getUpperBallRadius(polygons[i - 1]));
        }
    }


    /**
     * Gives x vertex of new ball.
     * @param lowerBall polygons that is under used ball
     * @return x vertex
     */
    private double getUpperBallX(RegularPolygon lowerBall) {
        return lowerBall.getCenter().getX();
    }

    /**
     * Gives y vertex of new ball.
     * @param lowerBall polygons that is under used ball
     * @return y vertex
     */
    private double getUpperBallY(RegularPolygon lowerBall) {
        return lowerBall.getCenter().getY() + lowerBall.getRadius() + lowerBall.getRadius() * shrinkingFactor;
    }

    /**
     * Gives radius of new ball.
     * @param lowerBall polygons that is under used ball
     * @return radius
     */
    private double getUpperBallRadius(RegularPolygon lowerBall) {
        return lowerBall.getRadius() * shrinkingFactor;
    }

    /**
     * Gives array of balls that snowman is made from.
     * @return array of balls
     */
    public RegularPolygon[] getBalls() {
        return this.polygons;
    }
}
