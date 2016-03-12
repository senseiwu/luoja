class UserCtrl

    constructor: (@$cookieStore, @$log, @$location,  @UserService, @CalendarService) ->
        @$log.debug "UserCtrl created"
        @userprofile = @$cookieStore.get('userdata')
        @suggestedEventsInfo = {}
        @upcomingEventsInfo = {}
        @pastEventsInfo = {}
        @uiConfig = @CalendarService.uiConfig
        @events1 = []
        @eventSources = [@events1]
        this.dashboard()
        @eventClicked = {}

    addEvents: (data) ->
      @events1.push {
          id:item.profileId,
          title: item.name,
          start: new Date(item.when),
          info: item.info,
          stick: true,
          url: '/#/event/'+item.slug
        } for item in data

    test: () ->
      @$log.debug "TEST "

    dashboard: () ->
      @UserService.details(@userprofile.email).then(
        (data) =>
          @$log.debug "dashboard: " + data.data.suggestedEventsInfo
          @upcomingEventsInfo = data.data.upcomingEventsInfo
          @pastEventsInfo = data.data.pastEventsInfo
          this.addEvents @upcomingEventsInfo
          # @$log.debug "ev2: " + JSON.stringify(obj) for obj in @eventSources
          # if data.data.status == 0
          #   @userprofile = data.data.user
          #   @$cookieStore.put('userdata', data.data.user)
          #   @$location.path("/dashboard")
          # else
          #   @$log.debug "Dashboard failed " + data.data.status
          #   @$cookieStore.remove('userdata')
          #   @$location.path("/")
      )
      @UserService.suggestions(@userprofile.email).then (
        (data) =>
          @$log.debug "Suggestions: " + data.data + ". " + data
          @suggestedEventsInfo = data.data
      )

controllersModule.controller('UserCtrl', ['$cookieStore', '$log', '$location', 'UserService', 'CalendarService', UserCtrl])
