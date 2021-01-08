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

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.Iterator;

public class BSTAnimation extends Application {
    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        BST<Integer> tree = new BST<>(); // Create a tree

        BorderPane pane = new BorderPane();
        BSTView view = new BSTView(tree); // Create a View
        pane.setCenter(view);

        TextField tfKey = new TextField();
        tfKey.setPrefColumnCount(3);
        tfKey.setAlignment(Pos.BASELINE_RIGHT);
        Button btInsert = new Button("Insert");
        Button btDelete = new Button("Delete");
        Button btInOrder = new Button("In Order");
        Button btPreOrder = new Button("Pre Order");
        Button btPostOrder = new Button("Post Order");
        HBox hBox = new HBox(8);
        hBox.getChildren().addAll(new Label("Enter a key: "),
                tfKey, btInsert, btDelete, btInOrder, btPreOrder, btPostOrder);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);

        btInsert.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (tree.search(key)) { // key is in the tree already
                view.displayTree();
                view.setStatus(key + " is already in the tree");
            } else {
                tree.insert(key); // Insert a new key
                view.displayTree();
                view.setStatus(key + " is inserted in the tree");
            }
        });

        btDelete.setOnAction(e -> {
            int key = Integer.parseInt(tfKey.getText());
            if (!tree.search(key)) { // key is not in the tree
                view.displayTree();
                view.setStatus(key + " is not in the tree");
            } else {
                tree.delete(key); // Delete a key
                view.displayTree();
                view.setStatus(key + " is deleted from the tree");
            }
        });

        btInOrder.setOnAction(e -> {
            String ordering = "In Order: [ ";
            for (Integer i : tree) {
                ordering += i + " ";
            }
            view.setOrdering(ordering + "]");
        });

        btPreOrder.setOnAction(e -> {
            String ordering = "Pre Order: [ ";
            for (Iterator<Integer> it = tree.preorderIterator(); it.hasNext(); ) {
                ordering += it.next() + " ";
            }
            view.setOrdering(ordering + " ]");
        });

        btPostOrder.setOnAction(e -> {
            String ordering = "Post Order: [ ";
            for (Iterator<Integer> it = tree.postorderIterator(); it.hasNext(); ) {
                ordering += it.next() + " ";
            }
            view.setOrdering(ordering + " ]");
        });

        // Create a scene and place the pane in the stage
        Scene scene = new Scene(pane, 450, 250);
        primaryStage.setTitle("BSTAnimation"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
        scene.widthProperty().addListener(ov -> view.displayTree());
        scene.heightProperty().addListener(ov -> view.displayTree());

        // Base Tree for testing:
        int[] keys = {45, 12, 47, 56, 12, 10, 23, 13};
        for (int k : keys) tree.insert(k);
        view.displayTree();
    }
}