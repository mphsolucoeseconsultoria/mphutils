package br.com.mph.mphutils.tests.reflection;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import br.com.mph.mphutils.reflection.ReflectionUtils;

public class ReflectionUtilTest {

	private Client cliente;

	@Before
	public void setUp() throws Exception {
		cliente = new Client("12345678900", "MARCOS ANDRE");
	}

	@Test
	public final void testListClassTypeAttributes() {
		assertTrue(ReflectionUtils.listClassTypeAttributes(String.class, cliente).size() == 2);
	}

	@Test
	public final void testGetAttributeValue() {
		assertTrue(ReflectionUtils.getAttributeValue(cliente, "cpf").equals("12345678900"));
	}

}
