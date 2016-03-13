
class EventCtrl

    constructor: (@$log, @EventService) ->
        @$log.debug "constructing EventController"
        @events = []
        @text = "Hello"
        @temp = []
        @getAllEvents()

    getAllEvents: () ->
        @$log.debug "Hello Ctrl"

        @EventService.httpCall("/upcomingJson").then(
          (data) => @temp.push({index:1, "name": "Upcoming", "content": data}),
          (error) => @$log.error "Unable to get Users: #{error}",
          @EventService.httpCall("/todayJson").then(
            (data) => @temp.push({index:2, "name": "Today", "content": data}),
            (error) => @$log.error "Unable to get Users: #{error}",
            @EventService.httpCall("/pastJson").then(
              (data) => @temp.push({index:3, "name": "Past", "content": data}),
              (error) => @$log.error "Unable to get Users: #{error}",
              @events = @temp)))

    getText: () ->
      "hello"
controllersModule.controller('EventCtrl', ['$log', 'EventService', EventCtrl])
