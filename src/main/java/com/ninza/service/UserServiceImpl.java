package com.ninza.service;

import java.io.File;
import java.io.IOException;

import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumWriter;

import com.ninza.avro.User;

public class UserServiceImpl {
	
	/**
	 * 사용자 정보를 받아서 Avro 직렬화를 사용하여 직렬화한 후 파일로 저장한다.
	 * <p> 저장폴더는 "/userData" 
	 * @param user	사용자 정보 
	 * @return	사용자 정보가 저장된 파일 주
	 */
	public String saveUser(User user) {
		// Avro Serialize & File writer
		DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
		DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
		
        // 사용자 정보를 저장할 디렉토리가 있는지 확인
		checkUserDataDir();
	
        // 저장할 파일명 생성
		String fileName = "user_"+user.getEmail()+".avro";
		File file = new File("./userData/"+fileName);

		try {
            // Serialize하여 디스크에 저장한다.     
			dataFileWriter.create(user.getSchema(), file);
			dataFileWriter.append(user);
		
			dataFileWriter.close();
		} catch (IOException e) {
			throw new RuntimeException("디스크 IO 에러입니다.");
		}
		return file.getAbsolutePath();
	}

	/**
	 * <p> 사용자 정보를 저장할 폴더가 있는지 확인한 후 없으면 생성한다.
	 * 
	 * @return File 사용자 정보를 저장할 폴더
	 */
	private File checkUserDataDir() {
		File file = new File("./userData");
		if(!file.exists())
			file.mkdir();
		return file;
	}
}