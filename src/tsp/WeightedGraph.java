/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Stack;

/**
 *
 * @author colbysadams
 */
public class WeightedGraph
{

    private Vertex[] verts;
    private boolean directed;

    public WeightedGraph(int n)
    {
        verts = new Vertex[n];
        for (int i = 0; i < n; ++i)
        {
            verts[i] = new Vertex(i, n);
        }
        directed = false;
    }

    public int size()
    {
        return verts.length;

    }

    public void removeEveryEdgeFromGraph()
    {
        verts = new Vertex[verts.length];
        for (int i = 0; i < verts.length; ++i)
        {
            verts[i] = new Vertex(i, verts.length);
        }
    }

    public boolean hasEdge(int source, int target)
    {
        return verts[source].hasEdgeTo(verts[target]);
    }

    public void addEdge(int source, int target, int weight)
    {
        verts[source].addEdge(verts[target], weight);
        if (!directed)
        {
            verts[target].addEdge(verts[source], weight);
        }
    }

    public void removeEdge(int source, int target)
    {
        verts[source].removeEdge(verts[target]);
        if (!directed)

        {
            verts[target].removeEdge(verts[source]);
        }
    }

    public void alterEdge(int source, int target, int weight)
    {
        verts[source].setEdgeWeight(verts[target], weight);
        if (!directed)

        {
            verts[target].setEdgeWeight(verts[source], weight);
        }
    }

    public Vertex getVertex(int i)
    {
        return verts[i];
    }

    public ArrayList<Stack<Integer>> getCycles()
    {
        Cycle cycle = new Cycle();
        return cycle.getCycles();

    }

    public int getEdgeWeight(int source, int target)
    {
        return verts[source].getEdgeWeight(target);
    }

    @Override
    public String toString()
    {
        String s = "";
        for (int i = 0; i < verts.length; ++i)
        {
            s += verts[i].toString() + ": " + verts[i].edgesToString() + "\n";
        }
        return s;
    }

    public CycleStack generateRandomGraph(int extraEdges,
                                          int lightWeight,
                                          int maxWeight)
    {

        CycleStack expectedCycle = new CycleStack(verts.length);

        int cycleSize = 0;
        int nextVert = 0;

        int prevVert;
        expectedCycle.push(nextVert);
        ++cycleSize;
        Random r = new Random();
        while (cycleSize < this.size())
        {
            prevVert = nextVert;
            nextVert = r.nextInt(this.size());
            while (expectedCycle.contains(nextVert))
            {
                nextVert = (nextVert + 1) % this.size();
            }

            cycleSize++;
            expectedCycle.push(nextVert);
            this.addEdge(prevVert, nextVert, r.nextInt(lightWeight) + 1);

        }
        this.addEdge(nextVert, 0, r.nextInt(lightWeight) + 1);

        for (int count = 0; count < extraEdges; ++count)
        {
            prevVert = r.nextInt(this.size());
            nextVert = r.nextInt(this.size());
            if (prevVert == nextVert)
            {
                nextVert = (nextVert + 1) % this.size();
            }
            this.addEdge(prevVert, nextVert, r.nextInt(maxWeight) + 1);
        }
        return expectedCycle;
    }

    /**
     *
     * @return returns a Hamiltonian cycle if graph
     *         contains a cycle that includes all vertices
     */
    private class Cycle
    {

        ArrayList<Stack<Integer>> cycles;
        //WeightedGraph graph;
        int count;

        Cycle()
        {
            cycles = new ArrayList();
            count = 0;
        }

        ArrayList<Stack<Integer>> getCycles()
        {

            for (int i = 0; i < verts.length; ++i)
            {
                if (verts[i].getDegree() < 2)
                {
                    return cycles;
                }
            }

            cycles = new ArrayList<>();
            //this.graph = graph;
            CycleStack stack = new CycleStack(verts.length);
            stack.push(1
            );
            cycleHelp(stack);
            //System.out.println("Cycle count: " + count);
            return cycles;

        }

        private void cycleHelp(CycleStack stack)
        {
            //count++;
            System.out.println(stack);
            int top = stack.peek();
            if (stack.size() == verts.length)
            {

                System.out.println("full path" + stack);

                //if (verts[top].hasEdgeTo(verts[stack.peekBottom()]))
                //{
                cycles.add((Stack) stack.clone());
                //}
                return;
            }

            Iterator<Integer> itr = verts[top].neighborIterator();
            boolean deadEnd = true;
            //System.out.println(deadEnd);
            Integer current = 0;
            while (itr.hasNext())
            {
                current = itr.next();
                if (!stack.contains(current))
                {
                    deadEnd = false;

                    stack.push(current);
                    cycleHelp(stack);
                    stack.pop();
                }
            }
            //System.out.println(deadEnd);
//            if (deadEnd)
//            {
//                stack = stack.reverseOrder();
//                cycleHelp(stack);
//            }
        }
    }
}
