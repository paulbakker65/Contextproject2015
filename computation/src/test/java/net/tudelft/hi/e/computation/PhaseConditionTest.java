package net.tudelft.hi.e.computation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.GregorianCalendar;

import net.tudelft.hi.e.data.DateValue;

import org.junit.Before;
import org.junit.Test;

/**
 * Class for testing PhaseCondition.
 */
public class PhaseConditionTest {
	private PhaseCondition condition;
	
	/**
	 * Setup method.
	 */
	@Before
	public void setUp() {
		condition = new PhaseCondition();
		assertTrue(condition.matches(new DateValue(new GregorianCalendar(2015, 5, 10))));
	}
	
	@Test
	public void testMatchesNoNewChunk() {
		assertTrue(condition.matches(new DateValue(new GregorianCalendar(2015, 5, 10))));
		assertTrue(condition.matches(new DateValue(new GregorianCalendar(2015, 5, 11))));
		assertTrue(condition.matches(new DateValue(new GregorianCalendar(2015, 5, 30))));
		assertEquals(0, condition.chunkIndex);
	}
	
	@Test
	public void testMatchesNewChunk1() {
		assertFalse(condition.matches(new DateValue(new GregorianCalendar(2015, 6, 15))));
		assertEquals(1, condition.chunkIndex);
	}
	
	@Test
	public void testMatchesNewChunk2() {
		assertFalse(condition.matches(new DateValue(new GregorianCalendar(2015, 7, 14))));
		assertEquals(2, condition.chunkIndex);
	}
	
	@Test
	public void testMatchesNewChunk3() {
		assertFalse(condition.matches(new DateValue(new GregorianCalendar(2015, 9, 14))));
		assertEquals(3, condition.chunkIndex);
	}
	
	@Test
	public void testMatchesNoNewChunk3() {
		assertFalse(condition.matches(new DateValue(new GregorianCalendar(2015, 9, 14))));
		assertEquals(3, condition.chunkIndex);
		assertTrue(condition.matches(new DateValue(new GregorianCalendar(2015, 9, 15))));
		assertEquals(3, condition.chunkIndex);
	}
}
