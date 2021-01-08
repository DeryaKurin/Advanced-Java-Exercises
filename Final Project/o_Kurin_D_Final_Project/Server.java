package o_Kurin_D_Final_Project;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class Server extends Application {
	private String medicine = "";
	private Statement stmt;
	private TextArea ta;
	private DataInputStream inputFromClient;
	private DataOutputStream outputToClient;
	
	@Override
    public void start(Stage primaryStage) {
		// Initialize database connection and create a Statement object
    	initializeDB();
    	
		ta = new TextArea();
        
        Scene scene = new Scene(new ScrollPane(ta), 500, 200);
        primaryStage.setTitle("Final Project Server");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        new Thread(() -> {
        	
            try {
                ServerSocket serverSocket = new ServerSocket(8000);
                Platform.runLater(() -> ta.appendText("Final Project started at " + new Date() + "\n"));
                
                Socket socket = serverSocket.accept();
                Platform.runLater(() -> ta.appendText("Connected to a client at " + new Date() + "\n"));
                
                //Create streams for input and output data transfer
                inputFromClient = new DataInputStream(socket.getInputStream());
                outputToClient = new DataOutputStream(socket.getOutputStream());
                
                //Create an infinite loop and read till end of the stream
                // Get the name of medicine from the client
                
                while(true) {
                	medicine = inputFromClient.readUTF();
  	              	Platform.runLater(() -> ta.appendText("Medicine name: " + medicine));
  	              
  	              	showInfo();
                }
                

            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }).start();
    }
	
	
	private void initializeDB() {
		try {
			// Load the JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			System.out.println("Driver loaded");
			
			// Establish a connection
			Connection connection = DriverManager.getConnection
	    	// Username: dozdemir, password: final
	        ("jdbc:mysql://localhost/medical_data?autoReconnect=true&useSSL=false", "dozdemir", "final");

			System.out.println("Database connected");

			// Create a statement
			stmt = connection.createStatement();
		}
		catch (Exception ex) {
			ex.printStackTrace();
	    }	
	}
	 
	
	private void showInfo() {

		  try {
			  // Create the sql query with the medicine name
			  String queryString = "SELECT Medical_Data.name, Medical_Data.dose, PregnancyCategory.category, Indications.health_condition, Contraindications.health_condition from Medical_Data "
			  		+ "JOIN PregnancyCategory ON Medical_Data.categoryId = PregnancyCategory.id "
			  		+ "JOIN Indications ON Medical_Data.indicationId = Indications.id "
			  		+ "JOIN Contraindications ON  Medical_data.contraindicationId = Contraindications.id "
			  		+ "WHERE name = '" + medicine + "'";
			  

			  // call executeQuery to place the query statment
			  ResultSet rset = stmt.executeQuery(queryString);
		  
			  //Use an if/else statement to save results in corresponding variables
			  if (rset.next()) {
				  //Get the response from rset and save them in String variables
				  String name = rset.getString(1);
				  String dose = rset.getString(2);
				  String categoryId = rset.getString(3);
				  String indicationId = rset.getString(4);
				  String contraindicationId = rset.getString(5);
				  
				  
				  //Set the text area with the received response data
				  ta.setText("Generic name: " + name + "\n" +
				  "Dose: " + dose  + "mg (max for adults per day)\n" +
			      "CategoryId: " + categoryId + "\n" +
				  "Indications: " + indicationId + "\n" +
			      "Contraindications: " + contraindicationId);
				  
				  try {
				  //write the sql query response in a formatted way to client 
				  outputToClient.writeUTF("Medicine name: " + name + "\n" +
						  "Dose: " + dose  + "mg (max for adults per day)\n" +
					      "CategoryId: " + categoryId + "\n" +
						  "Indications: " + indicationId + "\n" +
					      "Contraindications: " + contraindicationId);
				  //Clean the remaining bytes in the stream after the data is sent
				  outputToClient.flush();
				  
				  }  
				  catch(IOException ex) {
					  ex.printStackTrace();
				  }
				   
			  //When the sql query does not return a response inform the client
			  } else {
		      
				  ta.setText("Message: Medicine information not found");
			  } 
		  	} catch (SQLException ex) {
		  		ex.printStackTrace();
		  	}
	  	}
	 
	 

    public static void main(String[] args) {
        launch(args);
    }
}
