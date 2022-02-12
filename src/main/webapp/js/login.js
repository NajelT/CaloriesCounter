(function() {
	
	var loginApp = angular.module('login', ['ngMessages']);	
	
	loginApp.factory('loginFactory', function($http) {
		var myService = {
				async: function (emailValue, passwordValue) {
					var promise = $http.get('/login', { params: {email: emailValue, password: passwordValue} })
					.then(function(response){
						return response.data;
					});
					return promise;
				}
		};
		return myService;
	});
	
	loginApp.controller('LoginCtrl', function(loginFactory, $scope) {
		this.email = "cindy@email.com"; // FIXME remove this value 
		this.password = "senha"; // FIXME remove this value 
		this.$messages = {};
		this.submit = function() {
			loginFactory.async(this.email, this.password).then(function(d) {				
				if(!d) {
					$scope.login.$messages.invalidLogin = true;
				} else {
					$scope.calories.login(d);
					$scope.login.$messages = {}
				}
			});
		};
	});  
	
})();