# CS 3331: Advanced Object-Oriented Programming :small_blue_diamond:

## HOMEWORK: Warming Up

### Due: February 4, 2019

The purpose of this assignment is to refresh your memory on Java
programming and become familiar with a Java IDE (Eclipse or IntellJ
Idea). You will also produce baseline code that you will be extending
throughout this semester.

In this course, you are to develop a price tracking application, named
Price Watcher, in a series of homework assignments. The application
will help a user to figure out the best time to buy something by
watching over fluctuating online prices. It behaves like Amazon's wish
list (www.amazon.com). You will start with a very simple application
and refine it to an ultimate version in several iterations.

In this assignment, you are to write the first version of the Price
Watcher application that tracks "simulated" prices of a single, fixed
item. Your application shall meet the following functional and
non-functional requirements.

**R1.** The application shall display the name of an item, its initial and
    current prices, and the percentage change of the prices (see R3 and
    R4 below). You may assume that the application knows a single item
    to watch over the price.

**R2.** The application shall provide a way to find the current price of
    the item and calculate a new price change (see R3 below to
    simulate the price of an item).

**R3.** The application shall include a class, say PriceFinder, to
    simulate the price of an item. Given the URL of an item, the class
    returns a "simulated" price of the item, e.g., by generating a
    random, or normally-distributed, price. The idea is to apply the
    Strategy design pattern [1] in later assignments by introducing a
    subclass that actually downloads and parses the web document of
    the given URL to find the current price.

**R4.** The application shall provide a console-based user interface (UI)
    to communicate with a user. It shall take all user inputs from
    System.in and display all outputs to System.out. It shall provide
    a way for the user to quit the application, e.g., using a special
    input value. Below is a sample UI.

  
      Welcome to Price Watcher!

    Name:   LED monitor
    URL:    https://www.bestbuy.com/site/samsung-ue590-series-28-led
            -4k-uhd-monitor-black/5484022.p?skuId=5484022
    Price:  $61.13
    Change: 0.00%
    Added:  08/25/2018 ($61.13)

    Enter 1 (to check price), 2 (to view page), or -1 to quit? 
    

**R5.** Optionally, the application shall provide a way to view the Web
    page of the item. Learn how to launch a default web browser
    programmatically in Java.
