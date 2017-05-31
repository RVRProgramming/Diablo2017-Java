package newSelector;

public class Selector {

	/**
	 * This enumerator defines the different types of inputs supported by the selector.
	 * <ul>
	 * <li>POV and dPad are for four or eight way directional pad input. They are equivalent.</li>
	 * <li>joystick and twoAxis are for joystick, thumbstick, and two axis based input. They are equivalent.</li>
	 * <li>fourButton is for a 4 button based input similar to the ABXY buttons on a typical controller.</li>
	 * </ul>
	 */
	private enum input {
		POV, joystick, dPad, fourButton, twoAxis
	}

	private enum rowInteraction {
		off, adaptive, linkedAdaptive
	}

	public Selector(int numberOfRows, input typeOfInput, rowInteraction interactionState) {
		if (numberOfRows <= 0) {
			throw new IndexOutOfBoundsException("You must have at least 1 row.");
		}

	}

}
