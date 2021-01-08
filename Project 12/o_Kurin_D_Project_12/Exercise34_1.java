/*
 * Class: CMSC214 
 * Instructor: Dr. Mark Estep
 * Description: *34.1 Accessing and updating a Staff table

		Write a JavaFX GUI program that views, inserts, and updates staff information stored in a database, as shown in the following figure. The view button displays a record with a specified ID. The Insert button inserts a new record. The Staff table is created as follows:
		create table Staff (  
		  id char(9) not null,
		  lastName varchar(15),
		  firstName varchar(15),
		  mi char(1),
		  address varchar(20),
		  city varchar(20),Staff In
		  state char(2),
		  telephone char(10),
		  email varchar(40),
		  primary key (id)
		);

 * Due: 12/06/2020
 * I pledge that I have completed the programming assignment independently.
 * I have not copied the code from a student or any source.
 * I have not given my code to any student.
 * Print your Name here: Derya Ozdemir Kurin
 */


package o_Kurin_D_Project_12;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.*;

public class Exercise34_1 extends Application {
    //Date Fields
	private TextField tfId = new TextField();
	private TextField tfLN = new TextField();
	private TextField tfFN = new TextField();
	private TextField tfMI = new TextField();
	private TextField tfAddress = new TextField();
	private TextField tfCity = new TextField();
	private TextField tfState = new TextField();
	private TextField tfPhone = new TextField();
	private TextField tfEmail = new TextField();
	private Label tfStatus = new Label();
	
	// Statement for executing queries
    private Statement stmt;
  
  
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
    	// Initialize database connection and create a Statement object
    	initializeDB();
    	
    	Button btnView = new Button("View");
    	Button btnInsert = new Button("Insert");
    	Button btnUpdate = new Button("Update");
    	Button btnClear = new Button("Clear");
    
    	HBox hBoxBtns = new HBox(5);
    	hBoxBtns.getChildren().addAll(btnView, btnInsert, btnUpdate, btnClear);
    
    	HBox dataRow1 = new HBox(5);
	    HBox dataRow2 = new HBox(5);
	    HBox dataRow3 = new HBox(5);
	    HBox dataRow4 = new HBox(5);
	    HBox dataRow5 = new HBox(5);
	    HBox dataRow6 = new HBox(5);
	    HBox dataRow7 = new HBox(5);
    
    
	    HBox title = new HBox();
	    
	    title.getChildren().add(new Label("Staff Information"));
	    
	    dataRow1.getChildren().addAll(new Label("ID"),tfId);
	    dataRow2.getChildren().addAll(new Label("Last Name"),tfLN, new Label("First Name"),tfFN, new Label("mi"),tfMI);
	    dataRow3.getChildren().addAll(new Label("Address"),tfAddress);
	    dataRow4.getChildren().addAll(new Label("City"),tfCity, new Label("State"),tfState);
	    dataRow5.getChildren().addAll(new Label("Telephone"),tfPhone);
	    dataRow6.getChildren().addAll(new Label("Email"),tfEmail);
	    dataRow7.getChildren().addAll(tfStatus);
	    

	    VBox vBox = new VBox(10);
	    
	    vBox.getChildren().addAll(title, dataRow1, dataRow2, dataRow3, dataRow4, dataRow5, dataRow6, hBoxBtns, dataRow7);
	    VBox.setMargin(title, new Insets(20,0,0,20));
	    VBox.setMargin(dataRow1, new Insets(10,0,0,20));
	    VBox.setMargin(dataRow2, new Insets(10,0,0,20));
	    VBox.setMargin(dataRow3, new Insets(10,0,0,20));
	    VBox.setMargin(dataRow4, new Insets(10,0,0,20));
	    VBox.setMargin(dataRow5, new Insets(10,0,0,20));
	    VBox.setMargin(dataRow6, new Insets(10,0,0,20));
	    VBox.setMargin(hBoxBtns, new Insets(10,0,0,20));
	    VBox.setMargin(dataRow7, new Insets(10,0,0,20));
    
	    //Set event listeners for the buttons
	    
	    btnView.setOnAction(e -> showStaff());
	    btnInsert.setOnAction(e -> insertStaff());
	    btnUpdate.setOnAction(e -> updateStaff());
	    btnClear.setOnAction(e -> clearStaff());
	    
