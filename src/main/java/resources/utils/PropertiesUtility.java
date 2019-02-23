package resources.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author praveenkumar
 * This class contains the methods to read properties file data.
 */
public class PropertiesUtility {
	/**
	 * @param f
	 * @return properties
	 * This static method reads file properties data by file and returns.
	 */
	public static Properties readProperties(File f) {
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(f);
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * @param propertiesFilePath
	 * @return properties
	 * This static method reads file properties data by properties file path location and returns.
	 */
	public static Properties readProperties(String propertiesFilePath) {
		File file = new File(propertiesFilePath);
		Properties properties = new Properties();
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			properties.load(fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}

	/**
	 * @param inputStream
	 * @return properties
	 * This static method reads file properties data by inputstream and returns.
	 */
	public static Properties readProperties(InputStream inputStream) {
		Properties properties = new Properties();
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties;
	}
}
