/**
 * 
 */
package com.gareth;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Gareth Williams
 *
 */
public class Parser {
	
	private static Parser uniqueParser = new Parser();
	private String filePath = "";
	FileReader fr;
	BufferedReader buf;
	private String file = "";
	private boolean parse = false;
	
	private Parser() {}
	
	public static Parser getParser() {
		return uniqueParser;
	}
	
	
	/**
	 * Reads in a file, as given by the variable filePath.
	 * 
	 * @author Gareth Williams
	 */
	public void readFile() {
		try {
			fr = new FileReader(filePath);
			buf = new BufferedReader(fr);
			
			String s;
			while ((s = buf.readLine()) != null) {
				file += (s + '\n');
				if (parse == true) {
					if (s.equals("EOF")) {
						
					}
					else {
						addCity(s);
					}
				}
				else if (s.equals("NODE_COORD_SECTION")) {
					System.out.println(s);
					parse = true;
				}
			}
			fr.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Adds a City to the cityList ArrayList
	 * 
	 * @param line The line read in by readFile()
	 */
	public void addCity(String line) {
		String[] tokens = line.split(" ");
		City.getCityList().add(new City(Integer.parseInt(tokens[0]), 
				Double.parseDouble(tokens[1]), Double.parseDouble(tokens[2])));
	}
	
	
	public String getFile() {
		return file;
	}
	
	
	public void setFilePath(String path) {
		filePath = path;
	}
	
	
	public void resetParser() {
		file = "";
		parse = false;
	}
	
	
}
