package be.itsworking.dpl;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * @author samary
 */
public class MyPharmacy implements Parcelable, Comparable<MyPharmacy>
{
    private int id;
    private String name;
    private String pharmacist;
    private String adresse;
    private String cp;
    private String city;

    private String tel;
    private String url;

    private LatLng latlng;
    private double lat;
    private double lng;
    private double dist;

    private String hours;

    /**
     * This field is needed for Android to be able to create new objects,
     * individually or as arrays.
     * <p/>
     * This also means that you can use use the default constructor to create
     * the object and use another method to hyrdate it as necessary.
     * <p/>
     * I just find it easier to use the constructor. It makes sense for the way
     * my brain thinks ;-)
     */
    @SuppressWarnings("rawtypes")
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator()
    {
        public MyPharmacy createFromParcel(Parcel in)
        {
            return new MyPharmacy(in);
        }

        public MyPharmacy[] newArray(int size)
        {
            return new MyPharmacy[size];
        }
    };

    /**
     * @param id
     * @param nom
     * @param pharmacist
     * @param address
     * @param cp
     * @param city
     * @param tel
     * @param url
     * @param lat
     * @param lng
     * @param hours
     */
    public MyPharmacy(int id, String nom, String pharmacist, String address, String cp, String city, String tel, String url, double lat, double lng, double dist, String hours)
    {
        this.id = id;
        this.name = nom;
        this.pharmacist = pharmacist;
        this.adresse = address;
        this.cp = cp;
        this.city = city;
        this.tel = tel;
        this.url = url;

        this.lat = lat;
        this.lng = lng;
        this.latlng = new LatLng(lat, lng);
        this.dist = dist;

        this.hours = hours;

    }

    /**
     * Constructor to use when re-constructing object from a parcel
     *
     * @param in a parcel from which to read this object
     */
    public MyPharmacy(Parcel in)
    {
        readFromParcel(in);
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.Parcelable#writeToParcel(android.os.Parcel, int)
     */
    @Override
    public void writeToParcel(Parcel dest, int flags)
    {

        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(pharmacist);

        dest.writeString(adresse);
        dest.writeString(cp);
        dest.writeString(city);

        dest.writeString(tel);
        dest.writeString(url);

        dest.writeDouble(lat);
        dest.writeDouble(lng);

        dest.writeDouble(dist);

        dest.writeString(hours);
    }

    /**
     * Called from the constructor to create this object from a parcel.
     *
     * @param in parcel from which to re-create object
     */
    private void readFromParcel(Parcel in)
    {

        id = in.readInt();
        name = in.readString();
        pharmacist = in.readString();

        adresse = in.readString();
        cp = in.readString();
        city = in.readString();

        tel = in.readString();
        url = in.readString();

        lat = in.readDouble();
        lng = in.readDouble();
        latlng = new LatLng(lat, lng);

        dist = in.readDouble();

        hours = in.readString();
    }

    /**
     * @return the dist
     */
    public double getDist()
    {
        return dist;
    }

    /**
     * @param dist the dist to set
     */
    public void setDist(double dist)
    {
        this.dist = dist;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.os.Parcelable#describeContents()
     */
    @Override
    public int describeContents()
    {
        return 0;
    }

    /**
     * @return the id
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return the name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @return the pharmacist
     */
    public String getPharmacist()
    {
        return pharmacist;
    }

    /**
     * @return the adresse
     */
    public String getAdresse()
    {
        return adresse;
    }

    /**
     * @return the cp
     */
    public String getCp()
    {
        return cp;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @return the tel
     */
    public String getTel()
    {
        return tel;
    }

    /**
     * @return the url
     */
    public String getUrl()
    {
        return url;
    }

    /**
     * @return the latlng
     */
    public LatLng getLatlng()
    {
        return latlng;
    }

    /**
     * @return the lat
     */
    public double getLat()
    {
        return lat;
    }

    /**
     * @return the lng
     */
    public double getLng()
    {
        return lng;
    }

    /**
     * @return the hours
     */
    public String getHours()
    {
        return hours;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @param pharmacist the pharmacist to set
     */
    public void setPharmacist(String pharmacist)
    {
        this.pharmacist = pharmacist;
    }

    /**
     * @param adresse the adresse to set
     */
    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    /**
     * @param cp the cp to set
     */
    public void setCp(String cp)
    {
        this.cp = cp;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city)
    {
        this.city = city;
    }

    /**
     * @param tel the tel to set
     */
    public void setTel(String tel)
    {
        this.tel = tel;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url)
    {
        this.url = url;
    }

    /**
     * @param latlng the latlng to set
     */
    public void setLatlng(LatLng latlng)
    {
        this.latlng = latlng;
    }

    /**
     * @param lat the lat to set
     */
    public void setLat(double lat)
    {
        this.lat = lat;
    }

    /**
     * @param lng the lng to set
     */
    public void setLng(double lng)
    {
        this.lng = lng;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(String hours)
    {
        this.hours = hours;
    }

    @Override
    public int compareTo(MyPharmacy another)
    {
        return new Double(dist).compareTo(another.getDist());
    }

}
