package retriever

import play.api.libs.json.{JsValue, Json}
import scalaj.http.Http

object Retriever {
  def inputMovie(movieName: String): String = {
    val movie: Array[String] = movieName.split(" ")
    var movieTitle: String = ""
    for (word <- movie){
      if (movie(movie.length-1) != word){
        movieTitle += word
        movieTitle += "+"
      }
      else if (movie(movie.length-1) == word){
        movieTitle += word
      }
    }
    var urlBase: String = "http://www.omdbapi.com/?t="
    urlBase += movieTitle
    urlBase += "&apikey=d91d9b48"
    urlBase
    val response: String = Http(urlBase).asString.body

    val parsed: JsValue = Json.parse(response)
    val year: String = (parsed \ "Year").as[String]
    year
  }

  def main(args: Array[String]): Unit = {
    println(inputMovie("Titanic"))
  }
}
