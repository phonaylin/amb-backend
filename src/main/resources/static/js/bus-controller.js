'use strict';

var ambBusApp = angular.module('ambBusApp', ['ngRoute', 'ambApp', 'busControllers']);

//configure our routes
ambBusApp.config(['$routeProvider', function($routeProvider) {
    $routeProvider
        // route for the home page
        .when('/', {
            templateUrl : 'components/bus-route-list.html',
            controller  : 'BusRouteListCtrl'
        })
        
        // route for the about page
        .when('/routes/:routeId', {
            templateUrl : 'components/bus-route-detail.html',
            controller  : 'BusRouteDetailCtrl'
        })
        
        .otherwise({
        	redirectTo: '/'
        });
}]);

var busControllers = angular.module('busControllers', []);

busControllers.controller('BusRouteListCtrl', ['$scope', '$http',
  function ($scope, $http) {
//  When the user selects a "route" from our MasterView list, we'll set this variable.
    $scope.selectedRoute = null;
    $scope.busRoutes = [
                  {
                	  'originCity' : {
                		  'name' : 'Yangon'
                	  },
                	  'routes' :
                	  [
                	   {
                		   'id' : '1',
                		   'origin' : {
                     		  'name' : 'Yangon'
                     	   },
                     	   'destination' : {
                     		  'name' : 'Mandalay'
                     	   }
                	   },
                	   {
                		   'id' : '2',
                		   'origin' : {
                     		  'name' : 'Yangon'
                     	   },
                     	   'destination' : {
                     		  'name' : 'Mandalay'
                     	   }
                	   },
                	   {
                		   'id' : '3',
                		   'origin' : {
                     		  'name' : 'Yangon'
                     	   },
                     	   'destination' : {
                     		  'name' : 'Taungyi'
                     	   }
                	   },
                	   {
                		   'id' : '4',
                		   'origin' : {
                     		  'name' : 'Yangon'
                     	   },
                     	   'destination' : {
                     		  'name' : 'Inle'
                     	   }
                	   }
                	  ]
                  }
    	  ];
  }]);

busControllers.controller('BusRouteDetailCtrl', ['$scope', '$routeParams',
  function($scope, $routeParams) {
    $scope.routeId = $routeParams.routeId;
    
    // dummy data for given routeId
    $scope.busScheduleList = [ 
	                              {
	                            	  'name' : 'Yangon -> Mandalay - 9:30 am | One Way (Normal Bus)',
	                            	  'departureTime' : '9:30 AM',
	                            	  'price' : '$17.00'
	                              },
	                              {
	                            	  'name' : 'Yangon -> Mandalay - 8:00 pm | One Way (Normal Bus)',
	                            	  'departureTime' : '8:00 PM',
	                            	  'price' : '$17.00'
	                              },
	                              {
	                            	  'name' : 'Yangon -> Mandalay - 8:00 pm | One Way (VIP Bus)',
	                            	  'departureTime' : '8:00 PM',
	                            	  'price' : '$26.00'
	                              },
	                              ];
  }]);