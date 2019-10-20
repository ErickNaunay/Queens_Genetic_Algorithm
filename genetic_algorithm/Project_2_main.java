//***************************************
// Artificial Intelligence - USFQ
//  		Project 2
//
//	AUTHORS: Erick Naunay
//			Jonathan Cazco
//			Andrea Porras
//
//**************************************

package genetic_algorithm;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;

public class Project_2_main {
	
	static final int numberPopulation = 30;
	
	public static void main(String[] args) {
		
		ArrayList<Chromosome> population = GeneratePopulation.generate(numberPopulation);

		//Set the fitness value fo each chromosome in the population, then sort it DESC
		GeneticAlgorithms.getFitnessOfPopulation(population);
		population.sort(new CompareByFitness());
		
		System.out.printf("Inital Population with %d chromosomes: \n", numberPopulation);
		
		//print of first generation of the first population
		int cont = 1;
		for(Chromosome value: population) {
			System.out.printf("[%d]: %s\n", cont, value);
			cont++;
		}
		
		//algorithm starts
		System.out.println("\nStart Algorithm: ");
		
		//set out the max bound of generation created
		int maxGenerations = 200000;
		int currentGeneration = 1;
		
		//probability of a gen selected for mutation
		double probMutation = 0.01;
		double sumProbMutation;
		
		//set clock of execution time
		Instant begin = Instant.now();
		
		// loop until the goal state is reached or if a maximum time is hit
		while (currentGeneration <= maxGenerations) {
			
			//start calculation fitness value, percentage with the 2 generation
			if (currentGeneration > 1) {
				
				GeneticAlgorithms.getFitnessOfPopulation(population);
				population.sort(new CompareByFitness());
				
			} else 
				System.out.println("\nAlgorithm working...");
			
			//verifies if the goal state has reached
			int[] isSolved = GeneticAlgorithms.fitEnough(population);
			
			//if the goal state is encounter
			if ( isSolved[0] == 1) {
				
				System.out.println("\nGoal state found: ");
				System.out.println(population.get(isSolved[1]));
				break;
				
			}
			
			//mutation variables
			sumProbMutation = 0;
			double random = new SecureRandom().nextDouble();
			
			//new population of new child chromosomes
			ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>(numberPopulation);
			
			//iterate over each chromosome in th epopulation
			for(int i = 0; i < population.size(); i++) {
				
				//select 2 parent with the roulette wheel selection algorithm
				Chromosome x = GeneticAlgorithms.RouletteWheelSelection(population);
				Chromosome y = GeneticAlgorithms.RouletteWheelSelection(population);
				
				//System.out.printf("Chromose x for [%d]: %s\n", i, x);
				//System.out.printf("Chromose y for [%d]: %s\n", i, y);
				
				//cross-over the two parents creating the new chromosome 
				Chromosome child = GeneticAlgorithms.Reproduce_crossover(x, y);
				
				//System.out.printf("Chromose child for [%d]: %s\n", i, child);
				
				//if a small probability then mutate child with the muatte method 
				sumProbMutation += probMutation;
				
				if (sumProbMutation >= random) {
					GeneticAlgorithms.mutate(child);
					sumProbMutation = 0;
					
					//System.out.printf("Chromose mutate-child for [%d]: %s\n", i, child);
				}
				
				//append into the nre population
				newPopulation.add(child);
				
			}
			
			//setting new population for each generation
			population.clear();
			population = newPopulation;
			
			currentGeneration++;
		}
		
		Instant end = Instant.now();
		
		System.out.println("\n\nStatistics:");
		System.out.printf("Number of generation created: %d\n", currentGeneration);
		
		if (Duration.between(begin, end).toSeconds() > 0)
			System.out.printf("Execution time of GA: %d seconds\n", Duration.between(begin, end).toSeconds());
		else
			System.out.printf("Execution time of GA: %d mili seconds\n", Duration.between(begin, end).toMillis());
		
		if (currentGeneration >= maxGenerations)
			System.out.println("Enough time has elapsed: solution not found.");
		
	}
	
}
