dependencies = [
    'ngRoute',
    'ngCookies',
    'ui.calendar',
    'ui.bootstrap',
    'ui.select',
    'ngSanitize'
    'mpApp.filters',
    'mpApp.directives',
    'mpApp.routeConfig',
    # 'mpApp.selectConfig',
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
            .when('/register', {
                templateUrl: '/assets/html/createUser.html'
            })
            .when('/dashboard', {
                templateUrl: '/assets/html/dashboard.html'
            })
            .when('/event/:eventName', {
                templateUrl: '/assets/html/event.html'
            })
            .when('/newevent', {
                templateUrl: '/assets/html/venueProfile.html'
            })
            .when('/b2bwellcome', {
                templateUrl: '/assets/html/businessWellcome.html'
            })
            .when('/b2bcreate', {
                templateUrl: '/assets/html/createBusinessUser.html'
            })
            .otherwise({redirectTo: '/'})])
    .config(['$locationProvider', ($locationProvider) ->
        $locationProvider.html5Mode({
            enabled: true,
            requireBase: false
        })])

# angular.module('mpApp.selectConfig', ['ui.select', 'ngSanitize'])
#   .constant(['uiSelectConfig', (uiSelectConfig) ->
#     # uiSelectConfig.theme = 'bootstrap'
#     uiSelectConfig.tagging-tokens = 'ENTER|TAB|,|SPACE'
#   ])


@controllersModule = angular.module('mpApp.controllers', [])
@servicesModule = angular.module('mpApp.services', [])
@directivesModule = angular.module('mpApp.directives', [])
@filtersModule = angular.module('mpApp.filters', [])
