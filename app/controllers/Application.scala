package controllers

import javax.inject.Inject

import play.api.i18n.{I18nSupport, MessagesApi}
import play.api.mvc._

class Application @Inject()(val messagesApi: MessagesApi) extends Controller with I18nSupport {

  def index = Action {
    //Redirect(routes.Events.list())
    //Ok(views.html.users.login())
    Ok(views.html.index())
  }

}
