package com.app.investments.fund.investingapp.InvestitionServiceTest;

import com.app.investments.fund.investingapp.ApplicationTestContext;
import com.app.investments.fund.investingapp.fund.entity.repository.FundRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

/**
 * @author Krzysztof Lakomy
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationTestContext.class, loader = AnnotationConfigContextLoader.class)
@SqlGroup({@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
		scripts = "/investition_test/insert_sample_data.sql"),
		@Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
				scripts = "/investition_test/delete_sample_data.sql") })
public class InvestitionServiceImplTest {

	// After fix of context tests will be written
	@Test
	public void getInvestitionByDateTest(){
		Assert.assertTrue(true);
	}

}
