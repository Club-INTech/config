# This is a simple configuration manager

The configuration values are located in two places.
An java enum of the configurable values must provide default values. A text configuration file can overload these values.
The point of the configuration file is to change the configuration without recompiling.

# Downloading / compiling

You can find the latest compiled .jar here : [https://github.com/PFGimenez/config/releases/tag/latest]

Otherwise, you can compile it yourself. You will need a JDK and ```ant```.

    $ git clone https://github.com/PFGimenez/config.git --depth 1
    $ cd config
    $ ant

Two files will be created :
- config.jar, containing the compiled code .class
- config-sources.jar, containing the sources .java

An example is available with the files ```ConfigInfoExamples.java``` and ```config_example.ini```.

