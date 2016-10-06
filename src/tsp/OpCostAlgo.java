/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Stack;

/**
 *
 * @author colbysadams
 */
public class OpCostAlgo
{

    ArrayList<Stack<Integer>> solution;
    WeightedGraph g;

    public OpCostAlgo(WeightedGraph g)
    {
        solution = new ArrayList();
        this.g = g;
    }

    public String solutionsToString(ArrayList<Stack<Integer>> solution)
    {
        String solutionString = "";
        for (int i = 0; i < solution.size(); ++i)
        {

            Stack<Integer> stack = solution.get(i);
            solutionString += "C" + i + " verts: " + stack + "\n";
            int length = 0;
            int endpoint = stack.pop();
            int v1 = endpoint;
            int v2;
            while (!stack.isEmpty())
            {
                v2 = v1;
                v1 = stack.pop();

                length += g.getEdgeWeight(v1, v2);

            }
            length += g.getEdgeWeight(v1, endpoint);
            solutionString += ("C" + i + "  size: " + length + "\n");
        }
        return solutionString;
    }

    public String solutionsToString()
    {
        return this.solutionsToString(solution);
    }

    public ArrayList<Stack<Integer>> solution1()
    {

        //int[][] opCost = new int[g.size()][g.size()];
        int totalEdges = 0;
        for (int i = 0; i < g.size(); ++i)
        {
            totalEdges += g.getVertex(i).getDegree();
        }
        //System.out.println(totalEdges);
        EdgeData[] edgeStorage = new EdgeData[totalEdges];
        //ArrayList<EdgeData> edgeStorage = new ArrayList();

        int currentSource;
        Iterator<Integer> itr;
        EdgeData edge;
        int index = 0;
        for (currentSource = 0; currentSource < g.size(); ++currentSource)
        {

            itr = g.getVertex(currentSource).neighborIterator();
            int currentTarget;

            while (itr.hasNext())
            {

                currentTarget = itr.next();
                //System.out.println(currentSource + " " + currentTarget);
                edge = new EdgeData(currentSource, currentTarget, g.getEdgeWeight(currentSource, currentTarget));
                g.alterEdge(currentSource, currentTarget, Integer.MAX_VALUE);

                edge.opCost = getShortestPath(currentSource, currentTarget)[currentTarget].distance - edge.weight;
                g.alterEdge(edge.source, edge.target, edge.weight);

                edgeStorage[index++] = edge;
                //edgeStorage.add(edge);

            }

        }

        Arrays.sort(edgeStorage);

        g.removeEveryEdgeFromGraph();

        for (int j = 0; j < edgeStorage.length; j += 2)
        {
            if (g.getVertex(edgeStorage[j].source).getDegree() < 2
                    || g.getVertex(edgeStorage[j].target).getDegree() < 2)
            {
                g.addEdge(edgeStorage[j].source, edgeStorage[j].target, edgeStorage[j].weight);
            }
        }

        ArrayList<Stack<Integer>> cycles;
        EdgeData newEdge;
        for (int i = 0; i < edgeStorage.length; i += 2)
        {
            newEdge = edgeStorage[i];
            g.addEdge(newEdge.source, newEdge.target, newEdge.weight);
            cycles = g.getCycles();
            if (!cycles.isEmpty())
            {
                solution = cycles;
                return cycles;
            }
        }
        return null;
    }

