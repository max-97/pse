package de.sswis.controller;

/**
 * Created by Max Braun on 27.01.2019.
 */
class VariableDistribution {

	private int[] values;
	private static int size = 0;
	private boolean hasIDInterval;

	public VariableDistribution(String input) {
		String value = input.trim();
		if (containsInt(value)) {
			this.hasIDInterval = false;
			this.values = new int[1];
			this.values[0] = Integer.parseInt(value);
			if (size < 1)
				size = 1;
		} else if (containsInterval(value)) {
			this.hasIDInterval = true;
			this.values = calculateInterval(value);
			if (size < 1)
				size = 1;
		} else if (containsVariable(value)) {
			this.hasIDInterval = false;
			this.values = calculateValues(value);
			if (size < this.values.length)
				size = this.values.length;
		}
	}

	private int[] calculateValues(String value) {
		String[] parts = value.split(" - ");
		int start = Integer.parseInt(parts[0]);
		int end = Integer.parseInt(parts[1]);
		int step = Integer.parseInt(parts[2]);
		int index = ((end - start) / step) + 1;
		index = index < 0 ? index * (-1) : index;
		int[] values = new int[index];
		for (int i = start; i <= end; i += step) {
			values[(i - start) / step] = i;
		}
		return values;
	}

	private int[] calculateInterval(String value) {
		String[] parts = value.split(" - ");
		int start = Integer.parseInt(parts[0]);
		int end = Integer.parseInt(parts[1]);
		int[] ids = new int[end - start + 1];
		for (int i = start; i <= end; i++) {
			ids[i - start] = i;
		}
		return ids;
	}

	public int[] getValues() {
		return this.values;
	}

	public int getValue(int entry) {
		if (this.values.length == 1) {
			return this.values[0];
		} else {
			return this.values[entry];
		}
	}

	public static int getSize() {
		return size;
	}

	public boolean containsIDInterval() {
		return this.hasIDInterval;
	}

	private boolean containsInt(String value) {
		return value.matches("\\d+");
	}

	private boolean containsInterval(String value) {
		return value.matches("\\d+ - \\d+");
	}

	private boolean containsVariable(String value) {
		return value.matches("\\d+ - \\d+ - \\d+");
	}
}