import java.util.*;

public class CountingInversions {
	private ArrayList<ArrayList<Integer>> inversions;
	
	public static void main(String[] args) {
		CountingInversions inversionCounter = new CountingInversions();

		int[] array1 = {4, 3, 2, 1};	//6 inversions
		inversionCounter.mergeSort(array1);

		int[] array2 = {1, 3, 5, 2, 4, 6};	//3 inversions
		inversionCounter.mergeSort(array2);

		int[] array3 = {1, 20, 6, 4, 5};	//5 inversions
		inversionCounter.mergeSort(array3);

		int[] array4 = {5, 4, 3, 2, 1};	//10 inversions
		inversionCounter.mergeSort(array4);

		int[] array5 = {1, 2, 3, 4};	//0 inversions
		inversionCounter.mergeSort(array5);

		int[] array6 = {3, 3, 3, 3};	//0 inversions
		inversionCounter.mergeSort(array6);
	}

	//Public method to abstract away the recursive details of mergesort
	public int mergeSort(int[] array) {
		inversions = new ArrayList<ArrayList<Integer>>();		//reset the list of inversions from any previous call

		System.out.println("Original array: "+Arrays.toString(array));

		int[] tempArray = new int[array.length];
		int inversionCount = mergeSort(array, tempArray, 0, array.length - 1);		//Call the recursive mergeSort

		System.out.println("Inversion Count = "+inversionCount);
		if(!inversions.isEmpty()){
			System.out.println("Sorted array: "+Arrays.toString(array));
			System.out.println("Actual Inversions:");
			for(ArrayList<Integer> inversion : inversions){
				System.out.println(inversion);
			}
		}
		System.out.println();

		return inversionCount;
	}

	//Recursive mergeSort
	private int mergeSort(int[] array, int[] tempArray, int leftIndex, int rightIndex) {
		int inversionCount = 0;
		if( leftIndex < rightIndex ) {
			int center = ( leftIndex + rightIndex ) / 2;
			inversionCount += mergeSort( array, tempArray, leftIndex, center );			//left inversions
			inversionCount += mergeSort( array, tempArray, center + 1, rightIndex );	//right inversions
			inversionCount += mergeAndCount( array, tempArray, leftIndex, center + 1, rightIndex );		//split inversions
		}
		return inversionCount;
	}

	//Merges 2 sub-arrays & counts split inversions inversions between the 2
	private int mergeAndCount(int[] array, int[] tempArray, int leftPos, int rightPos, int rightEnd) {
		int inversionCount = 0;

		int center = rightPos;
		
		int leftEnd = rightPos - 1;
		int tempPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while( leftPos <= leftEnd && rightPos <= rightEnd ){
			if( array[leftPos] <= array[rightPos]){
				tempArray[ tempPos++ ] = array[ leftPos++ ];
			}
			else{	//Value from Right is greater than value from Left, we have as many inversions as elements remaining in left half
				tempArray[ tempPos++ ] = array[ rightPos++ ];
				inversionCount += center-leftPos;		//Most important line
				//Loop over the rest of the left half & find the actual numbers causing inversions with 1 constant number from the right half
				for(int i=leftPos; i<=leftEnd; i++){		//Bounds loop over the left half
					inversions.add(new ArrayList<Integer>(Arrays.asList(array[i], array[rightPos-1])));		//rightPos-1 because rightPos was incremented in line "tempArray[ tempPos++ ] = array[ rightPos++ ];", so the actual number from the right half is is 1 less then where the variable currently points
				}
			}
		}
		
		
		while( leftPos <= leftEnd ){		// Copy rest of first half
			tempArray[ tempPos++ ] = array[ leftPos++ ];
		}

		while( rightPos <= rightEnd ){		// Copy rest of right half
			tempArray[ tempPos++ ] = array[ rightPos++ ];
		}

		for( int i = 0; i < numElements; i++, rightEnd-- ){		// Copy tempArray back
			array[ rightEnd ] = tempArray[ rightEnd ];
		}

		return inversionCount;
	}

}