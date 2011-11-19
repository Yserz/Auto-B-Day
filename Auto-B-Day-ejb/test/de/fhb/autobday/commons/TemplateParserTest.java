package de.fhb.autobday.commons;
import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Testklasse fuer die Klasse TemplateParser
 * 
 * @author Andy Klay (klay@fh-brandenburg.de)
 *
 */
public class TemplateParserTest {

	
	//cut=class unter test
	private static TemplateParser cut;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cut= new TemplateParser("Andy", "Anne");
		
	}
	
	
	@Test
	public void testGetNumberOfExpressionRaw(){
		assertEquals("NumberTest",1,cut.getNumberOfExpression("1"));
	}
	
	@Test
	public void testGetNumberOfExpressionNumberInnerText(){
		assertEquals("NumberTest",1,cut.getNumberOfExpression("Te1xt"));
	}
	
	@Test
	public void testGetNumberOfExpressionNumberBeforeText(){
		assertEquals("NumberTest",1,cut.getNumberOfExpression("1Text"));
	}
	
	@Test
	public void testParseTemplateAll(){
		assertEquals("NumberTest",
				"Textanfangmussdrinbleiben\n "+TemplateParser.ANSPRACHE[3]+" "+cut.getAddressatenName()+",\n"+TemplateParser.GLUECKWUNSCH[0]+"\n"+TemplateParser.ABSCHIED[0]+" "+cut.getAbsenderName()+" \ntesttext muss drin bleiben!",	
				cut.parse("Textanfangmussdrinbleiben\n {ANSPRACHE3} {NAME0},\n{GLUECKWUNSCH0}\n{ABSCHIED0} {ABSENDER0} \ntesttext muss drin bleiben!")
				);
	}
	
	@Test
	public void testParseTemplateAnother(){
		assertEquals("NumberTest",
				"Textanfangmussdrinbleiben\n "+TemplateParser.ANSPRACHE[2]+" "+cut.getAddressatenName()+",\n"+TemplateParser.GLUECKWUNSCH[2]+"\n"+TemplateParser.ABSCHIED[1]+" "+cut.getAbsenderName()+" \ntesttext muss drin bleiben!",	
				cut.parse("Textanfangmussdrinbleiben\n {ANSPRACHE2} {NAME2},\n{GLUECKWUNSCH2}\n{ABSCHIED1} {ABSENDER1} \ntesttext muss drin bleiben!")
				);
	}
	
	@Test
	public void testParseTemplateFail(){
		assertEquals("NumberTest",
				TemplateParser.ANSPRACHE[2],	
				cut.parse("{ANSPRACHE2}")
				);

	}
	
}
