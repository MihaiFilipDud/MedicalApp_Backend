package ro.tuc.ds2020.web;

import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

public class JavaRMI {

    @Bean
    RmiProxyFactoryBean rmiProxy() {
        RmiProxyFactoryBean bean = new RmiProxyFactoryBean();
        bean.setServiceInterface(HelloWorldRMI.class);
        bean.setServiceUrl("rmi://localhost:1099/helloworldrmi");

        return bean;
    }
}
