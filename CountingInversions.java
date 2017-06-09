import java.util.*;

public class CountingInversions {
	private static ArrayList<ArrayList<Integer>> inversions = new ArrayList<ArrayList<Integer>>();
	
	public static void main(String[] args) {
		CountingInversions inversionCounter = new CountingInversions();
		
		int[] array = {1, 20, 6, 4, 5};	//5 inversions
		// int[] array = {1,3,5,2,4,6};	//3 inversions
//		int[] array = {3,2,1};	//3 inversions
//		int[] array = {4,3,2,1};	//6 inversions
//		int[] array = {5,4,3,2,1};	//10 inversions
		// int[] array = {2, 4, 1, 3, 5};	//3 inversions
		// int[] array = {1, 2, 3, 4};	//0 inversions
		// int[] array = {3, 3, 3, 3};	//0 inversions
		System.out.println("Inversion count = "+CountingInversions.mergeSort(array));
		System.out.println(Arrays.toString( array ) );
		System.out.println("Actual Inversions");
		for(ArrayList<Integer> inversion : inversions){
			System.out.println(inversion);
		}
	}

	public static int mergeSort(int[] array) {
		int[] tempArray = new int[array.length];
		return mergeSort(array, tempArray, 0, array.length - 1);
	}

	/**
	 * Internal method that makes recursive calls.
	 * @param a an array of Comparable items.
	 * @param tempArray an array to place the merged result.
	 * @param left the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 */
	private static int mergeSort(int[] array, int[] tempArray, int leftIndex, int rightIndex) {
		int inversionCount = 0;
		if( leftIndex < rightIndex ) {
			int center = ( leftIndex + rightIndex ) / 2;
			inversionCount += mergeSort( array, tempArray, leftIndex, center );			//left inversions
			inversionCount += mergeSort( array, tempArray, center + 1, rightIndex );	//right inversions
			inversionCount += mergeAndCount( array, tempArray, leftIndex, center + 1, rightIndex );		//split inversions
		}
		return inversionCount;
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * @param a an array of Comparable items.
	 * @param tempArray an array to place the merged result.
	 * @param leftPos the left-most index of the subarray.
	 * @param rightPos the index of the start of the second half.
	 * @param rightEnd the right-most index of the subarray.
	 */
	private static int mergeAndCount(int[] array, int[] tempArray, int leftPos, int rightPos, int rightEnd) {
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