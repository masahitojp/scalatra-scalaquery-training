package com.github.masahitojp.app

import org.scalatra.test.scalatest.ScalatraFunSuite


class MyScalatraServletSpec extends ScalatraFunSuite {
  // `MyScalatraServlet` is your app which extends ScalatraServlet
  addServlet(classOf[MyScalatraServlet], "/*")

  test("simple get") {
    get("/") {
      status should equal (200)
      body should include ("world!")
    }
  }
}
