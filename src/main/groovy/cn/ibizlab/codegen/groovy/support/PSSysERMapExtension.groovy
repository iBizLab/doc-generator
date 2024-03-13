package cn.ibizlab.codegen.groovy.support


import net.ibizsys.model.er.IPSSysERMap

public class PSSysERMapExtension {

    public static List getDERs(IPSSysERMap iPSSysERMap) {
        List ders = new ArrayList()

        if (iPSSysERMap.getPSSysERMapNodes()) {
            iPSSysERMap.getPSSysERMapNodes().each { node ->
                if (node.getPSDataEntity()) {
                    if(node.getPSDataEntity().getMajorPSDERs()){
                        node.getPSDataEntity().getMajorPSDERs().each {der ->
                            def find =  iPSSysERMap.getPSSysERMapNodes().find {n -> n.getPSDataEntity() && n.getPSDataEntity().getId().equals(der.getMinorPSDataEntity().getId())}
                            if(find)
                                ders.add(der)
                        }
                    }
                }
            }
        }

        return ders
    }


}
