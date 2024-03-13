# <%=(item.getCaption())?item.getCaption():item.getTitle()%>(${item.getCodeName()})  <!-- {docsify-ignore-all} -->

${ctx.ignoreNullString(item.getMemo())}


<% if (item.getViewType() == 'APPINDEXVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/APPINDEXVIEW.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DEEDITVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DEEDITVIEW.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DEGRIDVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DEGRIDVIEW.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DEOPTVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DEOPTVIEW.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DEPICKUPVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DEPICKUPVIEW.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DEPICKUPVIEW2'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DEPICKUPVIEW2.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DEMPICKUPVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DEMPICKUPVIEW.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DEMPICKUPVIEW2'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DEMPICKUPVIEW2.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DETREEEXPVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DETREEEXPVIEW.tpl') %>
<% }%>\
<% if (item.getViewType() == 'DETABEXPVIEW'){ %>\
<%= ctx.output(item,'/app/view/skeleton/DETABEXPVIEW.tpl') %>
<% }%>\

<% if (item.getRefControls()) {%>\

## 控件
<%  item.getRefControls().each{ control -> %>\
  * ${ctx.text('CtrlType',control.getControlType())}(${control.getName()})
<% }} %>\
<% if (item.getPSAppViewLogics()) {%>\

## 视图界面逻辑
<%  item.getPSAppViewLogics().each{ logic -> %>\
<%if(logic.getPSAppUILogic() instanceof net.ibizsys.model.app.logic.IPSAppUINewDataLogic){%>\
  * ${logic.getName()}(预置新建数据逻辑)
<%} %>\
<%if(logic.getPSAppUILogic() instanceof net.ibizsys.model.app.logic.IPSAppUIOpenDataLogic){%>\
  * ${logic.getName()}(预置打开数据逻辑)
<%} %>\
<%if(logic.getPSAppDEUILogic()){%>\
  * [${logic.getPSAppDEUILogic().getName()}](module/${logic.getPSAppDEUILogic().getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${logic.getPSAppDEUILogic().getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}/uilogic/${logic.getPSAppDEUILogic().getCodeName()})
<%}%>\
<% }} %>
<% if (item.getRefs().uiactions) {%>\

### 关联界面行为
<%  item.getRefs().uiactions.each{ uiaction -> %>\
<%if(uiaction.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class,true)){ %>\
  * [${uiaction.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getDisplayName()}](module/${uiaction.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${uiaction.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}) : [${uiaction.getName()}](module/${uiaction.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${uiaction.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}#界面行为)
<%} else{ %>\
  * ${uiaction.getName()}(${uiaction.getCodeName()})
<%}%>\
<% }} %>\
<% if (item.getRefs().uilogics) {%>\

### 关联界面逻辑
<%  item.getRefs().uilogics.each{ uilogic -> %>\
  * [${uilogic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getDisplayName()}](module/${uilogic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${uilogic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}) : [${uilogic.getName()}](module/${uilogic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getPSSystemModule().getCodeName()}/${uilogic.getParentPSModelObject(net.ibizsys.model.dataentity.IPSDataEntity.class).getCodeName()}/uilogic/${uilogic.getCodeName()})
<% }} %>\
<% if (item.getRefs().views) {%>\

### 关联视图
<%  item.getRefs().views.each{ view -> %>\
  * [<%=(view.getCaption())?view.getCaption():view.getTitle()%>(${view.getCodeName()})](app/view/${view.getCodeName()})
<% }} %>\

<script>
 const { createApp } = Vue
  createApp({
    data() {
      return {
        message: '!'
      }
    }
  }).use(ElementPlus).mount('#app')
</script>