/*
 * Class: CMSC214 
 * Instructor: Prof Mark Estep
 * Description: A program that uses scroll bars to select the foreground color for a label
 * Due: 09/27/2018
 * I pledge that I have completed the programming assignment independently.
   I have not copied the code from a student or any source.
   I have not given my code to any student.
   Print your name: Derya Ozdemir Kurin   
*/
//
//Write a program that uses scroll bars to select the foreground color 
//for a label, as shown in the following figure. 
//Three horizontal scroll bars are used for selecting 
//the red, green, and blue components of the color. 
//Use a title border on the panel that holds the scroll bars.


package o_Kurin_D_Project03_16;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;


public class SelectingColors extends Application {
	//Date field
    //Color properties by default black
	private double red = 0.0;
	private double green = 0.0;
	private double blue = 0.0;
	private double opacity = 1.0;
	private Label lb = new Label("Show Colors");
	ScrollBar sbRed = new ScrollBar();
	ScrollBar sbGreen = new ScrollBar();
	ScrollBar sbBlue = new ScrollBar();
	ScrollBar sbOpacity = new ScrollBar();
	
	//Setting the properties for the ScrollBars
	private double max = 1.0;
	private double min = 0.0;
	private double blockIncrement = 0.1;
	private double unitIncrement = 0.1;

	
	
	@Override
	public void start(Stage primaryStage) {	
		//Setting Orientation for ScrollBars
		
		sbRed.setOrientation(Orientation.HORIZONTAL);
		sbRed.setOrientation(Orientation.HORIZONTAL);
		sbBlue.setOrientation(Orientation.HORIZONTAL);
		sbOpacity.setOrientation(Orientation.HORIZONTAL);
		
		//A grid pane to hold the scroll bars
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		
		
	
        //Adding scroll bars and texts to grid pane
		gp.add(new Text("Red"), 0,0);
		gp.add(sbRed, 1, 0);
		gp.add(new Text("Green"), 0,1);
		gp.add(sbGreen, 1, 1);
		gp.add(new Text("Blue"), 0,2);
		gp.add(sbBlue, 1, 2);
		gp.add(new Text("Opacity"), 0,3);
		gp.add(sbOpacity, 1,3);
	
		
		//Setting the properties of the scroll bars
		sbRed.setMax(max);
        sbRed.setMin(min);    
        sbGreen.setMax(max);
        sbGreen.setMin(min);
        sbBlue.setMax(max);
        sbBlue.setMin(min);   
        sbOpacity.setMax(max);
        sbOpacity.setMin(min);
        
        
        //Listeners for scroll bars  
        sbRed.valueProperty().addListener(e -> setColor());
        sbGreen.valueProperty().addListener(e -> setColor());
        sbBlue.valueProperty().addListener(e -> setColor());
        sbOpacity.valueProperty().addListener(e -> setColor());

		
		
		// A BorderPane to hold the Label and the Scroll Bars
		BorderPane bp = new BorderPane();

		bp.setTop(lb);
		BorderPane.setMargin(lb, new Insets(20, 0, 10, 0));
		BorderPane.setAlignment(lb, Pos.TOP_CENTER);
		bp.setCenter(gp);
		BorderPane.setAlignment(gp, Pos.CENTER);

		Scene scene = new Scene(bp,200,200);
    	primaryStage.setScene(scene);
		primaryStage.show();

	}
	
	// setColor method is for setting the Color properties based on the change on scroll bars
	private void setColor() {
		lb.setTextFill(new Color(sbRed.getValue(), sbGreen.getValue(), sbBlue.getValue(), sbOpacity.getValue()));
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
