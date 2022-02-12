(function() {
	var registerApp = angular.module('register', ['ngMessages']);
	
	registerApp.factory('userAddService', function($http) {
		var myService = {
				async: function (newUser) {
					var promise = $http.post("/users/add", null, {params:{
						name: newUser.name,
						email: newUser.email,
						password: newUser.password,
						profile: 'user'
					}})
					.then(function(response){
						return response.data;
					});
					return promise;
				}
		};
		return myService;
	});
	
	registerApp.controller('RegisterCtrl', function($scope, userAddService) {
		this.$messages = {};
		this.newUser = {};
		this.save = function() {
			if(this.newUser.password != this.newUser.repeatedPassword) {
				this.$messages.warning = true;
				return;
			}
			userAddService.async(this.newUser).then(function(data) {
				if(data != null) {
					$scope.calories.login(data);
					$scope.register.$messages.saveSuccess = true;
					this.newUser = {};
				} else {
					$scope.register.$messages.warning = true;
				}
			});
		}
	});
	
})();