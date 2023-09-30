import java.util.Scanner;
class calc
{
    Scanner sc = new Scanner(System.in);
    int n;
    int A_time[];
    int B_time[];
    int C_time[];
    int T_time[];
    int W_time[];

    public void a_time()
    {
        System.out.println("Enter the no. of Processes : ");
        n = sc.nextInt();
        A_time=new int[n];
        B_time=new int[n];
        C_time=new int[n];
        T_time=new int[n];
        W_time=new int[n];
        System.out.println("Enter the arrival time : ");
        for (int i = 0; i < n; i++)
        {
            A_time[i]= sc.nextInt();
        }
    }

    public void show_atime()
    {
        for (int i = 0; i < n; i++)
        {
            int j=i+1;
            System.out.println("The Arrival time of Process P"+j+" is : "+A_time[i]);
        }
    }

    public void b_time()
    {
        System.out.println("Enter the burst time : ");
        for (int i = 0; i < n; i++)
        {
            B_time[i]=sc.nextInt();
        }
    }

    public void show_btime()
    {
        for (int i = 0; i < n; i++)
        {
            int j=i+1;
            System.out.println("The Burst time of Process P"+j+" is : "+B_time[i]);
        }
    }

    public void completion()
    {
        C_time[0]=B_time[0];
        for (int i=1;i<n;i++)
        {
            C_time[i]=C_time[i-1]+B_time[i];
        }
    }

    public void show_comp()
    {
        for (int i = 0; i < n; i++)
        {
            int j=i+1;
            System.out.println("The Completion time of Process P"+j+" is : "+C_time[i]);
        }
    }

    public void turn_time()
    {
        for (int i=0;i<n;i++)
        {
            T_time[i]=C_time[i]-A_time[i];
        }
    }

    public void show_turn()
    {
        for (int i = 0; i < n; i++)
        {
            int j=i+1;
            System.out.println("The Turn-Around time of Process P"+j+" is : "+T_time[i]);
        }
    }

    public void wait_time()
    {
        for (int i=0;i<n;i++)
        {
            W_time[i]=T_time[i]-B_time[i];
        }
    }

    public void show_wtime()
    {
        for (int i = 0; i < n; i++)
        {
            int j=i+1;
            System.out.println("The Waiting time of Process P"+j+" is : "+W_time[i]);
        }
    }
}
public class fcfsnew {
    public static void main(String[] args) {
        calc obj=new calc();
        int ch;
        int ans=1;
        while(ans==1) {
            System.out.println("*************************MENU*************************");
            System.out.println("1.Enter the Arrival Time\t\t\t2.Display Arrival Time\n3.Enter the Burst Time\t\t\t\t4.Display the Burst Time\n5.Compute the Completion Time\t\t6.Display the Completion Time\n7.Compute the Turn-Around Time\t\t8.Display the Turn-Around Time\n9.Compute the Waiting Time\t\t\t10.Display the Waiting Time\n11.Exit");
            System.out.println("Enter your choice : ");
            ch=obj.sc.nextInt();

            switch(ch)
            {
                case 1:
                    obj.a_time();
                    break;

                case 2:
                    System.out.println("The Arrival Time of the Processes are : ");
                    obj.show_atime();
                    break;

                case 3:
                    obj.b_time();
                    break;

                case 4:
                    System.out.println("The Burst time of the Processes are : ");
                    obj.show_btime();
                    break;

                case 5:
                    obj.completion();
                    System.out.println("The Completion Time for the Processes Computed successfully!!");
                    break;

                case 6:
                    System.out.println("The Completion Time of the Processes are : ");
                    obj.show_comp();
                    break;

                case 7:
                    obj.turn_time();
                    System.out.println("Turn-Around time Computed successfully!!");
                    break;

                case 8:
                    System.out.println("The Turn-Around Time of the Processes are : ");
                    obj.show_turn();
                    break;

                case 9:
                    obj.wait_time();
                    System.out.println("Waiting Time computed successfully!!");
                    break;

                case 10:
                    System.out.println("The Waiting Time of the Processes are : ");
                    obj.show_wtime();
                    break;

                case 11:
                    System.out.println("Exiting Successfully!!!");
                    ans=0;
                    break;

                default:
                    System.out.println("Wrong choice entered!!\nPlease try again!!");
                    System.out.println("Do you want to Continue?\n1.Yes\n2.No");
                    ans=obj.sc.nextInt();
                    break;
            }
        }
    }
}


/*
INPUT:-
No. of processes : 6

Arrival Time :
0
1
1
1
2
3

Burst Time:
9
3
2
4
3
2

Completion Time is :
9
12
14
18
21
23

T time is:
9
11
13
17
19
20

W time is :
0
8
11
13
16
18

*/
