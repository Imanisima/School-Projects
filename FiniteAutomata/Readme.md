## Finite Autoamta Emulator

* the number N of states q0, ..., qN − 1
  * assume that q0 is the start state;
  
* the number M of symbols s0, ... sM − 1

* an integer array state[n][m]
  * describes to what state the finite automaton moves if it was in the state qn and sees the symbol sm;
* a boolean array final[n] 
  * tells if this state is final or not.
  
__Note:__ This program needs to keep track of a current state. Initially, this location is 0.
