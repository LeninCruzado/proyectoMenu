angular
.module('restaurantApp',

  [
    //Dependencias
    'ngMaterial',
    'ui.router',
    'ngAnimate',
    'angular-parallax',
    //'ngMessages',
    //'ngMessages',

    //Common
    "directives",
    // "services",
    // "filters",
    "utils",

    //Componentes
    "cliente",
    "administrador"
    // "configuracion",
    // "factura",
    // "boleta",
    // "notaCredito",
    // "notaDebito",
    // "consulta",
    // "consulta.content",
    // "estadisticas",

  ])

.config(function($stateProvider, $urlRouterProvider){
    $urlRouterProvider.otherwise("/cliente");
    console.log("otherwise");
})
.run(function ($log) {
    $log.debug("App running");
    var wow = new WOW(
      {
        boxClass:     'wow',      // animated element css class (default is wow)
        animateClass: 'animated', // animation css class (default is animated)
        offset:       0,          // distance to the element when triggering the animation (default is 0)
        mobile:       true,       // trigger animations on mobile devices (default is true)
        live:         true,       // act on asynchronously loaded content (default is true)
        callback:     function(box) {
          // the callback is fired every time an animation is started
          // the argument that is passed in is the DOM node being animated
        },
        scrollContainer: null // optional scroll container selector, otherwise use window
      }
    );
    wow.init();
})
// angular
// .module('restaurantApp', ['ngMaterial', 'ui.router'])
// .run(function($log){
//     $log.debug("running....");
//     new WOW().init();
// })
// .config(function($urlRouterProvider, $stateProvider){
//       $urlRouterProvider.otherwise("/cliente");
//       $stateProvider
//       .state('cliente', {
//           url: "/cliente",
//           templateUrl: "partials/cliente.html",
//           controller: "clienteController"
//       })
//           .state('cliente.delivery',{
//             url: "/delivery",
//             templateUrl: "partials/cliente.delivery.html"
//           })
//            .state('cliente.platos', {
//           url: "/platos",
//           templateUrl: "partials/cliente.platos.html"
//       })
//       .state('login', {
//           url: "/login",
//           templateUrl: "partials/login.html",
//           controller: "loginController"
//       })
//       .state('partido',{
//               url: "/partido",
//               templateUrl: "partials/gestionarpartido.html"
//       })
// })
// .controller('loginController', function($scope){
//     $scope.usuario = "admin";
//     $scope.password = "admin";
//     $scope.user;
//     $scope.pass;
// })
// .controller('clienteController', function($scope, $mdDialog){
//   console.log("clientes");
//   $scope.myDate = new Date();
  
//   $scope.mostrarDetalles = function(){
//     $mdDialog.show({
//       controller: 'detallesDialogController',
//       templateUrl: 'partials/detallesDialog.html',
//       clickOutsideToClose:true
//     })
//     console.log("showing..")
//   }

// })

// .controller('detallesDialogController', function($scope, $mdDialog){
//    console.log("holo");
//    $scope.cancelar = function(){
//       $mdDialog.hide();
//    }
// })
