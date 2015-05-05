package ut.ee.autotest;

import java.io.FileNotFoundException;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.reporting.ColumnDataTypesReport;

public class Autotest implements Runnable {

	private SikuliSteps sikuli;
	private DRDataSource reportFile;
	private int iteration;
	private int jarVersion;

	public Autotest(int iteration, int jarVersion) {
		this.iteration = iteration;
		this.jarVersion = jarVersion;
	}


	public void run() {
		
		reportFile = new DRDataSource("result", "tab", "comment"); //Create report file
		sikuli = new SikuliSteps(); //Create Example class object
		
		//Open the software that opens the software+jarVersion.jar located in src/main/resources folder
		openSoftware();
		
		
		if (iteration == 1) {
			
			//YOUR CODE GOES HERE
			
			//Running this example now that is not related to any of the specification points
			//exampleTestCase(); //delete it
			
			spec1();	
			//tab2();
			//...
		}
		
		if (iteration == 2) {
			spec1();
			//tab1();
			//tab2();
			//...
			
		}
		
		if (iteration == 3) {
			spec1();
		//...
		//tabX();
		//...
		}
		
		//Create the report
		ColumnDataTypesReport report = new ColumnDataTypesReport(reportFile, "Report" + iteration + ".pdf");
		try {
			report.build();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void spec1() {
		
		/**
		 * 
		 */
		String ipsum = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. "+
		"Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, "+
		"when an unknown printer took a galley of type and scrambled it to make a type specimen book. "+
		"It has survived not only five centuries, but also the leap into electronic typesetting, "+
		"remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets "+
		"containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker"+
		" including versions of Lorem Ipsum.";
		
		sikuli.verifyIfExists("KoalaPic.png");
		sikuli.click("TextEditorTab.png");
		sikuli.click("KoalaTab.png");
		if (sikuli.verifyIfExists("KoalaPic.png")){
			reportFile.add("PASSED", "Tab 1", "Koala is exactly the same when returning to the tab.");
		}else{
			reportFile.add("FAILED", "Tab 1", "Koala picture not found.");
		};
		sikuli.click("TextEditorTab.png");
		if(sikuli.write("writeSomething.png", "I am writing here")) {
			System.out.println("Using resolution 1920 x 1080 picture");
		} else {
			//1366x768 resolution picture
			System.out.println("Using resolution 1366 x 768 picture");
			sikuli.write("writeSomethingLower.png", "I am writing here");
		}
		
		boolean succeeded = sikuli.compareTextToClipboards("I am writing here");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 2", "Entered text was the same.");
		} else {
			reportFile.add("FAILED", "Tab 2", "Entered text was not the same.");
		}
		if (sikuli.verifyIfExists("HTMLeditor.PNG")){
			reportFile.add("PASSED", "Tab 2", "HTML editor contains the required elements.");
		} else {
			reportFile.add("FAILED", "Tab 2", "HTML editor doesn't contain the required elements.");
		}
		if (sikuli.verifyIfExists("font.png")){
			reportFile.add("PASSED","Tab 2", "Segoe UI font selected");
		} else {
			reportFile.add("FAILED", "Tab 2", "Wrong font selected");
		}
		// Tab 3
		sikuli.click("loremIpsum.png");
		sikuli.click("ipsumtext.png");
		succeeded = sikuli.compareTextToClipboards(ipsum);
		if (succeeded){
			reportFile.add("PASSED", "Tab 3", "Text length was the same");
		} else {
			reportFile.add("FAILED", "Tab 3", "Text length was different from the original");
		}
		sikuli.rightClick();
		succeeded = sikuli.verifyIfExists("rightclickmenu.png");
		if (succeeded){
			reportFile.add("PASSED", "Tab 3", "Context menu present");
		} else {
			reportFile.add("FAILED", "Tab 3", "Context menu not present");
		}
		
		// Tab 4
		sikuli.click("ColorPickerTab.png");
		//Clicking twice to escape context menu.
		sikuli.click("ColorPickerTab.png");
		sikuli.click("uncollapsed.png");
		succeeded = sikuli.verifyIfExists("ColorPickerTabBody.png");
		if (succeeded) {
			sikuli.click("uncollapsed.png");
			succeeded = sikuli.verifyIfExists("ColorPickerTabBody2.png");
			if (succeeded){
				reportFile.add("PASSED", "Tab 4", "Color picker tab is collapsible");
			}else{
				reportFile.add("FAILED", "Tab 4", "Color picker tab is not collapsible");
			}
		}else{
			reportFile.add("FAILED", "Tab 4", "Can't find color picker tab body");
		}
		sikuli.click("uncollapsed.png");
		succeeded = sikuli.verifyIfExists("white.png");
		if (succeeded){
			sikuli.click("KoalaTab.png");
			sikuli.click("ColorPickerTab.png");
			if (succeeded){
				reportFile.add("PASSED", "Tab 4", "Selected color stays the same when returning to the tab");
			}else{
				reportFile.add("FAILED", "Tab 4", "Selected color has changed.");
			}
		}else{
			reportFile.add("FAILED", "Tab 4", "Color white not selected");
		}
		// and 1 bullet point can be similarly covered like this.
		/**
		 * 
		 */
		//Another test case goes here
		
		//ends after reporting
		
	}
	

	private void exampleTestCase() {
		
		/**
		 * 
		 */
		//Example test case that clicks on the text editor tab and finally compares the text that was entered by copying
		//it to the clipboard and then comparing it
		sikuli.click("TextEditorTab.png");
		if(sikuli.write("writeSomething.png", "I am writing here")) {
			System.out.println("Using resolution 1920 x 1080 picture");
			//Only needed for this example, you don't have to take this into consideration
		} else {
			//1366x768 resolution picture
			System.out.println("Using resolution 1366 x 768 picture");
			sikuli.write("writeSomethingLower.png", "I am writing here");
		}
		
		//Navigate away and back from the tab if spec requires it
		
		boolean succeeded = sikuli.compareTextToClipboards("I am writing here");
		if (succeeded) {
			reportFile.add("PASSED", "Tab 1", "Entered text was the same");
		} else {
			reportFile.add("FAILED", "Tab 1", "Entered text was not the same");
		}
		// and 1 bullet point can be similarly covered like this.
		/**
		 * 
		 */
		//Another test case goes here
		
		//ends after reporting
		
	}
	
	private void openSoftware() {
		try {
			sikuli.openJavaJar(jarVersion);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
