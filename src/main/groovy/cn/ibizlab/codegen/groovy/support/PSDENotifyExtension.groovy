package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.IPSSystem
import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.action.IPSDEActionLogic
import net.ibizsys.model.dataentity.logic.IPSDEDENotifyLogic
import net.ibizsys.model.dataentity.notify.IPSDENotify

public class PSDENotifyExtension {

    /**
     * 使用场景
     * @param iPSDENotify
     * @return
     */
    public static List refs(IPSDENotify iPSDENotify) {
        List refs = []
        IPSDataEntity iPSDataEntity = iPSDENotify.getParentPSModelObject(IPSDataEntity.class)
        iPSDataEntity.getAllPSDEActions()?.each { iPSDEAction ->
            iPSDEAction.getPreparePSDEActionLogics()?.each {actionlogic ->
                if(actionlogic.getActionLogicType() == PSModelEnums.DEActionLogicType.NOTIFY.value && actionlogic.getPSDENotify() && iPSDENotify.getId() == actionlogic.getPSDENotify().getId()){
                    refs.add(actionlogic)
                }
            }
            iPSDEAction.getCheckPSDEActionLogics()?.each {actionlogic ->
                if(actionlogic.getActionLogicType() == PSModelEnums.DEActionLogicType.NOTIFY.value && actionlogic.getPSDENotify() && iPSDENotify.getId() == actionlogic.getPSDENotify().getId()){
                    refs.add(actionlogic)
                }
            }
            iPSDEAction.getBeforePSDEActionLogics()?.each {actionlogic ->
                if(actionlogic.getActionLogicType() == PSModelEnums.DEActionLogicType.NOTIFY.value && actionlogic.getPSDENotify() && iPSDENotify.getId() == actionlogic.getPSDENotify().getId()){
                    refs.add(actionlogic)
                }
            }
            iPSDEAction.getAfterPSDEActionLogics()?.each {actionlogic ->
                if(actionlogic.getActionLogicType() == PSModelEnums.DEActionLogicType.NOTIFY.value && actionlogic.getPSDENotify() && iPSDENotify.getId() == actionlogic.getPSDENotify().getId()){
                    refs.add(actionlogic)
                }
            }
        }

        IPSSystem iPSSystem = iPSDENotify.getParentPSModelObject(IPSSystem.class)
        iPSSystem.getAllPSDataEntities()?.each {entity ->
            entity.getAllPSDELogics()?.each { iPSDELogic ->
                boolean hasNotify = false
                iPSDELogic.getPSDELogicNodes()?.each {node ->
                    if(node instanceof IPSDEDENotifyLogic && node.getDstPSDENotify().getId()==iPSDENotify.getId()){
                        hasNotify = true
                    }
                }
                if(hasNotify){
                    refs.add(iPSDELogic)
                }
            }
        }
        return refs
    }

}
