调用实体 [${item.getDstPSDataEntity().getDisplayName()}](module/${item.getDstPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSDataEntity().getCodeName()}.md) 数据查询 <%if(item.getDstPSDEDataQuery()){%>[${item.getDstPSDEDataQuery().getDisplayName()}](module/${item.getDstPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSDataEntity().getCodeName()}#数据查询)<%}%> <%if(item.getDstPSDELogicParam()){%>，查询参数为`${item.getDstPSDELogicParam().getInfo()}`<%}%>
<% if (item.getRetPSDELogicParam()){ %>
将执行结果返回给参数`${item.getRetPSDELogicParam().getInfo()}`
<% } %>