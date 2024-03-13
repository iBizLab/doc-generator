package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.defield.IPSDEField
import net.ibizsys.model.dataentity.logic.IPSDELogic
import net.ibizsys.model.dataentity.logic.IPSDELogicLinkCond
import net.ibizsys.model.dataentity.logic.IPSDELogicLinkGroupCond
import net.ibizsys.model.dataentity.logic.IPSDELogicLinkSingleCond
import net.ibizsys.model.util.Conditions
import org.springframework.util.StringUtils

public class PSDELogicLinkGroupCondExtension {

    public static String getInfo(IPSDELogicLinkGroupCond iPSDELogicLinkGroupCond) {
        StringBuffer info = new StringBuffer()
        IPSDELogic iPSDELogic = iPSDELogicLinkGroupCond.getParentPSModelObject(IPSDELogic.class)
        List<IPSDELogicLinkCond> conds = iPSDELogicLinkGroupCond.getPSDELogicLinkConds()
        if (conds) {
            for (int i = 0; i < iPSDELogicLinkGroupCond.getPSDELogicLinkConds().size(); i++) {
                if (i > 0)
                    info.append(" AND ");
                getCloudGroupCondInfo(info, iPSDELogic, conds.get(i));
            }
        }

        return info.toString()
    }

    public static void getCloudGroupCondInfo(StringBuffer info, IPSDELogic iPSDELogic, IPSDELogicLinkCond iPSDELogicLinkCond) {
        if (iPSDELogicLinkCond instanceof IPSDELogicLinkSingleCond) {
            IPSDELogicLinkSingleCond iPSDELogicLinkSingleCond = (IPSDELogicLinkSingleCond) iPSDELogicLinkCond
            StringBuffer fieldInfo = new StringBuffer()
            if (iPSDELogicLinkSingleCond.getDstLogicParam()) {
                fieldInfo.append(iPSDELogicLinkSingleCond.getDstLogicParam().getCodeName())
                if (StringUtils.hasLength(iPSDELogicLinkSingleCond.getDstLogicParam().getLogicName())) {
                    fieldInfo.append("(")
                    fieldInfo.append(iPSDELogicLinkSingleCond.getDstLogicParam().getLogicName())
                    fieldInfo.append(")")
                }
            }
            if (StringUtils.hasLength(iPSDELogicLinkSingleCond.getDstFieldName())) {
                fieldInfo.append(".")
                fieldInfo.append(iPSDELogicLinkSingleCond.getDstFieldName())
                IPSDataEntity iPSDataEntity = iPSDELogicLinkSingleCond.getDstLogicParam().getParamPSDataEntity()
                if (iPSDataEntity) {
                    IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDELogicLinkSingleCond.getDstFieldName()) })
                    if (iPSDEField) {
                        fieldInfo.append("(")
                        fieldInfo.append(iPSDEField.getLogicName())
                        fieldInfo.append(")")
                    }
                }
            }
            String fieldValue = ""
            String fieldCondInfo = ""
            if (Conditions.ISNULL.equals(iPSDELogicLinkSingleCond.getCondOP()) || Conditions.ISNOTNULL.equals(iPSDELogicLinkSingleCond.getCondOP())) {
                fieldCondInfo = String.format("`%s` %s", fieldInfo, iPSDELogicLinkSingleCond.getCondOP())
            } else {
                if (PSModelEnums.DELLCondParamType.ENTITYFIELD.value.equals(iPSDELogicLinkSingleCond.getParamType())) {
                    if (iPSDELogicLinkSingleCond.getSrcLogicParam()) {
                        fieldValue += iPSDELogicLinkSingleCond.getSrcLogicParam().getLogicName()
                    }
                    if (StringUtils.hasLength(iPSDELogicLinkSingleCond.getParamValue())) {
                        if (iPSDELogicLinkSingleCond.getSrcLogicParam()) {
                            fieldValue += "."
                            IPSDataEntity iPSDataEntity = iPSDELogicLinkSingleCond.getSrcLogicParam().getParamPSDataEntity()
                            if (iPSDataEntity) {
                                IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDELogicLinkSingleCond.getDstFieldName()) })
                                if (iPSDEField) {
                                    fieldValue += iPSDEField.getLogicName()
                                } else {
                                    fieldValue += iPSDELogicLinkSingleCond.getDstFieldName()
                                }
                            } else {
                                fieldValue += iPSDELogicLinkSingleCond.getDstFieldName()
                            }
                        } else {
                            fieldValue += iPSDELogicLinkSingleCond.getDstFieldName()
                        }
                    }
                    fieldCondInfo = String.format("`%s` %s `%s`", fieldInfo, iPSDELogicLinkSingleCond.getCondOP(), fieldValue)
                } else if (PSModelEnums.DELLCondParamType.SRCDLPARAM.value.equals(iPSDELogicLinkSingleCond.getParamType())) {

                } else if (PSModelEnums.DELLCondParamType.SRCENTITYFIELD.value.equals(iPSDELogicLinkSingleCond.getParamType())) {

                } else if (PSModelEnums.DELLCondParamType.CURTIME.value.equals(iPSDELogicLinkSingleCond.getParamType())) {
                    fieldCondInfo = String.format("`%s` %s `%s`", fieldInfo, iPSDELogicLinkSingleCond.getCondOP(), "当前时间")
                } else {
                    fieldCondInfo = String.format("`%s` %s `%s`", fieldInfo, iPSDELogicLinkSingleCond.getCondOP(), iPSDELogicLinkSingleCond.getParamValue())
                }

            }

            info.append(fieldCondInfo)
        } else if (iPSDELogicLinkCond instanceof IPSDELogicLinkGroupCond) {
            IPSDELogicLinkGroupCond iPSDELogicLinkGroupCond = (IPSDELogicLinkGroupCond) iPSDELogicLinkCond
            List<IPSDELogicLinkCond> conds = iPSDELogicLinkGroupCond.getPSDELogicLinkConds()
            if (conds) {
                if (iPSDELogicLinkGroupCond.isNotMode()) {
                    info.append("!");
                }
                info.append("(");
                for (int i = 0; i < iPSDELogicLinkGroupCond.getPSDELogicLinkConds().size(); i++) {
                    if (i > 0)
                        info.append(String.format(" %s ", iPSDELogicLinkGroupCond.getGroupOP()))
                    getCloudGroupCondInfo(info, iPSDELogic, conds.get(i))
                }
                info.append(")");
            }
        }
    }

}
