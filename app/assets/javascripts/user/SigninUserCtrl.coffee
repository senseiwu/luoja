
class SigninUserCtrl

    constructor: (@$cookieStore, @$log, @$location,  @UserService) ->
        @$log.debug "SigninUserCtrl created"
        @userprofile = @$cookieStore.get('userdata')
        if !this.isUndefined(@userprofile)
          @$log.debug "User is remembered, redirect to dashboard"
          @$location.path("/dashboard")

    loginUser: () ->
      @UserService.signin(@user).then(
        (data) =>
          if data.data.status == 0
            @userprofile = data.data.user
            @$cookieStore.put('userdata', data.data.user)
            @$location.path("/dashboard")
          else
            @$log.debug "Login failed " + data.data.status
            @$location.path("/")
      )

    createUser: () ->
        @$log.debug "createUser()"
        @user.active = true
        @UserService.createUser(@user)
        .then(
            (data) =>
                @$log.debug "Promise returned #{data} User"
                @user = data
                @$location.path("/")
            ,
            (error) =>
                @$log.error "Unable to create User: #{error}"
            )

    isUndefined: (value) ->
      angular.isUndefined(value) || value == null

controllersModule.controller('SigninUserCtrl', ['$cookieStore', '$log', '$location', 'UserService', SigninUserCtrl])
