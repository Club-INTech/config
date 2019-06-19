/*
 * Copyright (C) 2013-2017 Pierre-François Gimenez
 * Distributed under the MIT License.
 */

package pfg.config;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 * The ConfigInfo interface : each object is a configurable value.
 * This interface can be efficiently implemented by an enum
 * @author Pierre-François Gimenez, Xavier "jglrxavpok" Niochaut
 */
public interface ConfigInfo<Type>
{
	/**
	 * This method must return the name sought in the configuration file for this ConfigInfo
	 * @return
	 */
	@Override
	String toString();

	/**
	 * This method must return the default value of this ConfigInfo.
	 * This default value is fetched if the parameter is not present inside the configuration file
	 * @return
	 */
	Type getDefaultValue();

	/**
	 * This method must return the class corresponding to the generic parameter of this class.
	 * Used for casts inside the library
	 * @return
	 */
	Class<Type> getTypeClass();

	void setName(String name);

	/**
	 * Finds all public static final ConfigInfo fields inside the given class using reflection. It also overrides their name to use the name of the field
	 * @param holdingClass
	 * 		The class to search in
	 * @return
	 * 		An array of all renamed ConfigInfo found inside the class
	 */
	static ConfigInfo[] findAllIn(Class<?> holdingClass) {
		List<ConfigInfo<?>> parameters = new LinkedList<>();
		Field[] fields = holdingClass.getFields();
		for(Field field : fields)
		{
			if((field.getModifiers() & (Modifier.PUBLIC | Modifier.STATIC)) != 0)
			{
				if(ConfigInfo.class.isAssignableFrom(field.getType()))
				{
					// found a valid field!
					try
					{
						ConfigInfo<?> info = (ConfigInfo<?>) field.get(null);
						parameters.add(info);
						info.setName(field.getName());
					} catch (IllegalAccessException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return parameters.toArray(new ConfigInfo[0]);
	}

}
