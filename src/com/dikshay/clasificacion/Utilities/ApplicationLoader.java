package com.dikshay.clasificacion.Utilities;

import javax.servlet.http.HttpServlet;

public class ApplicationLoader extends HttpServlet{
	public void init()
	{
		Processor.getInstance().setCommand();
		
	}

}
