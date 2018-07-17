package game.elements;

import java.io.Serializable;

/**
 * Enum
 * things at game that needs to have an id choose their id from here.
 */
public enum ObjectId implements Serializable
{
    Player,              //lightPink(208, 135, 190)
    MachineGun,
    MissileGun,
    LightBullet,
    HeavyBullet,
    AITank,              //Orange(255, 114, 0)            ||    if it has damage upgrade under red(255, 0, 0)     ||    if it has tank health upgrade under Phosphoric(213, 236, 6)
    BuriedRobot,         //black(0, 0, 0)
    Turret,              //darkYellow(166, 166, 76)       ||      if it is second mode turret darkGreen(22, 62, 34)
    HardWall,            //yellow (255, 255, 0)
    SoftWall,            //blue (0, 0, 255)
    Plant,               //green (0, 255, 0)
    Soil,                //white (255, 255, 255)
    Teazel,              //purple (255, 0, 255)
    End,                 //dark red(100, 0, 0)
    ServerPlayer,
    ClientPlayer,
    SinglePlayer,
    Alone,
    TwoPlayer,
    DamageUpgrade,       //under the AITank
    MissileGunUpgrade,   //lightBlue(88, 162, 230)
    MachineGunUpgrade,   //lightGreen(143, 202, 160)
    HealthUpgrade,       //under the AITank
    ShieldUpgrade,       //gray(90, 90, 90)
    PlayerShooter,
    AIShooter,
    HardMode,
    MediumMode,
    EasyMode,
    FirstMap,
    SecondMap,
    ThirdMap,
    FourthMap,
    SavedGame,
    NewGame,
    Won,
    Lost;// win or lose
}
