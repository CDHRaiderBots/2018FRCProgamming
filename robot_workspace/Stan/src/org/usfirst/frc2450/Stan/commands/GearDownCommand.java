// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2450.Stan.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc2450.Stan.Robot;
import org.usfirst.frc2450.Stan.RobotMap;

/**
 *
 */
public class GearDownCommand extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public GearDownCommand() {
    	requires(Robot.gearSubsystem);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }
    

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        RobotMap.gearSubsystemLeftGearServo.set(RobotMap.leftGearServoUp);
        RobotMap.gearSubsystemRightGearServo.set(RobotMap.rightGearServoUp);
    	RobotMap.gearSubsystemGearMotor.set(0.3);
    	SmartDashboard.putBoolean("DownEnd", false);
    	SmartDashboard.putBoolean("DownInt", false);
    	
    	setTimeout(2);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

    		SmartDashboard.putString("DOWN",  RobotMap.gearSubsystemStringPotentiometer.get() + "      " + RobotMap.gearDown + "  "  + (RobotMap.gearSubsystemStringPotentiometer.get() > RobotMap.gearDown));

        return RobotMap.gearSubsystemStringPotentiometer.get() > RobotMap.gearDown || isTimedOut();
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.gearSubsystemLeftGearServo.set(RobotMap.leftGearServoOpen);
    	RobotMap.gearSubsystemRightGearServo.set(RobotMap.rightGearServoOpen);
    	RobotMap.gearSubsystemGearMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	SmartDashboard.putBoolean("DownInt", true);
    }
}
