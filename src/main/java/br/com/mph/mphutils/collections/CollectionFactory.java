package br.com.mph.mphutils.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import br.com.mph.mphutils.exceptions.MphUtilsRuntimeException;

public class CollectionFactory {

	@SuppressWarnings("rawtypes")
	private static final Class LIST_CLASS = ArrayList.class;
	
	//private static final String LIST_CLASS_NAME = LIST_CLASS.getName();
	
	@SuppressWarnings("rawtypes")
	private static final Class SET_CLASS = LinkedHashSet.class;
	
	//private static final String SET_CLASS_NAME = SET_CLASS.getName();
	
	@SuppressWarnings("rawtypes")
	private static final Class CONTEXT_ORDERED_SET_CLASS = TreeSet.class;
	
	//private static final String CONTEXT_ORDERED_SET_CLASS_NAME = CONTEXT_ORDERED_SET_CLASS.getName();
	
	@SuppressWarnings("rawtypes")
	private static final Class<? extends Map> MAP_CLASS = HashMap.class;
	
	//private static final String MAP_CLASS_NAME = MAP_CLASS.getName();
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static <V extends Object, T extends Collection<Class<V>>> T newCollection(Class collectionClass, Class<V> itemClass){		
		try {
			Object ret = Object.class.newInstance();
			
			if (List.class.isAssignableFrom(collectionClass)){
				ret = Collections.checkedList((List<V>) LIST_CLASS.newInstance(), itemClass) ;
			}
			if (Set.class.isAssignableFrom(collectionClass)){
				ret = Collections.checkedSet((Set<V>) SET_CLASS.newInstance(), itemClass);
			}
			if (SortedSet.class.isAssignableFrom(collectionClass)){
				ret = Collections.checkedSortedSet((SortedSet<V>) CONTEXT_ORDERED_SET_CLASS.newInstance(), itemClass);
			}
			
			return (T) ret;			
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
			throw new MphUtilsRuntimeException(e);
		}
	}
	
	@SuppressWarnings("unchecked")
	private static <K,V extends Object> Map<K, V> newMap(Class<K> keyClass, Class<V> valueClass)
	{
		try {
			return Collections.checkedMap(((Map<K,V>) MAP_CLASS.newInstance()), keyClass, valueClass);
		} catch (InstantiationException | IllegalAccessException e) {			
			e.printStackTrace();
			throw new MphUtilsRuntimeException(e);
		}
	}
	
	@SuppressWarnings({"rawtypes" })
	public static <T extends List<?>> T newList(){
		return (T) newCollection(List.class, Object.class);
	}
	
	public static <V extends Object, T extends List<V>> T newList(Class<V> itemClass)
	{
		return (T) newCollection(List.class, itemClass);
	}
	
	public static <T extends Set<?>> T newSet(){
		return (T) newCollection(Set.class, Object.class);
	}
	
	public static <V extends Object, T extends Set<?>> T newSet(Class<V> itemClass){
		return (T) newCollection(Set.class, Object.class);
	}
	
	@SuppressWarnings("rawtypes")
	public static <T extends SortedSet> T newSortedSet(){
		return (T) newCollection(SortedSet.class, Object.class);
	}
	
	public static <V extends Object, T extends SortedSet> T newSortedSet(Class<V> itemClass){
		return (T) newCollection(SortedSet.class, itemClass);
	}
	
	@SuppressWarnings("rawtypes")
	public static <T extends Map> T newMap(){
		return newCollection(Map.class, Object.class);
	}
}
