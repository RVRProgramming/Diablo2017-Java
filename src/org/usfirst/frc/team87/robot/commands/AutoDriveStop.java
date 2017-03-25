package org.usfirst.frc.team87.robot.commands;

import org.usfirst.frc.team87.robot.Robot;
import org.usfirst.frc.team87.robot.subsystems.DriveBase;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDriveStop extends Command {

    public AutoDriveStop() {
    }

    protected void initialize() {
    	Robot.drivebase.resetEncoder();
    	Robot.drivebase.drive(0, 0);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
