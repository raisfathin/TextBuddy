import java.util.ArrayList;
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
	
	public String[] search(String keyword) {
		//TODO
		return null;
	}
	
	public void clear() {
		texts.clear();
	}
}
