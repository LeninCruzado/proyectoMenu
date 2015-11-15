angular.module("administrador",[])
	.config(function($stateProvider){
        $stateProvider
        	.state('administrador', {
            	url: "/administrador",
            	templateUrl: 'js/administrador/administrador.html',
                controller:"AdministradorController"
            })
            // .state('administrador.delivery',{
            //     url: "/delivery",
            //     templateUrl: "js/administrador/administradorDelivery.html"
            // })
            // .state('administrador.platos', {
            //     url: "/platos",
            //     templateUrl: "js/administrador/administradorPlatos.html"
            // })
    })
	.controller("AdministradorController", function($scope, $http, $mdDialog, categorias, roles){
       console.log("hola desde amdinCtrlr");
       $scope.isActive = 1;
       $scope.roles = roles;
       // $scope.promocion = {
       //      "name"
       // }
        $scope.plato = {
            "id" : null,
            "nombre" : null,
            "descripcion" : null,
            "precio" : null
        }
        $scope.promocion = {
            "id" : null,
            "nombre" : null,
            "descripcion" : null,
            "precioAnt" : null,
            "precioAct" : null,
            "stock" : null
        }
        $scope.activate = function(param, view){
            if(view == 1)
                $scope.isActive = param;
            else
                if(view == 2)
                    $scope.isActiveCat = param;
        }
        $scope.sendPlato = function(){
            console.log($scope.plato);
            var newPlato = $scope.plato;
            $http.post("/addPlato",newPlato)
            .success(function(a){
                console.log(a)
            })
            .error(function(err,status){
                console.log(err)
                console.log(status)
            });
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
