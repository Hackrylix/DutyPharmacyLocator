package be.itsworking.dpl;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

public class MyPharmacySync
{

	public static String getFromPosition(LatLng position)
	{
		try
		{
			HttpClient client = new DefaultHttpClient();
			String getURL = "http://admin.ringring.be/apb/public/duty_xml.asp?lang=2&lat="+position.latitude+"&lng="+position.longitude;
			HttpGet get = new HttpGet(getURL);
			HttpResponse responseGet = client.execute(get);
			HttpEntity resEntityGet = responseGet.getEntity();
			if (resEntityGet != null)
			{
				// do something with the response
				String response = EntityUtils.toString(resEntityGet);

				Log.i("GET RESPONSE", response);
				return response;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;

	}
}
