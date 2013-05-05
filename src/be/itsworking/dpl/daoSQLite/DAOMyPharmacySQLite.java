/**
 * 
 */
package be.itsworking.dpl.daoSQLite;

import static be.itsworking.dpl.tools.Util.log;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import be.itsworking.dpl.dao.DAOMyPharmacy;
import be.itsworking.dpl.to.MyPharmacy;

/**
 * @author hackrylix
 * 
 */
public class DAOMyPharmacySQLite extends SQLiteOpenHelper implements DAOMyPharmacy
{
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "dpl";
	private static final String PHARMACIES_TABLE = "pharmacies";

	private static final String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + PHARMACIES_TABLE + " ( locationId INTEGER primary key autoincrement, locationLat DOUBLE, locationLng DOUBLE);";
	private static final String WHERE_CLAUSE = " locationLat=? AND locationLng=? ";
	private static final String WHERE_ID_CLAUSE = " locationId=? ";

	public DAOMyPharmacySQLite(Context context)
	{
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#deleteMyPharmacy(int)
	 */
	@Override
	public boolean deleteMyPharmacy(int id)
	{

		SQLiteDatabase wdb = getWritableDatabase();
		String[] arr = new String[1];
		arr[0] = "" + id;
		int res = wdb.delete(PHARMACIES_TABLE, WHERE_ID_CLAUSE, arr);
		wdb.close();
		if (res == 1)
			return true;
		else
			return false;
	}

	@Override
	public boolean exist(MyPharmacy tempLoc)
	{
		String[] arr = { tempLoc.getLocation().getLatitude() + "", "" + tempLoc.getLocation().getLongitude() };
		Cursor curs = getReadableDatabase().query(PHARMACIES_TABLE, null, WHERE_CLAUSE, arr, null, null, null);
		int c = curs.getCount();
		curs.close();
		close();
		if (c > 0)
			return true;
		else
			return false;

	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#insertMyPharmacy(be.itsworking.dpl.MyPharmacy)
	 */
	@Override
	public boolean insertMyPharmacy(MyPharmacy tempLoc)
	{
		if (exist(tempLoc))
		{
			log("already exist in db");
			return false;
		}
		else
		{

			ContentValues cv = new ContentValues();
			cv.put("locationLat", tempLoc.getLocation().getLatitude());
			cv.put("locationLng", tempLoc.getLocation().getLongitude());
			log("insert : " + tempLoc.toString());
			long ok = getWritableDatabase().insert(PHARMACIES_TABLE, null, cv);
			close();
			if (ok == -1)
			{
				log("error insert");
				return false;
			}
			else
			{
				log("insert (" + ok + ") ok");
				return true;
			}
		}
	}

	@Override
	public boolean insertMyPharmacyList(ArrayList<MyPharmacy> pharmacyList)
	{
		MyPharmacy tempLoc;
		boolean ok = true;

		for (int i = 0; i < pharmacyList.size(); i++)
		{
			tempLoc = pharmacyList.get(i);
			if (exist(tempLoc))
			{
				log("already exist in db");
			}
			else
			{

				ContentValues cv = new ContentValues();
				cv.put("locationLat", tempLoc.getLocation().getLatitude());
				cv.put("locationLng", tempLoc.getLocation().getLongitude());
				long res = getWritableDatabase().insert(PHARMACIES_TABLE, null, cv);

				if (res == -1)
				{
					log("error insert");
				}
				else
				{
					log("insert (" + res + ") ok");
				}
			}
		}
		close();
		return ok;

	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#updateMyPharmacy(be.itsworking.dpl.MyPharmacy)
	 */
	@Override
	public boolean updateMyPharmacy(MyPharmacy myLoc)
	{
		if (!exist(myLoc))
		{
			log("update " + myLoc.getNom() + " dont existe ");
			return false;

		}
		ContentValues cv = new ContentValues();

		cv.put("locationLat", myLoc.getLocation().getLatitude());
		cv.put("locationLng", myLoc.getLocation().getLongitude());
		String[] arr = new String[1];
		arr[0] = "" + myLoc.getNom();
		int ok = getWritableDatabase().update(PHARMACIES_TABLE, cv, WHERE_ID_CLAUSE, arr);
		close();
		if (ok == -1)
		{
			log("error update");
			return false;
		}
		else
		{
			log("update (" + ok + ") ok");
			return true;
		}

	}

	/**
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#loadMyPharmacy(int)
	 */
	@Override
	public MyPharmacy loadMyPharmacy(int id)
	{
		String[] arr = { id+""};
		Cursor curs = getReadableDatabase().query(PHARMACIES_TABLE, null, WHERE_ID_CLAUSE, arr, null, null, null);

		if(curs.getCount()<1)
			log("count <1 !");
		double lat = curs.getDouble(1);
		double lng = curs.getDouble(2);
		curs.close();
		close();
		return new MyPharmacy(id, lat, lng, "DB");

	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#selectAllPharmacies()
	 */
	@Override
	public ArrayList<MyPharmacy> selectAllPharmacies()
	{

		ArrayList<MyPharmacy> locationsList = new ArrayList<MyPharmacy>();

		Cursor curs = getWritableDatabase().query(PHARMACIES_TABLE, null, null, null, null, null, null);
		if (curs == null)
			log("curs == null while loading list");
		while (curs.moveToNext())
		{
			int id = curs.getInt(0);
			double lat = curs.getDouble(1);
			double lng = curs.getDouble(2);
			locationsList.add(new MyPharmacy(id, lat, lng, "DB"));

		}
		curs.close();
		close();
		return locationsList;
	}

	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL(CREATE_LOCATIONS_TABLE);
		db.execSQL("INSERT INTO  " + PHARMACIES_TABLE + " VALUES(NULL,50,4);");
		db.execSQL("INSERT INTO  " + PHARMACIES_TABLE + " VALUES(NULL,51,3.5);");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int countAll()
	{
		Cursor curs = getReadableDatabase().query(PHARMACIES_TABLE, null, null, null, null, null, null);
		int count = curs.getCount();
		curs.close();
		close();
		return count;
	}

}
