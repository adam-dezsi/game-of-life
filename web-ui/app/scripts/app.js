angular.module("GameOfLife", [])
    .constant("REST_API", "/api")
    .controller('GameOfLifeController', ['$scope', '$http',
        function ($scope, $http) {

            $scope.state = "stopped";
            $scope.width = 5;
            $scope.height = 5;

            $scope.board = getDefaultBoard($scope.width, $scope.height);
            
            $scope.startStop = function(){
                console.log($scope.width + " " + $scope.height);
            };

            function getDefaultBoard(width, height){
                var board = [];
                var column = [];
                for (var i = 0; i < height ; i++){
                    column.push(false);
                }
                for (var j = 0; j < width; j++){
                    board.push(angular.copy(column));
                }
                return board;
            }

            $scope.$watch('width', function() {
                if ($scope.width < 1){
                    $scope.width = 1;
                }
                if ($scope.board.length < $scope.width) {
                    var column = [];
                    for (var i = 0; i < $scope.height ; i++){
                        column.push(false);
                    }
                    $scope.board.push( column );
                } else if ($scope.board.length > $scope.width){
                    $scope.board.pop();
                }
            });

            $scope.$watch('height', function() {
                if ($scope.height < 1){
                    $scope.height = 1;
                }
                if ($scope.board[0].length < $scope.height) {
                    for (var i = 0; i < $scope.width ; i++){
                        $scope.board[i].push(false);
                    }
                } else if ($scope.board[0].length > $scope.height){
                    for (var i = 0; i < $scope.width ; i++){
                        $scope.board[i].pop();
                    }
                }
            });
        }
    ]);