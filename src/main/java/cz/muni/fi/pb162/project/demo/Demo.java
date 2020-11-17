package cz.muni.fi.pb162.project.demo;

import cz.muni.fi.pb162.project.geometry.RegularOctagon;
import cz.muni.fi.pb162.project.geometry.Vertex2D;

/**
 * Demo class
 *
 * @author Marek Radimersky
 */
public class Demo {

    /**
     * Class for project testing.
     * @param args arguments
     */
    public static void main(String[] args) {
        RegularOctagon octagon = new RegularOctagon(new Vertex2D(0, 0), 1);
        System.out.println(octagon.toString());

    }
}
