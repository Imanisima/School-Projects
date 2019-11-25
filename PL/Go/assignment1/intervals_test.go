package main

import "testing"

func Test1(t *testing.T){
	intervalsA := [numIntervals * 2]int{0, 2, 1, 2, 0, 1}
	intervalsB := [numIntervals * 2]int{0, 2, 0, 1, -1, 7}
	checkResult(t, intervalsA[:], intervalsB[:], true)
}

func Test2(t *testing.T){
	intervalsA := [numIntervals * 2]int{0, 2, 1, 2, 0, 1}
	intervalsC := [numIntervals * 2]int{3, 4, -1, 0, 4, 6}
	checkResult(t, intervalsA[:], intervalsC[:], false)
}

func Test3(t *testing.T){
	intervalsD := [numIntervals * 2]int{0, 2, 3, 4, 7, 9}
	intervalsE := [numIntervals * 2]int{7, 8, 1, 2, 3, 3}
	checkResult(t, intervalsD[:], intervalsE[:], true)
}

func checkResult(t *testing.T, ilist1 []int, ilist2 []int, expected bool){
	result := allIntersect(ilist1, ilist2)
	if expected != result{
		t.Errorf("allIntersect(%#v, %#v) is %t but expected %t)\n", ilist1, ilist2, result, expected)
	}
}