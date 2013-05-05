package be.itsworking.dpl.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import be.itsworking.dpl.MyPharmacyManager;
import be.itsworking.dpl.R;
import be.itsworking.dpl.to.MyPharmacy;

public class MyPharmacyActivity extends Activity
{
	private MyPharmacyManager myPharmacyManager;
	private MyPharmacy currentPharmacy;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		refresh();

		TextView nomText = (TextView) findViewById(R.id.pharmacyNom);
		nomText.setText(currentPharmacy.getNom(),BufferType.EDITABLE);
		
		TextView telText = (TextView) findViewById(R.id.pharmacyPhone);
		telText.setText(currentPharmacy.getTel(),BufferType.EDITABLE);
		
		TextView adressText = (TextView) findViewById(R.id.pharmacyAdress);
		adressText.setText(currentPharmacy.getAdresse(),BufferType.EDITABLE);
		
		Button phoneButton = (Button) findViewById(R.id.callPhoneButton);
		
		phoneButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+currentPharmacy.getTel()));
				
				startActivity(call);  				
			}
		});
		
		Button adresseButton = (Button) findViewById(R.id.showAdressButton);

		adresseButton.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				String str = "geo:0,0?q="+currentPharmacy.getAdresse();
				
				Uri geoUri = Uri.parse(str); 
				Intent mapCall = new Intent(Intent.ACTION_VIEW, geoUri);  
				startActivity(mapCall); 				
			}
		});
		
		
	}

	private void refresh()
	{
		String pharmacyId="";
		Intent sender=getIntent();
		if(sender.getExtras()!=null)
			pharmacyId=sender.getExtras().getString("PHARMACY_ID");
		setContentView(R.layout.pharmacydetails);
		myPharmacyManager=new MyPharmacyManager(this, false);
		log("id : "+pharmacyId);
		if(pharmacyId!="")
			currentPharmacy=myPharmacyManager.getPharmacy(Integer.parseInt(pharmacyId));
		else
		{
			currentPharmacy=myPharmacyManager.getPharmacy(1);
			log("no parse of "+pharmacyId+" -> fallback");
		}
		
		
	}

	@Override
	protected void onResume()
	{
		
		super.onResume();
	}

	private void log(String string)
	{
		System.out.println(string);

	}

}
