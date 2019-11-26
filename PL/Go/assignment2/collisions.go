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
	var listOfCuboids = loadCuboids()

	fmt.Println("\nDo the cuboids overlap?\n")

	for cubeA, n := range listOfCuboids{
		for cubeB, l := range listOfCuboids { // exclude first dictionary
			if(cubeB != cubeA){
				// fmt.Printf("%v\n", cubeB)
				printResults(cubeA, n, cubeB, l, isOverlappedX(n, l))
				printResults(cubeA, n, cubeB, l, isOverlappedY(n, l))
				printResults(cubeA, n, cubeB, l, isOverlappedZ(n, l))
			}
		}
	}
}

func loadCuboids() map[string]cuboid{
	list := map[string]cuboid{
				"jumpyFlower" : cuboid{0, 2, 1, 2, 0, 1},
				"dancyFlower" : cuboid{0, 2, 0, 1, -1, 7},
				"wigglyFlower" : cuboid{3, 4, -1, 0, 4, 6},
				"shinyFlower" : cuboid{0, 2, 3, 4, 0, 9},
				"kitMon" : cuboid{0, 1, 1, 2, 0, 1},
				"pupMon" : cuboid{2, 3, 1, 2, 0, 1},
				"birdMon" : cuboid{1, 2, 2, 3, 8, 9},
				"fishMon" : cuboid{3, 5, 2, 3, -1, -2},
				"babyMon" : cuboid{2, 4, 2, 4, 0, 1},
			}

	return list
}

func printResults(c1Name string, c1 cuboid, c2Name string, c2 cuboid, isCollision bool){

	// only print if an overlap is found
	if(isCollision){
		fmt.Printf("%s:\t%v\n%s:\t%v\n%v\n\n", c1Name, c1, c2Name, c2, isCollision)
		fmt.Println("===================================\n")
	}
}

// Takes Cuboid as argument and returns true if x points overlap
func isOverlappedX(c1 cuboid, c2 cuboid) bool{
	if((c2.xMin > c1.xMin || c2.xMin > c1.xMax) || (c1.xMin > c2.xMax || c1.xMax > c2.xMax)){
		return false
	}

	return true
}

// Takes Cuboid as argument and returns true if y points overlap
func isOverlappedY(c1 cuboid, c2 cuboid) bool{
	if((c2.yMin > c1.yMin || c2.yMin > c1.yMax) || (c1.yMin > c2.yMax || c1.yMax > c2.yMax)){
		return false
	}

	return true
}

// Takes Cuboid as argument and returns true if z points overlap
func isOverlappedZ(c1 cuboid, c2 cuboid) bool{
	if((c2.zMin > c1.zMin || c2.zMin > c1.zMax) || (c1.zMin > c2.zMax || c1.zMax > c2.zMax)){
		return false
	}

	return true
}