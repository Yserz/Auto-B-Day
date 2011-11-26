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
        //attribute setzen
        mock.setActive(true);
        mock.setBday(new Date(22,11,1988));
        mock.setId(1);
        mock.setAbdgroup(null);
        mock.setFirstname("Max");
        mock.setHashid("hashid");
        mock.setMail("mocktester@mock.de");
        mock.setName("MockTesterMann");
        mock.setSex('m');
        mock.setUrlid("localhost/blubb");

        
        //die zu testende Klasse 
        classUnderTest = new TemplateParser();
    }

    @Test
    public void myFirstMockTestMethod() {
    	
         EasyMock.replay(mock);   	

        String testString="Lieb${e/er} ${NAME}, ich wuensche dir alles gute zum Geburtstag!";

    	assertEquals("TemplateContactTest","Lieber Max, ich wuensche dir alles gute zum Geburtstag!",classUnderTest.parse(testString, mock));
        
//    	assertTrue(classUnderTest.verarbeite(mock));
    	

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
