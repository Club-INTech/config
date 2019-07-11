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
 * Instances of this interface should be either named by hand or sought using {@link ConfigInfo#findAllIn(Class)} which looks inside the class for {@link Configurable} fields
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

	/**
	 * This method must change the name for this ConfigInfo. Used in {@link #findAllIn(Class)}
	 * @param name
	 */
	void setName(String name);

	/**
	 * Finds all public static final ConfigInfo fields inside the given class using reflection. It also overrides their name to use the name of the field (reformatted in camelCase)
	 * @param holdingClass
	 * 		The class to search in
	 * @return
	 * 		An array of all renamed ConfigInfo found inside the class
	 */
	static ConfigInfo[] findAllIn(Class<?> holdingClass) {
		return findAllIn(holdingClass, true);
	}

	/**
	 * Finds all public static final ConfigInfo fields inside the given class using reflection. It also overrides their name to use the name of the field (reformatted in camelCase)
	 * @param holdingClass
	 * 		The class to search in
	 * @param reformat
	 * 		Use 'false' if the original field name should be kept (no reformatting to camelCase)
	 * @return
	 * 		An array of all renamed ConfigInfo found inside the class
	 */
	static ConfigInfo[] findAllIn(Class<?> holdingClass, boolean reformat) {
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
						String formattedName;
						if(reformat) { // reformat to camelCase
							formattedName = toCamelCase(field.getName());
						} else {
							formattedName = field.getName();
						}
						info.setName(formattedName);
					} catch (IllegalAccessException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		return parameters.toArray(new ConfigInfo[0]);
	}

	/**
	 * Transforms a 'STRING_CONSTANT_NAME' into a 'stringConstantName'
	 * @param input
	 * 		The string to transform
	 * @return
	 * 		The newly formatted camelCase string
	 */
	static String toCamelCase(String input) {
		String[] words = input.split("_");
		words[0] = words[0].toLowerCase();
		for (int i = 1; i < words.length; i++) { // convert to camel case by adding capitalization after changing the name to lowercase
			words[i] = Character.toUpperCase(words[i].charAt(0)) + words[i].toLowerCase().substring(1);
		}
		StringBuilder nameBuilder = new StringBuilder();
		for (String word : words) {
			nameBuilder.append(word);
		}
		return nameBuilder.toString();
	}
}
