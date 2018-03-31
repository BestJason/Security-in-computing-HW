import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;


public class Q2 {
	
	public static ArrayList<String> tmpArr = new ArrayList<String>();
	
	public static ArrayList<String> poss_keys = new ArrayList<String>();
	
	public static String API = "http://fy.iciba.com/ajax.php?a=fy&f=en&t=auto&w=";

	public static void main(String[] args) {
		
		char[] c1 = {'K', 'H', 'H', 'L', 'T', 'K'};
		char[] c2 = {'K', 'T', 'H', 'L', 'L', 'E'};
		
		HashMap<Character, String> dic = new HashMap<Character, String>();
		
		dic.put('E', "000");
		dic.put('H', "001");
		dic.put('I', "010");
		dic.put('K', "011");
		dic.put('L', "100");
		dic.put('R', "101");
		dic.put('S', "110");
		dic.put('T', "111");
				
		HashMap<String, Character> dic2 = new HashMap<String, Character>();
		
		for (char k : dic.keySet()) {
			dic2.put(dic.get(k), k);
		}
			
		// c1 xor c2
		String[] p3 = new String[c1.length];
		
		for(int i = 0; i < p3.length; i++) {
			int key = getIntFromBinaryString(dic.get(c1[i])) ^ getIntFromBinaryString(dic.get(c2[i]));
			String k = getBinaryStringFromInt(key);
			p3[i] = k;
		}
		

		ArrayList<ArrayList<HashMap<ArrayList<String>, ArrayList<String>>>> allposs = new ArrayList<ArrayList<HashMap<ArrayList<String>, ArrayList<String>>>>();
				
		ArrayList<HashMap<ArrayList<String>, ArrayList<String>>> all = null;
		
		HashMap<ArrayList<String>, ArrayList<String>> poss = null;
		
		for (int cs = 0; cs < c1.length; cs ++) {
			
			all = new ArrayList<HashMap<ArrayList<String>, ArrayList<String>>>();
					
			for (int i = 0; i < dic.keySet().size(); i++) {
				
				poss = new HashMap<ArrayList<String>, ArrayList<String>>();
												
				ArrayList<String> p1 = new ArrayList<String>();
				
				ArrayList<String> p2 = new ArrayList<String>();
				
				String p1w = dic.keySet().toArray()[i].toString();
					
				p1.add(p1w);
				
				for (int j = 0; j < dic.keySet().size(); j++) {
					
					String p2w = dic.keySet().toArray()[j].toString();
												
					if ((getIntFromBinaryString(dic.get(p1w.charAt(0))) ^ getIntFromBinaryString(dic.get(p2w.charAt(0)))) == getIntFromBinaryString(p3[cs])) {
						
						p2.add(p2w);
						
					}
					
				}
								
				poss.put(p1, p2);
				
				all.add(poss);
			}
					
			allposs.add(all);
			
		}
		printResult(dic, 6, c1, c2, dic2);			
	}

	/**
	 * @param key
	 * @return
	 */
	private static String getBinaryStringFromInt(int key) {
		String k = Integer.toBinaryString(key);
		if (k.length() < 3) {
			StringBuilder sb = new StringBuilder(); 
			for (int j = 0;j < 3-k.length(); j++) {
				sb.append('0');
			}
			k = sb.toString() + k;
		}
		return k;
	}
	
	public static int getIntFromBinaryString(String s) {
		return Integer.valueOf(s, 2);
	}
	
	public static void repeatableArrangement(int k, String[] arr){
        if(k==1){
            for(int i=0;i<arr.length;i++){
                tmpArr.add(arr[i]);
                poss_keys.add(String.join("", tmpArr));
                tmpArr.remove(tmpArr.size()-1); 
            }
        }else if(k >1){
            for(int i=0;i<arr.length;i++){
                tmpArr.add(arr[i]);
                repeatableArrangement(k - 1, arr); 
                tmpArr.remove(tmpArr.size()-1); 
            }
        } else {
        		return;
        }
        
    }
	
	public static void printResult(HashMap<Character, String> dic, int num, char[] c1, char[] c2, HashMap<String, Character> dic2) {
		
		int count = 0;
		
		Set<Character> keys = dic.keySet();
		
		StringBuilder sb = new StringBuilder();
		
		for (Character c : keys) {
			sb.append(c);
		}
		
		repeatableArrangement(num, sb.toString().split(""));
		for (String pk : poss_keys) {
			StringBuilder pt1 = new StringBuilder();
			StringBuilder pt2 = new StringBuilder();
			
			for (int i = 0; i < num; i++) {
				
				int p1 = getIntFromBinaryString(dic.get(c1[i])) ^ getIntFromBinaryString(dic.get(pk.charAt(i)));
				int p2 = getIntFromBinaryString(dic.get(c2[i])) ^ getIntFromBinaryString(dic.get(pk.charAt(i)));
				
				pt1.append(dic2.get(getBinaryStringFromInt(p1)));
				pt2.append(dic2.get(getBinaryStringFromInt(p2)));
				
			}
			count++;
			String plain1 = pt1.toString().toLowerCase();
			String plain2 = pt2.toString().toLowerCase();
						
			if(check_for_word(plain1) && check_for_word(plain2))
				System.out.println("p1: "+plain1+"--------p2: "+plain2+"--------key: "+pk);
		}
		
		System.out.println(count);
	}
	
	public static boolean check_for_word(String word) {
        // System.out.println(word);
        try {
            BufferedReader in = new BufferedReader(new FileReader(
                    "/usr/share/dict/web2"));
            String str;
            while ((str = in.readLine()) != null) {
                if (str.equals(word)) {
                    return true;
                }
            }
            in.close();
        } catch (IOException e) {
        }

        return false;
    }

}

