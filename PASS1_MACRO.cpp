# include <iostream>
# include <vector>
# include <fstream>
using namespace std;
int main()
{
	vector<string> mdt;
	vector<string> mnt;
	vector <char> pnt[4];
	fstream file;
	int pointer=0;
	file.open("macro_input.txt",ios::in);
	string s = "",str="";
	int c = 0,r=0,a=-1,b=0;
	while(getline(file,s))
	{
		if(s=="MACRO")
		{
			b=1;
			c=1;
			a++;
			continue;
		}
		for(auto it:s)
		{
			if(r==1)
			{
				int z=0;
				for(auto x:pnt[a])
				{
					if(x==it)
					{
						z=1;
					}
				}
				if(z==0)
				{
					pnt[a].push_back(it);
				}
				r=0;
			}
			if(it=='&')
			{
				r=1;
			}
		}
		if(b==1)
		{
			str="";
			int v=0;
			char t=s[v];
			while(t!=' ')
			{
				str=str+t;
				v++;
				t=s[v];
			}
			mnt.push_back(str);
			b=0;
		}
		if(c==1)
		{
			mdt.push_back(s);
		}
		if(s=="MEND")
		{
			c=0;
		}
	}
	file.close();
	cout<<"MDT"<<endl;
	for(auto it:mdt)
	{
		cout<<it<<endl;
	}
	cout<<endl;
	for(int i = 0 ; i<=a ; i++)
	{
		cout<<"PNT"<<i<<endl;
		for(auto it:pnt[i]){
		cout<<it<<endl;
		}
		cout<<endl;
	}
	cout<<"MNT"<<endl;
	for(auto it:mnt){
	cout<<it<<endl;
	}
	return 0;
}

/*OUTPUT :
MDT
ONE &O,&N,&E=AREG
MOVER &E,&O
ADD &E,&N
MOVEM &E,&O
MEND
TWO &T,&W,&O=DREG
MOVER &O,&T
ADD &O,&W
MOVEM &O,&T
MEND
PNT0
O
N
E
PNT1
T
WO
MNT
ONE
TWO*/
