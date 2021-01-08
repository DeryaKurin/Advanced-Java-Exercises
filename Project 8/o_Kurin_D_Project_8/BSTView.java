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

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

public class BSTView extends Pane {
    private BST<Integer> tree = new BST<>();
    private double radius = 15; 
    private double vGap = 50; 

    BSTView(BST<Integer> tree) {
        this.tree = tree;
        setStatus("Tree is empty");
        setOrdering("No Ordering");
    }

    public void setStatus(String msg) {
        getChildren().add(new Text(20, 20, msg));
    }

    
    public void setOrdering(String msg) {
        displayTree();
        getChildren().add(new Text(getWidth()/20, 20, msg));
    }

    public void displayTree() {
        this.getChildren().clear(); 
        if (tree.getRoot() != null) {
            // Display tree recursively
            displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth()/4);

        }
    }

    /**
     * Display a subtree rooted at position (x, y)
     */
    private void displayTree(BST.TreeNode<Integer> root, double x, double y, double hGap) {

        // left becomes bottom
        if (root.left != null) {
            double yVal = vGap - (vGap / 4);
            // Draw a line to the left node
            Line left = new Line(x - hGap, y + vGap, x, y);
            getChildren().add(left);
            // Draw the left subtree recursively
            displayTree(root.left, x - hGap, y + vGap, hGap/2);
        }
        
        
        // right becomes top
        if (root.right != null) {
            // Draw a line to the right node
            getChildren().add(new Line(x + hGap, y + vGap, x, y));
            // Draw the right subtree recursively
            displayTree(root.right, x + hGap, y + vGap, hGap/2);
        }


        // Display a node
        Circle circle = new Circle(x, y, radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,
        new Text(x - 4, y + 4, root.element + ""));
    }
}