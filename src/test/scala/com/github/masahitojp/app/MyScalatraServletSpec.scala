package com.github.masahitojp.app

import org.scalatra.test.scalatest.ScalatraFunSuite
import com.github.masahitojp.data.Repositories


class MyScalatraServletSpec extends ScalatraFunSuite {
  // `MyScalatraServlet` is your app which extends ScalatraServlet
  addServlet(classOf[MyScalatraServlet], "/*")

  override def start() {
    Repositories.initDB
    super.start()
  }
  test("simple get") {
    get("/") {
      status should equal (200)
      body should include ("world!")
    }
  }
  test("get resutls"){
    get("/beatles/"){
      status should equal (200)
      body should include ("id")
    }
  }

  test("get resutls by id "){
    get("/beatles/1"){
      status should equal (200)
      body should include ("id")
    }
  }

  test("put by id "){
      put("/beatles/4", """{"firstName":"foo","lastName":"Lennon"}""",
             Map("Content-Type" -> "application/json;charset=UTF-8",
               "accept-charset" -> "utf-8")) {
        status should equal (200)
        body should include ("true")
      }

      get("/beatles/4"){
        status should equal (200)
        body should include ("foo")
      }
  }

  test("delete by id "){
    delete("/beatles/1"){
      status should equal (200)
      body should include ("true")
    }

    get("/beatles/1"){
      status should equal (200)
    }
  }
}
