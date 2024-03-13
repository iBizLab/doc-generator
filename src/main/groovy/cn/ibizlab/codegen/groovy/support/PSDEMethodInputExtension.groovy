package cn.ibizlab.codegen.groovy.support

import cn.ibizlab.codegen.groovy.util.JavaTypeUtil;
import net.ibizsys.model.PSModelEnums.DEMethodInputType;
import net.ibizsys.model.dataentity.IPSDataEntity;
import net.ibizsys.model.dataentity.action.IPSDEActionInput;
import net.ibizsys.model.dataentity.ds.IPSDEDataSetInput;
import net.ibizsys.model.dataentity.service.IPSDEMethodInput;

public class PSDEMethodInputExtension {

	public static String getJavaType(IPSDEMethodInput iPSDEMethodInput){
		
		DEMethodInputType returnType = DEMethodInputType.from(iPSDEMethodInput.getType());
		if(DEMethodInputType.NONE == returnType) {
			return "";
		}
		
		if(DEMethodInputType.DTOS == returnType) {
			
			if(iPSDEMethodInput instanceof IPSDEActionInput) {
				IPSDEActionInput iPSDEActionInput = (IPSDEActionInput)iPSDEMethodInput;
				return String.format("List<%s>", iPSDEActionInput.getPSDEMethodDTOMust().getRealCodeName());
			}
						
			return "List";
		}
				
		if(DEMethodInputType.DTO == returnType) {
			
			if(iPSDEMethodInput instanceof IPSDEActionInput) {
				IPSDEActionInput iPSDEActionInput = (IPSDEActionInput)iPSDEMethodInput;
				return iPSDEActionInput.getPSDEMethodDTOMust().getRealCodeName();
			}
			
			if(iPSDEMethodInput instanceof IPSDEDataSetInput) {
				IPSDEDataSetInput iPSDEDataSetInput = (IPSDEDataSetInput)iPSDEMethodInput;
				return iPSDEDataSetInput.getPSDEFilterDTOMust().getRealCodeName();
			}
			
			return "Object";
		}
		
		if(DEMethodInputType.FILTER == returnType) {
			
			if(iPSDEMethodInput instanceof IPSDEDataSetInput) {
				IPSDEDataSetInput iPSDEDataSetInput = (IPSDEDataSetInput)iPSDEMethodInput;
				return iPSDEDataSetInput.getPSDEFilterDTOMust().getRealCodeName();
			}
			
			return "Object";
		}
		
		if(DEMethodInputType.KEYFIELD == returnType || DEMethodInputType.KEYFIELDS == returnType ) {
			
			if(iPSDEMethodInput instanceof IPSDEActionInput) {
				IPSDEActionInput iPSDEActionInput = (IPSDEActionInput)iPSDEMethodInput;
				IPSDataEntity iPSDataEntity = iPSDEActionInput.getParentPSModelObject(IPSDataEntity.class, false);
				String strJavaType = JavaTypeUtil.getJavaType(iPSDataEntity.getKeyPSDEFieldMust().getStdDataType());
				if(DEMethodInputType.KEYFIELDS == returnType) {
					if("Object".equals(strJavaType)) {
						return "List";
					}
					return String.format("List<%s>", strJavaType);
				}
				return strJavaType;
			}
			return "Object";
		}

		
		return "Object";
	}

		
}
