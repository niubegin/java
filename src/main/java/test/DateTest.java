package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateTest {

    /**
     * https://www.cnblogs.com/peida/archive/2013/05/31/3070790.html
     */
    public static void main(String[] args) {
        Long date = new Long(1575103860025L);//2019-11-30 16:51:00.0250
        Long ct = new Long(1575165347042L);//2019-12-01 09:55:47.0420
        Calendar inputDate = Calendar.getInstance();
        inputDate.setTimeInMillis(date);
        //inputDate.setTimeInMillis(1575103860025L);

        Calendar completionTime = Calendar.getInstance();
        completionTime.setTimeInMillis(ct);
        //completionTime.setTimeInMillis(1575165347042L);

        if (inputDate.get(Calendar.YEAR) != completionTime.get(Calendar.YEAR)
            || inputDate.get(Calendar.MONTH) != completionTime.get(Calendar.MONTH)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            try {
                System.out.println(sdf.format(completionTime.getTimeInMillis()).substring(0, 6) + "01");
                System.out
                    .println(sdf.parse(sdf.format(completionTime.getTimeInMillis()).substring(0, 6) + "01").getTime());
            } catch (ParseException e) {
                System.out.println(e);
            }
        } else {
            System.out.println("e");
        }
    }
}
