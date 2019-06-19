package pfg.config;

public class DerivedConfigInfo<Type> extends BaseConfigInfo<Type> {

    private final Deriver<Type> deriver;

    @FunctionalInterface
    public interface Deriver<DeriverType> {
        DeriverType derive(Config config);
    }

    public DerivedConfigInfo(Type defaultValue, Class<Type> typeClass, Deriver<Type> deriver) {
        super(defaultValue, typeClass);
        this.deriver = deriver;
    }

    public Type derive(Config config) {
        return deriver.derive(config);
    }

}
