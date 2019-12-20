#!/usr/bin/env python3

import threading
import cv2
import numpy as np
import base64
from Queue import Custom_Queue as Queue

def extractFrames(fileName, outputBuffer, frameCount):
    # open video file
    vidcap = cv2.VideoCapture(fileName)

    vidcap.set(cv2.CAP_PROP_POS_FRAMES,frameCount);
    success,image = vidcap.read()

    if success:
        success, jpgImage = cv2.imencode('.jpg', image)
        encodedFrame = base64.b64encode(jpgImage)
        outputBuffer.enqueue_colored_frames(encodedFrame)

def displayFrames(inputBuffer, count, isColorShown):

    if not inputBuffer.remove_all_color():

        if not isColorShown:
            frameAsText = inputBuffer.get_gray()
        else:
            frameAsText = inputBuffer.get_color()
        
        decodeJPG = base64.b64decode(frameAsText)

        # convert the raw frame to a numpy array
        encodedJPG = np.asarray(bytearray(decodeJPG), dtype=np.uint8)
        
        # get a jpg encoded frame
        img = cv2.imdecode( encodedJPG ,cv2.IMREAD_UNCHANGED)

        print("Displaying frame {}".format(count))        

        cv2.imshow("Video", img)
        if cv2.waitKey(20) and 0xFF == ord("q"):
            pass

    print("Frame extraction complete")

