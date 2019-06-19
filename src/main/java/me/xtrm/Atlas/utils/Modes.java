package me.xtrm.Atlas.utils;

import java.util.ArrayList;

public class Modes {
	
	public static ArrayList<String> build(String... args){
		ArrayList<String> finall = new ArrayList<String>();
		
		for(String s : args) {
			finall.add(s);
		}
		
		return finall;
	}

}
