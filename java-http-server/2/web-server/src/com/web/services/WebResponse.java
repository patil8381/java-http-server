package com.web.services;

import java.io.*;
public class WebResponse
{
private OutputStream outputStream;
private String contentType;
private boolean headerSent;
public WebResponse(OutputStream outputStream)
{
this.outputStream=outputStream;
this.headerSent=false;
this.contentType="";
}
public void setContentype(String contentType)
{
this.contentType=contentType;
}
public void writeLine(String line) throws IOException
{
if(headerSent==false)
{
StringBuilder responseHeaderBuilder=new StringBuilder();
responseHeaderBuilder.append("HTTP/1.1 200\r\n");
responseHeaderBuilder.append("content-type :"+this.contentType+"\r\n\r\n");
this.outputStream.write(responseHeaderBuilder.toString().getBytes());
this.outputStream.flush();
this.headerSent=true;
}
line=line+"\n";
this.outputStream.flush();
this.headerSent=true;
}

}