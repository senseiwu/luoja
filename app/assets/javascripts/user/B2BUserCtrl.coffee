class B2BUserCtrl
  constructor: (@$cookieStore, @$log, @$location,  @UserService) ->
      # @$location.path("/b2bwellcome")
      # @$log.debug "B2B UserCtrl created " + @$location

controllersModule.controller('B2BUserCtrl', ['$cookieStore', '$log', '$location', 'UserService', B2BUserCtrl])
