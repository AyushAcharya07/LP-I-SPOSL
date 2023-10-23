/*
Enter the no. of Processes :
7
Enter the process ID :
P1
Enter the Arrival time for Process 1 :
0
Enter the Burst time for Process 1 :
3
Enter the Priority for Process 1 :
2
Enter the process ID :
P2
Enter the Arrival time for Process P2 :
2
Enter the Burst time for Process P2 :
5
Enter the Priority for Process P2 :
6
Enter the process ID :
P3
Enter the Arrival time for Process P3 :
1
Enter the Burst time for Process P3 :
4
Enter the Priority for Process P3 :
3
Enter the process ID :
P4
Enter the Arrival time for Process P4 :
4
Enter the Burst time for Process P4 :
2
Enter the Priority for Process P4 :
5
Enter the process ID :
P5
Enter the Arrival time for Process P5 :
6
Enter the Burst time for Process P5 :
9
Enter the Priority for Process P5 :
7
Enter the process ID :
P6
Enter the Arrival time for Process P6 :
5
Enter the Burst time for Process P6 :
4
Enter the Priority for Process P6 :
4
Enter the process ID :
P7
Enter the Arrival time for Process P7 :
7
Enter the Burst time for Process P7 :
10
Enter the Priority for Process P7 :
10

*/


import java.util.*;

class input {
    String Process_id;
    int A_time, B_time, Prior;
}

public class priority {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        input[] obj;
        int C_time[];
        int T_time[];
        int W_time[];
        int totalTurnaroundTime = 0;
        int totalWaitingTime = 0;

        System.out.println("Enter the no. of Processes : ");
        n = sc.nextInt();
        obj = new input[n];
        C_time = new int[n];
        T_time = new int[n];
        W_time = new int[n];

        for (int i = 0; i < n; i++) {
            obj[i] = new input();
            System.out.println("Enter the process ID : ");
            obj[i].Process_id = sc.next();
            System.out.println("Enter the Arrival time for Process " + obj[i].Process_id + " : ");
            obj[i].A_time = sc.nextInt();
            System.out.println("Enter the Burst time for Process " + obj[i].Process_id + " : ");
            obj[i].B_time = sc.nextInt();
            System.out.println("Enter the Priority for Process " + obj[i].Process_id + " : ");
            obj[i].Prior = sc.nextInt();
        }

        // Sort the processes based on priority and arrival time (if priority is the same).
        Arrays.sort(obj, (a, b) -> {
            if (a.Prior == b.Prior) {
                return Integer.compare(a.A_time, b.A_time);
            }
            return Integer.compare(a.Prior, b.Prior);
        });

        int currentTime = 0;
        boolean[] completed = new boolean[n];

        System.out.println("Gantt Chart: ");
        StringBuilder ganttChart = new StringBuilder();
        StringBuilder timeLine = new StringBuilder("0");

        while (true) {
            int highestPriority = -1;
            for (int i = 0; i < n; i++) {
                if (!completed[i] && obj[i].A_time <= currentTime) {
                    if (highestPriority == -1 || obj[i].Prior < obj[highestPriority].Prior) {
                        highestPriority = i;
                    }
                }
            }

            if (highestPriority == -1) {
                int nextArrival = Integer.MAX_VALUE;
                for (int i = 0; i < n; i++) {
                    if (!completed[i] && obj[i].A_time > currentTime && obj[i].A_time < nextArrival) {
                        nextArrival = obj[i].A_time;
                    }
                }
                if (nextArrival != Integer.MAX_VALUE) {
                    int gap = nextArrival - currentTime;
                    ganttChart.append("\t".repeat(gap));
                    timeLine.append("\t".repeat(gap)).append(currentTime);
                    currentTime = nextArrival;
                } else {
                    break;
                }
            } else {
                int selectedProcess = highestPriority;
                currentTime += obj[selectedProcess].B_time;

                C_time[selectedProcess] = currentTime;
                T_time[selectedProcess] = C_time[selectedProcess] - obj[selectedProcess].A_time;
                W_time[selectedProcess] = T_time[selectedProcess] - obj[selectedProcess].B_time;

                completed[selectedProcess] = true;

                // Add process name in between the two completion times.
                ganttChart.append("\t").append(obj[selectedProcess].Process_id);
                timeLine.append("\t").append(currentTime);
            }
        }

        System.out.println(ganttChart.toString());
        System.out.println(timeLine.toString());
        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        System.out.println("PID\t\tArrival Time\t\tBurst Time\t\tPriority\t\tCompletion Time\t\tTurnaround Time\t\tWaiting Time");
        System.out.println("-------------------------------------------------------------------------------------------------------");
        for (int i = 0; i < n; i++)
        {
            System.out.println(obj[i].Process_id + "\t\t\t" + obj[i].A_time + "\t\t\t\t" + obj[i].B_time + "\t\t\t\t" + obj[i].Prior + "\t\t\t\t\t" + C_time[i] + "\t\t\t\t\t" + T_time[i] + "\t\t\t\t\t" + W_time[i]);
            totalTurnaroundTime += T_time[i];
            totalWaitingTime += W_time[i];
        }

        System.out.println(" ");
        System.out.println("-------------------------------------------------------------------------------------------------------");


        double avgTurnaroundTime = (double) totalTurnaroundTime / n;
        double avgWaitingTime = (double) totalWaitingTime / n;

        System.out.println("Average Turnaround Time: " + avgTurnaroundTime+" ms");
        System.out.println("Average Waiting Time: " + avgWaitingTime+" ms");
    }
}

