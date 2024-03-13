package cn.ibizlab.codegen.groovy.support


import net.ibizsys.model.dataentity.logic.*

public class PSDELogicExtension {

    static String STATE_FORMAT = "state \"%s\" as %s %s [[\$./%s#%s {\"%s\"}]]"

    /**
     * 有条件的 连接
     * @param iPSDELogic
     * @return
     */
    public static List<IPSDELogicLink> getConditionLinks(IPSDELogic iPSDELogic) {
        List<IPSDELogicLink> links = new ArrayList()
        Set<String> flags = new HashSet<>()
        reduceConditionLink(links, iPSDELogic.getStartPSDELogicNode(), flags)
        return links
    }

    public static void reduceConditionLink(List links, IPSDELogicNode iPSDELogicNode, Set<String> flags) {
        if (iPSDELogicNode == null || flags.contains(iPSDELogicNode.getId()))
            return
        flags.add(iPSDELogicNode.getId())
        if (iPSDELogicNode.getPSDELogicLinks()) {
            for (IPSDELogicLink iPSDELogicLink : iPSDELogicNode.getPSDELogicLinks()) {
                if (iPSDELogicLink.getPSDELogicLinkGroupCond())
                    links.add(iPSDELogicLink)
                reduceConditionLink(links, iPSDELogicLink.getDstPSDELogicNode(), flags)
            }
        }
    }

    public static String getMdUmlInfo(IPSDELogic iPSDELogic) {
        StringBuffer uml = new StringBuffer()
        uml.append("hide empty description")
        uml.append("\n")

        iPSDELogic.getPSDELogicNodes().each { node ->
//            if (!(node instanceof IPSDEBeginLogic)) {
            uml.append(node.mdUmlStateInfo)
            uml.append("\n")
//            }
        }

        //循环子调用
        iPSDELogic.getPSDELogicNodes().each { node ->
            if (node instanceof IPSDELoopSubCallLogic) {
                String replace = uml.toString().replace("\n" + node.mdUmlStateInfo, "")
                uml.setLength(0)
                uml.append(replace)
                uml.append(node.mdUmlStateInfo)
                uml.append(" #green {")
                uml.append("\n")
                if (node.getPSDELogicLinks()) {
                    node.getPSDELogicLinks().forEach { link ->
                        if (link.isSubCallLink())
                            reduceLoopSubNodeInfo(uml, link.getDstPSDELogicNode())
                    }
                }
                uml.append("}\n")
            }
        }


        uml.append("\n")
        uml.append("\n")
        Set<String> flags = new HashSet<>()
        reduceMdUmlInfo(uml, iPSDELogic.getStartPSDELogicNode(), flags)
        return uml.toString()
    }


    public static String getMdUmlStateInfo(IPSDELogicNode iPSDELogicNode) {
        IPSDELogic iPSDELogic = iPSDELogicNode.getParentPSModelObject(IPSDELogic.class)
        try {
            String strNodeType = "";
            if (iPSDELogicNode instanceof IPSDEBeginLogic)
                strNodeType = "<<start>>"
            if (iPSDELogicNode instanceof IPSDEEndLogic)
                strNodeType = "<<end>>"
            return String.format(STATE_FORMAT, iPSDELogicNode.getName().replaceAll('”|"', "'"), iPSDELogicNode.getCodeName(),
                    strNodeType,
                    iPSDELogic.getCodeName(), iPSDELogicNode.getCodeName().toLowerCase(), iPSDELogicNode.getName())
        } catch (Exception e) {

        }
    }

    public static void reduceLoopSubNodeInfo(StringBuffer uml, IPSDELogicNode iPSDELogicNode) {
        if (iPSDELogicNode instanceof IPSDEEndLogic || iPSDELogicNode instanceof IPSDELoopSubCallLogic)
            return
        String replace = uml.toString().replace("\n" + iPSDELogicNode.mdUmlStateInfo, "")
        uml.setLength(0)
        uml.append(replace)
        uml.append(iPSDELogicNode.mdUmlStateInfo)
        uml.append("\n")

        if (iPSDELogicNode.getPSDELogicLinks()) {
            iPSDELogicNode.getPSDELogicLinks().forEach { link ->
                reduceLoopSubNodeInfo(uml, link.getDstPSDELogicNode())
            }
        }
    }

    public static void reduceMdUmlInfo(StringBuffer uml, IPSDELogicNode iPSDELogicNode, Set<String> flags) {
        if (iPSDELogicNode == null || flags.contains(iPSDELogicNode.getId()))
            return
        flags.add(iPSDELogicNode.getId())
        if (iPSDELogicNode.getPSDELogicLinks()) {
            for (IPSDELogicLink iPSDELogicLink : iPSDELogicNode.getPSDELogicLinks()) {
                String link = String.format("%s -%s-> %s",
                        iPSDELogicLink.getSrcPSDELogicNode().getCodeName(),
                        (iPSDELogicLink.isCatchLink()) ? "[#red]" : "",
                        iPSDELogicLink.getDstPSDELogicNode().getCodeName())
                if (!uml.contains(link)) {
                    uml.append(link)

                    if (iPSDELogicLink.getPSDELogicLinkGroupCond() && (iPSDELogicLink.getPSDELogicLinkGroupCond().info)) {
                        uml.append(String.format(" : [[\$./%s#%s{%s} %s]]",
                                iPSDELogicNode.getParentPSModelObject().getCodeName(),
                                String.format("%s-%s", iPSDELogicLink.getSrcPSDELogicNode().getCodeName(), iPSDELogicLink.getDstPSDELogicNode().getCodeName()).toLowerCase(),
                                iPSDELogicLink.getName(),
                                iPSDELogicLink.getName()))
                    }
                    uml.append("\n")
                }
                reduceMdUmlInfo(uml, iPSDELogicLink.getDstPSDELogicNode(), flags)
            }

        }
    }

}
