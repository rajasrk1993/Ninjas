package com;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class serviceimpl implements service{
	
	@Autowired
	dao dao;
	
	@Override
	public String login(taskPojo list,HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
		
		return dao.login(list,request,response);
	}
	@Override
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		return dao.logout(request,response);
	}
	@Override
	public String session(HttpServletRequest request, HttpServletResponse response) {
		
		return dao.session(request,response);
	}
	@Override
	public String addUser(taskPojo addUser,HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return dao.addUser(addUser,request,response);
	}
	
	@Override
	public String deleteUser(taskPojo deleteUser, HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.deleteTask(deleteUser,request,response);
	}
	@Override
	public String updateUser(taskPojo updateUser, HttpServletRequest request,
			HttpServletResponse response) {
		return dao.updateUser(updateUser,request,response);
	}
	@Override
	public String getUser(HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.getUser(request,response);
	}
	@Override
	public String addVehicle(taskPojo addVehicle, HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.addVehicle(addVehicle,request,response);
	}
	@Override
	public String addCustomer(taskPojo addCustomer, HttpServletRequest request,
			HttpServletResponse response) {
		return dao.addCustomer(addCustomer,request,response);
	}
	@Override
	public String addSupplier(taskPojo addSupplier, HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.addSupplier(addSupplier,request,response);
	}
	@Override
	public String addDrivers(taskPojo addDrivers, HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.addDrivers(addDrivers,request,response);
	}
	@Override
	public String getDetails(HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.getDetails(request,response);
	}
	@Override
	public String createHourlyTrip(taskPojo trip, HttpServletRequest request,
			HttpServletResponse response) {
		return dao.createHourlyTrip(trip,request,response);
	}
	@Override
	public String supplierAmount(taskPojo amt, HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.supplierAmount(amt,request,response);
	}
	@Override
	public String getSupplierAmount(HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.getSupplierAmount(request,response);
	}
	@Override
	public String updateSupplierAmount(taskPojo amt,
			HttpServletRequest request, HttpServletResponse response) {
		
		return dao.updateSupplierAmount(amt,request,response);
	}
	@Override
	public String supplierAmountDetails(taskPojo id,
			HttpServletRequest request, HttpServletResponse response) {
		
		return dao.supplierAmountDetails(id,request,response);
	}
	@Override
	public String getCustomerAmount(HttpServletRequest request,
			HttpServletResponse response) {
		
		return dao.getCustomerAmount(request,response);
	}
	@Override
	public String customerAmountDetails(taskPojo id,
			HttpServletRequest request, HttpServletResponse response) {
		
		return dao.customerAmountDetails(id,request,response);
	}
	@Override
	public String updateCustomerAmount(taskPojo amt,
			HttpServletRequest request, HttpServletResponse response) {
		
		return dao.updateCustomerAmount(amt,request,response);
	}
	@Override
	public String getSupplierData(HttpServletRequest request, HttpServletResponse response) {
		return dao.getSupplierData(request,response);
	}
	@Override
	public String getCustomerData(HttpServletRequest request, HttpServletResponse response) {
		return dao.getCustomerData(request,response);
	}
	@Override
	public String getDriverData(HttpServletRequest request, HttpServletResponse response) {
		return dao.getDriverData(request,response);
	}
	@Override
	public String getVehicleData(HttpServletRequest request, HttpServletResponse response) {
		return dao.getVehicleData(request,response);
	}
	@Override
	public String getVoucher(HttpServletRequest request, HttpServletResponse response) {
		return dao.getVoucher(request,response);
	}
}
