import com.lynden.gmapsfx.javascript.object.LatLong;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * This class will launch the program
 * It contains the main method
 * It is also responsible to design the windows
 * @author LuyiYang
 */
public class FluPane extends Application{

	
	Functions f;
	MapGenerator mg;
	//Stage window;
	//Button button;
	//ComboBox<String> locationMenu;
	
	/**
	 * This is the constructor
	 */
	public FluPane(){

		f = new Functions();
		mg = new MapGenerator();	
		
	}

	/**
	 * This method will start the scene
	 * If the online image's url is available, it will start with the first scene
	 * If the online image's url is somehow not working, the exception will be catch and it will start with the second scene
	 */
	@Override
	public void start(Stage primaryStage) {
		
		Stage window = primaryStage;
		window.setTitle("Flu Prediction");

		Scene secondScene = setSecondScene();
		
		
		
		Button getStart = new Button("Let's get started!");
		getStart.setOnAction(e -> window.setScene(secondScene));
		
		BorderPane firstSenceLayOut = new BorderPane();
		firstSenceLayOut.setBottom(getStart);
		BorderPane.setAlignment(getStart, Pos.CENTER);
		BorderPane.setMargin(getStart, new Insets(12, 12, 12, 12));

		ImageView fluImage = new ImageView();
		String imageRemoteUrl = "http://blog.londondrugs.com/wp-content/uploads/2016/01/cold-flu.jpg";
//		String remoteUrl = "httkdsgagnarituip://blog.londondrugs.com/wp-content/uploads/2016/01/cold-flue.jpg";
		
		try {
			
			Image remoteImage = new Image(imageRemoteUrl, true);
			fluImage.setImage(remoteImage);
			firstSenceLayOut.setCenter(fluImage);
			
			Scene firstSence = new Scene(firstSenceLayOut, 600, 400);
			window.setScene(firstSence);
			
		} catch (IllegalArgumentException e) {
			
			window.setScene(secondScene);
			
		}

		window.show();

	}

	/**
	 * This method will set out the second scene
	 * @return Scene
	 */
	private Scene setSecondScene() {
		
		ComboBox<String> locationMenu = new ComboBox<>();
		locationMenu.setPromptText("what is your current location?");
		locationMenu.getItems().addAll(f.getStates());
		locationMenu.setOnAction(e->{
			
			String state = locationMenu.getValue(); 
			f.setLocation(state);
			
			LatLong loc = new LatLong(f.getLocation(state).getLatitude(),f.getLocation(state).getLongitude());
			mg.setMarker(loc);
			mg.setCenter(loc);
			mg.setCircle(loc);
			mg.setZoom(6);
//			System.out.println(locationMenu.getValue());
		});
		
		Button checkFlu = new Button("Click here to check the flu");
		checkFlu.setOnAction(e->{
			boolean isFlu = false;
			try {
				isFlu = f.alert();
			} catch (LocationNotSelectedExeption e1) {
				
				ExceptionBox.display("OOPS!", "Please select your location first.", null);
				return;
			}
			String s;
			if(isFlu){
				s = "It is likely to be a flu soon! Be prepared!";
				
			}else{
				s = "It is not likely to be a flu soon! Relax but pay attention!";
				
			}
			AlertBox.display("Prediction Results", s, f.getkeyWordsCounts());
		});


		BorderPane secondSenceLayout = new BorderPane();
		
		BorderPane.setAlignment(checkFlu, Pos.CENTER);
		BorderPane.setMargin(checkFlu, new Insets(12, 12, 12, 12));
		secondSenceLayout.setBottom(checkFlu);
		
		BorderPane.setAlignment(locationMenu, Pos.CENTER);
		BorderPane.setMargin(locationMenu, new Insets(12, 12, 12, 12));
		secondSenceLayout.setTop(locationMenu);

		secondSenceLayout.setCenter(mg.getMapView());
		
		Scene secondSence = new Scene(secondSenceLayout, 600, 600);
		return secondSence;
	
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}

}
