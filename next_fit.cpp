#include <iostream>
#include <string>
#include <cstring>
using namespace std;

int main()
{
    int n, m;
    cout << "Enter the no. of blocks of memory: " << endl;
    cin >> n;
    int blk_size[n];
    memset(blk_size, 0, sizeof(blk_size));

    cout << "Enter the Memory block sizes: " << endl;
    for (int i = 0; i < n; i++)
    {
        cin >> blk_size[i];
    }

    cout << "\nThe Memory blocks are: " << endl;
    for (int i = 0; i < n; i++)
    {
        cout << "Memory Block " << i + 1 << " is : " << blk_size[i] << endl;
    }

    cout << "\Enter the no. of processes: " << endl;
    cin >> m;
    int process_size[m];
    bool allocated[n];
    memset(allocated, false, sizeof(allocated));

    cout << "Enter the process sizes: " << endl;
    for (int i = 0; i < m; i++)
    {
        cin >> process_size[i];
    }

    cout << "\nThe processes with size are: " << endl;
    for (int i = 0; i < m; i++)
    {
        cout << "Process " << i + 1 << ": Size is : " << process_size[i] << endl;
    }

    int allocation_block[m];
    string allocation_status[m];

    int j = 0;  // Initialize memory block index
    for (int i = 0; i < m; i++)
    {
        bool allocated_flag = false;

        while (j < n)
        {
            if (!allocated[j] && blk_size[j] >= process_size[i])
            {
                allocation_block[i] = j + 1;
                allocation_status[i] = "Allocated";
                allocated[j] = true;
                allocated_flag = true;
                break;
            }
            j = (j + 1) % n;  // Move to the next block in a circular manner
        }

        if (!allocated_flag)
        {
            allocation_block[i] = -1;
            allocation_status[i] = "Not Allocated";
        }
    }

    cout << "\nProcess\tProcess Size\tMemory Block\tStatus" << endl;
    for (int i = 0; i < m; i++)
    {
        cout << "Process " << i + 1 << "\t" << process_size[i] << "\t";
        if (allocation_block[i] != -1)
        {
            cout << allocation_block[i] << "\t";
        }
        else
        {
            cout << "----\t";
        }
        cout << allocation_status[i] << endl;
    }

    return 0;
}
