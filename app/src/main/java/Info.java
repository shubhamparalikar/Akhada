import com.danielstone.materialaboutlibrary.MaterialAboutActivity;
import com.danielstone.materialaboutlibrary.model.MaterialAboutList;
import com.example.shubham.planexakhada.R;

/**
 * Created by shubham on 1/3/2017.
 */

public class Info extends MaterialAboutActivity {

    @Override
    protected MaterialAboutList getMaterialAboutList() {
        return new MaterialAboutList.Builder()
                .build();
    }

    @Override
    protected CharSequence getActivityTitle() {
        return getString(R.string.mal_title_about);
    }
}
