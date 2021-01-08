/*
 * Class: CMSC214 
 * Instructor: Mark Estep
 * Description: A program in JavaFX that creates a car shape and simulates a car race with an animation
 * Due: 09/20/2020
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your Name here: Derya Ozdemir Kurin
*/


package o_kurin_D_Project02;


import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class RacingCar extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Create an instance of CarPane
		CarPane car = new CarPane();
	    
	    /**
	     * Buttons for changing the speed
	     */
		Button pause = new Button("Pause");
		Button play = new Button("Play");
		Button speedUp = new Button("Speed Up");
		Button slowDown = new Button("SlowDown");
		
		
		//Setting event handlers for buttons
	    pause.setOnAction(e -> car.pauseRace());
	    play.setOnAction(e -> car.resumeRace());
	    speedUp.setOnAction(e -> car.increaseSpeed());
	    slowDown.setOnAction(e -> car.decreaseSpeed());
	    
	     
	    
	    /**
	     * Horizontal Box for holding the buttons
	     */
	    HBox buttons = new HBox();
	    buttons.getChildren().addAll(pause, play, speedUp, slowDown);
	    
	    
	    /**
	     * BorderPane for holding all the elements 
	     */
	    BorderPane bp = new BorderPane();
	    
	   //Register event handler for changing the speed by pressing UP and DOWN keys
	    bp.setOnKeyPressed(e -> {
	    	KeyCode keyCode = e.getCode();
	    	if(keyCode.equals(KeyCode.UP)) {
	    		car.increaseSpeed();
	    	} else if(keyCode.equals(KeyCode.DOWN)) {
	    		car.decreaseSpeed();
	    	}
	    });
	    

	    
	    //Register event handlers for mouse click and released
	    bp.setOnMouseClicked(e -> car.pauseRace());
	    bp.setOnMouseReleased(e -> car.resumeRace());
	    
	    bp.setTop(buttons);
	    bp.setBottom(car);
		
		Scene scene = new Scene(bp, 400, 200);
		primaryStage.setTitle("CMSC214_Project02");
		primaryStage.setScene(scene);
		primaryStage.show();
		car.requestFocus();
		
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	
	class CarPane extends Pane {
		//Data fields for car shape group and the animation
		private double x = 0;
		private double y = 100;
		private double radius = 5;
		private Circle tire1;
		private Circle tire2;
		private Rectangle rectangle;
		private Polygon polygon;
		private Timeline race;	

		
		/**
		 *  Move the car in the race animation by recreating it at the new iterated coordinates
		 *  When it reaches at the end of the pane, it starts from the x coordinate: 0 
		 */
		protected void moveCar() {
			if(x <= this.getWidth()) {
				x++;
			} else {
				x = 0;
			}
			
			formCar();
		}
		
		/**
		 * CarPane constructor method is for creating the car and playing a race animation
		 */
		CarPane() {
			formCar();
			race = new Timeline(
					new KeyFrame(Duration.millis(15), e -> moveCar()));
			
			race.setCycleCount(Timeline.INDEFINITE);
			race.play();
		}
		
		//EventHandler methods
		
		/**
		 * formCar method is for forming the group that creates the car
		 */
		private void formCar() {
			//Clear the previous carPane shapes from the previous coordinates
			this.getChildren().clear();
			
			// Created the shapes with the given x and y coordinates
			rectangle = new Rectangle(x, y - 20, 50, 10);
			polygon = new Polygon();
			polygon.getPoints().addAll(x + 10, y - 20, x + 20, y - 30, x + 30, y - 30, x + 40, y - 20);
			tire1 = new Circle(x + 15, y - 5, radius);
			tire2 = new Circle(x + 35, y - 5, radius);
			
			
			//Coloring the shapes with colors
			polygon.setFill(Color.RED);
			rectangle.setFill(Color.GREEN);
			tire1.setFill(Color.BLACK);
			tire2.setFill(Color.BLACK);
			
			// Adding the shapes into the carPane
			this.getChildren().addAll(polygon, rectangle, tire1, tire2);
		}
		
		/**
		 * Pause the race
		 */
		public void pauseRace() {
			race.pause();
		}
		
		/**
		 * Resume the race
		 */
		public void resumeRace() {
			race.play();
		}
		
		
		/**
		 * Increase the car speed
		 */
		public void increaseSpeed() {
			race.setRate(race.getRate() + 1);
		}
		
		/**
		 * Decrease the car speed
		 */
		public void decreaseSpeed() {
			race.setRate(race.getRate() - 1);
		}
		
	}
	
	
}



