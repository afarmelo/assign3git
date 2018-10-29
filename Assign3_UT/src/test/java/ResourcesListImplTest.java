package test.java;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Project;
import main.java.memoranda.ProjectImpl;
import main.java.memoranda.ProjectManager;
import main.java.memoranda.Resource;
import main.java.memoranda.ResourcesList;
import main.java.memoranda.ResourcesListImpl;
import main.java.memoranda.util.FileStorage;
import nu.xom.Attribute;
import nu.xom.Builder;
import nu.xom.Comment;
import nu.xom.DocType;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.Node;
import nu.xom.ParsingException;
import nu.xom.ProcessingInstruction;
import nu.xom.Text;

public class ResourcesListImplTest {
	
    ResourcesList prjResources;
    ResourcesList noDoc;
    Document doc;
    Element root;
    
    @Before
    public void __init() {

    }
    
    /**
     * This method will use the first constructor which is called using
     * CurrentProject.getResourcesList(). It runs the resourcesList
     * through scenarios where resources are added, retrieved, and removed.
     * 
     * Conditional checking has given rise to the basis of four test
     * adds, gets, and removes. For the compound conditional in the creation
     * of resources in the addResource method, it takes four calls to 
     * go through all possible combinations of conditions.
     * 
     */
	@Test
	public void testPrjResources() {
    	prjResources = CurrentProject.getResourcesList();
    	
		// test add resources
		prjResources.addResource("Java");
		prjResources.addResource("XML", false, true);
		prjResources.addResource("Gradle", true, false);
		prjResources.addResource("Jacoco", true, true);
		
		// test get resources
		Resource java = prjResources.getResource("Java");		
		assertEquals(false, java.isInetShortcut());
		assertEquals(false, java.isProjectFile());

		Resource xml = prjResources.getResource("XML");		
		assertEquals(false, xml.isInetShortcut());
		assertEquals(true, xml.isProjectFile());
		
		Resource gradle = prjResources.getResource("Gradle");		
		assertEquals(true, gradle.isInetShortcut());
		assertEquals(false, gradle.isProjectFile());
		
		Resource jacoco = prjResources.getResource("Jacoco");		
		assertEquals(true, jacoco.isInetShortcut());
		assertEquals(true, jacoco.isProjectFile());
		
		// test get all resources
		Vector resources = prjResources.getAllResources();
		assertEquals(4, resources.size());
		
		java = (Resource) resources.get(0);
		assertEquals(false, java.isInetShortcut());
		assertEquals(false, java.isProjectFile());
		
		xml = (Resource) resources.get(1);	
		assertEquals(false, xml.isInetShortcut());
		assertEquals(true, xml.isProjectFile());
		
		gradle = (Resource) resources.get(2);		
		assertEquals(true, gradle.isInetShortcut());
		assertEquals(false, gradle.isProjectFile());
		
		jacoco = (Resource) resources.get(3);		
		assertEquals(true, jacoco.isInetShortcut());
		assertEquals(true, jacoco.isProjectFile());
		
		// test get all resources count
		assertEquals(4, prjResources.getAllResourcesCount());
		
		// test remove resources
		prjResources.removeResource("Java");
		prjResources.removeResource("XML");
		prjResources.removeResource("Gradle");
		prjResources.removeResource("Jacoco");
		
		// test get a resource that doesn't exist
		assertEquals(null, prjResources.getResource("NULL"));
	}
	
	@Test
	public void testNoDoc() {
    	noDoc = new ResourcesListImpl(CurrentProject.get());
	}

}
