package de.sswis;

import de.sswis.model.algorithms.ranking.AverageRankTest;
import de.sswis.model.algorithms.ranking.CurrentCycleScoreTest;
import de.sswis.model.algorithms.ranking.CustomCycleScoreTest;
import de.sswis.model.algorithms.ranking.ScoreTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({AverageRankTest.class, CurrentCycleScoreTest.class, CustomCycleScoreTest.class,
        ScoreTest.class})
public class RankingSuite {
}
