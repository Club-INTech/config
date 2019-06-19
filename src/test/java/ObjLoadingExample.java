import org.junit.Assert;
import org.junit.Test;
import pfg.config.*;

/**
 * Example showing how to load config entries by using annotations
 * @author Xavier "jglrxavpok" Niochaut
 */
public class ObjLoadingExample {

    public static final ConfigInfo<String> greeting = new BaseConfigInfo<>("hello", String.class);
    public static final ConfigInfo<String> goodbye = new BaseConfigInfo<>("bye bye", String.class);
    public static final ConfigInfo<Boolean> computed = new DerivedConfigInfo<>(false, Boolean.class, c -> Character.isLowerCase(c.get(greeting).charAt(0)));

    private class ConfigHolderExample {
        @Configurable
        public String greeting;

        @Configurable("goodbye")
        public String renamedGoodbye;

        @Configurable
        public boolean computed;
    }

    @Test
    public void loadIntoInstance() throws ReflectiveOperationException {
        ConfigHolderExample example = new ConfigHolderExample();
        Config config = new Config(ConfigInfo.findAllIn(ObjLoadingExample.class), true);
        config.override(greeting, "Bonjour");
        config.override(goodbye, "Au revoir");
        config.loadInto(example);
        Assert.assertEquals("Bonjour", example.greeting);
        Assert.assertEquals("Au revoir", example.renamedGoodbye);
        Assert.assertFalse(example.computed);
    }
}
