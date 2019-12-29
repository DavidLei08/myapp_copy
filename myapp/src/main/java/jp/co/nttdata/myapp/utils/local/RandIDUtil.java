package jp.co.nttdata.myapp.utils.local;

import java.util.UUID;

public class RandIDUtil {

	
	public static String getTenId(){
		return UUID.randomUUID().toString().replace("-","").substring(0,10);
		
	}
	
	public static String getFifteenId(){
		return UUID.randomUUID().toString().replace("-","").substring(0,15);
		
	}
	
	public static String getTwentyId(){
		return UUID.randomUUID().toString().replace("-","").substring(0,20);
		
	}
	
}
