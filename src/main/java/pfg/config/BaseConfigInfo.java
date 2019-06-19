package pfg.config;

import java.util.List;

/**
 * Base {@link ConfigInfo} implementation. Has a constructor accepting the default value and the type of the config parameter.
 * These parameters are available in their respective getters {@link #getDefaultValue()} {@link #getTypeClass()}
 * @param <Type>
 *      The type of this parameter
 * @author Xavier "jglrxavpok" Niochaut
 */
public class BaseConfigInfo<Type> implements ConfigInfo<Type> {

    private String name;
    /**
     * Default value of the config parameter. Used if the parameter is not present inside the configuration file
     * @see #getDefaultValue()
     */
    private final Type defaultValue;

    /**
     * Class representing the type of this config parameter.
     */
    private final Class<Type> typeClass;

    /**
     * Creates a new instance of BaseConfigInfo
     * @param defaultValue
     *      The default value of this configuration parameter
     * @param typeClass
     *      Class representing the type of this parameter
     */
    public BaseConfigInfo(Type defaultValue, Class<Type> typeClass) {
        this("<TO BE NAMED>", defaultValue, typeClass);
    }

    /**
     * Creates a new instance of BaseConfigInfo
     * @param defaultValue
     *      The default value of this configuration parameter
     * @param typeClass
     *      Class representing the type of this parameter
     */
    public BaseConfigInfo(String name, Type defaultValue, Class<Type> typeClass) {
        this(null, name, defaultValue, typeClass);
    }

    /**
     * Creates a new instance of BaseConfigInfo
     * @param holder
     *      A list in which to save this configuration parameter, or null if none wanted
     * @param defaultValue
     *      The default value of this configuration parameter
     * @param typeClass
     *      Class representing the type of this parameter
     */
    public BaseConfigInfo(List<ConfigInfo<?>> holder, String name, Type defaultValue, Class<Type> typeClass) {
        this.name = name;
        this.defaultValue = defaultValue;
        this.typeClass = typeClass;

        if(holder != null) {
            holder.add(this);
        }
    }

    @Override
    public Type getDefaultValue() {
        return defaultValue;
    }

    @Override
    public Class<Type> getTypeClass() {
        return typeClass;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
