package com.web.services;

public abstract class WebRequestHandler
{
abstract public void processRequest(WebRequest request,WebResponse response);
}