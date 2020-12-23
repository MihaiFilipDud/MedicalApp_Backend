package ro.tuc.ds2020.hessian_rmi;

import java.util.Date;

public class HelloWorldImpl implements HelloWorld{
    @Override
    public String sayHelloWithHessian(String msg) {
        System.out.println("=============server side==============");
        System.out.println("msg : " + msg);
        return "Hello " + msg + " Response time :: " + new Date();
    }
}
