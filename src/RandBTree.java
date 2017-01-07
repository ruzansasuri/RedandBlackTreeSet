import java.util.Comparator;
import java.util.TreeSet;

/**
 * Created by Ruzan on 12/4/2016.
 */
public class RandBTree<E extends Comparable<E>> extends TreeSet<E>
{
    RandBNode<E> root;
    int treesize;
    public RandBTree()
    {
        root = null;
        treesize = 0;
    }
    public boolean add(E e)
    {
        RandBNode<E> newNode = new RandBNode<E>(e);
        if(root == null)
        {
            root = newNode;
            root.setRed(false);
            treesize++;
            return true;
        }
        if(addHelper(root, newNode))
        {
            colorCorrection(newNode);
            treesize++;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     *
     * @param node
     */
    public void colorCorrection(RandBNode<E> node)
    {
        if(node == root)
        {
            node.setRed(false);
        }
        else if(node.getParent().getIsRed())
        {
            if(node.getUncle() == null || !node.getUncle().getIsRed())
            {
                if(node.getGrandParent().getLeft() == node.getParent())
                {
                    if(node.getParent().getLeft() == node)
                    {
                        rotate(node.getGrandParent(), true);
                        boolean redness = node.getParent().getIsRed();
                        node.getParent().setRed(node.getParent().getRight().getIsRed());
                        node.getParent().getRight().setRed(redness);
                    }
                    else
                    {
                        rotate(node.getParent(), false);
                        rotate(node.getParent(), true);
                        boolean redness = node.getIsRed();
                        node.setRed(node.getRight().getIsRed());
                        node.getRight().setRed(redness);
                    }
                }
                else
                {
                    if(node.getParent().getRight() == node)
                    {
                        rotate(node.getGrandParent(), false);
                        boolean redness = node.getParent().getIsRed();
                        node.getParent().setRed(node.getParent().getLeft().getIsRed());
                        node.getParent().getLeft().setRed(redness);
                    }
                    else
                    {
                        rotate(node.getParent(), true);
                        rotate(node.getParent(), false);
                        boolean redness = node.getIsRed();
                        node.setRed(node.getLeft().getIsRed());
                        node.getLeft().setRed(redness);
                    }
                }
            }
            else
            {
                node.getParent().changeColor();
                node.getUncle().changeColor();
                node.getGrandParent().changeColor();
                colorCorrection(node.getGrandParent());
            }
        }
    }
    public void rotate(RandBNode<E> node, boolean right)
    {
        RandBNode<E> g = node;
        RandBNode<E> p;
        if(right)
        {
            p = g.getLeft();
            if (g != root)
            {
                if(g.getParent().getLeft() == g)
                {
                    g.getParent().setLeft(p);
                }
                else
                {
                    g.getParent().setRight(p);
                }
                p.setParent(g.getParent());
            }
            else
            {
                root = p;
                root.setParent(null);
            }
            g.setLeft(p.getRight());
            if(p.getRight() != null)
            {
                p.getRight().setParent(g);
            }
            p.setRight(g);
            g.setParent(p);
        }
        else
        {
            p = g.getRight();
            if (g != root)
            {
                if(g.getParent().getLeft() == g)
                {
                    g.getParent().setLeft(p);
                }
                else
                {
                    g.getParent().setRight(p);
                }
                p.setParent(g.getParent());
            }
            else
            {
                root = p;
                root.setParent(null);
            }
            g.setRight(p.getLeft());
            if(p.getLeft() != null)
            {
                p.getLeft().setParent(g);
            }
            p.setLeft(g);
            g.setParent(p);
        }
    }
    public boolean addHelper(RandBNode<E> n, RandBNode<E> e)
    {
        int c = e.getValue().compareTo(n.getValue());
        boolean f = false;
        if(c < 0)
        {
            if(n.getLeft() == null)
            {
                n.setLeft(e);
                n.getLeft().setParent(n);
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
        treesize = 0;
    }
    public boolean contains(Object o)
    {
        E val = (E)o;
        if(root == null)
        {
            return false;
        }
        RandBNode<E> trav = root;
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
        return root == null;
    }
    public RandBIterator<E> iterator()
    {
        return new RandBIterator<E>(this);
    }
    public int size()
    {
        return treesize;
    }
    public RandBNode<E> getRoot()
    {
        return root;
    }
    public static void main(String a[])
    {
        RandBTree<String> r = new RandBTree<String>();
        System.out.println(r.add("1"));
        System.out.println(r.root.getValue());
        System.out.println(r.add("2"));
        System.out.println(r.add("3"));
        System.out.println(r.add("4"));
        RandBIterator<String> i = new RandBIterator<String>(r);
        while(i.hasNext())
        {
            System.out.print(i.next() + " ");
        }
    }
}
