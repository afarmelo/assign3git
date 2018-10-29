package test.java;

import static org.junit.Assert.assertEquals;
import java.util.Vector;
import org.junit.Test;
import main.java.memoranda.CurrentProject;
import main.java.memoranda.Resource;
import main.java.memoranda.ResourcesList;
import main.java.memoranda.ResourcesListImpl;
import nu.xom.Document;
import nu.xom.Element;

public class ResourcesListImplTest {
	
    ResourcesList prjResources;
    ResourcesList noDoc;
    Document doc;
    Element root;
    
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
		
		// make sure all elements are removed
		assertEquals(0, prjResources.getAllResourcesCount());
		
		// test get a resource that doesn't exist
		assertEquals(null, prjResources.getResource("NULL"));
	}
	
    /**
     * This method will use the second constructor which requires a project.
     * The project is found using CurrentProject.get(). The functionality is 
     * similar to the previous test method
     */
	@Test
	public void testNoDoc() {
    	noDoc = new ResourcesListImpl(CurrentProject.get());
    	
		// test add resources
		noDoc.addResource("Java");
		noDoc.addResource("XML", false, true);
		noDoc.addResource("Gradle", true, false);
		noDoc.addResource("Jacoco", true, true);
		
		// test get resources
		Resource java = noDoc.getResource("Java");		
		assertEquals(false, java.isInetShortcut());
		assertEquals(false, java.isProjectFile());

		Resource xml = noDoc.getResource("XML");		
		assertEquals(false, xml.isInetShortcut());
		assertEquals(true, xml.isProjectFile());
		
		Resource gradle = noDoc.getResource("Gradle");		
		assertEquals(true, gradle.isInetShortcut());
		assertEquals(false, gradle.isProjectFile());
		
		Resource jacoco = noDoc.getResource("Jacoco");		
		assertEquals(true, jacoco.isInetShortcut());
		assertEquals(true, jacoco.isProjectFile());
		
		// test get all resources
		Vector resources = noDoc.getAllResources();
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
		assertEquals(4, noDoc.getAllResourcesCount());
		
		// test remove resources
		noDoc.removeResource("Java");
		noDoc.removeResource("XML");
		noDoc.removeResource("Gradle");
		noDoc.removeResource("Jacoco");
		
		// make sure all elements are removed
		assertEquals(0, noDoc.getAllResourcesCount());
		
		// test get a resource that doesn't exist
		assertEquals(null, noDoc.getResource("NULL"));
	}
	
	/** 
	 * This method tests the method which gets XML content from
	 * within the resource list. 
	 * 
	 * The comments have hidden lines that print the XML document
	 * to the console which can be checked by a user. Any more explicit
	 * tests would be testing the Document class, rather than the
	 * ResourcesListImpl class.
	 */
	@Test
	public void testXML() {
    	prjResources = CurrentProject.getResourcesList();
    	doc = prjResources.getXMLContent();
    	//System.out.println(doc.toXML());
    	
    	prjResources.addResource("Java", false, true);
    	prjResources.addResource("Jacoco");
    	
    	//System.out.println("\nData added.\n");
    	doc = prjResources.getXMLContent();
    	//System.out.println(doc.toXML());
    	
    	prjResources.removeResource("Java");
    	prjResources.removeResource("Jacoco");
    	
    	//System.out.println("\nData removed.\n");
    	doc = prjResources.getXMLContent();
    	//System.out.println(doc.toXML());
	}

}
