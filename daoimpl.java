package com;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
@Repository
public class daoimpl implements dao{
	// object for JDBC template
	@Autowired
private  JdbcTemplate jdbcTemplate;
// setter for datasource
	
	@Autowired
	HttpSession httpsession;

public void setDataSource(DataSource dataSource) {
	this.jdbcTemplate = new JdbcTemplate(dataSource);
}



@Override
public String login(taskPojo list,HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
	 String msg=null;
	 taskPojo login =new taskPojo();

		String Encrypt =encryptPassword(list.getPassword());	
		
		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("select user_name,password from login_details where user_name =?",new Object[]{list.getUserName()}) ;
		for (Map<String, Object> tempRow : checkUser) {
			
			login.setUserName( (String) tempRow.get("user_name"));
			login.setPassword( (String) tempRow.get("password"));
		
		
		}
		if(checkUser.isEmpty()){
			msg="Either username or password is incorrect";
		}
		
		else	if (login.getPassword().equals(Encrypt)){
			HttpSession session=request.getSession();
	
		     session.setAttribute("user_name",login.getUserName()); 
		     msg="success";
		}
		else{
			msg="Either username or password is incorrect";
		}
	Gson json=new Gson();
	  String returnString=json.toJson(msg);
	
	return returnString;
}

@Override
public String logout(HttpServletRequest request, HttpServletResponse response) {

	 HttpSession session=request.getSession();  
     session.invalidate();  
	 
     Gson json=new Gson();
	  String returnString=json.toJson("logout success");
	return returnString;
}
@Override
public String session(HttpServletRequest request, HttpServletResponse response) {

	String msg=new String();
	
	HttpSession session=request.getSession();
	
	
    if(session.getAttribute("user_name")!=null){
    	 msg="not expired";

    }
   else{
    	msg="expired";	
    	
    }


    Gson json=new Gson();
    String returnString=json.toJson(msg);
    return returnString;	 
	
}

public static String encryptPassword(String input) throws NoSuchAlgorithmException {
	StringBuilder sb = new StringBuilder();
	

		MessageDigest digest =MessageDigest.getInstance("SHA-256");

		byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));

		for (int i = 0; i < hash.length; i++) {
			sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16)
					.substring(1));
		}
		System.out.println(sb.toString());
			// TODO Auto-generated method stub
		return sb.toString();
	}


@Override
public String addUser(taskPojo addUser,HttpServletRequest request, HttpServletResponse response) {
	// TODO Auto-generated method stub
	
	String msg=null;
	String check=null;
	HttpSession session=request.getSession();
	
	String admin=(String) session.getAttribute("user_name");
	try{
	if(admin.equals("admin")){
	List<Map<String, Object>> checkUser = jdbcTemplate
			.queryForList("select user_name from login_details where user_name =?",new Object[]{addUser.getUserName()}) ;
	for (Map<String, Object> tempRow : checkUser) {
		
		check=(String) tempRow.get("user_name");
	
	}
	String encryptPass = null;
	try {
		encryptPass = encryptPassword(addUser.getPassword());
	} catch (NoSuchAlgorithmException e) {
		
	}
	if (!(check==null))
	 msg="UserName already exists!";
	else{
		int insertUser=jdbcTemplate.update("INSERT INTO login_details(user_name,password)VALUES(?,?)",new Object[]{addUser.getUserName(),encryptPass});
        
		if (insertUser==1)
         {
        	 msg="Successfully Added";
         }
         else {
        	 msg="SomeThing  went wrong";
         }
	}
	}else{
		msg="You are not admin";
		
	}
	}catch(Exception e){
		 msg="Please Check all the inputs are proper";
	}
	Gson json=new Gson();
    String returnString=json.toJson(msg);
    return returnString;	 
}




@Override
public String deleteTask(taskPojo deleteUser, HttpServletRequest request,
		HttpServletResponse response) {
	String msg=null;
	String check=null;
	HttpSession session=request.getSession();
	
	String admin=(String) session.getAttribute("user_name");
	try{
	if(admin.equals("admin")){
	
	
		int insertUser=jdbcTemplate.update("DELETE FROM login_details WHERE user_name=? ",new Object[]{deleteUser.getUserName()});
        
		if (insertUser==1)
         {
        	 msg="Successfully Deleted";
         }
         else {
        	 msg="SomeThing  went wrong";
         }
	
	}else{
		msg="You are not admin";
		
	}
	}catch(Exception e){
		 msg="Please Check all the inputs are proper";
	}
	Gson json=new Gson();
    String returnString=json.toJson(msg);
    return returnString;	 
}


