package cn.ibizlab.codegen.groovy.support

import com.google.common.base.CaseFormat
import net.ibizsys.model.IPSModelObject
import net.ibizsys.model.dataentity.service.IPSDEMethodDTO
import net.ibizsys.model.res.IPSSysSFPlugin
import net.ibizsys.model.res.IPSSysSFPluginSupportable
import org.springframework.util.StringUtils

public class PSModelExtension {

    /**
     * 获取插件
     * @param iPSModelObject
     * @return
     */
    public static IPSSysSFPlugin getPlugin(IPSModelObject iPSModelObject) {
        // IPSSysSFPluginSupportable
        if (iPSModelObject instanceof IPSSysSFPluginSupportable){
            return iPSModelObject.getPSSysSFPlugin()
        }
        try {
            def method = iPSModelObject.getClass().getDeclaredMethod("getPSSysSFPlugin")
            if (method) {
                return method.invoke(iPSModelObject)
            }
        } catch (Exception e) {
            return null
        }
    }

    public static String getDisplayName(IPSModelObject iPSModelObject) {
        getDisplayName(iPSModelObject, false)
    }

    public static String getDisplayName(IPSModelObject iPSModelObject, boolean useCodeName) {
        String logicName = iPSModelObject.getLogicName()
        if(logicName == '比较内容')
            print ''
        String name = iPSModelObject.getName()
        String codeName = iPSModelObject.getCodeName()
        if (!logicName) {
            logicName = name
        }
        if (!logicName) {
            logicName = codeName
        }
        if (!name) {
            name = codeName
        }

        if (useCodeName) {
            name = codeName
        }

        if (!(logicName) && !(name))
            return codeName
        if ((logicName) && !(name))
            return logicName
        if (!(logicName) && (name))
            return codeName
        if ((logicName) && (name)) {
            if (logicName == name)
                return logicName
            else
                return String.format("%s(%s)", logicName, name)
        }
    }

    public static String getRealCodeName(IPSModelObject iPSModelObject) {
        String strCodeName = iPSModelObject.getCodeName();
        if (!StringUtils.hasLength(strCodeName)) {
            strCodeName = iPSModelObject.getName();
        }
        if (StringUtils.hasLength(strCodeName)) {
            if (iPSModelObject instanceof IPSDEMethodDTO) {
                IPSDEMethodDTO iPSDEMethodDTO = (IPSDEMethodDTO) iPSModelObject;
                if (iPSDEMethodDTO.getType().equals("DEFILTER")) {
                    strCodeName = strCodeName.replace("FilterDTO", "");
                } else {
                    strCodeName = strCodeName.replace("DTO", "");
                }
                strCodeName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, strCodeName);

                if (iPSDEMethodDTO.getType().equals("DEFILTER")) {
                    strCodeName = strCodeName + "FilterDTO";
                } else {
                    strCodeName = strCodeName + "DTO";
                }
            } else {
                strCodeName = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, strCodeName);
            }
        }
        return strCodeName;
    }


}
