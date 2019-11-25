package main

import (
	"fmt"
	"os"
	"strings"
)

func main(){
	lessEfficient()
	moreEfficient()
}

func lessEfficient(){
	s, sep := "", ""
	for _, arg := range os.Args[1:]{
		s += sep + arg
		sep = " "
	}
	fmt.Println("Less efficient way: ", s)
}

func moreEfficient(){
	fmt.Println("More efficient way: ", strings.Join(os.Args[1:], " "))
}