package pfg.config;

/**
 * Represents a parser for a given type. Holds the rules to convert from the read String value to a value
 * @param <Type>
 *     The type of parameters this parser can read
 *
 * @author Xavier "jglrxavpok" Niochaut
 */
@FunctionalInterface
public interface ConfigInfoParser<Type> {

    /**
     * Parses the given String into a 'Type' instance or throws {@link IllegalArgumentException} if it is not possible
     * @param value
     *      The String to parse
     * @return
     *      A 'Type' instance corresponding to the given String value
     * @throws IllegalArgumentException
     *      Thrown if the value could not be parsed
     */
    Type parse(String value) throws IllegalArgumentException;
}
