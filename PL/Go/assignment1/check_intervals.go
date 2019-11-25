package main

import "fmt"

const(
	numIntervals = 3
)

func main(){
	// the intervals [0, 2], [1, 2], and [0, 1]
	intervalsA := [numIntervals * 2]int{0, 2, 1 ,2 , 0, 1}
	intervalsB := [numIntervals * 2]int{0, 2, 0, 1, -1, 7}
	intervalsC := [numIntervals * 2]int{3, 4, -1, 0, 4, 6}
	intervalsD := [numIntervals * 2]int{0, 2, 3, 4, 7, 9}
	intervalsE := [numIntervals * 2]int{7, 8, 1, 2, 3, 3}

	fmt.Printf("Checking IntervalA & IntervalB...\n")
	fmt.Printf("allIntersect(%x, %x) is %t (expected true)\n",
		intervalsA, intervalsB, allIntersect(intervalsA[:], intervalsB[:]))

	fmt.Printf("\nChecking IntervalA & IntervalC...\n")
	fmt.Printf("allIntersect(%x, %x) is %t (expected false)\n",
		intervalsA, intervalsC, allIntersect(intervalsA[:], intervalsC[:]))

	fmt.Printf("\nChecking IntervalD & IntervalE...\n")
	fmt.Printf("allIntersect(%x, %x) is %t (expected true)\n",
		intervalsD, intervalsE, allIntersect(intervalsD[:], intervalsE[:]))
	
}

// build a function that will compare arrays
func Equals(list1, list2 []int) bool {
	if len(list1) != len(list2) {
		return false
	}
	
	if list1[1] == list2[1]{
			return true
		}
	/*for i := range list1{
		if list1[i] == list2[1]{
			return true
		}
	}*/

	for i, v := range list1 {
		if v != list2[i] {
			return false
		}
	}

	return true
}

func checkIntersection(list1 []int, list2 []int) bool{
	isIntersected := Equals(list1, list2)
	return isIntersected
}

// return true if every interval of arg1 intersects at least one interval of arg2
func allIntersect(intervals1 []int, intervals2 []int) bool{
	firstInt1 := intervals1[0:2]
	firstInt2 := intervals2[0:2]
	secondInt1 := intervals1[2:4]
	secondInt2 := intervals2[2:4]
	thirdInt1 := intervals1[4:len(intervals1)]
	thirdInt2 := intervals2[4:len(intervals2)]

	fmt.Printf("Checking intervals: (%d, %d)\n", intervals1, intervals2)
	fmt.Printf("Does (%d,%d) intersect? %v\n", firstInt1, firstInt2, checkIntersection(firstInt1, firstInt2))
	fmt.Printf("Does (%d,%d) intersect? %v\n", firstInt1, secondInt2, checkIntersection(firstInt1, secondInt2))
	fmt.Printf("Does (%d,%d) intersect? %v\n", firstInt1, thirdInt2, checkIntersection(firstInt1, thirdInt2))

	fmt.Printf("\n")

	fmt.Printf("Does (%d,%d) intersect? %v\n", secondInt1, firstInt2,checkIntersection(secondInt1, firstInt2))
	fmt.Printf("Does (%d,%d) intersect? %v\n", secondInt1, secondInt2,checkIntersection(secondInt1, secondInt2))
	fmt.Printf("Does (%d,%d) intersect? %v\n", secondInt1, thirdInt2,checkIntersection(secondInt1, thirdInt2))

	fmt.Printf("\n")

	fmt.Printf("Does (%d,%d) intersect? %v\n", thirdInt1, firstInt2, checkIntersection(thirdInt1, firstInt2))
	fmt.Printf("Does (%d,%d) intersect? %v\n", thirdInt1, secondInt2,checkIntersection(thirdInt1, secondInt2))
	fmt.Printf("Does (%d,%d) intersect? %v\n", thirdInt1, thirdInt2, checkIntersection(thirdInt1, thirdInt2))

	return true
}