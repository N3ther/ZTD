package launcher;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.swing.filechooser.FileSystemView;

import game.HelloLWJGL;
import game.Main;
import launcher.OSValidator;

public class LauncherMain extends Application {
	
	
	private static boolean isRedirected( Map<String, List<String>> header ) {
	      for( String hv : header.get( null )) {
	         if(   hv.contains( " 301 " )
	            || hv.contains( " 302 " )) return true;
	      }
	      return false;
	   }
	
	
	
	public void start(final Stage primaryStage) throws Exception{
		String checksum = "4152020l006a";
		
		List<String> lines = Arrays.asList(checksum);
		Path file = Paths.get("checksum.txt");
		Files.write(file, lines, StandardCharsets.UTF_8);
		//Files.write(file, lines, StandardCharsets.UTF_8, StandardOpenOption.APPEND);
		
		Instant instant = Instant.now();
		System.out.println(instant + " - Checking instalation, please wait...");
		OSValidator.installDir();
		
		/*
		URL website = new URL("http://www.website.com/information.asp");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("checksumNew.txt");
		try {
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		} catch (IOException e1) {
			instant = Instant.now();
			System.out.println(instant + " - [Error] Cannot download checksum from GitHub!");
			e1.printStackTrace();
		}
		
		
		boolean needUpdate = ChecksumReader.isUpdated();
		if (needUpdate == true) {
			try {
				GithubDownloader.main(null);
			} catch (Throwable e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			UnzipFile.main(null);
		}
		*/
		
		instant = Instant.now();
		System.out.println(instant + " - Starting up...");
		//Starts stopwatch for how long the launcher takes to boot up
		int startTime = (int) System.nanoTime();
		
		//Version is changed here, might add a check to an update server later
		String version = "0.0.6a";
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
		playButton.setOnAction(e -> Main.main(null));
        playButton.setText("Play!");
        playButton.setStyle("-fx-font-size: 2em");
        playButton.setPrefSize(310.0, 100.0);
        
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
        
        Button optionsButton = new Button("Options");
        optionsButton.setText("Options");
        optionsButton.setPrefSize(150.0, 50.0);
        optionsButton.setStyle("-fx-font-size: 2em");
        
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
        VBox versions = new VBox(ver, blank1, debugMode, blank2, optionsButton);
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
        
        /*
         * Options Screen
         */
        
        Button mainScreenButton = new Button();
        mainScreenButton.setText("Back to Launcher");
        mainScreenButton.setPrefSize(150.0, 50.0);
        mainScreenButton.setStyle("-fx-font-size: 1.3em");
        
        Slider volume = new Slider();
        File volumeSaverFile = new File(OSValidator.installDir() + "\\prefs.ztdp");
        volume.setMin(0);
        volume.setMax(100);
        Double initVolValue = SettingsReader.Reader("volume", volumeSaverFile, null);
        volume.setValue(initVolValue);
        volume.setShowTickLabels(true);
        volume.setMajorTickUnit(20);
        volume.setMinorTickCount(10);
        
        Text volumeText = new Text();
        volumeText.setText("Volume: " + initVolValue);
        
        CheckBox debugMode2 = new CheckBox();
        debugMode2.setText("Debug Mode");
        debugMode2.setOnAction(new EventHandler<ActionEvent>() {
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
        
        
        
        volume.valueProperty().addListener(new ChangeListener<Number>() {
        	public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
        			volumeText.setText("Volume: " + String.format("%.2f", new_val));
        			Number volumeValue = new_val;
        			@SuppressWarnings("static-access")
					Instant instant2 = instant.now();
        			try {
        				File volumeSaverFile2 = new File(OSValidator.installDir() + "\\prefs.ztdp");
						SettingsSaver.Saver("volume", volumeSaverFile2, new_val);
					} catch (IOException e) {
						System.out.println("IOException");
						e.printStackTrace();
					}
        			System.out.println(instant2 + " - [Debug] Volume: " + volumeValue);
        	}
        });
        VBox oRow1 = new VBox(mainScreenButton, volumeText, volume, blank1, debugMode2);
        VBox oRow2 = new VBox();
        VBox oRow3 = new VBox();
        HBox root2 = new HBox(oRow1, oRow2, oRow3);
        Scene options = new Scene(root2, 600.0, 400.0);
        
        
        //scene changer buttons
        optionsButton.setOnAction(e -> primaryStage.setScene(options));
        mainScreenButton.setOnAction(e -> primaryStage.setScene(scene));
        
        //ends stopwatch
        int endTime = (int) System.nanoTime();
        //converts nanoseconds into seconds, had to be a float due to ints not being able to have decimals last i checked
        float totalTime = (float) ((endTime-startTime)*0.000000001);
        instant = Instant.now();
        System.out.println(instant + " - Launcher done! Total time taken: " + totalTime + " seconds.");

        
	}
	


	 public static void main(final String[] args) throws Throwable {
		 	
		 {
		      String link =
		         "https://github.com/N3ther/ZTD/blob/master/" +
		         "test.zip";
		      String fileName = "\\test.zip";
		      URL url  = new URL( link );
		      HttpURLConnection http = (HttpURLConnection)url.openConnection();
		      Map< String, List< String >> header = http.getHeaderFields();
		      while( isRedirected( header )) {
		         link = header.get( "Location" ).get( 0 );
		         url = new URL( link );
		         http = (HttpURLConnection)url.openConnection();
		         header = http.getHeaderFields();
		      }
		      InputStream input  = http.getInputStream();
		      byte[] buffer = new byte[4096];
		      int n = -1;
		      File home = FileSystemView.getFileSystemView().getHomeDirectory();
		      
		      File file = new File(home.getAbsolutePath() + "\\Zone To Defend");
		      if (!file.exists()) {
		            if (file.mkdir()) {
		                System.out.println("Directory is created!");
		            } else {
		                System.out.println("Failed to create directory!");
		            }
		        }
		      
		      OutputStream output = new FileOutputStream( new File( home.getAbsolutePath() + "\\Zone To Defend" + fileName ));
		      while ((n = input.read(buffer)) != -1) {
		         output.write( buffer, 0, n );
		      }
		      output.close();
		   }
		 	
	        Application.launch(args);
	        
	    }

}
