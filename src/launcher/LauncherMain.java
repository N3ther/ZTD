package launcher;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import java.time.Instant;
import launcher.OSValidator;

public class LauncherMain extends Application {
	
	private static long getTime() {
        return System.nanoTime() / 1000000;
    }
	
	
	
	
	
	public void start(final Stage primaryStage){
		
		Instant instant = Instant.now();
		System.out.println(instant + " - Checking instalation, please wait...");
		OSValidator.installDir();
		instant = Instant.now();
		System.out.println(instant + " - Starting up...");
		//Starts stopwatch for how long the launcher takes to boot up
		int startTime = (int) System.nanoTime();
		
		//Version is changed here, might add a check to an update server later
		String version = "0.0.3a";
		instant = Instant.now();
		System.out.println(instant + " - Version: " + version);
		
		//It says "Creating window" but in reality it just sets the title
		instant = Instant.now();
		System.out.println(instant + " - Creating launcher window...");
		primaryStage.setTitle("Zone to Defend Launcher");
		
		//Creates elements
		instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Creating play button...");
		Button playButton = new Button("Play!");
        playButton.setText("Play!");
        playButton.setStyle("-fx-font-size: 2em");
        playButton.setPrefSize(310.0, 100.0);
        instant = Instant.now();
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Creating github button...");
        Button githubButton = new Button("Github");
        githubButton.setText("GitHub");
        githubButton.setStyle("-fx-font-size: 2em");
        githubButton.setPrefSize(165.0, 50.0);
        githubButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                final WebView githubView = new WebView();
                final WebEngine gitEngine = githubView.getEngine();
                gitEngine.load("https://github.com/N3ther/ZTD");
                final VBox secondaryLayout = new VBox(githubView);
                final Scene secondScene = new Scene(secondaryLayout, 640.0, 480.0);
                final Stage git = new Stage();
                git.setTitle("Zone to Defend's GitHub Page");
                git.setScene(secondScene);
                git.setX(primaryStage.getX());
                git.setY(primaryStage.getY());
                git.setTitle("GitHub Browser");
                git.show();
            }
        });
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Creating exit button...");
        Button exit = new Button("Exit");
        exit.setText("Exit");
        exit.setStyle("-fx-font-size: 2em");
        exit.setPrefSize(165.0, 50.0);
        exit.setOnAction(e -> Platform.exit());
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Creating github vbox...");
        VBox githubExit = new VBox(githubButton, exit);
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Creating version text...");
        Text ver = new Text();
        ver.setText("Game Version: " + version);
        
        Text blank1 = new Text();
        Text blank2 = new Text();
        
        Text fpsText = new Text();
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Creating debug checkbox...");
        CheckBox debugMode = new CheckBox();
        debugMode.setText("Debug Mode");
        instant = Instant.now();
        debugMode.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
            	Boolean debug = false;
                if (debugMode.isSelected() == true) {
                    debug = true;
                    System.out.println("Debug mode set to " + debug);
                }
               
                if (debugMode.isSelected() == false) {
                    debug = false;
                    System.out.println("Debug mode set to " + debug);
                }
            }
        });
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Rendering window...");
        //Puts stuff all together
        VBox versions = new VBox(ver, blank1, debugMode, blank2, fpsText);
        HBox topBar = new HBox(playButton, versions, githubExit);
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        webEngine.load("http://ko-fi.com/zonetodefend");
        VBox root = new VBox(topBar, webView);
        Scene scene = new Scene(root, 600.0, 400.0);
        primaryStage.setScene(scene);
        primaryStage.show();
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Rendering done!");
        
        //Calculates an average of FPS, still trying to find a way that makes it just set the text as the FPS but its better than nothing
        instant = Instant.now();
        System.out.println(instant + " - Calculating FPS, please wait...");
        int fps1 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - First FPS value: " + fps1);
        int fps2 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Second FPS value: " + fps2);
        int fpsAvg1 = (fps1+fps2)/2;
        int fps3 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Third FPS value: " + fps3);
        int fps4 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Fourth FPS value: " + fps4);
        int fpsAvg2 = (fps3+fps4)/2;
        int fps5 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Fifth FPS value: " + fps5);
        int fps6 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Sixth FPS value: " + fps6);
        int fpsAvg3 = (fps5+fps6)/2;
        int fps7 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Seventh FPS value: " + fps7);
        int fps8 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Eighth FPS value: " + fps8);
        int fpsAvg4 = (fps7+fps8)/2;
        int fps9 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Ninth FPS value: " + fps9);
        int fps10 = (int) getTime();
        instant = Instant.now();
        System.out.println(instant + " - Tenth FPS value: " + fps10);
        int fpsAvg5 = (fps9+fps10)/2;
        int fpsAvg = (fpsAvg1+fpsAvg2+fpsAvg3+fpsAvg4+fpsAvg5)/5;
        fpsText.setText("FPS: " + fpsAvg);
        instant = Instant.now();
        System.out.println(instant + " - Average FPS Calculation done! FPS Average: " + fpsAvg);
        
        //ends stopwatch
        int endTime = (int) System.nanoTime();
        //converts nanoseconds into seconds, had to be a float due to ints not being able to have decimals last i checked
        float totalTime = (float) ((endTime-startTime)*0.000000001);
        instant = Instant.now();
        System.out.println(instant + " - Launcher done! Total time taken: " + totalTime + " seconds.");

        
	}
	


	 public static void main(final String[] args) {
		 	
		 	
	        Application.launch(args);
	        
	    }

}
