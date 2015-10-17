package fileupload;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.catalina.connector.Request;

import com.dikshay.clasificacion.Utilities.Constants;
import com.dikshay.clasificacion.sorting.SortingAlgorithms;

import interfaces.CommandInterface;

public class ReadFileData implements CommandInterface{
	private static ReadFileData readFileData;
	Vector<Integer> fileData = new Vector<Integer>();
	public static ReadFileData getInstance()
	{
		if(readFileData == null)
		{
			readFileData = new ReadFileData();
		}
		return readFileData;
	}
	@Override
	public boolean execute(HashMap<String, String> pRequestParameters, HashMap<String, Object> pRequestStorage,
			HashMap<String, Object> pResponseStorage) throws Exception {
		// TODO Auto-generated method stub
		HttpServletRequest pRequest = (HttpServletRequest) pRequestStorage.get(Constants.SERVLET_REQUEST_COMMAND);
			    Part filePart;
			try {
				filePart = pRequest.getPart("file");
				
			for(Part a : pRequest.getParts())
			{
				String name = a.getName();
				InputStream is = pRequest.getPart(name).getInputStream();
				  BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			        StringBuilder out = new StringBuilder();
			        String line;
			        fileData.removeAllElements();
			        while ((line = reader.readLine()) != null) {
			            out.append(line);
			            fileData.add(Integer.parseInt(line));
			        }

			
				
			/*	
		    String fileName = filePart.getSubmittedFileName();
		    InputStream fileContent = filePart.getInputStream();
		    BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));
	        StringBuilder out = new StringBuilder();
	        String line;
	        fileData.removeAllElements();
	        while ((line = reader.readLine()) != null) {
	            out.append(line);
	            fileData.add(Integer.parseInt(line));
	        }*/
	       Vector<Integer> sortedFileData = new SortingAlgorithms().InsertionSort(fileData);
	       System.out.println(sortedFileData);
	       pResponseStorage.put("SortedVaues", sortedFileData);
			}
			} catch (IOException | ServletException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} // Retrieves <input type="file" name="file">
			
		return false;
	}

}
