#include <msp430.h>
#include "switches.h"
#include "led.h"
#include "buzzer.h"

char switch1_state_down, switch2_state_down, switch3_state_down, switch4_state_down, switch_state_changed; /* effectively boolean */

static char
switch_update_interrupt_sense()
{
  char p2val = P2IN;
  /* update switch interrupt to detect changes from current buttons */
  P2IES |= (p2val & SWITCHES);	/* if switch up, sense down */
  P2IES &= (p2val | ~SWITCHES);	/* if switch down, sense up */
  return p2val;
}

void 
switch_init()			/* setup switch */
{
  P2REN |= SWITCHES;		/* enables resistors for switches */
  P2IE |= SWITCHES;		/* enable interrupts from switches */
  P2OUT |= SWITCHES;		/* pull-ups for switches */
  P2DIR &= ~SWITCHES;		/* set switches' bits for input */
  switch_update_interrupt_sense();
  switch_interrupt_handler();
  
  led_update(0,0);
}

void
switch_interrupt_handler()
{
  char p2val = switch_update_interrupt_sense();
  
   switch1_state_down = (p2val & SW1) ? 0 : 1; /* 0 when SW1 is up */
   switch2_state_down = (p2val & SW2) ? 0 : 1; // 0 when SW2 is up
   switch3_state_down = (p2val & SW3) ? 0 : 1; // 0 when SW3 is up
   switch4_state_down = (p2val & SW4) ? 0 : 1; // 0 when SW4 is up

   switch(switch1_state_down){
   case 0:
    
     break;
   case 1: // when pressed, song will play
     if (buzzer_state == play_song){ // we don't want it to play yet
       buzzer_state = ready_song;
       led_update(1,0);
       return;
     }
     else{
       buzzer_state = play_song;
       play_music();
     }
     break;
   }

   switch(switch4_state_down){
   case 0:
     
     break;
   case 1: // stop song
     if (buzzer_state == play_song){
       buzzer_state = ready_song;
       led_update(1,0);
       return;
       //play_music();
     }
     else{ // resume song
       buzzer_state = play_song;
       play_music();
     }
     break;
   }
}