    public void solution2()
    {

        int totalEdges = 0;
        for (int i = 0; i < g.size(); ++i)
        {
            totalEdges += g.getVertex(i).getDegree();
        }

        EdgeData[] edgeStorage = new EdgeData[totalEdges];

        int currentSource;
        Iterator<Integer> itr;
        EdgeData edge;
        int index = 0;
        for (currentSource = 0; currentSource < g.size(); ++currentSource)
        {

            itr = g.getVertex(currentSource).neighborIterator();
            int currentTarget;

            while (itr.hasNext())
            {

                currentTarget = itr.next();
                //System.out.println(currentSource + " " + currentTarget);
                edge = new EdgeData(currentSource, currentTarget, g.getEdgeWeight(currentSource, currentTarget));
                g.alterEdge(currentSource, currentTarget, Integer.MAX_VALUE);

                edge.opCost = getShortestPath(currentSource, currentTarget)[currentTarget].distance - edge.weight;
                g.alterEdge(edge.source, edge.target, edge.weight);

                edgeStorage[index++] = edge;
                //edgeStorage.add(edge);

            }

        }

        Arrays.sort(edgeStorage);

        g.removeEveryEdgeFromGraph();

        for (int j = 0; j < edgeStorage.length; j += 2)
        {
            if (g.getVertex(edgeStorage[j].source).getDegree() < 2
                    || g.getVertex(edgeStorage[j].target).getDegree() < 2)
            {
                g.addEdge(edgeStorage[j].source, edgeStorage[j].target, edgeStorage[j].weight);
            }
        }

        ArrayList<Stack<Integer>> cycles;
        EdgeData newEdge;
        for (int i = 0; i < edgeStorage.length; i += 2)
        {
            newEdge = edgeStorage[i];
            g.addEdge(newEdge.source, newEdge.target, newEdge.weight);
            cycles = g.getCycles();
            if (!cycles.isEmpty())
            {
                solution = cycles;
                //return cycles;
            }
        }
        //return null;
    }

    //Dijkstra's algo
    private VertexData[] getShortestPath(int vertA, int vertB)
    {
        //int[] dist = new int[g.size()];
        //boolean[] processed = new boolean[g.size()];
        VertexData[] verts = new VertexData[g.size()];
        //PriorityQueue<VertexData> vQueue = new PriorityQueue(g.size());
        for (int i = 0; i < g.size(); ++i)
        {
            verts[i] = new VertexData();
//            dist[i] = Integer.MAX_VALUE;
//            processed[i] = false;
        }

        //distance to source = 0;
        verts[vertA].distance = 0;

        for (int count = 0; count < g.size() - 1; ++count)
        {
            int u = minDistance(verts);
            verts[u].processed = true;

            if (u == vertB)
            {
                //System.out.println("Prelim dist from  " + vertA + " to " + vertB + ": " + dist[vertB]);
                return verts;
            }

            Iterator<Integer> itr = g.getVertex(u).neighborIterator();
            int neighbor;
            while (itr.hasNext())
            {
                neighbor = itr.next();

                if (!verts[neighbor].processed
                        && verts[u].distance != Integer.MAX_VALUE
                        && verts[u].distance + g.getEdgeWeight(u, neighbor) < verts[neighbor].distance)
                {
                    verts[neighbor].distance = verts[u].distance + g.getEdgeWeight(u, neighbor);
                }
            }
        }

        return verts;

    }

    private int minDistance(VertexData[] verts)
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < verts.length; v++)
        {
            if (verts[v].processed == false && verts[v].distance <= min)
            {
                min = verts[v].distance;
                min_index = v;
            }
        }

        return min_index;
    }

    private class EdgeData implements Comparable<EdgeData>
    {

        int source;
        int target;
        int weight;
        int opCost;

        EdgeData(int source, int target, int weight)
        {
            this.source = source;
            this.target = target;
            this.weight = weight;

        }

        @Override
        public int compareTo(EdgeData o)
        {
            if (o.opCost != this.opCost)
            {
                return o.opCost - this.opCost;
            }
            if (o.weight != this.weight)
            {
                return this.weight - o.weight;
            }
            if (Integer.min(this.source, this.target) != Integer.min(o.source, o.target))
            {
                return Integer.min(this.source, this.target) - Integer.min(o.source, o.target);
            }
            return Integer.max(this.source, this.target) - Integer.max(o.source, o.target);

        }

        public String toString()
        {
            String s = "(V" + source + "-V" + target + ":w=" + weight + ", oc=" + opCost + ")";
            return s;
        }
    }

    private class VertexData implements Comparable<VertexData>
    {

        int distance;
        int prevVertex;
        boolean processed;

        VertexData()
        {
            distance = Integer.MAX_VALUE;
            prevVertex = -1;
            processed = false;
        }

        @Override
        public int compareTo(VertexData o)
        {
            return this.distance - o.distance;
        }
    }
}
