class UserCtrl

    constructor: (@$cookieStore, @$log, @$location,  @UserService, @uiCalendarConfig) ->
        @$log.debug "UserCtrl created"
        @userprofile = @$cookieStore.get('userdata')
        @suggestedEventsInfo = {}
        @upcomingEventsInfo = {}
        @pastEventsInfo = {}
        @events1 = []
        @eventSources = [@events1]
        this.dashboard()
        @evc = {title:"jjj"}
        @eventClicked = [@evc]

        @uiConfig = {
          calendar:{
            height: 'auto',
            firstDay: 1,
            editable: true,
            header:{
              left: 'title',
              center: '',
              right: 'today prev,next'
            },
            eventClick: this.onEventClick
            eventRender: this.eventRender
          }
        };

    test: () ->
      @$log.debug "TEST " + $('#modalEventSlug').val()

    onEventClick: (date, jsEvent, view) ->
      console.log "ST: " + @evc
      @evc = date
      console.log "Event clicked --> " + date.slug + " , " + date.title + ", " + @evc.title
      if typeof(date.url) == 'undefined'
        $('#myModal').modal('toggle')
        $('#myModalLabel').text(date.title)
        $('#myModalInfo').text(date.info)
        $('#modalWhen').text(moment(date.start).format("DD-MM-YYYY"))
        $('#modalPeopleCount').text(33)
        $('#modalCommentsCount').text(12)
        $('#modalEventSlug').text(date.slug)
      else
        console.log "open link"

    eventRender: (event, element) ->
      element.attr(
        {
          'tooltip': event.title,
          'tooltip-append-to-body': true
          }
        );

    renderCalender: (calendar) ->
      if(@uiCalendarConfig.calendars[calendar])
        @uiCalendarConfig.calendars[calendar].fullCalendar('render')

    addEvents: (data) ->
      @events1.push {
          id:item.profileId,
          title: item.name,
          start: new Date(item.when),
          info: item.info,
          stick: true,
          slug: item.slug
        } for item in data

    dashboard: () ->
      @UserService.details(@userprofile.email).then(
        (data) =>
          @$log.debug "dashboard: " + data.data.suggestedEventsInfo
          @upcomingEventsInfo = data.data.upcomingEventsInfo
          @pastEventsInfo = data.data.pastEventsInfo
          this.addEvents @upcomingEventsInfo
          @$log.debug "ev2: " + JSON.stringify(obj) for obj in @eventSources
      )
      @UserService.suggestions(@userprofile.email).then (
        (data) =>
          @$log.debug "Suggestions: " + data.data + ". " + data
          @suggestedEventsInfo = data.data
      )

controllersModule.controller('UserCtrl', ['$cookieStore', '$log', '$location', 'UserService', 'uiCalendarConfig', UserCtrl])
