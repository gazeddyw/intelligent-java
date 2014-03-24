/**
 * 
 */
package com.gareth;

/**
 * @author Gareth Williams
 *
 */
public class GAController {

	private int numberOfGenerations = 0;
	private int strongestCutOff = 0;
	private long runningTime = 0;
	private long startTime = 0;
	private TSPChromosome tspc; 
	private TSPPopulation tspp;
	
	
	/**
	 * This method initialises and controls the Genetic Algorithm
	 *  
	 * @param initialPopulationSize The size of the population
	 * @param tournamentSize The tournament size
	 * @param crossoverRate The crossover rate to be used
	 * @param mutationRate The mutation rate to be used
	 */
	public void initTSPAlgorithm(int initialPopulationSize, int tournamentSize, 
			int crossoverRate, int mutationRate) {
		
		tspp = new TSPPopulation();
		tspc = new TSPChromosome();
		tspp.setPopulationSize(initialPopulationSize);
		tspp.setInitialPopulationSize(initialPopulationSize);
		tspp.setTournamentSize(tournamentSize);
		tspp.setCrossoverRate(crossoverRate);
		tspp.setMutationRate(mutationRate);
		
		TSPAppGui.getTextArea().setText(null);
		
		tspp.initPopulation();
		
		double strongestGlobalChromosome = tspp.getStrongestChromosome();
		
		startTime = System.currentTimeMillis();
		runningTime = 0;
		double runningTimeSeconds = getRunSecs();
		
		while ((strongestGlobalChromosome > strongestCutOff) && (runningTimeSeconds < 60.0)) {
			
			int strongestParent1 = 0;
			int strongestParent2 = 0;
			do {	// Ensures the same parent is not chosen twice
				strongestParent1 = tspp.selectParent();
				strongestParent2 = tspp.selectParent();
			} while (strongestParent1 == strongestParent2);
			
			int randCrossover = (int) (Math.random() * 100);
			if (randCrossover < tspp.getCrossoverRate()) {
				TSPChromosome tc1 = tspp.getPopulation().get(strongestParent1);
				TSPChromosome tc2 = tspp.getPopulation().get(strongestParent2);
				TSPChromosome child = tspc.orderedCrossover(tc1, tc2);
				
				tspp.getPopulation().remove(tspp.getWeakestChromosome());
				tspp.getPopulation().add(child);
				
			}
			System.out.println("Population Size: " + tspp.getPopulation().size() + "\n");
			
			int randMutate = (int) (Math.random() * 100);
			if (randMutate < tspp.getMutationRate()) {
				double fiftyfifty = (Math.random());
				if (fiftyfifty < 0.5)
					tspp.getPopulation().get(strongestParent1).mutate();
				else
					tspp.getPopulation().get(strongestParent2).mutate();
			}
			
			TSPAppGui.getTextArea().append("Generation " + numberOfGenerations + "\n");

			strongestGlobalChromosome = tspp.getStrongestChromosome();
			TSPAppGui.getTextArea().append(Double.toString(strongestGlobalChromosome) + "\n\n");
			
			
			
			runningTimeSeconds = getRunSecs();
			
			numberOfGenerations++;
		}
		System.out.println(runningTime);
		TSPAppGui.getTextArea().append("Running time (s): " + String.format("%.4f", getRunSecs()) + "\n\n");
		TSPAppGui.getTextArea().append("Number of Generations: " + numberOfGenerations);
	}
	
	
	public long getRunningTime() {
		runningTime = System.currentTimeMillis();
		runningTime = runningTime - startTime;
		
		return runningTime;
	}
	
	
	public double getRunSecs() {
		double temp = (double) getRunningTime();
		double t = (double) (temp / 1000);
		
		return t;
	}
	
	
	public void setCutOff(int strongestCutOff) {
		this.strongestCutOff = strongestCutOff;
	}
}
