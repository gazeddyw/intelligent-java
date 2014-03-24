/**
 * 
 */
package com.gareth;

import java.util.ArrayList;

/**
 * @author Gareth Williams
 * 
 * Each City is a 'gene' in the TSP algorithm
 */
public class City {

	private int cityNumber;
	private double xCoord;
	private double yCoord;
	private static ArrayList<City> cityList = new ArrayList<City>();
	
	public City() {}
	
	/**
	 * City Constructor.
	 * 
	 * @param number The number of the city in the file
	 * @param x The x coordinate of the city
	 * @param y The y coordinate of the city
	 */
	public City(int number, double x, double y) {
		cityNumber = number;
		xCoord = x;
		yCoord = y;
	}
	
	
	public int getCityNumber() {
		return cityNumber;
	}
	
	
	public double getXCoord() {
		return xCoord;
	}
	
	
	public double getYCoord() {
		return yCoord;
	}
	
	
	public static ArrayList<City> getCityList() {
		return cityList;
	}
	
	
	/**
	 * Takes in two cities, and calculates the distance between them.
	 * 
	 * @param a The first city
	 * @param b The second city
	 * @return
	 */
	public static double calculateCityDistance(City a, City b) {
		double cityDistance = 0;
		
		double ax = a.getXCoord();
		double ay = a.getYCoord();
		double bx = b.getXCoord();
		double by = b.getYCoord();
		
		double x = bx - ax;
		double y = by - ay;
		
		cityDistance = Math.sqrt((x * x) + (y * y));
		//System.out.println("DISTANCE: " + a.getCityNumber() + " to " + b.getCityNumber()
		//		+ " " + cityDistance);
		
		return cityDistance;
	}
	
}
