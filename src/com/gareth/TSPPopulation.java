/**
 * 
 */
package com.gareth;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gareth Williams
 *
 */
public class TSPPopulation {
	
	private int populationSize;
	private int initialPopulationSize;
	private int tournamentSize;
	private int chromosomeSize;
	private int crossoverRate;
	private int mutationRate;
	
	private List<TSPChromosome> population;
	
	/**
	 * Instantiates 'population' as an ArrayList. The boolean flag used in 
	 * population.add tells the TSPChromosome to generate a random tour of cities
	 */
	public void initPopulation() {
		population = new ArrayList<TSPChromosome>();
		
		for (int i = 0; i < populationSize; i++) {
			population.add(new TSPChromosome(true));
		}
		
		System.out.println(population.size());
		for (int i = 0; i < population.size(); i++) {
			System.out.println(i + " " + population.get(i).getFitness());
		}
	}
	
	
	public void createPopulationList() {
		this.population = new ArrayList<TSPChromosome>();
	}
	
	
	public List<TSPChromosome> getPopulation() {
		return population;
	}
	
	
	/**
	 * Use Tournament Selection to select parents.
	 * Several chromosomes chosen at random. The chromosome with the highest fitness is selected each time.
	 */
	public int selectParent() {
		double fittestSoFar = 0;
		int strongestIndex = 0;
		for (int i = 0; i < tournamentSize; i++) {
			int randomParent = 0;
			
			randomParent = (int) (Math.random() * population.size());
			//System.out.println("randomParent " + randomParent);
			
			double temp = population.get(randomParent).getFitness();
			//System.out.println("TEMP " + temp);
			if (fittestSoFar == 0 || temp < fittestSoFar) {
				fittestSoFar = temp;
				strongestIndex = randomParent;
			}
			//System.out.println(fittestSoFar);
		}
		
		return strongestIndex;
	}
	
	public double getStrongestChromosome() {
		double fittestSoFar = 0;
		double strongestChromosome = 0;
		for (int i = 0; i < population.size(); i++) {
			fittestSoFar = population.get(i).getFitness();
			if (strongestChromosome == 0 || fittestSoFar < strongestChromosome) {
				strongestChromosome = fittestSoFar;
			}
		}
		
		return strongestChromosome;
	}
	
	public TSPChromosome getWeakestChromosome() {
		double weakestSoFar = 0;
		double weakestChromosome = 0;
		int weakestIndex = 0;
		for (int i = 0; i < population.size(); i++) {
			weakestSoFar = population.get(i).getFitness();
			if (weakestChromosome == 0 || weakestSoFar > weakestChromosome) {
				weakestChromosome = weakestSoFar;
				weakestIndex++;
			}
		}
		
		return population.get(weakestIndex);
	}

	/**
	 * @return the populationSize
	 */
	public int getPopulationSize() {
		return populationSize;
	}

	/**
	 * @param populationSize the populationSize to set
	 */
	public void setPopulationSize(int populationSize) {
		this.populationSize = populationSize;
	}

	/**
	 * @return the initialPopulationSize
	 */
	public int getInitialPopulationSize() {
		return initialPopulationSize;
	}

	/**
	 * @param initialPopulationSize the initialPopulationSize to set
	 */
	public void setInitialPopulationSize(int initialPopulationSize) {
		this.initialPopulationSize = initialPopulationSize;
	}

	/**
	 * @return the tournamentSize
	 */
	public int getTournamentSize() {
		return tournamentSize;
	}

	/**
	 * @param tournamentSize the tournamentSize to set
	 */
	public void setTournamentSize(int tournamentSize) {
		this.tournamentSize = tournamentSize;
	}

	/**
	 * @return the chromosomeSize
	 */
	public int getChromosomeSize() {
		return chromosomeSize;
	}

	/**
	 * @param chromosomeSize the chromosomeSize to set
	 */
	public void setChromosomeSize(int chromosomeSize) {
		this.chromosomeSize = chromosomeSize;
	}

	/**
	 * @return the crossoverRate
	 */
	public int getCrossoverRate() {
		return crossoverRate;
	}

	/**
	 * @param crossoverRate the crossoverRate to set
	 */
	public void setCrossoverRate(int crossoverRate) {
		this.crossoverRate = crossoverRate;
	}

	/**
	 * @return the mutationRate
	 */
	public int getMutationRate() {
		return mutationRate;
	}

	/**
	 * @param mutationRate the mutationRate to set
	 */
	public void setMutationRate(int mutationRate) {
		this.mutationRate = mutationRate;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(List<TSPChromosome> population) {
		this.population = population;
	}
	
}
