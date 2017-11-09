empapp.controller("testController",function($scope,$http,$rootScope,$location, $localStorage)
{
	$rootScope.isbuttonDisabled = true;
	function init(){
		console.log("inside int");
	
	$http.post("rest/session").success(function(logindata){
			if(logindata==="not expired")
			{
				$location.path("/Home");
				
			}
	
		});
		};

	 init(); 
	
		
		$scope.checkUsername = function() {
			if($scope.login.userName.length === 0 || $scope.login.password.length === 0) {
				$rootScope.isbuttonDisabled = true;
			
			} else{
				$rootScope.isbuttonDisabled = false;
				
			}
		};
	
	$scope.userLogin=function(log){
		
		var loginValues=angular.toJson(log);
		console.log(loginValues,log);
		$http.post("rest/login",loginValues).success(function(logindata){
			
				
			if(logindata==="success")
			{
			
				$location.path("/Home");
				
				
			}else {
				
				alert(logindata);
			}
			
		})
		.error(function(data){
			console.log("Error while getting");
		});
	};


});
