package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.action.IPSDEActionInput

class PSDEActionInputExtension {

    static String getInputParams(IPSDEActionInput iPSDEActionInput) {
        String ret = ""
        if (iPSDEActionInput.getType() == PSModelEnums.DEMethodInputType.KEYFIELD.value) {
            ret = "key"
        } else if (iPSDEActionInput.getType() == PSModelEnums.DEMethodInputType.KEYFIELDS.value) {
            ret = "keys"
        } else if (iPSDEActionInput.getType() == PSModelEnums.DEMethodInputType.DTO.value) {
            ret = "dto"
        } else if (iPSDEActionInput.getType() == PSModelEnums.DEMethodInputType.DTOS.value) {
            ret = "dtos"
        }
        return ret;
    }

    static String getInput(IPSDEActionInput iPSDEActionInput) {
        return String.format("%s %s", PSDEMethodInputExtension.getJavaType(iPSDEActionInput), getInputParams(iPSDEActionInput));
    }

}
