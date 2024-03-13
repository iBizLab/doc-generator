package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.service.IPSDEMethodDTO

class PSDEMethodDTOExtension {

    static String getBaseClass(IPSDEMethodDTO iPSDEMethodDTO) {
        String baseClass = "EntityDTOBase"
        if (iPSDEMethodDTO.getType() == PSModelEnums.DEMethodDTOType.DEFAULT.value) {
            baseClass = "EntityDTOBase"
        } else if (iPSDEMethodDTO.getType() == PSModelEnums.DEMethodDTOType.DEFILTER.value
                || iPSDEMethodDTO.getType() == PSModelEnums.DEMethodDTOType.DEDATASETINPUT.value) {
            baseClass = "SearchContextDTOBase"
        }
        return baseClass
    }

}
