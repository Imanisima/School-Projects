package main

// if several imports, it's standard to print import via list format
import (
	"fmt"
	"os"
)

// enter a string and it will echo in the terminal
func main(){
	var s, sep string
	for i := 1; i < len(os.Args); i++ {
		s += sep + os.Args[i]
		sep = " "
	}
	fmt.Println(s)
}