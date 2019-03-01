package de.sswis;

import de.sswis.model.algorithms.adaptation.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({MixedLinearInterpolationTest.class, MixedSumTest.class, RankPercentageTest.class,
        ReplicatorDynamicRankTest.class, ReplicatorDynamicScoreTest.class})
public class AdaptationSuite {
}
