package genetic_algorithm;

import java.util.ArrayList;

//class that generates a population with n number of chromosomes
public class GeneratePopulation {
	
	//generate method for chromosomes
	static ArrayList<Chromosome> generate(int numberChromosomes) {
		
		ArrayList<Chromosome> population = new ArrayList<Chromosome>(numberChromosomes);
		
		for(int i = 0; i < numberChromosomes; i++) {
			population.add(new Chromosome());
		}
		
		return population;		
		
	}
	
}
