package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.IPSModelObject
import net.ibizsys.model.IPSSystem
import net.ibizsys.model.res.IPSSysSFPlugin

/**
 * 插件link相关
 *
 */
public class PSSysSFPluginExtension {

    /**
     * 插件相关
     * @param iPSSysSFPluginSupportable
     * @return
     */
    public static String getPluginLink(IPSModelObject iPSModelObject) {
        if (iPSModelObject.getPlugin()) {
            return String.format("[%s](index/plugin_index#%s)", iPSModelObject.getPlugin().getName(), iPSModelObject.getPlugin().getCodeName())
        }
        return ""
    }

    /**
     * 存在插件模型
     *
     * @param iPSSystem
     * @return
     */
    static Map<IPSModelObject, IPSSysSFPlugin> getPluginMap(IPSSystem iPSSystem) {
        Map<IPSModelObject, IPSSysSFPlugin> map = [:]

        iPSSystem.getAllPSSystemModules()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSCodeLists()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysServiceAPIs()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSubSysServiceAPIs()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysUtils()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysTranslators()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysSequences()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysUniStates()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysDataSyncAgents()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysLogics()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysBackServices()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysDBSchemes()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysBDSchemes()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysBISchemes()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysMsgQueues()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysMsgTempls()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSSysMsgTargets()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSWFRoles()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSWorkflows()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
        }
        iPSSystem.getAllPSDataEntities()?.forEach { model ->
            if (model.getPlugin()) {
                map.put(model, model.getPlugin())
            }
            model.getAllPSDEActions()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDELogics()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
                entity_model.getPSDELogicNodes()?.forEach { node ->
                    if (node.getPlugin()) {
                        map.put(node, node.getPlugin())
                    }
                }
            }
            model.getAllPSDEDataSets()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEMaps()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEDataImports()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEDataExports()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEPrints()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEReports()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEDataFlows()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEDataSyncs()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDENotifies()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
            model.getAllPSDEUtils()?.forEach { entity_model ->
                if (entity_model.getPlugin()) {
                    map.put(entity_model, entity_model.getPlugin())
                }
            }
        }
        return map
    }

}
