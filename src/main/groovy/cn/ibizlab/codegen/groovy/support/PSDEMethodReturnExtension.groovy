package cn.ibizlab.codegen.groovy.support

import cn.ibizlab.codegen.groovy.util.JavaTypeUtil;
import net.ibizsys.model.PSModelEnums.DEMethodReturnType;
import net.ibizsys.model.dataentity.action.IPSDEActionReturn;
import net.ibizsys.model.dataentity.ds.IPSDEDataSetReturn;
import net.ibizsys.model.dataentity.service.IPSDEMethodReturn;

public class PSDEMethodReturnExtension {

	public static String getJavaType(IPSDEMethodReturn iPSDEMethodReturn){
	
		DEMethodReturnType returnType = DEMethodReturnType.from(iPSDEMethodReturn.getType());
		if(returnType == DEMethodReturnType.VOID) {
			return "void";
		}
		
		if(DEMethodReturnType.DTOS == returnType) {
			
			if(iPSDEMethodReturn instanceof IPSDEActionReturn) {
				IPSDEActionReturn iPSDEActionReturn = (IPSDEActionReturn)iPSDEMethodReturn;
				return String.format("List<%s>", iPSDEActionReturn.getPSDEMethodDTOMust().getRealCodeName());
			}
			
			if(iPSDEMethodReturn instanceof IPSDEDataSetReturn) {
				IPSDEDataSetReturn iPSDEDataSetReturn = (IPSDEDataSetReturn)iPSDEMethodReturn;
				return String.format("List<%s>", iPSDEDataSetReturn.getPSDEMethodDTOMust().getRealCodeName());
			}
			
			return "List";
		}
		
		if(DEMethodReturnType.PAGE == returnType) {
				if(iPSDEMethodReturn instanceof IPSDEDataSetReturn) {
				IPSDEDataSetReturn iPSDEDataSetReturn = (IPSDEDataSetReturn)iPSDEMethodReturn;
				return String.format("Page<%s>", iPSDEDataSetReturn.getPSDEMethodDTOMust().getRealCodeName());
			}
			
			return "Page";
		}

		
		if(DEMethodReturnType.DTO == returnType) {
			
			if(iPSDEMethodReturn instanceof IPSDEActionReturn) {
				IPSDEActionReturn iPSDEActionReturn = (IPSDEActionReturn)iPSDEMethodReturn;
				return iPSDEActionReturn.getPSDEMethodDTOMust().getRealCodeName();
			}
			
			if(iPSDEMethodReturn instanceof IPSDEDataSetReturn) {
				IPSDEDataSetReturn iPSDEDataSetReturn = (IPSDEDataSetReturn)iPSDEMethodReturn;
				return iPSDEDataSetReturn.getPSDEMethodDTOMust().getRealCodeName();
			}
			
			return "Object";
		}
		
		if(DEMethodReturnType.SIMPLE == returnType || DEMethodReturnType.SIMPLES == returnType ) {
			if(iPSDEMethodReturn instanceof IPSDEActionReturn) {
				IPSDEActionReturn iPSDEActionReturn = (IPSDEActionReturn)iPSDEMethodReturn;
				String strJavaType = JavaTypeUtil.getJavaType(iPSDEActionReturn.getStdDataType());
				if(DEMethodReturnType.SIMPLES == returnType) {
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
