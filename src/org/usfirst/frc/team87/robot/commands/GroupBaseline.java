package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;
import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GroupBaseline extends CommandGroup {
	public GroupBaseline() {
		if (RobotMap.startingPosition != 1) {
			addSequential(new AutoDrive(-152.812));
		} else if (RobotMap.startingPosition == 1) {
			addSequential(new AutoDrive(-80.708));
		}
	}
}
