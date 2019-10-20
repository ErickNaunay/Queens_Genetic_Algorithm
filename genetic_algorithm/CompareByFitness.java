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

import java.util.Comparator;

//Comparator for sorting the population with the fitness value
public class CompareByFitness implements Comparator<Chromosome>{
	
	//method implemented for sorting DESC
	@Override
	public int compare(Chromosome a, Chromosome b){
		return  b.getFitnessValue() - a.getFitnessValue();
	}

}
