package be.itsworking.dpl.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import be.itsworking.dpl.R;
import be.itsworking.dpl.dao.DAOMyPharmacyXML;
import be.itsworking.dpl.to.MyPharmacy;

public class MyPharmacyActivity extends Activity
{
	private MyPharmacy currentPharmacy;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		Intent sender = getIntent();
		Bundle extras = sender.getExtras();
		if (extras != null)
		{
			int pharmacyId = 0;

			if (extras.containsKey("PHARMACY_ID"))
			{
				pharmacyId = extras.getInt("PHARMACY_ID");
				if (pharmacyId > 0)
					currentPharmacy = new DAOMyPharmacyXML().loadMyPharmacy(pharmacyId);
			}
			else if (extras.containsKey("PHARMACY_NAME"))
			{
				String pharmacyName = sender.getExtras().getString("PHARMACY_NAME");
				if (pharmacyName != null)
					currentPharmacy = new DAOMyPharmacyXML().loadMyPharmacy(pharmacyName);
			}
			setContentView(R.layout.pharmacydetails);
			TextView nomText = (TextView) findViewById(R.id.pharmacyNom);
			nomText.setText(currentPharmacy.getName(), BufferType.EDITABLE);

			TextView telText = (TextView) findViewById(R.id.pharmacyPhone);
			telText.setText(currentPharmacy.getTel(), BufferType.EDITABLE);

			TextView adressText = (TextView) findViewById(R.id.pharmacyAdress);
			adressText.setText(currentPharmacy.getAdresse(), BufferType.EDITABLE);

			ImageButton phoneButton = (ImageButton) findViewById(R.id.callPhoneButton);
			ImageButton adresseButton = (ImageButton) findViewById(R.id.showAdressButton);

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

	}

}
