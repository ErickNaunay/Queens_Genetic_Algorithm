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

//class that implements fisher yates shuffle to an array
public class Fisher_Yates_Array_Shuffling {
	
	//suffle method, receives an array with the initial condition
	static int[] suffle(int[] array_to_shuffle) {
		
		int length = array_to_shuffle.length;
		
		int[] a = new int[length];
        int[] ind = new int[length];
        
        for (int i = 0; i < length; i++) {
            ind[i] = 0;
        }
        
        SecureRandom rand = new SecureRandom();
        int index;
        
        for (int i = 0; i < length; i++) {
            do {
                index = rand.nextInt(length);
            } while (ind[index] != 0);

            ind[index] = 1;
            a[i] = array_to_shuffle[index];
        }
        
        return a.clone();
        
	}
}
