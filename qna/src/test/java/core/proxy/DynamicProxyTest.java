package core.proxy;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class DynamicProxyTest {
	@Test
	public void myproxy() throws Exception {
		Hello hello = new HelloTarget();
		assertThat(hello.sayHello("JavaJiGi"), is("Hello JavaJiGi"));
		assertThat(hello.sayHi("JavaJiGi"), is("Hi JavaJiGi"));
		assertThat(hello.sayThankYou("JavaJiGi"), is("Thank You JavaJiGi"));
		
		Hello proxiedHello = new HelloUppercase(hello);
		
		assertThat(proxiedHello.sayHello("JavaJiGi"), is("HELLO JAVAJIGI"));
		assertThat(proxiedHello.sayHi("JavaJiGi"), is("HI JAVAJIGI"));
		assertThat(proxiedHello.sayThankYou("JavaJiGi"), is("THANK YOU JAVAJIGI"));
	}
	
	interface Hello {
	    String sayHello(String name);
	    String sayHi(String name);
	    String sayThankYou(String name);
	}

	class HelloTarget implements Hello {
	    public String sayHello(String name) {
	        return "Hello " + name;
	    }

	    public String sayHi(String name) {
	        return "Hi " + name;
	    }

	    public String sayThankYou(String name) {
	        return "Thank You " + name;
	    }
	}
	
	class HelloUppercase implements Hello {
	    Hello hello;
	    
	    public HelloUppercase(Hello hello) {
	        this.hello = hello;
	    }

	    public String sayHello(String name) {
	        return hello.sayHello(name).toUpperCase();
	    }

	    public String sayHi(String name) {
	        return hello.sayHi(name).toUpperCase();
	    }

	    public String sayThankYou(String name) {
	        return hello.sayThankYou(name).toUpperCase();
	    }    
	}
}
