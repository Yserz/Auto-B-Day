package de.fhb.autobday.commons;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.*;

import java.util.Date;
import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import de.fhb.autobday.data.Contact;

/**
 * MockTest-Test
 * 
 * @author Andy Klay (klay@fh-brandenburg.de)
 *
 *
 */
public class TemplateParserTest {
	
	
    private TemplateParser classUnderTest;

    private Contact mock;

    @Before
    public void setup() {
    	
    	//mock vom colaborateur erzeugen
        mock = EasyMock.createMock(Contact.class);
        
        //die zu testende Klasse 
        classUnderTest = new TemplateParser();
    }

    @Test
    public void myFirstMockTestMethod() {
    	
        String testString="Lieb${e/er} ${NAME}, ich wuensche dir alles gute zum Geburtstag!";
    	
		// Setting up the expected value of the method call of Mockobject
		EasyMock.expect(mock.getFirstname()).andReturn("Max").times(1);
		// Setup is finished need to activate the mock
		EasyMock.replay(mock);
		
    	assertEquals("TemplateContactTest","Lieber Max, ich wuensche dir alles gute zum Geburtstag!",classUnderTest.parse(testString, mock));    	
    }
    
    @Test
    public void parseSlashExpressionTestMaskulina() { 	

        String testString="e/er";

    	assertEquals("TemplateContactTest","er",classUnderTest.parseSlashExpression(testString, 'm'));
    }
    
    @Test
    public void parseSlashExpressionTestFeminin() { 	

        String testString="e/er";

    	assertEquals("TemplateContactTest","e",classUnderTest.parseSlashExpression(testString, 'w'));
    }



	

}
