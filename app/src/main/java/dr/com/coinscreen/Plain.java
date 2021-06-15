package dr.com.coinscreen;

import android.util.Log;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Plain {
    private static final String TAG = "Plain";
    public String toPlainString(String num) {
        if (num.contains("-")) {
            if (num.length() > 8){
                double d = Double.parseDouble(num);
//                DecimalFormat df = new DecimalFormat("#,##0.00");
                return String.format(Locale.KOREA, "%.8f", d);
//                Log.i(TAG, "toPlainString: ddddddddddddd " + df.format(d));
//                return df.format(d);
            }else{
                return new BigDecimal(num).toPlainString();
            }
        } else if(num.contains("E")) {
            double d = Double.parseDouble(num);
            if (num.length() > 10){
                return String.format(Locale.KOREA, "%.8f", d);
            }else{
                return String.format(Locale.KOREA, "%1$,.0f", d);
            }
        }else{
            return new BigDecimal(num).toString();
        }
    }

    public String roundDouble(double num){
        return String.format(Locale.KOREA, "%.3f", num);
    }

    public String toTimeStamp(long num){
        Date toTimeStamp = new Date(num);
        SimpleDateFormat datef = new SimpleDateFormat("yyyy-MM-dd hh:mm", Locale.getDefault());
        return datef.format(toTimeStamp) ;
    }

    public String toFluctuationRate(double nowClosingPrice, double preClosingPrice){
        double dif_price = nowClosingPrice - preClosingPrice;
        double rate = (dif_price / preClosingPrice) * 100.0;
        String result;
        if (rate > 0) {
            result = "+" + String.format(Locale.KOREA, "%.2f", rate);
        }else {
            result = String.format(Locale.KOREA, "%.2f", rate);
        }
        return result;
    }

    public String subPrice(double nowPrice, double yesPrice, String field){
        String now = String.valueOf(nowPrice - yesPrice);
        return toPlainString(now);
    }
}
