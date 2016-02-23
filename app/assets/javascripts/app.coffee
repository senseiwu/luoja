dependencies = [
    'ngRoute',
    'ngCookies',
    'ui.bootstrap',
    'mpApp.filters',
    'mpApp.directives',
    'mpApp.routeConfig'
    'mpApp.services',
    'mpApp.controllers',
]

app = angular.module('mpApp', dependencies)

angular.module('mpApp.routeConfig', ['ngRoute'])
    .config(['$routeProvider', ($routeProvider) ->
        $routeProvider
            .when('/', {
                templateUrl: '/assets/html/signin.html'
            })
            .when('/signup', {
                templateUrl: '/assets/html/signup.html'
            })
            .when('/dashboard', {
                templateUrl: '/assets/html/dashboard.html'
            })
            .otherwise({redirectTo: '/'})])
    .config(['$locationProvider', ($locationProvider) ->
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        })])

@controllersModule = angular.module('mpApp.controllers', [])
@servicesModule = angular.module('mpApp.services', [])
@directivesModule = angular.module('mpApp.directives', [])
@filtersModule = angular.module('mpApp.filters', [])
