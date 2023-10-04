# include <iostream>
# include <string>
# include <cstring>
using namespace std;

int main()
{
	int n,m;
	cout<<"Enter the no. of blocks of memory : "<<endl;
	cin>>n;
	int blk_size[n],allocn[n],flags[n];
	int process_size[m];
	for (int i=0;i<n;i++)
	{
		flags[i]=0;
		allocn[i]=-1;
	}
	cout<<"Enter the Memory block sizes : "<<endl;
	for (int i=0;i<n;i++)
	{
		cin>>blk_size[i];
	}
	cout<<"\nThe Memory blocks are: "<<endl;
	for (int i=0;i<n;i++)
	{
		cout<<"Memory Block "<<i+1<<" is : "<<blk_size[i]<<endl;
	}
	
	cout<<"\nEnter the no. of processes : "<<endl;
	cin>>m;
	cout<<"Enter the process sizes : "<<endl;
	for (int i=0;i<m;i++)
	{
		cin>>process_size[i];
	}
	
	cout<<"\nThe processes with size are : "<<endl;
	for (int i=0;i<m;i++)
	{
		cout<<"Process "<<i+1<<" : Size is : "<<process_size[i]<<endl;
	}
	
	for (int i=0;i<m;i++)
	{
		for (int j=0;j<n;j++)
		{
			if (flags[j]==0 && blk_size[j]>=process_size[i])
			{
				allocn[j]=i;
				flags[j]=1;
				break;
			}
		}
	}
	
	cout<<"\nBlock_No.\tBlock_size\tProcess_No\t\tSize"<<endl;
	for (int i=0;i<n;i++)
	{
		cout<<"\n"<<i+1<<"\t\t"<<blk_size[i]<<"\t\t";
		if (flags[i]==1)
		{
			cout<<allocn[i]+1<<"\t\t\t"<<process_size[allocn[i]];
		}
		else
			cout<<"Not allocated"<<endl;
	}
	
	cout<<endl;
	
}
