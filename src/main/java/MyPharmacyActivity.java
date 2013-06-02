package be.itsworking.dpl;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.BufferType;

/**
 * @author samary
 */
public class MyPharmacyActivity extends Activity
{
    private MyPharmacy currentPharmacy;

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent sender = getIntent();
        Bundle extras = sender.getExtras();
        if (extras != null)
        {
            currentPharmacy = extras.getParcelable("pharmacy");

            setContentView(R.layout.activity_my_pharmacy);

            TextView nomText = (TextView) findViewById(R.id.pharmacyNom);

            String nom = currentPharmacy.getName();
            String pharmacist = currentPharmacy.getPharmacist();
            String totalname = nom;
            if (!nom.equalsIgnoreCase(pharmacist))
                totalname += "\r\n" + pharmacist;

            nomText.setText(totalname, BufferType.EDITABLE);

            TextView hoursText = (TextView) findViewById(R.id.pharmacyHours);
            hoursText.setText(currentPharmacy.getHours().replaceAll("<br/>", "\r\n"), BufferType.EDITABLE);

            TextView telText = (TextView) findViewById(R.id.pharmacyPhone);
            telText.setText(currentPharmacy.getTel(), BufferType.EDITABLE);

            TextView addressText = (TextView) findViewById(R.id.pharmacyAdress);
            addressText.setText(currentPharmacy.getAdresse() + " " + currentPharmacy.getCp() + " " + currentPharmacy.getCity(), BufferType.EDITABLE);

            String url = currentPharmacy.getUrl();

            if (url.isEmpty())
            {
                LinearLayout llsite = (LinearLayout) findViewById(R.id.llsite);
                llsite.setVisibility(LinearLayout.GONE);
            }
            else
            {
                TextView siteText = (TextView) findViewById(R.id.pharmacySite);
                siteText.setText(url, BufferType.EDITABLE);

                ImageButton siteButton = (ImageButton) findViewById(R.id.showSiteButton);
                siteButton.setOnClickListener(new OnClickListener()
                {

                    @Override
                    public void onClick(View v)
                    {
                        Intent httpIntent = new Intent(Intent.ACTION_VIEW);
                        httpIntent.setData(Uri.parse(currentPharmacy.getUrl()));
                        startActivity(httpIntent);
                    }
                });
            }

            ImageButton phoneButton = (ImageButton) findViewById(R.id.callPhoneButton);
            ImageButton addressButton = (ImageButton) findViewById(R.id.showAdressButton);

            phoneButton.setOnClickListener(new OnClickListener()
            {

                /*
                 * (non-Javadoc)
                 *
                 * @see
                 * android.view.View.OnClickListener#onClick(android.view.View)
                 */
                @Override
                public void onClick(View v)
                {
                    Intent call = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + currentPharmacy.getTel()));
                    startActivity(call);
                }
            });

            addressButton.setOnClickListener(new OnClickListener()
            {

                /*
                 * (non-Javadoc)
                 *
                 * @see
                 * android.view.View.OnClickListener#onClick(android.view.View)
                 */
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
