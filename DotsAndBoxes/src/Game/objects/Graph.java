package Game.objects;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * class that represents a graph that contains dots and lines
 */
public class Graph {

    private Map<Dot, LinkedList<Dot>> adj; //adjacency map which will store the adjacent dots
    private List<Line> lines;

    /**
     * constructor method
     * @param lines contain the lines(edges) within the graph
     */
    public Graph(List<Line> lines){
        this.lines = lines;
        adj = new HashMap<>();

        //initialize the adjacency map
        for(int i = 0; i < lines.size() ; i++){
            adj.put(lines.get(i).getSource() , new LinkedList<Dot>());
        }

        for(int i = 0; i < lines.size(); i++){
            adj.get(lines.get(i).getSource()).add(lines.get(i).getDest());
        }
    }

    /**
     * accessor method for the lines
     * @return the list of the lines
     */
    public List<Line> getLines(){
        return this.lines;
    }

    /**
     * accessor method for the adjacent dots of a chosen dot
     * @param dot is the particular dot
     * @return a list containing adjacent dots to the parameter dot.
     */
    public List<Dot> getAdjacents(Dot dot){
        return adj.get(dot);
    }

    /**
     * helper method for addLine method
     * @param source is the source vertex
     * @param dest destination vertex
     * @return true if the edge can be added
     */
    private boolean addEdge(Dot source, Dot dest,boolean vertical ){
        if(!adj.containsKey(source)){
            adj.put(source,new LinkedList<>());
        }

        adj.get(source).add(new Line(source,dest,vertical).getDest());
        return true;
    }

    /**
     * method to add lines in the game graph
     * @param source is the start point of the dot
     * @param dest endpoint of the dot
     * @return true if the line is added
     */
    public boolean addLine(Dot source, Dot dest,boolean vertical){
        addEdge(source,dest, vertical);
        addEdge(dest,source,vertical);
        return true;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(Line l : lines){
            sb.append("(" + l.getSource() + " -- " + l.getDest() +  ")\t"  );

        }
        return sb.toString();
    }


}
