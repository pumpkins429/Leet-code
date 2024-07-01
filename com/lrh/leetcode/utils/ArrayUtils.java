package com.lrh.leetcode.utils;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class ArrayUtils {

	private  ArrayUtils(){
	};

	/**
	 * 由int数组获取List
	 * @param nums
	 * @return
	 */
	public static List<Integer> generateArrayList(int[] nums) {
		List<Integer> list = new ArrayList<Integer>();

		for (int i = 0; i < nums.length; ++i) {
			list.add(nums[i]);
		}

		return list;
	}

	/**
	 * 转换二维数组
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> generate2DArrayList(int[][] nums) {
		List<List<Integer>> list = new ArrayList<List<Integer>>();

		for (int i = 0; i < nums.length; ++i) {
			List<Integer> tmp = new ArrayList<>();
			for (int j = 0; j < nums[i].length; ++j) {
				tmp.add(nums[i][j]);
			}
			list.add(tmp);
		}

		return list;
	}

	@Test
	public void test() {
		System.out.println(ArrayUtils.generateArrayList(new int[] {1, 2, 4, 5}));
		System.out.println(ArrayUtils.generate2DArrayList(new int[][] {{1, 2}, {2, 3}}));
	}
}
