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
		} catch (Exception e) {
			System.out.println("Error ocuured while ecrypting :" + strValue);
			e.printStackTrace();
		}
		return encryptedValue;
	}
	
	@GetMapping(path = "/uniquereqid")
	public String GetUniqueReqId() {
		String uniqueReqId = null;
		try {
			uniqueReqId = roopyaMoneyUtilityService.getUniqueReqId();
		} catch (Exception e) {
			System.out.println("Error ocuured while generating uniqueReqId");
			e.printStackTrace();
		}
		return uniqueReqId;
	}

}
