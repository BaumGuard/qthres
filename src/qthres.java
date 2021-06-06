import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;

//import org.jsoup.Jsoup;

public class qthres {

	static double latComp;
	static double longComp;
	static double distance;
	static double qthlat;
	static double qthlon;
	static String coordinates;

	static // Array for assigning the indexes
	String[] Capitals = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S",
			"T", "U", "V", "W", "X" };

	// Converting the qth locator to coordinates
	public static String Convert(String inputStr) {

		// Split the entered locator into the separate characters
		String[] split = inputStr.split("");

		// Assigning the index for upcoming operations
		double longitudeIndex = Arrays.binarySearch(Capitals, split[0].toUpperCase());
		double latitudeIndex = Arrays.binarySearch(Capitals, split[1].toUpperCase());

		// Declaration of indexes
		double longSmall;
		double latSmall;
		double longSmallIndex;
		double latSmallIndex;
		double longNumber;
		double latNumber;

		// Converting to degrees
		if (split.length <= 5) {
			longSmallIndex = 0;
			latSmallIndex = 0;
		} else {
			longSmallIndex = Arrays.binarySearch(Capitals, split[4].toUpperCase());
			latSmallIndex = Arrays.binarySearch(Capitals, split[5].toUpperCase());
		}

		double longCap = 20 * longitudeIndex;
		double latCap = 10 * latitudeIndex;

		if (split.length <= 3) {
			longNumber = 0;
			latNumber = 0;
		} else {
			longNumber = Float.parseFloat(split[2]) * 2;
			latNumber = Float.parseFloat(split[3]);
		}
		;

		longSmall = longSmallIndex * (1 / 12.0);
		latSmall = latSmallIndex * (1 / 24.0);

		latComp = latCap + latNumber + latSmall - 90;
		longComp = longCap + longNumber + longSmall - 180;
		latComp = Math.round(latComp * 10000) / 10000.0;
		longComp = Math.round(longComp * 10000) / 10000.0;

		coordinates = latComp + "," + longComp;
		// The output of the method "Convert" is a String with the format
		// latitude,longitude
		return coordinates;

	}

	public static String CoordsToLoc(double lat, double lon) {

		lon = lon + 180;
		lat = lat + 90;

		String Lon1Letter = Capitals[(int) Math.floor(lon / 20)];
		String Lat1Letter = Capitals[(int) Math.floor(lat / 10)];
		int LonNumber = (int) Math.floor((lon / 2) % 10);
		int LatNumber = (int) Math.floor(lat % 10);
		String Lon2Letter = Capitals[(int) Math.floor(((lon % 2) / 2) * 24)];
		String Lat2Letter = Capitals[(int) Math.floor(((lat % 1) * 24))];
		String loc = Lon1Letter + Lat1Letter + LonNumber + LatNumber + Lon2Letter + Lat2Letter;

		return loc;

	}

	// Method for calculating the distancetype filter text
	public static void Distance(double qhtlat, double qhtlon, double latCompRad, double longCompRad) {
		latCompRad = Math.toRadians(latComp);
		longCompRad = Math.toRadians(longComp);
		double qthlatRad = Math.toRadians(qthlat);
		double qthlonRad = Math.toRadians(qthlon);
		distance = (Math
				.acos(Math.sin(latCompRad) * Math.sin(qthlatRad)
						+ Math.cos(latCompRad) * Math.cos(qthlatRad) * Math.cos(qthlonRad - longCompRad))
				/ (2 * Math.PI)) * 40030;
		distance = Math.round(distance * 100) / 100.0;
	}

	public static boolean isNumeric(String strNum) {
		if (strNum == null) {
			return false;
		}
		try {
			double d = Double.parseDouble(strNum);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {

		// Help dialog
		if (args[0].equals("help") && args.length == 1) {

			System.out.println("qthres [locator/callsign/coordinates(lat,lon)] [options]");
			System.out.println();
			System.out.println("Options:");
			System.out.println("map: Display qth and distance on map");
			System.out.println("page: Open the webpage (qrzcq.com) of the callsign");
			System.out.println("imp: Convert to imperial units (miles)");
			System.out.println("nodist: Don't show distance, only coordinates");
			System.out.println("distonly: Only show distance, no coordinates");
			System.out.println("swap: Swap latitude and longitude (lon,lat)");
			System.out.println();
		} else {

			// Read users coordinates from file qth.txt
			LineNumberReader lineNumberReader = null;
			FileReader fileReader = null;
			String line = null;

			String user = System.getProperty("user.home");

			try {
				if (user.contains("/home")) {
					fileReader = new FileReader(user + "/qthres/qth.txt");
				} else {
					fileReader = new FileReader(user + "\\qthres\\qth.txt");
				}
				;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lineNumberReader = new LineNumberReader(fileReader);
			try {
				line = lineNumberReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (line.contains(",") == false) {
				line = Convert(line);

			}
			// Separating latitude and longitude
			String[] qthArr = line.split(",");

			// Assigning the qth's coordinates to variables

			String[] coordArray = null;
			qthArr = line.split(",");
			qthlat = Double.parseDouble(qthArr[0]);
			qthlon = Double.parseDouble(qthArr[1]);

			// Saving locator (argument) as String
			String inputStr = args[0];
			String[] inputArr = args[0].split("");
			String locator = "";
			String distStr = null;

			// Converting the array of the command into a list for better usage.
			List valid = Arrays.asList(args);

			if (args[0].contains(",")) {
				String[] CoordsArray = args[0].split(",");
				double LatConv = Double.parseDouble(CoordsArray[0]);
				double LonConv = Double.parseDouble(CoordsArray[1]);

				String LocConv = CoordsToLoc(LatConv, LonConv);
				System.out.println(LocConv);
			} else {

				if (inputArr.length==3 || (isNumeric(inputArr[2]) == false | isNumeric(inputArr[3]) == false) || inputArr.length>6) {

					// Downloading the callsign's page from qrzcq.com

					String url = "https://www.qrzcq.com/call/" + inputStr;
					String html = null;
					int csindex;

					try {
						html = Jsoup.connect(url).get().html();
					} catch (IOException e) {
						// TODO Auto-generated catch block
					}

					// Extracting the locator
					if (html.contains("locator supplied by user") == true) {
						csindex = html.indexOf("locator supplied by user");
						locator = html.substring(csindex + 26, csindex + 32);
					}

					else {
						csindex = html.indexOf("Locator");
						locator = html.substring(csindex + 44, csindex + 50);
					}

					Convert(locator);
					coordArray = coordinates.split(",");

					Distance(qthlat, qthlon, Double.parseDouble(coordArray[0]), Double.parseDouble(coordArray[1]));

				}
				
				if (inputArr.length<=6 && isNumeric(inputArr[2]) == true && isNumeric(inputArr[3]) == true) {

					locator = args[0];
				}
				

				if (args[0].equals("help") == false && args.length == 1) {
					Convert(locator);
					Distance(qthlat, qthlon, latComp, longComp);

					System.out.println(latComp + "," + longComp);
					System.out.println(distance + " km");
				}

				if (args[0].equals("help") == false && args.length > 1) {

					Convert(locator);
					Distance(qthlat, qthlon, latComp, longComp);

					if (valid.contains("swap")) {
						System.out.println(longComp + "," + latComp);
					} else {

						if (valid.contains("imp")) {
							distance = Math.round((distance * 0.6214) * 100) / 100.0;
							distStr = distance + " mi";
						}

						else {
							distStr = distance + " km";
						}
						;

						if (valid.contains("distonly")) {
							System.out.println(distStr);
						}
						if (valid.contains("nodist")) {
							System.out.println(latComp + "," + longComp);
						}

						if (valid.contains("page") && args.length == 2) {

							System.out.println(latComp + "," + longComp);
							System.out.println(distStr);

							Process p;
							try {
								if (user.contains("/home")) {
									p = Runtime.getRuntime().exec("firefox https://www.qrzcq.com/call/" + args[0]);
								} else {
									p = Runtime.getRuntime().exec(
											"C:\\/Program Files/\\/Mozilla Firefox/\\firefox.exe https://www.qrzcq.com/call/"
													+ args[0]);
								}
								;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}

						if (valid.contains("map")) {
							System.out.println(latComp + "," + longComp);
							System.out.println(distStr);

							Process p;
							try {
								if (user.contains("/home")) {
									p = Runtime.getRuntime()
											.exec("firefox https://www.luftlinie.org/" + line + "/" + coordinates);
								} else {
									p = Runtime.getRuntime().exec(
											"C:\\/Program Files/\\/Mozilla Firefox/\\firefox.exe https://www.luftlinie.org/"
													+ line + "/" + coordinates);
								}
								;
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						if (args[1].equals("imp") && args.length == 2) {
							System.out.println(coordinates);
							System.out.println(distStr);
						}

					}

				}
			}

		}

	}

}
