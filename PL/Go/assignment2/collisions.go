/*
* Detect positional overlaps (collisions) between objects. Each pair per dimenension
*
*
*/

package main

import (
	"fmt"
	//"math"
)

type cuboid struct {
	xMin, // lowest X coor of the cube
	xMax, // highest X coor of the cube
	yMin, 
	yMax, 
	zMin, 
	zMax float64
}

func main(){
	var jumpyFlower = cuboid{0, 2, 1, 2, 0 ,1}
	var dancyFlower = cuboid{0, 2, 0, 1, -1, 7}
	var wigglyFlower = cuboid{3, 4, -1, 0, 4, 6}
	var shinyFlower = cuboid{0, 2, 3, 4, 0, 9}
	var kitMon = cuboid{0, 1, 1, 2, 0, 1}
	var pupMon = cuboid{2, 3, 1, 2, 0, 1}
	var birdMon = cuboid{1, 2, 2, 3, 8, 9}
	var fishMon = cuboid{3, 5, 2, 3, -1, -2}
	var babyMon = cuboid{2, 4, 2, 4, 0, 1}

	fmt.Println("\nDo the cuboids overlap?\n")

	// eventually this is gonna have to go in a loop!!!
	printResults("jumpyFlower", jumpyFlower, "dancyFlower", dancyFlower, isOverlappedX(jumpyFlower, dancyFlower))
	printResults("jumpyFlower", jumpyFlower, "dancyFlower", dancyFlower, isOverlappedY(jumpyFlower, dancyFlower))
	printResults("jumpyFlower", jumpyFlower, "dancyFlower", dancyFlower, isOverlappedZ(jumpyFlower, dancyFlower))

	printResults("jumpyFlower", jumpyFlower, "wigglyFlower", wigglyFlower, isOverlappedX(jumpyFlower, wigglyFlower))
	printResults("jumpyFlower", jumpyFlower, "wigglyFlower", wigglyFlower, isOverlappedY(jumpyFlower, wigglyFlower))
	printResults("jumpyFlower", jumpyFlower, "wigglyFlower", wigglyFlower, isOverlappedZ(jumpyFlower, wigglyFlower))

	printResults("jumpyFlower", jumpyFlower, "shinyFlower", shinyFlower, isOverlappedX(jumpyFlower, shinyFlower))
	printResults("jumpyFlower", jumpyFlower, "shinyFlower", shinyFlower, isOverlappedY(jumpyFlower, shinyFlower))
	printResults("jumpyFlower", jumpyFlower, "shinyFlower", shinyFlower, isOverlappedZ(jumpyFlower, shinyFlower))

	printResults("jumpyFlower", jumpyFlower, "kitMon", kitMon, isOverlappedX(jumpyFlower, kitMon))
	printResults("jumpyFlower", jumpyFlower, "kitMon", kitMon, isOverlappedY(jumpyFlower, kitMon))
	printResults("jumpyFlower", jumpyFlower, "kitMon", kitMon, isOverlappedZ(jumpyFlower, kitMon))

	printResults("jumpyFlower", jumpyFlower, "pupMon", pupMon, isOverlappedX(jumpyFlower, pupMon))
	printResults("jumpyFlower", jumpyFlower, "pupMon", pupMon, isOverlappedY(jumpyFlower, pupMon))
	printResults("jumpyFlower", jumpyFlower, "pupMon", pupMon, isOverlappedZ(jumpyFlower, pupMon))

	printResults("jumpyFlower", jumpyFlower, "birdMon", birdMon, isOverlappedX(jumpyFlower, birdMon))
	printResults("jumpyFlower", jumpyFlower, "birdMon", birdMon, isOverlappedY(jumpyFlower, birdMon))
	printResults("jumpyFlower", jumpyFlower, "birdMon", birdMon, isOverlappedZ(jumpyFlower, birdMon))

	printResults("jumpyFlower", jumpyFlower, "fishMon", fishMon, isOverlappedX(jumpyFlower, fishMon))
	printResults("jumpyFlower", jumpyFlower, "fishMon", fishMon, isOverlappedY(jumpyFlower, fishMon))
	printResults("jumpyFlower", jumpyFlower, "fishMon", fishMon, isOverlappedZ(jumpyFlower, fishMon))

	printResults("jumpyFlower", jumpyFlower, "babyMon", babyMon, isOverlappedX(jumpyFlower, babyMon))
	printResults("jumpyFlower", jumpyFlower, "babyMon", babyMon, isOverlappedY(jumpyFlower, babyMon))
	printResults("jumpyFlower", jumpyFlower, "babyMon", babyMon, isOverlappedZ(jumpyFlower, babyMon))
}

func printResults(c1Name string, c1 cuboid, c2Name string, c2 cuboid, isCollision bool){

	// only print if an overlap is found
	if(isCollision){
		fmt.Printf("%s: %v\n%s: %v\n%v\n\n", c1Name, c1, c2Name, c2, isCollision)
		fmt.Println("===================================\n")
	}
}

// Takes Cuboid as argument and returns true if they overlap
func isOverlappedX(c1 cuboid, c2 cuboid) bool{
	if((c2.xMin > c1.xMin) || (c2.xMin > c1.xMax) || (c1.xMin > c2.xMax) || c1.xMax > c2.xMax){
		return false
	}

	if((c2.yMin > c1.yMin) || (c2.yMin > c1.yMax) || (c1.yMin > c2.yMax) || c1.yMax > c2.yMax){
		return false
	}

	if((c2.zMin > c1.zMin) || (c2.zMin > c1.zMax) || (c1.zMin > c2.zMax) || c1.zMax > c2.zMax){
		return false
	}

	return true
}

func isOverlappedY(c1 cuboid, c2 cuboid) bool{
	if((c2.yMin > c1.yMin) || (c2.yMin > c1.yMax) || (c1.yMin > c2.yMax) || c1.yMax > c2.yMax){
		return false
	}

	return true
}

func isOverlappedZ(c1 cuboid, c2 cuboid) bool{
	if((c2.zMin > c1.zMin) || (c2.zMin > c1.zMax) || (c1.zMin > c2.zMax) || c1.zMax > c2.zMax){
		return false
	}

	return true
}


/*func ivlAllIntersect(list1 []Interval, list2 []Interval) bool {
	for _, dim := range list1{
		if dim.ivlIntersectsAnyMethod(list2){
			continue
		}else{
			return false
		}
	}
	return true
}*/

/*func (ivl Interval) ivlIntersectsAnyMethod(intervalList []Interval) bool {
	for _, dim := range intervalList{
		if math.Max(float64(ivl.start), float64(dim.start)) <= math.Min(float64(ivl.end), float64(dim.end)){
			return true
		}
	}
	return false
}*/