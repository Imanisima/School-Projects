state = {0: 'start', 1:'move', 2:'back', 3:'halt', 4:'rejected'}
symbol = {0:0, 1:1, 2:'_'}

def run_Turing_Machine(num_of_states, numOfSymbols, states, symbols,left_to_right, tape):
    head = 0
    current_state = 0
    new_symbol = 0

    while(current_state < num_of_states - 2):
        current_symbol = tape[head]
        new_symbol = symbols[current_state][current_symbol]
        tape[head] = new_symbol
        if left_to_right[current_state][current_symbol] == 'L':
            head -= 1 # move backwards
        else:
            head += 1 # move forward

        print("Current State: %s -> %s , %s" % (state[current_state], symbol[current_symbol], left_to_right[current_state][current_symbol]))

        current_state = states[current_state][current_symbol]
    print("")
    [symbol[i] for i in tape],
    if current_state > num_of_states - 2:
        print("Rejected")
    else:
        print("\tAccepted")

states = [[4, 4, 1],[2, 1, 2],[2, 2, 3], [3, 3, 3]]
symbols = [[2, 2, 2], [1, 0, 1], [0, 1, 2], [2, 2, 2], [3, 3, 3]]
left_to_right = [['R', 'R', 'R'], ['L', 'R', 'L'], ['L', 'L', 'L'], ['L', 'L', 'L'], ['L', 'L', 'L']]
tape = [1, 2, 1, 2]
num_of_states = 5
num_of_symbols = 3
print(tape)
print("Running Turing Machine...\n\n")
run_Turing_Machine(num_of_states, num_of_symbols, states, symbols, left_to_right, tape)
