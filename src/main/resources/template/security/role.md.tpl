# ${item.getName()} <!-- {docsify-ignore-all} -->

${ctx.ignoreNullString(item.getMemo())}


### 统一资源

|统一资源|标识|
|---|---|
<% if (item.getPSSysUserRoleReses()) {
    item.getPSSysUserRoleReses().eachWithIndex{ roleres,index -> %>\
|${roleres.getPSSysUniRes().getName()}|${roleres.getPSSysUniRes().getResCode()}|
<% }} %>


### 数据能力

|实体|数据能力|
|---|---|
<% if (item.getPSSysUserRoleDatas()) {
    item.getPSSysUserRoleDatas().eachWithIndex{ dedata,index -> %>\
|[${dedata.getPSDataEntity().getDisplayName()}](module/${dedata.getPSDataEntity().getPSSystemModule().getCodeName()}/${dedata.getPSDataEntity().getCodeName()})|<a href ="/#/module/${dedata.getPSDataEntity().getPSSystemModule().getCodeName()}/${dedata.getPSDataEntity().getCodeName()}#${dedata.getPSDataEntity().getCodeName().toLowerCase()}-${dedata.getPSDEUserRole().getRoleTag().toLowerCase()}">${dedata.getPSDEUserRole().getName()}</a>|
<% }} %>


