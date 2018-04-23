package ec.bits.group.bpm;

import org.junit.Assert;
import org.junit.Test;

import ec.group.bits.bpm.tasks.TaskURIUtil;

public class LinkTest {
	
	private static final String FORM_KEY = "app:manager/pizza/approveOrder.xhtml";
	private static final String CONTEXT_PATH = "localhost:8080/pizza-order/";
	
	@Test
	public void testLinkForm () {
		TaskURIUtil taskURIUtil = new TaskURIUtil ();
		String link = taskURIUtil.replaceAppKeyword (FORM_KEY, CONTEXT_PATH);
		System.out.println(link);
		Assert.assertEquals(link, "localhost:8080/pizza-order/manager/pizza/approveOrder.xhtml");
	}
}
