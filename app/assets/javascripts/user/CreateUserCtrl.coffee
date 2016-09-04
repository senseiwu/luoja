class CreateUserCtrl
  constructor: (@$log, @$location,  @UserService) ->
    @$log.debug "User ctrl created"
    @interests = []
    @skills = []

  createUser: () ->
    @$log.debug "User creation request triggered: " + @user.name
    @$log.debug "skill: " + JSON.stringify(obj) for obj in @skills
    @$log.debug "tag: " + JSON.stringify(obj) for obj in @interests

controllersModule.controller('CreateUserCtrl', ['$log', '$location', 'UserService', CreateUserCtrl])
