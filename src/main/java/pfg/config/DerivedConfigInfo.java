package pfg.config;

/**
 * ConfigInfo instance whose values depends on other ConfigInfo instances. No guarantee on the loading other so try to avoid depending on other {@link DerivedConfigInfo}
 * @param <Type>
 */
public class DerivedConfigInfo<Type> extends BaseConfigInfo<Type> {

    /**
     * Object that represents the dependency over other {@link ConfigInfo}
     */
    private final Deriver<Type> deriver;

    /**
     * Functional interface that represents a (Config) -> DeriverType function, which models the dependency to other {@link ConfigInfo}
     * @param <DeriverType>
     */
    @FunctionalInterface
    public interface Deriver<DeriverType> {
        DeriverType derive(Config config);
    }

    /**
     * Creates a new {@link DerivedConfigInfo} with a default value, a type and a dependency function
     * @param defaultValue
     * Default value of the config element
     * @param typeClass
     * The type of the value held by this {@link ConfigInfo}
     * @param deriver
     * Dependency function
     */
    public DerivedConfigInfo(Type defaultValue, Class<Type> typeClass, Deriver<Type> deriver) {
        super(defaultValue, typeClass);
        this.deriver = deriver;
    }

    /**
     * Computes the new value for this config element
     * @param config
     * The config this element depends on
     * @return
     * The computed value
     */
    public Type derive(Config config) {
        return deriver.derive(config);
    }

}
