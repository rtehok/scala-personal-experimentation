case class Movie(movieId: String, title: String)
case class Video(movieId: String)
case class DecoratedMovie(movie: Movie, video: Video)

trait MovieDaoComponent {
  trait MovieDao {
    def getMovie(id: String): Movie
  }
}

trait FavoriteServiceComponent {
  trait FavoritesService {
    def getFavoriteVideos(id: String): Vector[Video]
  }
}

trait MovieDaoComponentImpl extends MovieDaoComponent {
  class MovieDaoImpl extends MovieDao {
    override def getMovie(id: String) = Movie("42", "A movie")
  }
}

trait FavoritesServiceComponentImpl extends FavoriteServiceComponent {
  class FavoritesServiceImpl extends FavoritesService {
    override def getFavoriteVideos(id: String) = Vector(Video("1"))
  }
}

// Cake pattern
trait MovieServiceComponentImpl {
  this: MovieDaoComponent with FavoriteServiceComponent =>

  val favoritesService: FavoritesService
  val movieDao: MovieDao

  class MovieServiceImpl {
    def getFavoriteDecoratedMovies(userId: String): Vector[DecoratedMovie] =
      for {
        favoriteVideo <- favoritesService.getFavoriteVideos(userId)
        movie = movieDao.getMovie(favoriteVideo.movieId)
      } yield DecoratedMovie(movie, favoriteVideo)
  }
}

object ComponentRegistry extends MovieServiceComponentImpl
  with FavoritesServiceComponentImpl with MovieDaoComponentImpl {
  val favoritesService = new FavoritesServiceImpl
  val movieDao = new MovieDaoImpl

  val movieService = new MovieServiceImpl
}

val movieService = ComponentRegistry.movieService
movieService.getFavoriteDecoratedMovies("someone")

trait MovieDaoComponentTestImpl extends MovieDaoComponent {
  class MovieDaoTestImpl extends MovieDao {
    override def getMovie(id: String): Movie = Movie("43", "A test Movie")
  }
}

trait FavoritesServiceComponentTestImpl extends FavoriteServiceComponent {
  class FavoriteServiceTestImpl extends FavoritesService {
    override def getFavoriteVideos(id: String) = Vector(Video("2"))
  }
}

object TestComponentRegistry extends MovieServiceComponentImpl
  with MovieDaoComponentTestImpl with FavoritesServiceComponentTestImpl {
  override val favoritesService = new FavoriteServiceTestImpl
  override val movieDao = new MovieDaoTestImpl

  val movieService = new MovieServiceImpl
}

val movieServiceTest = TestComponentRegistry.movieService
movieServiceTest.getFavoriteDecoratedMovies("test")