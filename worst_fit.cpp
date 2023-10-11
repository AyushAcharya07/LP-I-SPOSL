# include <iostream>
# include <string>
# include <cstring>
using namespace std;

int main()
{
    int n,m;
    cout<<"Enter the no. of blocks of memory : "<<endl;
    cin>>n;
    int blk_size[n];
    memset(blk_size,0,sizeof(blk_size));

    cout<<"\nEnter the Memory Block sizes : "<<endl;
    for (int i=0;i<n;i++)
    {
        cin>>blk_size[i];
    }

    cout << "\nThe Memory blocks are: " << endl;
    for (int i = 0; i < n; i++)
    {
        cout << "Memory Block " << i + 1 << " is : " << blk_size[i] << endl;
    }

    cout << "\nEnter the no. of processes: " << endl;
    cin >> m;
    int process_size[m];
    bool allocated[n];

    cout << "Enter the process sizes: " << endl;
    for (int i = 0; i < m; i++)
    {
        cin >> process_size[i];
    }

    cout << "\nThe processes with size are: " << endl;
    for (int i = 0; i < m; i++)
    {
        cout << "Process " << i + 1 << " : Size is : " << process_size[i] << endl;
    }

    memset(allocated,false,sizeof(allocated));

    int allocation_block[m];
    string allocation_status[m];

    for (int i=0;i<m;i++)
    {
        int best_blk=-1;
        int max=-1;
        for (int j=0;j<n;j++)
        {
            if (!allocated[j] && blk_size[j]>=process_size[i])
            {
                if (blk_size[j]-process_size[i]>max)
                {
                    best_blk=j;
                    max=blk_size[j]-process_size[i];
                }
            }
        }

        if (best_blk!=-1)
        {
            allocation_block[i]=best_blk+1;
            allocation_status[i]="Allocated";
            allocated[best_blk]=true;
        }
        else
        {
            allocation_block[i]=-1;
            allocation_status[i]="Not Allocated";
        }
    }

    cout << "\nProcess\t\tProcess Size\t\tMemory Block\t\tStatus" << endl;
    cout << endl;
    for (int i = 0; i < m; i++)
    {
        cout << "Process " << i + 1 << "\t\t" << process_size[i] << "\t";
        if (allocation_block[i] != -1)
        {
            cout << "\t" << allocation_block[i] << "\t";
        }
        else
        {
            cout << "----\t\t";
        }
        cout << allocation_status[i] << endl;
    }

    return 0;

}
