import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.*;

public abstract class SpringFactoriesLoader {
	private static final Log logger = LogFactory.getLog(org.springframework.core.io.support.SpringFactoriesLoader.class);
	public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

	public SpringFactoriesLoader() {
	}

	public static <T> List<T> loadFactories(Class<T> factoryClass, ClassLoader classLoader) {
		Assert.notNull(factoryClass, "'factoryClass' must not be null");
		ClassLoader classLoaderToUse = classLoader;
		if (classLoader == null) {
			classLoaderToUse = org.springframework.core.io.support.SpringFactoriesLoader.class.getClassLoader();
		}

		List<String> factoryNames = loadFactoryNames(factoryClass, classLoaderToUse);
		if (logger.isTraceEnabled()) {
			logger.trace("Loaded [" + factoryClass.getName() + "] names: " + factoryNames);
		}

		List<T> result = new ArrayList(factoryNames.size());
		Iterator var5 = factoryNames.iterator();

		while(var5.hasNext()) {
			String factoryName = (String)var5.next();
			result.add(instantiateFactory(factoryName, factoryClass, classLoaderToUse));
		}

		AnnotationAwareOrderComparator.sort(result);
		return result;
	}

	public static List<String> loadFactoryNames(Class<?> factoryClass, ClassLoader classLoader) {
		String factoryClassName = factoryClass.getName();

		try {
			Enumeration<URL> urls = classLoader != null ? classLoader.getResources("META-INF/spring.factories") : ClassLoader.getSystemResources("META-INF/spring.factories");
			ArrayList result = new ArrayList();

			while(urls.hasMoreElements()) {
				URL url = (URL)urls.nextElement();
				Properties properties = PropertiesLoaderUtils.loadProperties(new UrlResource(url));
				String factoryClassNames = properties.getProperty(factoryClassName);
				result.addAll(Arrays.asList(StringUtils.commaDelimitedListToStringArray(factoryClassNames)));
			}

			return result;
		} catch (IOException var8) {
			throw new IllegalArgumentException("Unable to load [" + factoryClass.getName() + "] factories from location [" + "META-INF/spring.factories" + "]", var8);
		}
	}

	private static <T> T instantiateFactory(String instanceClassName, Class<T> factoryClass, ClassLoader classLoader) {
		try {
			Class<?> instanceClass = ClassUtils.forName(instanceClassName, classLoader);
			if (!factoryClass.isAssignableFrom(instanceClass)) {
				throw new IllegalArgumentException("Class [" + instanceClassName + "] is not assignable to [" + factoryClass.getName() + "]");
			} else {
				Constructor<?> constructor = instanceClass.getDeclaredConstructor();
				ReflectionUtils.makeAccessible(constructor);
				return constructor.newInstance();
			}
		} catch (Throwable var5) {
			throw new IllegalArgumentException("Unable to instantiate factory class: " + factoryClass.getName(), var5);
		}
	}
}
