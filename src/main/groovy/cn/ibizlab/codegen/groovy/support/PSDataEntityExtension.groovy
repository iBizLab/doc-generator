package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.IPSSystem
import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.app.IPSApplication
import net.ibizsys.model.app.dataentity.IPSAppDEUIAction
import net.ibizsys.model.app.dataentity.IPSAppDEUIActionGroup
import net.ibizsys.model.app.dataentity.IPSAppDEUILogic
import net.ibizsys.model.app.dataentity.IPSAppDataEntity
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.defield.IPSDEField
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery
import net.ibizsys.model.dataentity.mainstate.IPSDEMainState
import org.springframework.util.ObjectUtils

public class PSDataEntityExtension {

    public static boolean enableOrgDR(IPSDataEntity iPSDataEntity) {
        boolean enableOrgDR = false
        if (iPSDataEntity.getAllPSDEUserRoles()) {
            iPSDataEntity.getAllPSDEUserRoles().forEach { derole ->
                if (derole.isEnableOrgDR()) {
                    enableOrgDR = true
                }
            }
        }
        return enableOrgDR
    }

    public static boolean enableSecDR(IPSDataEntity iPSDataEntity) {
        boolean enableSecDR = false
        if (iPSDataEntity.getAllPSDEUserRoles()) {
            iPSDataEntity.getAllPSDEUserRoles().forEach { derole ->
                if (derole.isEnableSecDR()) {
                    enableSecDR = true
                }
            }
        }
        return enableSecDR
    }

    public static boolean hasAuditUtil(IPSDataEntity iPSDataEntity) {
        boolean hasAuditUtil = false
        if (iPSDataEntity.getAllPSDEUtils()) {
            iPSDataEntity.getAllPSDEUtils().forEach { deUtil ->
                if (deUtil.getUtilType() == PSModelEnums.DEUtilType.DATAAUDIT.value)
                    hasAuditUtil = true
            }
        }
        return hasAuditUtil
    }

    public static boolean hasActionLogics(IPSDataEntity iPSDataEntity) {
        boolean hasActionLogics = false
        if (iPSDataEntity.getAllPSDEActions()) {
            iPSDataEntity.getAllPSDEActions().each { action ->
                if (action.hasActionLogics()) {
                    hasActionLogics = true
                }
            }
        }
        return hasActionLogics
    }

    public static boolean hasDEFValueRules(IPSDataEntity iPSDataEntity) {
        boolean hasValueRules = false
        if (iPSDataEntity.getAllPSDEFields()) {
            iPSDataEntity.getAllPSDEFields().each { field ->
                if (field.hasDEFValueRules()) {
                    hasValueRules = true
                }
            }
        }
        return hasValueRules
    }

    public static boolean hasDEFValueRules(IPSDEField iPSDEField) {
        boolean hasValueRules = false

        if (iPSDEField.getAllPSDEFValueRules()) {
            iPSDEField.getAllPSDEFValueRules().each { valuerule ->
                if (valuerule.getPSDEFVRGroupCondition()
                        && valuerule.getPSDEFVRGroupCondition().getPSDEFVRConditions()) {
                    hasValueRules = true
                }
            }
        }

        return hasValueRules
    }

    public static Map getMainStateMap(IPSDataEntity iPSDataEntity) {
        Map<String, IPSDEMainState> iPSDEMainStateMap = new HashMap<>()
        if (iPSDataEntity.getAllPSDEMainStates()) {
            iPSDataEntity.getAllPSDEMainStates().each { state ->
                iPSDEMainStateMap.put(state.getMSTag(), state)
            }
        }
        return iPSDEMainStateMap
    }


    /**
     * 界面逻辑
     *
     * @param iPSAppDataEntity
     * @return
     */
    public static Collection<IPSAppDEUILogic> getAllPSAppDEUILogics(IPSDataEntity iPSDataEntity) {
        Map<String, IPSAppDEUILogic> iPSAppDEUILogicMap = new HashMap<>()
        IPSSystem iPSSystem = iPSDataEntity.getParentPSModelObject(IPSSystem.class)
        List<IPSApplication> pSApplications = iPSSystem.getAllPSApps()
        if (!ObjectUtils.isEmpty(pSApplications)) {
            pSApplications.each { app ->
                if (app.getAllPSAppDataEntities()) {
                    IPSAppDataEntity iPSAppDataEntity = app.getAllPSAppDataEntities().find({ appentity -> appentity.getPSDataEntity() && appentity.getPSDataEntity().getName().equals(iPSDataEntity.getName()) })
                    if (iPSAppDataEntity) {
                        if (iPSAppDataEntity.getAllPSAppDEUILogics()) {
                            iPSAppDataEntity.getAllPSAppDEUILogics().each { iPSAppDEUILogic ->
                                if (!iPSAppDEUILogicMap.containsKey(iPSAppDEUILogic.getCodeName()))
                                    iPSAppDEUILogicMap.put(iPSAppDEUILogic.getCodeName(), iPSAppDEUILogic)
                            }
                        }
                    }
                }
            }
        }
        return iPSAppDEUILogicMap.values()
    }

