/**
 * 
 */
package com.gareth;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Gareth Williams
 *
 */
public class TSPChromosome {
	
	private double fitness;
	private List<City> chromosome = new ArrayList<City>();
	
	
	// CONSTRUCTORS //
	public TSPChromosome() {}
	
	public TSPChromosome(boolean generate) {
		//chromosome = new ArrayList<City>();
		generateChromosome(this);
	}
	
	
	public void addToChromosome(City a) {
		chromosome.add(a);
	}
	
	
	public List<City> getChromosome() {
		return this.chromosome;
	}
	
	/**
	 * Used by the orderedCrossover method to create a
	 * dummy chromosome to be added to
	 */
	private void initialiseChromosome() {
		for (int i = 0; i < City.getCityList().size(); i++) {
			chromosome.add(null);
        }
	}
	
	
	/**
	 * Generates a chromosome. In this case a random circuit of cities
	 * generated from cityList in class City. When a new instance of this
	 * class is created, a new chromosome is generated.
	 * 
	 * @param tspc Passed in from the constructor
	 */
	public void generateChromosome(TSPChromosome tspc) {
		
		@SuppressWarnings("unchecked")
		ArrayList<City> temp = (ArrayList<City>) City.getCityList().clone();
		
		for (int i = 0; i < temp.size(); ) {
			int random = (int) (Math.random() * temp.size());
			tspc.addToChromosome(temp.get(random));
			temp.remove(random);
		}
	}
	
	
	public double getFitness() {
		fitness = 0;
		
		for (int i = 0; i < chromosome.size(); i++) {
			if (i < chromosome.size() - 1) {
				fitness += City.calculateCityDistance(chromosome.get(i), chromosome.get(i + 1));
			}
			else if (i == chromosome.size() - 1) {
				fitness += City.calculateCityDistance(chromosome.get(i), chromosome.get(0));
			}
			
		}
		
		return fitness;
	}
	
	
	/**
	 * This method uses ordered crossover to achieve reproduction. 
	 * The first for loop gets a contiguous sub-tour from the first parent, and copies it to child.
	 * Second for loop adds all the cities to child which are not included in a's sub-tour.
	 * 
	 * NOTE: This algorithm has been adapted from:
	 * www.theprojectspot.com/tutorial-post/applying-a-genetic-algorithm-to-the-travelling-salesman-problem/5
	 * 
	 * @param a The first parent chromosome
	 * @param b The second parent chromosome
	 * @return The new child 
	 */
	public TSPChromosome orderedCrossover(TSPChromosome a, TSPChromosome b) {
		System.out.println("Crossing Over");
		
		TSPChromosome child = new TSPChromosome();
		child.initialiseChromosome();
		
		int randomStart = (int) (Math.random() * a.getChromosome().size());
        int randomEnd = (int) (Math.random() * a.getChromosome().size());
        
        for (int i = 0; i < child.getChromosome().size(); i++) {
        	
            if ((randomStart < randomEnd) && (i > randomStart) && (i < randomEnd)) 
                child.getChromosome().set(i, a.getCity(i));
            
            else if (randomStart > randomEnd) {
                
            	if (!((i < randomStart) && (i > randomEnd)))
                    child.getChromosome().set(i, a.getCity(i));
            }
        }
            
        for (int i = 0; i < b.getChromosome().size(); i++) {
            if (!child.getChromosome().contains(b.getCity(i))) {
                for (int j = 0; j < child.getChromosome().size(); j++) {
                    if (child.getCity(j) == null) {
                        child.getChromosome().set(j, b.getCity(i));
                        break;
                    }
                }
            }
        }
                
        return child;
	}
		

	/**
	 * Uses random swap mutation to exchange the position of two cities in a chromosome
	 */
	public void mutate() {
		System.out.println("Mutating");
		int rIndex1 = 0;
		int rIndex2 = 0;
		do { // loop prevents the same city being chosen for both indices
			rIndex1 = (int) (Math.random() * (chromosome.size()));
			rIndex2 = (int) (Math.random() * (chromosome.size()));
		} while (rIndex1 == rIndex2);
		
		swapCities(rIndex1, rIndex2);
	}
	
	
	/**
	 * Swaps two cities in a chromosome.
	 * 
	 * @param index1
	 * @param index2
	 */
	public void swapCities(int index1, int index2) {
		Collections.swap(this.chromosome, index1, index2);
	}
	
	
	/**
	 * Returns a city from the chromosome.
	 * 
	 * @param index
	 * @return The city specified by index
	 */
	public City getCity(int index) {
		
		return chromosome.get(index);
	}
	
}
