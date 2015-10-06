angular.module("directives",[])

.directive('enter', function () {
    return function(scope, element){
        element.bind("mouseenter", function() {
            element.addClass('animated shake')
        })
        element.bind("mouseleave", function() {
            element.removeClass('animated shake')
        })
    }
})