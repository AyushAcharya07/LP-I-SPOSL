import java.util.*;
import java.util.Scanner;

class Process {
    int processID;
    int arrival, burst, waiting, turnAround, remainingTime;
    int finish, completionTime;
}

public class rr2 {

    public static void main(String[] args) {
        int n, sumBurst = 0, quantum, time;
        double avgWAT = 0, avgTAT = 0;
        Scanner sc = new Scanner(System.in);
        Queue<Integer> q = new LinkedList<>();
        System.out.print("Enter Number of Process: ");
        n = sc.nextInt();
        Process[] p = new Process[n];
        for (int i = 0; i < n; i++) {
            p[i] = new Process();
            p[i].processID = i + 1;
            System.out.print("Enter the arrival time for P" + (i + 1) + ": ");
            p[i].arrival = sc.nextInt();
            System.out.print("Enter the burst time for P" + (i + 1) + ": ");
            p[i].burst = sc.nextInt();
            p[i].remainingTime = p[i].burst;
            p[i].finish = 0;
            sumBurst += p[i].burst;
            System.out.println();
        }
        System.out.print("\nEnter time quantum: ");
        quantum = sc.nextInt();
        Process pTemp;

        // Sort the processes by arrival time
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (p[i].arrival > p[j].arrival) {
                    pTemp = p[i];
                    p[i] = p[j];
                    p[j] = pTemp;
                }
            }
        }

        q.add(0);
        String ganttChart = "Gantt Chart:\n";
        StringBuilder timeline = new StringBuilder();
        timeline.append(0).append("\t\t");
        for (time = p[0].arrival; time < sumBurst;) {
            int i = q.remove();
            if (p[i].remainingTime <= quantum) {
                time += p[i].remainingTime;
                p[i].remainingTime = 0;
                p[i].finish = 1;
                p[i].completionTime = time;
                p[i].waiting = time - p[i].arrival - p[i].burst;
                p[i].turnAround = time - p[i].arrival;
                for (int j = 0; j < n; j++) {
                    if ((p[j].arrival <= time) && (p[j].finish != 1) && (!q.contains(j)))
                        q.add(j);
                }
                ganttChart += "   P" + p[i].processID + "    ";
                timeline.append(time).append("\t\t");
            }
            else
            {
                time += quantum;
                p[i].remainingTime -= quantum;
                for (int j = 0; j < n; j++) {
                    if (p[j].arrival <= time && p[j].finish != 1 && i != j && (!q.contains(j)))
                        q.add(j);
                }
                q.add(i);
                ganttChart += "   P" + p[i].processID + "    ";
                timeline.append(time).append("\t\t");
            }
        }
        System.out.println("\n---------------------------------------------------------------------------------------");
        System.out.println("Processor\tArrival time\tBurst time\tCompletion Time \tTurnaround time \tWaiting time");
        System.out.println("----------------------------------------------------------------------------------------");

        for (int i = 0; i < n; i++) {
            System.out.println("P" + p[i].processID + "\t\t\t\t" + p[i].arrival + "ms\t\t\t\t" + p[i].burst + "ms\t\t\t\t" + p[i].completionTime + "ms\t\t\t\t" + p[i].turnAround + "ms\t\t\t\t" + p[i].waiting + "ms");
            avgWAT += p[i].waiting;
            avgTAT += p[i].turnAround;
        }

        System.out.println("\nAverage turn around time for Processes is : " + (avgTAT / n) + "ms\nAverage waiting time of Processes is : " + (avgWAT / n) + "ms");
        System.out.println("\n" + ganttChart);
        System.out.println(timeline.toString().trim());
    }
}
