package cn.ibizlab.codegen.groovy.support


import net.ibizsys.model.app.dataentity.IPSAppDEUILogic
import net.ibizsys.model.app.logic.IPSAppUILogic
import net.ibizsys.model.app.logic.IPSAppUILogicRefView
import net.ibizsys.model.app.view.*
import net.ibizsys.model.control.IPSControl
import net.ibizsys.model.control.IPSControlContainer
import net.ibizsys.model.control.dashboard.IPSDBAppViewPortletPart
import net.ibizsys.model.control.dataview.IPSDEDataView
import net.ibizsys.model.control.drctrl.IPSDEDRCtrl
import net.ibizsys.model.control.drctrl.IPSDEDRCtrlItem
import net.ibizsys.model.control.drctrl.IPSDEDRTab
import net.ibizsys.model.control.drctrl.IPSDEDRTabPage
import net.ibizsys.model.control.editor.IPSPicker
import net.ibizsys.model.control.expbar.IPSTabExpPanel
import net.ibizsys.model.control.expbar.IPSTreeExpBar
import net.ibizsys.model.control.form.*
import net.ibizsys.model.control.grid.*
import net.ibizsys.model.control.list.IPSList
import net.ibizsys.model.control.menu.IPSAppMenu
import net.ibizsys.model.control.menu.IPSAppMenuItem
import net.ibizsys.model.control.menu.PSAppMenuItemImpl
import net.ibizsys.model.control.panel.*
import net.ibizsys.model.control.toolbar.IPSDEToolbar
import net.ibizsys.model.control.toolbar.IPSDEToolbarItem
import net.ibizsys.model.control.toolbar.PSDETBGroupItemImpl
import net.ibizsys.model.control.toolbar.PSDETBUIActionItemImpl
import net.ibizsys.model.control.viewpanel.IPSDEViewPanel
import net.ibizsys.model.control.wizardpanel.IPSDEWizardPanel
import net.ibizsys.model.view.IPSUIAction
import org.springframework.util.ObjectUtils

public class PSApplicationExtension {

    public static Map getRefs(IPSControl iPSControl) {
        Map<String, Set> refs = new HashMap<>()

        Set<IPSAppView> iPSAppViews = new HashSet<>()
        Set<IPSControl> iPSControls = new HashSet<>()
        Set<IPSAppDEUILogic> iPSAppDEUILogics = new HashSet<>()
        Set<IPSUIAction> iPSUIActions = new HashSet<>()

        refs.put("views", iPSAppViews)
        refs.put("uilogics", iPSAppDEUILogics)
        refs.put("uiactions", iPSUIActions)

        getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSControl)

