package com.dikshay.clasificacion.servlet;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.dikshay.clasificacion.Utilities.Constants;
import com.dikshay.clasificacion.Utilities.Processor;


@WebServlet("/upload")
@MultipartConfig
public class BaseServlet extends HttpServlet{
	ServletContext lServletContext;

	public void doGet(HttpServletRequest pRequest,HttpServletResponse pResponse)
	{
		doPost(pRequest,pResponse);
	}
	public void doPost(HttpServletRequest pRequest,HttpServletResponse pResponse)
	{

		HashMap<String,String> pRequestParameters;
		HashMap<String,Object> pRequestStorage = new HashMap<String,Object>();
		HashMap<String,Object> pResponseStorage = new HashMap<String,Object>();
		pRequestParameters = getParameterNames(pRequest);
		try{
			pRequestStorage.put(Constants.SERVLET_REQUEST_COMMAND, pRequest);
			pResponseStorage.put(Constants.SERVLET_RESPONSE_COMMAND, pResponse);
			Processor.getInstance().getCommand(pRequest).execute(pRequestParameters, pRequestStorage, pResponseStorage);
			processForward(pRequest,pResponse,pResponseStorage);
		}
		catch(Exception e){
			e.printStackTrace(); 
		}
	}

	private HashMap getParameterNames(HttpServletRequest pRequest)
	{
		HashMap<String,String> lRequestParameters = new HashMap<String,String>();	
		String lParameterKey,lParameterValue;
		String lParameterValuesArray[];
		Enumeration<String> lParameterNames = pRequest.getParameterNames();
		while(lParameterNames.hasMoreElements())
		{
			lParameterValue = "";
			lParameterKey = (String)lParameterNames.nextElement();
			lParameterValuesArray = pRequest.getParameterValues(lParameterKey);
			for(String lValue : lParameterValuesArray)
			{
				lParameterValue += lValue;
			}
			lRequestParameters.put(lParameterKey, lParameterValue);
		}
		return lRequestParameters;
		
	}
	public void init(ServletConfig pServletConfig)
	{
		lServletContext = pServletConfig.getServletContext();
	}
	public void processForward(HttpServletRequest pRequest,HttpServletResponse pResponse, HashMap<String,Object> pResponseStorage)
	{
		try{/*
		RequestDispatcher lRequestDispatcher;
		pRequest.setAttribute(Constants.RESPONSE_STORAGE, pResponseStorage);
		lRequestDispatcher = lServletContext.getRequestDispatcher("/index.jsp");
		lRequestDispatcher.forward(pRequest, pResponse);*/
		PrintWriter out = pResponse.getWriter();
		pResponse.setContentType("text/html");
		pResponse.setHeader("Cache-control", "no-cache, no-store");
		pResponse.setHeader("Pragma", "no-cache");
		pResponse.setHeader("Expires", "-1");
		pResponse.setHeader("Access-Control-Allow-Origin", "*");
		pResponse.setHeader("Access-Control-Allow-Methods", "POST,GET");
		pResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
		pResponse.setHeader("Access-Control-Max-Age", "86400");
		JSONObject newObject = new JSONObject();
		newObject.put(Constants.RESPONSE_STORAGE, pResponseStorage);
		newObject.put("success", true);
		out.println(newObject.toString());
		out.close();
		}
		catch(IOException e)
		{ 
			e.printStackTrace();
		}
		/*catch(ServletException e)
		{
			
		}*/ catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
