package exam.file.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exam.file.dao.IFileDao;
import exam.file.model.FileVO;

@Service
public class FileService implements IFileService{
	
	
	@Autowired
	private IFileDao dao;
	/**
	* Method : nowFileCodeNumber
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일 최신 코드를 가져온다.
	* 
	*/
  	public Integer nowFileCodeNumber(){
  		return dao.nowFileCodeNumber();
  	}
  	
  	/**
	* Method : insertFile
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :파일을 생성한다. 
	*/
  	public int insertFile(FileVO fileVo){
  		return dao.insertFile(fileVo);
  	}
  	
  	
  	/**
	* Method : selectFilebyPostCode
	* 작성자 : pc20
	* 변경이력 :
	* @return
	* Method 설명 :포스트 코드에 해당하는 파일들을 가져온다. 
	*/
  	public List<FileVO> selectFilebyPostCode(String postCode){
  		return dao.selectFilebyPostCode(postCode);
  	}
	
}	
