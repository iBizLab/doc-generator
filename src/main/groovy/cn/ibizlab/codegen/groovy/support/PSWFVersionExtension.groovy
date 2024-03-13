package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.IPSSystem
import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.logic.IPSDELogicLinkCond
import net.ibizsys.model.util.Conditions
import net.ibizsys.model.wf.IPSWFCallActivityProcess
import net.ibizsys.model.wf.IPSWFDE
import net.ibizsys.model.wf.IPSWFEmbedWFProcess
import net.ibizsys.model.wf.IPSWFEmbedWFProcessBase
import net.ibizsys.model.wf.IPSWFInteractiveProcess
import net.ibizsys.model.wf.IPSWFLink
import net.ibizsys.model.wf.IPSWFLinkCond
import net.ibizsys.model.wf.IPSWFLinkCustomCond
import net.ibizsys.model.wf.IPSWFLinkGroupCond
import net.ibizsys.model.wf.IPSWFLinkSingleCond
import net.ibizsys.model.wf.IPSWFProcess
import net.ibizsys.model.wf.IPSWFVersion
import org.springframework.util.StringUtils

public class PSWFVersionExtension {

    public static String getDeployId2(IPSWFProcess iPSWFProcess) {
        return iPSWFProcess.getCodeName().replace("_", "").toLowerCase()
    }

    public static String getDeployId2(IPSWFLink iPSWFLink) {
        return String.format("%s-%s", iPSWFLink.getFromPSWFProcess().getDeployId2(), iPSWFLink.getToPSWFProcess().getDeployId2())
    }

    public static Collection getWFRoles(IPSWFVersion iPSWFVersion) {
        Map wfRoleMap = new HashMap()
        iPSWFVersion.getPSWFProcesses().each { process ->
            if (process instanceof IPSWFInteractiveProcess || process instanceof IPSWFCallActivityProcess || process instanceof IPSWFEmbedWFProcess) {
                if (process.getPSWFProcessRoles()) {
                    process.getPSWFProcessRoles().each { processRole ->
                        if (PSModelEnums.WFProcRoleType.WFROLE.value.equals(processRole.getWFProcessRoleType())) {
                            if (!wfRoleMap.containsKey(processRole.getPSWFRole().getCodeName())) {
                                wfRoleMap.put(processRole.getPSWFRole().getCodeName(), processRole.getPSWFRole())
                            }
                        }
                    }
                }
            }
        }
        wfRoleMap.values()
    }

    public static List getApps(IPSWFDE iPSWFDE) {
        List list = new ArrayList()
        IPSSystem iPSSystem = iPSWFDE.getParentPSModelObject(net.ibizsys.model.IPSSystem.class)
        if (iPSSystem.getAllPSApps()) {
            iPSSystem.getAllPSApps().forEach { app ->
                if (!app.isMobileApp()) {
                    if (app.getAllPSAppDataEntities()) {
                        app.getAllPSAppDataEntities().each { appentity ->
                            if (appentity.getPSDataEntity() && appentity.getPSDataEntity().getName().equals(iPSWFDE.getPSDataEntity().getName())) {
                                list.add(app)
                            }

                        }
                    }
                }
            }
        }
        return list
    }

    public static List getMobApps(IPSWFDE iPSWFDE) {
        List list = new ArrayList()
        IPSSystem iPSSystem = iPSWFDE.getParentPSModelObject(net.ibizsys.model.IPSSystem.class)
        if (iPSSystem.getAllPSApps()) {
            iPSSystem.getAllPSApps().forEach { app ->
                if (app.isMobileApp()) {
                    if (app.getAllPSAppDataEntities()) {
                        app.getAllPSAppDataEntities().each { appentity ->
                            if (appentity.getPSDataEntity() && appentity.getPSDataEntity().getName().equals(iPSWFDE.getPSDataEntity().getName())) {
                                list.add(app)
                            }

                        }
                    }
                }
            }
        }
        return list
    }

    public static boolean isMultiInstance(IPSWFProcess iPSWFProcess) {
        if ((iPSWFProcess instanceof IPSWFInteractiveProcess || iPSWFProcess instanceof IPSWFEmbedWFProcessBase || iPSWFProcess instanceof IPSWFCallActivityProcess) && (iPSWFProcess.getMultiInstMode() == 'PARALLEL' || iPSWFProcess.getMultiInstMode() == 'SEQUENTIAL')) {
            return true
        }
        return false
    }

