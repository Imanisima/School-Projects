#ifndef buzzer_included
#define buzzer_included

#define play_song 1
#define ready_song 0

#define D3 6811
#define F3 5727
#define G3 5102
#define B3 4290
#define C4 3822
#define D4 3405
#define F4 2863
#define G2 97.99
#define A3 4545

void buzzer_init();
void play_music();
void pause_state();
void resume_state();
void buzzer_set_period(short cycles);

extern char buzzer_state;

#endif // included
