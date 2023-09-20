/*INPUT FILES:
mnt.txt
ONE 2 1 1 1
TWO 2 1 5 2
mdt.txt
MOVER (P,3) (P,1)
ADD (P,3) (P,2)
MOVEM (P,3) (P,1)
MEND
MOVER (P,3) (P,1)
ADD (P,3) (P,2)
MOVEM (P,3) (P,1)
MOVER CREG, (P,1)
ADD CREG,9
MOVEM CREG, (P,1)
MEND
ir.txt
START
READ O
READ T
TWO T, 7
PRINT O
PRINT T
STOP
O DS 1
T DS 1
END
kpdt.txt
1 E AREG
2 O DREG
CODE:*/
package macroP2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;


public class PASS2_MACRO 
{
	public static void main (String[] args) throws IOException {
	BufferedReader mntb=new BufferedReader(new FileReader("mnt.txt"));
	BufferedReader mdtb=new BufferedReader(new FileReader("mdt.txt"));
	BufferedReader kpdtb=new BufferedReader(new FileReader("kpdt.txt"));
	BufferedReader irb=new BufferedReader(new FileReader("ir.txt"));
	FileWriter fr=new FileWriter("pass2.txt");
	HashMap<String,MNTEntry>mnt=new HashMap<String,MNTEntry>();
	HashMap<Integer,String>aptab=new HashMap<Integer,String>();
	HashMap<String,Integer>aptabinverse=new HashMap<String,Integer>();
	Vector<String>mdt=new Vector<String>();
	Vector<String>kpdt=new Vector<String>();
	String line;
	int mdtp,kpdtp,pp,kp,paramNo;
	while((line=mdtb.readLine())!=null)
	{
	mdt.addElement(line);
	}
	while((line=kpdtb.readLine())!=null){
	kpdt.addElement(line);
	}
	while((line=mntb.readLine())!=null){
	String[] parts=line.split(" ");
	mnt.put(parts[0], new MNTEntry(parts[0],Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),Integer.parseInt(parts[3]),Integer.parseInt(parts[4])));
	}
	while((line=irb.readLine())!=null)
	{
	String []parts=line.split("\\s+");
	if(mnt.containsKey(parts[0]))
	{
	pp=mnt.get(parts[0]).getpp();
	kp=mnt.get(parts[0]).getkp();
	kpdtp=mnt.get(parts[0]).getkpdtp();
	mdtp=mnt.get(parts[0]).getmdtp();
	paramNo=1;
	for(int i=0;i<pp;i++)
	{
	parts[paramNo]=parts[paramNo].replace(",", "");
	aptab.put(paramNo, parts[paramNo]);
	aptabinverse.put(parts[paramNo], paramNo);
	paramNo++;
	}
	int j=kpdtp-1;
	for(int i=0;i<kp;i++)
	{
	String temp[]=kpdt.get(j).split(" ");aptab.put(paramNo,temp[1]);
	aptabinverse.put(temp[0],paramNo);
	j++;
	paramNo++;
	}
	for(int i=pp+1;i<parts.length;i++) {
	parts[i]=parts[i].replace(",", "");
	String []split=parts[i].split("=");
	String name=split[0].replace("&", "");
	aptab.put(aptabinverse.get(name),split[1]);
	paramNo++;
	}
	int i=mdtp-1;
	while(!mdt.get(i).equalsIgnoreCase("MEND"))
	{
	String splits[]=mdt.get(i).split(" ");
	fr.write("+");
	for(int k=0;k<splits.length;k++)
	{
	if(splits[k].contains("(P,"))
	{
	splits[k]=splits[k].replaceAll("[^0-9]", "");
	String
	value=aptab.get(Integer.parseInt(splits[k]));
	fr.write(value+"\t");
	}
	else
	{
	fr.write(splits[k]+"\t");
	}
	}
	fr.write("\n");
	i++;
	}
	}
	else
	{
	}
	aptab.clear();
	aptabinverse.clear();
	fr.write(line+"\n");
	}
	fr.close();
	mntb.close();
	mdtb.close();
	kpdtb.close();
	irb.close();
	System.out.println("Macro Pass2 done!");
	}
}

package macroP2;
public class MNTEntry {
int pp,kp,mdtp,kpdtp;
String S;
public MNTEntry(String s,int pp,int kp,int mdtp,int kpdtp) {
this.S=s;
this.pp=pp;
this.kp=kp;
this.mdtp=mdtp;
this.kpdtp=kpdtp;
}
public String getname() {
return S;
}
public int getpp() {
return pp;
}
}
public void setpp(int data) {
this.pp=data;
}
public void setkp(int data) {
this.kp=data;
}
public void setmdtp(int data) {
this.mdtp=data;
}
public void setkpdtp(int data) {
this.kpdtp=data;
}
public int getkp() {
return kp;
}
public int getmdtp() {
return mdtp;
}
public int getkpdtp() {
return kpdtp;
}
/*OUTPUT:
pass2.txt
START
READ O
READ T
+MOVER
DREG
+ADD DREG 7
+MOVEM
DREG
+MOVER
CREG,
+ADD CREG,9
+MOVEM
CREG,
PRINT O
PRINT T
STOP
O DS 1
T DS 1
END
T
T
T
T*/
