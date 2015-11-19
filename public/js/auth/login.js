angular.module("login",[])
	.config(function($stateProvider){
		$stateProvider
			.state('login',{
				url: "/login",
				templateUrl: 'js/auth/login.html',
				controller: 'loginController'
			})
	})
	.controller('loginController', function($scope,$state){
		console.log('hola desde login')
		var user = {
			username : "lenin",
			password : "lenin" 
		}
		console.log(user);
		$scope.sendForm = function(form){
			console.log(form)
			console.log(form.password.$modelValue)
			console.log(form.username.$modelValue)
			if(form.$invalid){
				if(form.username.$invalid){
					$scope.isInvalid = 1;
				}else{
					if(form.password.$invalid){
						$scope.isInvalid = 2;
					}		
				}
			}else{
				if(form.username.$modelValue == user.username && form.password.$modelValue == user.password){
					$state.go('cliente');
					console.log('welcome');
				}else{
					$scope.isInvalid = 3;
				}
			}
		}
	})