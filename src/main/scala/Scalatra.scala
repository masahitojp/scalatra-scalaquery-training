import com.github.masahitojp.app._
import com.github.masahitojp.data.Repositories
import org.scalatra._
import javax.servlet.ServletContext
import org.scalaquery.session.Database


/**
 * This is the Scalatra bootstrap file. You can use it to mount servlets or
 * filters. It's also a good place to put initialization code which needs to
 * run at application start (e.g. database configurations), and init params.
 */
class Scalatra extends LifeCycle {
  override def init(context: ServletContext) {

    Repositories.initDB
    // Mount one or more servlets
    context.mount(new MyScalatraServlet, "/*")
  }
}
