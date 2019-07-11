import org.junit.Assert;
import org.junit.Test;
import pfg.config.*;

/**
 * Example showing how to load config entries by using annotations
 * @author Xavier "jglrxavpok" Niochaut
 */
public class ObjLoadingExample {

    public static final ConfigInfo<String> GREETING = new BaseConfigInfo<>("hello", String.class);
    public static final ConfigInfo<String> GOODBYE = new BaseConfigInfo<>("bye bye", String.class);
    public static final ConfigInfo<String> GOODBYE_WITH_UNDERSCORES = new BaseConfigInfo<>("bye_bye", String.class);
    public static final ConfigInfo<Boolean> COMPUTED = new DerivedConfigInfo<>(false, Boolean.class, c -> Character.isLowerCase(c.get(GREETING).charAt(0)));

    private class ConfigHolderExample {
        @Configurable
        public String greeting;

        @Configurable("goodbye")
        public String renamedGoodbye;

        @Configurable()
        public String goodbyeWithUnderscores;

        @Configurable
        public boolean computed;
    }

    @Test
    public void loadIntoInstance() throws ReflectiveOperationException {
        ConfigHolderExample example = new ConfigHolderExample();
        Config config = new Config(ConfigInfo.findAllIn(ObjLoadingExample.class), true);
        config.override(GREETING, "Bonjour");
        config.override(GOODBYE, "Au revoir");
        config.override(GOODBYE_WITH_UNDERSCORES, "Au_revoir");
        config.loadInto(example);
        Assert.assertEquals("Bonjour", example.greeting);
        Assert.assertEquals("Au revoir", example.renamedGoodbye);
        Assert.assertEquals("Au_revoir", example.goodbyeWithUnderscores);
        Assert.assertFalse(example.computed);
    }
}
