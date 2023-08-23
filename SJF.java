import java.util.*;

class entry
{
	String Process;
	int A_time;
	int B_time;
}

class schd
{
	Scanner sc=new Scanner(System.in);
	int n;
	entry[] e;
	int C_time[];
    	int T_time[];
    	int W_time[];

	
	void input()
	{
		System.out.println("Enter the no. of Processes : ")
		n=sc.nextInt();
		e=new entry[n];
		C_time=new int[n];
       	T_time=new int[n];
	 	W_time=new int[n];
	 	
	 	 for (int i=0;i<n;i++)
        	{
		    e[i]=new calculn();
		    System.out.println("Enter the Process Name : ");
		    e[i].Process=sc.next();
		    System.out.println("Enter the Arrival Time of the Process "+c[i].Process+" : ");
		    e[i].A_time=sc.nextInt();
		    System.out.println("Enter the Burst Time of the Process "+c[i].Process+" : ");
		    e[i].B_time=sc.nextInt();
		    System.out.println(" ");
        	}
        	
        	System.out.println("The Processes Entered  are : ");
		for (int i=0;i<n;i++)
		{
		    System.out.println("Process "+e[i].Process);
		    System.out.println("->");
		    System.out.println("Arrival time of Process "+e[i].Process+" is : "+e[i].A_time);
		    System.out.println("Burst time of Process "+e[i].Process+" is : "+e[i].B_time);
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
		        if (e[j].A_time < e[min].A_time)
		        {
		            min=j;
		        }
		    }
		    schd temp=e[min];
		    e[min]=e[i];
		    e[i]=temp;
		}
	}
	
	void show_process()
	{
		System.out.println("Processes after sorting are : ");
		for (int i=0;i<n;i++)
		{
		    System.out.println(e[i].Process);
		    System.out.println("->");
		    System.out.println("Arrival time of Process "+e[i].Process+" is : "+e[i].A_time);
		    System.out.println("Burst time of Process "+e[i].Process+" is : "+e[i].B_time);
		    System.out.println(" ");
		}
	}
	
	void task()
	{
		sort();
		int count=0;
		int temp[]=new int[n];
		for (int i=0;i<n;i++)
		{
			count=e[i].B_time;
			count=count-1;
			
			if (c[i].B_time>c[i+1].B_time)
			{
				temp[i]=e[i];
				count=e[i].B_time;
				count=count-1;
			}
		}
	}	
}
