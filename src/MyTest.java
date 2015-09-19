import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.security.Permission;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyTest {
	
	@SuppressWarnings("serial")
	protected static class ExitException extends SecurityException {
		private int status;
		
		public ExitException(int status) {
			this.status = status;
		}
		
		public int getStatus() {
			return status;
		}
	}
	
	private static class NoExitSecurityManager extends SecurityManager {
		@Override
		public void checkPermission(Permission perm) {
			
		}
		
		@Override
		public void checkPermission(Permission perm, Object context) {
			
		}
		
		@Override
		public void checkExit(int status) {
			super.checkExit(status);
			throw new ExitException(status);
		}
	}
	
	@Before
	public void setUp() {
		System.setSecurityManager(new NoExitSecurityManager());
	}
	
	@Test
	public void testInvalidArgumentFormat() {
		OutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		
		System.setOut(printStream);
		
		try {
			TextBuddy.main(new String[] {"tc", "hue"});
		} catch (ExitException e) {
			int status = e.getStatus();
			assertEquals(0, status);
		}
		
		String result = outputStream.toString();
		
		assertEquals("Usage: java TextBuddy <file>\n", result);
	}
	
	@Test
	public void testInvalidArgumentFormat2() {
		OutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		
		System.setOut(printStream);
		
		try {
			TextBuddy.main(new String[] {});
		} catch (ExitException e) {
			int status = e.getStatus();
			assertEquals(0, status);
		}
		
		String result = outputStream.toString();
		
		assertEquals("Usage: java TextBuddy <file>\n", result);
	}
	
	@Test
	public void testInput() {
		Runtime r = Runtime.getRuntime();
		try {
			Process p = r.exec("rm mytextfile.txt");
			p.waitFor();
		} catch (IOException e) {
			fail("Should be able to remove test file");
			e.printStackTrace();
		} catch (InterruptedException e) {
			fail("Should be able to remove test file");
			e.printStackTrace();
		}
		
		String input = "add little brown fox\ndisplay\nadd jumped over the moon\ndisplay\ndelete 2\ndisplay\nclear\ndisplay\ndelete 1\nadd foo\nadd bar\nadd baz\ndisplay\ndelete 4\ndelete 0\ndisplay\nclear\nexit\n";
		InputStream stringStream = new ByteArrayInputStream(input.getBytes());

		OutputStream outputStream = new ByteArrayOutputStream();
		PrintStream printStream = new PrintStream(outputStream);
		
		System.setIn(stringStream);
		System.setOut(printStream);
		
		TextBuddy.main(new String[] {"mytextfile.txt"});
		
		String result = outputStream.toString();
		assertEquals("Welcome to TextBuddy. mytextfile.txt is ready for use\ncommand: added to mytextfile.txt: \"little brown fox\"\ncommand: 1: little brown fox\ncommand: added to mytextfile.txt: \"jumped over the moon\"\ncommand: 1: little brown fox\n2: jumped over the moon\ncommand: deleted from mytextfile.txt: \"jumped over the moon\"\ncommand: 1: little brown fox\ncommand: all content deleted from mytextfile.txt\ncommand: mytextfile.txt is empty\ncommand: Invalid text index\ncommand: added to mytextfile.txt: \"foo\"\ncommand: added to mytextfile.txt: \"bar\"\ncommand: added to mytextfile.txt: \"baz\"\ncommand: 1: foo\n2: bar\n3: baz\ncommand: Invalid text index\ncommand: Invalid text index\ncommand: 1: foo\n2: bar\n3: baz\ncommand: all content deleted from mytextfile.txt\ncommand: ", result);
	}
	
	@Test
	public void testTextListBasic() {
		TextList texts = new TextList();
		assertEquals(0, texts.size());
		texts.addText("test1");
		assertEquals(1, texts.size());
		assertEquals("test1", texts.get(0));
		texts.addText("hue");
		assertEquals("test1", texts.get(0));
		assertEquals("hue", texts.get(1));
		texts.sort();
		assertEquals("hue", texts.get(0));
		assertEquals("test1", texts.get(1));
	}
	
	@Test
	public void testTextListClear() {
		TextList texts = new TextList();
		texts.clear();
		assertEquals(0, texts.size());
		texts.addText("test1");
		assertEquals(1, texts.size());
		assertEquals("test1", texts.get(0));
		texts.addText("hue");
		assertEquals("test1", texts.get(0));
		assertEquals("hue", texts.get(1));
		texts.sort();
		assertEquals("hue", texts.get(0));
		assertEquals("test1", texts.get(1));
		texts.clear();
		assertEquals(0, texts.size());
	}
	
	@Test
	public void testTextListRemove() {
		TextList texts = new TextList();
		try {
			texts.remove(0);
			//Unexpected
			fail("Should not be able to remove from empty TextList");
		} catch (IndexOutOfBoundsException e) {
			//Expected
		}
		texts.addText("ha");
		texts.addText("hue");
		texts.remove(1);
		assertEquals(1, texts.size());
		assertEquals("ha", texts.get(0));
	}
	
	@Test
	public void testTextListSearchNotFound() {
		TextList texts = new TextList();
		String[] result = texts.search("hue");
		assertArrayEquals(null, result);
		texts.addText("test1");
		texts.addText("test2");
		texts.addText("hue2");
		result = texts.search("text");
		assertArrayEquals(null, result);
	}
	
	@Test
	public void testTextListSearchFound() {
		TextList texts = new TextList();
		String[] result = texts.search("hue");
		assertArrayEquals(null, result);
		texts.addText("test1");
		texts.addText("test2");
		texts.addText("hue2");
		result = texts.search("test");
		assertArrayEquals(new String[] {"test1", "test2"}, result);
	}
	
	@After
	public void tearDown() {
		System.setSecurityManager(null);
	}


}
