import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TextList {
	List<String> texts = new ArrayList<String>();
	
	public void addText(String newText) {
		texts.add(newText);
	}
	
	public void remove(int index) throws IndexOutOfBoundsException, UnsupportedOperationException {
		texts.remove(index);
	}
	
	public int size() {
		return texts.size();
	}
	
	public String get(int index) throws IndexOutOfBoundsException {
		return texts.get(index);
	}
	
	public void sort() {
		Collections.sort(texts);
	}
	
	public Integer[] search(String keyword) {
		Integer[] result = new Integer[size()];
		int resultSize = 0;
		for (int i = 0; i < size(); i++) {
			String currentText = get(i);
			if (currentText.contains(keyword)) {
				result[resultSize++] = i;
			}
		}
		return Arrays.copyOf(result, resultSize);
	}
	
	public void clear() {
		texts.clear();
	}
}
