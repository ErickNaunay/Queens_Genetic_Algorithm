package genetic_algorithm;

import java.security.SecureRandom;
import java.util.ArrayList;

//Class that contains all the algorithms for the GA
public class GeneticAlgorithms {
	
	//calculates the fitness function of a chromosome of the population
	static void getFitnessOfPopulation(ArrayList<Chromosome> population) {
		
		double totalFitness = 0;
		final int totalPairs = 28;
		
		//iterates over all the population
		for (Chromosome value: population) {
			
			int stateLength = value.getState().length;
			int[] state = value.getState();
			
			int attackingPairs = 0;
			int fitness = 0;
			
			//diagonal and horizontal attacking
			for (int i = 0; i < stateLength; i++) {
				
				int k = 1;
				for (int j = i + 1; j < stateLength; j++) {
					
					if (state[i] == state[j])
						attackingPairs++;
					
					if (state[i]+ k == state[j])
						attackingPairs++;
					if (state[i]- k == state[j])
						attackingPairs++;
					
					k++;
				}
				
			}
			
			//the fitness function uses all the non-attacking pairs;
			fitness = totalPairs - attackingPairs;
			value.setFitnessValue(fitness);
			totalFitness += fitness;
		}
		
		//calculates and set the fitness probability of solection for the next step
		for(Chromosome value: population) {
			value.setFitnessPercentage(value.getFitnessValue()/totalFitness);
		}
		
	}
	
	//algorithm used for the selection process of chromosome to be parents in the GA
	static Chromosome RouletteWheelSelection(ArrayList<Chromosome> population) {
		
		SecureRandom random = new SecureRandom();
		
		//probability of selection
		double prob = random.nextDouble();
		
		double sum = 0;
		
		//iterates over all the population, until the accumulative sum reaches the random
		for(int i = 0; i < population.size(); i++) {
			
			sum += population.get(i).getFitnessPercentage();
			
			if (prob < sum)
				return population.get(i);
			
		}
		
		return null;
		
	}
	
	//function that calculate the locus and reproduce for the crossover between the chromosomes parents
	static Chromosome Reproduce_crossover(Chromosome x, Chromosome y) {
		
		Chromosome child;
			
		SecureRandom rand = new SecureRandom();
		
		int length = x.getState().length;
		
		int locus = rand.nextInt(length-1);
		
		int[] newState = new int[8];
		
		//append to arrays with a locus as division
		for(int i = 0; i < length; i++) {
			
			if (i <= locus)
				newState[i] = x.getState()[i];
			else 
				newState[i] = y.getState()[i];
		}
		
		//creates new chromosome
		child = new Chromosome(newState);
		
		return child;
		
	}
	
	//mutate method for mutea random gene with a random value
	static Chromosome mutate(Chromosome x) {
		
		int posToMutate = new SecureRandom().nextInt(8);
		int valueMutate = new SecureRandom().nextInt(8) + 1;
		
		x.getState()[posToMutate] = valueMutate;
		
		return x;
	}
	
	//method that verifies if any chromosme in the population has reached the fitness goal
	static int[] fitEnough(ArrayList<Chromosome> population) {
		
		//0: true/false if is there 1: if yes the position
		int[] tuple = {0,-1};
		
		//iterates over all
		for(int i = 0; i < population.size(); i ++) {
			
			if(population.get(i).getFitnessValue() == 28) {
				
				tuple[0] = 1;
				tuple[1] = i;
				
				return tuple;
			}
		}
		
		return tuple;
		
	}
	
}