        return refs
    }

    public static Set getRefControls(IPSAppView iPSAppView) {
        Set<IPSControl> iPSControls = new HashSet<>()
        //控件相关
        if (iPSAppView.getPSControls()) {
            iPSControls.addAll(iPSAppView.getPSControls())
        }
        //布局控件
        if (iPSAppView.getPSViewLayoutPanel() && iPSAppView.getPSViewLayoutPanel().getPSControls()) {
            iPSControls.addAll(iPSAppView.getPSViewLayoutPanel().getPSControls())
        }
        return iPSControls
    }

    public static Map getRefs(IPSAppView iPSAppView) {
        Map<String, Set> refs = new HashMap<>()

        Set<IPSAppView> iPSAppViews = new HashSet<>()
        Set<IPSControl> iPSControls = new HashSet<>()
        Set<IPSAppDEUILogic> iPSAppDEUILogics = new HashSet<>()
        Set<IPSUIAction> iPSUIActions = new HashSet<>()

        refs.put("views", iPSAppViews)
        refs.put("controls", iPSControls)
        refs.put("uilogics", iPSAppDEUILogics)
        refs.put("uiactions", iPSUIActions)
//        if ('EMPlanDashboardView' != iPSAppView.getCodeName()) {
//            return refs
//        }

        // 关联视图
        if (iPSAppView.getPSAppViewRefs()) {
            for (IPSAppViewRef ref : iPSAppView.getPSAppViewRefs()) {
                if (ref.getRefPSAppView())
                    addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, ref.getRefPSAppView())
            }
        }

        if (iPSAppView.getPSAppViewLogics()) {
            iPSAppView.getPSAppViewLogics().each { iPSAppViewLogic ->
                //getPSAppViewUIAction
                // 界面行为关联视图无法从模型[net.ibizsys.model.app.view.PSAppViewLogicImpl@559fd5ec]获取指定类型[interface net.ibizsys.model.app.view.IPSAppViewUIAction]子模型，标识为[{"modelref":true,"id":"新建临时管理员"}]
                // 注释
//                if (iPSAppViewLogic.getPSAppViewUIAction() ) {
//                    IPSAppViewUIAction iPSAppViewUIAction = iPSAppViewLogic.getPSAppViewUIAction();
//                    if (iPSAppViewUIAction.getPSUIAction()) {
//                        getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppViewUIAction.getPSUIAction())
//                    }
//                }
                // 预置逻辑关联视图
                if (iPSAppViewLogic.getPSAppUILogic()) {
                    IPSAppUILogic iPSAppUILogic = iPSAppViewLogic.getPSAppUILogic();
                    if (iPSAppUILogic) {
                        //预置逻辑
                        //getUILogicRefViews(iPSAppViews, iPSAppDEUILogics, iPSUIActions, iPSAppUILogic)
                        // 关联视图
                        if (iPSAppUILogic.getPSAppUILogicRefViews()) {
                            for (IPSAppUILogicRefView ref : iPSAppUILogic.getPSAppUILogicRefViews()) {
                                if (ref.getRefPSAppView())
                                    addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, ref.getRefPSAppView())
                            }
                        }
                    }
                }
                // 实体处理逻辑
                if (iPSAppViewLogic.getPSAppDEUILogic()) {
                    getUILogicRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppViewLogic.getPSAppDEUILogic())
                }
            }
        }
        if (iPSAppView.getPSAppViewUIActions()) {
            iPSAppView.getPSAppViewUIActions().each { viewuiaction ->
                if (viewuiaction.getPSUIAction()) {
                    getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, viewuiaction.getPSUIAction())
                }
            }
        }


        //控件相关
        if (iPSAppView.getPSControls()) {
            for (IPSControl iPSControl : iPSAppView.getPSControls()) {
                getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSControl);
            }
        }
        //布局控件
        if (iPSAppView.getPSViewLayoutPanel()) {
            getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppView.getPSViewLayoutPanel());
        }

        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppView)

        //移除自身
        iPSAppViews.remove(iPSAppView)
        return refs
    }

    public static void addAppView(Set<IPSAppView> iPSAppViews, Set<IPSControl> iPSControls, Set<IPSAppDEUILogic> iPSAppDEUILogics, Set<IPSUIAction> iPSUIActions, IPSAppView iPSAppView) {
        IPSAppView exists = null
        try {
            exists = iPSAppViews.find { a ->
                iPSAppView.getCodeName() == a.getCodeName()
            }
        } catch (Exception e) {

        }
        if (exists)
            return
        iPSAppViews.add(iPSAppView)
        //关联相关视图
        if (
                iPSAppView instanceof IPSAppPortalView
                        || iPSAppView instanceof IPSAppRedirectView
                        || iPSAppView instanceof IPSAppExplorerView
                        || iPSAppView instanceof IPSAppPanelView
                        || iPSAppView instanceof IPSAppDEDashboardView
                        || iPSAppView instanceof IPSAppDEIndexView
                        || iPSAppView instanceof IPSAppDEPanelView
                        || iPSAppView instanceof IPSAppDECustomView
        ) {
            // 关联视图
            if (iPSAppView.getPSAppViewRefs()) {
                for (IPSAppViewRef ref : iPSAppView.getPSAppViewRefs()) {
                    if (ref.getRefPSAppView())
                        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, ref.getRefPSAppView())
                }
            }
            if (iPSAppView.getPSAppViewLogics()) {
                iPSAppView.getPSAppViewLogics().each { iPSAppViewLogic ->
                    // 界面行为关联视图
                    if (iPSAppViewLogic.getPSAppViewUIAction()) {
                        IPSAppViewUIAction iPSAppViewUIAction = iPSAppViewLogic.getPSAppViewUIAction();
                        if (iPSAppViewUIAction.getPSUIAction()) {
                            getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppViewUIAction.getPSUIAction())
                        }
                    }
                    // 预置逻辑关联视图
                    if (iPSAppViewLogic.getPSAppUILogic()) {
                        IPSAppUILogic iPSAppUILogic = iPSAppViewLogic.getPSAppUILogic();
                        if (iPSAppUILogic) {
                            //预置逻辑
                            //getUILogicRefViews(iPSAppViews, iPSAppDEUILogics, iPSUIActions, iPSAppUILogic)
                            // 关联视图
                            if (iPSAppUILogic.getPSAppUILogicRefViews()) {
                                for (IPSAppUILogicRefView ref : iPSAppUILogic.getPSAppUILogicRefViews()) {
                                    if (ref.getRefPSAppView())
                                        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, ref.getRefPSAppView())
                                }
                            }
                        }
                    }
                    // 实体处理逻辑
                    if (iPSAppViewLogic.getPSAppDEUILogic()) {
                        getUILogicRefViews(iPSAppViews, iPSAppDEUILogics, iPSUIActions, iPSAppViewLogic.getPSAppDEUILogic())
                    }
                }
            }
            if (iPSAppView.getPSAppViewUIActions()) {
                iPSAppView.getPSAppViewUIActions().each { viewuiaction ->
                    if (viewuiaction.getPSUIAction()) {
                        getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, viewuiaction.getPSUIAction())
                    }
                }
            }
            //控件相关
            if (iPSAppView.getPSControls()) {
                for (IPSControl iPSControl : iPSAppView.getPSControls()) {
                    getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSControl);
                }
            }
            //布局控件
            if (iPSAppView.getPSViewLayoutPanel()) {
                getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppView.getPSViewLayoutPanel());
            }
        }
    }


    /**
     * 控件相关视图
     *
     * @param iPSAppViews
     * @param iPSAppDEUILogics
     * @param iPSUIActions
     * @param iPSControl
     */
    public static void getControlRefViews(Set<IPSAppView> iPSAppViews, Set<IPSControl> iPSControls, Set<IPSAppDEUILogic> iPSAppDEUILogics, Set<IPSUIAction> iPSUIActions, IPSControl iPSControl) {
        IPSControl exists = null
        try {
            exists = iPSControls.find { a ->
                iPSControl.getPSAppDataEntity().getName() == a.getPSAppDataEntity().getName() && iPSControl.getName() == a.getName()
            }
        } catch (Exception e) {

        }
        if (exists)
            return
        iPSControls.add(iPSControl)

        //部件逻辑
        if (iPSControl.getPSControlLogics()) {
            iPSControl.getPSControlLogics().each { logic ->
                if (logic.getPSAppUILogic()) {
                    getUILogicRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppUILogic())
                }
                if (logic.getPSAppDEUIAction()) {
                    getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppDEUIAction())
                }
            }
        }

        if (iPSControl instanceof IPSAppMenu) {
            IPSAppMenu iPSAppMenu = (IPSAppMenu) iPSControl;
            List<IPSAppMenuItem> menuItems = loopMenuItems(iPSAppMenu.getPSAppMenuItems())
            for (IPSAppMenuItem iPSAppMenuItem : menuItems) {
                if (iPSAppMenuItem instanceof PSAppMenuItemImpl) {
                    if (iPSAppMenuItem.getPSAppFunc() && iPSAppMenuItem.getPSAppFunc().getPSAppView()) {
                        IPSAppView subView = iPSAppMenuItem.getPSAppFunc().getPSAppView()
                        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                    }
                }
            }
        }

        if (iPSControl instanceof IPSDEToolbar) {
            IPSDEToolbar iPSDEToolbar = (IPSDEToolbar) iPSControl
            if (iPSDEToolbar.getPSDEToolbarItems()) {
                List<IPSDEToolbarItem> iPSDEToolbarItems = loopToolbarItems(iPSDEToolbar.getPSDEToolbarItems())
                iPSDEToolbarItems.each { item ->
                    if (item instanceof PSDETBUIActionItemImpl) {
                        PSDETBUIActionItemImpl psDETBUIActionItemImpl = (PSDETBUIActionItemImpl) item
                        if (psDETBUIActionItemImpl.getPSUIAction()) {
                            getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, psDETBUIActionItemImpl.getPSUIAction())
                        }
                    }
                }
            }
        }

        if (iPSControl instanceof IPSDEWizardPanel) {
            IPSDEWizardPanel iPSDEWizardPanel = (IPSDEWizardPanel) iPSControl;
            for (IPSDEEditForm iPSDEEditForm : iPSDEWizardPanel.getPSDEEditForms()) {
                getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSDEEditForm);
            }
        }

        if (iPSControl instanceof IPSDEViewPanel) {
            IPSAppView subView = ((IPSDEViewPanel) iPSControl).getEmbeddedPSAppDEView();
            if (subView)
                addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
        }

        if (iPSControl instanceof IPSDEDRCtrl) {
            IPSDEDRCtrl iPSDEDRCtrl = (IPSDEDRCtrl) iPSControl;
            if (iPSDEDRCtrl.getPSDEDRCtrlItems()) {
                for (IPSDEDRCtrlItem iPSDEDRCtrlItem : iPSDEDRCtrl.getPSDEDRCtrlItems()) {
                    IPSAppView subView = iPSDEDRCtrlItem.getPSAppView();
                    if (subView)
                        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                }
            }
            if (iPSControl instanceof IPSDEDRTab) {
                IPSDEDRTab iPSDEDRTab = (IPSDEDRTab) iPSControl;
                if (iPSDEDRTab.getPSDEDRTabPages()) {
                    for (IPSDEDRTabPage iPSDEDRTabPage : iPSDEDRTab.getPSDEDRTabPages()) {
                        IPSAppView subView = iPSDEDRTabPage.getPSAppView();
                        if (subView)
                            addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                    }
                }
            }
        }

        if (iPSControl instanceof IPSDEGrid) {
            IPSDEGrid iPSDEGrid = (IPSDEGrid) iPSControl;
            if (iPSDEGrid.getPSDEGridColumns()) {
                List<IPSDEGridColumn> iPSDEGridColumn = loopGridDetails(iPSDEGrid.getPSDEGridColumns())
                iPSDEGridColumn.each { column ->
                    if (column instanceof IPSDEGridFieldColumn) {
                        IPSDEGridFieldColumn iPSDEGridFieldColumn = (IPSDEGridFieldColumn) column
                        if (iPSDEGridFieldColumn.getPSDEUIAction()) {
                            getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSDEGridFieldColumn.getPSDEUIAction())
                        }
                        if (iPSDEGridFieldColumn.getPSDEUIActionGroup() && iPSDEGridFieldColumn.getPSDEUIActionGroup().getPSUIActionGroupDetails()) {
                            iPSDEGridFieldColumn.getPSDEUIActionGroup().getPSUIActionGroupDetails().each { groupdetail ->
                                if (groupdetail.getPSUIAction()) {
                                    getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, groupdetail.getPSUIAction())
                                }
                            }
                        }
                    }
                    if (column instanceof IPSDEGridUAColumn) {
                        IPSDEGridUAColumn iPSDEGridUAColumn = (IPSDEGridUAColumn) column
                        if (iPSDEGridUAColumn.getPSDEUIActionGroup() && iPSDEGridUAColumn.getPSDEUIActionGroup().getPSUIActionGroupDetails()) {
                            iPSDEGridUAColumn.getPSDEUIActionGroup().getPSUIActionGroupDetails().each { groupdetail ->
                                if (groupdetail.getPSUIAction()) {
                                    getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, groupdetail.getPSUIAction())
                                }
                            }
                        }
                        if (iPSDEGridUAColumn.getPSControlLogics()) {
                            iPSDEGridUAColumn.getPSControlLogics().each { logic ->
                                if (logic.getPSAppDEUIAction()) {
                                    getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppDEUIAction())
                                }
                                if (logic.getPSAppDEUILogic()) {
                                    getUILogicRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppDEUILogic())
                                }
                            }
                        }
                    }
                }
            }
        }

        if (iPSControl instanceof IPSDEEditForm) {
            IPSDEEditForm iPSDEEditForm = (IPSDEEditForm) iPSControl;
            if (iPSDEEditForm.getPSDEFormPages()) {
                iPSDEEditForm.getPSDEFormPages().forEach({ formPage ->
                    List<IPSDEFormDetail> loopFormDetails = loopFormDetails(formPage.getPSDEFormDetails());
                    loopFormDetails.forEach({ detail ->
                        if (detail instanceof IPSDEFormDRUIPart) {
                            IPSAppView subView = ((IPSDEFormDRUIPart) detail).getPSAppView();
                            if (subView)
                                addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                        }
                        if (detail instanceof PSDEFormItemImpl) {
                            PSDEFormItemImpl iPSDEEditFormItem = (PSDEFormItemImpl) detail;
                            if (iPSDEEditFormItem.getPSEditor()
                                    && iPSDEEditFormItem.getPSEditor() instanceof IPSPicker) {
                                IPSPicker iPSPicker = (IPSPicker) iPSDEEditFormItem.getPSEditor();
                                IPSAppView subView = iPSPicker.getPickupPSAppView();
                                if (subView)
                                    addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                            }
                        }
                    });
                });
            }
        }

        if (iPSControl instanceof IPSDEFormDRUIPart) {
            IPSAppView subView = ((IPSDEFormDRUIPart) iPSControl).getPSAppView();
            if (subView)
                addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
        }

        if (iPSControl instanceof IPSDEMultiEditViewPanel) {
            IPSAppView subView = ((IPSDEMultiEditViewPanel) iPSControl).getEmbeddedPSAppView();
            if (subView)
                addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
        }

        if (iPSControl instanceof IPSDBAppViewPortletPart) {
            IPSAppView subView = ((IPSDBAppViewPortletPart) iPSControl).getPortletPSAppView();
            if (subView)
                addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
            List<IPSControl> subControls = ((IPSDBAppViewPortletPart) iPSControl).getPSControls()
            if (subControls) {
                subControls.each { subControl ->
                    getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subControl)
                }
            }
        }

        if (iPSControl instanceof IPSTabExpPanel) {
            List<IPSAppViewRef> refs = ((IPSTabExpPanel) iPSControl).getPSAppViewRefs();
            if (refs) {
                for (IPSAppViewRef ref : refs) {
                    IPSAppView subView = ref.getRefPSAppView()
                    if (subView) {
                        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                    }
                }
            }
        }

        if (iPSControl instanceof IPSTreeExpBar) {
            List<IPSAppViewRef> refs = ((IPSTreeExpBar) iPSControl).getPSAppViewRefs();
            if (refs) {
                for (IPSAppViewRef ref : refs) {
                    IPSAppView subView = ref.getRefPSAppView()
                    if (subView) {
                        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                    }
                }
            }
        }

        //容器控件
        if (iPSControl instanceof IPSControlContainer) {
            IPSControlContainer iPSControlContainer = (IPSControlContainer) iPSControl;

            List<IPSAppViewRef> refs = iPSControlContainer.getPSAppViewRefs();
            if (refs) {
                for (IPSAppViewRef ref : refs) {
                    IPSAppView subView = ref.getRefPSAppView()
                    if (subView) {
                        addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subView)
                    }
                }
            }

            List<IPSAppViewUIAction> psAppViewUIActions = iPSControlContainer.getPSAppViewUIActions();
            if (psAppViewUIActions) {
                for (IPSAppViewUIAction iPSAppViewUIAction : psAppViewUIActions) {
                    if (iPSAppViewUIAction.getPSUIAction())
                        getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppViewUIAction.getPSUIAction())
                }
            }

            if (iPSControlContainer.getPSAppViewLogics()) {
                iPSControlContainer.getPSAppViewLogics().each { iPSAppViewLogic ->
                    // 界面行为关联视图
                    if (iPSAppViewLogic.getPSAppViewUIAction()) {
                        IPSAppViewUIAction iPSAppViewUIAction = iPSAppViewLogic.getPSAppViewUIAction();
                        if (iPSAppViewUIAction.getPSUIAction()) {
                            getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSAppViewUIAction.getPSUIAction())
                        }
                    }
                    // 预置逻辑关联视图
                    if (iPSAppViewLogic.getPSAppUILogic()) {
                        IPSAppUILogic iPSAppUILogic = iPSAppViewLogic.getPSAppUILogic();
                        // 关联视图
                        if (iPSAppUILogic.getPSAppUILogicRefViews()) {
                            for (IPSAppUILogicRefView ref : iPSAppUILogic.getPSAppUILogicRefViews()) {
                                if (ref.getRefPSAppView())
                                    addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, ref.getRefPSAppView())
                            }
                        }
                    }
                }
            }

            if (iPSControlContainer.getPSControls()) {
                iPSControlContainer.getPSControls().each { subControl ->
                    getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, subControl);
                }
            }

            if (iPSControlContainer instanceof IPSDEDataView) {
                IPSDEDataView iPSDEDataView = (IPSDEDataView) iPSControlContainer
                if (iPSDEDataView.getItemPSLayoutPanel()) {
                    getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSDEDataView.getItemPSLayoutPanel());
                }
            }

            if (iPSControlContainer instanceof IPSList) {
                IPSList iPSList = (IPSList) iPSControlContainer
                if (iPSList.getItemPSLayoutPanel()) {
                    getControlRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSList.getItemPSLayoutPanel());
                }
            }

            if (iPSControl instanceof IPSPanel) {
                IPSPanel iPSPanel = (IPSPanel) iPSControl
                if (iPSPanel.getRootPSPanelItems()) {
                    List<IPSPanelItem> panelItems = loopPanelItems(iPSPanel.getRootPSPanelItems())
                    panelItems.each { item ->
                        if (item.getPSControlLogics()) {
                            item.getPSControlLogics().each { logic ->
                                if (logic.getPSAppUILogic()) {
                                    getUILogicRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppUILogic())
                                }
                                if (logic.getPSAppDEUIAction()) {
                                    getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppDEUIAction())
                                }
                            }
                        }
                        if (item instanceof IPSPanelButton) {
                            IPSPanelButton iPSPanelButton = (IPSPanelButton) item
                            if (iPSPanelButton.getPSAppViewUIAction() && iPSPanelButton.getPSAppViewUIAction().getPSUIAction()) {
                                getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSPanelButton.getPSAppViewUIAction().getPSUIAction())
                            }
                            if (iPSPanelButton.getPSControlLogics()) {
                                iPSPanelButton.getPSControlLogics().each { logic ->
                                    if (logic.getPSAppUILogic()) {
                                        getUILogicRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppUILogic())
                                    }
                                    if (logic.getPSAppDEUIAction()) {
                                        getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic.getPSAppDEUIAction())
                                    }
                                }
                            }
                            //显示逻辑 移除
                            if (iPSPanelButton.getPSPanelItemGroupLogics()) {
                                iPSPanelButton.getPSPanelItemGroupLogics().each { logic ->
//                                    getUILogicRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, logic)
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void getUIActionRefViews(Set<IPSAppView> iPSAppViews, Set<IPSControl> iPSControls, Set<IPSAppDEUILogic> iPSAppDEUILogics, Set<IPSUIAction> iPSUIActions, IPSUIAction iPSUIAction) {
        IPSUIAction exists = null
        try {
            exists = iPSUIActions.find { a ->
                iPSUIAction.getPSAppDataEntity().getName() == a.getPSAppDataEntity().getName() && iPSUIAction.getCodeName() == a.getCodeName()
            }
        } catch (Exception e) {

        }
        if (exists)
            return
        iPSUIActions.add(iPSUIAction)
        if (iPSUIAction.getFrontPSAppView()) {
            addAppView(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSUIAction.getFrontPSAppView());
        }

        if (iPSUIAction.getNextPSUIAction()) {
            getUIActionRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, iPSUIAction.getNextPSUIAction())
        }
        //界面逻辑
        //无法计算模型[IPSAppDEUILogic]标识
//        if (iPSUIAction instanceof IPSAppDEUIAction && ((IPSAppDEUIAction) iPSUIAction).getPSAppDEUILogic()) {
//            getUILogicRefViews(iPSAppViews, iPSControls, iPSAppDEUILogics, iPSUIActions, ((IPSAppDEUIAction) iPSUIAction).getPSAppDEUILogic())
//        }
    }

    public static void getUILogicRefViews(Set<IPSAppView> iPSAppViews, Set<IPSControl> iPSControls, Set<IPSAppDEUILogic> iPSAppDEUILogics, Set<IPSUIAction> iPSUIActions, IPSAppDEUILogic iPSAppDEUILogic) {
        IPSAppDEUILogic exists = null
        try {
            exists = iPSAppDEUILogics.find { a ->
                iPSAppDEUILogic.getParentPSModelObject().getName() == a.getParentPSModelObject().getName() && iPSAppDEUILogic.getCodeName() == a.getCodeName()
            }
        } catch (Exception e) {

        }
        if (exists)
            return
        iPSAppDEUILogics.add(iPSAppDEUILogic)
    }

    /**
     * 菜单项
     * @param items
     * @return
     */
    public static List<IPSAppMenuItem> loopMenuItems(List<IPSAppMenuItem> items) {
        List<IPSAppMenuItem> list = new ArrayList<>();
        if (items) {
            for (IPSAppMenuItem item : items) {
                list.add(item);
                List<IPSAppMenuItem> sbuItems = item.getPSAppMenuItems()
                if (sbuItems) {
                    list.addAll(loopMenuItems(sbuItems))
                }
            }
        }
        return list;
    }

    /**
     * 工具栏项
     * @param items
     * @return
     */
    public static List<IPSDEToolbarItem> loopToolbarItems(List<IPSDEToolbarItem> items) {
        List<IPSDEToolbarItem> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(items)) {
            for (IPSDEToolbarItem item : items) {
                if (item instanceof PSDETBGroupItemImpl) {
                    PSDETBGroupItemImpl psDETBGroupItemImpl = (PSDETBGroupItemImpl) item
                    if (psDETBGroupItemImpl.getPSDEToolbarItems())
                        list.addAll(loopToolbarItems(psDETBGroupItemImpl.getPSDEToolbarItems()))
                    if (psDETBGroupItemImpl.getPSDEContextMenuItems())
                        list.addAll(loopToolbarItems(psDETBGroupItemImpl.getPSDEContextMenuItems()))
                } else {
                    list.add(item);
                }
            }
        }
        return list;
    }

    /**
     * 获取所有面板项
     * @param items
     * @return
     */
    public static List<IPSPanelItem> loopPanelItems(List<IPSPanelItem> items) {
        List<IPSPanelItem> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(items)) {
            for (IPSPanelItem item : items) {
                if (item instanceof IPSPanelContainer) {
                    list.addAll(loopPanelItems(((IPSPanelContainer) item).getPSPanelItems()));
                }
                if (item instanceof IPSPanelTabPanel && ((IPSPanelTabPanel) item).getPSPanelTabPages()) {
                    ((IPSPanelTabPanel) item).getPSPanelTabPages().forEach({ page ->
                        list.addAll(loopPanelItems(page.getPSPanelItems()));
                    });
                }
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 获取所有表格项
     * @param items
     * @return
     */
    public static List<IPSDEGridColumn> loopGridDetails(List<IPSDEGridColumn> items) {
        List<IPSDEGridColumn> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(items)) {
            for (IPSDEGridColumn item : items) {
                if (item instanceof IPSDEGridGroupColumn) {
                    list.addAll(loopGridDetails(((IPSDEGridGroupColumn) item).getPSDEGridColumns()));
                }
                list.add(item);
            }
        }
        return list;
    }

    /**
     * 获取所有表单项
     * @param items
     * @return
     */
    public static List<IPSDEFormDetail> loopFormDetails(List<IPSDEFormDetail> items) {
        List<IPSDEFormDetail> list = new ArrayList<>();
        if (!ObjectUtils.isEmpty(items)) {
            for (IPSDEFormDetail item : items) {
                if (item instanceof IPSDEFormGroupBase) {
                    list.addAll(loopFormDetails(((IPSDEFormGroupBase) item).getPSDEFormDetails()));
                }
                if (item instanceof IPSDEFormTabPanel && ((IPSDEFormTabPanel) item).getPSDEFormTabPages()) {
                    ((IPSDEFormTabPanel) item).getPSDEFormTabPages().forEach({ page ->
                        list.addAll(loopFormDetails(page.getPSDEFormDetails()));
                    });

                } else
                    list.add(item);
            }
        }
        return list;
    }


}
