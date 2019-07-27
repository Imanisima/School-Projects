#include <msp430.h>
#include "buzzer.h"
#include "switches.h"
// #include "stateMachines.h"
#include "led.h"

void
__interrupt_vec(WDT_VECTOR) WDT(){	/* 250 interrupts/sec */
  static char count = 0;
  if (++count == 60) {
    play_music();
    count = 0;
  }
  //led_update(1,1);
}
