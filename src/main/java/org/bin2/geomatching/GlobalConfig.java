package org.bin2.geomatching;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by benoitroger on 20/03/14.
 */

@ComponentScan(basePackages = { "org.bin2.geomatching" })
@ImportResource({ "classpath:/security-context.xml", "classpath:/application-context.xml" })
@Configuration
public class GlobalConfig {
}