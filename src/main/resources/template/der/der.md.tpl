## ${item.getLogicName()}(${item.getName()}) <!-- {docsify-ignore-all} -->

<%= (item.getMemo()) ? item.getMemo() : "" %>

<br>
<p class="panel-title"><b>主实体</b></p>

* [${item.getMajorPSDataEntity().getDisplayName()}](module/${item.getMajorPSDataEntity().getPSSystemModule().getCodeName()}/${item.getMajorPSDataEntity().getCodeName()})

<p class="panel-title"><b>从实体</b></p>

* [${item.getMinorPSDataEntity().getDisplayName()}](module/${item.getMinorPSDataEntity().getPSSystemModule().getCodeName()}/${item.getMinorPSDataEntity().getCodeName()})

<p class="panel-title"><b>关系类型</b></p>

* `${ctx.text('DERType',item.getDERType())}`
<% if (item instanceof net.ibizsys.model.dataentity.der.IPSDER1N){ %><%= ctx.output(item,'/der/type/IPSDER1N.md.tpl') %><% }%>\
<% if (item instanceof net.ibizsys.model.dataentity.der.IPSDER11){ %><%= ctx.output(item,'/der/type/IPSDER11.md.tpl') %><% }%>\
<% if (item instanceof net.ibizsys.model.dataentity.der.IPSDERAggData){ %><%= ctx.output(item,'/der/type/IPSDERAggData.md.tpl') %><% }%>\
<% if (item instanceof net.ibizsys.model.dataentity.der.IPSDERCustom){ %><%= ctx.output(item,'/der/type/IPSDERCustom.md.tpl') %><% }%>\
<% if (item instanceof net.ibizsys.model.dataentity.der.IPSDERIndex){ %><%= ctx.output(item,'/der/type/IPSDERIndex.md.tpl') %><% }%>\
<% if (item instanceof net.ibizsys.model.dataentity.der.IPSDERInherit){ %><%= ctx.output(item,'/der/type/IPSDERInherit.md.tpl') %><% }%>\
<% if (item instanceof net.ibizsys.model.dataentity.der.IPSDERMultiInherit){ %><%= ctx.output(item,'/der/type/IPSDERMultiInherit.md.tpl') %><% }%>\
