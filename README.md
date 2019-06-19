# CONFIG

## A simple configuration manager

The configuration values are located in two places.
A java class containing the configurable values must provide default values. A text configuration file can overload these values.
The point of the configuration file is to change the configuration without recompiling.

The configuration file must consist of ```key = value``` lines. Commentaries are introduced with the ```#``` character. [Here is an config file example](https://raw.githubusercontent.com/PFGimenez/config/master/example/config_example.ini).

## Gradle installation
If you want to use this library in one of your gradle projects, add this to your `build.gradle`:
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
...
dependencies {
        implementation 'com.github.Club-INTech:config:v2.0'
}
```

## Maven installation

If you want to use this library in one of your maven project, add this to your `pom.xml`:

    <repositories>
        ...
        <repository>
            <id>jitpack.io</id>
            <url>https://jitpack.io</url>
        </repository>
    </repositories>

and

	<dependency>
	    <groupId>com.github.Club-INTech</groupId>
	    <artifactId>config</artifactId>
	    <version>v2.0</version>
	</dependency>



## Manual compilation [![](https://jitpack.io/v/Club-INTech/config.svg)](https://jitpack.io/#Club-INTech/config)

You can compile it yourself. You will need a JDK and gradle.

    $ git clone https://github.com/PFGimenez/config.git --depth 1
    $ cd config
    $ gradle build
