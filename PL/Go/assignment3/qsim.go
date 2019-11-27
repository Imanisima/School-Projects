package main

import (
	"fmt"
	"math/rand"
	"time"
)

// qsim.go   Queue Simulator   Nigel Ward   UTEP   November 2019
// one second of simulated time represents one minute of real time

const nSteps = 100
const maxQueue = 10
const minHandleTime = 1
const maxHandleTime = 7
const minCustomerInterval = 0
const maxCustomerInterval = 4

var totalIdleTime float64
var totalWorkingTime float64

func main() {
	rand.Seed(3360)
	sumOfQueueLengthsOverTime := 0
	ch := make(chan bool, maxQueue)

	go spawnCustomers(ch)
	go cashier(1, ch)
	go cashier(2, ch)
	for step := 0; step < nSteps; step++ {
		fmt.Printf("at step%3d queue length is %d\n", step, len(ch))
		sumOfQueueLengthsOverTime += len(ch)
		time.Sleep(1 * time.Second)
	}
	fmt.Println()
	fmt.Printf("average queue length was %.2f\n",
		float64(sumOfQueueLengthsOverTime)/float64(nSteps))
	fmt.Printf("idle percentage was %.0f%%\n",
		100*totalIdleTime/(totalIdleTime+totalWorkingTime))
}

func cashier(name int, ch chan bool) {
	startOfIdleness := time.Now()
	for {
		<-ch // receive and ignore
		idleTimeSeconds := time.Since(startOfIdleness).Seconds()
		fmt.Printf("    cashier %d was idle for %.2f\n", name, idleTimeSeconds)
		totalIdleTime += idleTimeSeconds
		handleTime := randomDuration(minHandleTime, maxHandleTime)
		fmt.Printf("    cashier %d serving a customer; will take %d seconds\n",
			name, handleTime)
		totalWorkingTime += float64(handleTime)
		time.Sleep(time.Duration(handleTime) * time.Second)
		startOfIdleness = time.Now()
	}
}

func spawnCustomers(ch chan bool) {
	for {
		ch <- true // while the queue is full, no new customers will appear
		timeToNextCustomer := randomDuration(minCustomerInterval, maxCustomerInterval)
		fmt.Printf("  a customer joined the queue (next in %d seconds)\n",
			timeToNextCustomer)
		time.Sleep(time.Duration(timeToNextCustomer) * time.Second)
	}
}

func randomDuration(min, max int) int {
	return min + rand.Intn(1+max-min) // a Poisson distribution would be more realistic
}