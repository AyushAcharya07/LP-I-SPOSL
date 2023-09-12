import java.util.*;

class Entry {
    String Process;
    int A_time;
    int B_time;
    int C_time;
    int T_time;
    int W_time;
}

public class sjfnew {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;

        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();
        Entry[] processes = new Entry[n];

        for (int i = 0; i < n; i++) {
            processes[i] = new Entry();
            System.out.println("Enter the Process Name: ");
            processes[i].Process = sc.next();
            System.out.println("Enter the Arrival Time of Process " + processes[i].Process + ": ");
            processes[i].A_time = sc.nextInt();
            System.out.println("Enter the Burst Time of Process " + processes[i].Process + ": ");
            processes[i].B_time = sc.nextInt();
        }

        int[] remainingTime = new int[n];
        for (int i = 0; i < n; i++) {
            remainingTime[i] = processes[i].B_time;
        }

        int currentTime = 0;
        int completed = 0;

        System.out.println("PID\t\tArrival Time\tBurst Time\tCompletion Time\t\tTurnaround Time\t\tWaiting Time");

        while (completed != n) {
            int shortest = -1;
            int shortestBurst = 999;

            for (int i = 0; i < n; i++) {
                if (processes[i].A_time <= currentTime && remainingTime[i] < shortestBurst && remainingTime[i] > 0) {
                    shortest = i;
                    shortestBurst = remainingTime[i];
                }
            }

            if (shortest == -1) {
                currentTime++;
            } else {
                remainingTime[shortest]--;
                currentTime++;

                if (remainingTime[shortest] == 0) {
                    completed++;
                    processes[shortest].C_time = currentTime;
                    processes[shortest].T_time = processes[shortest].C_time - processes[shortest].A_time;
                    processes[shortest].W_time = processes[shortest].T_time - processes[shortest].B_time;

                    System.out.println(processes[shortest].Process + "\t\t\t" + processes[shortest].A_time + "\t\t\t\t" + processes[shortest].B_time + "\t\t\t\t" + processes[shortest].C_time + "\t\t\t\t" + processes[shortest].T_time + "\t\t\t\t" + processes[shortest].W_time);
                }
            }
        }
    }
}

/*
OUTPUT:-
Enter the number of processes:
5
Enter the Process Name:
P1
Enter the Arrival Time of Process P1:
2
Enter the Burst Time of Process P1:
6
Enter the Process Name:
P2
Enter the Arrival Time of Process P2:
5
Enter the Burst Time of Process P2:
2
Enter the Process Name:
P3
Enter the Arrival Time of Process P3:
1
Enter the Burst Time of Process P3:
8
Enter the Process Name:
P4
Enter the Arrival Time of Process P4:
0
Enter the Burst Time of Process P4:
3
Enter the Process Name:
P5
Enter the Arrival Time of Process P5:
4
Enter the Burst Time of Process P5:
4
*/
