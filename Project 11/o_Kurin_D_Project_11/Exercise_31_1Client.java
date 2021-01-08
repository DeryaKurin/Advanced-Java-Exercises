/*
 * Class: CMSC214 
 * Instructor: Dr. Mark Estep
 * Description: The client in (b) sends the annual interest rate, number of years, 
 * and loan amount to the server and receives the monthly payment and total payment from the server in (a).
 * This file is for Client 
 * Due: 11/22/2018
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Derya O. Kurin
*/




package o_Kurin_D_Project_11;
	
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Exercise_31_1Client extends Application {
	
	//Creating Stream objects for transferring data
    private DataInputStream fromServer = null;
    private DataOutputStream toServer = null;
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane bp = new BorderPane();
        
        TextArea ta = new TextArea();
        bp.setCenter(new ScrollPane(ta));
        
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        TextField tf3 = new TextField();        
        
        tf1.setPrefSize(200, 20);
	    tf2.setPrefSize(200, 20);
	    tf3.setPrefSize(200, 20);
	    
	    
        Button btn = new Button("Submit");
              
	    
	    
        Label lb1 = new Label("Annual Interest Rate ");
	    Label lb2 = new Label("Number of Years ");
	    Label lb3 = new Label("Loan Amount ");
	    
	    gp.add(lb1, 0,1);
	    gp.add(tf1, 1,1);
	    gp.add(lb2, 0,2);
	    gp.add(tf2, 1,2);
	    gp.add(lb3, 0,3);
	    gp.add(tf3, 1,3);
	    
	    
	    gp.add(btn, 2,2);
	    
	    
	    bp.setPadding(new Insets(25, 25, 25, 25));
	    bp.setCenter(new ScrollPane(ta));
	    bp.setTop(gp);
	    
        
        Scene scene = new Scene(bp, 300, 200);
        primaryStage.setTitle("Project_10_Client");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        try {
            Socket socket = new Socket("localhost", 8000);
            fromServer = new DataInputStream(socket.getInputStream());
            toServer = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException ex) {
            ta.appendText(ex.toString() + "\n");
        }
        
        btn.setOnAction(e -> {
            try {
            	//Getting the values from text fields
                double annualRate = Double.parseDouble(tf1.getText().trim());
                int noOfYears = Integer.parseInt(tf2.getText().trim());
                double loanAmount = Double.parseDouble(tf3.getText().trim());
                
                //Sending data to Server
                toServer.writeDouble(annualRate);
                toServer.writeInt(noOfYears);
                toServer.writeDouble(loanAmount);
                
                //Updating text area in client view
                ta.appendText("Annual interest rate: " + annualRate + "\n" + 
                            "Number of Years: " + noOfYears + "\n" + "Loan amount: " + loanAmount + "\n");
                
                //Retrieving calculated data from server
                double monthlyPayment = fromServer.readDouble();
                double totalPayment = fromServer.readDouble();
                
                //Updating the client view with new data
                ta.appendText("Monthly Payment: " + monthlyPayment + "\n" + 
                            "Total Payment: " + totalPayment + "\n");
            }
            catch (IOException ex) {
                System.err.println(ex);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

}
