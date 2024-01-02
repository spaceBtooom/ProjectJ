package com.spring.web.api.backend;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
/**
 * Generic object converter.
 * @author BalusC
 * @link http://balusc.blogspot.com/2007/08/generic-object-converter.html
 */
public final class ObjectConvertor {

	private static final Map<String, Method> CONVERTERS = new HashMap<>();

	static {
		Method[] methods = ObjectConvertor.class.getDeclaredMethods();
		for(Method method : methods){
			if(method.getParameterTypes().length == 1){
				// Converter should accept 1 argument. This skips the convert() method.
				CONVERTERS.put(method.getParameterTypes()[0].getName()+"_"
					+ method.getReturnType().getName(), method);
			}
		}
	}

	public static <T> T convert(Object from, Class<T> to){

		// Null is just null.
		if(from == null){
			return null;
		}

		// Cast we cast?
		if(to.isAssignableFrom(from.getClass())){
			return to.cast(from);
		}

		// Lookup a suitable converter
		String converterId = from.getClass().getName()+"_"+to.getName();
		Method converter = CONVERTERS.get(converterId);
		if(converter == null){
			throw new UnsupportedOperationException("Cannot convert from "
			+ from.getClass().getName() + " to " + to.getName()
				+". Requested converter does not exist.");
		}

		// Convert the value.
		try{
			return to.cast(converter.invoke(to,from));
		} catch (Exception e){
			throw new RuntimeException("Cannot convert from"
				+ from.getClass().getName() + " to " + to.getName()
				+ ". Conversion failed with " + e.getMessage(), e);
		}
	}

}
