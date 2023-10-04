# include <iostream>
# include <string>
# include <cstring>
using namespace std;

int main()
{
	int n,m;
	cout<<"Enter the no. of blocks of memory : "<<endl;
	cin>>n;
	int blk_size[n],allocn[m];
	int process_size[m];
	memset(allocn,-1,sizeof(allocn));
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
			if (blk_size[j]>=process_size[i])
			{
				blk_size[j]=blk_size[j]-process_size[i];
				cout<<"Process "<<i+1<<" is allocated to Memory Block "<<j+1<<endl;
			}
		}
	}
	
}
