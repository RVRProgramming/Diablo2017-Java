package org.usfirst.frc.team87.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AutonomousSelector {
	public static int POV;
	public static boolean buttonPressed = false;
	public static String[] sides = { "red", "blue" };
	public static String[] position = { "left", "middle", "right" };
	public static String[] destination = { "off", "gear", "baseline", "boiler" };
	public static String finalDestinationRow = "";
	public static String[] multi = { "off", "boiler" };
	public static int row;
	public static String[] rowPrefix = { "      ", "      ", "      ", "      " };
	public static int[] column = { 0, 0, 0, 0, 2, 3, 1, 1 };

	public void autoSelectorLogic() {
		POV = Robot.oi.gamepad.getPOV();
		sides[0] = sides[0].toLowerCase();
		sides[1] = sides[1].toLowerCase();
		position[0] = position[0].toLowerCase();
		position[1] = position[1].toLowerCase();
		position[2] = position[2].toLowerCase();
		destination[0] = destination[0].toLowerCase();
		destination[1] = destination[1].toLowerCase();
		destination[2] = destination[2].toLowerCase();
		destination[3] = destination[3].toLowerCase();
		multi[0] = multi[0].toLowerCase();
		multi[1] = multi[1].toLowerCase();
		if (buttonPressed && POV == -1) {
			buttonPressed = false;
		}
		if (!buttonPressed && POV != -1) {
			buttonPressed = true;
			if (POV == 0) {
				row -= 1;
				if (row <= -1) {
					row = 3;
				}
			} else if (POV == 180) {
				row += 1;
				if (row >= 4) {
					row = 0;
				}
			} else if (POV == 90) {
				column[row] = column[row] + 1;
				if (column[row] >= column[row + 4]) {
					column[row] = 0;
				}
			} else if (POV == 270) {
				column[row] -= 1;
				if (column[row] <= -1) {
					column[row] = column[row + 4] - 1;
				}
			}
		}
		SmartDashboard.putString("POV Value", Double.toString(POV));
		rowPrefix[0] = "      ";
		rowPrefix[1] = "      ";
		rowPrefix[2] = "      ";
		rowPrefix[3] = "      ";
		rowPrefix[row] = ">> ";

		sides[column[0]] = sides[column[0]].toUpperCase();
		RobotMap.startingSide = column[0];
		SmartDashboard.putString("Side", rowPrefix[0] + sides[0] + " | " + sides[1]);
		int sideMultiplier = -1 + (2 * RobotMap.startingSide);
		position[column[1]] = position[column[1]].toUpperCase();
		RobotMap.startingPosition = column[1];
		SmartDashboard.putString("Position", rowPrefix[1] + position[0] + " | " + position[1] + " | " + position[2]);
		if (sideMultiplier * (column[1] - 1) == -1) {
			column[6] = 4;
		} else if (sideMultiplier * (column[1] - 1) >= 0) {
			column[6] = 3;
		}
		RobotMap.endDestination = column[2];
		destination[column[2]] = destination[column[2]].toUpperCase();
		finalDestinationRow = destination[0];
		for (int i = 1; i <= column[6] - 1; i++) {
			finalDestinationRow += " | " + destination[i];
		}
		SmartDashboard.putString("Destination", rowPrefix[2] + finalDestinationRow);
		if (RobotMap.endDestination == 1 && (sideMultiplier * (column[1] - 1)) <= 0) {
			column[7] = 2;
			RobotMap.multiAutonomous = column[3];
			multi[column[3]] = multi[column[3]].toUpperCase();
			SmartDashboard.putString("Second Destination", rowPrefix[3] + multi[0] + " | " + multi[1]);
		} else {
			RobotMap.multiAutonomous = 0;
			SmartDashboard.putString("Second Destination", rowPrefix[3] + multi[0].toUpperCase());
		}
		SmartDashboard.putString("Overall Autonomous", sides[RobotMap.startingSide] + " | " + position[RobotMap.startingPosition] + " | " + destination[RobotMap.endDestination] + " | " + multi[RobotMap.multiAutonomous]);
	}

	public void selectCommandGroup() {

	}
}
