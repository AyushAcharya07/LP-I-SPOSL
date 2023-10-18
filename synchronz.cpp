# include <iostream>
# include <string>
# include <cstring>
using namespace std;

void wait(int S)
{
	S--;
}

void signal(int S)
{
	S++;
}

class Sync
{
	public:
	int mutex=1,full=0,buff_size,user;
	void input()
	{
		cout<<"\nEnter the Buffer Size : "<<endl;
		cin>>buff_size;
		cout<<"\nEnter \n1.Producer\n2.Consumer : "<<endl;
		cin>>user;
	}
	int empty=buff_size;
	bool buff[buff_size];
	memset(buff,false,sizeof(buff));
	
	if (user==1)
	{
		wait(empty);
		cout<<"\nProducer is producing item"<<endl;
		int i=0;
		buff[i]=true;
		wait(mutex);
		if (mutex==1)
		{
			cout<<"\nConsumer cannot consume the items from buffer"<<endl;
			
		}
		
	}
	
	if (user==2)
	{
		signal(mutex);
		cout<<"\nConsumer is "
	}
	
	
};
