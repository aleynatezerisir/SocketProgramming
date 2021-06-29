package server;
import java.io.BufferedReader;
import java.util.HashMap;

public class HashTable {
	
	public static HashMap<Integer, String> map = new HashMap<Integer, String>();
	
	public static void main(String[] args) {
		
		
		map = HashMapFromTextFile();
	    System.out.println(map);
	}
	static int HashFunction(String s, int M) {
        long sum = 0, mul = 1;
        for (int i = 0; i < s.length(); i++) {
            mul = (i % 4 == 0) ? 1 : mul * 256;
            sum += s.charAt(i) * mul;
        }
        return (int)(Math.abs(sum) % M);
    }
	public static HashMap<Integer, String> HashMapFromTextFile() {
		BufferedReader br = null;
		try {
			
			ReadFile.main(null);
			
			for(int i=0;i<ReadFile.StringList.size();i++) {
				int key = HashFunction(ReadFile.StringList.get(i),Integer.parseInt(ReadFile.numOfNodes));
				map.put(key,ReadFile.StringList.get(i));
			}
            System.out.println("Size of map is: "
                    + map.size());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
		finally {
			  
            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
  
        return map;
	}
}
