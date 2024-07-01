package com.lrh.leetcode.utils;

import java.util.HashMap;
import java.util.Map;

public class StrUtils {

	private StrUtils() {
	};

	/**
	 * 改变字符串中的字符为特定字符
	 *
	 * @param str 待改变的字符
	 * @param map map中前一个是待修改字符，第二个是修改后的字符
	 * @return 返回修改后的字符串
	 */
	public static String changeChar(String str, Map<Character, Character> map) {

		StringBuilder sBuilder = new StringBuilder(str);// StringBuilder适合修改

		for (int i = 0; i < str.length(); ++i) {
			if (map.get(sBuilder.charAt(i)) != null) {// 字符存在map中就修改
				sBuilder.replace(i, i + 1, String.valueOf(map.get(sBuilder.charAt(i))));
			}
		}

		return sBuilder.toString();
	}

	/**
	 * 测试
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		Map<Character, Character> map = new HashMap<>();
		map.put('[', '{');
		map.put(']', '}');
		System.out.println(StrUtils.changeChar("[[2,1,1],[0,1,1],[1,0,1]]", map));
	}

}
