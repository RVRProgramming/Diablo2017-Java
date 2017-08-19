package newSelector;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Selector {

	/**
	 * This is the constructor for a new selector with automated grabbing for input values from given ports.
	 * @param numberOfRows Number of rows or option categories for the selector.
	 * @param rowInteraction See the javaDoc for the <b>rowInteraction</b> enum.
	 * @param inputPorts <ul><li>For POV/dPad control set up the input array like this {controllerNumber}</li>
	 * 					     <li>For fourButton control set up the input array like this {controllerNumber, leftButton, rightButton, upButton, downButton}</li></ul>
	 * @see rowInteraction
	 */
	public Selector(int numberOfRows, rowInteraction rowInteraction, inputType inputType, int[] inputPorts) {
	}
	
	/**
	 * This enumerator defines the different types of row interactions supported by the selector.
	 * <ul>
	 * <li><b>off</b> turns off all row interactions.</li>
	 * <li><b>adaptive</b> allows you to add adaptive option selection. This means that given option x in row y, you can choose to ban certain options from row y+1. This option supports only two or more rows.</li> 
	 * <li><b>linkedAdaptive</b> allows you to add advanced adaptive option selection. This means that given one of the listed options{{x, y, z, ...}, {x, y, z, ...}, {x, y, z, ...}, ...} from rows{a, b, c, ...} ban options{l, m, n, ...} from row s. For the ban to be true, every chosen row must have one of the signified options selected. This option supports only 3 or more rows.</li>
	 * </ul>
	 * ADD DOCUMENTATION HERE FOR AN EXAMPLE OF MAKING ADAPTIVE RESTRICTIONS WORK IN CONJUNCTION WITH LINKEDADAPTIVE RESTRICTIONS.
	 * <p>
	 * If you are not sure which to use, turn on <b>linkedAdaptive</b>. <b>LinkedAdaptive</b> adds its own features on top of the features from <b>adaptive</b>. Code will likely run faster if the rowInteraction level is lower.
	 */

	/**
	 * Main selector logic here. Should be called in a loop to keep the selector updated.
	 */
	public void selectorLogic() {
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
