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
public class GearUpCommand extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public GearUpCommand() {

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
    
    private boolean isInRange()
    {
    return true;	
    }
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	RobotMap.gearSubsystemLeftGearServo.set(RobotMap.leftGearServoClosed);
    	RobotMap.gearSubsystemRightGearServo.set(RobotMap.rightGearServoClosed);
		SmartDashboard.putBoolean("STOPPED", false);
		try 
		{
		Thread.sleep(1000);
		}
		catch(InterruptedException ex)
		{
			
		}
    		RobotMap.gearSubsystemGearMotor.set(-0.5);
    		setTimeout(2);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(RobotMap.gearSubsystemStringPotentiometer.get() < RobotMap.gearUp)
    	{
    		SmartDashboard.putBoolean("STOPPED",true);
    	}
    
		return RobotMap.gearSubsystemStringPotentiometer.get() < RobotMap.gearUp || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.gearSubsystemGearMotor.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
