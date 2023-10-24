import threading

num_philosophers=int(input("Enter the no. of philosophers : "))

mutex=threading.Lock()
forks=[threading.Semaphore(1) for i in range(num_philosophers)]

def philosopher(philosopher_id):
    while (True):
        wait(philosopher_id)
        eat(philosopher_id)

def wait(philosopher_id):
    print(f"Philosopher {philosopher_id} is waiting.")

def eat(philosopher_id):
    left_fork=philosopher_id
    right_fork=(philosopher_id+1)% num_philosophers

    mutex.acquire()
    forks[left_fork].acquire()
    forks[right_fork].acquire()
    mutex.release()

    print(f"Philosopher {philosopher_id} is eating.")

    forks[left_fork].release()
    forks[right_fork].release()


philosopher_threads=[threading.Thread(target=philosopher,args=(i,)) for i in range(num_philosophers)]

for thread in philosopher_threads:
    thread.start()

for thread in philosopher_threads:
    thread.join()
