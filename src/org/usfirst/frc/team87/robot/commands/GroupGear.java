package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;
import org.usfirst.frc.team87.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GroupGear extends CommandGroup {

    public GroupGear() {
    	if(RobotMap.startingPosition-1==0){
    		addSequential(new AutoDrive(1));
    	}else{
    		addSequential(new AutoDrive(1));
    		addSequential(new AutoTurn(1));
    		addSequential(new AutoDrive(1));
    	}
    }
}
