import java.util.ArrayList;
import java.util.HashMap;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;

import twitter4j.GeoLocation;
import javafx.geometry.*;
/**
 * This is a class to show more details about flu score by 
 * @author LuyiYang data visualization retrieved data.
 *
 */
public class FindMore {

	public static void display( ArrayList<HashMap<String, Integer>> keywordsCounts){
		
		//Functions f = new Functions();
/**
 * Build a new stage to hold this window.
 */
		Stage findMoreWindow = new Stage();
/**
 * set title for the box.	
 */
		findMoreWindow.initModality(Modality.APPLICATION_MODAL);
		findMoreWindow.setTitle("More about the flu");
		findMoreWindow.setMinWidth(400);

/**
 * set button to hold the action of going back.
 */
		Button closeButton = new Button("go back");
		closeButton.setOnAction (e->findMoreWindow.close());
/**
 * construct a bar chart to show the keywords data. 	
 */
	    CategoryAxis xAxis = new CategoryAxis();
	    NumberAxis yAxis = new NumberAxis();
        BarChart<String,Number> bc = 
            new BarChart<String,Number>(xAxis,yAxis);
        bc.setTitle("Flu Prediction Summary");
        xAxis.setLabel("Key Words");       
        yAxis.setLabel("Tweet Counts");
 
              
        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Recent Period");   
       
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Past Period");
 /**
  * use two for loops to present the real-time Twitter data, given the hashmaps.   
  */
        for(String keyWord: keywordsCounts.get(0).keySet()){
        	series1.getData().add(new XYChart.Data(keyWord, keywordsCounts.get(0).get(keyWord)));
           
        }
        
        for(String keyWord: keywordsCounts.get(1).keySet()){
            series2.getData().add(new XYChart.Data(keyWord, keywordsCounts.get(1).get(keyWord)));
               
        }
 
        bc.getData().addAll(series2, series1);
		
//      Text t = new Text();
//      t.setText("According to academic research paper, flu can be predicted by finding patterns of how people tweet. In our project, the sign of a upcoming flu is indicated by a 30% rising of flu score.");
//      t.setFont(new Font("Arial", 20));
//      t.setFill(Color.STEELBLUE);
//      t.setTextAlignment(TextAlignment.LEFT);
//		
/**
 * arrange the layout by setting center, bottom, aligment as well as margin.
 */
		BorderPane bp = new BorderPane();
		BorderPane.setAlignment(closeButton, Pos.CENTER);
		BorderPane.setMargin(closeButton, new Insets(12, 12, 12, 12));
//		
//		bp.setTop(t);
//		bp.setAlignment(t, Pos.CENTER);
		
		bp.setCenter(bc);
		bp.setAlignment(bc, Pos.CENTER);
		
		bp.setBottom(closeButton);
		bp.setAlignment(closeButton, Pos.CENTER);
//		
			
		Scene scene = new Scene(bp,500,500);
		findMoreWindow.setScene(scene);
		findMoreWindow.showAndWait();

	}

}
