
#include <msp430.h>
#include "libTimer.h"
#include "buzzer.h"
#include "led.h"
#include "switches.h"

static unsigned short quarter_note_length = 200;
static unsigned short current_note = 0;
static unsigned short update_duration = 0;
static unsigned int note;

char buzzer_state = 0;
static int play[] = {
  D3, F3, G3, 1,
  G3, B3, C4, 1,
  D4, F4, D4, 1,
  C4, B3, G3, 1,
  D4, C4, G3, 1,
  D4, C4, G3, 1,
  F3, D3,
  G2, D3, G3, A3, B3,
  0, 0, 0, -1
};

static int tempo[] = {
  60, 60, 40, 60,
  60, 60, 40, 60,
  60, 60, 40, 60,
  60, 60, 40, 60,
  60, 60, 40, 60,
  60, 60, 40, 60,
  40, 40,
  60, 60, 60, 60, 40,
  60, 60, 60
  
};

void buzzer_init()
{
    /* 
       Direct timer A output "TA0.1" to P2.6.  
        According to table 21 from data sheet:
          P2SEL2.6, P2SEL2.7, anmd P2SEL.7 must be zero
          P2SEL.6 must be 1
        Also: P2.6 direction must be output
    */
    timerAUpmode();		/* used to drive speaker */
    P2SEL2 &= ~(BIT6 | BIT7);
    P2SEL &= ~BIT7; 
    P2SEL |= BIT6;
    P2DIR = BIT6;		/* enable output to speaker (P2.6) */

    update_duration = (int)(quarter_note_length/tempo[0]);
    buzzer_state = ready_song;
    current_note = 0;
}

void play_music(){
  buzzer_state = play_song;
  note = play[current_note];
 
  if (note < 0){
    buzzer_state = ready_song; // the song has ended
    current_note = 0;
    return;
  }

  if(note == 0){
    led_update(1,1); // turn on both lights when music has stopped or is ready
  }
  else if(note == 1){
    led_update(1,0); // ADD DIM LIGHT HERE
  }
  else{
    led_update(0,1); //turn on green light when music is playing
  }

  buzzer_set_period(note); // play current note and update length
  update_duration--;

  // One the note has been played, advance to the next one
  if (update_duration <= 1){
    current_note++;
    update_duration = (int)(quarter_note_length/tempo[current_note]);
  }
}

void buzzer_set_period(short cycles)
{
  CCR0 = cycles; 
  CCR1 = cycles >> 1;		/* one half cycle */
}


    
    
  

