这是一个用FreeMarker生成Word文档的dome

copy :https://blog.csdn.net/renfufei/article/details/53283320
      https://github.com/cncounter/translation/blob/master/tiemao_2016/22_xml_word_freemarker/xml_word_freemarker.md
      
因为好用，所以自己稍微整理了一下，留下来自己用。本人已经在此基础上实现了word动态生成（主要指table），包括合并行，合并列，重复标题行，多表格分页嵌套等。谢谢铁锚大神。

需要从一个word文件中提取出 document.xml 这个xml文件，使用FreeMarker 标签重写。 建议先从设计好的word中提取，可以少设置一些其他页面格式，比如页面大小，页面方向等。

针对不同的表格数据，需要处理bean包下面的实体。也可以使用map的格式，但是一般从数据库来的数据都是bean格式，建议重写。

model 包下的export_sample.docx 为一个替换过程中被替换document.xml的壳，内容可以随意，可以理解为一个没有内容的word文件。

bean对象需要和模板文件对应。

记录几个FreeMarker 常用标签
<#list rows as row>
      <#--取list的循环下标 -->
      ${row_index}
      <#--取list的size -->
      ${list?size}
<#/list>


对了，变量声明在循环体内好呢还是循环体外好呢？
