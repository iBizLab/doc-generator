调用实体 [${item.getDstPSDataEntity().getDisplayName()}](module/${item.getDstPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSDataEntity().getCodeName()}.md) 行为 [${item.getDstPSDEAction().getDisplayName()}](module/${item.getDstPSDataEntity().getPSSystemModule().getCodeName()}/${item.getDstPSDataEntity().getCodeName()}#行为) ，行为参数为`${item.getDstPSDELogicParam().getInfo()}`
<% if (item.getRetPSDELogicParam()){ %>
将执行结果返回给参数`${item.getRetPSDELogicParam().getInfo()}`
<% } %>