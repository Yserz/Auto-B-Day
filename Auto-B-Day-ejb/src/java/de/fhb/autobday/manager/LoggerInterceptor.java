package de.fhb.autobday.manager;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Michael Koppen <koppen@fh-brandenburg.de>
 */
public class LoggerInterceptor {
   
    @AroundInvoke
    public Object logCall(InvocationContext context) throws Exception{
		int count = 0;
		Logger LOGGER = Logger.getLogger(context.getMethod().getDeclaringClass().getName());
		
		LOGGER.log(Level.INFO, "---------------------------------------------------------");
		LOGGER.log(Level.INFO, " + Class: {0}", getPureClassName(context.getMethod().getDeclaringClass()));
		LOGGER.log(Level.INFO, " -    Method: {0}", context.getMethod().getName());

		if (context.getParameters() != null) {
			for (Object object : context.getParameters()) {
				count++;
				object.getClass().getName();
				LOGGER.log(Level.INFO, " -       Param {0}: ({1}) {2}", new Object[]{count,getPureClassName(object.getClass()) ,object});

			}
		}
		
        return context.proceed();
    }
	private String getPureClassName(Class klasse){
		String temp = "";
		
		String classNameWithPackage = klasse.getName();
		String packetname = klasse.getPackage().getName();
		
		temp = packetname.replaceAll("package ", "");
		
		temp = classNameWithPackage.replaceAll(temp+".", "");
		
		
		return temp;
	}
}
