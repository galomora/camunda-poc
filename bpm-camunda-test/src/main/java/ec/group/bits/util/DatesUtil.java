package ec.group.bits.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.enterprise.inject.Model;

@Model
public class DatesUtil {
	public String getDate () {
//		TODO obtener formato
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		return format.format(new Date ());
	}
	
	public Date getDueDate (Integer days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, days);
		return calendar.getTime();
	}
}
