/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.csrfsynct;

import java.util.HashMap;

/**
 *
 * @author KALINDU
 */
public class Map {
	public static HashMap<String, String> hashMap = new HashMap<String, String>();
	
	public void setValue(String key,String value){
		hashMap.put(key, value);
	}
	
	public String getValue(String key){
		return hashMap.get(key);
	}
}
