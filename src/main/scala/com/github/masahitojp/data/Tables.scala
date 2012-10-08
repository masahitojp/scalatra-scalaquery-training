package com.github.masahitojp.data

/**
 * Created with IntelliJ IDEA.
 * User: test
 * Date: 12/10/05
 * Time: 0:35
 */

import org.scalaquery.ql._
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql.basic.BasicDriver.Implicit._
import org.scalaquery.ql.basic.{BasicTable => Table}

object Beatles extends Table[BeatlesMember]("beatles") {
   def id = column[Long]("id", O PrimaryKey)
   def first_name = column[String]("first_name")
   def last_name = column[String]("last_name")
   def * = id~first_name~last_name <> (BeatlesMember, BeatlesMember.unapply _)

   val byId = createFinderBy(_.id)
 }
