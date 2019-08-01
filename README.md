这是一个用FreeMarker生成Word文档的dome
copy :https://blog.csdn.net/renfufei/article/details/53283320

需要从一个word文件中提取出 document.xml 这个xml文件，使用FreeMarker 标签重写。 建议先从设计好的word中提取，可以少设置一些其他页面格式，比如页面大小，页面方向等。

针对不同的表格数据，需要处理bean包下面的实体。也可以使用map的格式，但是一般从数据库来的数据都是bean格式，建议重写。

model 包下的export_sample.docx 为一个替换过程中被替换document.xml的壳，内容可以随意，可以理解为一个没有内容的word文件。

bean对象需要和模板文件对应。
