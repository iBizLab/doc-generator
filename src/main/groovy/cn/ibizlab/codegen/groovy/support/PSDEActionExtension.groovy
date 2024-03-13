package cn.ibizlab.codegen.groovy.support


import net.ibizsys.model.dataentity.action.IPSDEAction
import net.ibizsys.model.dataentity.action.IPSDEActionLogic

public class PSDEActionExtension {

    public static boolean hasActionLogics(IPSDEAction iPSDEAction) {
        if (iPSDEAction.getCheckPSDEActionLogics()) {
            return true
        }
        if (iPSDEAction.getPreparePSDEActionLogics()) {
            return true
        }
        if (iPSDEAction.getBeforePSDEActionLogics()) {
            return true
        }
        if (iPSDEAction.getAfterPSDEActionLogics()) {
            return true
        }
        return false
    }

    public static List getActionLogics(IPSDEAction iPSDEAction) {
        List<IPSDEActionLogic> actionLogics = new ArrayList<>()
        if (iPSDEAction.getCheckPSDEActionLogics()) {
            actionLogics.addAll(iPSDEAction.getCheckPSDEActionLogics())
        }
        if (iPSDEAction.getPreparePSDEActionLogics()) {
            actionLogics.addAll(iPSDEAction.getPreparePSDEActionLogics())
        }
        if (iPSDEAction.getBeforePSDEActionLogics()) {
            actionLogics.addAll(iPSDEAction.getBeforePSDEActionLogics())
        }
        if (iPSDEAction.getAfterPSDEActionLogics()) {
            actionLogics.addAll(iPSDEAction.getAfterPSDEActionLogics())
        }
        return actionLogics
    }

    public static List getActionLogicInfo(IPSDEAction iPSDEAction) {
        List<String> infos = new ArrayList<>()
        List<IPSDEActionLogic> actionLogics = getActionLogics(iPSDEAction)
        actionLogics.forEach { actionLogic ->
            if (actionLogic.getPSDELogic()) {
                infos.add(actionLogic.getPSDELogic().getLogicName())
            }
            if (actionLogic.getDstPSDEAction()) {
                actionLogic.getDstPSDE()
                infos.add(actionLogic.getDstPSDEAction().getLogicName())
            }
            if (actionLogic.getPSDENotify()) {
                infos.add(actionLogic.getPSDENotify().getLogicName())
            }
            if (actionLogic.getPSSysLogic()) {
                infos.add(actionLogic.getPSSysLogic().getLogicName())
            }
            if (actionLogic.getPSSysSequence()) {
                infos.add(actionLogic.getPSSysSequence().getLogicName())
            }
            if (actionLogic.getPSDEMainState()) {
                infos.add(actionLogic.getPSDEMainState().getLogicName())
            }
            if (actionLogic.getPSDEDataSync()) {
                infos.add(actionLogic.getPSDEDataSync().getLogicName())
            }
            if (actionLogic.getMajorPSDER()) {
                infos.add(actionLogic.getMajorPSDER().getLogicName())
            }
            if (actionLogic.getPSSysTranslator()) {
                infos.add(actionLogic.getPSSysTranslator().getLogicName())
            }
            if (actionLogic.getPSDEFValueRule()) {
                infos.add(actionLogic.getPSDEFValueRule().getLogicName())
            }
            if (actionLogic.getDstPSDEDataSet()) {
                infos.add(actionLogic.getDstPSDEDataSet().getLogicName())
            }
        }
        return String.join("、", infos)
    }

    public static String getInfo(IPSDEActionLogic iPSDEActionLogic) {
        String strInfo = ""
        return strInfo
    }

}
