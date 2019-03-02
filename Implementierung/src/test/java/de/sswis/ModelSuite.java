package de.sswis;

import de.sswis.model.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CombinedStrategyTest.class, GameTest.class, HistoryTest.class, InitializationTest.class,
        MixedStrategyTest.class, SimulationTest.class})
public class ModelSuite {
}
