import java.util.*;

public class CountingInversions {
	private static ArrayList<ArrayList<String>> inversions = new ArrayList<ArrayList<String>>();
	
	public static void main(String[] args) {
//		int[] array = {1, 20, 6, 4, 5};	//5 inversions
//		int[] array = {1,3,5,2,4,6};		//3 inversions
//		int[] array = {3,2,1};	//3 inversions
		int[] array = {5,4,3,2,1};	//10 inversions
		System.out.println("Inversion count = "+CountingInversions.mergeSort(array));
		System.out.println(Arrays.toString( array ) );
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
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		int center = rightPos;

		int inversionCount = 0;

		// Main loop
		while( leftPos <= leftEnd && rightPos <= rightEnd ){
			if( array[ leftPos ] <= array[rightPos]){
				tempArray[ tmpPos++ ] = array[ leftPos++ ];
			}
			else{	//Value from Right is greater than value from Left, we have as many inversions as elements remaining in left half
				tempArray[ tmpPos++ ] = array[ rightPos++ ];
				inversionCount += center-leftPos;		//Most important line.
			}
		}

		while( leftPos <= leftEnd ){		// Copy rest of first half
			tempArray[ tmpPos++ ] = array[ leftPos++ ];
		}

		while( rightPos <= rightEnd ){		// Copy rest of right half
			tempArray[ tmpPos++ ] = array[ rightPos++ ];
		}

		for( int i = 0; i < numElements; i++, rightEnd-- ){		// Copy tempArray back
			array[ rightEnd ] = tempArray[ rightEnd ];
		}

		return inversionCount;
	}

}