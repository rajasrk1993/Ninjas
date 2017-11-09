var empapp=angular.module("sampleapp", ['ngRoute','ngStorage']);

	empapp.config(function($routeProvider,$locationProvider){
			$routeProvider
			.when(
				'/' , {
						controller : "testController",
						templateUrl : "pages/login.jsp",
//						routeName: 'HomePage'
						
					})
			
				.when(
				'/Home' , {
					    
						controller : "homeController",
						templateUrl : "pages/Home.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
				.when(
				'/addUser' , {
					    
						controller : "homeController",
						templateUrl : "pages/addUser.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
				.when(
				'/deleteUser' , {
					    
						controller : "homeController",
						templateUrl : "pages/deleteUser.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
				.when(
				'/updateUser' , {
					    
						controller : "homeController",
						templateUrl : "pages/updateUser.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
				.when(
				'/addVehicle' , {
					    
						controller : "homeController",
						templateUrl : "pages/addVehicle.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/addCustomer' , {
					    
						controller : "homeController",
						templateUrl : "pages/addCustomer.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/addSupplier' , {
					    
						controller : "homeController",
						templateUrl : "pages/addSupplier.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/addDrivers' , {
					    
						controller : "homeController",
						templateUrl : "pages/addDrivers.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
						.when(
				'/createTrip' , {
					    
						controller : "homeController",
						templateUrl : "pages/hourlyTripCreate.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
						.when(
				'/supplierAmount' , {
					    
						controller : "homeController",
						templateUrl : "pages/supplierAmount.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/viewSupplierAmount' , {
					    
						controller : "homeController",
						templateUrl : "pages/viewSupplierAmount.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/viewCustomerAmount' , {
					    
						controller : "homeController",
						templateUrl : "pages/viewCustomerAmount.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/viewCustomerData' , {
					    
						controller : "homeController",
						templateUrl : "pages/viewCustomerData.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/viewSupplierData' , {
					    
						controller : "homeController",
						templateUrl : "pages/viewSupplierData.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/viewDriverData' , {
					    
						controller : "homeController",
						templateUrl : "pages/viewDriverData.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/viewVehicleData' , {
					    
						controller : "homeController",
						templateUrl : "pages/viewVehicleData.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					.when(
				'/viewVoucher' , {
					    
						controller : "homeController",
						templateUrl : "pages/viewVoucherData.html",
//						routeName: 'HomePage'
						requireAuth: true
					})
					
			.otherwise ({redirectTo : '/'});
			
		});
	empapp.filter("myfilter", function() {
		console.log("inside filter");
		  return function(items, from, to) {
		        var df = from;
		        var dt =to;
		        var result = [];   
		       
		        for (var i=0; i<items.length; i++){
		            var date = new Date(items[i].dbDate);
		         console.log("date"+date);
		            var tf = date;
		            if (tf >=df && tf <= dt)  {
		                result.push(items[i]);
		            }
		        }   
		         console.log(items);
		        return result;
		  };
		});
	
	
	empapp.filter('sumByKey', function($rootScope) {
		console.log("inside sum filter");
	        return function(data, key) {
	            if (typeof(data) === 'undefined' || typeof(key) === 'undefined') {
	                return 0;
	            }

	            var total = 0;
	            var paid=0;
			    for(var i = 0; i < data.length; i++){
			        
			        total += data[i].amount;
			        paid +=data[i].amountPaid;
			    }
			    $rootScope.getTotalDebit=total;
			    $rootScope.getTotalCredit=paid;
			    $rootScope.getTotal=total-paid;
	            return data;
	        };
	    });
	
	empapp.filter('sumByKeyc', function($rootScope) {
		console.log("inside sum filter");
	        return function(data, key) {
	            if (typeof(data) === 'undefined' || typeof(key) === 'undefined') {
	                return 0;
	            }

	            var total = 0;
	            var paid=0;
	            var discount=0;
			    for(var i = 0; i < data.length; i++){
			        
			        total += data[i].amount;
			        paid +=data[i].amountPaid;
			        discount +=data[i].discount;
			    }
			    $rootScope.getTotalDebit=total;
			    $rootScope.getTotalCredit=paid;
			    $rootScope.getTotalDiscount=discount;
			    $rootScope.getTotal=total-paid-discount;
	            return data;
	        };
	    });
	
	empapp.filter('sumByKeyp', function($rootScope) {
		console.log("inside sum filter");
	        return function(data, key) {
	            if (typeof(data) === 'undefined' || typeof(key) === 'undefined') {
	                return 0;
	            }

	            var total = 0;
	           
			    for(var i = 0; i < data.length; i++){
			        
			        total += data[i].amount;
			       
			    }
			    $rootScope.getTotalPaidAmount=total;
			  
	            return data;
	        };
	    });
	
	/*	empapp.run(function($rootScope){
	   $rootScope.$on('$routeChangeStart', function (evt, nexot, currentRoute) {
	        $rootScope.isActive = next.$$route.routeName;
	   });
		});*/