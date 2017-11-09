package com;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public interface service {


	public String login(taskPojo list, HttpServletRequest request,
			HttpServletResponse response) throws NoSuchAlgorithmException;

	public String logout(HttpServletRequest request,
			HttpServletResponse response);

	public String session(HttpServletRequest request,
			HttpServletResponse response);

	

	public String addUser(taskPojo addUser, HttpServletRequest request, HttpServletResponse response);



	public String deleteUser(taskPojo deleteUser, HttpServletRequest request,
			HttpServletResponse response);

	public String updateUser(taskPojo updateUser, HttpServletRequest request,
			HttpServletResponse response);

	public String getUser(HttpServletRequest request,
			HttpServletResponse response);

	public String addVehicle(taskPojo addVehicle, HttpServletRequest request,
			HttpServletResponse response);

	public String addCustomer(taskPojo addCustomer, HttpServletRequest request,
			HttpServletResponse response);

	public String addSupplier(taskPojo addSupplier, HttpServletRequest request,
			HttpServletResponse response);

	public String addDrivers(taskPojo addDrivers, HttpServletRequest request,
			HttpServletResponse response);

	public String getDetails(HttpServletRequest request,
			HttpServletResponse response);

	public String createHourlyTrip(taskPojo trip, HttpServletRequest request,
			HttpServletResponse response);

	public String supplierAmount(taskPojo amt, HttpServletRequest request,
			HttpServletResponse response);

	public String getSupplierAmount(HttpServletRequest request,
			HttpServletResponse response);

	public String updateSupplierAmount(taskPojo amt,
			HttpServletRequest request, HttpServletResponse response);

	public String supplierAmountDetails(taskPojo id,
			HttpServletRequest request, HttpServletResponse response);

	public String getCustomerAmount(HttpServletRequest request,
			HttpServletResponse response);

	public String customerAmountDetails(taskPojo id,
			HttpServletRequest request, HttpServletResponse response);

	public String updateCustomerAmount(taskPojo amt,
			HttpServletRequest request, HttpServletResponse response);

	public String getSupplierData(HttpServletRequest request, HttpServletResponse response);

	public String getCustomerData(HttpServletRequest request, HttpServletResponse response);

	public String getDriverData(HttpServletRequest request, HttpServletResponse response);

	public String getVehicleData(HttpServletRequest request, HttpServletResponse response);

	public String getVoucher(HttpServletRequest request, HttpServletResponse response);



}
