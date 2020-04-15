package launcher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class SettingsReader {
	
public static Double Reader(String varName, File fileName, Double result) throws IOException {
		
		//Checks if saver values are set or not
		Instant instant = Instant.now();
		if (varName == null) {
			instant = Instant.now();
			System.out.println(instant + " - [SettingsReader] [Error] Variable varName not set, defaulting to volume");
			varName = "volume";
		}
		
		if (fileName == null) {
			instant = Instant.now();
			System.out.println(instant + " - [SettingsSaver] [Error] Variable fileName not set, defaulting to " + OSValidator.installDir() + "\\prefs.ztdp");
			String stringToFileConvert = OSValidator.installDir() + "\\prefs.ztdp";
			File stringToFileConvert2 = new File(stringToFileConvert);
			fileName = stringToFileConvert2;
		}

		boolean fileExists = fileName.exists();
		if (fileExists = false) {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		}
		
		Path dir = Paths.get(fileName.toURI());
		
		List<String> fileContent = new ArrayList<>(Files.readAllLines(dir, StandardCharsets.UTF_8));

		for (int i = 0; i < fileContent.size(); i++) {
			String line = fileContent.get(i);
			String oldValue = line.replaceAll("[^\\d.]", "");
			result = Double.parseDouble(oldValue);
			instant = instant.now();
			System.out.println(instant + " - [Debug] SettingsSaver is looking for " + varName + oldValue + ".");
		    if (fileContent.get(i).equals(varName + oldValue)) {
		    	instant = instant.now();
		    	System.out.println(instant + " - [Debug] varName is " + varName + ", oldValue is " + oldValue + ", value is " + ".");
		        break;
		    } else {
		    	instant = instant.now();
		    	System.out.println(instant + " - [Warn] Variable " + varName + " was not found in " + fileName + ".");
		    	System.out.println(instant + " - [Debug] varName is " + varName + ", oldValue is " + oldValue + ".");
		    }
		}
		
		

		return result;
		
		
	}

}
