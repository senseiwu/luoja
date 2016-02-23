
class EventService

  @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
  @defaultConfig = { headers: @headers }

  constructor: (@$log, @$http, @$q) ->
    @$log.debug "constructing EventService"

  httpCall: (url) ->
    deffer = @$q.defer()

    @$http.get(url)
      .success((data, status, headers) => deffer.resolve(data))
      .error((data, status, headers) => deffer.reject(data))
    deffer.promise

servicesModule.service('EventService', ['$log', '$http', '$q', EventService])
