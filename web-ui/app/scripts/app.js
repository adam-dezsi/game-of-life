angular.module("GameOfLife", [])
    .constant("REST_API", "http://localhost:8081/game")
    .controller('GameOfLifeController', ['$scope', '$http', 'REST_API',
        function ($scope, $http, REST_API) {

            $scope.state = "stopped";
            $scope.width = 5;
            $scope.height = 5;

            $scope.board = getDefaultBoard($scope.width, $scope.height);

            timer = null;
            
            $scope.startStop = function(){
                if ($scope.state == "stopped"){
                    $scope.state == "started";
                    start();
                } else if ($scope.state == "started"){
                    $scope.state == "stopped";
                    stop();
                }
            };

            function start(){
                timer = setInterval($scope.step, 3000);
            }

            function stop(){
                clearInterval(timer);
            }

            $scope.step = function(){
                $http.post(REST_API+'/step', {board: $scope.board})
                .then(
                    function onSuccess(response){
                        $scope.board = response.data.board;
                    },
                    function onError(){
                        //TODO
                    }
                );
            }

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

            $scope.flip = function(x, y){
                $scope.board[x][y] = !$scope.board[x][y];
            };

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