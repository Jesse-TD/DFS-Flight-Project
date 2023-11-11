public class Vertex {
    private String city;
    private int cost;
    private int time;
    private Vertex nextVertex;


    public Vertex(String city, int cost, int time) {
        this.city = city;
        this.cost = cost;
        this.time = time;
        this.nextVertex = null;
    }


    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
    public int getTime() { return time; }
    public void setTime(int time) { this.time = time; }
    public Vertex getNextVertex() { return nextVertex; }
    public void setNextVertex(Vertex nextVertex) { this.nextVertex = nextVertex; }
}

