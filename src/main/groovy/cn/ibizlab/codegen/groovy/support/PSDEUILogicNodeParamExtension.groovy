package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.defield.IPSDEField
import net.ibizsys.model.dataentity.logic.IPSDEUILogic
import net.ibizsys.model.dataentity.logic.IPSDEUILogicNode
import net.ibizsys.model.dataentity.logic.IPSDEUILogicNodeParam
import org.springframework.util.StringUtils

public class PSDEUILogicNodeParamExtension {

    public static String getActionInfo(IPSDEUILogicNodeParam iPSDEUILogicNodeParam) {
        String strParamAction = iPSDEUILogicNodeParam.getParamAction();
        if (PSModelEnums.DELogicNodeParamType.SETPARAMVALUE.value.equals(strParamAction)) {
            return "设置给"
        }
        if (PSModelEnums.DELogicNodeParamType.RESETPARAM.value.equals(strParamAction)) {
            return "重置"
        }
        if (PSModelEnums.DELogicNodeParamType.COPYPARAM.value.equals(strParamAction)) {
            return "拷贝到"
        }
        if (PSModelEnums.DELogicNodeParamType.SQLPARAM.value.equals(strParamAction)) {
            return ""
        }
        if (PSModelEnums.DELogicNodeParamType.SFPLUGINPARAM.value.equals(strParamAction)) {
            return PSModelEnums.DELogicNodeParamType.SFPLUGINPARAM.text
        }
        if (PSModelEnums.DELogicNodeParamType.BINDPARAM.value.equals(strParamAction)) {
            return "绑定给"
        }
        if (PSModelEnums.DELogicNodeParamType.APPENDPARAM.value.equals(strParamAction)) {
            return "追加到"
        }
        if (PSModelEnums.DELogicNodeParamType.SORTPARAM.value.equals(strParamAction)) {
            return "排序"
        }
        if (PSModelEnums.DELogicNodeParamType.RENEWPARAM.value.equals(strParamAction)) {
            return "重新建立为"
        }
        if (PSModelEnums.DELogicNodeParamType.WEBURIPARAM.value.equals(strParamAction)) {
            return PSModelEnums.DELogicNodeParamType.WEBURIPARAM.text
        }
        if (PSModelEnums.DELogicNodeParamType.WEBHEADERPARAM.value.equals(strParamAction)) {
            return PSModelEnums.DELogicNodeParamType.WEBHEADERPARAM.text
        }
        if (PSModelEnums.DELogicNodeParamType.MERGEMAPPARAM.value.equals(strParamAction)) {
            return PSModelEnums.DELogicNodeParamType.MERGEMAPPARAM.text
        }
        if (PSModelEnums.DELogicNodeParamType.AGGREGATEMAPPARAM.value.equals(strParamAction)) {
            return PSModelEnums.DELogicNodeParamType.AGGREGATEMAPPARAM.text
        }
        return ""
    }

