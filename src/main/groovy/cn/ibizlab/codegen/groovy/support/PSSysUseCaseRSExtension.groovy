package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.uml.IPSSysUseCaseRS

public class PSSysUseCaseRSExtension {

    public static Object getFromPSUMLObject(IPSSysUseCaseRS iPSSysUseCaseRS) {
        if (iPSSysUseCaseRS.getFromPSSysActor())
            return iPSSysUseCaseRS.getFromPSSysActor()
        return iPSSysUseCaseRS.getFromPSSysUseCase()
    }

    public static Object getToPSUMLObject(IPSSysUseCaseRS iPSSysUseCaseRS) {
        if (iPSSysUseCaseRS.getToPSSysActor())
            return iPSSysUseCaseRS.getToPSSysActor()
        return iPSSysUseCaseRS.getToPSSysUseCase()
    }

    public static String getRSTypeLink(IPSSysUseCaseRS iPSSysUseCaseRS) {
        if (PSModelEnums.UseCaseRSType.ASSOCIATION.value.equals(iPSSysUseCaseRS.getRSType())) {
            return "--"
        } else if (PSModelEnums.UseCaseRSType.INHERITANCE.value.equals(iPSSysUseCaseRS.getRSType())) {
            return "--|>"
        }else if (PSModelEnums.UseCaseRSType.INCLUDE.value.equals(iPSSysUseCaseRS.getRSType())) {
            return "..>"
        }else if (PSModelEnums.UseCaseRSType.EXTEND.value.equals(iPSSysUseCaseRS.getRSType())) {
            return "..>"
        }
        return "--"
    }
}
