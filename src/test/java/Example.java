import org.junit.Assert;
import org.junit.Test;
import pfg.config.Config;

/*
 * Copyright (C) 2013-2017 Pierre-François Gimenez
 * Distributed under the MIT License.
 */

/**
 * An example of the config library usage
 * @author Pierre-François Gimenez
 *
 */

public class Example
{
	/**
	 * First scenario : we load the default values only
	 */
	@Test
	public void loadDefaultsOnly()
	{
		System.out.println("DEFAULT VALUES ONLY");
		Config config;
		config = new Config(ConfigInfoExample.values(), true);
		
		int valInteger = config.getInt(ConfigInfoExample.SOME_INTEGER_VALUE);
		Assert.assertEquals((long)ConfigInfoExample.SOME_INTEGER_VALUE.getDefaultValue(), valInteger);
	}
	
	/**
	 * Second scenario : we load the config file "config_example.ini" with the profile "default"
	 * You must specify at least one profile !
	 */
	@Test
	public void loadOneProfile()
	{
		System.out.println("PROFILE : DEFAULT");

		Config config = new Config(ConfigInfoExample.values(), true, "/config_example.ini", "default");
		
		/*
		 * There are several getters depending on the type of the variable
		 */
		double valDouble = config.get(ConfigInfoExample.SOME_DOUBLE_VALUE);
		System.out.println("The double value hasn't been overridden : " + valDouble + " = " + ConfigInfoExample.SOME_DOUBLE_VALUE.getDefaultValue());
		Assert.assertEquals(ConfigInfoExample.SOME_DOUBLE_VALUE.getDefaultValue(), valDouble, 0.0001);

		String valString = config.get(ConfigInfoExample.SOME_STRING_VALUE);
		System.out.println("The string value has been overridden with the config file : " + valString + " != " + ConfigInfoExample.SOME_STRING_VALUE.getDefaultValue());
		Assert.assertNotSame(ConfigInfoExample.SOME_STRING_VALUE.getDefaultValue(), valString);

		int valInteger = config.get(ConfigInfoExample.SOME_INTEGER_VALUE);
		System.out.println("The integer value has been overridden with the config file : " + valInteger + " != " + ConfigInfoExample.SOME_INTEGER_VALUE.getDefaultValue());
		Assert.assertNotSame(ConfigInfoExample.SOME_INTEGER_VALUE.getDefaultValue(), valInteger);

		boolean valBool = config.get(ConfigInfoExample.SOME_BOOLEAN_VALUE);
		System.out.println("The boolean value has been overridden with the config file : " + valBool + " != " + ConfigInfoExample.SOME_BOOLEAN_VALUE.getDefaultValue());
		Assert.assertNotSame(ConfigInfoExample.SOME_BOOLEAN_VALUE.getDefaultValue(), valBool);

		/*
		 * We override the value of "SOME_STRING_VALUE" with "override-value"
		 */
		config.override(ConfigInfoExample.SOME_STRING_VALUE, "override-value");

		String newValString = config.get(ConfigInfoExample.SOME_STRING_VALUE);
		System.out.println("The string value has been manually overridden : " + newValString + " != " + valString);
		Assert.assertNotSame(valString, newValString);
	}
	
	/**
	 * Third scenario : we load the config file "config_example.ini" with two profiles "default" and "example"
	 * (using more than two profiles is possible)
	 * The order of the profiles is important ! The latter overrides the former.
	 */
	@Test
	public void profileOverride()
	{
		System.out.println("PROFILE : DEFAULT + EXAMPLE");
		
		Config config = new Config(ConfigInfoExample.values(), true, "/config_example.ini", "default", "example");
		
		int valInteger = config.get(ConfigInfoExample.SOME_INTEGER_VALUE);
		System.out.println("The integer value has been overridden with the profile \"example\" : " + valInteger);
		Assert.assertEquals(42, valInteger);
	}

	@Test
	public void noOverrideOnError()
	{
		System.out.println("PROFILE : DEFAULT + ERROR");
		
		Config config = new Config(ConfigInfoExample.values(), true, "/config_example.ini", "default", "error");
		
		int valInteger = config.get(ConfigInfoExample.SOME_INTEGER_VALUE);
		System.out.println("The integer value hasn't been overridden with the profile \"error\" because of an format error : "+valInteger);
		Assert.assertEquals(18754, valInteger);
	}

}
