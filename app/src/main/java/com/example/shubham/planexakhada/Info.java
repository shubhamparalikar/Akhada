package com.example.shubham.planexakhada;

import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutActionItem;
import com.danielstone.materialaboutlibrary.model.MaterialAboutCard;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.danielstone.materialaboutlibrary.model.MaterialAboutTitleItem;

/**
 * Created by shubham on 1/3/2017.
 */

public class Info extends MaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList() {
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        MaterialAboutCard.Builder expertDetails = new MaterialAboutCard.Builder();
        expertDetails.addItem(new MaterialAboutActionItem.Builder()
                .text("Our Expert:\nRam Shankar Mitra")
                .icon(R.mipmap.experticon)
                .subText("Fitness Trainer & Gym Owner\nAkhada (Gym/Physical fitness center)")
                .build());

        MaterialAboutCard.Builder gymaddress = new MaterialAboutCard.Builder();
        gymaddress.addItem(new MaterialAboutActionItem.Builder()
                .text("Jai Akhada")
                .icon(R.mipmap.addressicon)
                .subText("G-2/220,Krishna Tower,\nGulmohar colony,\nBhopal (Madhya Pradesh)\nIndia")
                .build());
        MaterialAboutCard.Builder contact = new MaterialAboutCard.Builder();
        contact.addItem(new MaterialAboutActionItem.Builder()
                .text("Contact us for any queries:")
                .icon(R.mipmap.conticon)
                .subText("Fitness queries\nemail: xyz@gmail.com\nGym related queries:\nMob: ----------")
                .build());
        MaterialAboutCard.Builder dev = new MaterialAboutCard.Builder();
        dev.addItem(new MaterialAboutActionItem.Builder()
                .text("App developed and maintained by:")
                .icon(R.mipmap.devicon)
                .subText("Shubham Paralikar\nshubham.paralikar@gmail.com\nApp Version:\n1.0.0")
                .build());


        return new MaterialAboutList.Builder()
                .addCard(expertDetails.build())
                .addCard(gymaddress.build())
                .addCard(contact.build())
                .addCard(dev.build())
                .build();
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }
}
