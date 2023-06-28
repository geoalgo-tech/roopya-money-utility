package roopya.money.utility.service;

import java.util.Base64;
import java.util.Random;
import java.util.regex.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.boot.web.servlet.server.Encoding;
import org.springframework.stereotype.Service;


//import io.github.novacrypto.hashing.Sha256;



@Service
public class RoopyaMoneyUtilityService {
	
	private final Random random = new Random();
	 
    private final char[] buf;
    private final int length = 20;
	
	public String getEncryptedTextForHDFC(String text) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

		byte[] keyBytes = new byte[16];

		String key = "HDFCBANK!@#987MOBAPP";

		byte[] b = key.getBytes("UTF-8");

		int len = b.length;

		if (len > keyBytes.length)
			len = keyBytes.length;

		System.arraycopy(b, 0, keyBytes, 0, len);

		SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
		
		System.out.println(results.toString());
		
		//return HttpRequest.Base64.encodeBytes(results);
		//return results.toString();
		
		return Base64.getEncoder().encodeToString(results);
		
		//return null;
	}

	
 
    public RoopyaMoneyUtilityService() {
 
        if (length < 1)
            throw new IllegalArgumentException("length < 1: " + length);
        buf = new char[length];
    }
	
	public String getUniqueReqId() {
		 
        char[] symbols;
 
        StringBuilder tmp = new StringBuilder();
        for (char ch = '0'; ch <= '9'; ++ch)
            tmp.append(ch);
        for (char ch = 'A'; ch <= 'Z'; ++ch)
            tmp.append(ch);
        symbols = tmp.toString().toCharArray();
 
        for (int idx = 0; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }
	
	
	/*
	public String generateToken(String str_Key, String deviceId, String mobileNumber)
    {
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Start");
		System.out.println("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber + " Start");
        
        String str_Salt = RegexState.Replace(deviceId, "[^a-zA-Z0-9]", "");
       
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_Salt : " + str_Salt);
        
        String str_Date = System.DateTime.Today.ToString("yyyy") + "0" + System.DateTime.Today.ToString("MM") + System.DateTime.Today.ToString("dd");
        
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_Date : " + str_Date);
        
        String str_1 = str_Salt + "." + str_Date + "." + str_Key;
        
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_1 : " + str_1);
        
        String str_2 = getHashSha256(str_1);
        
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_2 : " + str_2);
        
        String str_3 = "{\"alg\":\"HS256\",\"typ\":\"JWT\"}";
        
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_3 : " + str_3);
        
        String str_4 = (getHashSha256(str_3));
        
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_4 : " + str_4);
        
        //if (String.IsNullOrEmpty(mobileNumber))
        if(null == mobileNumber || mobileNumber.isEmpty()) 
        {
            mobileNumber = "0";
        }
        
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " ");
        
        String str_5 = "{\"tokenId\":" + "87241165" + ",\"userId\":\"" + deviceId + "\",\"username\":\"Guest\",\"validity\":\"2016-05-07 17:07\"}";  //tokenId as deviceId & userId as Mobile No for blank is 0
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_5 : " + str_5);
        String str_6 = (getHashSha256(str_5));
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_6 : " + str_6);
        String str_8 = str_4 + str_6;
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_8 : " + str_8);
        String str_7 = CreateToken(str_8, str_2);
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_7 : " + str_7);
        String str_9 = str_8 + str_7;
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " Response :: str_9 : " + str_9);
        //ErrorLog("generateToken :: str_Key: " + str_Key + " deviceId: " + deviceId + " mobileNumber: " + mobileNumber, " End");

        return str_9;
    }

	 

	
	
	public static String getHashSha256(String text)
    {
        byte[] bytes = Encoding.UTF8.GetBytes(text);
        SHA256Managed hashstring = new SHA256Managed();
        byte[] hash = hashstring.ComputeHash(bytes);
        String hashString = String.Empty;
        for(byte x in hash)
        {
            hashString += String.Format("{0:x2}", x);
        }
        return hashString;
    }

	 
	private String CreateToken(String message, String secret)
    {
        secret = secret == null ? "" : secret;
        var encoding = new System.Text.ASCIIEncoding();
        byte[] keyByte = encoding.GetBytes(secret);
        byte[] messageBytes = encoding.GetBytes(message);
        using (var hmacsha256 = new HMACSHA256(keyByte))
        {
            byte[] hashmessage = hmacsha256.ComputeHash(messageBytes);
            return Convert.ToBase64String(hashmessage);
        }
        
    }

   */
	
}
