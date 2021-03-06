package code.remotedata

import java.util.Date

import akka.actor.Actor
import akka.event.Logging
import code.usercustomerlinks.{MappedUserCustomerLinkProvider, RemotedataUserCustomerLinkProviderCaseClass}
import code.util.Helper.MdcLoggable


class RemotedataUserCustomerLinksActor extends Actor with ActorHelper with MdcLoggable {

  val mapper = MappedUserCustomerLinkProvider
  val cc = RemotedataUserCustomerLinkProviderCaseClass

  def receive = {

    case cc.createUserCustomerLink(userId: String, customerId: String, dateInserted: Date, isActive: Boolean) =>
      logger.debug("createUserCustomerLink(" + userId + ", " + dateInserted + ", " + isActive + ")")
      sender ! extractResult(mapper.createUserCustomerLink(userId, customerId, dateInserted, isActive))

    case cc.getUserCustomerLinkByCustomerId(customerId: String) =>
      logger.debug("getUserCustomerLinkByCustomerId(" + customerId + ")")
      sender ! extractResult(mapper.getUserCustomerLinkByCustomerId(customerId))

    case cc.getUserCustomerLinkByUserId(userId: String) =>
      logger.debug("getUserCustomerLinkByUserId(" + userId + ")")
      sender ! extractResult(mapper.getUserCustomerLinkByUserId(userId))

    case cc.getUserCustomerLink(userId: String, customerId: String) =>
      logger.debug("getUserCustomerLink(" + userId + ", " + customerId + ")")
      sender ! extractResult(mapper.getUserCustomerLink(userId, customerId))

    case cc.getUserCustomerLinks() =>
      logger.debug("getUserCustomerLinks()")
      sender ! extractResult(mapper.getUserCustomerLinks)

    case cc.bulkDeleteUserCustomerLinks() =>
      logger.debug("bulkDeleteUserCustomerLinks()")
      sender ! extractResult(mapper.bulkDeleteUserCustomerLinks())

    case message => logger.warn("[AKKA ACTOR ERROR - REQUEST NOT RECOGNIZED] " + message)

  }

}

