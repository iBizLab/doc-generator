package cn.ibizlab.codegen.groovy

import cn.ibizlab.codegen.groovy.engine.IGroovyCodeGenEngineContext
import groovy.text.Template
import net.ibizsys.codegen.core.engine.ICodeGenEngineContext
import net.ibizsys.codegen.groovy.engine.GroovyCodeGenEngineBase
import net.ibizsys.model.IPSModelObject
import net.ibizsys.model.IPSObject
import net.ibizsys.model.IPSSystem
import net.ibizsys.model.app.view.IPSAppIndexView
import net.ibizsys.model.control.IPSControl
import net.ibizsys.model.control.menu.PSAppMenuItemImplBase
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.service.IPSSubSysServiceAPIDE
import org.apache.commons.io.FileUtils
import org.springframework.util.ObjectUtils
import org.springframework.util.StringUtils

class GroovyCodeGenEngine extends GroovyCodeGenEngineBase {

    private ICodeGenEngineContext iCodeGenEngineContext = new IGroovyCodeGenEngineContext() {
        public String output(IPSObject iPSObject, String strTemplateId) throws Exception {
            return this.output(iPSObject, strTemplateId, (String) null);
        }

        public String output(IPSObject iPSObject, String strTemplateId, String strIndent) throws Exception {
            String strContent = GroovyCodeGenEngine.this.getContent(iPSObject, strTemplateId);
            if (StringUtils.hasLength(strContent) && StringUtils.hasLength(strIndent)) {
                StringBuilder stringBuilder = new StringBuilder();
                String[] lines = strContent.replace("\r\n", "\n").replace("\r", "\n").split("\n");

                for (int i = 0; i < lines.length; ++i) {
                    if (i != 0) {
                        stringBuilder.append("\n");
                    }

                    stringBuilder.append(strIndent);
                    if (StringUtils.hasLength(lines[i])) {
                        stringBuilder.append(lines[i]);
                    }
                }

                return stringBuilder.toString();
            } else {
                return strContent;
            }
        }

        public String outputWithParent(IPSObject iPSObject, String strTemplateId, IPSObject parent) throws Exception {
            String strContent = GroovyCodeGenEngine.this.getContent(iPSObject, strTemplateId, parent);
            return strContent;
        }

        public boolean contains(String strTemplateId) {
            try {
                return StringUtils.hasLength(GroovyCodeGenEngine.this.getTemplateContent(strTemplateId, true));
            } catch (Exception var3) {
                return false;
            }
        }

        public String ignoreNullString(String str) {
            if (str == null)
                return ""
            return str
        }

        public String ignoreNullString(String str, String strDefault) {
            if (str)
                return str
            if (strDefault)
                return strDefault
            return ""
        }

        public String tableString(String str) {
            if (str == null)
                return ""
            str = str.replace("\n", "<br>").replace("|", "\\|")
            return str
        }

        @Override
        String text(String codelist, Object value) {
            if (value == null)
                return ""
            try {
                Class clazz = Class.forName("net.ibizsys.model.PSModelEnums\$" + codelist)
                if (clazz.isEnum()) {
                    List enums = clazz.getEnumConstants()
                    if (enums) {
                        def find = enums.find { e -> e.value.equals(value) }
                        if (find)
                            return find.text
                    }
                }
            } catch (Exception e) {
                println e.getMessage()
            }
            return value
        }
    };

    @Override
    protected ICodeGenEngineContext getContext() {
        return iCodeGenEngineContext
    }

    @Override
    protected void onInit() throws Exception {
        super.onInit()
    }

