import java.io.*;
import java.net.*;

class HttpRequestProcessor
{
private Socket client;
HttpRequestProcessor(Socket client)
{
this.client=client;
run();
}
public void run()
{
try
{
System.out.println("-------------------------------");
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
while(true)
{
line=bufferedReader.readLine();
if(line==null || line.equals("")) break;
System.out.println(line);
}

//code to read http request data
System.out.println("Preparing response");
String responseHeader;
StringBuilder responseHeaderBuilder=new StringBuilder();
responseHeaderBuilder.append("HTTP/1.1 404\r\n");
responseHeaderBuilder.append("Content-type: text/html\r\n\r\n");

responseHeader=responseHeaderBuilder.toString();

byte responseHeaderByte[];
responseHeaderByte=responseHeader.getBytes();

StringBuilder responseBuilder=new StringBuilder();
responseBuilder.append("<!DOCTYPE HTML>\n");
responseBuilder.append("<html>\n");
responseBuilder.append("<head>\n");
responseBuilder.append("<meta charset='utf-8' >\n");
responseBuilder.append("<title>Thinking Machines-Student Portal</title>\n");
responseBuilder.append("</head>\n");
responseBuilder.append("<body>\n");
responseBuilder.append("<h1>Student  Portal</h1>\n");
responseBuilder.append("<body>\n");
responseBuilder.append("</html>\n");

String response=responseBuilder.toString();
byte responseByte[]=response.getBytes();

OutputStream outputStream=this.client.getOutputStream();
outputStream.write(responseHeaderByte);
outputStream.flush();
outputStream.write(responseByte);
outputStream.flush();

outputStream.close();

this.client.close();		//socket closed
}catch(Exception e)
{
System.out.println(e);
}
}
}

class HttpServer
{
public static void main(String args[])
{
try
{
int port = Integer.parseInt(args[0]);
ServerSocket serverSocket=new ServerSocket(port);
System.out.println("Http Web server is ready to accept request on port "+port);
Socket client;
HttpRequestProcessor httpRequestProcessor;
while(true)
{
client = serverSocket.accept();
httpRequestProcessor=new HttpRequestProcessor(client);
}

}catch(Exception e)
{
System.out.println(e);
}
}
}