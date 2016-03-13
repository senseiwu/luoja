class CreateEventCtrl
  constructor: (@$log, @$location,  @EventService) ->
      @$log.debug "CreateEventCtrl created"
      @availableColors = ['Red','Green','Blue','Yellow','Magenta','Maroon','Umbra','Turquoise']
      @multipleDemo = {}
      @multipleDemo.colors2 = ['Blue','Red']
      @newItemType = 'bill'

      @event = {}
      @event.private = false

      setEventPrivete: () ->
        @$log.debug "Event private"
        @event.private = true

      setEventPublic: () ->
        @$log.debug "Event public"
        @event.private = false

      change: () ->
        @$log.debug "change"

controllersModule.controller('CreateEventCtrl', ['$log', '$location', 'EventService', CreateEventCtrl])
