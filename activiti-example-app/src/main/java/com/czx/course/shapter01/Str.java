package com.czx.course.shapter01;

public class Str {

	public static void main(String[] args) {
		String s = "drop index ACT_IDX_EXEC_BUSKEY on ${prefix}ACT_RU_EXECUTION;";
		//String k  = s.replaceAll("$\\{prefix\\}", "tablenam");
		System.out.println(s.replaceAll("\\$\\{prefix\\}", "tablenam"));
	}
}
