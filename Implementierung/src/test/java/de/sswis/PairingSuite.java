package de.sswis;

import de.sswis.model.algorithms.pairing.BruteForcePairingHeuristicTest;
import de.sswis.model.algorithms.pairing.BruteForcePairingTest;
import de.sswis.model.algorithms.pairing.MinimumWeightMatchingTest;
import de.sswis.model.algorithms.pairing.RandomPairingTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({BruteForcePairingHeuristicTest.class, BruteForcePairingTest.class,
        MinimumWeightMatchingTest.class, RandomPairingTest.class})
public class PairingSuite {
}
