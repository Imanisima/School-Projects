#include <msp430.h>
#include "libTimer.h"
#include "led.h"
#include "switches.h"
#include "buzzer.h"

void main(void) 
{
  switch_init();
  configureClocks();
  buzzer_init();
  led_init();

  led_update(1,1);

  enableWDTInterrupts();

  or_sr(0x18);  // CPU off, GIE on
} 
