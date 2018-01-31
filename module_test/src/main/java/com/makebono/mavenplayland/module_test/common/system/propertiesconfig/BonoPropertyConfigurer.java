package com.makebono.mavenplayland.module_test.common.system.propertiesconfig;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Set;

import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/** 
 * @ClassName: BonoPropertyConfigurer 
 * @Description: BonoPropertyConfigurer 
 * @author makebono
 * @date 2018年1月31日 上午9:48:43 
 *  
 */
public class BonoPropertyConfigurer extends PropertySourcesPlaceholderConfigurer {

    static {
        System.out.println(BonoPropertyConfigurer.class.getName() + " is on.");
    }

    public void setConfigFiles(final Set<String> configFiles) {
        final Properties properties = new Properties();

        try {
            configFiles.forEach(x -> {
                final Resource resource = new ClassPathResource(x);
                logger.info(this.getClass().getName() + " loading properties from " + resource.getFilename());

                try {
                    try (InputStream inputStream = resource.getInputStream()) {
                        final Properties prop = new Properties();
                        prop.load(inputStream);
                        if (prop != null) {
                            properties.putAll(prop);
                        }
                    }
                }
                catch (final Exception e) {
                    System.out.println("Error occurs, message: " + e.getMessage());
                }
            });
        }
        catch (final Exception e) {
            System.out.println("Error occurs, message: " + e.getMessage());
        }

        System.out.print("All properties resolved.\n    ");
        final Enumeration<?> propertySet = properties.propertyNames();

        while (propertySet.hasMoreElements()) {
            final String candidate = (String) propertySet.nextElement();
            System.out.print(candidate + " = " + properties.getProperty(candidate) + "\n    ");
        }
        System.out.println();

        this.setProperties(properties);
    }
}
