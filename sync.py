import threading
import random
import time

def producer_consumer_problem():
    mutex = threading.Lock()
    empty_semaphore = None
    full_semaphore = None
    num_itr = int(input("Enter the no. of iterations needed: "))

    def producer(producer_id, buffer, buffer_size):
        for i in range(num_itr):
            item = random.randint(1, 10)
            empty_semaphore.acquire()
            mutex.acquire()
            buffer.append(item)
            print(f"Producer {producer_id} produced {item}. Buffer: {buffer}")
            mutex.release()
            full_semaphore.release()
            time.sleep(random.uniform(0.1, 1.5))

    def consumer(consumer_id, buffer):
        for i in range(num_itr):
            full_semaphore.acquire()
            mutex.acquire()
            item = buffer.pop(0)
            print(f"Consumer {consumer_id} consumed {item}. Buffer: {buffer}")
            mutex.release()
            empty_semaphore.release()
            time.sleep(random.uniform(0.1, 1.5))

    num_producers = int(input("Enter the no. of Producers: "))
    num_consumers = int(input("Enter the no. of Consumers: "))
    buffer_size = int(input("Enter the Buffer Size: "))

    buffer = []

    empty_semaphore = threading.Semaphore(buffer_size)
    full_semaphore = threading.Semaphore(0)

    producer_threads = [threading.Thread(target=producer, args=(i + 1, buffer, buffer_size)) for i in range(num_producers)]
    consumer_threads = [threading.Thread(target=consumer, args=(i + 1, buffer,)) for i in range(num_consumers)]

    for thread in (producer_threads + consumer_threads):
        thread.start()

    for thread in (producer_threads + consumer_threads):
        thread.join()

def dining_philosophers_problem():
    num_philosophers = int(input("Enter the number of philosophers: "))

    mutex = threading.Lock()
    forks = [threading.Semaphore(1) for i in range(num_philosophers)]

    def philosopher(philosopher_id):
        while True:
            wait(philosopher_id)
            eat(philosopher_id)

    def wait(philosopher_id):
        print(f"Philosopher {philosopher_id} is waiting.")

    def eat(philosopher_id):
        left_fork = philosopher_id
        right_fork = (philosopher_id + 1) % num_philosophers

        mutex.acquire()
        forks[left_fork].acquire()
        forks[right_fork].acquire()
        mutex.release()

        print(f"Philosopher {philosopher_id} is eating.")

        forks[left_fork].release()
        forks[right_fork].release()

    philosopher_threads = [threading.Thread(target=philosopher, args=(i,)) for i in range(num_philosophers)]

    for thread in philosopher_threads:
        thread.start()

    for thread in philosopher_threads:
        thread.join()

def readers_writers_problem():
    readers = int(input("Enter the number of readers: "))
    writers = int(input("Enter the number of writers: "))
    read_iterations = int(input("Enter the number of times each reader reads: "))
    write_iterations = int(input("Enter the number of times each writer writes: "))

    readers_mutex = threading.Semaphore(1)
    writers_mutex = threading.Semaphore(1)
    readers_count = 0
    resource_mutex = threading.Semaphore(1)

    def reader(reader_id):
        global readers_count
        for _ in range(read_iterations):
            readers_mutex.acquire()
            readers_count += 1
            if readers_count == 1:
                resource_mutex.acquire()
            readers_mutex.release()

            print(f"Reader {reader_id} is reading.")

            readers_mutex.acquire()
            readers_count -= 1
            if readers_count == 0:
                resource_mutex.release()
            readers_mutex.release()

    def writer(writer_id):
        for _ in range(write_iterations):
            writers_mutex.acquire()
            resource_mutex.acquire()

            print(f"Writer {writer_id} is writing.")

            resource_mutex.release()
            writers_mutex.release()

    reader_threads = [threading.Thread(target=reader, args=(i,)) for i in range(readers)]
    writer_threads = [threading.Thread(target=writer, args=(i,)) for i in range(writers)]

    for thread in reader_threads + writer_threads:
        thread.start()

    for thread in reader_threads + writer_threads:
        thread.join()

def main():
    while True:
        print("Choose a problem to solve:")
        print("1. Producer-Consumer Problem")
        print("2. Dining Philosophers Problem")
        print("3. Readers-Writers Problem")
        print("4. Exit")

        choice = input("Enter the problem number (1/2/3/4): ")

        if choice == '1':
            producer_consumer_problem()
        elif choice == '2':
            dining_philosophers_problem()
        elif choice == '3':
            readers_writers_problem()
        elif choice == '4':
            break
        else:
            print("Invalid choice. Please select a valid option.")

main()