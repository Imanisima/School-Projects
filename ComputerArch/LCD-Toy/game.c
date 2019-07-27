#include <msp430.h>
#include <libTimer.h>
#include <lcdutils.h>
#include <lcddraw.h>
#include <p2switches.h>
#include <shape.h>
#include "buzzer.h"
#include <abCircle.h>
#include "game.h"
#include "buzzer.h"
#include "drawScore.h"

#define GREEN_LED BIT6

char score[] = "00";
char startGame = 1;

/* Create a (12, 2) rectangle */
AbRect rect10 = {abRectGetBounds, abRectCheck, {12,2}};
AbRect rect5 = {abRectGetBounds, abRectCheck, {2,2}};

/* playing field */
AbRectOutline fieldOutline = {
  abRectOutlineGetBounds, abRectOutlineCheck,   
  {screenWidth/2 - 5, screenHeight/2 - 5}
};

u_int bgColor = COLOR_BLACK;
int redrawScreen = 1;

/* Regin around playing field and paddle */
Region fieldFence;
Region paddleFence;

/* Region around the bricks to detect collision */
Region brickFence0;
Region brickFence1;
Region brickFence2;

/* Layers with bricks */
Layer brickLayer2 = {
  (AbShape *) &rect11,
  (Vec2){screenWidth/2, 18},
  {0,0}, {0,0},
  COLOR_FIREBRICK,
  0,
};

Layer brickLayer1 = {
  (AbShape *) &rect11,
  (Vec2){screenWidth/2 + 30, 18},
  {0,0}, {0,0},
  COLOR_FIREBRICK,
  &brickLayer2,
};

Layer brickLayer0 = {
  (AbShape *) &rect11,
  (Vec2){screenWidth/2 - 30, 18},
  {0,0}, {0,0},
  COLOR_FIREBRICK,
  &brickLayer1,
};

/* Layer with ball */
Layer ballLayer = {
  (AbShape *) &rect5,
  (Vec2){screenWidth/2, screenHeight/2 + 53},
  {0,0},{0,0},
  COLOR_GOLD,
  &brickLayer0,
};

/* Layers for game */
Layer fieldLayer = {
  (AbShape *) &fieldOutline,
  {screenWidth/2, screenHeight/2},
  {0,0},{0,0},
  COLOR_BLACK,
  &ballLayer,
};

/* Layer with paddle */
Layer paddleLayer = {
  (AbShape *)&rect10, // purple rect
  (Vec2){screenWidth/2, screenHeight/2 + 62}, //start at bottom
    {0,0},{0,0},
  COLOR_VIOLET,
  &fieldLayer,
};

MovLayer ml1 = {&ballLayer, {0,0}, 0}; // ball
MovLayer ml0 = {&paddleLayer, {0,0}, &ml1}; // paddle

void ballCollision(){
  layerGetBounds(&paddleLayer, &paddleFence);
  ballAdvance(&ml0, &paddleFence);

  layerGetBounds(&brickLayer0, &brickFence0);
  brickAdvance(&ml0, &brickFence0, 0);

  layerGetBounds(&brickLayer1, &brickFence1);
  brickAdvance(&ml0, &brickFence1, 1);

  layerGetBounds(&brickLayer2, &brickFence2);
  brickAdvance(&ml0, &brickFence2, 2); 
  
}

/* when ball hits paddle, ball goes in another direction */
void ballAdvance(MovLayer *ml, Region *fence){
  Vec2 newPos;
  u_char axis;
  Region shapeBoundary;

  for(; ml; ml = ml -> next){
    vec2Add(&newPos, &ml->layer->posNext, &ml->velocity);
    abShapeGetBounds(ml->layer->abShape, &newPos,&shapeBoundary);
    int centerX = (shapeBoundary.topLeft.axes[0] + shapeBoundary.botRight.axes[0])/2;
    int centerY = (shapeBoundary.topLeft.axes[1] + shapeBoundary.botRight.axes[1])/2;

        /* Check center */
    if(centerX >= fence -> topLeft.axes[0] && centerX <= fence-> botRight.axes[0]){
        if(centerY >= fence -> topLeft.axes[1] && centerY <= fence -> botRight.axes[1]){
	  
            // move to opposite direction
            int velocity_Y = ml -> velocity.axes[1] = -ml -> velocity.axes[1];
	    int velocity_X = ml -> velocity.axes[0] = ml -> velocity.axes[0];
            newPos.axes[1] += (2 * velocity_Y);
	    newPos.axes[0] += (2 * velocity_X);
	}
    }
  }
}

/* when ball hits a brick, it should disappear  */
void brickAdvance(MovLayer *ml, Region *fence, int brickValue){
  Vec2 newPos;
  u_char axis;
  Region shapeBoundary;

  for(; ml; ml = ml -> next){
    vec2Add(&newPos, &ml->layer->posNext, &ml->velocity);
    abShapeGetBounds(ml->layer->abShape, &newPos,&shapeBoundary);
    int centerX = (shapeBoundary.topLeft.axes[0] + shapeBoundary.botRight.axes[0])/2;
    int centerY = (shapeBoundary.topLeft.axes[1] + shapeBoundary.botRight.axes[1])/2;

        /* Check center */
    if(centerX >= fence -> topLeft.axes[0] && centerX <= fence-> botRight.axes[0]){
        if(centerY >= fence -> topLeft.axes[1] && centerY <= fence -> botRight.axes[1]){

            // move to opposite direction
            int velocity_Y = ml -> velocity.axes[1] = -ml -> velocity.axes[1];
	    int velocity_X = ml -> velocity.axes[0] = ml -> velocity.axes[0];
	    
            newPos.axes[1] += (2 * velocity_Y);
	    newPos.axes[0] += (2 * velocity_X);

            // make brick disappear
            if(brickValue == 0){
	      buzzer_set_period(3000);
              destroyBrick(brickValue);
	      
	    }
            else if (brickValue == 1){
	      buzzer_set_period(3000);
	      destroyBrick(brickValue);
	    }
            else if(brickValue == 2){
	      buzzer_set_period(3000);
              destroyBrick(brickValue);
	    }
        }
    }
  }
}

