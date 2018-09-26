package fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.ezcollect.raghu.ezcollect.R;

public class PaymentFragment extends Fragment {
    private ViewGroup mRootView;
    private TextView mServiceNoTv;
    private TextView mFromTv;
    private TextView mDestinationTv;
    private TextView mTicketIdTv;
    private TextView mAmountTv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = (ViewGroup) inflater.inflate(R.layout.payment_layout, container, false);

            init();
        } else {
            ViewParent parent = mRootView.getParent();
            if (parent != null)
                ((ViewGroup) parent).removeView(mRootView);
        }
        return mRootView;
    }

    private void init() {
        Bundle arguments = getArguments();
        mServiceNoTv = (TextView) mRootView.findViewById(R.id.service);
        mFromTv = (TextView) mRootView.findViewById(R.id.from);
        mDestinationTv = (TextView) mRootView.findViewById(R.id.to);
        mTicketIdTv = (TextView) mRootView.findViewById(R.id.ticket);
        mAmountTv = (TextView) mRootView.findViewById(R.id.amount);

        if (arguments != null) {
            mServiceNoTv.setText(arguments.getString("serviceId"));
            mFromTv.setText(arguments.getString("from"));
            mDestinationTv.setText(arguments.getString("to"));
            mTicketIdTv.setText(arguments.getString("ticketId"));
            mAmountTv.setText(arguments.getString("amount"));
        }
    }

}
