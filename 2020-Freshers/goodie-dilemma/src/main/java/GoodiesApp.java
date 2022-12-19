import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GoodiesApp {

	static int noOfEmployee = 0;

	public static void main(String[] args) {
		List<Goodie> goodies = readGoodiesFromFile();
		selectGoodies(goodies, noOfEmployee);
		System.out.println("Distribution done, please look into file for the result.");
	}

	/**
	 * 
	 * @param goodies: list of goodies sorted in ASC order
	 * @param M:       No of employee among which this goodies needs to be
	 *                 distributed
	 */
	static public void selectGoodies(List<Goodie> goodies, int M) {
		int windowSize = M;

		// sorting the goodies list in ASC order by price
		// intention behind doing this to get the min & max of each window
		mergeSort(goodies, 0, goodies.size() - 1);

		int start = 0;
		// defining window size as number employee
		int end = windowSize - 1;
		long min = Long.MAX_VALUE;
		int j = 0;

		// using sliding window concept to iterate on each window
		// find the difference b/w max & min of each window
		// comparing with prev min

		// i is the start of window
		for (int i = 0; i <= goodies.size() - windowSize; i++) {

			// calculating end point of a window
			j = i + windowSize - 1;
			// calculating diff of min & max
			long diff = goodies.get(j).getPrice() - goodies.get(i).getPrice();

			// comparing with prev min
			if (diff < min) {
				start = i;
				end = j;
				min = diff;
			}
		}

		// Writing to file
		writeToFile(goodies, start, end);
	}

	/**
	 * 
	 * @param goodies: list of goodies sorted in ASC order
	 * @param start:   start index of the window
	 * @param end:     end index of the window
	 */
	static void writeToFile(List<Goodie> goodies, int start, int end) {
		File file = new File("output.txt");
		FileWriter fr = null;
		BufferedWriter br = null;
		try {
			fr = new FileWriter(file);
			br = new BufferedWriter(fr);
			br.write("The goodies selected for distribution are:\n");
			br.write("\n");
			for (int i = start; i <= end; i++) {
				br.write(goodies.get(i).toString());
				br.write("\n");
			}
			br.write("\n");
			br.write("And the difference between the chosen goodie with highest price and the lowest price is "
					+ (goodies.get(end).getPrice() - goodies.get(start).getPrice()));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * utility method to read goodies from input file and create Goodie object and
	 * collect in a list
	 * 
	 * @return
	 */
	static List<Goodie> readGoodiesFromFile() {
		String fileName = "input.txt";
		List<Goodie> goodies = new ArrayList<Goodie>();
		BufferedReader buffer = null;
		int M = 0;
		try {
			String line = "";
			buffer = new BufferedReader(new FileReader(fileName));
			int linecount = 1;

			while ((line = buffer.readLine()) != null) {
				if (linecount == 1) {
					M = Integer.parseInt(line.split(":")[1].trim());
				} else if (linecount >= 5) {
					String goodieInfo[] = line.split(":");
					String goodieName = goodieInfo[0];
					long price = Long.parseLong(goodieInfo[1].trim());
					Goodie goodie = new Goodie(goodieName, price);
					goodies.add(goodie);
				}
				linecount++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (buffer != null) {
				try {
					buffer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		noOfEmployee = M;
		return goodies;
	}

	/**
	 * Utility to sort goodies
	 * 
	 * @param goodies
	 * @param left
	 * @param right
	 */
	public static void mergeSort(List<Goodie> goodies, int left, int right) {
		int mid = (left + right) / 2;
		if (left < right) {
			mergeSort(goodies, left, mid);
			mergeSort(goodies, mid + 1, right);
			mergeArray(goodies, left, right, mid);

		}
	}

	/**
	 * 
	 * Program to sort the goodies in ascending order on price attribute
	 * 
	 * @param goodies
	 * @param left
	 * @param right
	 * @param mid
	 */
	public static void mergeArray(List<Goodie> goodies, int left, int right, int mid) {
		int size1 = mid - left + 1;
		int size2 = right - mid;

		List<Goodie> g1 = new ArrayList<Goodie>();
		List<Goodie> g2 = new ArrayList<Goodie>();

		for (int i = 0; i < size1; i++) {
			g1.add(goodies.get(left + i));
		}
		for (int i = 0; i < size2; i++) {
			g2.add(goodies.get(mid + 1 + i));
		}
		int l = 0, r = 0;
		int k = left;
		while (l < size1 && r < size2) {
			if (g1.get(l).getPrice() <= g2.get(r).getPrice()) {
				goodies.set(k++, g1.get(l));
				l++;
			} else {
				goodies.set(k++, g2.get(r));
				r++;
			}
		}
		while (l < size1) {
			goodies.set(k++, g1.get(l));
			l++;
		}
		while (r < size2) {
			goodies.set(k++, g2.get(r));
			r++;
		}

	}

}
