trait User {
  val child: Option[User]
}
object UserService {
  def loadUser(name: String): Option[User] = { /** get user **/ }
}
val getChild: User => Option[User] = (user: User) => user.child

val res: Option[User] = UserService.loadUser("mike").flatMap(getChild).flatMap(getChild)

val result: Option[User] = for {
  user <- UserService.loadUser("mike")
  usersChild <- user.child
  usersGrandChild <- usersChild.child
} yield usersGrandChild