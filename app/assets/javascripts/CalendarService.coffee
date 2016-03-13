class CalendarService
  constructor: (@$compile, @$timeout, @uiCalendarConfig, @$log) ->
      @$log.debug "CalendarService created"
      @eventClicked = {}
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

  onEventClick: (date, jsEvent, view) ->
    @eventClicked = date
    console.log "Event clicked --> " + date.url + " , " + date.title + ", " + @eventClicked.title
    if typeof(date.url) == 'undefined'
      $('#myModal').modal('toggle')
      $('#myModalLabel').text(date.title)
      $('#myModalInfo').text(date.info)
      $('#modalWhen').text(moment(date.start).format("DD-MM-YYYY"))
      $('#modalPeopleCount').text(33)
      $('#modalCommentsCount').text(12)
    else
      console.log "open link"

  eventRender: (event, element, view) ->
    console.log "ev render"

  renderCalender: (calendar) ->
    if(@uiCalendarConfig.calendars[calendar])
      @uiCalendarConfig.calendars[calendar].fullCalendar('render')

servicesModule.service('CalendarService', ['$compile', '$timeout', 'uiCalendarConfig', '$log', CalendarService])
