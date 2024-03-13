package cn.ibizlab.codegen.groovy.filter

import net.ibizsys.model.IPSModelObject
import net.ibizsys.model.wf.IPSWFVersion

/**
 * 模板输出判断
 */
public class OutPutFilterExtension {

    public static boolean filter(IPSModelObject iPSModelObject) {
        return true
    }

    //流程过滤
    public static boolean filter(IPSWFVersion iPSWFVersion) {
        if (iPSWFVersion.getPSWFProcesses())
            return true
        return false
    }


}
