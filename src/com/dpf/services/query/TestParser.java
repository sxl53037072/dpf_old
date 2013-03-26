package com.dpf.services.query;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.dpf.exception.ApplicationException;

public class TestParser
{

    public static void main(String[] args) throws ApplicationException
    {
        SQLParser sqlParser = new SQLParser();
        try
        {
            FileInputStream in = new FileInputStream("C:\\Users\\Administrator\\Desktop\\sql.txt");
            StringBuffer sb = new StringBuffer();
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            while ((byteread = in.read(tempbytes)) != -1)
            {
                sb.append(new String(tempbytes, 0, byteread));
            }

            in.close();
            String sql = sb.toString();
            sqlParser.setSql(sql);
            Element paramElement = DocumentHelper.createElement("params");
            Element param = paramElement.addElement("param");
            param.addAttribute("name", "status_flag");
            param.addAttribute("type", "STRING");
//            param.addAttribute("isMultiple", "0BT");
            param.setText("2");
            Element param2 = paramElement.addElement("param");
            param2.addAttribute("name", "IMGID");
            param2.addAttribute("type", "STRING");
            param2.setText("666");
            
            sqlParser.setParamElement(paramElement);
            sqlParser.parse();
            System.out.println(sqlParser.getSql());
            List list = sqlParser.getParamList();
            for (int i = 0, len = list.size(); i < len; i++)
            {
                ParamInfo info = (ParamInfo) list.get(i);
                System.out.println(info.getValue());
            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
