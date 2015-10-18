angular.module("administrador",[])
	.config(function($stateProvider){
        $stateProvider
        	.state('administrador', {
            	url: "/administrador",
            	templateUrl: 'js/administrador/administrador.html',
                controller:"AdministradorController"
            })
            .state('administrador.delivery',{
                url: "/delivery",
                templateUrl: "js/administrador/administrador.delivery.html"
            })
            .state('administrador.platos', {
                url: "/platos",
                templateUrl: "js/administrador/administrador.platos.html"
            })
    })
	.controller("AdministradorController", function($scope, $http, $mdDialog, categorias){
       console.log("hola desde amdinCtrlr");
       // $scope.promocion = {
       //      "name"
       // }
        $scope.promocion = {
            "id" : null,
            "nombre" : null,
            "descripcion" : null,
            "precioAnt" : null,
            "precioAct" : null
        }

        $scope.sendPromocion = function(){
            console.log($scope.promocion);
            var newPromocion = $scope.promocion;
            $http.post("/addPromocion",newPromocion)
            .success(function(a){
                console.log(a)
            })
            .error(function(err,status){
                console.log(err)
                console.log(status)
            });
        }
        $scope.deletePromocion = function(){
            console.log($scope.promocion);
            var oldPromocion = $scope.promocion;
            $http.post("/deletePromocion",oldPromocion)
            .success(function(a){
                console.log(a)
            })
            .error(function(err,status){
                console.log(err)
                console.log(status)
            });
        }
    })
