empapp.controller("homeController",function($scope,$http,$rootScope,$location, $localStorage)
{
	$rootScope.userNames=$localStorage.userNames;
	$rootScope.details=$localStorage.details;
	$rootScope.supplierDetails=$localStorage.supplierDetails;
	$rootScope.customerDetails=$localStorage.customerDetails;
	$rootScope.allSupplierDetails=$localStorage.allSupplierDetails;
	$rootScope.allCustomerDetails=$localStorage.allCustomerDetails;
	$rootScope.from=$localStorage.from;
	$rootScope.to=$localStorage.to;
	$rootScope.vehicleData=[];
	$rootScope.customerData=[];
	$rootScope.supplierData=[];
	$rootScope.driverData=[];
	$rootScope.voucher=[];
	$rootScope.vehicleData=$localStorage.vehicleData;
	$rootScope.customerData=$localStorage.customerData;
	$rootScope.supplierData=$localStorage.supplierData;
	$rootScope.driverData=$localStorage.driverData;
	$rootScope.voucher=$localStorage.voucher;
	
	$rootScope.trip={};
	$rootScope.vehicles=[];
	
	$rootScope.customers=[];
	$rootScope.drivers=[];
	$rootScope.suppliers=[];
	$rootScope.s={};
	$rootScope.c={};
	
	 $scope.session=function(){
			 
		 $http.post("rest/session").success(function(logindata){
				if(logindata==="expired")
				{
					$localStorage.$reset();
					$location.path("/");
					
				}
		
			})
			.error(function(data){
				console.log("Error while getting");
			});
		};
		
$scope.getDetails=function(){
		
		$http.post("rest/getDetails").success(function(logindata){
			
			console.log(logindata);
			$rootScope.details=logindata;
			
			var allDetails=[];
		
			
			allDetails=$rootScope.details;
			
			$rootScope.vehicles=[];
			$rootScope.customers=[];
			$rootScope.drivers=[];
			$rootScope.suppliers=[];
			
			$rootScope.trip.sheetNumber=allDetails[0];
			
			for ( var int = 0; int < allDetails[1].length; int++) {
				$rootScope.vehicles.push(allDetails[1][int]);
			}
			
			
			for ( var int = 0; int < allDetails[2].length; int++) {
				$rootScope.customers.push(allDetails[2][int]);
			}
			for ( var int = 0; int < allDetails[3].length; int++) {
				$rootScope.drivers.push(allDetails[3][int]);
			}
			
			for ( var int = 0; int < allDetails[4].length; int++) {
				$rootScope.suppliers.push(allDetails[4][int]);
			}
			
			console.log(allDetails[2]);
			console.log($rootScope.customers);
			
			$localStorage.details=$rootScope.details;	
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};	
	
	$scope.getSupplierAmount=function(){
		
		 $scope.session();
		$http.post("rest/getSupplierAmount").success(function(logindata){
			var sDetails=[];
			sDetails=logindata;
			$rootScope.supplierDetails=sDetails;
		
				console.log("vcxv"+$rootScope.supplierDetails[0].supplierName);
			console.log($rootScope.supplierDetails);
			$localStorage.supplierDetails=$rootScope.supplierDetails;
			
				})
		.error(function(data){
			console.log("Error while getting");
		});
	};	
$scope.getCustomerAmount=function(){
		
	 $scope.session();
		$http.post("rest/getCustomerAmount").success(function(logindata){
			var sDetails=[];
			sDetails=logindata;
			$rootScope.customerDetails=sDetails;
		
				
			console.log($rootScope.customerDetails);
			$localStorage.customerDetails=$rootScope.customerDetails;
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};	
	function init(){
		
		console.log("inside int");
		
		$http.post("rest/session").success(function(logindata){
				if(logindata==="expired")
				{
					$localStorage.$reset();
					$location.path("/");
					
				}
		
			});
		
		$rootScope.from=new Date("2017-01-01");
		var now = new Date();

		var day = ("0" + now.getDate()).slice(-2);
		var month = ("0" + (now.getMonth() + 1)).slice(-2);

		var today = now.getFullYear()+"-"+(month)+"-"+(day) ;
			$rootScope.to=new Date(today);
			
		
		var allDetails=[];
		
		
		allDetails=$rootScope.details;
		
		if(typeof allDetails === 'undefined' ){
			console.log("inside details");
		$scope.getDetails();
	
		
		}else{
			
			$rootScope.vehicles=[];
			$rootScope.customers=[];
			$rootScope.drivers=[];
			$rootScope.suppliers=[];
			
		$rootScope.trip.sheetNumber=allDetails[0];
		
		for ( var int = 0; int < allDetails[1].length; int++) {
			$rootScope.vehicles.push(allDetails[1][int]);
		}
		
		
		for ( var int = 0; int < allDetails[2].length; int++) {
			$rootScope.customers.push(allDetails[2][int]);
		}
		for ( var int = 0; int < allDetails[3].length; int++) {
			$rootScope.drivers.push(allDetails[3][int]);
		}
		
		for ( var int = 0; int < allDetails[4].length; int++) {
			$rootScope.suppliers.push(allDetails[4][int]);
		}
		}
		};

	 init(); 
	 
	 

	
	
$scope.logout=function(){
		
		console.log('trying out log out');
		$http.post("rest/logout").success(function(logindata){
			$rootScope.auth=null;
			console.log('trying out log out');
			$localStorage.$reset();
		
			$location.path("/");
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};
$scope.getUser=function(){
		
		$http.post("rest/getUser").success(function(logindata){
			
			$rootScope.userNames=logindata;
			
			$localStorage.userNames=$rootScope.userNames;
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};	
	
$scope.addUser=function(user){
		
	 $scope.session();
		$http.post("rest/addUser",user).success(function(logindata){
			 $scope.clearUser();
			alert(logindata);
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};	
$scope.updateUser=function(user){
	 $scope.session();
		$http.post("rest/updateUser",user).success(function(logindata){
			alert(logindata);
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};	
$scope.deleteUser=function(user){
	 $scope.session();
		$http.post("rest/deleteUser",user).success(function(logindata){
			$scope.getUser();
			alert(logindata);
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};	
	
$scope.addVehicle=function(vehicle){
	 $scope.session();
		$http.post("rest/addVehicle",vehicle).success(function(logindata){
			
			$scope.clearVehicle();
			$scope.getDetails();
			alert(logindata);
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};
	
	 $scope.clearVehicle = function () {
	      
         $scope.vehicle=null;
          }	
	 $scope.clearCustomer = function () {
	      
         $scope.customer=null;
          }	
	 $scope.clearSupplier = function () {
	      
         $scope.supplier=null;
          }	
	 $scope.clearDrivers = function () {
	      
         $scope.driverss=null;
          }	
	 $scope.clearUser = function () {
	      
         $scope.user=null;
          }	
	 
	 $scope.clearTrip = function () {
	      var sheetNumber=$rootScope.trip.sheetNumber;
	      $rootScope.trip={};
	      $rootScope.trip.sheetNumber=sheetNumber;
          }	
	 
$scope.addDrivers=function(driverss){
	 $scope.session();
	 
		$http.post("rest/addDrivers",driverss).success(function(logindata){
			 $scope.clearDrivers();
			$scope.getDetails();
			alert(logindata);
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};
$scope.addCustomer=function(customer){
	 $scope.session();
		$http.post("rest/addCustomer",customer).success(function(logindata){
			$scope.clearCustomer();
			$scope.getDetails();
			alert(logindata);
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};
$scope.addSupplier=function(supplier){
	 $scope.session();
		$http.post("rest/addSupplier",supplier).success(function(logindata){
			$scope.clearSupplier();
			$scope.getDetails();
			alert(logindata);
			
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};
	

	
	
$scope.totalHours=function(){
	$rootScope.trip.totalHours=$rootScope.trip.endHours-$rootScope.trip.startHours;
		
	};
	$scope.amount=function(){
		$rootScope.trip.amount=$rootScope.trip.totalHours*$rootScope.trip.rate;
			
		};
		$scope.diselAmount=function(){
			$rootScope.trip.diselAmount=$rootScope.trip.diselQuantity*$rootScope.trip.diselRate;
				
			};
					
			$scope.createHourlyTrip=function(value){
				if(typeof(value.supplierName) === 'undefined' || value.supplierName.length=== 0) {
					value.supplierName="null";
					value.diselAmount=0;
				}
				
				 $scope.session();
				$http.post("rest/createHourlyTrip",value).success(function(logindata){
					$rootScope.trip={};
					$scope.getDetails();
					alert(logindata);
					
					
				})
				.error(function(data){
					console.log("Error while getting");
				});
			};
				
			
			 $scope.clearSupplierAmount = function () {
			      
		         $scope.supplier=null;
		          }
			
			
			$scope.addSupplierAmount=function(supplier){
				 $scope.session();
				$http.post("rest/supplierAmount",supplier).success(function(logindata){
					$scope.supplier=null;
					alert(logindata);
					
					
				})
				.error(function(data){
					console.log("Error while getting");
				});
			};		

			
			$scope.clearFilters=function(){
				location.reload();
				};	
				
				$scope.updateSupplierCredit=function(id,amt){
					var sendData={};
					sendData.id=id;
					sendData.amount=amt;
					 $scope.session();
					$http.post("rest/updateSupplierAmount",sendData).success(function(logindata){
						
						alert(logindata);
						$scope.getSupplierAmount();
						$rootScope.edit=false;
						
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	
				
				
				$scope.supplierAmountDetails=function(id){
					 $scope.session();
					$http.post("rest/supplierAmountDetails",id).success(function(logindata){
						
						$rootScope.allSupplierDetails=logindata;
						
						$localStorage.allSupplierDetails=$rootScope.allSupplierDetails;
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	
				
				$scope.updateCustomerCredit=function(name,id,amt,disc){
					var sendData={};
					sendData.customerName=name;
					sendData.sheetNumber=id;
					sendData.amount=amt;
					sendData.discount=disc;
					 $scope.session();
					$http.post("rest/updateCustomerAmount",sendData).success(function(logindata){
						
						alert(logindata);
						$scope.getCustomerAmount();
						$rootScope.edit=false;
						
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	

				$scope.customerAmountDetails=function(id){
					 $scope.session();
					$http.post("rest/customerAmountDetails",id).success(function(logindata){
						
						$rootScope.allCustomerDetails=logindata;
						
						$localStorage.allCustomerDetails=$rootScope.allCustomerDetails;
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	
				
				$scope.getSupplierData=function(){
					
					$http.post("rest/getSupplierData").success(function(logindata){
						
						$rootScope.supplierData=logindata;
						
						$localStorage.supplierData=$rootScope.supplierData;
						
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	
				
	$scope.getCustomerData=function(){
					
					$http.post("rest/getCustomerData").success(function(logindata){
						
						$rootScope.customerData=logindata;
						
						$localStorage.customerData=$rootScope.customerData;
						
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	
	$scope.getDriverData=function(){
					
					$http.post("rest/getDriverData").success(function(logindata){
						
						$rootScope.driverData=logindata;
						
						$localStorage.driverData=$rootScope.driverData;
						
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	
$scope.getVehicleData=function(){
					
					$http.post("rest/getVehicleData").success(function(logindata){
						
						$rootScope.vehicleData=logindata;
						console.log($rootScope.vehicleData);
						$localStorage.vehicleData=$rootScope.vehicleData;
						
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};	
$scope.getVoucher=function(){
					
					$http.post("rest/voucher").success(function(logindata){
						
						$rootScope.voucher=logindata;
						
						$localStorage.voucher=$rootScope.voucher;
						
					})
					.error(function(data){
						console.log("Error while getting");
					});
				};		
				
		        $scope.printDiv = function (printable) {
		      
                var divToPrint=document.getElementById("printable");
                divToPrint.removeAttribute('style');
            
             	   console.log(divToPrint);
             	   var newWin;
             	   newWin=  window.open('', '_blank');
             	   newWin.document.open();
             	   newWin.document.write(divToPrint.outerHTML);
             	   newWin.print();
             	  divToPrint.setAttribute("style", "display: none; ");
             	   newWin.close();
                }		
		       	
				
				
		
});