    public static boolean isSequential(IPSWFProcess iPSWFProcess) {
        if ((iPSWFProcess instanceof IPSWFInteractiveProcess || iPSWFProcess instanceof IPSWFEmbedWFProcessBase || iPSWFProcess instanceof IPSWFCallActivityProcess) && (iPSWFProcess.getMultiInstMode() == 'SEQUENTIAL')) {
            return true
        }
        return false
    }

    public static boolean hasTimeOutLink(IPSWFProcess iPSWFProcess) {
        boolean hasTimeOutLink = false
        if (iPSWFProcess.getPSWFLinks()) {
            iPSWFProcess.getPSWFLinks().each { link ->
                if (PSModelEnums.WFLinkType.TIMEOUT.value.equals(link.getWFLinkType())) {
                    hasTimeOutLink = true
                }
            }
        }
        return hasTimeOutLink
    }

    public static String getCloudPorcessUser(IPSWFProcess iPSWFProcess) {
        if (iPSWFProcess instanceof IPSWFInteractiveProcess || iPSWFProcess instanceof IPSWFCallActivityProcess || iPSWFProcess instanceof IPSWFEmbedWFProcess) {
            List list = new ArrayList()
            if (iPSWFProcess.getPSWFProcessRoles()) {
                iPSWFProcess.getPSWFProcessRoles().each { processRole ->
                    if (PSModelEnums.WFProcRoleType.WFROLE.value.equals(processRole.getWFProcessRoleType())) {
                        if (processRole.getPSWFRole()) {
                            if (StringUtils.hasLength(processRole.getUserData()) && StringUtils.hasLength(processRole.getUserData2())) {
                                list.add(String.format("\${wfCoreService.getGroupUsers('%s|%s|%s',execution)}", processRole.getPSWFRole().getCodeName(), processRole.getUserData(), processRole.getUserData2()))
                            } else {
                                list.add(String.format("\${wfCoreService.getGroupUsers('%s',execution)}", processRole.getPSWFRole().getCodeName()))
                            }
                        }
                    } else if (PSModelEnums.WFProcRoleType.UDACTOR.value.equals(processRole.getWFProcessRoleType())) {
                        if (processRole.getUDField()) {
                            processRole.getUDField().split(";").each { field ->
                                list.add(String.format("\${activedata.%s}", field.toLowerCase()))
                            }
                        }
                    } else if (PSModelEnums.WFProcRoleType.CURACTOR.value.equals(processRole.getWFProcessRoleType())) {
                        list.add(String.format("\${activedata.%s}", "createman"))
                    }
                }
            }
            return String.join(",", list)
        }
        return ""
    }

    public static String getCloudPorcessUser2(IPSWFProcess iPSWFProcess) {
        if (iPSWFProcess instanceof IPSWFInteractiveProcess || iPSWFProcess instanceof IPSWFCallActivityProcess || iPSWFProcess instanceof IPSWFEmbedWFProcess) {
            List list = new ArrayList()
            if (iPSWFProcess.getPSWFProcessRoles()) {
                iPSWFProcess.getPSWFProcessRoles().each { processRole ->
                    if (PSModelEnums.WFProcRoleType.WFROLE.value.equals(processRole.getWFProcessRoleType())) {
                        if (processRole.getPSWFRole()) {
                            if (StringUtils.hasLength(processRole.getUserData()) && StringUtils.hasLength(processRole.getUserData2())) {
                                list.add(String.format("#wfCoreService.getGroupUsers2('%s|%s|%s',#execution)", processRole.getPSWFRole().getCodeName(), processRole.getUserData(), processRole.getUserData2()))
                            } else {
                                list.add(String.format("#wfCoreService.getGroupUsers2('%s',#execution)", processRole.getPSWFRole().getCodeName()))
                            }
                        }
                    } else if (PSModelEnums.WFProcRoleType.UDACTOR.value.equals(processRole.getWFProcessRoleType())) {
                        if (processRole.getUDField()) {
                            processRole.getUDField().split(";").each { field ->
                                list.add(String.format("#activedata.%s", field.toLowerCase()))
                            }
                        }
                    } else if (PSModelEnums.WFProcRoleType.CURACTOR.value.equals(processRole.getWFProcessRoleType())) {

                    }
                }
            }
            return String.join("||", list)
        }
        return ""
    }

