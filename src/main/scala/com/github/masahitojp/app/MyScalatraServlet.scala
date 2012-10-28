package com.github.masahitojp.app

import org.scalatra._
import scalate.ScalateSupport
import com.github.masahitojp.data.{BeatlesMemberNoId, RepositorySupport, BeatlesMember}

import net.liftweb.json._
import org.scalatra.liftjson.LiftJsonSupport
import grizzled.slf4j.Logger
import vo.VoResponse

class MyScalatraServlet extends ScalatraServlet with ScalateSupport with LiftJsonSupport with RepositorySupport
  {

  val logger = Logger(classOf[MyScalatraServlet])

  get("/") {
    <html>
      <body>
        <h1>Hello, world!!!?</h1>
        Say <a href="hello-scalate">hello to Scalate</a>.
      </body>
    </html>
  }

  get("/beatles/?"){
    Extraction.decompose{
      beatlesRepository.findAll
    }
  }

  get("/beatles/:id"){
    val id:Long = params("id").toLong
    Extraction.decompose{
      beatlesRepository.byId(id).getOrElse("")
    }
  }

  put("/beatles/:id"){
    val id:Long = (params.get("id") getOrElse "0").toLong
    parsedBody match {
      case JNothing ⇒ halt(401, "invalid json")
      case json: JObject ⇒ {

        val member: BeatlesMemberNoId = json.extract[BeatlesMemberNoId]
        val result = beatlesRepository.update(BeatlesMember(id, member.firstName, member.lastName))
        Extraction.decompose(VoResponse("ok", result))
      }
      case _ ⇒ halt(400, "unknown json")
    }

  }

  delete("/beatles/:id"){
    val id:Long = (params.get("id") getOrElse "0").toLong
    val result = beatlesRepository.delete(id)
    Extraction.decompose(VoResponse("ok", result))
  }

  notFound {
    // remove content type in case it was set through an action
    contentType = null 
    // Try to render a ScalateTemplate if no route matched
    findTemplate(requestPath) map { path =>
      contentType = "text/html"
      layoutTemplate(path)
    } orElse serveStaticResource() getOrElse resourceNotFound() 
  }
}
