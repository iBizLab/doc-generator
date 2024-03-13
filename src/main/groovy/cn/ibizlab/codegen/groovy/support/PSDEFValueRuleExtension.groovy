package cn.ibizlab.codegen.groovy.support

import cn.ibizlab.codegen.groovy.util.KeyValueUtils
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.defield.IPSDEField
import net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRCondition
import net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRGroupCondition
import net.ibizsys.model.dataentity.defield.valuerule.IPSDEFVRSingleCondition
import net.ibizsys.model.dataentity.defield.valuerule.IPSDEFValueRule

class PSDEFValueRuleExtension {

    static String STATE_FORMAT = "state \"%s\" as %s "
    static String STATE_WITH_LINK_FORMAT = "state \"%s\" as %s [[\$./%s#%s {\"%s\"}]]"
    static String LINK_FORMAT = "%s %s %s "

    public static String START = "start"
    public static String END = "end"


    public static String YES_LINK = "-->"
    public static String NO_LINK = "-[#red]->"


    public static IPSDEFValueRule getPSDEFValueRule(IPSDEFVRCondition iPSDEFVRCondition) {
        Object parent = iPSDEFVRCondition.getParentPSModelObject()
        if (parent) {
            if (parent instanceof IPSDEFValueRule)
                return parent
            else
                return getPSDEFValueRule(parent)
        }
        return null
    }


    /**
     * 获取条件属性
     * @param iPSDEFValueRule
     * @return
     */
    public static IPSDEField getCurPSDEField(IPSDEFVRSingleCondition iPSDEFVRSingleCondition) {
        IPSDEFValueRule iPSDEFValueRule = getPSDEFValueRule(iPSDEFVRSingleCondition)
        if (iPSDEFValueRule) {
            IPSDEField iPSDEField = iPSDEFValueRule.getCurPSDataEntity().getAllPSDEFields().find({ field -> field.getName().equalsIgnoreCase(iPSDEFVRSingleCondition.getDEFName()) })
            if (iPSDEField) {
                return iPSDEField
            }
        }
        return null
    }

    /**
     * 获取值规则属性
     * @param iPSDEFValueRule
     * @return
     */
    public static IPSDEField getCurPSDEField(IPSDEFValueRule iPSDEFValueRule) {
        IPSDEField iPSDEField = iPSDEFValueRule.getParentPSModelObject(IPSDEField.class, true)
        return iPSDEField
    }

    /**
     * 获取实体
     * @param iPSDEFValueRule
     * @return
     */
    public static IPSDataEntity getCurPSDataEntity(IPSDEFValueRule iPSDEFValueRule) {
        IPSDataEntity iPSDataEntity = getCurPSDEField(iPSDEFValueRule).getParentPSModelObject(IPSDataEntity.class, true)
        return iPSDataEntity
    }


    static Map<IPSDEFVRCondition, String> getAllConditions(IPSDEFValueRule iPSDEFValueRule) {
//        List<IPSDEFVRCondition> conditions = new ArrayList<>()
        Map<IPSDEFVRCondition, String> map = new HashMap<>()
        reduceMap(map, iPSDEFValueRule.getPSDEFVRGroupCondition())
//        conditions.addAll(map.keySet())
        return map
    }


    static String getMdUmlInfo(IPSDEFValueRule iPSDEFValueRule) {
        StringBuffer uml = new StringBuffer()
        if (iPSDEFValueRule.getPSDEFVRGroupCondition()
                && iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions()) {
            uml.append(String.format(STATE_FORMAT, START, START) + " <<start>>")
            uml.append("\n")

            uml.append(String.format(STATE_FORMAT, END, END) + "<<end>>")
            uml.append("\n")

            Map<IPSDEFVRCondition, String> map = new HashMap<>()
            //map 所有节点唯一key
            reduceMap(map, iPSDEFValueRule.getPSDEFVRGroupCondition())

            //node
            iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions().each { cond ->
                reduceUmUmlInfo(iPSDEFValueRule, uml, map, cond, null)
            }
            uml.append("\n")
            uml.append("\n")

            //link
            String first = map.get(iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions().get(0))
            if (iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions().get(0) instanceof IPSDEFVRGroupCondition)
                first += "_entry"
            uml.append(String.format(LINK_FORMAT, START, YES_LINK, first))
            uml.append("\n")

            reduceUmUmlLinkInfo(uml, map, iPSDEFValueRule.getPSDEFVRGroupCondition(), null)

            String last = map.get(iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions().get(iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions().size() - 1))
            if (iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions().get(iPSDEFValueRule.getPSDEFVRGroupCondition().getPSDEFVRConditions().size() - 1) instanceof IPSDEFVRGroupCondition)
                last += "_exit"
            uml.append(String.format(LINK_FORMAT, last, YES_LINK, END))
            uml.append("\n")
        }
        KeyValueUtils.genGuid()
        return uml.toString()
    }

