package cn.erp.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 2018-12-18
 * @author wangshu
 *
 */
public class StringUtils {

	/**
	  *   	删除下划线，并把下划线后面的字母变为大写
	  * @param s 传入需要修改的字符串
	  * @return 返回修改后的字符串
	  */
	public static String removeUnderlineAndUpperCase(String s) {
		String[] split = s.split("_");
		if(split == null || split.length == 0) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		sb.append(split[0]);
		for (int i =1;i<split.length;++i) {
			if(split[i] == null || "".equals(split[i])) {
				continue;
			}
			char c = split[i].charAt(0);
			String str = c + "";
			String replaceFirst = split[i];
			if(c >= 97 && c <= 97+26) {
				replaceFirst = split[i].replaceFirst(str, str.toUpperCase());
			}
			sb.append(replaceFirst);
		}
		return sb.toString();
	}
	
	 public static List<String> getMonthBetween(String minDate, String maxDate) throws ParseException {
		    ArrayList<String> result = new ArrayList<String>();
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");//格式化为年月
		 
		    Calendar min = Calendar.getInstance();
		    Calendar max = Calendar.getInstance();
		 
		    min.setTime(sdf.parse(minDate));
		    min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);
		 
		    max.setTime(sdf.parse(maxDate));
		    max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);
		 
		    Calendar curr = min;
		    while (curr.before(max)) {
		     result.add(sdf.format(curr.getTime()));
		     curr.add(Calendar.MONTH, 1);
		    }
		 
		    return result;
	}
}
