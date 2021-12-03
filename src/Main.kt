import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import javax.net.ssl.HttpsURLConnection

fun main(args: Array<String>) {
    println(getStringJson("Москва"))
}
fun getStringJson(townName: String): String? {
    val API_KEY = "10f7db9c15a6ea66e809f1460b82341a"
    val https =
        "https://api.openweathermap.org/data/2.5/weather?q=${townName}&appid=${API_KEY}&units=metric&lang=ru"

    var connection: HttpsURLConnection? = null
    var reader: BufferedReader? = null

    try {
        val url = URL(https)
        connection = url.openConnection() as (HttpsURLConnection)
        connection.connect()

        val stream: InputStream? = connection.inputStream
        reader = BufferedReader(InputStreamReader(stream!!))

        val buffer = StringBuffer()
        var line = ""

        line = reader.readLine()
        buffer.append(line).append("\n")
        return buffer.toString()
    } catch (e: MalformedURLException){
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    } finally {
        connection?.disconnect()
        reader?.close()
    }
    return null
}

/*    fun parseJsonToArr(jsonStr: String): ArrayList<String> {
        val array = ArrayList<String>()
        try {
            val jsonObject = JSONObject(jsonStr)
            array.add(jsonObject
                .getJSONObject("main")
                .getDouble("temp").toString()
            )
        } catch (e : JSONException) {
            e.printStackTrace()
        }
        return array
    }*/