package cz.muni.fi.pb162.project.geometry;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Created by xradimer on 06.12.2017.
 */
public class LabeledPolygon extends SimplePolygon implements Labelable, Sortable, PolygonIO {
    private Map<String, Vertex2D> tMap = new TreeMap<>();

    /**
     * Constructor
     */
    public LabeledPolygon() {
        super(new Vertex2D[0]);
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

        Vertex2D[] sortedVertexArray = tMap.values().toArray(new Vertex2D[0]);

        return sortedVertexArray[index % getNumVertices()];
    }

    /**
     * Returns number of vertices of the polygon.
     *
     * @return number of vertices
     */
    @Override
    public int getNumVertices() {
        return tMap.size();
    }

    /**
     * Returns unmodifiable collection of vertices.
     *
     * @return unmodifiable collection of vertices
     */
    @Override
    public Collection<Vertex2D> getVertices() {
        return Collections.unmodifiableCollection(tMap.values());
    }

    /**
     * Add labeled vertex to polygon. If label already exists, it is replaced.
     * If any of the input parameters are null, method should throw an exception.
     *
     * @param label  label of the vertex
     * @param vertex the actual vertex object
     */
    @Override
    public void addVertex(String label, Vertex2D vertex) throws NullPointerException {
        if (label == null || vertex == null) {
            throw new IllegalArgumentException("vertex or label parameter is null");
        }

        tMap.put(label, vertex);
    }

    /**
     * Get vertex stored under given label in a polygon.
     * If label does not exists, IllegalArgumentException is thrown.
     *
     * @param label label under which the vertex is stored
     * @return vertex with given label
     */
    @Override
    public Vertex2D getVertex(String label) throws NullPointerException {
        if (!(tMap.containsKey(label))) {
            throw new NullPointerException("label does not exists");
        }
        return tMap.get(label);
    }

    /**
     * Method returns the labels of vertices in a polygon.
     * The labels are sorted in the ascending order lexicographically.
     *
     * @return collection of labels in ascending order
     */
    @Override
    public Collection<String> getLabels() {
        return Collections.unmodifiableCollection(tMap.keySet());
    }

    /**
     * Finds all labels corresponding to given vertex.
     *
     * @param vertex vertex which labels are searched
     * @return collection of corresponding labels
     */
    @Override
    public Collection<String> getLabels(Vertex2D vertex) {
        Collection<String> labels = new ArrayList<>();
        for (String key : tMap.keySet()) {
            if (tMap.get(key).equals(vertex)) {
                labels.add(key);
            }
        }
        return Collections.unmodifiableCollection(labels);
    }

    /**
     * Method returns the vertices of this polygon in ascending order
     * defined by he natural ordering of {@link cz.muni.fi.pb162.project.geometry.Vertex2D} class.
     *
     * @return sorted vertices
     */
    @Override
    public Collection<Vertex2D> getSortedVertices() {
        return new TreeSet(tMap.values());

    }

    /**
     * Method returns the vertices of this polygon in ascending order
     * defined by given comparator.
     *
     * @param comparator comparator object used to determine the ordering
     * @return sorted vertices
     */
    @Override
    public Collection<Vertex2D> getSortedVertices(Comparator<Vertex2D> comparator) {
        Collection col = new TreeSet<>(comparator);
        col.addAll(tMap.values());
        return col;
    }

    /**
     * Returns vertices that occurs more than once in the collection.
     * @return duplicate vertices
     */
    public Collection<Vertex2D> duplicateVertices() {

        Collection<Vertex2D> duplicateVerticesSet = new HashSet<>();
        List<Vertex2D> verticesList = new ArrayList<>();
        verticesList.addAll(tMap.values());
        for (int x = 0; x < verticesList.size(); x++) {
            for (int y = x + 1; y < verticesList.size(); y++) {
                if (verticesList.get(x).equals(verticesList.get(y))) {
                    duplicateVerticesSet.add(verticesList.get(x));
                }
            }
        }
        return new TreeSet<>(duplicateVerticesSet);
    }

    /**
     * Write polygon data into output stream.
     *
     * @param os output stream
     * @throws java.io.IOException on write error
     */
    @Override
    public void write(OutputStream os) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "utf-8"));
        List listOfVerticesMap = new ArrayList<>();
        listOfVerticesMap.add(tMap);
        try {
            for(Map.Entry<String,Vertex2D> entry : tMap.entrySet()) {
                double x = entry.getValue().getX();
                double y = entry.getValue().getY();
                String label = entry.getKey();

                String write = x + " " + y + " " + label;
                bw.write(write);
                bw.newLine();

            }
        } catch (IOException ex) {
            throw new IOException(ex);
        }
        bw.flush();
    }

    /**
     * Write polygon data into file.
     *
     * @param file ouput file
     * @throws java.io.IOException on write error
     */
    @Override
    public void write(File file) throws IOException {
        try(FileOutputStream fos = new FileOutputStream(file)) {
            write(fos);
        }
    }

    /**
     * Read polygon data from input stream.
     *
     * @param is input stream
     * @throws java.io.IOException on read error
     */
    @Override
    public void read(InputStream is) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(is, "utf-8"));
        Map<String, Vertex2D> tmp = new HashMap<>();
        String line = null;

        while ((line = br.readLine()) != null) {

            String[] data = line.split("\\s", 3);
            if (data.length < 3) {
                throw new IOException("exception");
            }

            try {
                double x = Double.valueOf(data[0]);
                double y = Double.valueOf(data[1]);
                String label = data[2];
                tmp.put(label, new Vertex2D(x, y));
            } catch (NumberFormatException ex) {
                throw new IOException(ex);
            }
        }

        tMap.putAll(tmp);
    }

    /**
     * Read polygon data from file.
     *
     * @param file input file
     * @throws java.io.IOException on read error
     */
    @Override
    public void read(File file) throws IOException {
        try (FileInputStream fis = new FileInputStream(file)) {
            read(fis);
        }
    }

    /**
     * Writes binary polygon data into output stream.
     * @param os outpustream to write into
     * @throws IOException on write error
     */
    public void binaryWrite(OutputStream os) throws IOException {
        new OutputStreamWriter(os, "utf-8");
        List listOfVerticesMap = new ArrayList<>();
        listOfVerticesMap.add(tMap);
        try {
            for(Map.Entry<String,Vertex2D> entry : tMap.entrySet()) {
                double x = entry.getValue().getX();
                double y = entry.getValue().getY();
                String label = entry.getKey();

                String write = x + " " + y + " " + label + System.lineSeparator();
                os.write(write.getBytes());

            }
        } catch (IOException ex) {
            throw new IOException(ex);
        }

        os.flush();
    }
}
