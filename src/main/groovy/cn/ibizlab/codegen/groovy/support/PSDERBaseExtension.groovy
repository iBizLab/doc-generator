package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.dataentity.der.IPSDERBase

public class PSDERBaseExtension {

    public static String getDERTypeLink(IPSDERBase iPSDERBase) {
        if (PSModelEnums.DERType.DER1N.value.equals(iPSDERBase.getDERType())) {
            return "-->"
        } else if (PSModelEnums.DERType.DER11.value.equals(iPSDERBase.getDERType())) {
            return "<-->"
        } else if (PSModelEnums.DERType.DERINHERIT.value.equals(iPSDERBase.getDERType())) {
            return ".[#8A2BE2].|>"
        } else if (PSModelEnums.DERType.DERINDEX.value.equals(iPSDERBase.getDERType())) {
            return ".[#deeppink].>"
        }
        return "--"
    }


}
