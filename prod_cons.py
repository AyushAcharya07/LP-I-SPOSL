import threading
import random
import time

mutex=threading.Lock()  # creates mutex Lock but does not lock, it is done by acquire
empty_semaphore=None # intialize
full_semaphore=None  # intialize
num_itr=int(input("Enter the no. of iterations needed : "))


def producer(producer_id,buffer,buffer_size):
    for i in range(num_itr):
        item=random.randint(1,10)
        empty_semaphore.acquire()  # ensures producer doesn't produce until there is an empty slot in buffer
        mutex.acquire() # locks the shared buffer
        buffer.append(item)
        print(f"Producer {producer_id} produced {item}.     Buffer : {buffer}")
        mutex.release()  # releases the mutex lock
        full_semaphore.release()    # notifies that buffer is full
        time.sleep(random.uniform(0.1,1.5))     # random delay betn 0.1 and 1.5 sec

def consumer(consumer_id,buffer):
     for i in range(num_itr):
        full_semaphore.acquire()
        mutex.acquire()
        item=buffer.pop(0)
        print(f"Consumer {consumer_id} consumed {item}.     Buffer : {buffer}")
        mutex.release()
        empty_semaphore.release()
        time.sleep(random.uniform(0.1,1.5))

num_producers=int(input("Enter the no. of Producers : "))
num_consumers=int(input("Enter the no. of Consumers : "))
buffer_size=int(input("Enter the Buffer Size : "))

buffer=[]

empty_semaphore=threading.Semaphore(buffer_size)    # empty_semaphore represents the number of empty slots in the buffer, and it starts with a value equal to the size of the buffer. It is used to control how many producers can add items to the buffer.
full_semaphore=threading.Semaphore(0)   #  It starts with a value of 0, indicating that initially, there are no full slots in the buffer. It is used to control how many consumers can consume items from the buffer.

producer_threads=[threading.Thread(target=producer,args=(i+1,buffer,buffer_size)) for i in range(num_producers)] # assigning no. of threads as per the no. of producers
consumer_threads=[threading.Thread(target=consumer,args=(i+1,buffer,)) for i in range(num_consumers)]   # assigning no. of threads as per the no. of consumers

for thread in (producer_threads+consumer_threads):
    thread.start()  # This initiates the execution of the producer and consumer functions in separate threads concurrently.

for thread in (producer_threads+consumer_threads):
    thread.join()   # This ensures that the program doesn't exit until all threads have completed their tasks.