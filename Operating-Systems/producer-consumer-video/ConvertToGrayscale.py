#!/usr/bin/env python3

import cv2
import threading
import numpy as np
import base64
from Queue import Custom_Queue as Queue

def convertToGrayscale(fileName, buffer):
    # retrieve frame and decode it
    frameAsText = buffer.get_color()
    jpgRawImageOriginal = base64.b64decode(frameAsText)
    
    convert_image = np.asarray(bytearray(jpgRawImageOriginal), dtype=np.uint8)
    enconde_image = cv2.imdecode(convert_image ,cv2.IMREAD_UNCHANGED)

    if enconde_image is not None:
        # convert the image to grayscale
        convert_to_grayscale = cv2.cvtColor(enconde_image, cv2.COLOR_BGR2GRAY)

        # get a jpg encoded frame
        success, jpgImage = cv2.imencode('.jpg', convert_to_grayscale)

        if success:
            #encode the frame as base 64 to make debugging easier
            encode_frame = base64.b64encode(jpgImage)

            # add the frame to the buffer
            buffer.enqueue_gray_frames(encode_frame)

    
    
