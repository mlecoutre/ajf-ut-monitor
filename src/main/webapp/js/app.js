'use strict';

// Declare app level module which depends on filters, and services
angular.module('blankApp', ['blankApp.filters', 'blankApp.services', 'blankApp.directives', 'ui']).
  config(['$routeProvider', function($routeProvider) {
    $routeProvider.
    	when('/index', {templateUrl: 'partials/index.html', controller: IndexCtrl}).
    	when('/contacts', {templateUrl: 'partials/contacts.html', controller: ContactsCtrl}).
    	when('/about', {templateUrl: 'partials/about.html', controller: AboutCtrl}).
    	otherwise({redirectTo: '/index'});
  }]);