@Override
public String updateUser(taskPojo updateUser, HttpServletRequest request,
		HttpServletResponse response) {
	String msg=null;
	
	HttpSession session=request.getSession();
	
	String admin=(String) session.getAttribute("user_name");
	String encryptPass = null;
	try{
	try {
		encryptPass = encryptPassword(updateUser.getPassword());
	} catch (NoSuchAlgorithmException e) {
		
	}

	
	if(admin.equals("admin")){
	
	
		int insertUser=jdbcTemplate.update("UPDATE login_details SET  password=? WHERE user_name=? ",new Object[]{encryptPass,updateUser.getUserName()});
        
		if (insertUser==1)
         {
        	 msg="Successfully Updated";
         }
         else {
        	 msg="SomeThing  went wrong";
         }
	
	}else{
		msg="You are not admin";
		
	}
	}catch(Exception e){
		 msg="Please Check all the inputs are proper";
	}
	Gson json=new Gson();
    String returnString=json.toJson(msg);
    return returnString;	
}


@Override
public String getUser(HttpServletRequest request, HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	try{
	List<String> names=new ArrayList<String>();
	
		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("select user_name from login_details") ;
		for (Map<String, Object> tempRow : checkUser) {
			
			names.add((String) tempRow.get("user_name"));
		
		}
		
		names.remove("admin");
	
	     returnString=json.toJson(names);
		
	}
	catch(Exception e){
		
		  returnString=json.toJson("Error occured");
	}
	
    return returnString;	
}


@Override
public String addVehicle(taskPojo addVehicle, HttpServletRequest request,
		HttpServletResponse response) {
	
	String msg=null;
HttpSession session=request.getSession();
	
	String user=(String) session.getAttribute("user_name");
	
	try{
		int insertUser=jdbcTemplate.update("INSERT INTO vehicle_details( vehiclenumber, vehicletype, vehiclemake, chasenumber, enginenumber,vehicleid, addedby) VALUES (?, ?, ?, ?, ?, ?, ?)",new Object[]{addVehicle.getVehicleNumber(),addVehicle.getVehicleType(),addVehicle.getVehicleMake(),addVehicle.getChaseNumber(),addVehicle.getEngineNumber(),addVehicle.getVehicleId(),user});
        
		if (insertUser==1)
         {
        	 msg="Successfully Added";
         }
         else {
        	 msg="Error while adding";
         }
	
	}
	catch(Exception e){
		 msg="Please Check all the inputs are proper";
	}
	Gson json=new Gson();
    String returnString=json.toJson(msg);
    return returnString;	 
}


@Override
public String addCustomer(taskPojo addCustomer, HttpServletRequest request,
		HttpServletResponse response) {
	String msg=null;
	HttpSession session=request.getSession();
		
		String user=(String) session.getAttribute("user_name");
		
		try{
			int insertUser=jdbcTemplate.update("INSERT INTO customer_details(customername, address, contactnumber, gst, addedby) VALUES (?, ?, ?, ?, ?)",new Object[]{addCustomer.getCustomerName(),addCustomer.getAddress(),addCustomer.getContactNumber(),addCustomer.getGST(),user});
	        
			if (insertUser==1)
	         {
	        	 msg="Successfully Added";
	         }
	         else {
	        	 msg="Error while adding";
	         }
		
		}
		catch(Exception e){
			 msg="Please Check all the inputs are proper";
		}
		Gson json=new Gson();
	    String returnString=json.toJson(msg);
	    return returnString;	
}


