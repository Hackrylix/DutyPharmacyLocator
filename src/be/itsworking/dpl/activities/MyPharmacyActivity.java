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
import be.itsworking.dpl.tools.Util;

public class MyPharmacyActivity extends Activity
{
	private MyPharmacy currentPharmacy;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent sender = getIntent();
		if (sender.getExtras() != null)
		{
			try
			{
				int pharmacyId = 0;
				pharmacyId = Integer.parseInt(sender.getExtras().getString("PHARMACY_ID"));
				if (pharmacyId != 0)
				{
					currentPharmacy = new MyPharmacyManager(this, false).getPharmacy(pharmacyId);
					setContentView(R.layout.pharmacydetails);
				}

			}
			catch (NumberFormatException ex)
			{
				Util.log("ERROR !!! no parse int for " + sender.getExtras().getString("PHARMACY_ID") + " !!!");
			}
		}

		TextView nomText = (TextView) findViewById(R.id.pharmacyNom);
		nomText.setText(currentPharmacy.getNom(), BufferType.EDITABLE);

		TextView telText = (TextView) findViewById(R.id.pharmacyPhone);
		telText.setText(currentPharmacy.getTel(), BufferType.EDITABLE);

		TextView adressText = (TextView) findViewById(R.id.pharmacyAdress);
		adressText.setText(currentPharmacy.getAdresse(), BufferType.EDITABLE);

		Button phoneButton = (Button) findViewById(R.id.callPhoneButton);
		Button adresseButton = (Button) findViewById(R.id.showAdressButton);
		
		phoneButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currentPharmacy.getTel()));
				startActivity(call);
			}
		});

		
		adresseButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view)
			{
				String str = "geo:0,0?q=" + currentPharmacy.getAdresse();

				Uri geoUri = Uri.parse(str);
				Intent mapCall = new Intent(Intent.ACTION_VIEW, geoUri);
				startActivity(mapCall);
			}
		});

	}

	@Override
	protected void onResume()
	{
		super.onResume();
	}

}
