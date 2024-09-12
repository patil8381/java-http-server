package com.web.services;
import java.util.*;

public class WebRequest
{
private Map<String,String> nameValuePairMap;
public WebRequest(Map<String,String> nameValuePairMap)
{
this.nameValuePairMap=nameValuePairMap;
}
public String getQueryStringValue(String name)
{
return this.nameValuePairMap.get(name);
}
}