@Override
public String addSupplier(taskPojo addSupplier, HttpServletRequest request,
		HttpServletResponse response) {
	
	String msg=null;
	HttpSession session=request.getSession();
		
		String user=(String) session.getAttribute("user_name");
		
		try{
			int insertUser=jdbcTemplate.update("INSERT INTO supplier_details(suppliername, address, contactnumber, gst, addedby) VALUES (?, ?, ?, ?, ?)",new Object[]{addSupplier.getSupplierName(),addSupplier.getAddress(),addSupplier.getContactNumber(),addSupplier.getGST(),user});
	        
			if (insertUser==1)
	         {
	        	 msg="Successfully Added";
	         }
	         else {
	        	 msg="Error while adding";
	         }
		
		}
		catch(Exception e){
			 msg="Please Check all the inputs are proper";
		}
		Gson json=new Gson();
	    String returnString=json.toJson(msg);
	    return returnString;
}


@Override
public String addDrivers(taskPojo addDrivers, HttpServletRequest request,
		HttpServletResponse response) {
	System.out.println(addDrivers.getJoiningDate());
	String msg=null;
	HttpSession session=request.getSession();
		
		String user=(String) session.getAttribute("user_name");
		
		try{
			
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
			 
			 java.util.Date utilStartDate =  dateFormat.parse(addDrivers.getJoiningDate());
			 
			 
			 java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
			 
			 Calendar c = Calendar.getInstance(); 
			 c.setTime(sqlStartDate); 
			 c.add(Calendar.DATE, 1);
			 java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
			   
			    Timestamp timestamp = new java.sql.Timestamp(startDate.getTime());
			
			
			
			
			int insertUser=jdbcTemplate.update("INSERT INTO driver_details(drivername, address, contactnumber, monthlysalary, licensenumber, joiningdate, addedby)VALUES (?, ?, ?, ?, ?, ?, ?)",new Object[]{addDrivers.getDriverName(),addDrivers.getAddress(),addDrivers.getContactNumber(),addDrivers.getSalary(),addDrivers.getLicenseNumber(),timestamp,user});
	        
			if (insertUser==1)
	         {
	        	 msg="Successfully Added";
	         }
	         else {
	        	 msg="Please Check all the inputs are proper";
	         }
		
		}
		catch(Exception e){
			System.out.println(e);
			 msg="Error while adding";
		}
		Gson json=new Gson();
	    String returnString=json.toJson(msg);
	    return returnString;
}



@Override
public String getDetails(HttpServletRequest request,
		HttpServletResponse response) {
	
	Gson json=new Gson();
	String returnString;
	List<String> vehicles=new ArrayList<String>();
	List<String> customers=new ArrayList<String>();
	List<String> drivers=new ArrayList<String>();
	List<String> suppliers=new ArrayList<String>();
	List<Object> allDetails=new ArrayList<Object>();
	Integer count=null;
	try{
		System.out.println("inside try");
		String tripCount="select max(tripSheetNumber) from hourlyTrip";
		 count=jdbcTemplate.queryForObject(tripCount,Integer.class);
		if(count==null){
			count=1;
		}else{
			count=count+1;
		}
		System.out.println("count"+count);
	
		List<Map<String, Object>> vehiclesDb = jdbcTemplate
				.queryForList("select vehicleid from vehicle_details") ;
		
		for (Map<String, Object> tempRow : vehiclesDb) {
			System.out.println((String) tempRow.get("vehicleid"));
			vehicles.add((String) tempRow.get("vehicleid"));
		
		}
		
		
		
		List<Map<String, Object>> customersDb = jdbcTemplate
				.queryForList("select customerName from customer_details") ;
		for (Map<String, Object> tempRow : customersDb) {
			System.out.println((String) tempRow.get("customername"));
			customers.add((String) tempRow.get("customername"));
		
		}
		
		
		List<Map<String, Object>> suppliersDb = jdbcTemplate
				.queryForList("select supplierName from supplier_details") ;
		for (Map<String, Object> tempRow : suppliersDb) {
			System.out.println((String) tempRow.get("suppliername"));
			suppliers.add((String) tempRow.get("suppliername"));
		
		}
		
		List<Map<String, Object>> driversDb = jdbcTemplate
				.queryForList("select driverName from driver_details") ;
		for (Map<String, Object> tempRow : driversDb) {
		
			drivers.add((String) tempRow.get("driverName"));
		
		}
		
		allDetails.add(count);
		allDetails.add(vehicles);
		allDetails.add(customers);
		allDetails.add(drivers);
		allDetails.add(suppliers);
		
		returnString=json.toJson(allDetails);
	}
	catch(Exception e){
		
		  returnString=json.toJson("Please Check all the inputs are proper");
	}

	
	return returnString;
	
}



