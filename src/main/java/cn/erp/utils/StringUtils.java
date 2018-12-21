package cn.erp.utils;


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
	
	

}
