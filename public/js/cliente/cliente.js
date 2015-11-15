angular.module("cliente",[])
	.config(function($stateProvider){
        $stateProvider
        	.state('cliente', {
            	url: "/cliente",
            	templateUrl: 'js/cliente/cliente.html',
                controller:"ClienteController"
                //,
            	// resolve: {
             //    	respuesta: function (Usuario) {
	            //         return Usuario.getDatos();
             //        }
             //    }
            })
                .state('cliente.delivery',{
                    url: "/delivery",
                    templateUrl: "js/cliente/cliente.delivery.html",
                    controller: "clienteDeliveryController"
                })
                .state('cliente.platos', {
                    url: "/platos",
                    templateUrl: "js/cliente/cliente.platos.html"
                })
    })
	.controller("ClienteController", function($scope, $http, $mdDialog, categorias){
        console.log("hola desde cliente");
        console.log(categorias);
        $scope.categorias = categorias;
        $scope.isActive = 1;


        $scope.activate = function(param, view){
            if(view == 1)
                $scope.isActive = param;
            else
                if(view == 2)
                    $scope.isActiveCat = param;
        }

        $scope.mostrarDetalles = function(plato){
            $mdDialog.show({
              controller: 'detallesDialogController',
              templateUrl: 'js/cliente/detallesDialog.html',
              clickOutsideToClose:true,
              locals: {
                plato:plato
              }
            })
            console.log("showing..")
        }
        //Aqui ira la peticion de promociones
        $http.get("/promociones")
            .success(function(data){
                console.log(data)
                $scope.promociones = data;
                console.log($scope.promociones);
            })
            .error(function(err,status){
                console.log(err);
                console.log(status)
            })
        $http.get("/platos")
            .success(function(data){
                console.log(data)
                var platos = data;
                console.log(platos);
            })
            .error(function(err,status){
                console.log(err);
                console.log(status)
            })
        //console.log($scope.promociones);
	})
    .controller('detallesDialogController', function($scope, $mdDialog, plato){
       console.log("holo");
       console.log(plato);
       $scope.plato = plato;
       $scope.cancelar = function(){
          $mdDialog.hide();
       }
    })
    .controller('clienteDeliveryController', function($scope, $state){
        console.log($scope.isActive);
        if($scope.isActive == 1){
            $state.go('cliente')
        }   
    })
