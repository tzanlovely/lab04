package test;

import com.oms.bean.Order;
import com.oms.bean.OrderLine;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author phong.cv173298
 * @created 15/12/2020 - 10:44 AM
 * @project oms
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({ TotalCostBlackBoxTest.class, TotalCostWhiteBoxTest.class })
public class GetTotalCostTestSuit {
}
