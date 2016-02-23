
class UserService

  @headers = {'Accept': 'application/json', 'Content-Type': 'application/json'}
  @defaultConfig = { headers: @headers }
  # @user = {}

  constructor: (@$log, @$http, @$q) ->
    @$log.debug "constructing UserService"

  details: (username) ->
    deffer = @$q.defer()
    @$http.get('/usersdata/' + username)
      .success((data,status,headers) => deffer.resolve(data))
      .error((data,status,headers) => deffer.reject(data))

  signin: (user) ->
    deffer = @$q.defer()
    @$http.post('/signin', user)
      .success((data,status,headers) => deffer.resolve(data))
      .error((data,status,headers) => deffer.reject(data))

  update: (username, user) ->
    deffer = @$q.defer()
    @$http.put('/user/#{username}', user)
      .success((data,status,headers) => deffer.resolve(data))
      .error((datamstatus,headers) => deffer.reject(data))


  httpCall: (url) ->
    deffer = @$q.defer()

    @$http.get(url)
      .success((data, status, headers) => deffer.resolve(data))
      .error((data, status, headers) => deffer.reject(data))
    deffer.promise

servicesModule.service('UserService', ['$log', '$http', '$q', UserService])
