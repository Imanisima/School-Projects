#!/bin/bash
# DON'T FORGET TO ADD ARGUMENTS WHEN RUNNING THE CODE

# files
CODEBOOK="$1" # key
FISHLIST="$2"
DECODER="scrambledMsg.txt" #contains scramled message
OUTPUT="output.txt" # final output

# Exclude the fish names whose length is greater 4
awk 'length($1) == 4 {print $2}' $FISHLIST | sed 's/-/ /g' > $DECODER # dashes have now been replaced with spaces

# delete line if first half of SKU is even
awk '($1%2==1)' $DECODER | tr -s ' ' | cut -d ' ' -f1,2 > $OUTPUT

# sort second field in ascending order
sort -n -k 1 $OUTPUT > $DECODER

# Add three(3) to second half of SKU and then convert the new number to ASCII
awk -v s=3 '{print $2+s}' $DECODER > $OUTPUT

# Get the ASCII code
awk '{printf "%c\n", $1}' $OUTPUT > $DECODER

# Compare to codebook
grep -Ff $DECODER $CODEBOOK | awk '{printf "%s\n", $2}' $CODEBOOK > $OUTPUT
cat $OUTPUT
