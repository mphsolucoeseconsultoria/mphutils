package br.com.mph.mphutils.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.mph.mphutils.collections.CollectionFactory;
import br.com.mph.mphutils.exceptions.MphUtilsRuntimeException;

/**
 * 
 * @author Ruben
 *
 */
public class ReflectionUtils {
	
	/**
	 * Returns the public attribute list of an object
	 * 
	 * @param object	the object which should list the attribute names result
	 * @return			the object's attribute list
	 */
	public static List<String> listClassAttributes(Class clazz)
	{
		return Arrays
				.asList(
						clazz.getDeclaredFields())
				.stream()
				.map(Field::getName)
				.collect(
						Collectors.toList());
	}

	/**
	 * Returns a set of attribute names of object class parameter that extends from ancestor class parameter.
	 * 
	 * @param ancestor	a class type from the result attribute names extends  
	 * @param object	the object which should list the attribute names result
	 * @return			a set with attribute names which extends from ancestor class
	 */
	public static <T,V extends Object> Set<String> listClassTypeAttributes(Class<T> ancestor, V object){
		
		return Arrays.asList(object.getClass().getDeclaredFields())
				.stream()
				.filter((Predicate<? super Field>) new Predicate<Field>() {
					@Override
					public boolean test(Field t) {						
						return ancestor.isAssignableFrom(t.getType());
					}
				})
				.map(t -> t.getName())
				.collect(Collectors.toSet());
	}
	
	/**
	 * Returns the attribute value of object param that has name equals to attributeName parameter
	 * 
	 * @param object			the object from which you want to get the value of the attribute
	 * @param attributeName		the name of the attribute whose value you want to know
	 * @return					the value of object attribute whose name is attributeName
	 */
	public static <T extends Object> Object getAttributeValue(T object, String attributeName){
		String getterName = getMethodName(attributeName);
		try {
			Method getter = object.getClass().getMethod(getterName);
			return getter.invoke(object);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | 
				IllegalArgumentException | InvocationTargetException e) {			
			e.printStackTrace();
			throw new MphUtilsRuntimeException(e);
		}
	}

	private static String getMethodName(String attributeName) {
		String getterName = "get" + attributeName.substring(0,1).toUpperCase() + attributeName.substring(1);
		return getterName;
	}

	public static <T extends Object> Object getFieldValue(T o1, String attribute) {
		
		if (o1 != null)
		{
			String methodName = getMethodName(attribute);
			try {				
				return o1.getClass().getMethod(methodName);
			} catch (NoSuchMethodException | SecurityException e) {				
				e.printStackTrace();
				throw new MphUtilsRuntimeException(e);
			}
		}
		
		return null;
	}
	
}
