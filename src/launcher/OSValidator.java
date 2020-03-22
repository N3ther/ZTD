package launcher;

import java.time.Instant;

public class OSValidator {
	//C:\Program Files (x86)\Zone To Defend
	public static String installDir() {
		Instant instant = Instant.now();
		String OS = System.getProperty("os.name").toLowerCase();
		String installdir = null;
		if (isWindows()) {
			System.out.println(instant + " - [OSValidator] OS is Windows");
			String sysDrive = System.getenv("SystemDrive");
			if (sysDrive == null) {
				instant = Instant.now();
			    System.out.println(instant + " - [Critical] System drive cannot be found!");
			} else {
				instant = Instant.now();
				System.out.println(instant + " - [OSValidator] Drive is " + sysDrive);
			}
			installdir = sysDrive + "\\Program Files (x86)\\Zone To Defend";
		} else if (isMac()) {
			System.out.println(instant + " - [OSValidator] - OS is macOS");
			String sysDrive = System.getenv("SystemDrive");
			if (sysDrive == null) {
				instant = Instant.now();
			    System.out.println(instant + " - [Critical] System drive cannot be found!");
			} else {
				instant = Instant.now();
				System.out.println(instant + " - [OSValidator] Drive is " + sysDrive);
			}
			installdir = sysDrive + "\\Applications\\Zone To Defend";
		} else if (isUnix()) {
			System.out.println(instant + " - [OSValidator] - OS is Unix or Linux");
			String sysDrive = System.getenv("SystemDrive");
			if (sysDrive == null) {
				instant = Instant.now();
			    System.out.println(instant + " - [Critical] System drive cannot be found!");
			} else {
				instant = Instant.now();
				System.out.println(instant + " - [OSValidator] Drive is " + sysDrive);
			}
			installdir = sysDrive + "\\msc\\Zone To Defend";
		} else if (isSolaris()) {
			instant = Instant.now();
			System.out.println(instant + " - [Critical] Solaris is not currently supported due to me not knowing the file system. If you are reading this and aren't a dev, please DM me on discord, my name on there is N3#0889.");
		} else {
			instant = Instant.now();
			System.out.println(instant + " - [Critical] Your OS, " + OS + " is not supported!");
		}
		return installdir;
		
	}
	
	public static boolean isWindows() {
		String OS = System.getProperty("os.name").toLowerCase();

		return (OS.indexOf("win") >= 0);

	}

	public static boolean isMac() {
		String OS = System.getProperty("os.name").toLowerCase();

		return (OS.indexOf("mac") >= 0);

	}

	public static boolean isUnix() {
		String OS = System.getProperty("os.name").toLowerCase();

		return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );
		
	}

	public static boolean isSolaris() {
		String OS = System.getProperty("os.name").toLowerCase();

		return (OS.indexOf("sunos") >= 0);

}
}
