// dups1: prints the text of each line that appears more than once

package main

import (
	"bufio"
	"fmt"
	"os"
)

func main(){
	// allocate space for a variable that will map strings to ints
	counts := make(map[string] int)
	fmt.Printf("Please enter something\n")
	input := bufio.NewScanner(os.Stdin)

	// anytime a line of input is read, the line is used as a key and the corresponding value is incremented
	for input.Scan(){
		counts[input.Text()]++
	}

	for line, n := range counts{
		if n > 1{
			fmt.Printf("%d\t%s\n", n, line)
		}
	}
}