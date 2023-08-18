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
            System.out.println("->");
            System.out.println("Arrival time of Process "+c[i].Process+" is : "+c[i].A_time);
            System.out.println("Burst time of Process "+c[i].Process+" is : "+c[i].B_time);
            System.out.println(" ");
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
        System.out.println("Processes after sorting are : ");
        for (int i=0;i<n;i++)
        {
            System.out.println(c[i].Process);
            System.out.println("->");
            System.out.println("Arrival time of Process "+c[i].Process+" is : "+c[i].A_time);
            System.out.println("Burst time of Process "+c[i].Process+" is : "+c[i].B_time);
            System.out.println(" ");
        }
    }

    void c_time()
    {
        C_time[0]=c[0].B_time;
        for (int i=1;i<n;i++)
        {
            C_time[i]=C_time[i-1]+c[i].B_time;
        }

    }

    void show_c()
    {
        for (int i=0;i<n;i++)
        {
            System.out.println(C_time[i]);
        }
    }

    void t_time()
    {
        for (int i=0;i<n;i++)
        {
            T_time[i]=C_time[i]-c[i].A_time;
        }
    }

    void show_t()
    {
        for (int i=0;i<n;i++)
        {
            System.out.println(T_time[i]);
        }
    }

    void w_time()
    {
        for (int i=0;i<n;i++)
        {
            W_time[i]=T_time[i]-c[i].B_time;
        }
    }

    void show_w()
    {
        for (int i=0;i<n;i++)
        {
            System.out.println(W_time[i]);
        }
    }

//    void gantt_cht()
//    {
//        for (int i=0;i<n;i++)
//        {
//            System.out.print(c[i].Process+"\t\t\t");
////            System.out.println();
//        }
//        System.out.println(" ");
////        System.out.println("0\t");
//        for (int i=0;i<C_time.length;i++)
//        {
//            if (i==0)
//                System.out.println("0\t"+C_time[i]+"\t\t\t");
//            else
//                System.out.println("\t\t\t"+C_time[i]+"\t\t\t");
//        }
//    }

    void gantt_cht() {
        for (int i = 0; i < n; i++) {
            System.out.print(c[i].Process + "\t\t\t");
        }
        System.out.println();
        for (int i = 0; i < C_time.length; i++) {
            if (i == 0) {
                System.out.print("0\t\t" + C_time[i] );
            } else {
                System.out.print("\t\t\t" + C_time[i] );
            }
        }
        System.out.println();
    }

}

public class fcfs2 {
    public static void main(String[] args) {
        schd obj=new schd();
        int ch,ans=1;
        while(ans==1)
        {
            System.out.println("********************MENU********************");
            System.out.println("1.Take the Entry for Process Name,Arrival & Burst Time\n2.Sort the Processes\t\t\t\t\t3.Display the Processes\n4.Compute the Completion Time\t\t\t5.Display the Completion Time\n6.Compute the Turn-Around Time\t\t\t7.Display the Turn-Around Time\n8.Compute the Waiting Time\t\t\t\t9.Display the Waiting Time\n10.Display the Gantt Chart\n11.Exit");
            System.out.println("Enter your choice : ");
            ch=obj.sc.nextInt();
            switch(ch)
            {
                case 1:
                    obj.entry();
                    break;

                case 2:
                    obj.sort();
                    System.out.println("Processes Sorted Successfully!!");
                    break;

                case 3:
                    obj.show_process();
                    break;

                case 4:
                    obj.c_time();
                    System.out.println("Completion Time for the Processes computed successfully!!");
                    break;

                case 5:
                    System.out.println("The Completion Time for the Processes are : ");
                    obj.show_c();
                    break;

                case 6:
                    obj.t_time();
                    System.out.println("Turn-Around Time for the Processes computed successfully!!");
                    break;

                case 7:
                    System.out.println("The Turn-Around Time for the Processes are : ");
                    obj.show_t();
                    break;

                case 8:
                    obj.w_time();
                    System.out.println("Waiting Time for the Processes computed successfully!!");
                    break;

                case 9:
                    System.out.println("The Waiting Time for the Processes are : ");
                    obj.show_w();
                    break;

                case 10:
                    System.out.println("The Gantt Chart generated is as : ");
                    obj.gantt_cht();
                    break;

                case 11:
                    System.out.println("Terminating successfully!!");
                    ans=2;
                    break;

                default:
                    System.out.println("Wrong choice entered!!\nPlease Try again!");
                    break;
            }
        }
    }
}

/*
OUTPUT:

No. of Processes : 5
 P1 2 2
 P2 5 6
 P3 0 4
 P4 0 7
 P5 7 4
 */
