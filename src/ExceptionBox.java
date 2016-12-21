import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import twitter4j.GeoLocation;
import javafx.geometry.*;
/**
 * This is the exception box, when the user did not select his or her location, this window will pop out
 * @author zhangxiang
 *
 */
public class ExceptionBox {
	
	/**
	 * This method will display the location not selected box
	 * @param title, title of the exception box
	 * @param message, a message saying that the location should be selected first
	 * @param tweets
	 */
	public static void display(String title, String message, ArrayList<String> tweets){
/**
 * Set a new stage to hold message, buttons and title.
 */
		Stage alertWindow = new Stage();
		//prevent user get back without taking care of the open window
		alertWindow.initModality(Modality.APPLICATION_MODAL);
		alertWindow.setTitle(title);
		alertWindow.setMinWidth(400);
/**
 * set new label, which is the message for conversation.
 */
		Label label = new Label();
		label.setText(message);
		/**
		 * add a button to go back to last page.
		 */
		Button closeButton = new Button("go back");
		closeButton.setOnAction (e->alertWindow.close());
/**
 * arrange the layout by VBox.
 */
		VBox layout = new VBox(10);
		layout.getChildren().addAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		layout.setMargin(closeButton, new Insets(5,6, 6, 5) );
		layout.setMargin(label, new Insets(5,6, 6, 5) );
/**
 * set scene to prompt the events.
 */
		Scene scene = new Scene(layout);
		alertWindow.setScene(scene);
		alertWindow.showAndWait();


	}

}