/* Display score */
void printScore(){
  if(score[1] > '9'){
    score[0]++;
    score[1] = '0';
  }else{
    score[1]++;
  }

  /* Display score */
	drawString5x7(screenWidth/2 + 50, screenHeight/2 + 70, score, COLOR_GREEN, COLOR_BLACK);

	if(score[1] >= '9'){
	  drawString5x7(screenWidth/2 - 40, screenHeight/2, "YOU'RE A BEAST!!", COLOR_GREEN, COLOR_BLACK); 
    drawString5x7(screenWidth/2 - 40, screenHeight/2, "YOU'RE A BEAST!!", COLOR_RED, COLOR_BLACK);  
    drawString5x7(screenWidth/2 - 40, screenHeight/2, "YOU'RE A BEAST!!", COLOR_GREEN, COLOR_BLACK); 
    

}else if(score[0] > '9' && score[0] > '9'){
	  score[0] = '0';
    score[1] = '0';
	  drawString5x7(screenWidth/2 - 50, screenHeight/2, "SEGMENTATION FAULT", COLOR_RED, COLOR_BLACK);
	}
}

void destroyBrick(int brickValue){
    /* transport brick off the screen */
  if(brickValue == 0){
    brickLayer0.pos = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickLayer0.posNext = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickFence0.topLeft = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050}; // necessary for preventing ball from getting stuck
    brickFence0.botRight = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};


    printScore();
  }
   else if(brickValue == 1){
    brickLayer1.pos = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickLayer1.posNext = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickFence1.topLeft = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickFence1.botRight = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};

    printScore();

    
   }
   else if(brickValue == 2){
    brickLayer2.pos = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickLayer2.posNext = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickFence2.topLeft = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};
    brickFence2.botRight = (Vec2){screenWidth/2 - 1000, screenHeight/2 - 1050};

    printScore();
    
   }
}

/* Maps movement to switches */
char buttonSense(char str){
  u_int switches = p2sw_read(), i;
  for (int i = 0; i < 4; i++)
    if(!(switches & (1 << i)))
      str = i;

  switch(str){
  /* SW1: move left  */
  case 0:
    if(!startGame)
      ml0.velocity = (Vec2){-5, 0};
    break;

    /* SW2: start game */
  case 1:
    if(startGame){
      ml1.velocity = (Vec2){4,-2};
      startGame = 0;
    }
    break;

    /* SW4: move right  */
  case 3:
    if(!startGame)
      ml0.velocity = (Vec2){5, 0};
    break;

    /* No button pressed */
  default:
    ml0.velocity = (Vec2){0,0};
  }
  return -1;
}
    

/* Initialization */
void main(){
    P1DIR |= GREEN_LED;
    P1OUT |= GREEN_LED;

    configureClocks();
    lcd_init();
    buzzer_init();
    buzzer_set_period(4000);
    buzzer_set_period(0);

      drawString5x7(screenWidth/2 - 40, screenHeight/2 + 10, "BREAKOUT!", COLOR_PINK, COLOR_BLACK);
      drawString5x7(screenWidth/2 - 50, screenHeight/2 + 20, "Press S2 to start!", COLOR_GREEN, COLOR_BLACK);
    
    shapeInit();
    p2sw_init(15);

    shapeInit();

    layerInit(&paddleLayer);
    layerDraw(&paddleLayer);

    layerGetBounds(&fieldLayer, &fieldFence);

    enableWDTInterrupts();      /**< enable periodic interrupt */
    or_sr(0x8);	              /**< GIE (enable interrupts) */


    for(;;) { 
        while (!redrawScreen) { /**< Pause CPU if screen doesn't need updating */
	  P1OUT &= ~GREEN_LED;    /**< Green led off witHo CPU */
	  or_sr(0x10);	      /**< CPU OFF */
        }
        P1OUT |= GREEN_LED;       /**< Green led on when CPU on */
        redrawScreen = 0;

        /* Button */
        char str;
        str = buttonSense(str);

	// /* Display score */
	// drawString5x7(screenWidth/2 + 50, screenHeight/2 + 70, score, COLOR_GREEN, COLOR_BLACK);

	// if(score[1] >= '9'){
	//   drawString5x7(screenWidth/2 - 40, screenHeight/2, "YOU'RE A BEAST!!", COLOR_YELLOW, COLOR_BLACK);  
	// }else if(score[0] > '9'){
	//   score[0] = '0';
	//   drawString5x7(screenWidth/2 - 50, screenHeight/2, "SEGMENTATION FAULT", COLOR_RED, COLOR_BLACK);
	// }
	
        movLayerDraw(&ml0, &paddleLayer);
    }
}

/** Watchdog timer interrupt handler. 15 interrupts/sec */
void wdt_c_handler()
{
  static short count = 0;
  static short buzzerCount = 0;
  
  P1OUT |= GREEN_LED; /**< Green LED on when cpu on */
  count ++;
  buzzerCount++;

  if(buzzerCount == 200){
    buzzer_set_period(0);
    buzzerCount = 0;
  }
  
  if (count == 15) {
    mlAdvance(&ml0, &fieldFence);
    ballCollision();
    
    if (p2sw_read())
      redrawScreen = 1;
    count = 0;
  }
  P1OUT &= ~GREEN_LED;		    /**< Green LED off when cpu off */
}
