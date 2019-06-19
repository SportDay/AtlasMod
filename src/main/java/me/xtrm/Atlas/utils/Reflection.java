package me.xtrm.Atlas.utils;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

@SuppressWarnings("all")
public class Reflection {
	
	public static void setField(Object obj, String field, Object value) {
		try {
			Field f = obj.getClass().getDeclaredField(field);
			f.setAccessible(true);
			f.set(obj, value);
		} catch(SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static Object getFieldValue(Object obj, String field) {
		try {
			Field f = obj.getClass().getDeclaredField(field);
			f.setAccessible(true);
			return f.get(obj);
		} catch(SecurityException | NoSuchFieldException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object invokeMethod(Object obj, String method, Class<?>... param) {
		try {
			Method m = obj.getClass().getDeclaredMethod(method, param);
			m.setAccessible(true);
			return m.invoke(obj, param);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Constructor getConstructor(Object obj, Class<?>... param) {
		try {
			return obj.getClass().getDeclaredConstructor(param);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object newInstance(Constructor constr, Object... params) {
		try {
			return constr.newInstance(params);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Class getClass(String name) {
		try {
			return Class.forName(name);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Hacky way to load a file,
	 * does it work?
	 * @param file
	 */
	public static void loadFile(File file) {
		try {
			URL url = file.toURI().toURL();
			URLClassLoader classLoader = (URLClassLoader)ClassLoader.getSystemClassLoader();
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			method.setAccessible(true);
			method.invoke(classLoader, url);
		} catch (MalformedURLException | IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}		
	}
}