@Override
public String createHourlyTrip(taskPojo trip, HttpServletRequest request,
		HttpServletResponse response) {
	String msg=null;
	HttpSession session=request.getSession();
		
		String user=(String) session.getAttribute("user_name");
		if(trip.getDiselAmount()==0) {
			trip.setSupplierName(null);
		}
	
		try{
			
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
			 
			 java.util.Date utilStartDate = null;
			try {
				utilStartDate = dateFormat.parse(trip.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
			 
			 Calendar c = Calendar.getInstance(); 
			 c.setTime(sqlStartDate); 
			 c.add(Calendar.DATE, 1);
			 java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
			   
			    Timestamp timestamp = new java.sql.Timestamp(startDate.getTime());
			
			
			
			
			int insertUser=jdbcTemplate.update("INSERT INTO hourlytrip( tripsheetnumber, date, vehicleid, fromvalue, tovalue, customername,   drivername, beta, starthours, endhours, totalhours, rate, amount, diselquantity, diselrate, diselamount, bunk, addedby) VALUES (?, ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?, ?, ?,   ?, ?, ?, ?, ?)",new Object[]{trip.getSheetNumber(),timestamp,trip.getVehicleId(),trip.getFrom(),trip.getTo(),trip.getCustomerName(),trip.getDriverName(),trip.getBeta(),trip.getStartHours(),trip.getEndHours(),trip.getTotalHours(),trip.getRate(),trip.getAmount(),trip.getDiselQuantity(),trip.getDiselRate(),trip.getDiselAmount(),trip.getSupplierName(),user});
	        
			if (insertUser==1)
	         {
				if( trip.getDiselAmount()==0)
				{
					 msg="added successfully";
					
				}else {
			int insertSupplier=jdbcTemplate.update("INSERT INTO supplier_amount(id,suppliername, date, amount, reason, addedby)VALUES (NEXTVAL('venderSequence'),?, ?, ?, ?, ?)",new Object[]{ trip.getSupplierName(),timestamp,trip.getDiselAmount(),"Disel",user});
             if (insertSupplier==1){
            	 msg="added successfully";
            	 
             }else{
            	 msg="Error while adding";
             }
	         }
	         }
	         else {
	        	 msg="Error while adding";
	         }
		
		}
		catch(Exception e){
			System.out.println(e);
			 msg="Please Check all the inputs are proper";
		}
		Gson json=new Gson();
	    String returnString=json.toJson(msg);
	    return returnString;
}



@Override
public String supplierAmount(taskPojo amt, HttpServletRequest request,
		HttpServletResponse response) {
	String msg=null;
	HttpSession session=request.getSession();
		
		String user=(String) session.getAttribute("user_name");
		
		try{
			
			 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			 
			 
			 java.util.Date utilStartDate =  dateFormat.parse(amt.getDate());
			 
			 
			 java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
			 
			 Calendar c = Calendar.getInstance(); 
			 c.setTime(sqlStartDate); 
			 c.add(Calendar.DATE, 1);
			 java.sql.Date startDate= new java.sql.Date(c.getTimeInMillis());
			   
			    Timestamp timestamp = new java.sql.Timestamp(startDate.getTime());
			
			
			
			
				int insertSupplier=jdbcTemplate.update("INSERT INTO supplier_amount(id,suppliername, date, amount, reason, addedby)VALUES (NEXTVAL('venderSequence'),?, ?, ?, ?, ?)",new Object[]{ amt.getSupplierName(),timestamp,amt.getAmount(),amt.getReason(),user});
	        
			if (insertSupplier==1)
	         {
	        	 msg="Successfully Added";
	         }
	         else {
	        	 msg="Error while adding";
	         }
		
		}
		catch(Exception e){
			System.out.println(e);
			 msg="Please Check all the inputs are proper";
		}
		Gson json=new Gson();
	    String returnString=json.toJson(msg);
	    return returnString;
}



@Override
public String getSupplierAmount(HttpServletRequest request,
		HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 
	 
	List<taskPojo> totalDatas=new ArrayList<taskPojo>();
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT id,suppliername, date, amount, amountpaid FROM supplier_amount") ;
		
		for (Map<String, Object> tempRow : checkUser) {
			taskPojo data=new taskPojo();
			data.setId((Integer) tempRow.get("id"));
			data.setSupplierName((String) tempRow.get("suppliername"));
		String sendDate=dateFormat.format((Timestamp) tempRow.get("date")).toString();
		 java.util.Date utilDate =  dateFormat.parse(sendDate);
		 
		 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	
		/* Timestamp timestamp = new java.sql.Timestamp(sqlDate.getTime());*/
			data.setDbDate(sqlDate);
			
			BigDecimal bd=(BigDecimal) tempRow.get("amount");
			data.setAmount(bd.doubleValue());
			BigDecimal bd1=(BigDecimal) tempRow.get("amountpaid");
			data.setAmountPaid(bd1.doubleValue());
			totalDatas.add(data);
		}
		
	
	     returnString=json.toJson(totalDatas);
		
}
	catch(Exception e){
		
		  returnString=json.toJson("Please Check all the inputs are proper");
	}
	
    return returnString;	
}



@Override
public String updateSupplierAmount(taskPojo amt, HttpServletRequest request,
		HttpServletResponse response) {
	String msg=null;
	HttpSession session=request.getSession();
		
	
		
		try{
			
			
			
				int insertSupplier=jdbcTemplate.update("UPDATE supplier_amount SET amountpaid=? WHERE id=?",new Object[]{ amt.getAmount(),amt.getId()});
	        
			if (insertSupplier==1)
	         {
	        	 msg="Successfully Updated";
	         }
	         else {
	        	 msg="Error while adding";
	         }
		
		}
		catch(Exception e){
			System.out.println(e);
			 msg="Please Check all the inputs are proper";
		}
		Gson json=new Gson();
	    String returnString=json.toJson(msg);
	    return returnString;
}



@Override
public String supplierAmountDetails(taskPojo id, HttpServletRequest request,
		HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 
	taskPojo data=new taskPojo();
	
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT * FROM supplier_amount where id=?",new Object[]{ id.getId()});
		for (Map<String, Object> tempRow : checkUser) {
		
			data.setId((Integer) tempRow.get("id"));
			data.setSupplierName((String) tempRow.get("suppliername"));
		String sendDate=dateFormat.format((Timestamp) tempRow.get("date")).toString();
		 java.util.Date utilDate =  dateFormat.parse(sendDate);
		 
		 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			data.setDbDate(sqlDate);	
			BigDecimal bd=(BigDecimal) tempRow.get("amount");
			data.setAmount(bd.doubleValue());
			BigDecimal bd1=(BigDecimal) tempRow.get("amountpaid");
			data.setAmountPaid(bd1.doubleValue());
			data.setReason((String) tempRow.get("reason"));
			data.setAddedBy((String) tempRow.get("addedby"));
		}
		
	
	     returnString=json.toJson(data);
		
}
	catch(Exception e){
		
		  returnString=json.toJson("Please Check all the inputs are proper");
	}
	
    return returnString;	
}



