import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import fr.esiea.glpoo.model.dao.csv.CsvFaceDao;
import fr.esiea.glpoo.model.dao.csv.OpenCsvFaceDao;
import fr.esiea.glpoo.model.domain.Face;

public class TestingStuff {

	private static final Logger LOGGER = Logger.getLogger(Main.class);

	public static void main(String[] args) {

		String[] n_element = { "1", "2", "3", "4", "5" };
		List<String[]> combi_possible = new ArrayList<String[]>();
		int i, j, k, l = 0;
		// for(i=0; i<n_element.length;i++){
		// for(j=0; j<n_element.length;j++){
		// for(k=0; k<n_element.length;k++){
		// for(l=0; l<n_element.length;l++){
		// combi_possible.add(n_element[i]+","+n_element[j]+","+n_element[k]+","+n_element[l]);
		// }
		// }
		// }
		// }

		for (String s1 : n_element) {
			for (String s2 : n_element) {
				for (String s3 : n_element) {
					for (String s4 : n_element) {
						// for(String s5 : n_element){
						combi_possible.add(new String[] { s1, s2, s3, s4 });
						// }
					}
				}
			}
		}

		// List<String> l1 = new ArrayList<String>();
		// for(String[] s : combi_possible){
		// l1.add(Arrays.toString(s));
		// }
		//
		// System.out.println(l1);
		// String[] sa = new String[]{"1","1","1","1"};
		// System.out.println(l1.contains(Arrays.toString(sa)));

		// String result ="";
		// for(String[] s : combi_possible){
		// String e ="[";
		// for(String a : s){
		// e+=a+",";
		// }
		// e+="]";
		// result+=e;
		// }
		// System.out.println(result);

		for (String[] s : combi_possible) {
			System.out.print(Arrays.toString(s));
		}

		// String[] test = new String[]{"1","1","1","1"};
		// test = shiftRight(test);
		// System.out.println(Arrays.toString(test));

		System.out.println();
		System.out.println(combi_possible.size());

		// String[] test2 = new String[]{"1","2","3"};
		// String[] test3 = new String(test2);
		// test3 = shiftRight(test3);
		// System.out.println(Arrays.toString(test2));

		// Boolean skip = false;
		// for(i=combi_possible.size()-2;i>1;i--){
		// String[] s = combi_possible.get(i).clone();
		// for(j=0;j<3;j++){
		// s = shiftRight(s);
		// String temp = Arrays.toString(s);
		// System.out.println(temp);
		// for(k=i-1;k>1;k--){
		// if(skip){
		// skip = false;
		// break;
		// }
		// String[] s2 = combi_possible.get(k).clone();
		// String temp2 = Arrays.toString(s2);
		// if(k==7){
		// System.out.println("om");
		// }
		// if(temp.contains(temp2)){
		// combi_possible.remove(k);
		// skip = true;
		// }
		// }
		//
		// }
		// }

		// Boolean skip = false;
		for (i = 1; i < combi_possible.size() - 1; i++) {
			String[] s = combi_possible.get(i).clone();
			for (j = 0; j < 3; j++) {
				s = shiftRight(s);
				String temp = Arrays.toString(s);
				for (k = i + 1; k < combi_possible.size() - 1; k++) {
					String[] s2 = combi_possible.get(k).clone();
					String temp2 = Arrays.toString(s2);
					if (temp.contains(temp2)) {
						combi_possible.remove(k);
					}
				}

			}
		}

		for (i=combi_possible.size()-1;i>=0;i--) {
			String[] tmp = combi_possible.get(i).clone();
			String tmptest = Arrays.toString(tmp);
			
			if(tmptest.matches(".*1.{5}1.*")){ 
				System.out.println(tmptest);
				combi_possible.remove(i);
			}
		}
		
		
//		for (String[] s : combi_possible) {
//			System.out.print(Arrays.toString(s));
//		}

		System.out.println();
		System.out.println(combi_possible.size());
		
		int id = 1;
		for(String[] s : combi_possible){
			System.out.println("P "+id+" "+s[0]+" "+s[1]+" "+s[2]+" "+s[3]);
			id+=1;
		}

	}

	private static String[] shiftRight(String[] tab) {
		int size = tab.length;
		String temp = tab[size - 1];
		for (int i = tab.length - 1; i > 0; i--) {
			tab[i] = tab[i - 1];
		}
		tab[0] = temp;
		return tab;
	}

}
