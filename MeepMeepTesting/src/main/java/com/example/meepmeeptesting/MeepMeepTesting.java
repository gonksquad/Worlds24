package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;



public class MeepMeepTesting {
    public static void main(String[] args) {

        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-36, 31, Math.toRadians(180)))


                //Red On-stage Center  --  StartPose (-36, -60, 90 deg)
//                        .splineTo(new Vector2d(-33, -36), Math.toRadians(90))
//                        .strafeTo(new Vector2d(-50, -40))
//                        .strafeTo(new Vector2d(-50, -41))
//                        .lineToY(-24)
//                        .splineTo(new Vector2d(-12,-12), Math.toRadians(0))
//                        .splineTo(new Vector2d(30,-12), Math.toRadians(0))
//                        .splineTo(new Vector2d(40,-36), Math.toRadians(180))

                //Red On-stage Right  --  StartPose (-36, -60, 90 deg)
//                        .splineTo(new Vector2d(-34, -36), Math.toRadians(0))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(-34, -12))
//                        .strafeTo(new Vector2d(-33.9, -12))
//                        .splineTo(new Vector2d(-12,-12), Math.toRadians(0))
//                        .splineTo(new Vector2d(30,-12), Math.toRadians(0))
//                        .splineTo(new Vector2d(40,-36), Math.toRadians(180))

                //Red On-stage Left  --  StartPose (-36, -60, 90 deg)
//                        .splineTo(new Vector2d(-40, -36), Math.toRadians(180))
//                        .lineToX(-34)
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(-34, -12))
//                        .strafeTo(new Vector2d(-33.9, -12))
//                        .turn(Math.toRadians(180))
//                        .splineTo(new Vector2d(-12,-12), Math.toRadians(0))
//                        .splineTo(new Vector2d(30,-12), Math.toRadians(0))
//                        .splineTo(new Vector2d(40,-36), Math.toRadians(180))


                //Blue On-stage Center -- StartPose (-36, 60, 270 deg)
//                        .splineTo(new Vector2d(-36, 30), Math.toRadians(270))
//                        .lineToY(34)
//                        .strafeTo(new Vector2d(-50, 40))
//                        .strafeTo(new Vector2d(-50, 41))
//                        .lineToY(24)
//                        .splineTo(new Vector2d(-12,12), Math.toRadians(0))
//                        .splineTo(new Vector2d(30,12), Math.toRadians(0))
//                        .splineTo(new Vector2d(40,36), Math.toRadians(180))

                //Blue On-stage Left  --  StartPose (-36, 60, 270 deg)
//                        .splineTo(new Vector2d(-34, 36), Math.toRadians(0))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(-34, 12))
//                        .strafeTo(new Vector2d(-33.9, 12))
//                        .splineTo(new Vector2d(-12,12), Math.toRadians(0))
//                        .splineTo(new Vector2d(30,12), Math.toRadians(0))
//                        .splineTo(new Vector2d(40,36), Math.toRadians(180))

                //Blue On-stage Right  --  StartPose (-36, 60, 270 deg)
//                        .splineTo(new Vector2d(-40, 36), Math.toRadians(180))
//                        .lineToX(-34)
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(-34, 12))
//                        .strafeTo(new Vector2d(-33.9, 12))
//                        .turn(Math.toRadians(180))
//                        .splineTo(new Vector2d(-12,12), Math.toRadians(0))
//                        .splineTo(new Vector2d(30,12), Math.toRadians(0))
//                        .splineTo(new Vector2d(40,36), Math.toRadians(180))


                //Red Back-Stage Center  --  StartPose (12, -60, 90 deg)
//                        .splineTo(new Vector2d(10, -35), Math.toRadians(90))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(40, -35))
//                        .turn(Math.toRadians(90))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(45, -60))
//                        .strafeTo(new Vector2d(60, -60))

                //Red Back-Stage Left  --  StartPose (12, -60, 90 deg)
//                        .splineTo(new Vector2d(10, -35), Math.toRadians(180))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(40, -35))
//                        //.turn(Math.toRadians(90))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(45, -60))
//                        .strafeTo(new Vector2d(60, -60))

                //Red Back-Stage Right  --  StartPose (12, -60, 90 deg)
//                        .strafeTo(new Vector2d(36, -35))
//                        .turnTo(Math.toRadians(180))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(40, -35))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(45, -60))
//                        .strafeTo(new Vector2d(60, -60))


                //Blue Back-Stage Center  --  StartPose (12, 60, 270 deg)
//                        .splineTo(new Vector2d(14, 35), Math.toRadians(270))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(40, 35))
//                        .turn(Math.toRadians(-90))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(45, 60))
//                        .strafeTo(new Vector2d(60, 60))
//
//                //Blue Back-Stage Right  --  StartPose (12, 60, 270 deg)
//                        .splineTo(new Vector2d(10, 35), Math.toRadians(180))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(40, 35))
//                        //.turn(Math.toRadians(90))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(45, 60))
//                        .strafeTo(new Vector2d(60, 60))
//
//                //Blue Back-Stage Left  --  StartPose (12, 60, 270 deg)
//                        .strafeTo(new Vector2d(36, 35))
//                        .turnTo(Math.toRadians(180))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(40, 35))
//                        .waitSeconds(2)
//                        .strafeTo(new Vector2d(45, 60))
//                        .strafeTo(new Vector2d(60, 60))
//
//


//                .strafeTo(new Vector2d(36, -35))
//                .turnTo(Math.toRadians(180))
//                .strafeTo(new Vector2d(46, -43))
//                .strafeTo(new Vector2d(45, -60))
//                .strafeTo(new Vector2d(60, -60))
                .strafeTo(new Vector2d(-34, 10))
                .strafeTo(new Vector2d(-33.9, 10))
                .splineTo(new Vector2d(-12,10), Math.toRadians(0))
                .splineTo(new Vector2d(24,10), Math.toRadians(0))
                .splineTo(new Vector2d(45,30), Math.toRadians(0))


                //TEST TRAJECTORY -- just for my sanity, hehe... (Start pose at 0 0 0)
//                        .splineTo(new Vector2d(15,15), 90)
//                        .splineTo(new Vector2d(-15,15), 180)
//                        .splineTo(new Vector2d(-15,-15), 270)
//                        .splineTo(new Vector2d(15,-15), 0)
//                        .splineTo(new Vector2d(30,30), 90)
//                        .splineTo(new Vector2d(-30,30), 180)
//                        .splineTo(new Vector2d(-30,-30), 270)
//                        .splineTo(new Vector2d(30,-30), 0)


                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}