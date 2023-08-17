import java.util.*;
class calculn
{
    String Process;
    int A_time,B_time;
}

class schd
{
    Scanner sc=new Scanner(System.in);
    int n;
    calculn[] c;
    int C_time[];
    int T_time[];
    int W_time[];

    void entry()
    {
        System.out.println("Enter the no. of Processes : ");
        n=sc.nextInt();
        c=new calculn[n];
        C_time=new int[n];
        T_time=new int[n];
        W_time=new int[n];

        for (int i=0;i<n;i++)
        {
            c[i]=new calculn();
            System.out.println("Enter the Process Name : ");
            c[i].Process=sc.next();
            System.out.println("Enter the Arrival Time of the Process "+c[i].Process+" : ");
            c[i].A_time=sc.nextInt();
            System.out.println("Enter the Burst Time of the Process "+c[i].Process+" : ");
            c[i].B_time=sc.nextInt();
            System.out.println(" ");
        }

        System.out.println("The Processes Entered  are : ");
        for (int i=0;i<n;i++)
        {
            System.out.println("Process "+c[i].Process);
            System.out.println(" ");
            System.out.println("Arrival time of Process "+c[i].Process+" is : "+c[i].A_time);
            System.out.println("Burst time of Process "+c[i].Process+" is : "+c[i].B_time);
        }

    }

    void sort()
    {
        for (int i=0;i<n;i++)
        {
            int min=i;
            for (int j=i+1;j<n;j++)
            {
                if (c[j].A_time < c[min].A_time)
                {
                    min=j;
                }
            }
            calculn temp=c[min];
            c[min]=c[i];
            c[i]=temp;
        }
    }

    void show_process()
    {
        System.out.println("Proceeses after sorting are : ");
        for (int i=0;i<n;i++)
        {
            System.out.println(c[i].Process);
            System.out.println("Arrival time of Process "+c[i].Process+" is : "+c[i].A_time);
            System.out.println("Burst time of Process "+c[i].Process+" is : "+c[i].B_time);
        }
    }

    void c_time()
    {
        C_time[0]=c[0].B_time;
        for (int i=1;i<n;i++)
        {
            C_time[i]=C_time[i-1]+c[i].B_time;
        }

        for (int i=0;i<n;i++)
        {
            System.out.println(C_time[i]);
        }
    }




}

public class fcfs2 {
    public static void main(String[] args) {
        schd obj=new schd();
        obj.entry();
        obj.sort();
        obj.show_process();
        obj.c_time();
    }
}
