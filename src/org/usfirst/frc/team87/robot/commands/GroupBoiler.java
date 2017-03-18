package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GroupBoiler extends CommandGroup {
	int sideMultiplier = -1 + (2 * RobotMap.startingSide);

	public GroupBoiler() {
		addSequential(new AutoDrive(-93.307));
		addSequential(new WaitCommand(0.5));
		addSequential(new AutoTurn(46 * sideMultiplier));
		addSequential(new WaitCommand(0.5));
		addSequential(new AutoDrive(118.374));
	}
}
