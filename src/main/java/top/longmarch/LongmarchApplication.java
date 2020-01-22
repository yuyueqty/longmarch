package top.longmarch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import springfox.documentation.annotations.Cacheable;
import top.longmarch.core.config.CrossDomainFilter;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
@EnableCaching
public class LongmarchApplication {

    public final static Logger log = LoggerFactory.getLogger(LongmarchApplication.class);

    public static void main(String[] args) throws UnknownHostException {
        ConfigurableApplicationContext application = SpringApplication.run(LongmarchApplication.class, args);
        Environment env = application.getEnvironment();
        String ip = InetAddress.getLocalHost().getHostAddress();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        log.info("\n----------------------------------------------------------\n\t" +
                "Application AI-Coal is running! Access URLs:\n\t" +
                "Local: \t\thttp://localhost:" + port + path + "/\n\t" +
                "External: \thttp://" + ip + ":" + port + path + "/\n\t" +
                "API Local: \thttp://localhost" + port + path + "/doc.html\n\t" +
                "API External: \thttp://" + ip + ":" + port + path + "/doc.html\n\t" +
                "----------------------------------------------------------");
    }

    @Bean
    public CrossDomainFilter crossDomainFilter() {
        return new CrossDomainFilter();
    }

}
