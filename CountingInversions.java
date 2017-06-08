import java.util.*;

public class CountingInversions {

	public static void main(String[] args) {
		int[] array = {3,2,1};
		CountingInversions.mergeSort(array);
		System.out.println(Arrays.toString( array ) );
	}

	public static void mergeSort(int[] array) {
		int[] tempArray = new int[array.length];
		mergeSort(array, tempArray, 0, array.length - 1);
	}

	/**
	 * Internal method that makes recursive calls.
	 * @param a an array of Comparable items.
	 * @param tempArray an array to place the merged result.
	 * @param left the left-most index of the subarray.
	 * @param right the right-most index of the subarray.
	 */
	private static void mergeSort(int[] array, int[] tempArray, int leftIndex, int rightIndex) {
		if( leftIndex < rightIndex ) {
			int center = ( leftIndex + rightIndex ) / 2;
			mergeSort( array, tempArray, leftIndex, center );
			mergeSort( array, tempArray, center + 1, rightIndex );
			merge( array, tempArray, leftIndex, center + 1, rightIndex );
		}
	}

	/**
	 * Internal method that merges two sorted halves of a subarray.
	 * @param a an array of Comparable items.
	 * @param tempArray an array to place the merged result.
	 * @param leftPos the left-most index of the subarray.
	 * @param rightPos the index of the start of the second half.
	 * @param rightEnd the right-most index of the subarray.
	 */
	private static void merge(int[] array, int[] tempArray, int leftPos, int rightPos, int rightEnd) {
		
		
		int leftEnd = rightPos - 1;
		int tmpPos = leftPos;
		int numElements = rightEnd - leftPos + 1;

		// Main loop
		while( leftPos <= leftEnd && rightPos <= rightEnd )
			if( array[ leftPos ] <= array[rightPos])
				tempArray[ tmpPos++ ] = array[ leftPos++ ];
			else
				tempArray[ tmpPos++ ] = array[ rightPos++ ];

		while( leftPos <= leftEnd )    // Copy rest of first half
		tempArray[ tmpPos++ ] = array[ leftPos++ ];

		while( rightPos <= rightEnd )  // Copy rest of right half
		tempArray[ tmpPos++ ] = array[ rightPos++ ];

		// Copy tempArray back
		for( int i = 0; i < numElements; i++, rightEnd-- )
			array[ rightEnd ] = tempArray[ rightEnd ];
	}

}