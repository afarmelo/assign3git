package test.java;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import main.java.memoranda.CurrentProject;
import main.java.memoranda.Project;
import main.java.memoranda.ProjectImpl;
import main.java.memoranda.ProjectManager;
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
    Document doc;
    Element root;
    
    @Before
    public void __init() {
    	prjResources = CurrentProject.getResourcesList();
    	doc = prjResources.getXMLContent();
    	root = doc.getRootElement();
    	printElements(root);
    }
    
	public void printElements(Node curr) {
//		String data = "";
//		Node current = doc;
//		if (current instanceof Element) {
//			Element temp = (Element) current;
//			data = ": " + temp.getQualifiedName();
//		}
//	    else if (current instanceof ProcessingInstruction) {
//	        ProcessingInstruction temp = (ProcessingInstruction) current;
//	        data = ": " + temp.getTarget();   
//	    }
//	    else if (current instanceof DocType) {
//	        DocType temp = (DocType) current;
//	        data = ": " + temp.getRootElementName();   
//	    }
//	    else if (current instanceof Text || current instanceof Comment) {
//	        String value = current.getValue();
//	        value = value.replace('\n', ' ').trim();
//	        if (value.length() <= 20) data = ": " + value;
//	        else data = ": " + current.getValue().substring(0, 17) + "...";   
//	    }
//		System.out.println(current.getClass().getName() + data);
		
		Element temp = (Element) curr;
		Elements children = temp.getChildElements();
		System.out.print("Element: " + temp.getQualifiedName());
		System.out.print(" has " + temp.getChildElements().size());
		System.out.println(" children.");
		
		for (int i = 0; i < children.size(); i++) {
			Element child = children.get(i);
			int childAttr = child.getAttributeCount();
			Elements grandChildren = child.getChildElements();
			int grandChildrenNum = grandChildren.size();
			
			System.out.print("Child: " + child.getQualifiedName());
			System.out.print(" has " + childAttr);
			System.out.print(" attributes and " + grandChildrenNum);
			System.out.println(" children.");
			
			System.out.println("Child: " + child.getQualifiedName());
			
			if (childAttr > 0) {
				for(int j = 0; j < childAttr; j++) {
					Attribute attribute = child.getAttribute(j);
					String att = attribute.getValue();
					System.out.println(att);
					System.out.println(attribute.getQualifiedName());
				}
			}
			
			if (grandChildrenNum > 0) {
				for (int k = 0; k < grandChildrenNum; k++) {
					Element grandChild = grandChildren.get(i);
					System.out.println("Grandchild: " + grandChild.getQualifiedName());
				}
			}
		}
	}
	
	@Test
	public void test() {
		System.out.println("Tests ran successfully");
	}

}