    /**
     * 界面行为
     *
     * @param iPSAppDataEntity
     * @return
     */
    public static Collection<IPSAppDEUIAction> getAllPSAppDEUIActions(IPSDataEntity iPSDataEntity) {
        Map<String, IPSAppDEUIAction> iPSAppDEUIActions = new HashMap<>()
        IPSSystem iPSSystem = iPSDataEntity.getParentPSModelObject(IPSSystem.class)
        List<IPSApplication> pSApplications = iPSSystem.getAllPSApps()
        if (!ObjectUtils.isEmpty(pSApplications)) {
            pSApplications.each { app ->
                if (app.getAllPSAppDataEntities()) {
                    IPSAppDataEntity iPSAppDataEntity = app.getAllPSAppDataEntities().find({ appentity -> appentity.getPSDataEntity() && appentity.getPSDataEntity().getName().equals(iPSDataEntity.getName()) })
                    if (iPSAppDataEntity) {
                        if (iPSAppDataEntity.getAllPSAppDEUIActions()) {
                            iPSAppDataEntity.getAllPSAppDEUIActions().each { iPSAppDEUIAction ->
                                if (!iPSAppDEUIActions.containsKey(iPSAppDEUIAction.getCodeName()))
                                    iPSAppDEUIActions.put(iPSAppDEUIAction.getCodeName(), iPSAppDEUIAction)
                            }
                        }
                    }
                }
            }
        }
        return iPSAppDEUIActions.values()
    }

    /**
     * 界面行为组
     *
     * @param iPSAppDataEntity
     * @return
     */
    public static Collection<IPSAppDEUIActionGroup> getAllPSAppDEUIActionGroups(IPSDataEntity iPSDataEntity) {
        Map<String, IPSAppDEUIActionGroup> iPSAppDEUIActionGroups = new HashMap<>()
        IPSSystem iPSSystem = iPSDataEntity.getParentPSModelObject(IPSSystem.class)
        List<IPSApplication> pSApplications = iPSSystem.getAllPSApps()
        if (!ObjectUtils.isEmpty(pSApplications)) {
            pSApplications.each { app ->
                if (app.getAllPSAppDataEntities()) {
                    IPSAppDataEntity iPSAppDataEntity = app.getAllPSAppDataEntities().find({ appentity -> appentity.getPSDataEntity() && appentity.getPSDataEntity().getName().equals(iPSDataEntity.getName()) })
                    if (iPSAppDataEntity) {
                        if (iPSAppDataEntity.getAllPSAppDEUIActionGroups()) {
                            iPSAppDataEntity.getAllPSAppDEUIActionGroups().each { iPSAppDEUIActionGroup ->
                                if (!iPSAppDEUIActionGroups.containsKey(iPSAppDEUIActionGroup.getCodeName()))
                                    iPSAppDEUIActionGroups.put(iPSAppDEUIActionGroup.getCodeName(), iPSAppDEUIActionGroup)
                            }
                        }
                    }
                }
            }
        }
        return iPSAppDEUIActionGroups.values()
    }

    /**
     * 查询包含的长文本
     *
     * @param iPSAppDataEntity
     * @return
     */
    public static  List<IPSDEField> getTextFields(IPSDEDataQuery iPSDEDataQuery) {
        List<IPSDEField> textFields = new ArrayList<>()
        IPSDataEntity iPSDataEntity = iPSDEDataQuery.getParentPSModelObject(IPSDataEntity.class, true)
        if (iPSDataEntity && iPSDataEntity.getStorageMode() == PSModelEnums.DEStorageType.SQL.value) {
            if (iPSDEDataQuery.getViewLevel() == -1) {
                iPSDataEntity.getAllPSDEFields().each { field ->
                    if (field.isQueryColumn() && field.isPhisicalDEField() && field.getDataType() == PSModelEnums.DEFDataType.LONGTEXT.value) {
                        textFields.add(field)
                    }
                }
            }
            if (iPSDEDataQuery.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.ALL.value) {
                iPSDataEntity.getAllPSDEFields().each { field ->
                    if (field.isPhisicalDEField() && field.getDataType() == PSModelEnums.DEFDataType.LONGTEXT.value) {
                        textFields.add(field)
                    }
                }
            }
            if (iPSDEDataQuery.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.LEVEL1.value) {
                iPSDataEntity.getAllPSDEFields().each { field ->
                    if (field.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.LEVEL1.value && field.isPhisicalDEField() && field.getDataType() == PSModelEnums.DEFDataType.LONGTEXT.value) {
                        textFields.add(field)
                    }
                }
            }
            if (iPSDEDataQuery.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.LEVEL2.value) {
                iPSDataEntity.getAllPSDEFields().each { field ->
                    if (field.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.LEVEL2.value && field.isPhisicalDEField() && field.getDataType() == PSModelEnums.DEFDataType.LONGTEXT.value) {
                        textFields.add(field)
                    }
                }
            }
            if (iPSDEDataQuery.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.LEVEL3.value) {
                iPSDataEntity.getAllPSDEFields().each { field ->
                    if (field.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.LEVEL3.value && field.isPhisicalDEField() && field.getDataType() == PSModelEnums.DEFDataType.LONGTEXT.value) {
                        textFields.add(field)
                    }
                }
            }
            if (iPSDEDataQuery.getViewLevel() == PSModelEnums.DEDataQueryColLevel2.DEFGROUP.value && iPSDEDataQuery.getPSDEFGroup()) {
                if (iPSDEDataQuery.getPSDEFGroup().getPSDEFGroupDetails()) {
                    iPSDEDataQuery.getPSDEFGroup().getPSDEFGroupDetails().each { detail ->
                        if (detail.getPSDEField() && detail.getPSDEField().isPhisicalDEField() && detail.getPSDEField().getDataType() == PSModelEnums.DEFDataType.LONGTEXT.value) {
                            textFields.add(detail.getPSDEField())
                        }
                    }
                }
            }
        }
        return textFields
    }
}