    public static String getSrcInfo(IPSDEUILogicNodeParam iPSDEUILogicNodeParam) {
        String strParamAction = iPSDEUILogicNodeParam.getParamAction();


        if (PSModelEnums.DELogicParamValueType.SRCDLPARAM.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            IPSDEUILogicNode iPSDEUILogicNode = iPSDEUILogicNodeParam.getParentPSModelObject(IPSDEUILogicNode.class)
            IPSDEUILogic iPSDEUILogic = iPSDEUILogicNode.getParentPSModelObject(IPSDEUILogic.class)
            StringBuffer stringBuffer = new StringBuffer()

            if (iPSDEUILogicNodeParam.getSrcPSDEUILogicParam()) {
                stringBuffer.append(iPSDEUILogicNodeParam.getSrcPSDEUILogicParam().getCodeName())
                if (StringUtils.hasLength(iPSDEUILogicNodeParam.getSrcPSDEUILogicParam().getLogicName()) && iPSDEUILogicNodeParam.getSrcPSDEUILogicParam().getLogicName() != iPSDEUILogicNodeParam.getSrcPSDEUILogicParam().getCodeName()) {
                    stringBuffer.append("(")
                    stringBuffer.append(iPSDEUILogicNodeParam.getSrcPSDEUILogicParam().getLogicName())
                    stringBuffer.append(")")
                }
            }
            if (StringUtils.hasLength(iPSDEUILogicNodeParam.getSrcFieldName())) {
                if (iPSDEUILogicNodeParam.getSrcPSDEUILogicParam()) {
                    stringBuffer.append(".")
                    stringBuffer.append(iPSDEUILogicNodeParam.getSrcFieldName())
//                    IPSDataEntity iPSDataEntity = iPSDEUILogicNodeParam.getSrcPSDEUILogicParam().getParamPSDataEntity()
                    IPSDataEntity iPSDataEntity = null
                    if (iPSDataEntity) {
                        IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDEUILogicNodeParam.getSrcFieldName()) })
                        if (iPSDEField) {
                            stringBuffer.append("(")
                            stringBuffer.append(iPSDEField.getLogicName())
                            stringBuffer.append(")")
                        }
                    }
                } else {
                    stringBuffer.append(iPSDEUILogicNodeParam.getSrcFieldName())
                }
            }
            return stringBuffer.toString()
        }

        if (PSModelEnums.DELogicParamValueType.NONEVALUE.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.NONEVALUE.text
        }

        if (PSModelEnums.DELogicParamValueType.NULLVALUE.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.NULLVALUE.text
        }

        if (PSModelEnums.DELogicParamValueType.SRCVALUE.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return iPSDEUILogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.SESSION.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.SESSION.text + "." + iPSDEUILogicNodeParam.getSrcFieldName()
        }


        if (PSModelEnums.DELogicParamValueType.APPLICATION.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.APPLICATION.text + "." + iPSDEUILogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.EXPRESSION.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.EXPRESSION.text + " " + iPSDEUILogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.COUNT.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.EXPRESSION.text + " " + iPSDEUILogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.AGGREGATION.value.equals(iPSDEUILogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.EXPRESSION.text + " " + iPSDEUILogicNodeParam.getSrcValue()
        }
        return iPSDEUILogicNodeParam.getName()
    }

    public static String getDstInfo(IPSDEUILogicNodeParam iPSDEUILogicNodeParam) {
        StringBuffer stringBuffer = new StringBuffer()
        if (iPSDEUILogicNodeParam.getDstPSDEUILogicParam()) {
            stringBuffer.append(iPSDEUILogicNodeParam.getDstPSDEUILogicParam().getCodeName())
            if (StringUtils.hasLength(iPSDEUILogicNodeParam.getDstPSDEUILogicParam().getLogicName()) && iPSDEUILogicNodeParam.getDstPSDEUILogicParam().getLogicName() != iPSDEUILogicNodeParam.getDstPSDEUILogicParam().getCodeName()) {
                stringBuffer.append("(")
                stringBuffer.append(iPSDEUILogicNodeParam.getDstPSDEUILogicParam().getLogicName())
                stringBuffer.append(")")
            }
        }
        if (StringUtils.hasLength(iPSDEUILogicNodeParam.getDstFieldName())) {
            if (iPSDEUILogicNodeParam.getDstPSDEUILogicParam()) {
                stringBuffer.append(".")
                stringBuffer.append(iPSDEUILogicNodeParam.getDstFieldName())
//                IPSDataEntity iPSDataEntity = iPSDEUILogicNodeParam.getSrcPSDEUILogicParam().getParamPSDataEntity()
                IPSDataEntity iPSDataEntity = null
                if (iPSDataEntity) {
                    IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDEUILogicNodeParam.getDstFieldName()) })
                    if (iPSDEField) {
                        stringBuffer.append("(")
                        stringBuffer.append(iPSDEField.getLogicName())
                        stringBuffer.append(")")
                    }
                }
            } else {
                stringBuffer.append(iPSDEUILogicNodeParam.getDstFieldName())
            }
        }
        return stringBuffer.toString()
    }

}
