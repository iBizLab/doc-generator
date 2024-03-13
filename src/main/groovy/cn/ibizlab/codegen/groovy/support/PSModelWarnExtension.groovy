package cn.ibizlab.codegen.groovy.support

import net.ibizsys.model.IPSSystem
import net.ibizsys.model.PSModelEnums
import net.ibizsys.model.app.dataentity.IPSAppDEField
import net.ibizsys.model.app.view.IPSAppView
import net.ibizsys.model.codelist.IPSCodeList
import net.ibizsys.model.control.form.*
import net.ibizsys.model.control.grid.IPSDEGrid
import net.ibizsys.model.control.grid.IPSDEGridColumn
import net.ibizsys.model.control.grid.IPSDEGridFieldColumn
import net.ibizsys.model.control.grid.IPSDEGridGroupColumn
import net.ibizsys.model.dataentity.IPSDataEntity
import net.ibizsys.model.dataentity.action.IPSDEAction
import net.ibizsys.model.dataentity.action.IPSDEActionLogic
import net.ibizsys.model.dataentity.defield.IPSDEField
import net.ibizsys.model.dataentity.ds.IPSDEDataQuery
import net.ibizsys.model.dataentity.ds.IPSDEDataSet
import net.ibizsys.model.dataentity.logic.*
import net.ibizsys.model.dataentity.priv.IPSDEOPPriv
import net.ibizsys.model.dataentity.service.IPSDEServiceAPIMethod
import org.springframework.util.ObjectUtils

public class PSModelWarnExtension {