@Override
public String getCustomerAmount(HttpServletRequest request,
		HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 
	 
	List<taskPojo> totalDatas=new ArrayList<taskPojo>();
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT tripsheetnumber,customername, date, amount, amountpaid,discount FROM hourlytrip") ;
		
		for (Map<String, Object> tempRow : checkUser) {
			taskPojo data=new taskPojo();
			data.setSheetNumber((Integer) tempRow.get("tripsheetnumber"));
			data.setCustomerName((String) tempRow.get("customername"));
		String sendDate=dateFormat.format((Timestamp) tempRow.get("date")).toString();
		 java.util.Date utilDate = null;
		try {
			utilDate = dateFormat.parse(sendDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
	
	
			data.setDbDate(sqlDate);
			
			BigDecimal bd=(BigDecimal) tempRow.get("amount");
			data.setAmount(bd.doubleValue());
			BigDecimal bd1=(BigDecimal) tempRow.get("amountpaid");
			data.setAmountPaid(bd1.doubleValue());
			BigDecimal bd2=(BigDecimal) tempRow.get("discount");
			data.setDiscount(bd2.doubleValue());
			totalDatas.add(data);
		}
		
	
	     returnString=json.toJson(totalDatas);
		
}
	catch(Exception e){
		
		  returnString=json.toJson("Please Check all the inputs are proper");
	}
	
    return returnString;
}



@Override
public String customerAmountDetails(taskPojo id, HttpServletRequest request,
		HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	 
	taskPojo data=new taskPojo();
	
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT * FROM hourlytrip where tripsheetnumber=?",new Object[]{ id.getSheetNumber()});
		for (Map<String, Object> tempRow : checkUser) {
		
			data.setSheetNumber((Integer) tempRow.get("tripsheetnumber"));
			data.setCustomerName((String) tempRow.get("customername"));
		String sendDate=dateFormat.format((Timestamp) tempRow.get("date")).toString();
		 java.util.Date utilDate =  dateFormat.parse(sendDate);
		 
		 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
			data.setDbDate(sqlDate);
			BigDecimal bd=(BigDecimal) tempRow.get("amount");
			data.setAmount(bd.doubleValue());
			BigDecimal bd1=(BigDecimal) tempRow.get("amountpaid");
			data.setAmountPaid(bd1.doubleValue());
			data.setVehicleId((String) tempRow.get("vehicleid"));
			data.setDriverName((String) tempRow.get("drivername"));
			BigDecimal bd2=(BigDecimal) tempRow.get("totalhours");
			data.setTotalHours(bd2.doubleValue());	
			data.setAddedBy((String) tempRow.get("addedby"));
			BigDecimal bd3=(BigDecimal) tempRow.get("discount");
			data.setDiscount(bd3.doubleValue());
			BigDecimal bd4=(BigDecimal) tempRow.get("diselamount");
			data.setDiselAmount(bd4.doubleValue());
			data.setSupplierName((String) tempRow.get("bunk"));
			
		}
		
	
	     returnString=json.toJson(data);
		
}
	catch(Exception e){
		
		  returnString=json.toJson("Please Check all the inputs are proper");
	}
	
    return returnString;	
}



