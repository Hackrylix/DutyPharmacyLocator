package be.itsworking.dpl.tools;

import android.content.Context;
import android.widget.Toast;

public class Util
{
	private String sdCardState;
	private boolean isSDAvailaible = false;
	private boolean isSDWritable = false;

	public static void showMessage(Context mContext, String string)
	{

		Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show();

	}

	public static void log(String string)
	{
		System.out.println("log : " + string);

	}

	public static void log(String string, Context ctx)
	{
		if (ctx != null)
			showMessage(ctx, string);
		log(string);

	}

	public boolean isSDAvailaible()
	{
		return isSDAvailaible;
	}

	public void setSDAvailaible(boolean isSDAvailaible)
	{
		this.isSDAvailaible = isSDAvailaible;
	}

	/**
	 * @return the isSDWritable
	 */
	public boolean isSDWritable()
	{
		return isSDWritable;
	}

	/**
	 * @param isSDWritable the isSDWritable to set
	 */
	public void setSDWritable(boolean isSDWritable)
	{
		this.isSDWritable = isSDWritable;
	}

	/**
	 * @return the sdCardState
	 */
	public String getSdCardState()
	{
		return sdCardState;
	}

	/**
	 * @param sdCardState the sdCardState to set
	 */
	public void setSdCardState(String sdCardState)
	{
		this.sdCardState = sdCardState;
	}

	// private void loadAllPositions(Activity act)
	// {
	//
	// SharedPreferences settings = act.getSharedPreferences("mt", 0);
	// int nbLoc = settings.getInt("nbLoc", 0);
	// try
	// {
	//
	// BufferedReader reader = new BufferedReader(new FileReader("loclist"));
	// for (int i = 0; i < nbLoc; i++)
	// {
	// String line = reader.readLine();
	// locationsList.add(new MyLocation(line));
	// }
	//
	// reader.close();
	//
	// }
	// catch (Exception e)
	// {
	// Util.showMessage(act.getApplicationContext(), e.getMessage());
	// e.printStackTrace();
	// }
	//
	// }
	//
	// private void saveAllPositions(Activity act)
	// {
	//
	// try
	// {
	// FileOutputStream fos = act.openFileOutput("loclist",
	// Context.MODE_PRIVATE);
	// for (int i = 0; i < locationsList.size(); i++)
	// {
	// MyLocation tempLoc = locationsList.get(i);
	// fos.write(tempLoc.getData().getBytes());
	// Util.showMessage(act.getApplicationContext(), "Position " +
	// tempLoc.toString() + " saved");
	// }
	// fos.close();
	// SharedPreferences settings = act.getSharedPreferences("mt", 0);
	// settings.edit().putInt("nbLoc", locationsList.size()).commit();
	//
	// }
	// catch (Exception e)
	// {
	// Util.showMessage(act.getApplicationContext(), e.getMessage());
	// e.printStackTrace();
	// }
	//
	// }
	//
	// private boolean loadAllPositionsSD(Activity act)
	// {
	// if (!isSDAvailaible)
	// {
	// Util.showMessage(act.getApplicationContext(), "Storage not avalaible");
	// return false;
	// }
	// File file = new File(act.getExternalFilesDir(null), "loclist");
	//
	// try
	// {
	//
	// BufferedReader reader = new BufferedReader(new FileReader(file));
	// String line = reader.readLine();
	// do
	// {
	// Util.showMessage(act.getApplicationContext(), line);
	// currentLocation = new MyLocation(line);
	// locationsList.add(currentLocation);
	// line = reader.readLine();
	//
	// } while (line != null);
	//
	// reader.close();
	// return true;
	// }
	// catch (Exception e)
	// {
	// Util.showMessage(act.getApplicationContext(), e.getMessage());
	// e.printStackTrace();
	// return false;
	// }
	//
	// }
	//
	// private boolean saveAllPositionsSD(Activity act)
	// {
	// if (!isSDWritable)
	// {
	// Util.showMessage(act.getApplicationContext(), "Storage not writable");
	// return false;
	// }
	//
	// try
	// {
	// File file = new File(act.getExternalFilesDir(null), "loclist");
	//
	// FileOutputStream fos = new FileOutputStream(file);
	// for (int i = 0; i < locationsList.size(); i++)
	// {
	// MyLocation tempLoc = locationsList.get(i);
	// fos.write(tempLoc.getData().getBytes());
	// }
	// fos.close();
	// SharedPreferences settings = act.getSharedPreferences("mt", 0);
	// settings.edit().putInt("nbLoc", locationsList.size()).commit();
	// Util.showMessage(act.getApplicationContext(), "Positions Saved");
	// return true;
	// }
	// catch (Exception e)
	// {
	// Util.showMessage(act.getApplicationContext(), e.getMessage());
	// e.printStackTrace();
	// return false;
	// }
	//
	// }
	//
	// private void checkSD()
	// {
	// sdCardState = Environment.getExternalStorageState();
	// if (Environment.MEDIA_MOUNTED.equals(sdCardState))
	// {
	// // We can read and write the media
	// isSDAvailaible = isSDWritable = true;
	// }
	// else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(sdCardState))
	// {
	// // We can only read the media
	// isSDAvailaible = true;
	// isSDWritable = false;
	// }
	// else
	// {
	// // Something else is wrong. It may be one of many other states,
	// // but all we need to know is we can neither read nor write
	// isSDAvailaible = isSDWritable = false;
	// }
	//
	// }
}
