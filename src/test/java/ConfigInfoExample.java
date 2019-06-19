import pfg.config.BaseConfigInfo;
import pfg.config.ConfigInfo;
import pfg.config.DerivedConfigInfo;

/*
 * Copyright (C) 2013-2019 Pierre-François Gimenez, Xavier Niochaut
 * Distributed under the MIT License.
 */

/**
 * An example of ConfigInfo implementation
 * @author Pierre-François Gimenez, Xavier "jglrxavpok" Niochaut
 *
 */
public final class ConfigInfoExample
{
	public static ConfigInfo<Integer> SOME_INTEGER_VALUE = new BaseConfigInfo<>(1337, Integer.TYPE);
	public static ConfigInfo<Double> SOME_DOUBLE_VALUE = new BaseConfigInfo<>(255.42, Double.TYPE);
	public static ConfigInfo<String> SOME_STRING_VALUE = new BaseConfigInfo<>("default-value", String.class);
	public static ConfigInfo<Boolean> SOME_BOOLEAN_VALUE = new BaseConfigInfo<>(false, Boolean.TYPE);

	public static ConfigInfo<Boolean> IS_ODD = new DerivedConfigInfo<>(false, Boolean.TYPE, config -> config.get(SOME_INTEGER_VALUE) % 2 == 1);

	public static ConfigInfo[] values() {
		return ConfigInfo.findAllIn(ConfigInfoExample.class);
	}
}
