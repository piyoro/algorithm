package algorithm.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtil {

	public static String getDate2String(Date date, String format) {
		if (date != null) {
			DateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
		return null;
	}
}
