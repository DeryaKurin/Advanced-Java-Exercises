/*
 * Class: CMSC214 
 * Instructor: Mark Estep
 * Description: Modify Listing 25.8, BSTAnimation.java (see attachment), to add three new buttons Show Inorder, Show Preorder, and Show Postorder to display the result in a label, as shown in the following figure. You need also to modify BST.java to implement the inorder(), preorder(), and postorder() methods so that each of these methods returns a List of node elements in inorder, preorder, and postorder, as follows:
	public java.util.List<E> inorderList();
	public java.util.List<E> preorderList();
	public java.util.List<E> postorderList();
 * Due: 1/11/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: __Derya Ozdemir Kurin__
*/


package o_Kurin_D_Project_8;

import java.util.Collection;

public interface Tree<E> extends java.util.Collection<E> {
    /**
     * Return true if the element is in the tree
     */
    public boolean search(E e);

    /**
     * Insert element o into the binary tree
     * Return true if the element is inserted successfully
     */
    public boolean insert(E e);

    /**
     * Delete the specified element from the tree
     * Return true if the element is deleted successfully
     */
    public boolean delete(E e);

    /**
     * Get the number of nodes in the tree
     */
    public int getSize();

    /**
     * Inorder traversal from the root
     */
    public default void inorder() {
    }

    /**
     * Postorder traversal from the root
     */
    public default void postorder() {
    }

    /**
     * Preorder traversal from the root
     */
    public default void preorder() {
    }

    @Override
    /** Return true if the tree is empty */
    public default boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public default boolean contains(Object e) {
        return search((E) e);
    }

    @Override
    public default boolean add(E e) {
        return insert(e);
    }

    @Override
    public default boolean remove(Object e) {
        return delete((E) e);
    }

    @Override
    public default int size() {
        return getSize();
    }

    @Override
    public default boolean containsAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean addAll(Collection<? extends E> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean removeAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default boolean retainAll(Collection<?> c) {
        // Left as an exercise
        return false;
    }

    @Override
    public default Object[] toArray() {
        // Left as an exercise
        return null;
    }

    @Override
    public default <T> T[] toArray(T[] array) {
        // Left as an exercise
        return null;
    }
}