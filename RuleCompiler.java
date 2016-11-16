package org.wso2.training;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import org.drools.decisiontable.InputType;
import org.drools.decisiontable.SpreadsheetCompiler;
import org.drools.decisiontable.parser.RuleMatrixSheetListener;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message.Level;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;

public class RuleCompiler {

	
	public static final void main(String[] args) {
		try {
			String xlsFile = "BoltonOfferings.xlsx";
			KieContainer kieContainer = RuleCompiler.getRulesContainer(xlsFile);
			KieSession kieSession =  kieContainer.newKieSession();
			Option option = new Option("DT4525", "DT4141 HBO");
			kieSession.insert(option);
			kieSession.getAgenda().getAgendaGroup("category-matrix").setFocus();
			kieSession.fireAllRules();

			System.out.println("Option : " + option);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	 public static  KieContainer getRulesContainer(String xlsFile) throws FileNotFoundException {
		SpreadsheetCompiler converter = new SpreadsheetCompiler();
		String drl = converter.compile(
				RuleCompiler.class.getResourceAsStream(xlsFile), InputType.XLS,
				new RuleMatrixSheetListener());

		System.out.println(drl);
		PrintWriter out = new PrintWriter("Comp.drl");
		out.println(drl);
		out.close();

		KieServices kieServices = KieServices.Factory.get();

		KieFileSystem kfs = kieServices.newKieFileSystem();
		kfs.write(ResourceFactory.newFileResource("Comp.drl"));

		KieBuilder kieBuilder = kieServices.newKieBuilder(kfs).buildAll();
		if (kieBuilder.getResults().hasMessages(Level.ERROR)) {
			System.out.println("Error compiling drl file.");
		}

		 KieContainer kieContainer = kieServices.newKieContainer(kieServices
				.getRepository().getDefaultReleaseId());
		return kieContainer;

	}
}