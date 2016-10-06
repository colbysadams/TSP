/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tsp;

import java.util.Stack;

/**
 *
 * @author colbysadams
 */
public class CycleStack extends Stack<Integer>
{

    private boolean[] inStack;
    private Integer bottom;

    CycleStack(int n)
    {
        super();
        inStack = new boolean[n];
        for (int i = 0; i < n; ++i)
        {
            inStack[i] = false;
        }
        bottom = null;

    }

    public int peekBottom()
    {
        return bottom;
    }

    @Override
    public Integer push(Integer item)
    {
        inStack[item] = true;
        if (this.isEmpty())
        {
            bottom = item;
        }
        return super.push(item);
    }

    public Integer pop()
    {
        inStack[this.peek()] = false;
        if (this.size() == 1)
        {
            bottom = null;
        }
        return super.pop();
    }

    public boolean contains(Integer item)
    {
        return inStack[item];
    }

    public CycleStack reverseOrder()
    {
        CycleStack revStack = new CycleStack(inStack.length);
        while (!this.isEmpty())
        {
            revStack.push(this.pop());
        }
        return revStack;
    }
}
