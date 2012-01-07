package de.fhb.autobday.manager;

/**
 *
 * @author Michael Koppen
 */
import de.fhb.autobday.manager.user.UserManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LoggerInterceptor {
   
    @AroundInvoke
    public Object logCall(InvocationContext context) throws Exception{
		int count = 0;
		Logger LOGGER = Logger.getLogger(context.getMethod().getDeclaringClass().getName());
		
		LOGGER.log(Level.INFO, "---------------------------------------------------------");
		LOGGER.log(Level.INFO, " + Class: {0}", context.getMethod().getDeclaringClass().getName());
		LOGGER.log(Level.INFO, " -    Method: {0}", context.getMethod().getName());

		
		for (Object object : context.getParameters()) {
			count++;
			LOGGER.log(Level.INFO, " -       Param {0}: {1}", new Object[]{count, object});
			
		}
        return context.proceed();
    }
}
