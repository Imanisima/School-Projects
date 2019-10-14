#!/bin/bash
# DON'T FORGET TO ADD ARGUMENTS WHEN RUNNING THE CODE

# files
CODEBOOK="codebook.txt"
FISHLIST="mini-fishlist.txt"
EDITED_FISHLIST="fishlist-with-spaces.txt"
MINI_CODEBOOK="mini-codebook.txt"
NEW_LIST="newList.txt"
SORTED_LIST="sorted_list.txt"

# prints out the hashed numbers in fish-list
echo -e "fish list prints hashed numbers only" >> output.txt
awk '{print $2}' $FISHLIST >> output.txt

# prints out the fish names in fishList
echo -e "\nfish list print fish names" >> output.txt
awk '{print $1}' $FISHLIST >> output.txt

# Exclude the fish names whose length is greater 4
echo -e "\nList fish whose chars are 4 characters and less" >> output.txt
awk 'length($1) == 4 {print $2}' $FISHLIST | sed 's/-/ /g' >> $EDITED_FISHLIST # dashes have now been replaced with spaces

# delete line if first half of SKU is even
echo -e "\nIf number is even, delete the field from the list" >> output.txt
awk '($1%2==1)' $EDITED_FISHLIST | tr -s ' ' | cut -d ' ' -f1,2 >> $NEW_LIST

# sort second field in ascending order
echo -e "\nSort first column in ascending order" >> output.txt
sort -n -k 1 $NEW_LIST >> $SORTED_LIST

# Add three(3) to second half of SKU and then convert the new number to ASCII
echo -e "\nAdd 3 to the end of each SKU" >> output.txt
awk -v s=3 '{print $2+s}' $SORTED_LIST >> output.txt


