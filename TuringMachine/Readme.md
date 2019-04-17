## Turing Machine Emulator

* the number N of states q0, ..., qN − 1
  * assume that q0 is the start state
  * last-but-one state qN − 2 is the accept state
  * last state qN − 1 is the reject state

* the number M of symbols s0, ... sM − 1
  * assume that s0 is the blank state _
  
* an integer array state[n][m]
__Note__: this describes to what state the Turing machine moves if it was in the state qn and sees the symbol sm

* an integer array symbol[n][m]
__Note__: this describes what symbol should be on the tape after the Turing Machine in the state qn sees the symbol sm (it may be the same symbol as before, or it may be some other symbol written by the Turing machine)

* a character array lr[n][m]
__Note__: describes, for each state qn and for each symbol sm, whether the Turing machine moves to the left (L), or to the right (R), or stays in place (blank symbol)

* keep track of a current location of the head. Initially, this location is 0.

The integer array of a large size describing the original contents of the tape, i.e., what symbols are written in each cell.
