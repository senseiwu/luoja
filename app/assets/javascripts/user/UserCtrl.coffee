class UserCtrl

    constructor: (@$cookieStore, @$log, @$location,  @UserService) ->
        @$log.debug "UserCtrl created"
        @userprofile = @$cookieStore.get('userdata')
        @suggestedEvents = {}
        @upcomingEvent = {}
        @pastEvents = {}
        this.dashboard()

    dashboard: () ->
      @UserService.details(@userprofile.email).then(
        (data) =>
          @$log.debug "dashboard: " + data.data
          if data.data.status == 0
            @userprofile = data.data.user
            @$cookieStore.put('userdata', data.data.user)
            @$location.path("/dashboard")
          # else
          #   @$log.debug "Dashboard failed " + data.data.status
          #   @$cookieStore.remove('userdata')
          #   @$location.path("/")
      )

controllersModule.controller('UserCtrl', ['$cookieStore', '$log', '$location', 'UserService', UserCtrl])
