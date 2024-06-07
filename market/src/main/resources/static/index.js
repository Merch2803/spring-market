angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.init = function () {
        $http.get(contextPath + '/api/v1/products')
            .then(function (response) {
                $scope.products = response.data;
            });
    };

    $scope.createNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                $scope.init();
                $scope.newProduct = null;
            }, function errorCallback(response) {
                console.log(response.data);
                alert('Error: ' + response.data.messages);
            });
    };

    $scope.addProductToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add',
            method: 'GET',
            params: {
                id: productId
            }
        }).then(function (response) {
            $scope.getProductCarts();
        }, function errorCallback(response) {
        });
    }

    $scope.getProductCarts = function () {
        $http.get(contextPath + '/api/v1/cart')
            .then(function (response) {
                $scope.productsInCart = response.data;
            });
    }

    $scope.clearCart = function () {
        $http({
            url: contextPath + '/api/v1/cart/clear',
            method: 'GET'
        }).then(function (response) {
            $scope.getProductCarts();
        }, function errorCallback(response) {
        });
    }

    $scope.init();
    $scope.getProductCarts();

});