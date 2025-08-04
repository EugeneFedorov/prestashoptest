package com.prestashop.suites;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectPackages("com.prestashop.tests")
@SuiteDisplayName("All Prestashop Tests Suite")
public class AllTestsSuite {
}
