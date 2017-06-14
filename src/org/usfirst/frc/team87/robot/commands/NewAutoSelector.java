package org.usfirst.frc.team87.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import newSelector.Selector;
import newSelector.Selector.inputType;
import newSelector.Selector.rowInteraction;

/**
 *
 */
public class NewAutoSelector extends Command {
	Selector newSelector;

	public NewAutoSelector() {
		int[] inputs = {0};
		newSelector = new Selector(2, rowInteraction.off, inputType.dPad, inputs);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		String[] row1options={"1", "2", "3"};
		String[] row2options={"1","2"};
		newSelector.setRowName(0, "Row 1");
		newSelector.setRowName(1, "Row 2");
		newSelector.setRowOptions(0, row2options);
		newSelector.setRowOptions(1, row2options);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
