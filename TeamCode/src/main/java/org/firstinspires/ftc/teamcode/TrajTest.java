package org.firstinspires.ftc.teamcode;

import android.drm.DrmStore;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MecanumDrive;
import org.firstinspires.ftc.teamcode.TankDrive;
import org.firstinspires.ftc.teamcode.ThreeDeadWheelLocalizer;
import org.firstinspires.ftc.teamcode.TwoDeadWheelLocalizer;

@Autonomous
public class TrajTest extends LinearOpMode{

    @Override
    public void runOpMode(){
        waitForStart();
        MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(-36, -60, Math.toRadians(90)));

        Actions.runBlocking(
                drive.actionBuilder(new Pose2d(-36, -60, Math.toRadians(90)))
                        .lineToYConstantHeading(-34)
                        .waitSeconds(1)
                        .splineTo(new Vector2d(-37, -10), Math.toRadians(0))
                        .splineTo(new Vector2d(30, -40), Math.toRadians(180))
                        .splineTo(new Vector2d(5, -25), Math.toRadians(90))
                        .splineTo(new Vector2d(-36, -40), Math.toRadians(90))
                        .build()
        );
    }
    }

