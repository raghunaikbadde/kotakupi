package fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.ezcollect.raghu.ezcollect.R;

import java.util.ArrayList;

import pojo.BookHistory;

public class BookingHistoryFragment extends Fragment implements View.OnClickListener {

    private ViewGroup mRootView;
    private ArrayList<BookHistory> historyList = new ArrayList<>();
    private RecyclerView mHistoryRecycler;
    private Activity mContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = (ViewGroup) inflater.inflate(R.layout.history_layout, container, false);
            init();
        } else {
            ViewParent parent = mRootView.getParent();
            if (parent != null)
                ((ViewGroup) parent).removeView(mRootView);
        }
        return mRootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    private void init() {

        BookHistory bookHistory = new BookHistory();
        bookHistory.setBoarding("Boarding at Ameerpet");
        bookHistory.setDate("28");
        bookHistory.setMonth("SEP");
        bookHistory.setYear("2018");
        bookHistory.setRouteMap("Hyderabad - Bengaluru");
        historyList.add(bookHistory);
        historyList.add(bookHistory);
        historyList.add(bookHistory);
        historyList.add(bookHistory);
        mHistoryRecycler = (RecyclerView) mRootView.findViewById(R.id.history_list);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        mHistoryRecycler.setLayoutManager(mLayoutManager);
        HistoryAdapter adapter = new HistoryAdapter();
        mHistoryRecycler.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

    }

    class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_item, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            if (historyList != null && historyList.size() > 0 && historyList.size() > position) {
                BookHistory billing = historyList.get(position);
                holder.mDateTv.setText(billing.getDate());
                holder.mMonthTv.setText(billing.getMonth());
                holder.mYear.setText(billing.getYear());
                holder.mBoardigTv.setText(billing.getBoarding());
                holder.mRouteTv.setText(billing.getRouteMap());
            }
        }


        @Override
        public int getItemCount() {
            if (historyList == null)
                return 0;
            else {
                return historyList.size();
            }
        }


        class MyViewHolder extends RecyclerView.ViewHolder {
            private TextView mYear;
            public TextView mRouteTv;
            public TextView mBoardigTv;

            private TextView mDateTv;
            private TextView mMonthTv;

            public MyViewHolder(View convertView) {
                super(convertView);
                mDateTv = (TextView) convertView.findViewById(R.id.date);
                mMonthTv = (TextView) convertView.findViewById(R.id.month);
                mYear = (TextView) convertView.findViewById(R.id.year);
                mRouteTv = (TextView) convertView.findViewById(R.id.route);
                mBoardigTv = (TextView) convertView.findViewById(R.id.boarding);
                convertView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int adapterPosition = getAdapterPosition();
                        /**Index out of bounds exception raised
                         * to avoid this situation to check
                         *
                         * adapter position is always less than search result size.
                         *
                         */

                    }
                });
            }
        }
    }
}
