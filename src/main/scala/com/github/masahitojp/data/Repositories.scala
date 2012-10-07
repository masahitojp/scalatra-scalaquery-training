package com.github.masahitojp.data

/**
 * Created with IntelliJ IDEA.
 * User: test
 * Date: 12/10/05
 * Time: 0:43
 */
import org.scalaquery.session._
import org.scalaquery.session.Database.threadLocalSession
import org.scalaquery.ql._
import org.scalaquery.ql.TypeMapper._
import org.scalaquery.ql.basic.BasicDriver.Implicit._
import org.scalaquery.ql.basic.{BasicTable => Table}


object Repositories {

  val db = Database.forURL("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", driver = "org.h2.Driver")

  // TODO
  def initDB:Boolean = {
    var rtn = false
    try {
      db withSession {
        Beatles.ddl.create
        Beatles.insert(BeatlesMember(1, "John", "Lennon"))
        Beatles.insertAll(
          BeatlesMember(2, "Paul", "McCartney"),
          BeatlesMember(3, "George", "Harrison"),
          BeatlesMember(4, "Peter", "Best"))
      }
      rtn = true
    } catch {
      case e:Exception =>
    }
    rtn
  }

  val beatlesRepository = new BeatlesRepository
}

trait RepositorySupport {
  def beatlesRepository = Repositories.beatlesRepository

  def db = Repositories.db
}


class BeatlesRepository extends RepositorySupport {

  def findAll : Seq[BeatlesMember] = db withSession {
    (for (b <- Beatles) yield b.*).list
  }

}

