import cv2
from threading import Thread, Lock
from Queue import Custom_Queue as Queue
from ConvertToGrayscale import convertToGrayscale
from ExtractAndDisplay import extractFrames, displayFrames


file = 'clip.mp4'
vidcap = cv2.VideoCapture(file)
length_of_clip = int(vidcap.get(cv2.CAP_PROP_FRAME_COUNT))
frameCounter = 0

def producer(queue, start, end, step):

    for value in range(start, end, step):
        queue.put(value)
    print('Producer finished')


def consumer(queue, count, result, lock):

    local_result = []
    for _ in range(count):
        local_result.append(queue.get())
    with lock:
        result.update(local_result)
    print('Consumer finished')


def main():
    image_queue = Queue(10)
    for i in range(length_of_clip):
        extractFrames(file, image_queue, i)
        convertToGrayscale(file, image_queue)
        displayFrames(image_queue, i, False)


main()