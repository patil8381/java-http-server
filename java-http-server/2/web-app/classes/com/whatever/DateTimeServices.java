package com.whatever;
import com.web.services.*;	//created by Amit 
				//webRequestHandler and abstract class created by Amit

public class DateTimeServices extends WebRequestHandler
{
public void processRequest(WebRequest request,WebResponse response)
{

try
{
String whatIsRequired=request.getQueryStringValue("giveme");

if(whatIsRequired==null)
{
response.setContentType("text/html");
response.writeLine("<!DOCTYPE HTML>");
response.writeLine("<html>");
response.writeLine("<head>");
response.writeLine("<title>Some title</title>");
response.writeLine("</head>");
response.writeLine("<body>");
response.writeLine("<h1> INVALID REQUEST </h1>");
response.writeLine("<a href='index.html'/>Home</a>");
response.writeLine("</body>");
response.writeLine("</html>");
}
else if(whatIsRequired.equals("date"))
{

java.util.Date now=new java.util.Date();
java.text.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new java.text.SimpleDateFormat("dd/MM/yyyy");
String nowString=simpleDateFormat.format(now);

response.setContentType("text/html");
response.writeLine("<!DOCTYPE HTML>");
response.writeLine("<html>");
response.writeLine("<head>");
response.writeLine("<title>Some title</title>");
response.writeLine("</head>");
response.writeLine("<body>");
response.writeLine("<img href='images/logo.png' />");
response.writeLine("<h1>Time : "+nowString+"</h1>");
response.writeLine("<a href='index.html'/>Home</a>");
response.writeLine("</body>");
response.writeLine("</html>");

}
else if(whatIsRequired.equals("time"))
{
java.util.Date now=new java.util.Date();
java.text.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new java.text.SimpleDateFormat("hh:mm:ss");

String nowString=simpleDateFormat.format(now);

response.setContentType("text/html");
response.writeLine("<!DOCTYPE HTML>");
response.writeLine("<html>");
response.writeLine("<head>");
response.writeLine("<title>Some title</title>");
response.writeLine("</head>");
response.writeLine("<body>");
response.writeLine("<img href='images/logo.png' />");
response.writeLine("<h1>Time : "+nowString+"</h1>");
response.writeLine("<a href='index.html'/>Home</a>");
response.writeLine("</body>");
response.writeLine("</html>");

}
else if(whatIsRequired.equals("datetime"))
{

java.util.Date now=new java.util.Date();
java.text.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new java.text.SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

String nowString=simpleDateFormat.format(now);

response.setContentType("text/html");
response.writeLine("<!DOCTYPE HTML>");
response.writeLine("<html>");
response.writeLine("<head>");
response.writeLine("<title>Some title</title>");
response.writeLine("</head>");
response.writeLine("<body>");
response.writeLine("<img href='images/logo.png' />");
response.writeLine("<h1>Date and Time is : "+nowString+"</h1>");
response.writeLine("<a href='index.html'/>Home</a>");
response.writeLine("</body>");
response.writeLine("</html>");

}
else
{

response.setContentType("text/html");
response.writeLine("<!DOCTYPE HTML>");
response.writeLine("<html>");
response.writeLine("<head>");
response.writeLine("<title>Some title</title>");
response.writeLine("</head>");
response.writeLine("<body>");
response.writeLine("<h1>Invalid Request</h1>");
response.writeLine("<a href='index.html'/>Home</a>");
response.writeLine("</body>");
response.writeLine("</html>");
}

}catch(Exception e)
{
System.out.println(e);
}
}

}