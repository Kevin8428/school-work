package main

import (
	"fmt"
	"time"
)

// func main() {
// 	ch := make(chan bool)

// 	go func() {
// 		fmt.Println("start")
// 		ch <- true
// 		fmt.Println("end")
// 	}()
// 	time.Sleep(3 * time.Second)
// 	<-ch
// 	time.Sleep(1 * time.Second)
// 	fmt.Println("done")
// }

func main() {
	// 'end' prints immediately because the goroutine is no longer blocked
	// if you pass 3 things to the channel, it will block the goroutine until one item is removed from channel
	// if you pass 100 things to channel, it will block forever because only 1 thing is removed. However program will exit w/ goroutine still running
	ch := make(chan bool, 2)

	go func() {
		fmt.Println("start")
		ch <- true
		ch <- true
		ch <- true
		fmt.Println("end")
	}()
	time.Sleep(3 * time.Second)
	<-ch
	time.Sleep(1 * time.Second)
	fmt.Println("done")
}
