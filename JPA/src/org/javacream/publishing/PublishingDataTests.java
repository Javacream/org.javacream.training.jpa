package org.javacream.publishing;

import org.javacream.util.test.jpa.BaseJpaTest;
import org.junit.Test;

public class PublishingDataTests extends BaseJpaTest {

	@Test
	public void testPublisher() {
		Util.createPublishers(entityManager);
		transaction.commit();
		entityManager.close();
	}

}
