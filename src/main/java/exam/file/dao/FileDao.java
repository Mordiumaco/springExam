package exam.file.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import exam.file.model.FileVO;

@Repository
public class FileDao implements IFileDao{
	
	@Resource(name="sqlSessionTemplate")
	SqlSessionTemplate template;
	
	/**
	* Method : nowFileCodeNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일 최신 코드를 가져온다.
	*/
	@Override
  	public Integer nowFileCodeNumber(){
		
		try {
			Integer nowFileCode = template.selectOne("fileSQL.nowFileCodeNumber");
			return nowFileCode;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
  	}
  	
  	/**
	* Method : insertFile
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일을 생성한다. 
	*/
	@Override
  	public int insertFile(FileVO fileVo){
		
		try {
			int result = template.insert("fileSQL.insertFile", fileVo);
			
			return result;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return -1;
	}
  	
  	
  	/**
	* Method : selectFilebyPostCode
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :포스트 코드에 해당하는 파일들을 가져온다. 
	*/
	@Override
  	public List<FileVO> selectFilebyPostCode(String postCode){
		
		try {
			
			List<FileVO> fileList = template.selectList("fileSQL.selectFilebyPostCode", postCode);
			return fileList;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
