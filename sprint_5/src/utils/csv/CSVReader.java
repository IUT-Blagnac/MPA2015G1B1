package utils.csv;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/**
 * CSVReader is a class created for reading a .csv file.
 * 
 * @see CSVWriter
 * 
 * @author Nicolas CALVET
 * @version 2.0
 */

public class CSVReader {

	/**
     * Return the data contains in the .csv file.
     *
     * @since 1.0
     * 
     * @param path The path for accessing the chosen file.
     *  
     * @return fileData - An ArrayList of arrays strings that represent the data contains in the .csv file.
	 *
	 * @throws FileNotFoundException if the file is not reachable
	 * @throws UnsupportedEncodingException if the file is not UTF-8 encoded
	 * @throws IOException if an error occurs while reading the file
     */
	
	public static ArrayList<String[]> fromFile(String path) 
			throws UnsupportedEncodingException, FileNotFoundException, IOException {
		
		String[] pathSplited = path.split("\\.");
		if(pathSplited.length == 1)
			path = path + ".csv";
		
		ArrayList<String[]> fileData = new ArrayList<String[]>();
				
		BufferedReader br = new BufferedReader(
									new InputStreamReader(
						                new FileInputStream(path), "UTF8"));
		
		String line;
		String[] lineSplited;

		while ((line=br.readLine())!=null) {
				
			lineSplited = line.split(";");
			fileData.add(lineSplited);
				
		}
		
		br.close();

		return fileData;
		
	}

}