@Override
public String updateCustomerAmount(taskPojo amt, HttpServletRequest request,
		HttpServletResponse response) {
	String msg=null;
	HttpSession session=request.getSession();
		
	
		
		try{
			
			
			
				int insertSupplier=jdbcTemplate.update("UPDATE hourlytrip SET amountpaid=?,discount=? WHERE tripsheetnumber=?",new Object[]{ amt.getAmount(),amt.getDiscount(),amt.getSheetNumber()});
	        
			if (insertSupplier==1)
	         {
				
				int insertVoucher=jdbcTemplate.update("INSERT INTO voucher( customername, date, amount) VALUES (?, now(), ?)",new Object[]{ amt.getCustomerName(),amt.getAmount()});

				if (insertSupplier==1)
		         {
	        	 msg="Successfully Updated";
		         }else {
		        	 msg="Error while adding";
		         }
	         }
	         else {
	        	 msg="Error while adding";
	         }
		
		}
		catch(Exception e){
			System.out.println(e);
			 msg="Please Check all the inputs are proper";
		}
		Gson json=new Gson();
	    String returnString=json.toJson(msg);
	    return returnString;
}



@Override
public String getSupplierData(HttpServletRequest request, HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	
	
	List<taskPojo> totalDatas=new ArrayList<taskPojo>();
	
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT * FROM supplier_details");
		for (Map<String, Object> tempRow : checkUser) {
			taskPojo data=new taskPojo();
			data.setSupplierName((String) tempRow.get("suppliername"));
			data.setAddress((String) tempRow.get("address"));
			data.setContactNumber((String) tempRow.get("contactnumber"));
			data.setGST((String) tempRow.get("gst"));
			data.setAddedBy((String) tempRow.get("addedby"));
			totalDatas.add(data);
		}
		
	
	     returnString=json.toJson(totalDatas);
}
	catch(Exception e){
		System.out.println(e);
		String msg="Please Check all the inputs are proper";
		 returnString=json.toJson(msg);
		   
	}
    return returnString;
}



