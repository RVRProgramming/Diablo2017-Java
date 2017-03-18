package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GroupGear extends CommandGroup {
	int sideMultiplier = -1+(2*RobotMap.startingSide);
	public GroupGear() {
		if (RobotMap.startingPosition == 1) {
			addSequential(new AutoDrive(-80.707));
		} else {
			addSequential(new AutoDrive(-88.563));
			addSequential(new AutoTurn(-60 * (RobotMap.startingPosition - 1)));
			addSequential(new AutoDrive(-38.974));
		}
		if (RobotMap.multiAutonomous == 1) {
			addSequential(new AutoGearCheck());
			if((RobotMap.startingSide==0&&RobotMap.startingPosition==2)||(RobotMap.startingSide==1&&RobotMap.startingPosition==0)){
				addSequential(new AutoDrive(30));
				addSequential(new AutoTurn(-28*sideMultiplier));
				addSequential(new AutoDrive(62.606));
				addSequential(new AutoTurn(15*sideMultiplier));
				addSequential(new AutoDrive(37));
			}else if(RobotMap.startingPosition==1){
				addSequential(new AutoDrive(42.794));
				addSequential(new AutoTurn(90*sideMultiplier));
				addSequential(new AutoDrive(107.078));
				addSequential(new AutoTurn(-44*sideMultiplier));
				addSequential(new AutoDrive(35.008));
			}
		}
	}
}
