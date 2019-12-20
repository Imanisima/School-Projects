from threading import Lock, Semaphore

class Custom_Queue:

    def __init__(self, buffer_size):
        # two buffers to detect color and gray
        self.color_buffer = [None] * buffer_size
        self.gray_buffer = [None] * buffer_size

        # producers/consumers
        self.producer_index = 0    # writing          
        self.consumer_index = 0    # reading

        # Locks for producers and consumers
        self.write_lock = Lock()           
        self.read_lock = Lock()             

        # buffer_size of the buffers            
        self.buffer_size = buffer_size                    
        
        # Controls access for producer/consumer
        self.produce_semaphore = Semaphore()       
        self.consume_semaphore = Semaphore()

        # consumer lock acquired      
        self.consume_semaphore.acquire()           

    # Producers - colored frames are enqueued
    def enqueue_colored_frames(self, frame):
        self.produce_semaphore.acquire()
        with self.write_lock:
            i = self.producer_index
            self.producer_index = (self.producer_index + 1) % len(self.color_buffer)
        self.color_buffer[i] = frame
        self.consume_semaphore.release()

    # Consumers - colored frames are dequeued
    def get_color(self):
        self.consume_semaphore.acquire()
        with self.read_lock:
            i = self.consumer_index
            self.consumer_index = (self.consumer_index + 1) % len(self.color_buffer)
        frame = self.color_buffer[i]
        self.produce_semaphore.release()
        return frame

    # Producers - gray frames are enqueue
    def enqueue_gray_frames(self, frame):
        self.produce_semaphore.acquire()
        with self.write_lock:
            i = self.producer_index
            self.producer_index = (self.producer_index + 1) % len(self.gray_buffer)
        self.gray_buffer[i] = frame
        self.consume_semaphore.release()

    # Consumers - gray frames are dequeue
    def get_gray(self):
        self.consume_semaphore.acquire()
        with self.read_lock:
            i = self.consumer_index
            self.consumer_index = (self.consumer_index + 1) % len(self.gray_buffer)
        frame = self.gray_buffer[i]
        self.produce_semaphore.release()
        return frame
    
    def remove_all_color(self):
        return self.color_buffer == []