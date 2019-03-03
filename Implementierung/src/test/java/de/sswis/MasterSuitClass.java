package de.sswis;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ModelSuite.class, AdaptationSuite.class, PairingSuite.class, RankingSuite.class})
public class MasterSuitClass {
}
