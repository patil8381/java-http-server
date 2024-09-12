package com.web;
import java.io.*;
import java.net.*;
import java.util.*;
import com.web.services.*;

public class HttpRequestProcessor
{
private Socket client;
private Map<String,String> servicesMap;

HttpRequestProcessor(Socket client,Map<String,String> servicesMap)
{
this.servicesMap=servicesMap;
this.client=client;
run();
}
public void run()
{
try
{
String queryString=null;
Map<String,String> requestDataMap;
	// I should not taken map
	//because request data can have duplicate names.

		//code to process request and send response
requestDataMap=new HashMap<String,String>();

WebRequest webRequest;
WebResponse webResponse;
WebRequestHandler webRequestHandler;


System.out.println("---------------------------------------");
System.out.println("Web Request arrived");
InputStream inputStream;
InputStreamReader inputStreamReader;
BufferedReader bufferedReader;
inputStream=this.client.getInputStream();
inputStreamReader=new InputStreamReader(inputStream);
bufferedReader=new BufferedReader(inputStreamReader);

String line;
System.out.println("Reading Infrastructure created");
//Following loop reads the HTTP request Header
String requestPath=null;
while(true)
{
line=bufferedReader.readLine();
if(line==null || line.equals("")) break;
if(line.toUpperCase().startsWith("GET "))
{
String pcs[];
pcs=line.split(" ");
requestPath=pcs[1];
}
System.out.println(line);
}
if(requestPath==null)
{
client.close();
return;
}
OutputStream outputStream=client.getOutputStream();
String fileToServe="";
if(requestPath.equals("/"))
{
if(new File("web-app/index.htm").exists())
{
fileToServe="web-app/index.htm";
}else if(new File("web-app/index.html").exists())
{
fileToServe="web-app/index.html";
}
}else
{
int indexOfQuestionMark=requestPath.indexOf("?");
if(indexOfQuestionMark!=-1)
{
//requestPath has ?
System.out.println("------------");
queryString=requestPath.substring(0,indexOfQuestionMark+1);
String nameValuePairs[];
nameValuePairs=queryString.split("&");
if(nameValuePairs!=null)
{
int j;
int indexOfEqualTo;
String name,value;
for(j=0;j<nameValuePairs.length;j++)
{
indexOfEqualTo=nameValuePairs[j].indexOf("=");

name=nameValuePairs[j].substring(0,indexOfEqualTo);


if(indexOfEqualTo<nameValuePairs[j].length()-1)
{
value=nameValuePairs[j].substring(indexOfEqualTo+1);
}
else
{
value="";
}
name=URLDecoder.decode(name);
value=URLDecoder.decode(value);
requestDataMap.put(name,value);
}
}
System.out.println("------------");

String requestPathWithoutQueryString=requestPath.substring(0,indexOfQuestionMark);
if(this.servicesMap.containsKey(requestPathWithoutQueryString))
{
String whichClass;
whichClass=this.servicesMap.get(requestPathWithoutQueryString);

try
{
Class c=Class.forName(whichClass);
webRequestHandler=(WebRequestHandler)c.newInstance();
webRequest=new WebRequest(requestDataMap);
webResponse=new WebResponse(outputStream);
webRequestHandler.processRequest(webRequest,webResponse);
System.out.println("------------");

}catch(ClassNotFoundException e)
{
System.out.println(e);

}
this.client.close();
return;
}
System.out.println("------------");

fileToServe="web-app"+requestPath.substring(0,indexOfQuestionMark);

}
else if(this.servicesMap.containsKey(requestPath))
{
			// TEMPORARY CODE in this else if block
		
System.out.println("request path does not have  '=' in URL.");

}
else
{
fileToServe="web-app"+requestPath;
}

}

//code to read http request data
System.out.println("Preparing response");
String responseHeader;
StringBuilder responseHeaderBuilder=new StringBuilder();
byte responseHeaderByte[];
//responseHeaderByte=responseHeader.getBytes();
if(fileToServe.equals(""))
{
	//this means that path is "/" and index.htm and index.html not found

responseHeaderBuilder.append("HTTP/1.1 404\r\n\r\n");

responseHeader=responseHeaderBuilder.toString();
responseHeaderByte=responseHeader.getBytes();

outputStream.write(responseHeaderByte);
outputStream.flush();
client.close();
}
File file=new File(fileToServe);
if(file.exists()==false)
{
responseHeaderBuilder.append("HTTP/1.1 404\r\n\r\n");

responseHeader=responseHeaderBuilder.toString();
responseHeaderByte=responseHeader.getBytes();

outputStream.write(responseHeaderByte);
outputStream.flush();
client.close();
}


//the file to server exists, it may be a huge file 
//so we will write in chunks of 4096
long contentLength=file.length();	//file size;
System.out.println(fileToServe+","+contentLength);
String contentType=URLConnection.guessContentTypeFromName(file.getName());
responseHeaderBuilder.append("HTTP/1.1 200\r\n");
responseHeaderBuilder.append("content-type: "+contentType+"\r\n");
responseHeaderBuilder.append("content-length: "+contentLength+"\r\n\r\n");
responseHeader=responseHeaderBuilder.toString();
responseHeaderByte=responseHeader.getBytes();
outputStream.write(responseHeaderByte);
outputStream.flush();

FileInputStream fileInputStream = new FileInputStream(fileToServe);
byte buffer[] = new byte[4096];
long totalBytesFetched=0;
int bytesFetched;
while(totalBytesFetched<contentLength)
{
bytesFetched=fileInputStream.read(buffer);
outputStream.write(buffer,0,bytesFetched);
outputStream.flush();
totalBytesFetched=totalBytesFetched+bytesFetched;
}
fileInputStream.close();
this.client.close();

}catch(Exception e)
{
System.out.println(e);
}
}
}