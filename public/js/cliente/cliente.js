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
                    templateUrl: "js/cliente/cliente.delivery.html"
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
            })
            .error(function(err,status){
                console.log(err);
                console.log(status)
            })
        // $scope.promociones = [
        //     {
        //         idPlato: "01",
        //         nombre: "Cuy Chactado",
        //         descripcion: "1/2 Cuy + papas doradas",
        //         precioAnterior: "25.00",
        //         precioActual: "20.00"
        //     },
        //     {
        //         idPlato: "02",
        //         nombre: "Lomo Saltado",
        //         descripcion: "Lomo fino de la mas alta calidad",
        //         precioAnterior: "25.00",
        //         precioActual: "20.00"
        //     },
        //     {
        //         idPlato: "03",
        //         nombre: "Ceviche",
        //         descripcion: "Cojinova + el mejor limon",
        //         precioAnterior: "25.00",
        //         precioActual: "20.00"
        //     },
        //     {
        //         idPlato: "04",
        //         nombre: "Seco de Pato",
        //         descripcion: "Al mejor estilo norteño",
        //         precioAnterior: "25.00",
        //         precioActual: "20.00"
        //     }
        // ]

        console.log($scope.promociones);
	})
    .controller('detallesDialogController', function($scope, $mdDialog, plato){
       console.log("holo");
       console.log(plato);
       $scope.plato = plato;
       $scope.cancelar = function(){
          $mdDialog.hide();
       }
    })
