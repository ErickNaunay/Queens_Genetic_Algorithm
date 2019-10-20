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

//Class for encode chromosome behavior
public class Chromosome {

	private int fitnessValue;
	
	//fitness probaility of selection
	private double fitnessPercentage;
	
	//state (genes) of chromosome
	private int[] state;
	
	//initial state for shuffling
	private int[] initState = {1,2,3,4,5,6,7,8};
	
	//Constructors
	public Chromosome() {
		setFitnessValue(0);
		state = Fisher_Yates_Array_Shuffling.suffle(initState);
	}
	
	public Chromosome(int[] state) {
		setFitnessValue(0);
		setState(state);
	}
	
	//getters and setters
	public int getFitnessValue() {
		return fitnessValue;
	}
	public void setFitnessValue(int fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
	public double getFitnessPercentage() {
		return fitnessPercentage;
	}

	public void setFitnessPercentage(double fitnessPercentage) {
		this.fitnessPercentage = fitnessPercentage;
	}

	public int[] getState() {
		return state;
	}
	public void setState(int[] state) {
		this.state = state.clone();
	}
	
	//print method
	@Override
	public String toString() {
		
		String printStmt = String.format("[%d, %d, %d, %d, %d, %d, %d, %d] => fitness: %d, %f%%",
				state[0], state[1], state[2], state[3], state[4], state[5], state[6], state[7],
				getFitnessValue(), getFitnessPercentage());
		
		return printStmt;
	}
	
}
