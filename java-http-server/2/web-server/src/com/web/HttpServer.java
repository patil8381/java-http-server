package com.web;
import java.io.*;
import java.net.*;
import java.util.*;

public class HttpServer
{
public static void main(String args[])
{
try
{
int port = Integer.parseInt(args[0]);
//File file=new File("web-app\config\services.cfg");
File file=new File("web-app/config/services.cfg");

Map<String,String> servicesMap;
servicesMap=new HashMap<String,String>();

if(file.exists())
{
RandomAccessFile randomAccessFile=new RandomAccessFile(file,"rw");
String line;
String pcs[];
int x=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
x++;
line=randomAccessFile.readLine();
pcs=line.split(",");
if(pcs.length!=2)
{
System.out.println("Issue in config(services.cfg)");
System.out.println("Line number : "+x);
System.out.println("Line : "+line);
System.out.println("Path, class expected");
System.out.println("Read Documentation.");

randomAccessFile.close();
return;
}
if(servicesMap.get(pcs[0])!=null)
{
System.out.println("Duplicate request path : "+pcs[0]);
randomAccessFile.close();
return;
}
servicesMap.put(pcs[0],pcs[1]);
}
randomAccessFile.close();
}



ServerSocket serverSocket=new ServerSocket(port);
System.out.println("Http Web server is ready to accept request on port "+port);
Socket client;
HttpRequestProcessor httpRequestProcessor;
while(true)
{
client = serverSocket.accept();
httpRequestProcessor=new HttpRequestProcessor(client,servicesMap);
}

}catch(Exception e)
{
System.out.println(e);
}
}
}