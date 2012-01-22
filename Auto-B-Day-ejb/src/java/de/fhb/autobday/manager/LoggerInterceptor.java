package de.fhb.autobday.manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * This Interceptor is invoked in every session-bean-manager-class and is
 * responsible for logging genaral information about actual classname methodname
 * and params.
 *
 * @author Michael Koppen mail: koppen@fh-brandenburg.de
 */
public class LoggerInterceptor {

	/**
	 * logging of genaral information about actual classname methodname and
	 * params.
	 *
	 * @param context
	 * @return context proceed
	 * @throws Exception
	 */
	@AroundInvoke
	public Object logCall(InvocationContext context) throws Exception {
		int count = 0;
		Logger LOGGER = Logger.getLogger(context.getMethod().getDeclaringClass().getName());

		LOGGER.log(Level.INFO, "---------------------------------------------------------");
		LOGGER.log(Level.INFO, " + Class: {0}", getPureClassName(context.getMethod().getDeclaringClass()));
		LOGGER.log(Level.INFO, " -    Method: {0}", context.getMethod().getName());

		if (context.getParameters() != null) {
			for (Object object : context.getParameters()) {
				count++;
				object.getClass().getName();
				LOGGER.log(Level.INFO, " -       Param {0}: ({1}) {2}", new Object[]{count, getPureClassName(object.getClass()), object});

			}
		}

		return context.proceed();
	}

	/**
	 * seperates the packagename from the classname and returns the classname.
	 *
	 * @param klasse
	 * @return String
	 */
	private String getPureClassName(Class klasse) {
		String temp = "";

		String classNameWithPackage = klasse.getName();
		String packetname = klasse.getPackage().getName();

		temp = packetname.replaceAll("package ", "");

		temp = classNameWithPackage.replaceAll(temp + ".", "");


		return temp;
	}
}
