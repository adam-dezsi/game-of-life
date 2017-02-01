angular.module("GameOfLife", [])
    .constant("REST_API", "/api")
    .controller('GameOfLifeController', ['$scope', '$http',
        function ($scope, $http) {
            $scope.hello = "Adam";
        }
    ]);