/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edgepackage;

import tsp.Vertex;

/**
 *
 * @author colbysadams
 */
class EdgeNode
{

    // private Vertex source;
    private int target;
    //private Vertex vertexB;
    private int weight;
    EdgeNode next;

    public EdgeNode(int target, int weight)
    {
        //this.source = source;
        this.target = target;
        //this.vertexB = vertexB;
        this.weight = weight;

        this.next = null;
    }

    public EdgeNode()
    {
        this.target = -1;
        this.weight = -1;
        this.next = null;
    }

    /**
     * @return the destination
     */
    public int getTarget()
    {
        return target;
    }

//    public Vertex getSource()
//    {
//        return source;
//    }
    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    /**
     * @return the weight
     */
    public int getWeight()
    {
        return weight;
    }

    public String toString()
    {
        return Vertex.label + target + ":" + weight;
    }
}
