package cn.com.sparknet.common.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.com.sparknet.common.util.StringUtil;

@Repository ("fileDao")
public class FileDao {
	@Resource
    BaseDao baseDao;
	@Autowired
    DataSource defaultDataSource;
	
	
	/*public Page FileuploadList(Map<String,Object> map)throws Exception{
		StringBuffer sql= new StringBuffer();
        List<String> list = new ArrayList<String>();
        String start = StringUtil.nullToEmpty(map.get("page"));
        String end = StringUtil.nullToEmpty(map.get("rows"));
        
        sql.append("select id,filename,filetype,filesize,to_char(filedate,'yyyy-mm-dd') filedate,fileeditor,filecontent,FILEURL from T_FILEUPLOAD ");
        sql.append(" where  1=1 ");
        if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("txtfilename")))){
			sql.append(" and filename LIKE ? ");
			list.add("%"+StringUtil.nullToEmpty(map.get("txtfilename"))+"%");
		}if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("startDatetime"))) && StringUtil.isNotEmpty(StringUtil.nullToEmpty(map.get("endDatetime")))){
			sql.append(" and  filedate >= to_date(?,'yyyy/mm/dd') ");
			list.add(StringUtil.nullToEmpty(map.get("startDatetime")));
			sql.append(" and filedate < to_date(?,'yyyy/mm/dd') +1 ");
			list.add(StringUtil.nullToEmpty(map.get("endDatetime")));
		}
        return baseDao.findPageAllBySql(sql.toString(),start,end,list.toArray());
	}*/
	
	
	/*public int FileAdd(Map<String, Object> map)throws Exception{
		 StringBuffer sql= new StringBuffer();
		 List<String> list = new ArrayList<String>();
		 sql.append("insert into T_FILEUPLOAD (id,filename,filetype,filesize,filedate,fileeditor,filecontent,fileurl) values(");
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("ID")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("FILENAME")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("FILETYPE")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("FILESIZE")));
		 sql.append("to_date(?,'yyyy-mm-dd,hh24:mi:ss'),");
		 list.add(StringUtil.nullToEmpty(map.get("FILEDATE")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("FILEEDITOR")));
		 sql.append("?,");
		 list.add(StringUtil.nullToEmpty(map.get("FILECONTENT")));
		 sql.append("? )");
		 list.add(StringUtil.nullToEmpty(map.get("FILEURL")));
		 return baseDao.updateBySql(sql.toString(),list.toArray());
   }*/
	
	/**
	 * 根据文件id查询文件路径
	 * @param map
	 * @return
	 * @throws Exception
	 */
	/*public List<Map<String, Object>> FilebyId(Map<String, Object> map)throws Exception{
		StringBuffer sql=new StringBuffer();
		String fileid = StringUtil.nullToEmpty(map.get("fileid"));
        sql.append(" SELECT * FROM T_FILEUPLOAD where ID=?");
        return baseDao.findListBySql(sql.toString(), new Object[] {fileid});
    }
	
	public int FileDel(String fileid)throws Exception{
		if(StringUtil.isNotEmpty(StringUtil.nullToEmpty(fileid))){
			 String sql=" delete from T_FILEUPLOAD where ID= ?";	
			 return baseDao.updateBySql(sql.toString(),new Object[] {StringUtil.nullToEmpty(fileid)});
		 }else{
			 return 0;
		 }
    }*/
}
