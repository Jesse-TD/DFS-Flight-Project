public class CityRouteList {
    Vertex firstVertex;


    public CityRouteList() {
        firstVertex = null;
    }

    // Adds a vertex to the list
    public void addVertex(String city, int cost, int time) {
        Vertex newVertex = new Vertex(city, cost, time);
        if (firstVertex == null) {
            firstVertex = newVertex;
        } else {
            Vertex entry = firstVertex;
            while (entry.getNextVertex() != null) {
                entry = entry.getNextVertex();
            }
            entry.setNextVertex(newVertex);
        }
    }

    // Finds a vertex by city name
    public Vertex findVertex(String city) {
        Vertex entry = firstVertex;
        while (entry != null) {
            if (entry.getCity().equals(city)) {
                return entry;
            }
            entry = entry.getNextVertex();
        }
        return null;
    }
}
