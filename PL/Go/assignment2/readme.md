## Go Assignment 2
### Detect positional overlaps (collisions) between objects

## More Info

Model the spatial extent of each object as a cuboid, represented as an array of three pairs; one pair per dimension (x, y, and z). Detect if two cuboids overlap in space. For example, in **cuboids.txt**:


```
"jumpyFlower", 0, 2, 1, 2, 0, 1
"danceyFlower", 0, 2, 0, 1, -1, 7
"wigglyFlower", 3, 4, -1, 0, 4, 6
"shinyFlower", 0, 2, 3, 4, 0, 9
"kitMon", 0, 1, 1, 2, 0, 1
"pupMon", 2, 3, 1, 2, 0, 1
"birdMon", 1, 2, 2, 3, 8, 9
"fishMon", 3, 5, 2, 3, -1, -2
"babyMon", 2, 4, 2, 4, 0, 1

```

where *jumpyFlower* and *danceFlower* overlap, but jumpyFlower and birdMon do not.

## Instructions
* Define a Cuboid struct and a method for it that takes another Cuboid as argument and returns true if their __positions overlap__.
* Write a driver that applies this to all pairs of the above objects and outputs a readable list of all overlaps