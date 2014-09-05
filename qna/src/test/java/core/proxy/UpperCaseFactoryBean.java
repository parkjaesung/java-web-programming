package core.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import org.springframework.beans.factory.FactoryBean;

import core.proxy.DynamicProxyTest.Hello;

public class UpperCaseFactoryBean implements FactoryBean<Hello> {
	private InvocationHandler invocationHandler;
	
	public void setInvocationHandler(InvocationHandler invocationHandler) {
		this.invocationHandler = invocationHandler;
	}
	
	@Override
	public Hello getObject() throws Exception {
		return (Hello)Proxy.newProxyInstance(
				getClass().getClassLoader(), 
				new Class[] { Hello.class},
				invocationHandler);
	}

	@Override
	public Class<?> getObjectType() {
		return Hello.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
