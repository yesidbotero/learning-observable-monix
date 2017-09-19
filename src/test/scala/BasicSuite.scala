import monix.eval.Task
import monix.reactive.Observable
import org.scalatest.FunSuite
import scala.concurrent.duration._
import monix.execution.Scheduler.Implicits.global


class BasicSuite extends FunSuite {

  test("a Task's behavior is lazy") {
    //side effect for test
    val task = Task {
      println("lazy")
    }
    println("-----")
    task.runAsync
    assert(true)
  }

  test("observable") {
    val tick = {
      Observable.fromIterable(0 to 10)
        // common filtering and mapping
        .filter(_ % 2 == 0)
        .map(_ * 2)
        // any respectable Scala type has flatMap, w00t!
        .flatMap(x => Observable.fromIterable(List(x, x)))
        // only take the first 5 elements, then stop
        .take(5)
        // to print the generated events to console
        .dump("Out")
    }

    tick.subscribe()
  }
}
