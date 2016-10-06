/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

/**
 *
 * @author colbysadams
 */
public class TSP
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {

        int verts = 11;

        //Stack<Integer> test = new Stack();
        //System.out.println(test.push(5));
        WeightedGraph g = new WeightedGraph(verts);

        int totalEdges = verts * (verts - 1) / 2;

        g.addEdge(0, 1, 1);
        g.addEdge(0, 8, 1);
        g.addEdge(0, 7, 1);
        g.addEdge(1, 9, 1);
        g.addEdge(1, 2, 1);
        g.addEdge(2, 8, 1);
        g.addEdge(2, 10, 1);
        g.addEdge(2, 3, 1);
        g.addEdge(3, 9, 1);
        g.addEdge(3, 4, 1);
        g.addEdge(4, 10, 1);
        g.addEdge(4, 5, 1);
        g.addEdge(5, 9, 1);
        g.addEdge(5, 6, 1);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 1);
        g.addEdge(6, 10, 1);
        g.addEdge(7, 9, 1);
        //g.addEdge(verts, verts, verts);

        CycleStack expectedCycle = new CycleStack(g.size());
        //expectedCycle = g.generateRandomGraph(totalEdges, 50, 600);

        System.out.println(g);
//        ArrayList<Stack<Integer>> aList = new ArrayList();
//        aList.add((Stack<Integer>) expectedCycle.clone());
//        System.out.println(g);
        OpCostAlgo opCost = new OpCostAlgo(g);
//        System.out.println("Expected Cycle: \n" + opCost.solutionsToString(aList));
//        expectedCycle = expectedCycle.reverseOrder();
//        aList = new ArrayList();
//        aList.add((Stack<Integer>) expectedCycle.clone());
//        System.out.println("Expected Cycle: \n" + opCost.solutionsToString(aList));
        //opCost.solution1();
//        //System.out.println(opCost.solution);
        //System.out.println(opCost.solutionsToString());

        System.out.println(g.getCycles());

    }
    //        g.addEdge(0, 1, 1);
// for (int i = 0; i < cycles.size(); ++i)
//        {
//            System.out.println(cycles.get(i));
//            Stack<Vertex> stack = cycles.get(i);
//            int length = 0;
//            Vertex endpoint = stack.pop();
//            Vertex v1 = endpoint;
//            Vertex v2;
//            while (!stack.isEmpty())
//            {
//                v2 = v1;
//                v1 = stack.pop();
//
//                length += g.getEdgeWeight(v1.getNumber(), v2.getNumber());
//
//            }
//            length += g.getEdgeWeight(v1.getNumber(), endpoint.getNumber());
//            System.out.println("cycle length: " + length);
//        }
//        g.addEdge(0, 2, 10);
//        g.addEdge(0, 3, 1);
//        g.addEdge(1, 2, 100);
//        g.addEdge(1, 3, 10);
//        g.addEdge(2, 3, 1);
//        g.addEdge(3, 4, 5);
//        g.addEdge(2, 4, 5);
//        g.addEdge(1, 4, 5);
//        g.addEdge(0, 4, 5);
//
}
