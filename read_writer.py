import threading

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

        # Reading (simulated by printing)
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

        # Writing (simulated by printing)
        print(f"Writer {writer_id} is writing.")

        resource_mutex.release()
        writers_mutex.release()

reader_threads = [threading.Thread(target=reader, args=(i,)) for i in range(readers)]
writer_threads = [threading.Thread(target=writer, args=(i,)) for i in range(writers)]

for thread in reader_threads + writer_threads:
    thread.start()

for thread in reader_threads + writer_threads:
    thread.join()
