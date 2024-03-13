<% if (item.getMainMenuAlign() == 'TOP'){ %>\
<el-skeleton style="width:60%">
	<template #template>
		<div style="padding-bottom: 5px;">
            <div style="">
				<el-tooltip content="菜单">
					<el-skeleton-item variant="text" style="height:40px;"></el-skeleton-item>
				</el-tooltip>
			</div>
			<el-tooltip content="导航区域">
				<el-skeleton-item variant="p" style="height:350px"></el-skeleton-item>
			</el-tooltip>
		</div>
	</template>
</el-skeleton>
<% } else { %>\
<el-skeleton style="width:60%">
	<template #template>
		<div style="padding-bottom: 5px;display: flex;">
			<div style="display: flex;align-items: center;justify-content: space-between;flex-direction: column;">
				<el-tooltip content="菜单">
					<el-skeleton-item variant="text" style="width:180px;height:350px;"></el-skeleton-item>
				</el-tooltip>
			</div>
			<el-tooltip content="导航区域">
				<el-skeleton-item variant="p" style="margin-left: 10px;height:350px"></el-skeleton-item>
			</el-tooltip>
		</div>
	</template>
</el-skeleton>
<% } %>