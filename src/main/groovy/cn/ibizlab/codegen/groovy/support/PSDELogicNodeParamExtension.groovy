package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.defield.IPSDEField
import net.ibizsys.model.dataentity.logic.IPSDELogic
import net.ibizsys.model.dataentity.logic.IPSDELogicNode
import net.ibizsys.model.dataentity.logic.IPSDELogicNodeParam
import org.springframework.util.StringUtils

public class PSDELogicNodeParamExtension {

    public static String getActionInfo(IPSDELogicNodeParam iPSDELogicNodeParam) {
        String strParamAction = iPSDELogicNodeParam.getParamAction();
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

    public static String getSrcInfo(IPSDELogicNodeParam iPSDELogicNodeParam) {
        String strParamAction = iPSDELogicNodeParam.getParamAction();


        if (PSModelEnums.DELogicParamValueType.SRCDLPARAM.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            IPSDELogicNode iPSDELogicNode = iPSDELogicNodeParam.getParentPSModelObject(IPSDELogicNode.class)
            IPSDELogic iPSDELogic = iPSDELogicNode.getParentPSModelObject(IPSDELogic.class)
            StringBuffer stringBuffer = new StringBuffer()

            if (iPSDELogicNodeParam.getSrcPSDELogicParam()) {
                stringBuffer.append(iPSDELogicNodeParam.getSrcPSDELogicParam().getCodeName())
                if (StringUtils.hasLength(iPSDELogicNodeParam.getSrcPSDELogicParam().getLogicName()) && iPSDELogicNodeParam.getSrcPSDELogicParam().getLogicName() != iPSDELogicNodeParam.getSrcPSDELogicParam().getCodeName()) {
                    stringBuffer.append("(")
                    stringBuffer.append(iPSDELogicNodeParam.getSrcPSDELogicParam().getLogicName())
                    stringBuffer.append(")")
                }
            }
            if (StringUtils.hasLength(iPSDELogicNodeParam.getSrcFieldName())) {
                if (iPSDELogicNodeParam.getSrcPSDELogicParam()) {
                    stringBuffer.append(".")
                    stringBuffer.append(iPSDELogicNodeParam.getSrcFieldName())
                    IPSDataEntity iPSDataEntity = iPSDELogicNodeParam.getSrcPSDELogicParam().getParamPSDataEntity()
                    if (iPSDataEntity) {
                        IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDELogicNodeParam.getSrcFieldName()) })
                        if (iPSDEField) {
                            stringBuffer.append("(")
                            stringBuffer.append(iPSDEField.getLogicName())
                            stringBuffer.append(")")
                        }
                    }
                } else {
                    stringBuffer.append(iPSDELogicNodeParam.getSrcFieldName())
                }
            }
            return stringBuffer.toString()
        }

        if (PSModelEnums.DELogicParamValueType.NONEVALUE.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.NONEVALUE.text
        }

        if (PSModelEnums.DELogicParamValueType.NULLVALUE.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.NULLVALUE.text
        }

        if (PSModelEnums.DELogicParamValueType.SRCVALUE.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return iPSDELogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.SESSION.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.SESSION.text + "." + iPSDELogicNodeParam.getSrcFieldName()
        }


        if (PSModelEnums.DELogicParamValueType.APPLICATION.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.APPLICATION.text + "." + iPSDELogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.EXPRESSION.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.EXPRESSION.text + " " + iPSDELogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.COUNT.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.EXPRESSION.text + " " + iPSDELogicNodeParam.getSrcValue()
        }

        if (PSModelEnums.DELogicParamValueType.AGGREGATION.value.equals(iPSDELogicNodeParam.getSrcValueType())) {
            return PSModelEnums.DELogicParamValueType.EXPRESSION.text + " " + iPSDELogicNodeParam.getSrcValue()
        }
        return iPSDELogicNodeParam.getName()
    }

    public static String getDstInfo(IPSDELogicNodeParam iPSDELogicNodeParam) {
        StringBuffer stringBuffer = new StringBuffer()
        if (iPSDELogicNodeParam.getDstPSDELogicParam()) {
            stringBuffer.append(iPSDELogicNodeParam.getDstPSDELogicParam().getCodeName())
            if (StringUtils.hasLength(iPSDELogicNodeParam.getDstPSDELogicParam().getLogicName()) && iPSDELogicNodeParam.getDstPSDELogicParam().getLogicName() != iPSDELogicNodeParam.getDstPSDELogicParam().getCodeName()) {
                stringBuffer.append("(")
                stringBuffer.append(iPSDELogicNodeParam.getDstPSDELogicParam().getLogicName())
                stringBuffer.append(")")
            }
        }
        if (StringUtils.hasLength(iPSDELogicNodeParam.getDstFieldName())) {
            if (iPSDELogicNodeParam.getDstPSDELogicParam()) {
                stringBuffer.append(".")
                stringBuffer.append(iPSDELogicNodeParam.getDstFieldName())
                IPSDataEntity iPSDataEntity = iPSDELogicNodeParam.getDstPSDELogicParam().getParamPSDataEntity()
                if (iPSDataEntity) {
                    IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDELogicNodeParam.getDstFieldName()) })
                    if (iPSDEField) {
                        stringBuffer.append("(")
                        stringBuffer.append(iPSDEField.getLogicName())
                        stringBuffer.append(")")
                    }
                }
            } else {
                stringBuffer.append(iPSDELogicNodeParam.getDstFieldName())
            }
        }
        return stringBuffer.toString()
    }

}
