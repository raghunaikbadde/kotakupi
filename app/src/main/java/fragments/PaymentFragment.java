package fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

import com.ezcollect.raghu.ezcollect.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class PaymentFragment extends Fragment {
    private ViewGroup mRootView;
    private TextView mServiceNoTv;
    private TextView mFromTv;
    private TextView mDestinationTv;
    private TextView mTicketIdTv;
    private TextView mAmountTv;
    private Bitmap bitmap;
    private ImageView mQrCode;

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
        mQrCode = (ImageView) mRootView.findViewById(R.id.qr_code);
        if (arguments != null) {
            mServiceNoTv.setText(arguments.getString("serviceId"));
            mFromTv.setText(arguments.getString("from"));
            mDestinationTv.setText(arguments.getString("to"));
            mTicketIdTv.setText(arguments.getString("ticketId"));
            mAmountTv.setText(arguments.getString("amount"));
        }


        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        String qr1 = "upi://pay?pa=nukkadshop@kotak&pn=NukkadShops Technologies India Pvt. Ltd&mc=1520&tid=A-AB136&tr=RA-AB136&tn=WeightReader Note&am=10&mam=5&cu=INR&url=https://www.nukkadshops.com/ku.php";

        String pa = "ma1@kotak";
        String pn = "TS RTC";
        String mc = "MC001";
        String tid = arguments.getString("ticketId");
        String tr = arguments.getString("ticketId");
        String tn = "Test Note";
        String am = arguments.getString("amount");
        String mam = "100";
        String cu = "INR";
        String url = "https://bit.ly/2rxPxAF";
        String name = "nukkadshop@kotak";
        String store = "NukkadShops Technologies India Pvt. Ltd";

       /* Uri.Builder builder = new Uri.Builder();
        builder.scheme("upi")
                .authority("pay")
                .appendQueryParameter("pa", name)
                .appendQueryParameter("pn",store )
                .appendQueryParameter("mc", "1520")
                .appendQueryParameter("tid", tid)
                .appendQueryParameter("tr", tr)
                .appendQueryParameter("tn", tn)
                .appendQueryParameter("am", am)
                .appendQueryParameter("mam", mam)
                .appendQueryParameter("cu", cu);
        String mUrl = ( builder.build().toString() + url);*/
        String mUrl = "upi://pay?pa=" + pa + "&pn=" + pn + "&mc=" + mc + "&tid=" + tid + "&tr=" + tr + "&tn=" + tn + "&am=" + am + "&mam=" + mam + "&cu=" + cu + "&url=" + url;
        Log.d("uuuuurrl", mUrl);


        if (!pa.isEmpty() && !pn.isEmpty()) {
            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(mUrl, BarcodeFormat.QR_CODE, 800, 800);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                bitmap = barcodeEncoder.createBitmap(bitMatrix);
                mQrCode.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        } else {

        }

    }

}
