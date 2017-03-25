package org.usfirst.frc.team87.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GroupDriveStraight extends CommandGroup {

    public GroupDriveStraight() {
        addSequential(new AutoDriveStraight());
    }
}
