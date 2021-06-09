package dr.com.coinscreen;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Locale;

public class Plain {
    public String toPlainString(String num) {
//        String str = String.valueOf(num);
//        DecimalFormat df = new DecimalFormat();
        /*if (num.contains("E")){
            return new BigDecimal(num).toPlainString();
        }else{
            return new BigDecimal(num).toString();
        }*/
        if (num.contains("-")) {
            return new BigDecimal(num).toPlainString();
        } else if(num.contains("E")) {
            //            DecimalFormat df = new DecimalFormat();
            double d = Double.parseDouble(num);
            return String.format(Locale.KOREA, "%1$,.0f", d);
        }else{
            return new BigDecimal(num).toString();
        }
    }

    /*public String toDoubleFormat(Double num) {
        DecimalFormat df = null;

        if (num >= 100 && num <=999.9){
            df = new DecimalFormat("000.0");
        }else if(num >= 10 && num <= 99.99){
            df = new DecimalFormat("00.00");
        }else if(num >= 1 && num <= 9.9999){
            df = new DecimalFormat("0.000");
        }else if(num < 1){
            df = new DecimalFormat("0.0000");
        }else{
            df = new DecimalFormat("###.####");
        }
        return  df.format(num);
    }*/
}
