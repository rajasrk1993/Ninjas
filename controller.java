package com;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;






import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.google.gson.Gson;



@RestController 
public class controller {
	

	HttpSession httpsession;
	
	
	@Autowired
	service task;


	


	@RequestMapping(value = "/rest/addUser", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String addUser(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo addUser = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		addUser= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.addUser(addUser,request,response);
		
		
	}
	@RequestMapping(value = "/rest/deleteUser", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String deleteUser(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo deleteUser = new taskPojo();
		
		Gson gson = new Gson();
		deleteUser= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.deleteUser(deleteUser,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/updateUser", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String updateUser(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo updateUser = new taskPojo();
		
		Gson gson = new Gson();
		updateUser= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.updateUser(updateUser,request,response);
		
		
	}
	@RequestMapping(value = "/rest/getUser", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getUser(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
	
		return task.getUser(request,response);
		
		
	}
	
	
	
	
	@RequestMapping(value = "/rest/login", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String login(@RequestBody String jsonInput,HttpServletRequest request, HttpServletResponse response) throws  SQLException, NoSuchAlgorithmException{
		taskPojo list=new taskPojo();
		Gson gson = new Gson();
		list= gson.fromJson(jsonInput.toString(),taskPojo.class);
		return task.login(list,request,response);
		
		
	}
	@RequestMapping(value = "/rest/logout", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response) throws  SQLException{
		
		return task.logout(request,response);
		
		
	}
	@RequestMapping(value = "/rest/session", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String session(HttpServletRequest request, HttpServletResponse response) throws  SQLException{
		
		return task.session(request,response);
		
		
	}
	
	
	
	
	@RequestMapping(value = "/rest/addVehicle", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String addVehicle(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo addVehicle = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		addVehicle= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.addVehicle(addVehicle,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/addCustomer", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String addCustomer(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo addCustomer = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		addCustomer= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.addCustomer(addCustomer,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/addSupplier", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String addSupplier(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo addSupplier = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		addSupplier= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.addSupplier(addSupplier,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/addDrivers", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String addDrivers(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo addDrivers = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		addDrivers= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.addDrivers(addDrivers,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/getDetails", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getDetails(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
	
		return task.getDetails(request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/createHourlyTrip", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String createHourlyTrip(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo trip = new taskPojo();
		System.out.println("inside add user controller "+jsonInput);
		Gson gson = new Gson();
		trip= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.createHourlyTrip(trip,request,response);
		
		
	}
	@RequestMapping(value = "/rest/supplierAmount", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String supplierAmount(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo amt = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		amt= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
		return task.supplierAmount(amt,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/getSupplierAmount", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getSupplierAmount(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
		System.out.println("inside add user controller ");
		
	
		return task.getSupplierAmount(request,response);
		
		
	}
	@RequestMapping(value = "/rest/updateSupplierAmount", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String updateSupplierAmount(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo amt = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		amt= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
	
		return task.updateSupplierAmount(amt,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/supplierAmountDetails", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String supplierAmountDetails(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo id = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		id= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
	
		return task.supplierAmountDetails(id,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/getCustomerAmount", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getCustomerAmount(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
		System.out.println("inside add user controller ");
		
	
		return task.getCustomerAmount(request,response);
		
		
	}
	@RequestMapping(value = "/rest/customerAmountDetails", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String customerAmountDetails(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo id = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		id= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
	
		return task.customerAmountDetails(id,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/updateCustomerAmount", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String updateCustomerAmount(@RequestBody String jsonInput,HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		taskPojo amt = new taskPojo();
		System.out.println("inside add user controller ");
		Gson gson = new Gson();
		amt= gson.fromJson(jsonInput.toString(),taskPojo.class);
	
	
		return task.updateCustomerAmount(amt,request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/getSupplierData", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getSupplierData(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
		System.out.println("inside add user controller ");
		
	
		return task.getSupplierData(request,response);
		
		
	}
	@RequestMapping(value = "/rest/getCustomerData", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getCustomerData(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
		System.out.println("inside add user controller ");
		
	
		return task.getCustomerData(request,response);
		
		
	}
	@RequestMapping(value = "/rest/getDriverData", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getDriverData(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
		System.out.println("inside add user controller ");
		
	
		return task.getDriverData(request,response);
		
		
	}
	@RequestMapping(value = "/rest/getVehicleData", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getVehicleData(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
		System.out.println("inside add user controller ");
		
	
		return task.getVehicleData(request,response);
		
		
	}
	
	@RequestMapping(value = "/rest/voucher", method = RequestMethod.POST)
	// Response for Given Request
	@ResponseBody
	public String getVoucher(HttpServletRequest request,HttpServletResponse response) throws  SQLException{
		
		System.out.println("inside add user controller ");
		
	
		return task.getVoucher(request,response);
		
		
	}
	
	 @RequestMapping(value="/rest/download", method = RequestMethod.GET)
	    public void downloadFile(HttpServletRequest request,HttpServletResponse response) throws IOException {
		 
		 
		 String valueAsString = request.getParameter("url");
	        File file = null;
	        
	            file = new File(valueAsString);
	    
	         
	        if(!file.exists()){
	            String errorMessage = "Sorry. The file you are looking for does not exist";
	            System.out.println("Sorry. The file you are looking for does not exist");
	            OutputStream outputStream = response.getOutputStream();
	            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	            outputStream.close();
	            return;
	        }
	         
	        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
	        if(mimeType==null){
	            System.out.println("mimetype is not detectable, will take default");
	            mimeType = "application/octet-stream";
	        }
	         
	        System.out.println("mimetype : "+mimeType);
	         
	        response.setContentType(mimeType);
	         
	        /* "Content-Disposition : inline" will show viewable types [like images/text/pdf/anything viewable by browser] right on browser 
	            while others(zip e.g) will be directly downloaded [may provide save as popup, based on your browser setting.]*/
	        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
	 
	         
	        /* "Content-Disposition : attachment" will be directly download, may provide save as popup, based on your browser setting*/
	        //response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	         
	        response.setContentLength((int)file.length());
	 
	        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
	 
	        //Copy bytes from source to destination(outputstream in this example), closes both streams.
	        FileCopyUtils.copy(inputStream, response.getOutputStream());
	    }
	
	
	
	
	
	
	
	
	

}
