##Producer-Consumer Lab

### Goal
Given a colored video clip, convert it to gray by  by counting and binary semaphores for a system of two producers and two consumers. One thread will read frames from a file, a second thread will take those frames and convert them to grayscale, and the third thread will display those frames. The threads will run concurrently.

This lab consists of the following:
* ExtractAndDisplay.py
    Extracts and loads a series of frames and displays them

* ConvertToGrayScale.py
    Loads a series for frams from sequentially numbered files with the pattern 'frame_xxxx.jpg', converts the grames to grayscale, and saves them as jpeg images

