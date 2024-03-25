var app = angular.module("MyApp", []);
app.controller("LoginController", function ($scope, $http) {

    $scope.welcomeMessage = "Hey buddy welcome...";
    $scope.LongMessage = "You have successfully integrated Angular with java";
    console.log("Processed Till here");
    $http.get('http://localhost:8080/api/getData').then(function (response) {
        if (response.status == "200") {
            //$scope. = true;
            $scope.userCreatedSucess = true;
        } else {
            $scope.displayError = true;
        }
    });
});

angular.module("Register", ['ui.bootstrap']).controller("UserController",
    function ($scope, $http) {

    console.log("here....is usercontroller.....")
    $scope.userData = {
        'email': "",
        'password': "",
        "name":"",
        "dob":""
    };
    $scope.addUser = function () {
        console.log(" I reached here........");
        $http.post('http://localhost:8080/user/register', $scope.userData).then(function (response) {
            if (response.status == "200") {
                $scope.userCreatedSucess = true;
            } else {
                $scope.displayError = true;
            }
        }).catch(function (response) {
            $scope.displayError = true;
        });
    };
});

angular.module("Search", ['ui.bootstrap']).controller("LibroController",
    function ($scope, $http) {
        // it's for default check box thing...
        $scope.libri = "getLibros";
        $scope.rounds = 5;
        $scope.getLibros = "Search all Libro Details";
        $scope.count = "No of Libros";
        $scope.addLibro = "Add New Libro";
        $scope.delLibro = "Delete Existing Libro";
        $scope.borrowLibro = "Libroing";
        $scope.cancelBorrow = "Cancel Libroing";
        $scope.libroCart = [{
            'isbn': '',
            'title': '',
            'cover': '',
            'publishser': '',
            'pages': '',
            'available': ''
        }];
        $scope.delCart = [];
        $scope.orderCart = {
            'libroing_id': "",
            'isbn': "isbn",
            'libroing_date': "",
            'quantity': "quantity"
        };
        $scope.cancelCart;
        $scope.search = function () {
            var choice = $scope.libri;
            $scope.searchLibro = false;
            $scope.addLibroFlag = false;
            $scope.countFlag = false;
            $scope.delLibroFlag = false;
            $scope.displayStandardMessage = false;
            $scope.displayError = false;
            $scope.borrowFlag = false;
            $scope.cancelLibroing = false;
            $scope.displayLibroingSucess = false;
            $scope.displayCancelSucess = false;
            // Pagination Logic
            if (choice == 'getLibros') {
                $scope.searchLibro = true;
                var url = 'http://localhost:8080/api/' + choice;
                $http.get(url).then(function (response) {
                    $scope.output = response.data;
                    pagination();
                });
            } else if (choice == 'addLibro') {
                // clear before adding libri....
                clear();
                $scope.addLibroFlag = true;

            } else if (choice == 'delLibro') {
                $scope.delLibroFlag = true;
                searchLibros();

            } else if (choice == 'count') {
                $scope.countFlag = true;

            } else if (choice == 'borrowLibro') {
                $scope.borrowFlag = true;
                loadLibros();

            } else if (choice == 'cancelBorrow') {
                $scope.cancelLibroing = true;
                loadLibroedThings();

            } else {
                $scope.searchLibro = true;
                var url = 'http://localhost:8080/api/' + choice;
                $http.get(url).then(function (response) {
                    $scope.output = response.data;
                });
            }
        }

        $scope.addRow = function () {
            console.log($scope.libroCart);
            $http.post("http://localhost:8080/api/addLibro", $scope.libroCart).then(function (response) {
                if (response.status == "200") {
                    $scope.addLibroFlag = false;
                    $scope.displayError = false;
                    $scope.displayStandardMessage = true;
                    clear();
                } else {
                    $scope.displayError = true;
                }
            }).catch(function (response) {
                $scope.displayError = true;
            });
        };

        function paginationDel() {
            $scope.filteredTodosC = [];
            $scope.todos = [];
            $scope.currentPage = 1;
            $scope.numPerPage = 9;
            $scope.maxSize = 5;
            $scope.$watch('currentPage + numPerPage', function () {
                var begin = (($scope.currentPage - 1) * $scope.numPerPage);
                var end = begin + $scope.numPerPage;
                $scope.filteredTodosC = $scope.libroCache.slice(begin, end);
            });
        }

        function paginationLibroing() {

            $scope.filteredTodosC = [];
            $scope.todos = [];
            $scope.currentPage = 1;
            $scope.numPerPage = 9;
            $scope.maxSize = 5;


            $scope.$watch('currentPage + numPerPage', function () {

                var begin = (($scope.currentPage - 1) * $scope.numPerPage);
                var end = begin + $scope.numPerPage;
                $scope.alreadyLibroed = $scope.cancelCache.slice(begin, end);
            });


        }

        function loadLibros() {

            $http.get('http://localhost:8080/api/getLibros').then(function (response) {
                $scope.libroCache = response.data;
                paginationDel();
            });


        }

        function loadLibroedThings() {

            $http.get('http://localhost:8080/api/getLibroingDetails').then(function (response) {
                $scope.cancelCache = response.data;
                paginationLibroing();
            });


        }

        function clear() {
            $scope.libroCart = [{
                'isbn': '',
                'title': '',
                'cover': '',
                'publishser': '',
                'pages': '',
                'available': ''
            }];

        }

        function pagination() {
            $scope.filteredTodos = [];
            $scope.todos = [];
            $scope.currentPage = 1;
            $scope.numPerPage = 9;
            $scope.maxSize = 5;


            $scope.$watch('currentPage + numPerPage', function () {
                var begin = (($scope.currentPage - 1) * $scope.numPerPage);
                var end = begin + $scope.numPerPage;
                $scope.filteredTodos = $scope.output.slice(begin, end);


            });


        }

        function clearDelCart() {
            $scope.delCart = [];

        }

        function searchLibros() {

            $http.get('http://localhost:8080/api/getLibros').then(function (response) {
                $scope.libroCache = response.data;
                paginationDel();
            });


        }

        $scope.addRows = function ($event) {
            $scope.libroCart.push({});
        };

        $scope.makeLibroing = function (bcache) {
            /*
             * Pick required fields from Libroing to be inserted into Order table.
             * Libroing_id
             */

            $scope.orderCart.isbn = bcache.isbn;
            $scope.orderCart.quantity = bcache.pages;
            $scope.orderCart.libroing_date = new Date();

            //
            $http.post("http://localhost:8080/api/makeLibroing", $scope.orderCart).then(function (response) {
                if (response.status == "200") {
                    $scope.displayError = false;
                    $scope.displayLibroingSucess = true;
                    clear();
                } else {
                    $scope.displayError = true;
                }
            }).catch(function (response) {

                $scope.displayError = true;
            });

        }

        $scope.cancelLibroingM = function (bcache) {
            /*
             * Pick required fields from Libroing to be inserted into Order table.
             * Libroing_id
             */

            $scope.cancelCart = bcache.libroing_id;

            /*alert($scope.cancelCart.libroing_id);*/
            //
            $http.post("http://localhost:8080/api/cancelLibroing", $scope.cancelCart).then(function (response) {
                if (response.status == "200") {
                    $scope.displayError = false;
                    $scope.displayCancelSucess = true;
                    clear();
                    loadLibroedThings();
                } else {
                    $scope.displayError = true;
                }
            }).catch(function (response) {

                $scope.displayError = true;
            });

        }

        $scope.deleteRows = function (bcache) {

            angular.forEach($scope.filteredTodosC, function (sel) {
                if (sel.selected) {
                    $scope.delCart.push(sel);
                }
            });

            // alert(JSON.stringify($scope.delCart));
            $http.post('http://localhost:8080/api/delLibro/', $scope.delCart).then
            (function (response) {

                if (response.status == "200") {

                    $scope.addLibroFlag = false;
                    $scope.displayError = false;
                    $scope.displayStandardMessage = true;
                    clearDelCart();
                    searchLibros();
                } else {
                    $scope.displayError = true;
                }
            }).catch(function (response) {

                $scope.displayError = true;
            });


        }
        $scope.removeRows = function (user) {

            $scope.libroCart.splice();

        };

    });