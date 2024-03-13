<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>${item.logicName}文档</title>
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<meta name="description" content="Description">
		<!-- 设置浏览器图标 -->
		<link rel="icon" href="https://librarycodes.gitee.io/docsify-plus/img/logo.ico" type="image/x-icon" />
		<link rel="shortcut icon" href="https://librarycodes.gitee.io/docsify-plus/img/logo.ico" type="image/x-icon" />
		<!-- 默认主题 -->
		<link rel="stylesheet" href="//fastly.jsdelivr.net/npm/docsify/lib/themes/vue.css">
		<link rel="stylesheet" href="//fastly.jsdelivr.net/npm/prismjs/themes/prism.min.css">
        <link rel="stylesheet" href="https://cdn.bootcdn.net/ajax/libs/font-awesome/6.4.2/css/all.min.css">

		<!-- <link rel="stylesheet" href="//fastly.jsdelivr.net/npm/font-awesome/css/font-awesome.min.css" >-->
		<!-- <link rel="stylesheet" href="//fastly.jsdelivr.net/npm/docsify/lib/themes/vue.css" /> -->
		<!-- <link rel="stylesheet" href="//fastly.jsdelivr.net/npm/docsify/lib/themes/buble.css" /> -->
		<!-- <link rel="stylesheet" href="//fastly.jsdelivr.net/npm/docsify/lib/themes/dark.css" /> -->
		<!-- <link rel="stylesheet" href="//fastly.jsdelivr.net/npm/docsify/lib/themes/pure.css" /> -->
	</head>

	<style>
		div#app {
			font-size: inherit;
			font-weight: inherit;
			margin: inherit;
			text-align: inherit;
		}

		:root{
			--docsifytabs-content-padding: 0.5rem 0rem;
			--docsify-example-panels-document-width: 95%;
		}

		.markdown-section {
		    margin: 0;
		}

		.content {
    		padding-top: 0px;
		}

		.sidebar {
			width: 250px;
		}

	    .markdown-section pre>code{
			padding: 1.2em 5px;
		}

        p.panel-title {
            position: relative;
            height: 0;
            margin: 0;
            line-height: 0;
            left: 4px;
            font-size: 12px;
        }

        .docsify-tabs {
        	margin: 0!important
        }
	</style>

	<body>
	    <div id="app">
            <div data-app id="main">加载中</div>
        </div>

        <script>
			window.\$docsify = {
				el: '#main',
				// 项目名称
				name: '${item.logicName}',
				// 项目图标
				// logo: '/img/icon.svg',
				// 仓库地址，点击右上角的Github章鱼猫头像会跳转到此地址
				// repo: 'http://172.16.180.230/full-dynamic-ftl/full-dynamic-vue',
				// 封面支持，默认加载的是项目根目录下的_coverpage.md文件
				coverpage: false,
				// 侧边栏支持，默认加载的是项目根目录下的_sidebar.md文件
				loadSidebar: true,
				// 导航栏支持，默认加载的是项目根目录下的_navbar.md文件
				loadNavbar: false,
				// 最大支持渲染的标题层级
				maxLevel: 5,
				// 自定义侧边栏后默认不会再生成目录，设置生成目录的最大层级（建议配置为2-4）
				subMaxLevel: 4,
				// 小屏设备下合并导航栏到侧边栏
				mergeNavbar: true,
				// 未找到页面 _404.md
				//notFoundPage: true,
				// 搜索配置
				search:{
					// 过期时间，单位毫秒，默认一天
					maxAge: 86400000,
					// 注意：仅适用于 paths: 'auto' 模式
					paths: "auto",
					// 支持本地化
					placeholder: {
						'/': '搜索',
						'/zh-en/': 'Type to search'
					},
					noData: {
					        '/': '找不到结果',
					        '/zh-en/': 'No Results'
					      },
					depth: 3,
					hideOtherSidebarContent: false,
					namespace: 'Docsify-Guide',
				},
                copyCode: {
                    buttonText: '复制',
                    errorText: '错误',
                    successText: '已复制',
                },
				plantuml: {
					//skin: 'https://raw.githubusercontent.com/plantuml/plantuml/master/themes/puml-theme-sandstone.puml',
					skin: 'classic',
					renderAsObject: true,
			    },
			    tabs: {
                    sync: true,
                    theme: 'material'
                },
				scrollToTop: {
					text: '🔝',
				},
                'flexible-alerts': {
				},
				formatUpdated: '{YYYY}-{MM}-{DD} {HH}:{mm}',
				formatUpdated: function(time) {
					return time;
				},
				// 目标是在 markdown 中打开外部链接。默认'_blank'（新窗口/标签）
				externalLinkTarget: '_blank', // default: '_blank' '_self'
			}
		</script>
		<!-- docsify的js依赖 -->
		<!-- <script src="//fastly.jsdelivr.net/npm/docsify/lib/docsify.min.js"></script> -->
		<script src="script/docsify.js"></script>
        <script src="//fastly.jsdelivr.net/npm/vue@3/dist/vue.global.prod.js"></script>
        <!-- element style -->
        <link rel="stylesheet" href="//fastly.jsdelivr.net/npm/element-plus/dist/index.css"/>
        <!-- element component library -->
        <script type="module" src="//fastly.jsdelivr.net/npm/element-plus"></script>

		<!-- 图片放大缩小支持 -->
		<script src="//fastly.jsdelivr.net/npm/docsify/lib/plugins/zoom-image.min.js"></script>
		<!-- 搜索功能支持 -->
		<script src="//fastly.jsdelivr.net/npm/docsify/lib/plugins/search.min.js"></script>
		<!--在所有的代码块上添加一个简单的Click to copy按钮来允许用户从你的文档中轻易地复制代码-->
		<script src="//fastly.jsdelivr.net/npm/docsify-copy-code/dist/docsify-copy-code.min.js"></script>
		<!-- 分页导航
		<script src="//fastly.jsdelivr.net/npm/docsify-pagination/dist/docsify-pagination.min.js"></script>
		 -->
		<!-- 外部脚本 如果页面上的脚本是外部脚本（通过src属性导入 js 文件） -->
		<script src="//fastly.jsdelivr.net/npm/docsify/lib/plugins/external-script.min.js"></script>
		<!-- 插件列表 -->
        <script src="//fastly.jsdelivr.net/npm/docsify-puml@1.1.1/dist/docsify-puml.min.js"></script>

		<script src="//fastly.jsdelivr.net/npm/prismjs@1/components/prism-bash.min.js"></script>
		<script src="//fastly.jsdelivr.net/npm/prismjs@1/components/prism-php.min.js"></script>
		<script src="//fastly.jsdelivr.net/npm/prismjs@1.24.1/components/prism-java.min.js"></script>
		<script src="//fastly.jsdelivr.net/npm/prismjs@1.24.1/components/prism-python.min.js"></script>
		<script src="//fastly.jsdelivr.net/npm/prismjs@1.24.1/components/prism-json.min.js"></script>
		<script src="//fastly.jsdelivr.net/npm/prismjs@1.24.1/components/prism-sql.min.js"></script>
		<script src="//fastly.jsdelivr.net/npm/prismjs@1.24.1/components/prism-groovy.min.js"></script>

		<!-- example -->
		<script src="//fastly.jsdelivr.net/npm/docsify-example-panels"></script>

		<!-- tabs -->
		<script src="//fastly.jsdelivr.net/npm/docsify-tabs"></script>

		<!-- flexible-alerts -->
		<script src="//fastly.jsdelivr.net/npm/docsify-plugin-flexible-alerts"></script>

		<!-- scroll-to-top -->
		<script src="//fastly.jsdelivr.net/npm/docsify-scroll-to-top/dist/docsify-scroll-to-top.min.js"></script>

		<!-- footnote -->
		<script src="//fastly.jsdelivr.net/npm/docsify-footnote/dist/docsify-footnote.min.js"></script>

	</body>
</html>
