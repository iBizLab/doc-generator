package cn.ibizlab.codegen.groovy.support


import net.ibizsys.model.dataentity.logic.*

public class PSDEUILogicExtension {

    static String STATE_FORMAT = "state \"%s\" as %s %s [[\$./%s#%s {%s}]]"

    /**
     * 有条件的 连接
     * @param iPSDEUILogic
     * @return
     */
    public static List getConditionLinks(IPSDEUILogic iPSDEUILogic) {
        List links = new ArrayList()
        Set<String> flags = new HashSet<>()
        reduceConditionLink(links, iPSDEUILogic.getStartPSDEUILogicNode(), flags)
        return links
    }

    public static void reduceConditionLink(List links, IPSDEUILogicNode iPSDEUILogicNode, Set<String> flags) {
        if (iPSDEUILogicNode == null || flags.contains(iPSDEUILogicNode.getId())) {
            return
        }
        flags.add(iPSDEUILogicNode.getId())
        if (iPSDEUILogicNode.getPSDEUILogicLinks()) {
            for (IPSDEUILogicLink iPSDEUILogicLink : iPSDEUILogicNode.getPSDEUILogicLinks()) {
                if (iPSDEUILogicLink.getPSDEUILogicLinkGroupCond())
                    links.add(iPSDEUILogicLink)
                reduceConditionLink(links, iPSDEUILogicLink.getDstPSDEUILogicNode(), flags)
            }
        }
    }

    public static String getMdUmlInfo(IPSDEUILogic iPSDEUILogic) {
        StringBuffer uml = new StringBuffer()
        uml.append("hide empty description")
        uml.append("\n")

        iPSDEUILogic.getPSDEUILogicNodes().each { node ->
//            if (!(node instanceof IPSDEUIBeginLogic)) {
            uml.append(node.mdUmlStateInfo)
            uml.append("\n")
//            }
        }

        //循环子调用
        iPSDEUILogic.getPSDEUILogicNodes().each { node ->
            if (node instanceof IPSDEUILoopSubCallLogic) {
                String replace = uml.toString().replace("\n" + node.mdUmlStateInfo, "")
                uml.setLength(0)
                uml.append(replace)
                uml.append(node.mdUmlStateInfo)
                uml.append(" #green {")
                uml.append("\n")
                if (node.getPSDEUILogicLinks()) {
                    node.getPSDEUILogicLinks().forEach { link ->
                        if (link.isSubCallLink())
                            reduceLoopSubNodeInfo(uml, link.getDstPSDEUILogicNode())
                    }
                }
                uml.append("}\n")
            }
        }


        uml.append("\n")
        uml.append("\n")
        Set<String> flags = new HashSet<>()
        reduceMdUmlInfo(uml, iPSDEUILogic.getStartPSDEUILogicNode(), flags)
        return uml.toString()
    }


    public static String getMdUmlStateInfo(IPSDEUILogicNode iPSDEUILogicNode) {
        IPSDEUILogic iPSDEUILogic = iPSDEUILogicNode.getParentPSModelObject(IPSDEUILogic.class)
        try {
            String strNodeType = "";
            if (iPSDEUILogicNode instanceof IPSDEUIBeginLogic)
                strNodeType = "<<start>>"
            if (iPSDEUILogicNode instanceof IPSDEUIEndLogic)
                strNodeType = "<<end>>"
            return String.format(STATE_FORMAT, iPSDEUILogicNode.getName().replaceAll('”|"', "'"), iPSDEUILogicNode.getCodeName(),
                    strNodeType,
                    iPSDEUILogic.getCodeName(), iPSDEUILogicNode.getCodeName().toLowerCase(), iPSDEUILogicNode.getName())
        } catch (Exception e) {
            println iPSDEUILogicNode
        }
    }

    public static void reduceLoopSubNodeInfo(StringBuffer uml, IPSDEUILogicNode iPSDEUILogicNode) {
        if (iPSDEUILogicNode instanceof IPSDEEndLogic || iPSDEUILogicNode instanceof IPSDELoopSubCallLogic)
            return
        String replace = uml.toString().replace("\n" + iPSDEUILogicNode.mdUmlStateInfo, "")
        uml.setLength(0)
        uml.append(replace)
        uml.append(iPSDEUILogicNode.mdUmlStateInfo)
        uml.append("\n")

        if (iPSDEUILogicNode.getPSDEUILogicLinks()) {
            iPSDEUILogicNode.getPSDEUILogicLinks().forEach { link ->
                reduceLoopSubNodeInfo(uml, link.getDstPSDEUILogicNode())
            }
        }
    }

    public static void reduceMdUmlInfo(StringBuffer uml, IPSDEUILogicNode iPSDEUILogicNode, Set<String> flags) {
        if (flags.contains(iPSDEUILogicNode.getId()))
            return
        flags.add(iPSDEUILogicNode.getId())
        if (iPSDEUILogicNode.getPSDEUILogicLinks()) {
            for (IPSDEUILogicLink iPSDEUILogicLink : iPSDEUILogicNode.getPSDEUILogicLinks()) {
                String link = String.format("%s -%s-> %s",
                        iPSDEUILogicLink.getSrcPSDEUILogicNode().getCodeName(),
                        (iPSDEUILogicLink.isCatchLink()) ? "[#red]" : "",
                        iPSDEUILogicLink.getDstPSDEUILogicNode().getCodeName())
                if (!uml.contains(link)) {
                    uml.append(link)

                    if (iPSDEUILogicLink.getPSDEUILogicLinkGroupCond() && (iPSDEUILogicLink.getPSDEUILogicLinkGroupCond().info)) {
                        uml.append(String.format(" : [[\$./%s#%s{%s} %s]]",
                                iPSDEUILogicNode.getParentPSModelObject().getCodeName(),
                                String.format("%s-%s", iPSDEUILogicLink.getSrcPSDEUILogicNode().getCodeName(), iPSDEUILogicLink.getDstPSDEUILogicNode().getCodeName()).toLowerCase(),
                                iPSDEUILogicLink.getName(),
                                iPSDEUILogicLink.getName()))
                    }
                    uml.append("\n")
                }
                reduceMdUmlInfo(uml, iPSDEUILogicLink.getDstPSDEUILogicNode(), flags)
            }

        }
    }

}
