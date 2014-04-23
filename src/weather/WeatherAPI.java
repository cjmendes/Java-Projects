package weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.net.URL;

import com.google.gson.*;

public class WeatherAPI
{
	// Weather OAuth access token (a unique ID).
	// You can register for your own access token, or use the one here.
	private final String ACCESS_TOKEN = "0757ee8cfe589f60";
	private JsonElement jse = null;
	
	
	public void getInformation(String zip)
	{
		try
		{
			// Encode given URL -- could throw UnsupportedEncodingException
			String zipCode = URLEncoder.encode(zip, "utf-8");

			// Construct Weather API URL
			URL weatherURL = new URL("http://api.wunderground.com/api/"
					+ ACCESS_TOKEN + "/conditions/q/"
					+ zipCode + ".json");

			// Open the URL
			InputStream is = weatherURL.openStream(); // throws an IOException
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			// Read the result into a JSON Element
			jse = new JsonParser().parse(br);

			// Close the connection
			is.close();
			br.close();
		}
		catch (java.io.UnsupportedEncodingException uee)
		{
			uee.printStackTrace();
		}
		catch (java.net.MalformedURLException mue)
		{
			mue.printStackTrace();
		}
		catch (java.io.IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public String getWeather()
	{
		if (jse != null)
		{
		    String weather = jse.getAsJsonObject().get("current_observation")
                    .getAsJsonObject().get("weather").getAsString();
		    return weather;
		}
		else
		{
			return null;
		}
	}
	
	public double getTemperature()
	{
		if (jse != null)
		{
		    Double temp = jse.getAsJsonObject().get("current_observation")
                    .getAsJsonObject().get("temp_f").getAsDouble();
		    return temp;
		}
		else
		{
			Double temp = Double.NaN;
			return temp;
		}
	}
	
	public double getWindSpeed()
	{
		if (jse != null)
		{
		    Double wind = jse.getAsJsonObject().get("current_observation")
                    .getAsJsonObject().get("wind_mph").getAsDouble();
		    return wind;
		}
		else
		{
			Double wind = Double.NaN;
			return wind;
		}
	}

}