    @Override
    String getContent(IPSObject iPSObject, String strTemplateId) throws Exception {
        Template template
        try {
            template = this.getTemplate(strTemplateId);
        } catch (Exception e) {
            println "请检查模板ID是否正确,模板ID = " + strTemplateId
            throw e
        }
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("item", iPSObject);
        IPSSystem iPSSystem = null;

        if (iPSObject instanceof IPSSystem) {
            iPSSystem = (IPSSystem) iPSObject;
        } else {
            if (iPSObject instanceof IPSModelObject) {
                iPSSystem = ((IPSModelObject) iPSObject).getParentPSModelObject(IPSSystem.class, false);
            }
        }
        params.put("pub", iPSSystem.getDefaultPSSysSFPub());
        params.put("sys", iPSSystem);
        params.put("ctx", this.getContext());

        StringWriter sw = new StringWriter();
        try {
            template.make(params).writeTo(sw);
        } catch (Exception e) {
            println "请检查模板ID是否正确,模板ID = " + strTemplateId
            throw e
        }
        return sw.toString();
    }

    String getContent(IPSObject iPSObject, String strTemplateId, IPSObject parent) throws Exception {

        Template template
        try {
            template = this.getTemplate(strTemplateId);
        } catch (Exception e) {
            println "请检查模板ID是否正确,模板ID = " + strTemplateId
            throw e
        }

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("item", iPSObject);
        params.put("parent", parent);
        IPSSystem iPSSystem = null;

        if (iPSObject instanceof IPSSystem) {
            iPSSystem = (IPSSystem) iPSObject;
        } else {
            if (iPSObject instanceof IPSModelObject) {
                iPSSystem = ((IPSModelObject) iPSObject).getParentPSModelObject(IPSSystem.class, false);
            }
        }
        params.put("pub", iPSSystem.getDefaultPSSysSFPub());
        params.put("sys", iPSSystem);
        params.put("ctx", this.getContext());

        StringWriter sw = new StringWriter();
        try {
            template.make(params).writeTo(sw);
        } catch (Exception e) {
            println "请检查模板ID是否正确,模板ID = " + strTemplateId
            throw e
        }

        return sw.toString();
    }

    @Override
    protected boolean testOutput(IPSModelObject iPSModelObject, Object[] filters) throws Exception {
        if (!iPSModelObject.filter()) {
            return false
        }
        return super.testOutput(iPSModelObject, filters)
    }

