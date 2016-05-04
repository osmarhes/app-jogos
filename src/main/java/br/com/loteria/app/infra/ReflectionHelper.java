package br.com.loteria.app.infra;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.persistence.Id;

import org.hibernate.proxy.HibernateProxyHelper;

public class ReflectionHelper {
	public static boolean isIdNull(Object obj)
	throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Class<?> valueClass = HibernateProxyHelper.getClassWithoutInitializingProxy(obj);
		for(Field field : valueClass.getDeclaredFields()){
			if(field.getAnnotation(Id.class)!= null && getValue(obj, field) != null)
				return false;			
		}
		return true;
	}
	
	public static Method getGetter(Class<?> targetClass, Field field)
	throws NoSuchMethodException, SecurityException{
		String getterName = "get" + field.getName().substring(0, 1).toUpperCase();
		if(field.getName().length() > 1)
			getterName += field.getName().substring(1);
		
		return targetClass.getMethod(getterName);
	}
	
	public static Method getSetter(Class<?> targetClass, Field field)
	throws NoSuchMethodException, SecurityException{
		String setterName = "set" + field.getName().substring(0, 1).toUpperCase();
		if(field.getName().length() > 1)
			setterName += field.getName().substring(1);
		
		return targetClass.getMethod(setterName, field.getType());
	}
	
	public static Object getValue(Object obj, Field field)
	throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		Object value;
		if(field.isAccessible())
			value = field.get(obj);
		else
			value = getGetter(obj.getClass(), field).invoke(obj);
		return value;
	}
	
	public static void setValue(Object obj, Field field, Object value)
	throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
		if(field.isAccessible())
			field.set(obj, value);
		else{
			/*
			if(value == null){
				Object[] nullValue = new Object[1];
				nullValue[0] = null;
				value = nullValue;
			}
			*/
			getSetter(obj.getClass(), field).invoke(obj, value);
		}
	}
	
	public static void setValue(Object obj, String fieldName, Object value)
	throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException{
		Class<?> objClass = obj.getClass();
		Field field = null;
		while(field == null && !objClass.equals(Object.class)){
			try {
				field = objClass.getDeclaredField(fieldName);
			}
			catch(NoSuchFieldException | SecurityException e){}
			objClass = objClass.getSuperclass();
		}
		if(field == null)
			throw new NoSuchFieldException("Field with name \"" + fieldName + "\" not found in class \"" + obj.getClass().getName() + "\".");
		else
			setValue(obj, field, value);
	}
}
