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

public class LauncherMain extends Application {
	public void start(final Stage primaryStage){
		Instant instant = Instant.now();
		System.out.println(instant + " - Starting up...");
		
		String version = "0.0.1a";
		instant = Instant.now();
		System.out.println(instant + " - Version: " + version);
		
		instant = Instant.now();
		System.out.println(instant + " - Creating launcher window...");
		primaryStage.setTitle("Zone to Defend Launcher");
		
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
                git.setX(primaryStage.getX() + 200.0);
                git.setY(primaryStage.getY() + 100.0);
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
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Creating debug checkbox...");
        CheckBox debugMode = new CheckBox();
        debugMode.setText("Debug Mode");
        instant = Instant.now();
        debugMode.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(final ActionEvent event) {
                if (debugMode.isSelected() == true) {
                    Boolean debug = true;
                }
               
                if (debugMode.isSelected() == false) {
                    Boolean debug = false;
                }
                
            }
        });
        
        instant = Instant.now();
        System.out.println(instant + " - [JavaFX] Rendering window...");
        VBox versions = new VBox(ver, blank1, debugMode);
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
        
	}
	 public static void main(final String[] args) {
	        Application.launch(args);
	    }
}
