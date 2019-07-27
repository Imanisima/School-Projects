# :small_red_triangle_down: Lab 2: Blinky-Buzzy Toy :small_red_triangle_down:

## Introduction :closed_book:
This code for the toy contains the following:
1. A short excerpt called _Futari no Kimochi_ ("To Love's End") by Kaoru Wada from Inuyasha OST :notes:
2. Piano keys (3) that are allocated to each Switch :musical_keyboard:
3. A siren with three different variations :sound:

## Getting Started :computer:
* To compile, outside of the toy_demo folder but inside of the project folder type:
``` make ```

* Next, go into the toy_demo folder and type:
``` make load ```

* If you want to recompile be sure to type ``` make clean ``` before following the above steps again.

**_IMPORTANT_**: To completely remove the program from the MSP430, type ``` mspdebug rf2500 erase ```

## Instructions :book:
* When you first turn on the MSP430, there will be two lights on: red and green. 
  You are in the START state. 
* Press _any_ of the switches (SW1, SW2, SW3, SW4) on the green board to get to the **MENU** option

**Note**: **MENU** is indicated by the **RED** LED. 

* From here there are THREE choices:

     ## SW1: Play Song :notes:
       * When this option is chosen, the song will automatically play
       * Press SW2 or SW3: Return to START
       * Press SW4: QUIT

     ## SW2: Piano Mode :musical_keyboard:
        * Press SW1: A
        * Press SW2: B
        * Press SW3: C
        * Press SW4: return to START
     ## SW3: Play Sirens :sound:
        * The original siren will automatically start to play
        * Press SW1: Variation 1
        * Press SW2: Variation 2
        * Press SW3: Return to START
        * Press SW4: QUIT
        
  Choose one.
  
**SOME THINGS TO KEEP IN MIND** :bulb:
* When an option is chosen both LEDs will light up.
* When in QUIT, both LEDs will blink lightly. If you want to return to START again after quitting, _press any of the switches (SW1, SW2, SW3, SW4)_
* Once the song from Option 1 has been completely played, the LED will turn green which will not allow you to listen to the song again. Or, if the song is playing and you switch to another option in the middle, when you return to the song again it will resume in the middle. To completely reset the song, press **SW1 on the red board**. This will ensure a fresh start!

Side-note: Idea for how to create the tempo for the song is credited to @DeveloperJose's github.
https://github.com/DeveloperJose/C-MSP430-BlinkyToy/blob/master/project/buzzer.c