    static void reduceMap(Map<IPSDEFVRCondition, String> map, IPSDEFVRCondition iPSDEFVRCondition) {
        String id = KeyValueUtils.genUniqueId(iPSDEFVRCondition)
        map.put(iPSDEFVRCondition, id)
        if (iPSDEFVRCondition instanceof IPSDEFVRGroupCondition) {
            IPSDEFVRGroupCondition iPSDEFVRGroupCondition = (IPSDEFVRGroupCondition) iPSDEFVRCondition
            if (iPSDEFVRGroupCondition.getPSDEFVRConditions()) {
                iPSDEFVRGroupCondition.getPSDEFVRConditions().each { sub ->
                    reduceMap(map, sub)
                }
            }
        }
    }

    static void reduceUmUmlInfo(IPSDEFValueRule iPSDEFValueRule, StringBuffer uml, Map<IPSDEFVRCondition, String> map, IPSDEFVRCondition iPSDEFVRCondition, IPSDEFVRGroupCondition parent) {
        IPSDEField iPSDEField = iPSDEFValueRule.getParentPSModelObject(IPSDEField.class, true)
        uml.append(String.format(STATE_WITH_LINK_FORMAT, iPSDEFVRCondition.getName(), map.get(iPSDEFVRCondition), iPSDEField.getCodeName(), "a" + map.get(iPSDEFVRCondition), iPSDEFVRCondition.getName()))
        if (iPSDEFVRCondition instanceof IPSDEFVRSingleCondition) {
            uml.append("\n")
        } else if (iPSDEFVRCondition instanceof IPSDEFVRGroupCondition) {
            IPSDEFVRGroupCondition iPSDEFVRGroupCondition = (IPSDEFVRGroupCondition) iPSDEFVRCondition
            if (iPSDEFVRGroupCondition.getPSDEFVRConditions()) {
                uml.append(" {\n")

                uml.append(String.format(STATE_FORMAT, " ", map.get(iPSDEFVRCondition) + "_entry") + " <<entryPoint>>")
                uml.append("\n")
                iPSDEFVRGroupCondition.getPSDEFVRConditions().eachWithIndex { sub, index ->
                    reduceUmUmlInfo(iPSDEFValueRule, uml, map, sub, iPSDEFVRGroupCondition)
                }
                uml.append(String.format(STATE_FORMAT, " ", map.get(iPSDEFVRCondition) + "_exit") + " <<exitPoint>>")
                uml.append("\n")

                uml.append("}\n")
            }
        }
    }

    static void reduceUmUmlLinkInfo(StringBuffer uml, Map<IPSDEFVRCondition, String> map, IPSDEFVRGroupCondition iPSDEFVRGroupCondition, IPSDEFVRGroupCondition parent) {
        if (iPSDEFVRGroupCondition.getPSDEFVRConditions().size() == 1) {
            IPSDEFVRCondition start = iPSDEFVRGroupCondition.getPSDEFVRConditions().get(0)
            if (start instanceof IPSDEFVRGroupCondition) {
                reduceUmUmlLinkInfo(uml, map, start, start)
            }
        } else {
            for (int i = 0; i < iPSDEFVRGroupCondition.getPSDEFVRConditions().size(); i++) {

                if (i + 1 < iPSDEFVRGroupCondition.getPSDEFVRConditions().size()) {
                    IPSDEFVRCondition start = iPSDEFVRGroupCondition.getPSDEFVRConditions().get(i)
                    IPSDEFVRCondition end = iPSDEFVRGroupCondition.getPSDEFVRConditions().get(i + 1)

                    if (parent && i == 0) {
                        String endId = map.get(start)
                        if (start instanceof IPSDEFVRGroupCondition) {
                            endId += "_entry"
                        }
                        uml.append(String.format(LINK_FORMAT, map.get(parent) + "_entry", YES_LINK, endId))
                        uml.append("\n")
                    }

                    reduceUmUmlLinkInfo(uml, map, start, end, parent)

                    //子条件
                    if (start instanceof IPSDEFVRGroupCondition) {
                        reduceUmUmlLinkInfo(uml, map, start, start)
                    }
                    //子条件
                    if (end instanceof IPSDEFVRGroupCondition && i == iPSDEFVRGroupCondition.getPSDEFVRConditions().size() - 2) {
                        reduceUmUmlLinkInfo(uml, map, end, end)
                    }

                    if (parent && i == iPSDEFVRGroupCondition.getPSDEFVRConditions().size() - 2) {
                        String startId = map.get(end)
                        if (end instanceof IPSDEFVRGroupCondition) {
                            startId += "_exit"
                            uml.append(String.format(LINK_FORMAT, startId, YES_LINK, map.get(parent) + "_exit"))
                            uml.append("\n")
                        } else {
                            uml.append(String.format(LINK_FORMAT, startId, YES_LINK, map.get(parent) + "_exit") + " : yes")
                            uml.append("\n")
                            String endId = END
                            if (!start.isKeyCond()) {
                                IPSDEFVRCondition orNextCond = getOrNextCond(end)
                                if (orNextCond) {
                                    endId = map.get(orNextCond)
                                    if (orNextCond instanceof IPSDEFVRGroupCondition) {
                                        endId += "_entry"
                                    }
                                }
                            }
                            uml.append(String.format(LINK_FORMAT, startId, NO_LINK, endId) + " : no")
                            uml.append("\n")
                        }
                    }
                }
            }
        }
    }

