package com.whatever;
import com.web.Services.*;

public class DateTimeServices extends WebRequestHandler
{
public void processRequest(WebRequest request,WebResponse response)
{
String whatIsRequired=request.getQueryStringValue("giveme");
if(whatIsRequired==null)
{
response.setContentType("text/html");
response.wrteLine("<!DOCTYPE HTML>");
response.wrteLine("<html>");
response.wrteLine("<head>");
response.wrteLine("<title>Some title<title>");
response.wrteLine("</head>");
response.wrteLine("<body>");
response.wrteLine("<h1> INVALID REQUEST </h1>");
response.wrteLine("<a href='index.html'/>Home</a>");
response.wrteLine("</body>");
response.wrteLine("</html>");
}
elseif(whatisRequired.equals("Date"))
{
java.util.Date now=new java.util.Date();
java.util.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy");
String nowString=simpleDateFormate.format(now);

response.setContentType("text/html");
response.wrteLine("<!DOCTYPE HTML>");
response.wrteLine("<html>");
response.wrteLine("<head>");
response.wrteLine("<title>Some title<title>");
response.wrteLine("</head>");
response.wrteLine("<body>");
response.wrteLine("<img href='images/logo.png' />");
response.wrteLine("<h1>Time : "+nowString+"</h1>");
response.wrteLine("<a href='index.html'/>Home</a>");
response.wrteLine("</body>");
response.wrteLine("</html>");

}
elseif(whatisRequired.equals("Time"))
{
java.util.Date now=new java.util.Date();
java.util.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("hh:mm:ss");

String nowString=simpleDateFormate.format(now);

response.setContentType("text/html");
response.wrteLine("<!DOCTYPE HTML>");
response.wrteLine("<html>");
response.wrteLine("<head>");
response.wrteLine("<title>Some title<title>");
response.wrteLine("</head>");
response.wrteLine("<body>");
response.wrteLine("<img href='images/logo.png' />");
response.wrteLine("<h1>Time : "+nowString+"</h1>");
response.wrteLine("<a href='index.html'/>Home</a>");
response.wrteLine("</body>");
response.wrteLine("</html>");

}
elseif(whatisRequired.equals("DateTime"))
{
java.util.Date now=new java.util.Date();
java.util.SimpleDateFormat simpleDateFormat;
simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");

String nowString=simpleDateFormate.format(now);

response.setContentType("text/html");
response.wrteLine("<!DOCTYPE HTML>");
response.wrteLine("<html>");
response.wrteLine("<head>");
response.wrteLine("<title>Some title<title>");
response.wrteLine("</head>");
response.wrteLine("<body>");
response.wrteLine("<img href='images/logo.png' />");
response.wrteLine("<h1>Time : "+nowString+"</h1>");
response.wrteLine("<a href='index.html'/>Home</a>");
response.wrteLine("</body>");
response.wrteLine("</html>");

}
else
{

response.setContentType("text/html");
response.wrteLine("<!DOCTYPE HTML>");
response.wrteLine("<html>");
response.wrteLine("<head>");
response.wrteLine("<title>Some title<title>");
response.wrteLine("</head>");
response.wrteLine("<body>");
response.wrteLine("<h1>Invalid Request</h1>");
response.wrteLine("<a href='index.html'/>Home</a>");
response.wrteLine("</body>");
response.wrteLine("</html>");
}

}

}