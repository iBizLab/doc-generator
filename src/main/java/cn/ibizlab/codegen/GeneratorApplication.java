package cn.ibizlab.codegen;


import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.ibizlab.codegen.groovy.GroovyCodeGenEngine;
import net.ibizsys.model.IPSModelObject;
import net.ibizsys.model.IPSSystem;
import net.ibizsys.model.PSModelServiceImpl;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

public class GeneratorApplication {

	private static final Log log = LogFactory.getLog(GeneratorApplication.class);
	
	public static void main(String[] args) {

		GeneratorParam generatorParam = new GeneratorParam();
		CmdLineParser cmdLineParser = new CmdLineParser(generatorParam);
		try {
			cmdLineParser.parseArgument(args);
		} catch (CmdLineException e) {
			cmdLineParser.printUsage(System.out);
			throw new RuntimeException(e);
		}

		PSModelServiceImpl psModelServiceImpl = new PSModelServiceImpl();
//		psModelServiceImpl.setPSModelFolderPath("D:\\Workspace\\220.11\\metaSphere\\metasphere-core\\src\\main\\resources\\static\\model\\cn\\ibizlab");
		psModelServiceImpl.setPSModelFolderPath(generatorParam.getModelPath());

		IPSSystem iPSSystem = psModelServiceImpl.getPSSystem();
		
		GroovyCodeGenEngine demoEngine = new GroovyCodeGenEngine();
		try {
//			demoEngine.init("/ibiz/template/runtime/v1", true, null);
			demoEngine.init("/template", true, null);
			//groovyCodeGenEngine.output(iPSSystem, "D:\\groovy_result2");
			Map<String, IPSModelObject> psModelObjectMap = new LinkedHashMap<String, IPSModelObject>();
//			demoEngine.output(iPSSystem, "D:\\result\\metasphere", null, psModelObjectMap, false);
			demoEngine.output(iPSSystem, generatorParam.getOutput(), null, psModelObjectMap, false);

			
			for(Map.Entry<String, IPSModelObject> entry : psModelObjectMap.entrySet()) {
				System.out.println(String.format("输出文件[%1$s]，模型[%2$s]", entry.getKey(), entry.getValue().getId()));
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
//		Map<String, Object> params = new HashMap<String, Object>();
//		params.put("sys", iPSSystem);
//		
//		GStringTemplateEngine engine = new GStringTemplateEngine();
//		
//		String strCode = "${sys.greeting()}\n${sys.test('test')}\n$sys.codeVer\n${sys.getCodeVer()}\n$sys.codeName";
//		
//		try {
//			StringReader reader = new StringReader(strCode);
//			Template template = engine.createTemplate(reader);
//			
//			StringWriter sw = new StringWriter();
//			template.make(params).writeTo(sw);
//			
//			System.out.println(sw.toString());
//		}
//		catch (Exception ex) {
//			ex.printStackTrace();
//		}
		
	}

}
