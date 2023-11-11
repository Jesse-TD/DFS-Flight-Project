public class DfsStack {
    private Vertex stackTop;


    public DfsStack() {
        this.stackTop = null;
    }

    // pushes an element onto the stack
    public void pushToStack(Vertex vertex) {
        vertex.setNextVertex(stackTop);
        stackTop = vertex;
    }

    // pops an element off the stack
    public Vertex popOffStack() {
        if (stackTop == null) {
            return null;
        }
        Vertex value = stackTop;
        stackTop = stackTop.getNextVertex();
        return value;
    }

    // peeks at the top element without removing it
    public Vertex peekStackTop() {
        return stackTop;
    }

    // checks if the stack is empty
    public boolean checkIfEmpty() {
        return stackTop == null;
    }
}