	    // Create a scene and place it in the stage
	    Scene scene = new Scene(vBox, 700, 440);
	    primaryStage.setTitle("Project 12"); // Set the stage title
	    primaryStage.setScene(scene); // Place the scene in the stage
	    primaryStage.show(); // Display the stage   
    }
  
  
    private void initializeDB() {
	    try {
	      // Load the JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      System.out.println("Driver loaded");

	      // Establish a connection
	      Connection connection = DriverManager.getConnection
	    	// Username: dozdemir, password: staff
	        ("jdbc:mysql://localhost/project12?autoReconnect=true&useSSL=false", "dozdemir", "staff");

	      System.out.println("Database connected");

	      // Create a statement
	      stmt = connection.createStatement();
	    }
	    catch (Exception ex) {
	      ex.printStackTrace();
	    }
    }

    //Deletes the Staff with a particular id
    private void clearStaff() {
		  String id = tfId.getText();
	   
		  try {
			  String queryString = "DELETE FROM `staff` WHERE id = " + id;		
	
		  int rowsAffected = stmt.executeUpdate(queryString);
		  if(rowsAffected > 0) {
			  //Reset the fields in panel
			  tfLN.setText("");
			  tfFN.setText("");
	          tfMI.setText("");
	          tfAddress.setText("");
	          tfCity.setText("");
	          tfState.setText("");
	          tfPhone.setText("");
	          tfEmail.setText("");
	  
	          tfStatus.setText("Rows affected: " + rowsAffected + "\nDelete complete.");
		  } else {
			  tfStatus.setText("Rows affected: " + rowsAffected + "\nDelete not complete.");
		  }
	  } catch (SQLException ex) {
		  ex.printStackTrace();
	  }
	  
  }

   //Updates a staff with a particular id
  private void updateStaff() {
	  try {
		  String id = tfId.getText();
		  String lastName = tfLN.getText();
		  String firstName = tfFN.getText();
		  String mi = tfMI.getText();
		  String address = tfAddress.getText();
		  String city = tfCity.getText();
		  String state = tfState.getText();
		  String telephone = tfPhone.getText();
		  String email = tfEmail.getText();
	
	      
	      String insertedRow = "UPDATE Staff SET lastName = '" +  lastName + "', firstName = '" + firstName  + "', mi = '" + mi + "', address = '"  
	        			+ address  + "', city = '" + city  + "', state = '" + state  + "', telephone = '" + telephone  + "', email = '" + email + "' WHERE id = " + id;	
	      int rowsAffected = stmt.executeUpdate(insertedRow);
	        
	        //set the fields in panel
	      tfId.setText(id);
	      tfLN.setText(lastName);
	      tfFN.setText(firstName);
	      tfMI.setText(mi);
	      tfAddress.setText(address);
	      tfCity.setText(city);
	      tfState.setText(state);
	      tfPhone.setText(telephone);
	      tfEmail.setText(email);
	       
	      //We only can update 1 row if the query is successful
          if(rowsAffected == 1) {
        	  tfStatus.setText("Rows affected: " + rowsAffected + "\nUpdate complete.");
          } else {
        	  tfStatus.setText("Rows affected: " + rowsAffected + "\nUpdate not complete.");
          }
	      	 
	  } catch (SQLException ex) {
	    	  
	      ex.printStackTrace();
	  }	

  }
  
  
  // Inserts a new staff to the Staff table 
  private void insertStaff() {

	  try {
		  String id = tfId.getText();
		  String lastName = tfLN.getText();
		  String firstName = tfFN.getText();
		  String mi = tfMI.getText();
		  String address = tfAddress.getText();
		  String city = tfCity.getText();
		  String state = tfState.getText();
		  String telephone = tfPhone.getText();
		  String email = tfEmail.getText();

  
		  String insertedRow = "INSERT INTO Staff " + "VALUES ('" + id + "', '" +  lastName + "', '" + firstName  + "', '" + mi + "', '" 
        			+ address  + "', '" + city  + "', '" + state  + "', '" + telephone  + "', '" + email + "')";	
		  int rowsAffected = stmt.executeUpdate(insertedRow);
        
		  //set the fields in panel
		  tfId.setText(id);
		  tfLN.setText(lastName);
		  tfFN.setText(firstName);
		  tfMI.setText(mi);
		  tfAddress.setText(address);
		  tfCity.setText(city);
		  tfState.setText(state);
		  tfPhone.setText(telephone);
		  tfEmail.setText(email);
          
          if(rowsAffected == 1) {
        	  tfStatus.setText("Rows affected: " + rowsAffected + "\nInsert complete.");
          } else {
        	  tfStatus.setText("Rows affected: " + rowsAffected + "\nInsert not complete.");
          }
      	 
	  } catch (SQLException ex) {
	  
		  ex.printStackTrace();
	  }	
  }

  //Shows staff with a particular id
  private void showStaff() {
	  String id = tfId.getText();
	   
	  try {
		  String queryString = "SELECT * FROM `staff` WHERE id = " + id;		
	
		  ResultSet rset = stmt.executeQuery(queryString);
	  
		  //Use a while loop to save results in corresponding variables
		  if (rset.next()) {
			  //Get the response from rset and save them in String variables
			  String lastName = rset.getString(2);
			  String firstName = rset.getString(3);
			  String mi = rset.getString(4);
			  String address = rset.getString(5);
			  String city = rset.getString(6);
			  String state = rset.getString(7);
			  String telephone = rset.getString(8);
			  String email = rset.getString(9);
	
	
			  // Display result in the panel     
			  tfLN.setText(lastName);
			  tfFN.setText(firstName);
			  tfMI.setText(mi);
			  tfAddress.setText(address);
			  tfCity.setText(city);
			  tfState.setText(state);
			  tfPhone.setText(telephone);
			  tfEmail.setText(email);
			  tfStatus.setText("Message: Success");
		  } else {
	      
			  //Reset the fields to empty strings in the panel, if no staff found with that id
			  tfLN.setText("");
			  tfFN.setText("");
			  tfMI.setText("");
			  tfAddress.setText("");
			  tfCity.setText("");
			  tfState.setText("");
			  tfPhone.setText("");
			  tfEmail.setText("");
	  
			  tfStatus.setText("Message: Row not found");
		  } 
	  	} catch (SQLException ex) {
	  		ex.printStackTrace();
	  	}
  	}

  /**
   * The main method is only needed for the IDE with limited
   * JavaFX support. Not needed for running from the command line.
   */
  	public static void main(String[] args) {
  		launch(args);
  	}
}