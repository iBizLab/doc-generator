package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.IPSSystem
import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.app.res.IPSAppPFPluginRef
import net.ibizsys.model.backservice.IPSSysBackService
import net.ibizsys.model.dataentity.der.IPSDERBase
import net.ibizsys.model.dataentity.ds.IPSDEDataSet

class PSSystemExtension {

    public static boolean hasSysAuditUtil(IPSSystem iPSSystem) {
        boolean hasSysAuditUtil = false
        if (iPSSystem.getAllPSSysUtils()) {
            iPSSystem.getAllPSSysUtils().forEach { sysUtil ->
                if (sysUtil.getUtilType() == PSModelEnums.SysUtilType.DATAAUDIT.value)
                    hasSysAuditUtil = true
            }
        }
        return hasSysAuditUtil
    }

    static List<IPSDERBase> getAllPSDERs(IPSSystem iPSSystem) {
        Map derMap = new HashMap()
        iPSSystem.getAllPSDataEntities().forEach { entity ->
            if (entity.getMajorPSDERs()) {
                entity.getMajorPSDERs().forEach { der ->
                    if (!derMap.containsKey(der.name)) {
                        derMap.put(der.name, der)
                    }
                }
            }
        }
        List ders = new ArrayList()
        ders.addAll(derMap.values())
        return ders
    }

    /**
     * 非预置的后台任务
     *
     * @param iPSSystem
     * @return
     */
    static List<IPSSysBackService> getAllBackServices(IPSSystem iPSSystem) {
        List<IPSSysBackService> backServices = new ArrayList<>()
        iPSSystem.getAllPSSysBackServices().forEach { backService ->
            if (net.ibizsys.model.PSModelEnums.BackendTaskType.PREDEFINED.value != backService.getTaskType()) {
                backServices.add(backService)
            }
        }
        return backServices
    }

    /**
     * 前端插件
     *
     * @param iPSSystem
     * @return
     */
    static Collection<IPSAppPFPluginRef> getAllAppPFPluginRefs(IPSSystem iPSSystem) {
        Map<String, IPSAppPFPluginRef> appPFPluginRefMap = new HashMap<>()
        if (iPSSystem.getAllPSApps()) {
            iPSSystem.getAllPSApps().forEach { app ->
                if (app.getAllPSAppPFPluginRefs()) {
                    app.getAllPSAppPFPluginRefs().each { pluginref ->
                        if (!appPFPluginRefMap.containsKey(pluginref.getPluginCode())) {
                            appPFPluginRefMap.put(pluginref.getPluginCode(), pluginref)
                        }
                    }
                }
            }
        }

        return appPFPluginRefMap.values()
    }

    /**
     * 处理逻辑
     * @param iPSDataEntity
     * @return
     */
    public static boolean hasLogics(IPSSystem iPSSystem) {
        boolean hasLogics = false
        iPSSystem.getAllPSDataEntities().forEach { entity ->
            if (entity.getAllPSDELogics())
                hasLogics = true
        }
        return hasLogics
    }

    /**
     * DEMaps
     * @param iPSDataEntity
     * @return
     */
    public static boolean hasDEMaps(IPSSystem iPSSystem) {
        boolean hasDEMaps = false
        iPSSystem.getAllPSDataEntities().forEach { entity ->
            if (entity.getAllPSDEMaps())
                hasDEMaps = true
        }
        return hasDEMaps
    }

    /**
     * 分组数据集合
     * @param iPSDataEntity
     * @return
     */
    public static List<IPSDEDataSet> getGroupDatasets(IPSSystem iPSSystem) {
        List<IPSDEDataSet> iPSDEDataSets = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().forEach { entity ->
            if (entity.getAllPSDEDataSets()) {
                entity.getAllPSDEDataSets().each { dataset ->
                    if (dataset.getGroupMode() != 0) {
                        iPSDEDataSets.add(dataset)
                    }
                }
            }
        }
        return iPSDEDataSets
    }

    /**
     * 行为附加逻辑
     * @param iPSDataEntity
     * @return
     */
    public static boolean hasActionLogics(IPSSystem iPSSystem) {
        boolean hasActionLogics = false
        iPSSystem.getAllPSDataEntities().forEach { entity ->
            if (entity.hasActionLogics())
                hasActionLogics = true
        }
        return hasActionLogics
    }

    /**
     * 附加逻辑
     * @param iPSDataEntity
     * @return
     */
    public static boolean hasUILogics(IPSSystem iPSSystem) {
        boolean hasUILogics = false
        iPSSystem.getAllPSDataEntities().forEach { entity ->
            if (entity.getAllPSAppDEUILogics())
                hasUILogics = true
        }
        return hasUILogics
    }

}
