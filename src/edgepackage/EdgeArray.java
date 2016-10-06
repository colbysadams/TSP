/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edgepackage;

import java.util.Iterator;

/**
 *
 * @author colbysadams
 */
public class EdgeArray
{

    private EdgeNode[] edges;
    private EdgeNode head;
    private int degree;

    public EdgeArray(int size)
    {
        edges = new EdgeNode[size];
    }

    public int getWeightTo(int target)
    {
        return edges[target].getWeight();
    }

    public int getDegree()
    {
        return degree;
    }

    public boolean hasEdgeTo(int target)
    {
        return (edges[target] != null);
    }

    public void addEdge(int target, int weight)
    {
        if (this.hasEdgeTo(target))
        {

            if (weight < edges[target].getWeight())
            {
                edges[target].setWeight(weight);
            }

        } else
        {

            EdgeNode newEdge = new EdgeNode(target, weight);
            edges[target] = newEdge;
            degree++;
            try
            {
                EdgeNode prev = getPreviousEdge(target);

                newEdge.next = prev.next;
                prev.next = newEdge;
            }
            catch (NoPreviousEdgeException e)
            {
                newEdge.next = head;
                head = newEdge;
            }

        }
    }

    public void removeEdge(int target)
    {
        if (!this.hasEdgeTo(target))
        {
            return;
        }

        try
        {
            EdgeNode prev = getPreviousEdge(target);

            prev.next = edges[target].next;
        }
        catch (NoPreviousEdgeException e)
        {
            head = head.next;
        }
        edges[target].next = null;
        edges[target] = null;
        degree--;

    }

    public void replaceEdge(int target)
    {

    }

    public void setEdgeWeight(int target, int weight)
    {
        edges[target].setWeight(weight);
    }

    private EdgeNode getPreviousEdge(int target)
    {
        //System.out.println(target);
        EdgeNode prev;
        int index = target;
        if ((head == null || target <= head.getTarget()))
        {
            throw new NoPreviousEdgeException();
        }
        do
        {
            index--;

            prev = edges[index];
        } while (prev == null);
        return prev;
    }

    public String toString()
    {
        String s = "{";
        Iterator<Integer> itr = neighborIterator();
        int i;
        while (itr.hasNext())
        {
            i = itr.next();
            s += " " + edges[i];
        }
        s += "}";
        return s;
    }

    /**
     *
     * @return
     */
    public Iterator<Integer> neighborIterator()
    {
        return new NeighborIterator(head);
    }

    private class NeighborIterator implements Iterator<Integer>
    {

        EdgeNode current;

        NeighborIterator(EdgeNode head)
        {
            current = new EdgeNode();
            this.current.next = head;
        }

        @Override
        public boolean hasNext()
        {
            return current.next != null;
        }

        @Override
        public Integer next()
        {
            this.current = current.next;
            return this.current.getTarget();
        }
    }

    private class NoPreviousEdgeException extends RuntimeException
    {
    }
}
