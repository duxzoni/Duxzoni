package vivencia.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateManager {

	private static final String DD_MM_YYYY = "dd/MM/yyyy";

	public static Date today() {
		return new Date();
	}
	
	public static Date ddmmaaaaSeparated(String data){
		try {
			return new SimpleDateFormat(DD_MM_YYYY).parse(data);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String ddmmaaaaSeparated(Date data){
		return new SimpleDateFormat(DD_MM_YYYY).format(data);
	}

	public static Date addDays(Date dataFim, int i) {
		if (dataFim == null ) return null;
		Calendar cal = Calendar.getInstance();
        cal.setTime(dataFim);
        cal.add(Calendar.DATE, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		return calendar.getTime();
	}

	public static String ddmmaaaahhMMssSeparated(Date data) {
		return new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(data);
	}
	
	

}
