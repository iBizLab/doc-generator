package cn.ibizlab.codegen.groovy.support

import cn.ibizlab.codegen.groovy.util.JavaTypeUtil
import net.ibizsys.codegen.groovy.util.GroovyUtils
import net.ibizsys.model.PSModelEnums;
import net.ibizsys.model.PSModelEnums.DEMethodDTOFieldType;
import net.ibizsys.model.dataentity.service.IPSDEMethodDTOField;


public class PSDEMethodDTOFieldExtension {

	public static String getJavaType(IPSDEMethodDTOField iPSDEMethodDTOField) {
		String ret = "Object"

		DEMethodDTOFieldType type = DEMethodDTOFieldType.from(iPSDEMethodDTOField.getType());
		if(DEMethodDTOFieldType.DTOS == type) {
			if(iPSDEMethodDTOField.getRefPSDEMethodDTO())
				ret = "List<" + iPSDEMethodDTOField.getRefPSDEMethodDTO().getRealCodeName() + ">"
			else
				ret = "List<IEntityDTO>"
		}

		if(DEMethodDTOFieldType.DTO == type) {
			if(iPSDEMethodDTOField.getRefPSDEMethodDTO())
				ret = iPSDEMethodDTOField.getRefPSDEMethodDTO().getRealCodeName()
			else
				ret = "IEntityDTO"
		}

		if(DEMethodDTOFieldType.SIMPLE == type || DEMethodDTOFieldType.SIMPLES == type ) {
			String strJavaType = JavaTypeUtil.getJavaType(iPSDEMethodDTOField.getStdDataType());
			if(DEMethodDTOFieldType.SIMPLES == type) {
				if("Object".equals(strJavaType)) {
					return "List";
				}
				return String.format("List<%s>", strJavaType);
			}
			return strJavaType;
		}

		return ret
	}
	

}
