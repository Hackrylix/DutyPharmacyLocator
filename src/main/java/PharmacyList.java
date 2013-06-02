package be.itsworking.dpl;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by samary on 5/26/13.
 * Class handling pharmacy List
 */
public class PharmacyList
{
    private static ArrayList<MyPharmacy> list = null;

    public static void initList(LatLng currentPosition)
    {
            setList(fetchList(currentPosition));
    }

    public static ArrayList<MyPharmacy> getList(LatLng currentPosition)
    {
        if(list==null)
        initList(currentPosition);
        return list;
    }

    public static ArrayList<MyPharmacy> getList()
    {
        return list;
    }

    public static void setList(ArrayList<MyPharmacy> list)
    {
        PharmacyList.list = list;
    }

    public static MyPharmacy getPharmacy(String pharmacyName)
    {
        for (MyPharmacy pharmacy : list)
        {
            if (pharmacy.getName().equalsIgnoreCase(pharmacyName))
                return pharmacy;
        }

        return null;
    }


    public static int getSize()
    {
        return list.size();
    }



    /**
     * @param position LatLng
     * @return String
     */
    public static String getXmlFromPosition(LatLng position) throws IOException
    {

            HttpClient client = new DefaultHttpClient();
            String getURL = "http://admin.ringring.be/apb/public/duty_xml.asp?lang=2&lat=" + position.latitude + "&lng=" + position.longitude;
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

        Log.e("getXmlFromPosition","response null ??");
        return null;

    }

    /**
     * @param position LatLng
     * @return ArrayList<MyPharmacy>
     */
    public static ArrayList<MyPharmacy> fetchList(LatLng position)
    {
        try
        {
            Log.i("fetch from", "loc : " + position.latitude + "-" + position.longitude);
            String xml = getXmlFromPosition(position);
            return PharmacyXMLParser.parse(xml);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
}
