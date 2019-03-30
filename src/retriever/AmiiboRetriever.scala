package retriever

import play.api.libs.json.{JsArray, JsLookupResult, JsValue, Json}
import scalaj.http.Http

object AmiiboRetriever {
  def amiibo(name: String): String ={
    val urlBase: String = "http://www.amiiboapi.com/api/amiibo/?amiiboSeries=Super%20Smash%20Bros."
    val response: String = Http(urlBase).asString.body

    val parsed: JsValue = Json.parse(response)
    val array: JsArray = (parsed \ "amiibo").as[JsArray]
    val arraySize: Int = array.as[JsArray].value.size
    var release = ""
    for (index <- 0 until arraySize){
      if ((parsed \ "amiibo" \ index \ "name").as[String] == name){
        release = (parsed \ "amiibo" \ index \ "release" \ "na").as[String]
      }
    }
    name + " amiibo release date:  " + release
  }

  def main(args: Array[String]): Unit = {
    println(amiibo("Kirby"))
  }
}
