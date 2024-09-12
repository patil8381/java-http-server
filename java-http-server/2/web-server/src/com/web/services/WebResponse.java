package com.web.services;
import java.io.*;

public class WebResponse
{
private String contentType;
private OutputStream outputStream;
private boolean headerSent;
public WebResponse(OutputStream outputStream)
{
this.headerSent=false;
this.outputStream=outputStream;
this.contentType="";
}
public void setContentType(String contentType)
{
this.contentType=contentType;
}
public void writeLine(String line) throws IOException 
{
if(this.headerSent==false)
{
StringBuilder responseHeaderBuilder;
responseHeaderBuilder= new StringBuilder();
responseHeaderBuilder.append("HTTP/1.1 200\r\r");
responseHeaderBuilder.append("content-type :"+this.contentType+"\r\n\r\n");
this.outputStream.write(responseHeaderBuilder.toString().getBytes());
this.outputStream.flush();
this.headerSent=true;
}
}

}