    public static String getCloudDueDate(IPSWFProcess iPSWFProcess) {
        String temp = "%s%s%s"
        if (iPSWFProcess.isEnableTimeout() && iPSWFProcess.getTimeout() > 0) {
            if (PSModelEnums.WFTimeoutType.MINUTE.value.equals(iPSWFProcess.getTimeoutType())) {
                return String.format(temp, "M", iPSWFProcess.getTimeout(), "PT")
            } else if (PSModelEnums.WFTimeoutType.HOUR.value.equals(iPSWFProcess.getTimeoutType())) {
                return String.format(temp, "H", iPSWFProcess.getTimeout(), "PT")
            } else if (PSModelEnums.WFTimeoutType.DAY.value.equals(iPSWFProcess.getTimeoutType())) {
                return String.format(temp, "D", iPSWFProcess.getTimeout(), "P")
            }
        }
        return ""
    }

    public static String getCloudCondInfo(IPSWFLink iPSWFLink) {
        StringBuffer info = new StringBuffer()
        if (iPSWFLink.getPSWFLinkGroupCond()) {
            List<IPSWFLinkCond> conds = iPSWFLink.getPSWFLinkGroupCond().getPSWFLinkConds()
            if (conds) {
                for (int i = 0; i < conds.size(); i++) {
                    if (i > 0)
                        info.append(" && ");
                    getCloudGroupCondInfo(info, conds.get(i));
                }
            }
        }

        return info.toString()
    }

    public static void getCloudGroupCondInfo(StringBuffer info, IPSWFLinkCond iPSWFLinkCond) {
        if (iPSWFLinkCond instanceof IPSWFLinkSingleCond) {
            IPSWFLinkSingleCond iPSWFLinkSingleCond = (IPSWFLinkSingleCond) iPSWFLinkCond
            String fieldInfo = iPSWFLinkSingleCond.getFieldName()
            if(fieldInfo) {
                fieldInfo =  fieldInfo.toLowerCase()
                String fieldCondInfo = ""
                if (Conditions.ISNULL.equals(iPSWFLinkSingleCond.getCondOP()) || Conditions.ISNOTNULL.equals(iPSWFLinkSingleCond.getCondOP())) {
                    fieldCondInfo = String.format("wfCoreService.test(activedata.%s, '%s', null)", fieldInfo, iPSWFLinkSingleCond.getCondOP())
                } else if ("ENTITYFIELD".equals(iPSWFLinkSingleCond.getParamType())) {
                    if (iPSWFLinkSingleCond.getParamValue()) {
                        fieldCondInfo = String.format("wfCoreService.test(activedata.%s, '%s', activedata.%s)",
                                fieldInfo,
                                iPSWFLinkSingleCond.getCondOP(),
                                iPSWFLinkSingleCond.getParamValue().toLowerCase())
                    } else {
                        fieldCondInfo = String.format("wfCoreService.test(activedata.%s, '%s', null)", fieldInfo, iPSWFLinkSingleCond.getCondOP())
                    }
                } else if ("CURTIME".equals(iPSWFLinkSingleCond.getParamType())) {
                    fieldCondInfo = String.format("wfCoreService.test(activedata.%s, '%s',  wfCoreService.getnow())", fieldInfo, iPSWFLinkSingleCond.getCondOP())
                } else {
                    if (iPSWFLinkSingleCond.getParamValue()) {
                        fieldCondInfo = String.format("wfCoreService.test(activedata.%s, '%s', '%s')",
                                fieldInfo,
                                iPSWFLinkSingleCond.getCondOP(),
                                iPSWFLinkSingleCond.getParamValue().replace("‘", "").replace("“", "").replace("”", ""))
                    } else {
                        fieldCondInfo = String.format("wfCoreService.test(activedata.%s, '%s', null)", fieldInfo, iPSWFLinkSingleCond.getCondOP())
                    }
                }
                info.append(fieldCondInfo)
            }
        } else if (iPSWFLinkCond instanceof IPSWFLinkCustomCond) {

        } else if (iPSWFLinkCond instanceof IPSWFLinkGroupCond) {
            IPSWFLinkGroupCond iPSWFLinkGroupCond = (IPSWFLinkGroupCond) iPSWFLinkCond
            List<IPSDELogicLinkCond> conds = iPSWFLinkGroupCond.getPSWFLinkConds()
            if (conds) {
                if (iPSWFLinkGroupCond.isNotMode()) {
                    info.append("!");
                }
                info.append("(");
                for (int i = 0; i < conds.size(); i++) {
                    if (i > 0)
                        info.append(String.format(" %s ", "AND".equals(iPSWFLinkGroupCond.getGroupOP()) ? "&&" : "||"))
                    getCloudGroupCondInfo(info, conds.get(i))
                }
                info.append(")");
            }
        }
    }
}
