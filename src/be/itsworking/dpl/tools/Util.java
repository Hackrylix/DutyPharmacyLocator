package be.itsworking.dpl.tools;

import android.content.Context;
import android.widget.Toast;

public class Util
{
	public static void showMessage(Context mContext, String string)
	{
		Toast.makeText(mContext, string, Toast.LENGTH_SHORT).show();
	}
	
	public static void showMessageLong(Context mContext, String string)
	{
		Toast.makeText(mContext, string, Toast.LENGTH_LONG).show();
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

}
