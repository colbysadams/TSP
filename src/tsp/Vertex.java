/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import edgepackage.EdgeArray;
import java.util.Iterator;

/**
 *
 * @author colbysadams
 */
public class Vertex implements Comparable<Vertex>
{

    private int number;
    public static final String label = "V";
    private EdgeArray edges;

    public Vertex(String label, int i, int n)
    {
        number = i;

        edges = new EdgeArray(n);

    }

    public void setEdgeWeight(Vertex target, int weight)
    {
        edges.setEdgeWeight(target.number, weight);
    }

    public Vertex(int i, int n)
    {
        this("V", i, n);
    }

    public int getNumber()
    {
        return number;
    }

    public void addEdge(Vertex target, int weight)
    {
        if (edges.hasEdgeTo(target.number))
        {
            return;
        }
        edges.addEdge(target.number, weight);

        //System.out.println("addEdge" + number + "," + target.number);
        //hasEdge[target.number] = true;
    }

    @Override
    public int compareTo(Vertex o)
    {
        return this.number - o.number;
    }

    public void removeEdge(Vertex target)
    {
        edges.removeEdge(target.number);

    }

    public boolean hasEdgeTo(Vertex target)
    {
        return edges.hasEdgeTo(target.number);
    }

    public Iterator<Integer> neighborIterator()
    {
        return edges.neighborIterator();
    }

    public int getDegree()
    {
        return edges.getDegree();
    }

    public int getEdgeWeight(int target)
    {
        return edges.getWeightTo(target);
    }

    public String getLabel()
    {
        return label;
    }

    public String edgesToString()
    {
        return edges.toString();
    }

    public String toString()
    {
        return label + number;
    }
}