    static void reduceUmUmlLinkInfo(StringBuffer uml, Map<IPSDEFVRCondition, String> map, IPSDEFVRCondition start, IPSDEFVRCondition end, IPSDEFVRGroupCondition parent) {
        String startId = null

        startId = map.get(start)
        if (start instanceof IPSDEFVRGroupCondition) {
            startId += "_exit"
        }

        String endId = null

        endId = map.get(end)
        if (end instanceof IPSDEFVRGroupCondition) {
            endId += "_entry"
        }
        if (parent) {
            if (parent.getCondOp() == 'OR') {
                if (start instanceof IPSDEFVRGroupCondition) {
                    uml.append(String.format(LINK_FORMAT, startId, YES_LINK, map.get(parent) + "_exit"))
                } else {
                    uml.append(String.format(LINK_FORMAT, startId, YES_LINK, map.get(parent) + "_exit") + " : yes")
                }
                uml.append("\n")
                if (start instanceof IPSDEFVRSingleCondition) {
                    IPSDEFVRSingleCondition iPSDEFVRSingleCondition = (IPSDEFVRSingleCondition) start
                    if (iPSDEFVRSingleCondition.isKeyCond()) {
                        uml.append(String.format(LINK_FORMAT, startId, NO_LINK, END) + " : no")
                    } else {
                        IPSDEFVRCondition orNextCond = getOrNextCond(start)
                        if (orNextCond) {
                            endId = map.get(orNextCond)
                            if (orNextCond instanceof IPSDEFVRGroupCondition) {
                                endId += "_entry"
                            }
                            uml.append(String.format(LINK_FORMAT, startId, NO_LINK, endId) + " : no")
                        } else {
                            uml.append(String.format(LINK_FORMAT, startId, NO_LINK, END) + " : no")
                        }
                    }
                    uml.append("\n")
                }
            } else {
                if (start instanceof IPSDEFVRGroupCondition) {
                    uml.append(String.format(LINK_FORMAT, startId, YES_LINK, endId))
                } else {
                    uml.append(String.format(LINK_FORMAT, startId, YES_LINK, endId) + " :yes")
                    uml.append("\n")
                    if (start.isKeyCond()) {
                        uml.append(String.format(LINK_FORMAT, startId, NO_LINK, END) + " : no")
                    } else {
                        IPSDEFVRCondition orNextCond = getOrNextCond(start)
                        if (orNextCond) {
                            endId = map.get(orNextCond)
                            if (orNextCond instanceof IPSDEFVRGroupCondition) {
                                endId += "_entry"
                            }
                            uml.append(String.format(LINK_FORMAT, startId, NO_LINK, endId) + " : no")
                        } else {
                            uml.append(String.format(LINK_FORMAT, startId, NO_LINK, END) + " : no")
                        }
                        uml.append("\n")
                    }
                }
            }
        } else {
            if (start instanceof IPSDEFVRGroupCondition) {
                uml.append(String.format(LINK_FORMAT, startId, YES_LINK, endId))
            } else {
                uml.append(String.format(LINK_FORMAT, startId, YES_LINK, endId) + " :yes")
                uml.append("\n")
                uml.append(String.format(LINK_FORMAT, startId, NO_LINK, END) + " : no")
                uml.append("\n")
            }
        }


        uml.append("\n")
    }


    /**
     * 获取上一步or的next条件
     * @return
     */
    public static IPSDEFVRCondition getOrNextCond(IPSDEFVRCondition start) {
        IPSDEFVRCondition parent = start.getParentPSModelObject(IPSDEFVRGroupCondition.class, true)
        if (parent) {
            if (parent.getCondOp() == 'OR') {
                IPSDEFVRGroupCondition IPSDEFVRGroupCondition = (IPSDEFVRGroupCondition) parent
                int index = IPSDEFVRGroupCondition.getPSDEFVRConditions().indexOf(start)
                if (index > -1 && index + 1 < parent.getPSDEFVRConditions().size())
                    return parent.getPSDEFVRConditions().get(index + 1)
                else
                    return getOrNextCond(parent)
            } else {
                return getOrNextCond(parent)
            }
        }
        return null
    }

}