    @Override
    protected void onOutput(IPSSystem iPSSystem, String outputPath, Object[] filters, Map<String, IPSModelObject> fileMap, boolean bIgnoreException) throws Exception {
        if (iPSSystem.getDefaultPSSysSFPub() == null) {
            throw new Exception("未定义系统默认发布对象")
        }

        if (ObjectUtils.isEmpty(iPSSystem.getDefaultPSSysSFPub().getPKGCodeName())) {
            throw new Exception("系统默认发布对象未定义包代码标识")
        }

        if (ObjectUtils.isEmpty(iPSSystem.getDefaultPSSysSFPub().getCodeName())) {
            throw new Exception("系统默认发布对象未定义代码标识")
        }

        String outputRootPath = outputPath
        if (true) {
            FileUtils.copyToFile(this.getClass().getResourceAsStream("/template/script/docsify.js"), new File(outputPath + "/script/docsify.js"))

            output(iPSSystem, "/_404.md.tpl", outputRootPath + "/_404.md", fileMap, bIgnoreException)
            output(iPSSystem, "/_coverpage.md.tpl", outputRootPath + "/_coverpage.md", fileMap, bIgnoreException)
            output(iPSSystem, "/_navbar.md.tpl", outputRootPath + "/_navbar.md", fileMap, bIgnoreException)
            output(iPSSystem, "/_sidebar.md.tpl", outputRootPath + "/_sidebar.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index.html.tpl", outputRootPath + "/index.html", fileMap, bIgnoreException)
            output(iPSSystem, "/README.md.tpl", outputRootPath + "/README.md", fileMap, bIgnoreException)

            output(iPSSystem, "/index/dictionary_index.md.tpl", outputRootPath + "/index/dictionary_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/der_index.md.tpl", outputRootPath + "/index/der_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/demap_index.md.tpl", outputRootPath + "/index/demap_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/logic_index.md.tpl", outputRootPath + "/index/logic_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/ui_logic_index.md.tpl", outputRootPath + "/index/ui_logic_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/action_logic_index.md.tpl", outputRootPath + "/index/action_logic_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/group_dataset_index.md.tpl", outputRootPath + "/index/group_dataset_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/value_rule_index.md.tpl", outputRootPath + "/index/value_rule_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/wf_index.md.tpl", outputRootPath + "/index/wf_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/wfrole_index.md.tpl", outputRootPath + "/index/wfrole_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/security_index.md.tpl", outputRootPath + "/index/security_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/sequence_index.md.tpl", outputRootPath + "/index/sequence_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/notify_index.md.tpl", outputRootPath + "/index/notify_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/sys_value_rule_index.md.tpl", outputRootPath + "/index/sys_value_rule_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/task_index.md.tpl", outputRootPath + "/index/task_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/sfplugin_index.md.tpl", outputRootPath + "/index/sfplugin_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/pfplugin_index.md.tpl", outputRootPath + "/index/pfplugin_index.md", fileMap, bIgnoreException)
            output(iPSSystem, "/index/warn_index.md.tpl", outputRootPath + "/index/warn_index.md", fileMap, bIgnoreException)

            output(iPSSystem, "/.gitlab-ci.yml.tpl", outputRootPath + "/.gitlab-ci.yml", fileMap, bIgnoreException)
        }

        //用例图
        iPSSystem.getAllPSSysUCMaps()?.each { ucMap ->
            if (testOutput(ucMap, filters)) {
                output(ucMap, "/uc/uc.md.tpl", outputRootPath + "/uc/" + ucMap.codeName + ".md", fileMap, bIgnoreException)
            }
        }

        //ER图
        iPSSystem.getAllPSSysERMaps()?.each { erMap ->
            if (testOutput(erMap, filters)) {
                output(erMap, "/er/er.md.tpl", outputRootPath + "/er/" + erMap.codeName + ".md", fileMap, bIgnoreException)
            }
        }

        //模块
        iPSSystem.getAllPSSystemModules()?.each { module ->
            if (testOutput(module, filters)) {
                output(module, "/module/module.md.tpl", outputRootPath + "/module/" + module.codeName + ".md", fileMap, bIgnoreException)
            }
        }

        //实体
        List<IPSDataEntity> pSDataEntities = iPSSystem.getAllPSDataEntities()
        iPSSystem.getAllPSDataEntities()?.each { entity ->
            if (testOutput(entity, filters)) {
                output(entity, "/module/entity.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + ".md", fileMap, bIgnoreException)
                //实体映射
                entity.getAllPSDEMaps()?.each { demap ->
                    output(demap, "/module/demap/demap.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + "/demap/" + demap.codeName + ".md", fileMap, bIgnoreException)
                }
                //值规则
                entity.getAllPSDEFields()?.each { field ->
                    output(field, "/module/field/value_rule/value_rule.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + "/value_rule/" + field.codeName + ".md", fileMap, bIgnoreException)
                }
                //查询
                entity.getAllPSDEDataQueries()?.each { query ->
                    output(query, "/module/query/query.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + "/query/" + query.codeName + ".md", fileMap, bIgnoreException)
                }
                //数据集
                entity.getAllPSDEDataSets()?.each { dataset ->
                    output(dataset, "/module/dataset/dataset.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + "/dataset/" + dataset.codeName + ".md", fileMap, bIgnoreException)
                }
                //处理逻辑
                entity.getAllPSDELogics()?.each { logic ->
                    output(logic, "/module/logic/logic.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + "/logic/" + logic.codeName + ".md", fileMap, bIgnoreException)
                }
                //界面逻辑
                entity.getAllPSAppDEUILogics()?.each { uilogic ->
                    output(uilogic, "/module/uilogic/uilogic.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + "/uilogic/" + uilogic.codeName + ".md", fileMap, bIgnoreException)
                }
                //消息通知
                entity.getAllPSDENotifies()?.each { notify ->
                    output(notify, "/module/notify/notify.md.tpl", outputRootPath + "/module/" + entity.getPSSystemModule().codeName + "/" + entity.codeName + "/notify/" + notify.codeName + ".md", fileMap, bIgnoreException)
                }
            }
        }

        //实体关系
        iPSSystem.getAllPSDERs()?.each { der ->
            if (testOutput(der, filters)) {
                output(der, "/der/der.md.tpl", outputRootPath + "/der/" + der.getName() + ".md", fileMap, bIgnoreException)
            }
        }

        //流程
        iPSSystem.getAllPSWorkflows()?.each { workflow ->
            if (testOutput(workflow, filters)) {
                output(workflow, "/workflow/workflow.md.tpl", outputRootPath + "/workflow/" + workflow.codeName + ".md", fileMap, bIgnoreException)
                //流程bpmn
//                    if (workflow.getPSWFVersions() && workflow.getPSWFVersions().size() > 0) {
//                        workflow.getPSWFVersions().each { version ->
//                            if (testOutput(version, filters)) {
//                                output(version, "/workflow/bpmn.bpmn.tpl", outputRootPath + "/bpmn/" + workflow.codeName + "/" + version.getCodeName() + ".bpmn", fileMap, bIgnoreException)
//                            }
//                        }
//                    }
            }
        }

        //系统角色
        iPSSystem.getAllPSSysUserRoles()?.each { role ->
            if (testOutput(role, filters)) {
                output(role, "/security/role.md.tpl", outputRootPath + "/security/" + role.codeName + ".md", fileMap, bIgnoreException)
            }
        }

        //api
        iPSSystem.getAllPSSysServiceAPIs()?.each { api ->
            if (testOutput(api, filters)) {
                output(api, "/api/api.md.tpl", outputRootPath + "/api/" + api.codeName + ".md", fileMap, bIgnoreException)
            }

            api.getPSDEServiceAPIs()?.each { deapi ->
                if (testOutput(deapi, filters)) {
                    output(deapi, "/api/deapi.md.tpl", outputRootPath + "/api/deapi/" + deapi.codeName + ".md", fileMap, bIgnoreException)
                }
            }
        }

        //client
        iPSSystem.getAllPSSubSysServiceAPIs()?.each { client ->
            if (testOutput(client, filters)) {
                output(client, "/client/client.md.tpl", outputRootPath + "/client/" + client.codeName + ".md", fileMap, bIgnoreException)
            }

            List<IPSSubSysServiceAPIDE> pSSubSysServiceAPIDE = client.getAllPSSubSysServiceAPIDEs()
            if (pSSubSysServiceAPIDE) {
                pSSubSysServiceAPIDE.each { declient ->
                    if (testOutput(declient, filters)) {
                        output(declient, "/client/declient.md.tpl", outputRootPath + "/client/declient/" + declient.codeName + ".md", fileMap, bIgnoreException)
                    }
                }
            }
        }

        //app
        iPSSystem.getAllPSApps()?.each { app ->
            if (testOutput(app, filters)) {
                output(app, "/app/app.md.tpl", outputRootPath + "/app/" + app.codeName + ".md", fileMap, bIgnoreException)
                app.getAllPSAppViews()?.each { iPSAppView ->
                    if (testOutput(iPSAppView, filters)) {
                        output(iPSAppView, "/app/view/app_view.md.tpl", outputRootPath + "/app/view/" + iPSAppView.codeName + ".md", fileMap, bIgnoreException)
                    }
                }


            }
        }


        //数据库查询
        iPSSystem.getAllPSSystemDBConfigs()?.each { dbConfig ->
            if (testOutput(dbConfig, filters)) {
                output(dbConfig, "/index/db_query_index.md.tpl", outputRootPath + "/index/" + dbConfig.getName() + "_db_query_index.md", fileMap, bIgnoreException)
            }
        }
    }


}
