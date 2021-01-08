/*
 * Class: CMSC214 
 * Instructor: Dr. Mark Estep
 * Description: The client in (b) sends the annual interest rate, number of years, 
 * and loan amount to the server and receives the monthly payment and total payment from the server in (a).
 * This file is for Server 
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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Exercise_31_1Server extends Application {

    @Override
    public void start(Stage primaryStage) {
        TextArea ta = new TextArea();
        
        Scene scene = new Scene(new ScrollPane(ta), 400, 150);
        primaryStage.setTitle("Project_10_Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        new Thread(() -> {
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> ta.appendText("Project_10_Server started at " + new Date() + "\n"));
                
                Socket socket = serverSocket.accept();
                Platform.runLater(() -> ta.appendText("Connected to a client at " + new Date() + "\n"));
                
                //Create streams for input and output data transfer
                DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
                DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());
                
                //Create an infinite loop
                while(true) {
                    double annualRate = inputFromClient.readDouble();
                    int noOfYears = inputFromClient.readInt();
                    double loanAmount = inputFromClient.readDouble();
                    //Append the data to text area when the thread is available
                    Platform.runLater(() -> ta.appendText("Annual interest rate: " + annualRate + "\n" + 
                            "Number of Years: " + noOfYears + "\n" + "Loan amount: " + loanAmount + "\n"));
                    
                    Loan loan = new Loan(annualRate, noOfYears, loanAmount);
                    
                    double monthlyPayment = loan.calculateMonthlyPayment();
                    double totalPayment = loan.calculateTotalPayment();
                    
                    outputToClient.writeDouble(monthlyPayment);
                    outputToClient.writeDouble(totalPayment);
                    //Append the data to text area when the thread is available
                    Platform.runLater(() -> ta.appendText("Monthly Payment: " + monthlyPayment + "\n" + 
                            "Total Payment: " + totalPayment));
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}