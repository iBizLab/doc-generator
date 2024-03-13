package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.defield.IPSDEField
import net.ibizsys.model.dataentity.logic.IPSDELogic
import net.ibizsys.model.dataentity.logic.IPSDELogicLinkCond
import net.ibizsys.model.dataentity.logic.IPSDELogicLinkGroupCond
import net.ibizsys.model.dataentity.logic.IPSDELogicLinkSingleCond
import net.ibizsys.model.dataentity.logic.IPSDEUILogic
import net.ibizsys.model.dataentity.logic.IPSDEUILogicLinkCond
import net.ibizsys.model.dataentity.logic.IPSDEUILogicLinkGroupCond
import net.ibizsys.model.dataentity.logic.IPSDEUILogicLinkSingleCond
import net.ibizsys.model.util.Conditions
import org.springframework.util.StringUtils

public class PSDEUILogicLinkGroupCondExtension {

    public static String getInfo(IPSDEUILogicLinkGroupCond iPSDEUILogicLinkGroupCond) {
        StringBuffer info = new StringBuffer()
        IPSDEUILogic iPSDEUILogic = iPSDEUILogicLinkGroupCond.getParentPSModelObject(IPSDEUILogic.class)
        List<IPSDEUILogicLinkCond> conds = iPSDEUILogicLinkGroupCond.getPSDEUILogicLinkConds()
        if (conds) {
            for (int i = 0; i < iPSDEUILogicLinkGroupCond.getPSDEUILogicLinkConds().size(); i++) {
                if (i > 0)
                    info.append(" AND ");
                getCloudGroupCondInfo(info, iPSDEUILogic, conds.get(i));
            }
        }

        return info.toString()
    }

    public static void getCloudGroupCondInfo(StringBuffer info, IPSDEUILogic iPSDEUILogic, IPSDEUILogicLinkCond iPSDEUILogicLinkCond) {
        if (iPSDEUILogicLinkCond instanceof IPSDEUILogicLinkSingleCond) {
            IPSDEUILogicLinkSingleCond iPSDEUILogicLinkSingleCond = (IPSDEUILogicLinkSingleCond) iPSDEUILogicLinkCond
            StringBuffer fieldInfo = new StringBuffer()
            if (iPSDEUILogicLinkSingleCond.getDstLogicParam()) {
                fieldInfo.append(iPSDEUILogicLinkSingleCond.getDstLogicParam().getCodeName())
                if (StringUtils.hasLength(iPSDEUILogicLinkSingleCond.getDstLogicParam().getLogicName())) {
                    fieldInfo.append("(")
                    fieldInfo.append(iPSDEUILogicLinkSingleCond.getDstLogicParam().getLogicName())
                    fieldInfo.append(")")
                }
            }
            if (StringUtils.hasLength(iPSDEUILogicLinkSingleCond.getDstFieldName())) {
                fieldInfo.append(".")
                fieldInfo.append(iPSDEUILogicLinkSingleCond.getDstFieldName())
//                IPSDataEntity iPSDataEntity = iPSDEUILogicLinkSingleCond.getDstLogicParam().getParamPSDataEntity()
                IPSDataEntity iPSDataEntity = null
                if (iPSDataEntity) {
                    IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDEUILogicLinkSingleCond.getDstFieldName()) })
                    if (iPSDEField) {
                        fieldInfo.append("(")
                        fieldInfo.append(iPSDEField.getLogicName())
                        fieldInfo.append(")")
                    }
                }
            }
            String fieldValue = ""
            String fieldCondInfo = ""
            if (Conditions.ISNULL.equals(iPSDEUILogicLinkSingleCond.getCondOP()) || Conditions.ISNOTNULL.equals(iPSDEUILogicLinkSingleCond.getCondOP())) {
                fieldCondInfo = String.format("```%s``` %s", fieldInfo, iPSDEUILogicLinkSingleCond.getCondOP())
            } else {
                if (PSModelEnums.DELLCondParamType.ENTITYFIELD.value.equals(iPSDEUILogicLinkSingleCond.getParamType())) {
                    if (iPSDEUILogicLinkSingleCond.getSrcLogicParam()) {
                        fieldValue += iPSDEUILogicLinkSingleCond.getSrcLogicParam().getLogicName()
                    }
                    if (StringUtils.hasLength(iPSDEUILogicLinkSingleCond.getParamValue())) {
                        if (iPSDEUILogicLinkSingleCond.getSrcLogicParam()) {
                            fieldValue += "."
//                            IPSDataEntity iPSDataEntity = iPSDEUILogicLinkSingleCond.getSrcLogicParam().getParamPSDataEntity()
                            IPSDataEntity iPSDataEntity = null
                            if (iPSDataEntity) {
                                IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDEUILogicLinkSingleCond.getDstFieldName()) })
                                if (iPSDEField) {
                                    fieldValue += iPSDEField.getLogicName()
                                } else {
                                    fieldValue += iPSDEUILogicLinkSingleCond.getDstFieldName()
                                }
                            } else {
                                fieldValue += iPSDEUILogicLinkSingleCond.getDstFieldName()
                            }
                        } else {
                            fieldValue += iPSDEUILogicLinkSingleCond.getDstFieldName()
                        }
                    }
                    fieldCondInfo = String.format("```%s``` %s ```%s```", fieldInfo, iPSDEUILogicLinkSingleCond.getCondOP(), fieldValue)
                } else if (PSModelEnums.DELLCondParamType.SRCDLPARAM.value.equals(iPSDEUILogicLinkSingleCond.getParamType())) {

                } else if (PSModelEnums.DELLCondParamType.SRCENTITYFIELD.value.equals(iPSDEUILogicLinkSingleCond.getParamType())) {

                } else if (PSModelEnums.DELLCondParamType.CURTIME.value.equals(iPSDEUILogicLinkSingleCond.getParamType())) {
                    fieldCondInfo = String.format("```%s``` %s ```%s```", fieldInfo, iPSDEUILogicLinkSingleCond.getCondOP(), "当前时间")
                } else {
                    fieldCondInfo = String.format("```%s``` %s ```%s```", fieldInfo, iPSDEUILogicLinkSingleCond.getCondOP(), iPSDEUILogicLinkSingleCond.getParamValue())
                }

            }

            info.append(fieldCondInfo)
        } else if (iPSDEUILogicLinkCond instanceof IPSDEUILogicLinkGroupCond) {
            IPSDEUILogicLinkGroupCond iPSDEUILogicLinkGroupCond = (IPSDEUILogicLinkGroupCond) iPSDEUILogicLinkCond
            List<IPSDEUILogicLinkCond> conds = iPSDEUILogicLinkGroupCond.getPSDEUILogicLinkConds()
            if (conds) {
                if (iPSDEUILogicLinkGroupCond.isNotMode()) {
                    info.append("!");
                }
                info.append("(");
                for (int i = 0; i < iPSDEUILogicLinkGroupCond.getPSDEUILogicLinkConds().size(); i++) {
                    if (i > 0)
                        info.append(String.format(" %s ", iPSDEUILogicLinkGroupCond.getGroupOP()))
                    getCloudGroupCondInfo(info, iPSDEUILogic, conds.get(i))
                }
                info.append(")");
            }
        }
    }

}