@Override
public String getCustomerData(HttpServletRequest request, HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	
	List<taskPojo> totalDatas=new ArrayList<taskPojo>();
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT c.*,sum(p.amount-p.amountpaid-p.discount) as amount FROM customer_details c left join hourlytrip p on (c.customername=p.customername) group by c.customername ");
		for (Map<String, Object> tempRow : checkUser) {
			taskPojo data=new taskPojo();
			data.setCustomerName((String) tempRow.get("customername"));
			data.setAddress((String) tempRow.get("address"));
			data.setContactNumber((String) tempRow.get("contactnumber"));
			data.setGST((String) tempRow.get("gst"));
			data.setAddedBy((String) tempRow.get("addedby"));
			long l=0;
			if(tempRow.get("amount")!=null) {
			l=(long)tempRow.get("amount");
			}
			data.setAmount((int) l);
			totalDatas.add(data);
		}
		
	
	     returnString=json.toJson(totalDatas);
}
	catch(Exception e){
		System.out.println(e);
		String msg="Please Check all the inputs are proper";
		 returnString=json.toJson(msg);
		   
	}
    return returnString;
}



@Override
public String getDriverData(HttpServletRequest request, HttpServletResponse response) {
	
	Gson json=new Gson();
	String returnString;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	List<taskPojo> totalDatas=new ArrayList<taskPojo>();
	
	
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT * FROM driver_details");
		for (Map<String, Object> tempRow : checkUser) {
			taskPojo data=new taskPojo();
			data.setDriverName((String) tempRow.get("drivername"));
			data.setAddress((String) tempRow.get("address"));
			data.setContactNumber((String) tempRow.get("contactnumber"));
			BigDecimal bd = new BigDecimal ((Integer) tempRow.get("monthlysalary"));

			data.setSalary(bd);
			data.setLicenseNumber((String) tempRow.get("licensenumber"));
			String sendDate=dateFormat.format((Timestamp) tempRow.get("joiningdate")).toString();
			 java.util.Date utilDate =  dateFormat.parse(sendDate);
			 
			 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				data.setDbDate(sqlDate);	
		
			data.setAddedBy((String) tempRow.get("addedby"));
			totalDatas.add(data);
		}
		
	
	     returnString=json.toJson(totalDatas);
}
	catch(Exception e){
		System.out.println(e);
		String msg="Please Check all the inputs are proper";
		 returnString=json.toJson(msg);
		   
	}
    return returnString;
}



@Override
public String getVehicleData(HttpServletRequest request, HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	List<taskPojo> totalDatas=new ArrayList<taskPojo>();
	
	
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT * FROM vehicle_details");
		for (Map<String, Object> tempRow : checkUser) {
			taskPojo data=new taskPojo();
			data.setVehicleNumber((String) tempRow.get("vehiclenumber"));
			data.setVehicleType((String) tempRow.get("vehicletype"));
			data.setVehicleMake((String) tempRow.get("vehiclemake"));
			data.setChaseNumber((String) tempRow.get("chasenumber"));
			data.setEngineNumber((String) tempRow.get("enginenumber"));
			data.setVehicleId((String) tempRow.get("vehicleid"));
			data.setAddedBy((String) tempRow.get("addedby"));
			totalDatas.add(data);
		}
		
	
	     returnString=json.toJson(totalDatas);
}
	catch(Exception e){
		System.out.println(e);
		String msg="Please Check all the inputs are proper";
		 returnString=json.toJson(msg);
		   
	}
    return returnString;
}



@Override
public String getVoucher(HttpServletRequest request, HttpServletResponse response) {
	Gson json=new Gson();
	String returnString;
	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	List<taskPojo> totalDatas=new ArrayList<taskPojo>();
	
	
	try{

		List<Map<String, Object>> checkUser = jdbcTemplate
				.queryForList("SELECT * FROM voucher");
		for (Map<String, Object> tempRow : checkUser) {
			taskPojo data=new taskPojo();
			data.setCustomerName((String) tempRow.get("customername"));
			
			String sendDate=dateFormat.format((Timestamp) tempRow.get("date")).toString();
			 java.util.Date utilDate =  dateFormat.parse(sendDate);
			 
			 java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
				data.setDbDate(sqlDate);	
				BigDecimal bd=(BigDecimal) tempRow.get("amount");
			data.setAmount(bd.doubleValue());
			totalDatas.add(data);
		}
		
	
	     returnString=json.toJson(totalDatas);
}
	catch(Exception e){
		System.out.println(e);
		String msg="Please Check all the inputs are proper";
		 returnString=json.toJson(msg);
		   
	}
    return returnString;
}
}


