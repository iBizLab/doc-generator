package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.dataentity.logic.IPSDELogicParam
import net.ibizsys.model.dataentity.logic.IPSDEUILogicParam
import org.springframework.util.StringUtils

public class PSDEUILogicParamExtension {

    public static String getDataType(IPSDEUILogicParam iPSDEUILogicParam) {
        if (iPSDEUILogicParam.isActiveContainerParam()) {
            return "当前容器对象"
        } else if (iPSDEUILogicParam.isActiveCtrlParam()) {
            return "当前部件对象"
        } else if (iPSDEUILogicParam.isActiveViewParam()) {
            return "当前视图对象"
        } else if (iPSDEUILogicParam.isAppGlobalParam()) {
            return "应用全局变量"
        } else if (iPSDEUILogicParam.isApplicationParam()) {
            return "应用程序变量"
        } else if (iPSDEUILogicParam.isCtrlParam()) {
            return "部件对象"
        } else if (iPSDEUILogicParam.isAppGlobalParam()) {
            return "应用全局变量"
        } else if (iPSDEUILogicParam.isAppGlobalParam()) {
            return "应用全局变量"
        } else if (iPSDEUILogicParam.isEntityListParam()) {
            return "数据对象列表"
        } else if (iPSDEUILogicParam.isEntityMapParam()) {
            return "数据对象字典"
        } else if (iPSDEUILogicParam.isEntityPageParam()) {
            return "分页查询"
        } else if (iPSDEUILogicParam.isEntityParam()) {
            return "数据对象"
        } else if (iPSDEUILogicParam.isFilterParam()) {
            return "过滤器"
        } else if (iPSDEUILogicParam.isLastReturnParam()) {
            return "上一次调用返回"
        } else if (iPSDEUILogicParam.isNavContextParam()) {
            return "导航视图参数绑定参数"
        } else if (iPSDEUILogicParam.isRouteViewSessionParam()) {
            return "顶级视图会话共享参数绑定参数"
        } else if (iPSDEUILogicParam.isSimpleListParam()) {
            return "简单数据列表"
        } else if (iPSDEUILogicParam.isSimpleParam()) {
            return "简单数据"
        } else if (iPSDEUILogicParam.isViewSessionParam()) {
            return "视图会话共享参数绑定参数"
        }
        return ""
    }

    public static String getInfo(IPSDEUILogicParam iPSDEUILogicParam) {
        StringBuffer stringBuffer = new StringBuffer()
        stringBuffer.append(iPSDEUILogicParam.getCodeName())
        if (StringUtils.hasLength(iPSDEUILogicParam.getLogicName()) && iPSDEUILogicParam.getLogicName() != iPSDEUILogicParam.getCodeName()) {
            stringBuffer.append("(")
            stringBuffer.append(iPSDEUILogicParam.getLogicName())
            stringBuffer.append(")")
        }
        return stringBuffer.toString()
    }

}
