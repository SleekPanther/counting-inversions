# Counting Inversions
Counting Inversions between 2 lists with Divide & Conquer approach. Extension of MergeSort that actually displays the specific inversions as well as just counting the total number

## Problem Statement
Compare ratings of n items between 2 lists. How similar are they (how many inversions)  
1 list is ranked; 1, 2, 3, ... , n  
The other list has arbitrary order: a<sub>1</sub>, a<sub>2</sub>, a<sub>3</sub>, ... , a<sub>n</sub> (values in the array at an index)  
<img src="images/you-me-lists.png" width="200">  
**Inversion:** index `i` is less then index `j`, but the value at `i` is greater than the value at `j` (`i` < `j`, but a<sub>i</sub> > a<sub>j</sub>)  
**This example has 6 inversions: (4,1), (4,2), (4,3), (3,1), (3,2), (2,1)**

To find the number of inversions, we can just write out the 2 lists & **draw lines connecting identical values**
<img src="images/array1-crossed.png" width="200">  
Now count the number of times these lines cross (actual inversions are in color parentheses)
<img src="images/array1-inversions.png" width="200">  

**If a list has no inversions, it is already sorted and no crossed lines**  
<img src="images/no-inversions-crossed.png" width="200">

This reduces down to just comparing the 2nd list to its sorted order

## Solution
Only requires a slight modification to MergeSort  
**When merging the 2 sublists, if a value is copied from the right half, then it is greater than all the remaining elements in the left half so the inversion could should increase by the number of elements remaining in the left half**  
**This is a *Split Inversion***  
Divide stages:  
<img src="images/mergesort-divide.png" width="200">  
*In the actual algorithm, the left half happens 1st, but I write both divide steps here for simplicity*  
In **Merge 1**, the item from the right half (`3`) is copied 1st so we have an inversion (4,3)  
In **Merge 2**, item from the right half (`1`) is copied 1st so we have the inversion (2,1)  
In **Merge 3**, `1` is < both items in the left half so 2 inversions: (3,1) & 4,1)  
Also in **Merge 3**, `2` is < both items in the left half so 2 more inversions: (3,2) & 4,2)

Divide step is the same, but now in the Merge stage, count the number of inversions in the left half, right half & split inversions  
The Divide stage counts the inversion in the left & right halves, split inversions are counted in the Merge stage


<img src="images/.png" width="200">  

## Code Details
- _

## References
- [Counting Inversions - Kevin Wayne](https://www.cs.princeton.edu/~wayne/kleinberg-tardos/pdf/05DivideAndConquerI.pdf#page=12)
- [MergeSort Code - Data Structures and Algorithm Analysis in Java (Third Edition) by Mark Allen Weiss](http://users.cis.fiu.edu/~weiss/dsaajava3/code/Sort.java)
- [Part 1: O(n log n) Algorithm for Counting Inversions - Free Engineering Lectures](https://www.youtube.com/watch?v=4IvYaOY8Pxw)
- [Part 2: O(n log n) Algorithm for Counting Inversions - Free Engineering Lectures](https://www.youtube.com/watch?v=PLkuid82dbc)
- [Count Inversions - GeeksForGeeks](http://www.geeksforgeeks.org/counting-inversions/)  
Changed specific implementation of `inversionCount`
- [Counting inversions - toto2 (Stack Overflow)](https://codereview.stackexchange.com/q/54756)  
Helped figure out Left Boundary & test cases
