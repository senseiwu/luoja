class CalendarService
  constructor: (@$compile, @$timeout, @uiCalendarConfig, @$log) ->
      @$log.debug "CalendarService created"
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
      date = new Date('05-Mar-2016');
      d = date.getDate();
      m = date.getMonth();
      y = date.getFullYear();
      @events = [
        {title: 'All Day Event',start: new Date(y, m, 1)},
        {title: 'Long Event4',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
        {id: 999,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),allDay: false},
        {id: 999,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false},
        {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
        {title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://bing.com/'}
      ]
      @events1 = [] #= [{title: 'All Day Event',start: new Date(y, m, 1), url:'http://www.wp.pl'}]
      @eventSources = [@events]
      @eventClicked = {}

  addEvents: (data) ->
    @events1.push { id:item.profileId, title: item.name, start: new Date(item.when), info: item.info, stick: true} for item in data

  onEventClick: (date, jsEvent, view) ->
    @eventClicked = date
    console.log "Event clicked ughh --> " + date.url + " , " + date.title + ", " + @eventClicked.title
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
