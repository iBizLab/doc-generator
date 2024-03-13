package cn.ibizlab.codegen.groovy.support


import net.ibizsys.model.dataentity.logic.IPSDELogicParam
import org.springframework.util.StringUtils

public class PSDELogicParamExtension {

    public static String getDataType(IPSDELogicParam iPSDELogicParam) {
        if (iPSDELogicParam.isAppContextParam()) {

        } else if (iPSDELogicParam.isAppGlobalParam()) {
            return "应用全局变量"
        } else if (iPSDELogicParam.isCloneParam()) {

        } else if (iPSDELogicParam.isEntityListParam()) {
            return "数据对象列表"
        } else if (iPSDELogicParam.isEntityMapParam()) {
            return "数据对象字典"
        } else if (iPSDELogicParam.isEntityPageParam()) {
            return "分页查询"
        } else if (iPSDELogicParam.isEntityParam()) {
            return "数据对象"
        } else if (iPSDELogicParam.isEnvParam()) {
            return "环境变量"
        } else if (iPSDELogicParam.isFileListParam()) {
            return "文件对象列表"
        } else if (iPSDELogicParam.isFileParam()) {
            return "文件对象"
        } else if (iPSDELogicParam.isFilterParam()) {
            return "过滤器"
        } else if (iPSDELogicParam.isLastParam()) {
            return "最后数据变量"
        } else if (iPSDELogicParam.isLastReturnParam()) {
            return "上一次调用返回"
        } else if (iPSDELogicParam.isOriginEntity()) {

        } else if (iPSDELogicParam.isSessionParam()) {
            return "会话变量"
        } else if (iPSDELogicParam.isSimpleListParam()) {
            return "简单数据列表"
        } else if (iPSDELogicParam.isSimpleParam()) {
            return "简单数据"
        }
        return ""
    }

    public static String getInfo(IPSDELogicParam iPSDELogicParam) {
        StringBuffer stringBuffer = new StringBuffer()
        stringBuffer.append(iPSDELogicParam.getCodeName())
        if (StringUtils.hasLength(iPSDELogicParam.getLogicName()) && iPSDELogicParam.getLogicName() != iPSDELogicParam.getCodeName()) {
            stringBuffer.append("(")
            stringBuffer.append(iPSDELogicParam.getLogicName())
            stringBuffer.append(")")
        }
        return stringBuffer.toString()
    }


}