    /**
     * 存在org范围，但未定义组织字段
     * @param iPSSystem
     * @return
     */
    public static List<IPSDataEntity> getNoOrgIdEntities(IPSSystem iPSSystem) {
        List<IPSDataEntity> iPSDataEntities = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getStorageMode() != PSModelEnums.DEStorageType.SERVICEAPI.value) {
                if (entity.enableOrgDR() && entity.getOrgIdPSDEField() == null) {
                    iPSDataEntities.add(entity)
                }
            }
        }
        return iPSDataEntities
    }


    /**
     * 存在sec范围，但未定义部门字段
     * @param iPSSystem
     * @return
     */
    public static List<IPSDataEntity> getNoSecIdEntities(IPSSystem iPSSystem) {
        List<IPSDataEntity> iPSDataEntities = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getStorageMode() != PSModelEnums.DEStorageType.SERVICEAPI.value) {
                if (entity.enableSecDR()) {
                    IPSDEField iPSDEField = entity.getAllPSDEFields().find { field -> field.getPredefinedType() == PSModelEnums.PredefinedFieldType.ORGSECTORID }
                    if (iPSDEField == null) {
                        iPSDataEntities.add(entity)
                    }
                }
            }
        }
        return iPSDataEntities
    }


    /**
     * 实体 启用审计，无审计 配置
     * @param iPSSystem
     * @return
     */
    public static List<IPSDataEntity> getNoAuditUtilEntities(IPSSystem iPSSystem) {
        List<IPSDataEntity> iPSDataEntities = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getStorageMode() != PSModelEnums.DEStorageType.SERVICEAPI.value) {
                if (entity.getAuditMode() > 0 && !(iPSSystem.hasSysAuditUtil()) && !(entity.hasAuditUtil())) {
                    iPSDataEntities.add(entity)
                }
            }
        }
        return iPSDataEntities
    }


    /**
     * 直接sql查询
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEDataQuery> getCustomQueries(IPSSystem iPSSystem) {
        List<IPSDEDataQuery> iPSDEDataQueries = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getStorageMode() == PSModelEnums.DEStorageType.SQL.value) {
                if (entity.getAllPSDEDataQueries()) {
                    entity.getAllPSDEDataQueries().each { query ->
                        if (query.isCustomCode()) {
                            iPSDEDataQueries.add(query)
                        }
                    }
                }
            }
        }
        return iPSDEDataQueries
    }

    /**
     * 查询包含长文本属性
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEDataQuery> getTextFieldQueries(IPSSystem iPSSystem) {
        List<IPSDEDataQuery> iPSDEDataQueries = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getStorageMode() == PSModelEnums.DEStorageType.SQL.value) {
                if (entity.getAllPSDEDataQueries()) {
                    entity.getAllPSDEDataQueries().each { query ->
                        if (query.getTextFields()) {
                            iPSDEDataQueries.add(query)
                        }
                    }
                }
            }
        }
        return iPSDEDataQueries
    }

    /**
     * 无查询
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEDataSet> getNoQueryDatasets(IPSSystem iPSSystem) {
        List<IPSDEDataSet> iPSDEDataSets = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getStorageMode() == PSModelEnums.DEStorageType.SQL.value) {
                if (entity.getAllPSDEDataSets()) {
                    entity.getAllPSDEDataSets().each { iPSDEDataSet ->
                        if (iPSDEDataSet.getDataSetType() == PSModelEnums.DEDataSetType.DATAQUERY.value
                                && (iPSDEDataSet.getPSDEDataQueries() == null || iPSDEDataSet.getPSDEDataQueries().size() == 0)) {
                            iPSDEDataSets.add(iPSDEDataSet)
                        }
                    }
                }
            }
        }
        return iPSDEDataSets
    }

    /**
     * 存在sql的处理逻辑
     * @param iPSSystem
     * @return
     */
    public static List<IPSDELogic> getSqlNodeLogics(IPSSystem iPSSystem) {
        List<IPSDELogic> iPSDELogics = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getAllPSDELogics()) {
                entity.getAllPSDELogics().each { iPSDELogic ->
                    boolean hasSqlNode = false
                    iPSDELogic.getPSDELogicNodes().each { node ->
                        if (node instanceof IPSDERawSqlCallLogic || node instanceof IPSDERawSqlAndLoopCallLogic) {
                            hasSqlNode = true
                        }
                    }
                    if (hasSqlNode) {
                        iPSDELogics.add(iPSDELogic)
                    }
                }
            }
        }
        return iPSDELogics
    }

    /**
     * 存在脚本的处理逻辑
     * @param iPSSystem
     * @return
     */
    public static List<IPSDELogic> getScriptLogics(IPSSystem iPSSystem) {
        List<IPSDELogic> iPSDELogics = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getAllPSDELogics()) {
                entity.getAllPSDELogics().each { iPSDELogic ->
                    if(iPSDELogic.isCustomCode()){
                        iPSDELogics.add(iPSDELogic)
                    }else{
                        boolean hasSqlNode = false
                        iPSDELogic.getPSDELogicNodes().each { node ->
                            if (node instanceof IPSDERawCodeLogic) {
                                hasSqlNode = true
                            }
                        }
                        if (hasSqlNode) {
                            iPSDELogics.add(iPSDELogic)
                        }
                    }
                }
            }
        }
        return iPSDELogics
    }

    /**
     * 脚本行为
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEAction> getScriptActions(IPSSystem iPSSystem) {
        List<IPSDEAction> iPSDEActions = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getAllPSDEActions()) {
                entity.getAllPSDEActions().each { iPSDEAction ->
                    if(iPSDEAction.getActionType() == PSModelEnums.DEActionType.SCRIPT.value){
                        iPSDEActions.add(iPSDEAction)
                    }
                }
            }
        }
        return iPSDEActions
    }

    /**
     * 脚本行为附加处理
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEActionLogic> getScriptActionLogics(IPSSystem iPSSystem) {
        List<IPSDEActionLogic> iPSDEActionLogics = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getAllPSDEActions()) {
                entity.getAllPSDEActions().each { iPSDEAction ->
                    iPSDEAction.getPreparePSDEActionLogics()?.each { iPSDEActionLogic ->
                        if (iPSDEActionLogic.getActionLogicType() == PSModelEnums.DEActionLogicType.SCRIPT.value) {
                            iPSDEActionLogics.add(iPSDEActionLogic)
                        }
                        iPSDEActionLogic.getDstPSDEAction()
                    }
                    iPSDEAction.getCheckPSDEActionLogics()?.each { iPSDEActionLogic ->
                        if (iPSDEActionLogic.getActionLogicType() == PSModelEnums.DEActionLogicType.SCRIPT.value) {
                            iPSDEActionLogics.add(iPSDEActionLogic)
                        }
                    }
                    iPSDEAction.getBeforePSDEActionLogics()?.each { iPSDEActionLogic ->
                        if (iPSDEActionLogic.getActionLogicType() == PSModelEnums.DEActionLogicType.SCRIPT.value) {
                            iPSDEActionLogics.add(iPSDEActionLogic)
                        }
                    }
                    iPSDEAction.getAfterPSDEActionLogics()?.each { iPSDEActionLogic ->
                        if (iPSDEActionLogic.getActionLogicType() == PSModelEnums.DEActionLogicType.SCRIPT.value) {
                            iPSDEActionLogics.add(iPSDEActionLogic)
                        }
                    }
                }
            }
        }
        return iPSDEActionLogics
    }

    /**
     * 存在数据库操作的处理逻辑
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEDataSet> getDBTableLogics(IPSSystem iPSSystem) {
        List<IPSDELogic> iPSDELogics = new ArrayList<>()
        iPSSystem.getAllPSDataEntities().each { entity ->
            if (entity.getAllPSDELogics()) {
                entity.getAllPSDELogics().each { iPSDELogic ->
                    boolean hasDBTableNode = false
                    iPSDELogic.getPSDELogicNodes().each { node ->
                        if (node instanceof IPSDESysDBTableActionLogic) {
                            hasDBTableNode = true
                        }
                    }
                    if (hasDBTableNode) {
                        iPSDELogics.add(iPSDELogic)
                    }
                }
            }
        }
        return iPSDELogics
    }

    /**
     * 接口未配置权限
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEServiceAPIMethod> getNoOPPrivMethods(IPSSystem iPSSystem) {
        List<IPSDEServiceAPIMethod> iPSDEServiceAPIMethods = new ArrayList()
        if (iPSSystem.getAllPSSysServiceAPIs()) {
            iPSSystem.getAllPSSysServiceAPIs().each { api ->
                if (api.getPSDEServiceAPIs()) {
                    api.getPSDEServiceAPIs().each { deapi ->
                        if (deapi.getPSDEServiceAPIMethods() && deapi.getPSDataEntity().getStorageMode() != PSModelEnums.DEStorageType.SERVICEAPI.value) {
                            deapi.getPSDEServiceAPIMethods().each { method ->
                                if (method.getDataAccessAction() == null) {
                                    iPSDEServiceAPIMethods.add(method)
                                }
                            }
                        }
                    }
                }
            }
        }
        return iPSDEServiceAPIMethods
    }

    /**
     * 接口 NONE
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEServiceAPIMethod> getNoneOPPrivMethods(IPSSystem iPSSystem) {
        List<IPSDEServiceAPIMethod> iPSDEServiceAPIMethods = new ArrayList()
        if (iPSSystem.getAllPSSysServiceAPIs()) {
            iPSSystem.getAllPSSysServiceAPIs().each { api ->
                if (api.getPSDEServiceAPIs()) {
                    api.getPSDEServiceAPIs().each { deapi ->
                        if (deapi.getPSDEServiceAPIMethods() && deapi.getPSDataEntity().getStorageMode() != PSModelEnums.DEStorageType.SERVICEAPI.value) {
                            deapi.getPSDEServiceAPIMethods().each { method ->
                                if (method.getDataAccessAction() == 'NONE') {
                                    iPSDEServiceAPIMethods.add(method)
                                }
                            }
                        }
                    }
                }
            }
        }
        return iPSDEServiceAPIMethods
    }

    /**
     * 接口 DENY
     * @param iPSSystem
     * @return
     */
    public static List<IPSDEServiceAPIMethod> getDenyOPPrivMethods(IPSSystem iPSSystem) {
        List<IPSDEServiceAPIMethod> iPSDEServiceAPIMethods = new ArrayList()
        if (iPSSystem.getAllPSSysServiceAPIs()) {
            iPSSystem.getAllPSSysServiceAPIs().each { api ->
                if (api.getPSDEServiceAPIs()) {
                    api.getPSDEServiceAPIs().each { deapi ->
                        if (deapi.getPSDEServiceAPIMethods() && deapi.getPSDataEntity().getStorageMode() != PSModelEnums.DEStorageType.SERVICEAPI.value) {
                            deapi.getPSDEServiceAPIMethods().each { method ->
                                if (method.getDataAccessAction() == 'DENY') {
                                    iPSDEServiceAPIMethods.add(method)
                                }
                            }
                        }
                    }
                }
            }
        }
        return iPSDEServiceAPIMethods
    }

    /**
     * 附属实体控制未配置映射关系
     * @param iPSSystem
     * @return
     */
    public static Map<IPSDataEntity, List> getNoOPPrivMaps(IPSSystem iPSSystem) {
        Map<IPSDataEntity, List> entityListMap = new HashMap<>()
        if (iPSSystem.getAllPSSysServiceAPIs()) {
            iPSSystem.getAllPSSysServiceAPIs().each { api ->
                if (api.getPSDEServiceAPIs()) {
                    api.getPSDEServiceAPIs().each { deapi ->
                        IPSDataEntity entity = deapi.getPSDataEntity()
                        if (PSModelEnums.DEDataAccCtrlMode.MASTER.value == entity.getDataAccCtrlMode() && deapi.getPSDataEntity().getStorageMode() != PSModelEnums.DEStorageType.SERVICEAPI.value) {
                            List opprivs = new ArrayList()
                            entityListMap.put(entity, opprivs)

                            if (deapi.getPSDEServiceAPIMethods()) {
                                deapi.getPSDEServiceAPIMethods().each { method ->
                                    if (method.getDataAccessAction() && method.getDataAccessAction() != 'NONE' && method.getDataAccessAction() != 'DENY' && !opprivs.contains(method.getDataAccessAction())) {
                                        opprivs.add(method.getDataAccessAction())
                                    }
                                }
                            }
                            Iterator iterator = opprivs.iterator()
                            while (iterator.hasNext()) {
                                String oppriv = iterator.next();
                                IPSDEOPPriv iPSDEOPPriv = entity.getAllPSDEOPPrivs().find { op -> op.getName().equals(oppriv) && op.getMapPSDER() }
                                if (iPSDEOPPriv) {
                                    iterator.remove()
                                }
                            }
                        }
                    }
                }
            }
        }
        Iterator<Map.Entry<IPSDataEntity, List>> iterator = entityListMap.iterator()
        while (iterator.hasNext()) {
            Map.Entry<IPSDataEntity, List> entry = iterator.next();
            if (entry.value.size() == 0) {
                iterator.remove()
            }
        }
        return entityListMap
    }

    /**
     * 空静态代码表
     * @param iPSSystem
     * @return
     */
    public static List<IPSCodeList> getEmptyCodeLists(IPSSystem iPSSystem) {
        List<IPSCodeList> iPSCodeLists = new ArrayList<>()
        if (iPSSystem.getAllPSCodeLists()) {
            iPSSystem.getAllPSCodeLists().each { codelist ->
                if (codelist.getPSSystemModule() && codelist.getCodeListType() == 'STATIC' && (codelist.getPSCodeItems() == null || codelist.getPSCodeItems().size() == 0)) {
                    iPSCodeLists.add(codelist)
                }
            }
        }
        return iPSCodeLists
    }

    /**
     * 表单项少
     * @param iPSSystem
     * @return
     */
    public static Map<IPSAppView, IPSDEForm> getLessItemsForms(IPSSystem iPSSystem) {
        Map<IPSAppView, IPSDEForm> map = new TreeMap<>(new Comparator<IPSAppView>() {
            @Override
            int compare(IPSAppView o1, IPSAppView o2) {
                return o1.getCodeName() <=> o2.getCodeName()
            }
        })
        map.sort {it.getKey().getCodeName()}
        if (iPSSystem.getAllPSApps()) {
            iPSSystem.getAllPSApps().each { app ->
                if (app.getAllPSAppViews()) {
                    app.getAllPSAppViews().each { view ->
                        List<IPSDEForm> iPSDEForms = new ArrayList<>()
                        if (view.getPSControls()) {
                            view.getPSControls().each { control ->
                                if (control instanceof IPSDEEditForm) {
                                    IPSDEEditForm iPSDEEditForm = (IPSDEEditForm) control
                                    IPSDataEntity iPSDataEntity = iPSDEEditForm.getParentPSModelObject(IPSDataEntity.class)
                                    List<IPSDEFormDetail> iPSDEFormDetails = loopFormDetails(iPSDataEntity, iPSDEEditForm, iPSDEEditForm.getPSDEFormItems())
                                    if (iPSDEFormDetails.size() == 0) {
                                        map.put(view, iPSDEEditForm)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return map
    }

    /**
     * 表单项少
     * @param iPSSystem
     * @return
     */
    public static Map<IPSAppView, IPSDEForm> getLessItemsSearchForms(IPSSystem iPSSystem) {
        Map<IPSAppView, IPSDEForm> map = new TreeMap<>(new Comparator<IPSAppView>() {
            @Override
            int compare(IPSAppView o1, IPSAppView o2) {
                return o1.getCodeName() <=> o2.getCodeName()
            }
        })
        map.sort {it.getKey().getCodeName()}
        if (iPSSystem.getAllPSApps()) {
            iPSSystem.getAllPSApps().each { app ->
                if (app.getAllPSAppViews()) {
                    app.getAllPSAppViews().each { view ->
                        List<IPSDEForm> iPSDEForms = new ArrayList<>()
                        if (view.getPSControls()) {
                            view.getPSControls().each { control ->
                                if (control instanceof IPSDESearchForm) {
                                    IPSDESearchForm iPSDESearchForm = (IPSDESearchForm) control
                                    IPSDataEntity iPSDataEntity = iPSDESearchForm.getParentPSModelObject(IPSDataEntity.class)
                                    List<IPSDEFormDetail> iPSDEFormDetails = loopFormDetails(iPSDataEntity, iPSDESearchForm, iPSDESearchForm.getPSDEFormItems())
                                    if (iPSDEFormDetails.size() == 0) {
                                        map.put(view, iPSDESearchForm)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return map
    }

    /**
     * 获取所有表单项
     * @param iPSDataEntity
     * @param items
     * @return
     */
    public static List<IPSDEFormDetail> loopFormDetails(IPSDataEntity iPSDataEntity, IPSDEForm iPSDEForm, List<IPSDEFormDetail> items) {
        List<IPSDEFormDetail> list = new ArrayList<>();
        if (items) {
            items.each { item ->
                if (item instanceof IPSDEFormGroupBase) {
                    list.addAll(loopFormDetails(iPSDataEntity, iPSDEForm, ((IPSDEFormGroupBase) item).getPSDEFormDetails()));
                }
                if (item instanceof IPSDEFormTabPanel && ((IPSDEFormTabPanel) item).getPSDEFormTabPages()) {
                    ((IPSDEFormTabPanel) item).getPSDEFormTabPages().forEach({ page ->
                        list.addAll(loopFormDetails(iPSDataEntity, iPSDEForm, page.getPSDEFormDetails()));
                    });

                }
                if (item instanceof IPSDEFormItem) {
                    //预置属性忽略
                    IPSDEFormItem iPSDEFormItem = (IPSDEFormItem) item
                    IPSAppDEField iPSAppDEField = iPSDEFormItem.getPSAppDEField()
                    if (iPSAppDEField) {
                        IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find { field -> field.getName().equals(iPSAppDEField.getName()) }
                        boolean isAdd = true
                        if (iPSDEField) {
                            if (iPSDEForm instanceof IPSDEEditForm) {
                                if ((iPSDEField.getPredefinedType() || iPSDEField.isKeyDEField() || iPSDEField.isMajorDEField())) {
                                    isAdd = false
                                }
                            }
                        }
                        if (isAdd) {
                            list.add(item)
                        }
                    }
                } else {
                    list.add(item);
                }
            }
        }
        return list;
    }

    /**
     * 表格项少
     * @param iPSSystem
     * @return
     */
    public static Map<IPSAppView, IPSDEGrid> getLessItemsGrids(IPSSystem iPSSystem) {
        Map<IPSAppView, IPSDEGrid> map = new TreeMap<>(new Comparator<IPSAppView>() {
            @Override
            int compare(IPSAppView o1, IPSAppView o2) {
                return o1.getCodeName() <=> o2.getCodeName()
            }
        })
        if (iPSSystem.getAllPSApps()) {
            iPSSystem.getAllPSApps().each { app ->
                if (app.getAllPSAppViews()) {
                    app.getAllPSAppViews().each { view ->
                        if (view.getPSControls()) {
                            view.getPSControls().each { control ->
                                if (control instanceof IPSDEGrid) {
                                    IPSDEGrid iPSDEGrid = (IPSDEGrid) control
                                    IPSDataEntity iPSDataEntity = iPSDEGrid.getParentPSModelObject(IPSDataEntity.class)
                                    List<IPSDEGridColumn> iPSDEGridColumns = loopGridDetails(iPSDataEntity, iPSDEGrid.getPSDEGridColumns())
                                    if (iPSDEGridColumns.size() == 0) {
                                        map.put(view, iPSDEGrid)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return map
    }

    /**
     * 获取所有表格项
     * @param items
     * @return
     */
    public static List<IPSDEGridColumn> loopGridDetails(IPSDataEntity iPSDataEntity, List<IPSDEGridColumn> items) {
        List<IPSDEGridColumn> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(items)) {
            for (IPSDEGridColumn item : items) {
                if (item instanceof IPSDEGridGroupColumn) {
                    list.addAll(loopGridDetails(iPSDataEntity, ((IPSDEGridGroupColumn) item).getPSDEGridColumns()));
                }
                if (item instanceof IPSDEGridFieldColumn) {
                    //主键、主信息、预置属性忽略
                    IPSDEGridFieldColumn iPSDEGridFieldColumn = (IPSDEGridFieldColumn) item
                    IPSAppDEField iPSAppDEField = iPSDEGridFieldColumn.getPSAppDEField()
                    if (iPSAppDEField) {
                        IPSDEField iPSDEField = iPSDataEntity.getAllPSDEFields().find { field -> field.getName().equals(iPSAppDEField.getName()) }
                        if (iPSDEField && (iPSDEField.getPredefinedType() || iPSDEField.isKeyDEField() || iPSDEField.isMajorDEField())) {

                        } else {
                            list.add(item)
                        }
                    }
                } else {
                    list.add(item);
                }
            }
        }
        return list;
    }
}
