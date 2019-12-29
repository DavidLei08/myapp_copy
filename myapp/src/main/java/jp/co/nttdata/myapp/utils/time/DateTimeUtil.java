package jp.co.nttdata.myapp.utils.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtil {

	/**
	 *  获取当前时间yyyy-MM-dd HH:mm:ss时间格式的字符串
	 * @return
	 */
	public static String getDateTimeStr() {

		String timeString;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		timeString = LocalDateTime.now().format(formatter);
		return timeString;
	}

	/**
	 * 获取当前yyyy-MM-dd日期格式的字符串
	 * 
	 * @return
	 */
	public static String getDateStr() {

		String dateString;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		dateString = LocalDateTime.now().format(formatter);
		return dateString;
	}
}
