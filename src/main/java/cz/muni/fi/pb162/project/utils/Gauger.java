package cz.muni.fi.pb162.project.utils;

import cz.muni.fi.pb162.project.geometry.Measurable;
import cz.muni.fi.pb162.project.geometry.Triangle;

/**
 * Gives info about objects that can be gaugerable.
 *
 * @author Marek Radimerky
 */
public class Gauger {

    /**
     * Prints width and height of object.
     * @param object wanted object
     */
    public static void printMeasurement(Measurable object) {
        System.out.println("Width: " + object.getWidth());
        //"Width: <w>"
        System.out.println("Height: " + object.getHeight());
        //"Height: <h>"
    }

    /**
     * Prints width and height and info about triangle.
     * @param triangle to get information about
     */
    public static void printMeasurement(Triangle triangle) {
        System.out.println(triangle.toString());
        printMeasurement((Measurable) triangle);
    }
}
