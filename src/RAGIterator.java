import java.util.Comparator;
import java.util.Iterator;

/**
 * Created by Ruzan on 11/29/2016.
 */
public class RAGIterator<E extends Comparable<E>> implements Iterator<E>
{
    Node<E> traverse;
    Node<E> root;
    public RAGIterator(RAGTreeSet<E> r)
    {
        root = r.getRoot();
    }
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
        Node<E> trav = traverse;
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
