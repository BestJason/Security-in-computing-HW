import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Q1_c {

	public static void main(String[] args) {
		
		String cipherText = "GBSXUCGSZQGKGSQPKQKGLSKASPCGBGBKGUKGCEUKUZKGGBSQEICACGKGCEUERWKLKUPKQQGCIICUAEUVSHqKGCEUPCGBCGQOEVSHUNSUGKUZCGQSNLSHEHIEEDCUOGEPKHZGBSNKCUGSUKUASERLSKASCUGBSLKACRCACUZSSZEUSBEXHKRGSHWKLKUSQSKCHQTXKZHEUQBKZAENNSUASZFENFCUOCUEKBXGBSWKLKUSQSKNFKQQKZEHGEGBSXUCGSZQGKGSQKUZBCQAEIISKOXSZSICVSHSZGEGBSQSAHSGKHMERQGKGSKREHNKIHSLIMGEKHSASUGKNSHCAKUNSQQKOSPBCISGBCqHSLIMQGKGSZGBKGCGQSSNSZXQSISQQGEAEUGCUXSGBSSJCqGCUOZCLIENKGCAUSOEGCKGCEUqCGAEUGKCUSZUEGBHSKGEHBCUGERPKHEHKHNSZKGGKAD";

		cipherText = cipherText.toUpperCase();
		
		String[] strArr = cipherText.split("");
		
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		
		for (String str : strArr) {
			
			int fre = 1;
			
			if (hm.containsKey(str)) {
				fre = hm.get(str);
				fre++;
			}
			
			hm.put(str, fre);
		}
				
		Character[] elc = {
				'E', 'T', 'A', 'I', 'N', 'O', 'S', 'R', 'H', 'D', 'C', 'L', 'M',
				'P', 'U', 'G', 'W', 'F', 'B', 'Y', 'V', 'J', 'K', 'X', 'Q', 'Z'
				};
		
		for (Character c : elc) {
			if (!hm.containsKey(c.toString())) {
				hm.put(c.toString(), 0);
			}
		}
		
		ArrayList<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(hm.entrySet());
		
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });
		
		System.out.println(list);
		
		
				
//		System.out.print("{ ");
		
		for (int i = 0; i < elc.length; i++) {
//			System.out.print(elc[i]);
//			System.out.print("-");
//			System.out.print(list.get(i).getKey());
//			System.out.print(",");
			cipherText = cipherText.replace(list.get(i).getKey().charAt(0), Character.toLowerCase(elc[i]));
		}
		
//		System.out.println(" }");
		
		System.out.println(cipherText.toUpperCase());
		
		
	}
}
