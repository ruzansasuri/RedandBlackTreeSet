/**
 * Created by Ruzan on 12/4/2016.
 */
public class RandBNode<E extends Comparable<E>>
{
    E value;
    RandBNode<E> parent;
    RandBNode<E> left;
    RandBNode<E> right;
    boolean isRed;
    public RandBNode(E val)
    {
        value = val;
        parent = null;
        left = null;
        right = null;
        isRed = true;
    }
    E getValue()
    {
        return value;
    }
    RandBNode<E> getLeft()
    {
        return left;
    }
    RandBNode<E> getRight(){
        return right;
    }

    void setValue(E data){
        value=data;
    }
    void setLeft(RandBNode<E> data)
    {
        left = data;
    }

    void setRight(RandBNode<E> data)
    {
        right = data;
    }

    RandBNode<E> getParent()
    {
        return parent;
    }
    void setParent(RandBNode<E> parent)
    {
        this.parent=parent;

    }
    void changeColor()
    {
        isRed = !isRed;
    }
    boolean getIsRed()
    {
        return isRed;
    }
    RandBNode<E> getGrandParent()
    {
        return parent.parent;
    }
    RandBNode<E> getUncle()
    {
        if(parent.parent != null)
        {
            if (parent.parent.getLeft() == parent)
            {
                return parent.parent.getRight();
            }
            else
            {
                return parent.parent.getLeft();
            }
        }
        else
        {
            return null;
        }
    }
    void setRed(boolean color)
    {
        isRed = color;
    }
}
