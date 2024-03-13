触发<%
if(item.getFireCtrl()) {
    out <<  '`' << item.getFireCtrl().getInfo() <<  '`的'
}
if(item.getEventName()) {
    out <<  '事件`' << item.getEventName() << '`'
}
if(item.getEventParam()) {
    out <<  '，参数为`' << item.getEventParam().getInfo() <<  '`'
}
%>