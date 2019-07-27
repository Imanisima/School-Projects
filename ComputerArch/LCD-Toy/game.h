#ifndef game_include
#define game_include

#include "moveLayer.h"

extern AbRect rect10;
AbRect rect11 = {abRectGetBounds, abRectCheck, {12, 5}};
extern AbRectOutline fieldOutline;
void ballAdvance(MovLayer *ml, Region *fence);
char buttonSense(char str);
void brickAdvance(MovLayer *ml, Region *fence, int brickValue);
void destroyBrick(int brickValue);
void printScore();

#endif
