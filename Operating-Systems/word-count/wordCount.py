import os
import re
import string
import sys

def main():
    # setting cmdLine arguments for file names
    textFile = sys.argv[1]
    outputFile = sys.argv[2]

    # parse input file
    readText = open(textFile, 'r')
    convertText = readText.read().lower()

    # exclude punctuation and whitespace
    wordList = re.findall(r'[a-z0-9]+', convertText)
    tracker = {}
    
    # search for the word and increase counter
    for w in wordList:
        numOfWords = tracker.get(w, 0)
        tracker[w] = numOfWords + 1
    
    # retrieve all keys in dictionary
    trackerList = sorted(tracker.keys())
    
    # write results to file
    with open(outputFile, "w") as f:

        # print results
        for w in trackerList:
            # print("%s: %d" % (w, tracker[w]))

            f.write("%s %d\n" % (w, tracker[w]))
        
    f.close()

main()