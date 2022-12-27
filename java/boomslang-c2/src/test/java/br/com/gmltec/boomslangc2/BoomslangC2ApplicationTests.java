package br.com.gmltec.boomslangc2;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.gmltec.boomslangc2.phy.model.Entity;
import br.com.gmltec.boomslangc2.phy.model.types.IEntityType;
import br.com.gmltec.boomslangc2.phy.utils.EntityTypeUtils;
import br.com.gmltec.boomslangc2.phy.utils.EntityUtils;

@SpringBootTest
class BoomslangC2ApplicationTests {

	@Test
	void loadEntityType() {
		HashMap<String,IEntityType> entTypes = EntityTypeUtils.loadEntityTypes();
		assertTrue(entTypes.size()>0);
	}
	
	@Test
	void loadEntities() {
		HashMap<String,IEntityType> entTypes = EntityTypeUtils.loadEntityTypes();
		
		HashMap<String,Entity> entDB = EntityUtils.loadEntities(entTypes);
		
		Entity BFIG2 = entDB.get("BFIG2");
		
		assertEquals(2, BFIG2.getBehavior_mode());
		assertEquals("AF", BFIG2.getForce());
		assertEquals(9.114209, BFIG2.getPosition().getLatitude());
		
		
	}

}
