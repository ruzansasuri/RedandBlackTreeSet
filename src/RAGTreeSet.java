import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by Ruzan on 11/29/2016.
 */
@SuppressWarnings({"serial","unchecked"})
public class RAGTreeSet<E extends Comparable<E>> extends TreeSet<E>
{
    private Node<E> root;
    private int treeseize;
    public RAGTreeSet()
    {
        root = null;
        treeseize = 0;
    }
    public boolean add(E e)
    {
        if(root == null)
        {
            treeseize++;
            root = new Node<E>(e);
            return true;
        }
        return addHelper(root, e);
    }
    public boolean addHelper(Node<E> n, E e)
    {
        int c = e.compareTo(n.getValue());
        boolean f = false;
        if(c < 0)
        {
            if(n.getLeft() == null)
            {
                n.setLeft(e);
                n.getLeft().setParent(n);
                treeseize++;
                f = true;
            }
            else
            {
                f = addHelper(n.getLeft(),e);
            }
        }
        else if (c > 0)
        {
            if(n.getRight() == null)
            {
                n.setRight(e);
                n.getRight().setParent(n);
                treeseize++;
                f = true;
            }
            else
            {
                f = addHelper(n.getRight(),e);
            }
        }
        return f;
    }
    public void clear()
    {
        root = null;
        treeseize = 0;
    }
    public boolean contains(Object o)
    {
        E val = (E)o;
        if(root == null)
        {
            return false;
        }
        Node<E> trav = root;
        while(!val.equals(trav.getValue()) && (trav.getLeft() != null || trav.getRight() != null))
        {
            int c = trav.getValue().compareTo(val);
            if(c < 0)
            {
                trav = trav.getRight();
            }
            else
            {
                trav = trav.getLeft();
            }
        }
        if(val.equals(trav.getValue()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    public boolean isEmpty()
    {
        if(root == null)
        {
            return true;
        }
        return false;
    }
    public RAGIterator<E> iterator()
    {
        return new RAGIterator<E>(this);
    }
    public int size()
    {
        return treeseize;
    }
    public Node<E> getRoot()
    {
        return root;
    }
}
