package se.gritacademy.apicall

import android.R.attr.password
import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.widget.DatePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var rq: RequestQueue = Volley.newRequestQueue(this)
        val url = "https://italian-jokes.vercel.app/api/jokes"
        var r: StringRequest = StringRequest(
            Request.Method.GET,
            url,
            { res -> Log.d("Alrik", res.toString()) },
            { err -> Log.d("Alrik", err.toString()) }
        )

        var j: JsonObjectRequest = JsonObjectRequest(
            url,
            { res -> Log.d("Alrik", res.get("joke").toString()) },
            { err -> Log.d("Alrik", err.toString()) })



        rq.add(r)
        rq.add(j)


        /*openWeather*/

        //"http://api.openweathermap.org/geo/1.0/direct?q={city name},{state code},{country code}&limit={limit}&appid={API key}"
        var apikey = "66fe6923319ff73ef10de2fc1af0a10e"
        val city = "lund"

        var urlWeather =
            "https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$apikey";
        var v3url ="https://api.openweathermap.org/data/3.0/onecall?lat=55.6059&lon=13.000713.0007&exclude=alerts&appid=66fe6923319ff73ef10de2fc1af0a10e"


        var j2: JsonObjectRequest = JsonObjectRequest(
            urlWeather,
            { res ->
                //parse the API
                var mainWeather = res.getJSONObject("main");
                var descWeather = res.getJSONArray("weather");
                var description = descWeather.getJSONObject(0);
                var temperature = mainWeather.getString("temp");
                var feelsLike = mainWeather.getString("feels_like");
                var tempMin = mainWeather.getString("temp_min");
                var tempMax = mainWeather.getString("temp_max");
                var humidity = mainWeather.getString("humidity");
                var condition = description.getString("description");
                Log.d("Alrik", res.toString());
            }, { err -> Log.d("Alrik", err.toString()) }).also {

            Log.d("Alrik", "EFTER REQUESTEN")
        }




        val datePickerDialog = DatePickerDialog(
            // on below line we are passing context.
            this, { _: DatePicker, i: Int, i1: Int, i2: Int ->
                Log.d("Alrik", "hello")
            },
            // on below line we are passing year, month
            // and day for the selected date in our date picker.
            2023,
            9,
            7
        )
        // at last we are calling show
        // to display our date picker dialog.
        datePickerDialog.show()
    }
}
