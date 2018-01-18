// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc2450.Stan.subsystems;

import org.usfirst.frc2450.Stan.RobotMap;
import org.usfirst.frc2450.Stan.commands.*;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 */
public class DriveTrainSubsystem extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS
	public static boolean driveForward = true;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftMotor = RobotMap.driveTrainSubsystemLeftMotor;
    private final SpeedController rightMotor = RobotMap.driveTrainSubsystemRightMotor;
    private final RobotDrive robotDrive2 = RobotMap.driveTrainSubsystemRobotDrive2;
    private final AnalogGyro gyroGyro = RobotMap.driveTrainSubsystemGyroGyro;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new DriveWithJoysticks());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    private boolean arcadeDrive = true;
    
    public void takeJoystickInputs(Joystick left, Joystick right)
    {
    	if(arcadeDrive)
    	{
    		doArcadeDrive(left, right);
    	}
    	else
    	{
    	doTankDrive(left,right);	
    	}
    }
    
    
    public void doTankDrive(Joystick left, Joystick right)
    {
    	double a = 1;
    	double x = left.getY();
    	double leftOutput = a * x * x * x + (1 - a) * x;
    	x = right.getY();
    	double rightOutput = a * x * x * x + (1 - a) * x;
    	SmartDashboard.putNumber("right", rightOutput);
    	SmartDashboard.putNumber("left", leftOutput);

    	if (RobotMap.chuteFront)
    	{
    	// robotDrive2.tankDrive(left, right);
    	robotDrive2.setLeftRightMotorOutputs(leftOutput, rightOutput);
    	
    	}
    	else {
    		robotDrive2.setLeftRightMotorOutputs(-rightOutput, -leftOutput);
   
    		}
    	
   
    	}
    	
    	//robotDrive2.tankDrive(left, right);
 	private void doArcadeDrive(Joystick left, Joystick right){
 		double move = right.getY();
 		double rotate = right.getX();
 		if(! RobotMap.chuteFront)
 		{
 			move = -move;
 		}

 		robotDrive2.arcadeDrive(move, rotate);
 		SmartDashboard.putNumber("move", move);
    	SmartDashboard.putNumber("rotate", rotate);
	}

	

    public void stop()
    {
    	robotDrive2.drive(0, 0);
    }
}

