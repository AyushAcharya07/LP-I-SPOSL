#include <iostream>
#include <string>
#include <cstring>
using namespace std;

int main() {
    int n, m, ch, ans;
    cout << "Enter the no. of blocks of memory: " << endl;
    cin >> n;
    int blk_size[n];
    memset(blk_size, 0, sizeof(blk_size));

    cout << "Enter the Memory block sizes: " << endl;
    for (int i = 0; i < n; i++) {
        cin >> blk_size[i];
    }

    cout << "\nThe Memory blocks are: " << endl;
    for (int i = 0; i < n; i++) {
        cout << "Memory Block " << i + 1 << " is : " << blk_size[i] << endl;
    }

    cout << "\Enter the no. of processes: " << endl;
    cin >> m;
    int process_size[m];
    bool allocated[n];

    cout << "Enter the process sizes: " << endl;
    for (int i = 0; i < m; i++) {
        cin >> process_size[i];
    }

    cout << "\nThe processes with size are: " << endl;
    for (int i = 0; i < m; i++) {
        cout << "Process " << i + 1 << " : Size is : " << process_size[i] << endl;
    }

    memset(allocated, false, sizeof(allocated));

    int allocation_block[m];
    string allocation_status[m];
    int j = 0, t = n - 1;

    cout << endl;

    do {
        cout << "\n\t\tMEMORY ALLOCATION ALGORITHMS" << endl;
        cout << "\n1.First_Fit\n2.Best_Fit\n3.Worst_Fit\n4.Next_Fit" << endl;
        cout << "Enter your choice for using Memory Allocation : " << endl;
        cin >> ch;
        switch (ch) {
            case 1:
                memset(allocated, false, sizeof(allocated));
                for (int i = 0; i < m; i++) {
                    bool allocated_flag = false;

                    for (int j = 0; j < n; j++) {
                        if (!allocated[j] && blk_size[j] >= process_size[i]) {
                            allocation_block[i] = j + 1;
                            allocation_status[i] = "Allocated";
                            allocated[j] = true;
                            allocated_flag = true;
                            break;
                        }
                    }

                    if (!allocated_flag) {
                        allocation_block[i] = -1;
                        allocation_status[i] = "Not Allocated";
                    }
                }

                cout << "\nProcess\t\tProcess Size\t\tMemory Block\t\tStatus" << endl;
                cout << endl;
                for (int i = 0; i < m; i++) {
                    cout << "Process " << i + 1 << "\t\t" << process_size[i] << "\t\t";
                    if (allocation_block[i] != -1) {
                        cout << "\t" << allocation_block[i] << "\t\t";
                    } else {
                        cout << "----\t\t";
                    }
                    cout << allocation_status[i] << endl;
                }
                break;

            case 2:
                memset(allocated, false, sizeof(allocated));
                for (int i = 0; i < m; i++) {
                    int best_blk = -1;
                    int min = 1000000;
                    for (int j = 0; j < n; j++) {
                        if (!allocated[j] && blk_size[j] >= process_size[i]) {
                            if (blk_size[j] - process_size[i] < min) {
                                best_blk = j;
                                min = blk_size[j] - process_size[i];
                            }
                        }
                    }

                    if (best_blk != -1) {
                        allocation_block[i] = best_blk + 1;
                        allocation_status[i] = "Allocated";
                        allocated[best_blk] = true;
                    } else {
                        allocation_block[i] = -1;
                        allocation_status[i] = "Not Allocated";
                    }
                }

                cout << "\nProcess\t\tProcess Size\t\tMemory Block\t\tStatus" << endl;
                cout << endl;
                for (int i = 0; i < m; i++) {
                    cout << "Process " << i + 1 << "\t\t" << process_size[i] << "\t";
                    if (allocation_block[i] != -1) {
                        cout << "\t" << allocation_block[i] << "\t\t";
                    } else {
                        cout << "----\t\t";
                    }
                    cout << allocation_status[i] << endl;
                }
                break;

            case 3:
                memset(allocated, false, sizeof(allocated));
                for (int i = 0; i < m; i++) {
                    int best_blk = -1;
                    int max = -1;
                    for (int j = 0; j < n; j++) {
                        if (!allocated[j] && blk_size[j] >= process_size[i]) {
                            if (blk_size[j] - process_size[i] > max) {
                                best_blk = j;
                                max = blk_size[j] - process_size[i];
                            }
                        }
                    }

                    if (best_blk != -1) {
                        allocation_block[i] = best_blk + 1;
                        allocation_status[i] = "Allocated";
                        allocated[best_blk] = true;
                    } else {
                        allocation_block[i] = -1;
                        allocation_status[i] = "Not Allocated";
                    }
                }

                cout << "\nProcess\t\tProcess Size\t\tMemory Block\t\tStatus" << endl;
                cout << endl;
                for (int i = 0; i < m; i++) {
                    cout << "Process " << i + 1 << "\t\t" << process_size[i] << "\t";
                    if (allocation_block[i] != -1) {
                        cout << "\t" << allocation_block[i] << "\t";
                    } else {
                        cout << "----\t\t";
                    }
                    cout << allocation_status[i] << endl;
                }
                break;

            case 4:
                memset(allocated, false, sizeof(allocated));

                for (int i = 0; i < m; i++) {
                    bool allocated_flag = false; // Flag to check if the process is allocated

                    while (j < n) {
                        if (!allocated[j] && blk_size[j] >= process_size[i]) {
                            allocation_block[i] = j + 1;
                            allocation_status[i] = "Allocated";
                            allocated[j] = true;
                            allocated_flag = true;
                            blk_size[j] -= process_size[i];
                            t = (j - 1) % n; // Set a new endpoint
                            break;
                        }

                        if (t == j) {
                            t = (j - 1) % n; // Set a new endpoint
                            break;
                        }

                        j = (j + 1) % n; // Move to the next block
                    }

                    // If the process was not allocated, mark it as "Not Allocated"
                    if (!allocated_flag) {
                        allocation_block[i] = -1; // Invalid block number
                        allocation_status[i] = "Not Allocated";
                    }
                }

                cout << "\nProcess\t\tProcess Size\t\tMemory Block\t\tStatus" << endl;
                cout << endl;
                for (int i = 0; i < m; i++) {
                    cout << "Process " << i + 1 << "\t\t" << process_size[i] << "\t\t";
                    if (allocation_block[i] != -1) {
                        cout << "\t" << allocation_block[i] << "\t\t";
                    } else {
                        cout << "----\t\t";
                    }
                    cout << allocation_status[i] << endl;
                }
                break;

            default:
                cout << "\nYou have Entered a Wrong choice!\nPlease Try Again!" << endl;
                break;
        }
        cout << "\nDo you want to continue?\n1.Yes\n2.No" << endl;
        cin >> ans;
    } while (ans == 1);
}
