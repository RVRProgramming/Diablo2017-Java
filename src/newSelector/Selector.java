package newSelector;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Selector {

	private Joystick controller;
	private inputType inputType;
	private int[] inputPorts;
	private rowInteraction interactionType;
	private int numberOfRows;
	private String[][] rowOptions;
	private int[] selectedOptions;
	private int selectedRow = 0;
	private String[] rowNames;
	private Boolean properlyInitialized;
	private boolean properlyConfigured = true;
	private int numberOfFailedInits = 0;
	private boolean buttonPressed = false;
	private int movementDirection = 0;
	private boolean buttonNewlyPressed = false;
	private ArrayList adaptivelyConstrainedRows = new ArrayList(0);
	private ArrayList adaptivelyConstrainedOptions = new ArrayList(0);
	private ArrayList adaptiveConstraints = new ArrayList(0);
	private ArrayList linkedConstraints = new ArrayList(0);

	/**
	 * This enumerator defines the different types of row interactions supported by the selector.
	 * <ul>
	 * <li><b>off</b> turns off all row interactions.</li>
	 * <li><b>adaptive</b> allows you to add adaptive option selection. This means that given option x in row y, you can choose to display only certain options from row y+1. This option supports only two or more rows.</li> 
	 * <li><b>linkedAdaptive</b> allows you to add advanced adaptive option selection. This means that given one of the listed options{{x, y, z, ...}, {x, y, z, ...}, {x, y, z, ...}, ...} from rows{a, b, c, ...} ban options{l, m, n, ...} from row s. For the ban to be true, every chosen row must have one of the signified options selected. This option supports only 3 or more rows.</li>
	 * </ul>
	 * ADD DOCUMENTATION HERE FOR AN EXAMPLE OF MAKING ADAPTIVE RESTRICTIONS WORK IN CONJUNCTION WITH LINKEDADAPTIVE RESTRICTIONS.
	 * <p>
	 * If you are not sure which to use, turn on <b>linkedAdaptive</b>. <b>LinkedAdaptive</b> adds its own features on top of the features from <b>adaptive</b>. Code will likely run faster if the rowInteraction level is lower.
	 */
	public enum rowInteraction {
		off, adaptive, linkedAdaptive
	}

	/**
	 * This enumerator defines the different types of inputs supported by the selector.
	 * <ul>
	 * <li><b>POV</b> and <b>dPad</b> use the directional pad or POV pad. They are interchangeable.</li>
	 * <li><b>fourButton</b> uses four buttons.
	 * </ul>
	 */
	public enum inputType {
		POV, dPad, fourButton
	}

	/**
	 * This is the constructor for a new selector with automated grabbing for input values from given ports.
	 * @param numberOfRows Number of rows or option categories for the selector.
	 * @param rowInteraction See the javaDoc for the <b>rowInteraction</b> enum.
	 * @param inputPorts <ul><li>For POV/dPad control set up the input array like this {controllerNumber}</li>
	 * 					     <li>For fourButton control set up the input array like this {controllerNumber, leftButton, rightButton, upButton, downButton}</li></ul>
	 * @see rowInteraction
	 */
	public Selector(int numberOfRows, rowInteraction rowInteraction, inputType inputType, int[] inputPorts) {
		if (numberOfRows <= 0) {
			properlyConfigured = false;
			throw new IndexOutOfBoundsException("You must have at least 1 row.");
		} else {
			this.numberOfRows = numberOfRows;
			rowOptions = new String[numberOfRows][];
			rowNames = new String[numberOfRows];
			selectedOptions = new int[numberOfRows];
			for (int i = 0; i < selectedOptions.length; i++) {
				selectedOptions[i] = 0;
			}
		}

		if (rowInteraction == null) {
			properlyConfigured = false;
			throw new IllegalArgumentException("Null is not valid for the rowInteraction.");
		} else if (numberOfRows == 1 && rowInteraction != rowInteraction.off) {
			interactionType = rowInteraction.off;
			System.out.println("This rowInteraction is not valid for 1 row. The rowInteraction is now off. For 1 row, off is the only valid rowInteraction.");
		} else if (numberOfRows == 2 && rowInteraction == rowInteraction.linkedAdaptive) {
			interactionType = rowInteraction.adaptive;
			System.out.println("LinkedAdaptive rowInteraction is not valid for 2 rows. The rowInteraction is now adaptive.");
		} else {
			interactionType = rowInteraction;
		}

		if (inputType == null) {
			properlyConfigured = false;
			throw new IllegalArgumentException("Null is not valid for the inputType.");
		} else if (inputPorts == null) {
			properlyConfigured = false;
			throw new IllegalArgumentException("Null is not valid for the inputPorts.");
		} else if ((inputPorts.length != 1 && (inputType == inputType.POV || inputType == inputType.dPad)) || (inputPorts.length != 5 && inputType == inputType.fourButton)) {
			properlyConfigured = false;
			throw new IllegalArgumentException("The inputPorts array must be the correct size for the inputType of " + inputType + ".");
		} else {
			for (int i = 0; i < inputPorts.length; i++) {
				if (inputPorts[i] < 0) {
					properlyConfigured = false;
					throw new IndexOutOfBoundsException("Port/button numbers should have a value of 0 or greater. The detected offending port value is #" + i + " in the inputPorts array.");
				}
			}
			this.inputPorts = inputPorts;
			controller = new Joystick(inputPorts[0]);
			this.inputType = inputType;
		}

	}

	/**
	 * This function tests the initialization of options and names for rows along with any row linkages.
	 * @return Returns true if initialization passes, false if it fails.
	 */
	public boolean initTester() {
		if (!properlyConfigured) {
			properlyInitialized = false;
			System.out.println("This selector is not properly configured. Please check the constructor arguments.");
		}

		for (int i = 0; i < numberOfRows; i++) {
			if (rowOptions[i] == null) {
				System.out.println("Options have not been set (or set properly) for row " + i + ".");
				properlyInitialized = false;
			}
			if (rowNames[i] == null) {
				System.out.println("The name has not been set (or set properly) for row " + i + ".");
				properlyInitialized = false;
			}
		}

		if (properlyInitialized == null) {
			properlyInitialized = true;
		}

		return properlyInitialized;
	}

	/**
	 * Gets the selected option number for the designated row.
	 * @param row What row to find the selected option from,
	 * @return Returns the selected option as an integer.
	 */
	public int getSelectedOption(int row) {
		return selectedOptions[row];
	}

	/**
	 * Gets the selected option name for the designated row.
	 * @param row What row to find the selected option from,
	 * @return Returns the selected option's name/value as a String.
	 */
	public String getSelectedOptionAsString(int row) {
		return rowOptions[row][selectedOptions[row]];
	}

	/**
	 * Grabs the name of the designated row.
	 * @param row Row to find the name of.
	 * @return Returns the row's name as a String.
	 */
	public String getRowName(int row) {
		return rowNames[row];
	}

	/**
	 * Grabs the name of the designated option.
	 * @param row Row that contains the option.
	 * @param option Option to find the name of.
	 * @return Returns the option's name as a String.
	 */
	public String getOptionName(int row, int option) {
		return rowOptions[row][option];
	}

	/**
	 * This sets the options for a row. Do not set a row's options twice.
	 * @param row Starts at 0 and goes to the specified number of rows less 1.
	 * @param options All options must have a name. In the current version there is not protection against options in the same row having the same name.
	 */
	public void setRowOptions(int row, String[] options) {
		if (row < 0 || row > numberOfRows - 1) {
			throw new IndexOutOfBoundsException("The valid row numbers are from 0 to " + (numberOfRows - 1) + ".");
		} else if (rowOptions[row] != null) {
			throw new IllegalArgumentException("This row already has options assigned to it.");
		}

		if (options == null) {
			throw new IllegalArgumentException("The options must have a value.");
		} else {
			for (int i = 0; i < options.length; i++) {
				if (options[i] == null || options[i].length() < 1) {
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
			throw new IndexOutOfBoundsException("The valid row numbers are from 0 to " + (numberOfRows - 1) + ".");
		} else if (rowNames[row] != null) {
			throw new IllegalArgumentException("This row already has a name assigned to it.");
		}

		if (name == null) {
			throw new IllegalArgumentException("The name must have a value. Please use \"\" for null.");
		}

		rowNames[row] = name;
	}

	public void addAdaptiveConstraint(int row, int option, int[] allowedOptions) {
		boolean optionRepeats = false;
		boolean optionIsNotAnOption = false;
		boolean optionAlreadyUsed = false;
		if (interactionType != rowInteraction.off) {
			for (int i = 0; i < allowedOptions.length; i++) {
				for (int i2 = 0; i2 < allowedOptions.length; i++) {
					if (i != i2 && allowedOptions[i] == allowedOptions[i2]) {
						optionRepeats = true;
					}
				}
				if (allowedOptions[i] < 0 || allowedOptions[i] > rowOptions[row + 1].length) {
					optionIsNotAnOption = true;
				}
			}
			
			for (int i = 0; i < adaptivelyConstrainedRows.size(); i++){
				if ((int) adaptivelyConstrainedRows.get(i) == row && (int) adaptivelyConstrainedOptions.get(i) == option) {
					optionAlreadyUsed = true;
				}
			}

			if (optionAlreadyUsed) {
				throw new IllegalArgumentException("The given option in the given row has already been constrained.");
			} else if (row < 0 || row > numberOfRows - 2) {
				throw new IllegalArgumentException("The valid row numbers are from 0 to " + (numberOfRows - 2) + ".");
			} else if (allowedOptions.length > rowOptions[row + 1].length - 1) {
				throw new IllegalArgumentException("There must be at least one option that is not whitelisted ");
			} else if (optionRepeats) {
				throw new IllegalArgumentException("Options in the allowedOptions array must not repeat.");
			} else if (optionIsNotAnOption) {
				throw new IllegalArgumentException("All option numbers in allowedOptions must be valid option numbers for the row after the designated row.");
			} else {
				adaptivelyConstrainedRows.add(row);
				adaptivelyConstrainedOptions.add(option);
				adaptiveConstraints.add(allowedOptions);
			}

		} else {
			System.out.println("You cannot use adaptive constraints while the rowInteraction type is set to off.");
		}
	}

	public void addLinkedConstraint() {
		if (interactionType == rowInteraction.linkedAdaptive) {

		} else {
			System.out.println("You cannot use linked constraints while the rowInteraction type is set to off.");
		}
	}

	/**
	 * Getter for the movement direction from the POV. 
	 * @return Output values explained in the controlLogic JavaDoc.
	 * @see #controlLogic
	 */
	private int getPOV() {
		int POV = controller.getPOV();
		if (POV == -1) {
			return 0;
		} else if (POV % 90 != 0) {
			return -1;
		} else {
			return ((POV / 90) + 1);
		}
	}

	/**
	 * Getter for the movement direction from the 4 designated buttons. 
	 * @return Output values explained in the controlLogic JavaDoc.
	 * @see #controlLogic
	 */
	private int getFourButton() {
		boolean up = controller.getRawButton(inputPorts[3]);
		boolean right = controller.getRawButton(inputPorts[2]);
		boolean down = controller.getRawButton(inputPorts[4]);
		boolean left = controller.getRawButton(inputPorts[1]);
		if (!up && !right && !down && !left) {
			return 0;
		} else if (up && !right && !down && !left) {
			return 1;
		} else if (!up && right && !down && !left) {
			return 2;
		} else if (!up && !right && down && !left) {
			return 3;
		} else if (!up && !right && !down && left) {
			return 4;
		} else {
			return -1;
		}
	}

	/**
	 * Main selector logic here. Should be called in a loop to keep the selector updated.
	 */
	public void selectorLogic() {
		if (properlyInitialized) { //I haven't decided what to do if the selector isn't initialized properly. If I print out a message, it will spam the end user which is not desirable. Maybe spam it 5 or 10 times and then trigger an off toggle?
			controlLogic();
			if (buttonNewlyPressed) {
				buttonNewlyPressed = false;
				switch (movementDirection) {
				case 1:
					selectedRow--;
				case 2:
					selectedOptions[selectedRow]++;
				case 3:
					selectedRow++;
				case 4:
					selectedOptions[selectedRow]--;
				default:
				}

				if (selectedRow > numberOfRows - 1) {
					selectedRow = 0;
				} else if (selectedRow < 0) {
					selectedRow = numberOfRows - 1;
				}

				if (selectedOptions[selectedRow] > rowOptions[selectedRow].length - 1) {
					selectedOptions[selectedRow] = 0;
				} else if (selectedOptions[selectedRow] < 0) {
					selectedOptions[selectedRow] = rowOptions[selectedRow].length - 1;
				}
			}
			displayLogic();
		} else if (numberOfFailedInits < 5) {
			numberOfFailedInits++;
			System.out.println("There was an issue while starting your selector. Please check your logs to determine what went wrong.");
		}
	}

	/**
	 *  This handles the main control logic for row and option switching. Values passed through should range from -1 to 4 where the values are interpreted as follows:
	 *  <br>-1: Invalid input, typically ignored.
	 *  <br>0: No button pressed.
	 *  <br>1: Up
	 *  <br>2: Right
	 *  <br>3: Down
	 *  <br>4: Left
	 */
	private void controlLogic() {
		int buttonDirection = -1;
		if (inputType == inputType.POV || inputType == inputType.dPad) {
			buttonDirection = getPOV();
		} else if (inputType == inputType.fourButton) {
			buttonDirection = getFourButton();
		}

		if (buttonDirection == -1) {
		} else if (buttonPressed && buttonDirection == 0) {
			buttonPressed = false;
			movementDirection = 0;
			buttonNewlyPressed = true;
		} else if (!buttonPressed && buttonDirection != 0) {
			buttonPressed = true;
			movementDirection = buttonDirection;
			buttonNewlyPressed = true;
		}
	}

	/**
	 * This handles all of the display logic for a selector.
	 */
	private void displayLogic() {
		for (int i = 0; i < numberOfRows; i++) {
			String rowToDisplay = "";
			for (int i1 = 0; i1 < rowOptions[selectedRow].length; i++) {
				if (i1 == selectedOptions[selectedRow]) {
					rowToDisplay += rowOptions[selectedRow][i1].toUpperCase();
				} else {
					rowToDisplay += rowOptions[selectedRow][i1];
				}

				if (i1 < rowOptions[selectedRow].length - 1) {
					rowToDisplay += " | ";
				}
			}
			SmartDashboard.putString(rowNames[i], rowToDisplay);
		}
	}

}
