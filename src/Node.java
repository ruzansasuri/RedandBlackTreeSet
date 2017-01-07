import javafx.scene.Parent;

import java.util.Comparator;

/**
 * Created by alipour_ghodrat on 11/29/2016.
 */
public class Node <E extends Comparable<E>>
{
    private E value;
    private Node<E> left, right, parent;
    Node(E data)
    {
        left = null;
        right = null;
        parent = null;
        this.value=data;
    }
    E getValue(){
        return value;
    }
    Node<E> getLeft(){
        return left;
    }
    Node<E> getRight(){
        return right;
    }

    void setValue(E data){
        value=data;
    }
    void setLeft(E data){
        left = new Node<E>(data);
        left.value=data;
    }

    void setRight(E data){
        right = new Node<E>(data);
        right.value=data;
    }

    Node<E> getParent()
    {
        return parent;
    }
    void setParent(Node<E> parent)
    {
        this.parent=parent;

    }
}
