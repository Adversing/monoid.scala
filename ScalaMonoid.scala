object ScalaMonoid extends App {
  trait Monoid[A] {
    def mempty: A
    def mappend(a: A, b: A): A
  }

  object Monoid {
    def apply[A](implicit m: Monoid[A]): Monoid[A] = m

    implicit val stringMonoid: Monoid[String] = new Monoid[String] {
      def mempty: String = ""
      def mappend(a: String, b: String): String = a + b
    }

    implicit val intMonoid: Monoid[Int] = new Monoid[Int] {
      def mempty: Int = 0
      def mappend(a: Int, b: Int): Int = a + b
    }

    implicit def listMonoid[A]: Monoid[List[A]] = new Monoid[List[A]] {
      def mempty: List[A] = List.empty[A]
      def mappend(a: List[A], b: List[A]): List[A] = a ++ b
    }
  }

  implicit class MonoidOps[A](val a: A)(implicit m: Monoid[A]) {
    def |+|(b: A): A = m.mappend(a, b)
  }

  println("Hello" |+| " " |+| "World") // Output: Hello World
  println(1 |+| 2 |+| 3) // Output: 6
  println(List(1, 2) |+| List(3, 4)) // Output: List(1, 2, 3, 4)
  println(Monoid[String].mempty |+| "Hello") // Output: Hello
}
