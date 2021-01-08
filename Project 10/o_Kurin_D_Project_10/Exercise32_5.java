/*
 * Class: CMSC214 
 * Instructor: Dr. Mark Estep
 * Description: (Display a running fan). Rewrite programming exercise 15.28 using a thread to control animation 
 * rather than using Timeline animation object.
 * Due: 11/15/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: _Derya O. Kurin__
   
*/



package o_Kurin_D_Project_10;
	
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Circle;


public class Exercise32_5 extends Application {
	class FanPane extends Pane {
	
		
		private double height = 150;
		private double width = 150;
		private double radius = Math.min(width,height) * 0.5;
	
		
		private Arc arc[] = new Arc[4];
		 
		private double startAngle = 30;
		
		private Circle circle = new Circle(width/2, height/2, radius);
		
		private long sleepDuration = 100;
		
		//Thread to control the animation
		private Thread thread = new Thread(() -> {
			try {
				//create an infinite loop for the Thread
				while(true) {
					Platform.runLater(() -> move());
					Thread.sleep(sleepDuration);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		
		// Method to resume the Thread
		public void play() {
			thread.resume();
		}
		
		// Method to pause the Thread
		public void pause() {
			thread.suspend();
		}
		
		public FanPane() {
			circle.setStroke(Color.BLACK);
			circle.setFill(Color.WHITE);
			
			getChildren().add(circle);
			
			//Creating 4 arches to display panels
			for(int i = 0; i < 4; i++) {
				arc[i] = new Arc(width/2, height/2, radius, radius, startAngle + i * 90, 45);
				arc[i].setFill(Color.RED);
				arc[i].setType(ArcType.ROUND);
				getChildren().addAll(arc[i]);
				
			}
			
			thread.start();
			
		}
		
		//
		private int moveIteration = 10;
		
		//Method to reverse the Fan
		public void reverse() {
			//Changes the direction of the movement
			moveIteration = -moveIteration;
		}
		
		//Method to move fan
		public void move() {
			//Changes the angles of the arches by reducing the starting angle
			setStartAngle(startAngle + moveIteration);
		}
		
		//Setter for startAngle
		public void setStartAngle(double startingAngle) {
			startAngle = startingAngle;
			updateSettings();
		}
		
		
		
		//Method to be called to update the circle and arch orientation with the changing width or height 
		public void updateSettings() {
			radius = Math.min(width,  height) * 0.5;
			circle.setRadius(radius);
			//Set x coordinate of the center
			circle.setCenterX(width/20);
			//Set y coordinate of the center
			circle.setCenterY(height/20);
			
			//Loop to set 4 separate arches to be displayed on the FanPane
			for(int i = 0; i < 4; i++) {
				arc[i].setRadiusX(radius);
				arc[i].setRadiusY(radius);
				//Set x coordinate of the center
				arc[i].setCenterX(width/20);
				//Set y coordinate of the center
				arc[i].setCenterY(height/20);
				// Each of them start with a different 
				//angle and with move or reverse update the agnles accordingly
				arc[i].setStartAngle(startAngle + i * 90);
		
			}
			
		}
		
		public void setWidth(double width) {
			this.width = width;
			updateSettings();
		}
		
		public void setHeight(double height) {
			this.height = height;
			updateSettings();
		}	
		
		
		
		
	}
	
	//Start method to start the Thread
	
	@Override 
	public void start(Stage primaryStage) {
	   FanPane fan = new FanPane();
	   
	   HBox buttons = new HBox();
	   
	   //Buttons to manipulate the animation
	   Button pause = new Button("Pause");
	   Button resume = new Button("Resume");
	   Button reverse = new Button("Reverse");
	   
	   buttons.setAlignment(Pos.CENTER);
	   buttons.getChildren().addAll(pause, resume, reverse);
	   
	   //Border Pane to hold the animation
	   BorderPane bPane = new BorderPane();
	  
	   bPane.setCenter(fan);
	   bPane.setBottom(buttons);
	   
	   Scene scene = new Scene(bPane, 300, 300);
	   primaryStage.setTitle("CMSC214 Project_10");
	   primaryStage.setScene(scene);
	   primaryStage.show();  
	   
	   //Setting width and height of fan depending on the scene size
	   scene.widthProperty().addListener(e -> fan.setWidth(fan.getWidth()));
	   scene.heightProperty().addListener(e -> fan.setHeight(fan.getHeight()));
	   
	   //Set pause action to pause button
	   pause.setOnAction(e -> fan.pause());
	   
	   //Set resume action to resume button
	   resume.setOnAction(e -> fan.play());
	   
	   //Set reverse action to reverse button
	   reverse.setOnAction(e -> fan.reverse());
	   
	}
	
	//Main method to launch the FX GUI
	public static void main(String[] args) {
       Application.launch(args);
    }
	
}
