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
import com.jolbox.bonecp.BoneCPDataSource


object Repositories {

  Class.forName("org.h2.Driver") 	// load the DB driver
  protected val ds = new BoneCPDataSource()  // create a new datasource object
  ds.setJdbcUrl("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1");		// set the JDBC url
  ds.setUsername("sa")				// set the username
  ds.setPassword("")				// set the password
  ds.setMinConnectionsPerPartition(5)
  ds.setMaxConnectionsPerPartition(10)
  ds.setPartitionCount(1)

  val db = Database.forDataSource(ds)

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
  protected def beatlesRepository = Repositories.beatlesRepository

  protected def db = Repositories.db
}


class BeatlesRepository extends RepositorySupport {

  def findAll : Seq[BeatlesMember] = db withSession {
    (for (b <- Beatles) yield b.*).list
  }
  def byId(id: Long) : Option[BeatlesMember] = db withSession {
    Beatles.byId(id).firstOption
  }

  def update(member: BeatlesMember) = {
    db withSession {
      val q = for (b <- Beatles if b.id === member.id) yield b.first_name ~ b.last_name
      q.update(member.firstName, member.lastName)
    }
  }
}

