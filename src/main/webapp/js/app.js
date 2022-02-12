(function(){
	var app = angular.module('calories', ['login', 'settings', 'meals', 'register', 'users']);
	
	var visibleTabsRoles = { USER: ['meals', 'settings'], 
	                         USER_MANAGER: ['users'],
	                         ADMIN_MANAGER: ['users', 'meals', 'settings'] };

	app.controller('CaloriesController', function($scope){
		this.loggedUser = null;
		this.loggingShowing = false;
		this.registeringShowing = false;
		this.logout = function() {
			this.loggedUser = null;
			$scope.logout();
		};
		this.showLogin = function() {
			this.loggingShowing = true;
			this.registeringShowing = false;
		};
		this.showRegister = function() {
			this.registeringShowing = true;
			this.loggingShowing = false;
		};
		this.login = function(user) {
			this.loggedUser = user;
			this.loggingShowing = false;
			this.registeringShowing = false;
			$scope.loginSuccess();
		};
		$scope.loginSuccess = function(){
		   $scope.$broadcast("loginSuccess", {loggedUser: $scope.calories.loggedUser});
		};		
		$scope.logout = function(){
		   $scope.$broadcast("logout", {loggedUser: $scope.calories.loggedUser});
		};		
	});
  
	app.controller('SectionController', function($scope) {
		this.selectedTab = {};
		this.visibleTabs = [];
		this.selectedUser = null;
		this.setTab = function(newTab) {
			this.selectedTab = newTab;
			$scope.tabSelected();	
		};
		this.setSelectedUser = function(user) {
			this.selectedUser = user;
		};
		this.isSelected = function(tab) {
			return this.selectedTab === tab;
		};
		this.tabIsVisible = function(tab) {
			if(this.visibleTabs.indexOf(tab) >= 0) return true;
			return false;
		}
		$scope.tabSelected = function(){
		   $scope.$broadcast("tabSelected", {selectedTab: $scope.section.selectedTab});
		};
		$scope.$on("loginSuccess", function(event, args){
			var userProfile = args.loggedUser.profile;
			$scope.section.visibleTabs = visibleTabsRoles[userProfile];
			$scope.section.setTab($scope.section.visibleTabs[0]);
		});
		$scope.$on("logout", function(event, args){
			$scope.section.selectedUser = null;
		});		
		
	});
	
	app.directive('login', function() {
		return { restrict: 'E', templateUrl: 'login.html' };
	});
	
	app.directive('register', function() {
		return { restrict: 'E', templateUrl: 'register.html' };
	});
	
	app.directive('settings', function() {
		return { restrict: 'E', templateUrl: 'settings.html' };
	});
	
	app.directive('meals', function(){
		return { restrict: 'E', templateUrl: 'meals.html' };
	});
	
	app.directive('users', function(){
		return { restrict: 'E', templateUrl: 'users.html' };
	});
		
		
})();