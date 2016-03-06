class CalendarService
  constructor: (@$compile, @$timeout, @uiCalendarConfig, @$log) ->
      @$log.debug "UserCtrl created"
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
        }
      };
      date = new Date();
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
      @eventSources = [@events]

  renderCalender: (calendar) ->
    @$log.debug "Render: " + calendar
    if(@uiCalendarConfig.calendars[calendar])
      @uiCalendarConfig.calendars[calendar].fullCalendar('render')

servicesModule.service('CalendarService', ['$compile', '$timeout', 'uiCalendarConfig', '$log', CalendarService])
