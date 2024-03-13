<% if (item.getDstPSAppDataEntity() && item.getDstPSAppDEUIAction() ){ %>\
调用实体 [${item.getDstPSAppDataEntity().getDisplayName()}](module/${item.getDstPSAppDataEntity().getPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSAppDataEntity().getCodeName()}.md) 界面行为 [${item.getDstPSAppDEUIAction().getName()}](module/${item.getDstPSAppDataEntity().getPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSAppDataEntity().getCodeName()}#界面行为) <%if(item.getDstPSDEUILogicParam()){%>，行为参数为`${item.getDstPSDEUILogicParam().getInfo()}`<%}%>
<% } else  {%>
调用界面行为异常，请检查配置平台相关配置
<% } %>