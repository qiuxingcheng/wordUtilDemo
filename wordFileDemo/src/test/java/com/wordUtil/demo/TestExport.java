package com.wordUtil.demo;

import com.wordUtil.bean.*;
import com.wordUtil.util.FreeMarkerHelper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.*;
import org.springframework.util.Assert;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class TestExport {

    private Log logger = LogFactory.getLog(this.getClass());

    @Test
    public  void testExportDocx(){
        String toPath = "outFile/export_"+ System.currentTimeMillis() +".docx";
        ExportVO exportVO = getTestExportVO(); // ...
        //
        Assert.notNull(exportVO, "exportVO 不能为 null");
        //
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(toPath);
            FreeMarkerHelper.parse2DocxAndClose(exportVO, fileOutputStream);
            logger.info("输出到" + toPath);
        } catch (Exception e) {
            logger.error(e);
        }
    }


    public ExportVO getTestExportVO(){
        ExportVO exportVO=new ExportVO();

        //插入header数据
        WordTableHeaderVO wordTableHeaderVO=new WordTableHeaderVO();

        //插入header的列数据
        List<WordTableColumnVO> wordTableColumnVOList=new ArrayList<>(  );
        for (int i = 0; i <10 ; i++) {
            WordTableColumnVO wordTableColumnVO=new WordTableColumnVO();
            wordTableColumnVO.setTitle( "11111" );
            wordTableColumnVO.setWidth( 100 );
            wordTableColumnVOList.add(wordTableColumnVO  );
        }
        wordTableHeaderVO.setColumns( wordTableColumnVOList );


        //插入tr中的数据
        List<WordTableRowVO>  WordTableRowVOlist=new ArrayList<>(  );
        //tr
        for (int i = 0; i <10 ; i++) {
            WordTableRowVO wordTableRowVO=new WordTableRowVO();
            //td
            List<WordTableCellVO> cells =new ArrayList<>(  );
            for (int j = 0; j <10 ; j++) {
                WordTableCellVO cellVO=new WordTableCellVO();
                cellVO.setText( "这是第"+j );
                cells.add( cellVO );
            }
            wordTableRowVO.setCells( cells );
            wordTableRowVO.setIndex(  i );

            WordTableRowVOlist.add( wordTableRowVO );
        }

        exportVO.setHeader(wordTableHeaderVO  );
        exportVO.setRows(  WordTableRowVOlist);

        return exportVO;
    }
}