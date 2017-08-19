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
