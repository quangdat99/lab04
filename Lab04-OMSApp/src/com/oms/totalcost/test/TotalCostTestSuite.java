package com.oms.totalcost.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.oms.serverapi.test.BookApiTest;
import com.oms.serverapi.test.CompactDiscApiTest;
import com.oms.serverapi.test.ParameterizedBookApiTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ TotalCostBlackBoxTest.class, TotalCostWhiteBoxTest.class })

public class TotalCostTestSuite {

}
