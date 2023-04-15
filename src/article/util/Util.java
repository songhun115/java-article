package article.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {
	public static String getRegDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date time = new Date();
		String time2 = format.format(time);
		return format.format(time);
	}	
}



