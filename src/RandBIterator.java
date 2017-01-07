/**
 * RandBIterator.java
 * @author: Ruzan Sasuri            rps7183@g.rit.edu
 * @author: Akash Venkatachalam     av2833@g.rit.edu
 * @author: Ghodratollah Aalipour   ga5481@g.rit.edu
 * Id: $ RandBIterator.java v1.0, 2016/12/05$
 * Revision: First Revision
 *
 * This class acts like an in-order iterator for the RandBTree class.
 */

import java.util.Iterator;

public class RandBIterator<E extends Comparable<E>> implements Iterator<E>
{
    RandBNode<E> traverse;
    RandBNode<E> root;

    /**
     * Parametrized constructor that takes in a RandBTree object.
     * @param r The RandBTree object.
     */
    public RandBIterator(RandBTree<E> r)
    {
        root = r.getRoot();
    }

    /**
     * Returns the next node and moves the pointer to that node.
     * @return The next node.
     */
    public E next()
    {
        if(traverse == null)
        {
            if(root == null)
            {
                return null;
            }
            traverse = root;
            while(traverse.getLeft() != null)
            {
                traverse=traverse.getLeft();
            }
        }
        else
        {
            if (traverse.getRight() != null)
            {
                traverse = traverse.getRight();
                while (traverse.getLeft() != null)
                {
                    traverse = traverse.getLeft();
                }
            }
            else
            {
                while (traverse.getParent().getLeft() != traverse)
                {
                    traverse = traverse.getParent();
                }
                traverse = traverse.getParent();
            }
        }
        return traverse.getValue();
    }

    /**
     * Checks if there is a node after the current node.
     * @return true if a next node exists, otherwise false.
     */
    public boolean hasNext()
    {
        if(traverse == null)
        {
            if(root == null)
            {
                return false;
            }
            return true;
        }
        boolean t = false;
        RandBNode<E> trav = traverse;
        if(traverse.getRight() != null)
        {
            t = true;
        }
        else if(traverse.getParent() == null)
        {
            return false;
        }
        else if(trav.getParent().getLeft() == trav)
        {
            t = true;
        }
        else
        {
            while(trav.getParent() != null)
            {
                if(trav.getParent().getLeft() == trav)
                {
                    t = true;
                    break;
                }
                trav = trav.getParent();
            }
        }
        return t;
    }
}

