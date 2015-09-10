angular
.module('voteApp', ['ngMaterial', 'ui.router'])
.run(function($log){
  $log.debug("voteApp running");
})
.config(['$urlRouterProvider','$stateProvider', function($urlRouterProvider, $stateProvider){
      $urlRouterProvider.otherwise("/cliente");
      $stateProvider
      .state('cliente', {
          url: "/cliente",
          templateUrl: "partials/cliente.html",
          controller: "clienteController"
      })
        .state('login', {
            url: "/login",
            templateUrl: "partials/login.html",
            controller: "loginController"
        })
        .state('gestionar', {
            url: "/gestionar",
            templateUrl: "partials/gestionar.html"
        })
            .state('partido',{
                    url: "/partido",
                    templateUrl: "partials/gestionarpartido.html"
            })
          /*.state('route2.list', {
              url: "/list",
              templateUrl: "route2.list.html",
              controller: function($scope){
                $scope.things = ["A", "Set", "Of", "Things"];
              }
          })*/
}])
.controller('loginController',['$scope', function($scope){
    $scope.usuario = "admin";
    $scope.password = "admin";
    $scope.user;
    $scope.pass;
}])
.controller('clienteController',['$scope', function($scope){
  console.log("clientes");
  $scope.myDate = new Date();

}])
