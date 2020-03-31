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

public class SettingsSaver {
	public static void Saver(String varName, String dir, String fileName, Number oldValue, Number value) throws IOException {
		
		//Checks if saver values are set or not
		Instant instant = Instant.now();
		if (varName != null) {
			instant = Instant.now();
			System.out.println(instant + " - [SettingsSaver] [Error] Variable varName not set, defaulting to volume");
			varName = "volume";
		}
		
		if (dir != null) {
			instant = Instant.now();
			System.out.println(instant + " - [SettingsSaver] [Error] Variable dir not set, defaulting to " + OSValidator.installDir());
			dir = OSValidator.installDir();
		}
		
		if (fileName != null) {
			instant = Instant.now();
			System.out.println(instant + " - [SettingsSaver] [Error] Variable fileName not set, defaulting to prefs.ztdp");
			fileName = "prefs.ztdp";
		}
		
		File check = new File(dir + fileName);
		boolean fileExists = check.exists();
		if (fileExists = false) {
			PrintWriter writer = new PrintWriter(fileName, "UTF-8");
		}
		
		Path dirDir = Paths.get(dir);
		
		List<String> fileContent = new ArrayList<>(Files.readAllLines(dirDir, StandardCharsets.UTF_8));

		for (int i = 0; i < fileContent.size(); i++) {
		    if (fileContent.get(i).equals(varName + oldValue)) {
		        fileContent.set(i, varName + value);
		        break;
		    }
		}
		
		

		Files.write(dirDir, fileContent, StandardCharsets.UTF_8);
		
		
	}
	
	

}
