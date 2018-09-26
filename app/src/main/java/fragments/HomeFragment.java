package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.ezcollect.raghu.ezcollect.MainActivity;
import com.ezcollect.raghu.ezcollect.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private ViewGroup mRootView;
    private TextView mServiceTv;
    private TextView mFromEt;
    private TextView mToEt;
    private PopupWindow popupWindow;
    private Activity mContext;
    private String selectedService = "";
    private int clickFrom;
    private ImageView mproceed;
    private String selectedFromLocation = "";
    private String selectedDestLocation = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = (ViewGroup) inflater.inflate(R.layout.home_layout, container, false);
            init();
        } else {
            ViewParent parent = mRootView.getParent();
            if (parent != null)
                ((ViewGroup) parent).removeView(mRootView);
        }
        return mRootView;
    }

    private void init() {

        mServiceTv = (TextView) mRootView.findViewById(R.id.service);
        mFromEt = (TextView) mRootView.findViewById(R.id.from);
        mToEt = (TextView) mRootView.findViewById(R.id.to);
        mproceed = (ImageView) mRootView.findViewById(R.id.proceed);

        mServiceTv.setOnClickListener(this);
        mFromEt.setOnClickListener(this);
        mToEt.setOnClickListener(this);
        mproceed.setOnClickListener(this);

    }


    public ArrayList<String> getFromLocationsList() {
        ArrayList<String> fromLocations = new ArrayList<>();

        if (selectedService.equalsIgnoreCase("TS1474(HYD-BGLR)")) {
            fromLocations.add("MiyaPur");
            fromLocations.add("Kukkatpally");
            fromLocations.add("Ameerpet");
            fromLocations.add("Panjagutta");
            fromLocations.add("M.G.B.S");
            fromLocations.add("LB Nagar");
        } else if (selectedService.equalsIgnoreCase("TS1414(MUM-CHENNAI)")) {
            fromLocations.add("Nerul Lp");
            fromLocations.add("Kharghar");
            fromLocations.add("Kalamboli");
        }
        return fromLocations;
    }

    public ArrayList<String> getDestinationsList() {
        ArrayList<String> fromLocations = new ArrayList<>();

        if (selectedService.equalsIgnoreCase("TS1474(HYD-BGLR)")) {
            fromLocations.add("Goraguntepaly");
            fromLocations.add("Marathahali Mh");
        } else if (selectedService.equalsIgnoreCase("TS1414(MUM-CHENNAI)")) {
            fromLocations.add("Chennai CMBT");
        }
        return fromLocations;
    }

    private ArrayList<String> getServicesList() {
        ArrayList<String> sortList = new ArrayList<String>();
        sortList.add("TS1474(HYD-BGLR)");
        sortList.add("TS1414(MUM-CHENNAI)");
        return sortList;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    private PopupWindow popUpWindow(ArrayList<String> list) {

        // initialize a pop up window type
        popupWindow = new PopupWindow(mContext);


        ListViewAdapter adapter = new ListViewAdapter(mContext, android.R.layout.simple_dropdown_item_1line,
                list);
        // the drop down list is a list view
        ListView listViewSort = new ListView(mContext);

        // set our adapter and pass our pop up window contents
        listViewSort.setAdapter(adapter);
        // set on item selected

        // some other visual settings for popup window
        popupWindow.setFocusable(true);
        popupWindow.setWidth(mServiceTv.getWidth());
        // popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.white));
        popupWindow.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        // set the listview as popup content
        popupWindow.setContentView(listViewSort);

        return popupWindow;
    }

    private void dismissPopup() {
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.service:
                clickFrom = 1;
                PopupWindow popUp = popUpWindow(getServicesList());
                popUp.showAsDropDown(v, 0, 0);
                break;
            case R.id.from:
                clickFrom = 2;
                if (selectedService.isEmpty()) {
                    Toast.makeText(mContext, "Please select service number", Toast.LENGTH_SHORT).show();
                } else {
                    PopupWindow fromPopUp = popUpWindow(getFromLocationsList());
                    fromPopUp.showAsDropDown(v, 0, 0);
                }

                break;
            case R.id.to:
                clickFrom = 3;
                if (selectedService.isEmpty()) {
                    Toast.makeText(mContext, "Please select service number", Toast.LENGTH_SHORT).show();
                } else if (mFromEt.getText().toString().trim().isEmpty()) {
                    Toast.makeText(mContext, "Please select From location", Toast.LENGTH_SHORT).show();
                } else {
                    PopupWindow toPopUp = popUpWindow(getDestinationsList());
                    toPopUp.showAsDropDown(v, 0, 0);
                }

                break;
            case R.id.proceed:
                PaymentFragment paymentFragment = new PaymentFragment();
                Bundle bundle = new Bundle();
                bundle.putString("ticketId", "TS/18/874");
                bundle.putString("from", selectedFromLocation);
                bundle.putString("to", selectedDestLocation);
                bundle.putString("serviceId", selectedService);
                bundle.putString("amount", "857.00");
                paymentFragment.setArguments(bundle);
                ((MainActivity) mContext).navigateToFragment(paymentFragment,true);
                break;
        }
    }


    public class ListViewAdapter extends ArrayAdapter<String> {

        private Activity activity;
        private ArrayList<String> products;
        private final String TAG = ListViewAdapter.class.getSimpleName();

        public ListViewAdapter(Activity activity, int resource, ArrayList<String> products) {
            super(activity, resource, products);
            this.activity = activity;
            this.products = products;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;

            // inflate layout from xml
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            // If holder not exist then locate all view from UI file.
            if (convertView == null) {
                // inflate UI from XML file
                convertView = inflater.inflate(R.layout.item_listview, parent, false);
                // get all UI view
                holder = new ViewHolder(convertView);
                // set tag for holder
                convertView.setTag(holder);
            } else {
                // if holder created, get tag from view
                holder = (ViewHolder) convertView.getTag();
            }

            holder.name.setText(products.get(position));

            // the view must be returned to our activity
            return convertView;
        }

        private class ViewHolder {
            private TextView name;

            public ViewHolder(View v) {
                name = (TextView) v.findViewById(R.id.name);
                name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String displayText = ((TextView) v).getText().toString();
                        switch (clickFrom) {
                            case 1:
                                mServiceTv.setText("Service No : " + displayText);
                                mFromEt.setText("");
                                mToEt.setText("");
                                selectedService = displayText;
                                mproceed.setImageResource(R.drawable.proceed_inactive);
                                mproceed.setEnabled(false);
                                break;
                            case 2:
                                selectedFromLocation = displayText;
                                mFromEt.setText("From : " + displayText);
                                break;
                            case 3:
                                selectedDestLocation = displayText;
                                mToEt.setText("To : " + displayText);
                                mproceed.setImageResource(R.drawable.proceed_active);
                                mproceed.setEnabled(true);
                                break;
                        }
                        dismissPopup();
                    }
                });
            }
        }

    }
}
