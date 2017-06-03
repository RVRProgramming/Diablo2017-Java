package newSelector;

public class Selector {

	/**
	 * <ul><li>For POV/dPad control set up the input array like this {controllerNumber}</li><li>For joystick/2-axis control set up the input array like this {controllerNumber, leftRightAxis, upDownAxis}</li><li>For 4-button control set up the input array like this {controllerNumber, leftButton, rightButton, upButton, downButton}</li></ul>
	 */
	private int[] inputPorts;
	private rowInteraction interactionType;
	private int numberOfRows;
	private String[][] rowOptions;
	private String[] rowNames;

	/**
	 * This enumerator defines the different types of row interactions supported by the selector.
	 * <ul>
	 * <li><b>off</b> turns off all row interactions.</li>
	 * <li><b>adaptive</b> allows you to add adaptive option selection. This means that given option x in row y, you can choose to display only certain options from row y+1.</li>
	 * <li><b>linkedAdaptive</b> allows you to add advanced adaptive option selection. This means that given one of the listed options{{x, y, z, ...}, {x, y, z, ...}, {x, y, z, ...}, ...} from rows{a, b, c, ...} ban options{l, m, n, ...} from row s. For the ban to be true, every chosen row must have one of the signified options selected.</li>
	 * </ul>
	 * ADD DOCUMENTATION HERE FOR AN EXAMPLE OF MAKING ADAPTIVE RESTRICTIONS WORK IN CONJUNCTION WITH LINKEDADAPTIVE RESTRICTIONS.
	 * <p>
	 * If you are not sure which to use, turn on <b>linkedAdaptive</b>. <b>LinkedAdaptive</b> adds its own features on top of the features from <b>adaptive</b>. Code will likely run faster if the rowInteraction level is lower.
	 */
	public static enum rowInteraction {
		off, adaptive, linkedAdaptive
	}

	/**
	 * This is the constructor for a new selector with automated grabbing for input values from given ports.
	 * @param numberOfRows Number of rows or option categories for the selector.
	 * @param rowInteraction See the javaDoc for the <b>rowInteraction</b> enum.
	 * @param inputPorts <ul><li>For POV/dPad control set up the input array like this {controllerNumber}</li><li>For joystick/2-axis control set up the input array like this {controllerNumber, leftRightAxis, upDownAxis}</li><li>For 4-button control set up the input array like this {controllerNumber, leftButton, rightButton, upButton, downButton}</li></ul>
	 * @see rowInteraction
	 */
	public Selector(int numberOfRows, rowInteraction rowInteraction, int[] inputPorts) {
		if (numberOfRows <= 0) {
			throw new IndexOutOfBoundsException("You must have at least 1 row.");
		} else {
			this.numberOfRows = numberOfRows;
			rowOptions = new String[numberOfRows][];
			rowNames = new String[numberOfRows];
		}

		if (rowInteraction == null) {
			throw new IllegalArgumentException("No null arguments are valid for this constructor.");
		} else {
			interactionType = rowInteraction;
		}

		if (inputPorts.length < 1 || inputPorts.length % 2 == 0 || inputPorts.length > 5 || inputPorts == null) {
			throw new IllegalArgumentException("The inputPorts array must be either 1, 3, or 5 ports/items in length.");
		} else {
			this.inputPorts = inputPorts;
		}
	}

	/**
	 * 
	 * @param row Starts at 0 and goes to the specified number of rows less 1.
	 * @param options All options must have a name. In the current version there is not protection against options in the same row having the same name.
	 */
	public void setRowOptions(int row, String[] options) {
		if (row < 0 || row > numberOfRows - 1) {
			throw new IndexOutOfBoundsException("The valid row numbers are from 0 to " + numberOfRows + ".");
		} else if (rowOptions[row] != null) {
			throw new IllegalArgumentException("This row already has options assigned to it.");
		}
		
		if (options == null) {
			throw new IllegalArgumentException("The options must have a value.");
		} else {
			for (int i = 0; i <= options.length - 1; i--){
				if (options[i] == null || options[i].length() < 1){
					throw new IllegalArgumentException("The name of an option must be a string with length greater than 1.");
				}
			}
		}

		rowOptions[row] = options;
	}

	/**
	 * This sets a row's name. Do not set a name twice.
	 * @param row Starts at 0 and goes to the specified number of rows less 1.
	 * @param name Use "" for null. Otherwise use a normal string.
	 */
	public void setRowName(int row, String name) {
		if (row < 0 || row > numberOfRows - 1) {
			throw new IndexOutOfBoundsException("The valid row numbers are from 0 to " + numberOfRows + ".");
		} else if (rowNames[row] != null) {
			throw new IllegalArgumentException("This row already has a name assigned to it.");
		}

		if (name == null) {
			throw new IllegalArgumentException("The name must have a value. Please use \"\" for null.");
		}

	}

}
