package roopya.money.utility.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import roopya.money.utility.service.RoopyaMoneyUtilityService;

@RestController
public class RoopyaMoneyUtilityController {
	
	@Autowired
	RoopyaMoneyUtilityService roopyaMoneyUtilityService;
	
	@GetMapping(path = "/encrypt/{strValue}")
	public String encrypt(@PathVariable String strValue) {
		String encryptedValue = null;
		try {
			encryptedValue = roopyaMoneyUtilityService.getEncryptedTextForHDFC(strValue);
			System.out.println("Encrypted values of " + strValue + " is :" + encryptedValue);
		} catch (Exception e) {
			System.out.println("Error occurred while ecrypting :" + strValue);
			e.printStackTrace();
		}
		return encryptedValue;
	}
	
	@GetMapping(path = "/uniquereqid")
	public String getUniqueReqId() {
		String uniqueReqId = null;
		try {
			uniqueReqId = roopyaMoneyUtilityService.getUniqueReqId();
			System.out.println("The UniqueReqId is : " + uniqueReqId);
		} catch (Exception e) {
			System.out.println("Error occurred while generating uniqueReqId");
			e.printStackTrace();
		}
		return uniqueReqId;
	}
	
	@GetMapping(path = "/generateToken/{str_Key}/{deviceId}/{mobileNumber}")
	public String generateToken(@PathVariable String str_Key, @PathVariable String deviceId,@PathVariable String mobileNumber) {
		String theToken = null;
		try {
			theToken = roopyaMoneyUtilityService.generateToken(str_Key, deviceId, mobileNumber);
			System.out.println("The Token is : " + theToken);
		} catch (Exception e) {
			System.out.println("Error occurred in generateToken");
			e.printStackTrace();
		}
		return theToken;
	}

}
