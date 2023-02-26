package cz.cvut.fit.miadp.mvcgame.config;

public class MvcGameConfig {
    public static final int MAX_X = 1280;
    public static final int MAX_Y = 720;
    public static final int MOVE_STEP = 10;
    public static final int CANNON_POS_X = 50;
    public static final int CANNON_POS_Y = MAX_Y / 2;
    public static final int CANNON_SIZE_Y = 69;
    public static final int ENEMY_OFFSET_X = 300;
    public static final int ENEMY_OFFSET_Y = 100;
    public static final int INIT_POWER = 10;
    public static final double INIT_ANGLE = 0;
    public static final double ANGLE_STEP = Math.PI / 18;
    public static final int POWER_STEP = 1;
    public static final double GRAVITY = 9.8;
    public static final int ENEMIES_LEVEL_1 = 5;
    public static final int ENEMIES_LEVEL_2 = 7;
    public static final int ENEMIES_LEVEL_3 = 9;
    public static final int MAX_LEVEL = 3;
    public static final int ENEMY_LIVES_LEVEL_1 = 2;
    public static final int ENEMY_LIVES_LEVEL_2 = 2;
    public static final int ENEMY_LIVES_LEVEL_3 = 2;
    public static final int ENEMY_HIT_RADIUS = 15;
    public static final int MISSILE_HIT_RADIUS = 14;
    public static final int COLLISION_DELETE_TIME = 3000; //miliseconds
    public static final int GAME_INFO_POS_X = 10;
    public static final int GAME_INFO_POS_Y = 10;
    public static final int GAME_INFO_TEXT_SPACING = 20;
    public static final int KILL_SCORE = 1;
    public static final int BOUND_SIZE = 30;
    public static final int BOUND_POS_X = 50;
    public static final int BOUND_Y_UPPER_BORDER = 600;
    public static final int BOUND_Y_LOWER_BORDER = 150;
    public static final int RELOAD_TIME = 2;
    public static final int MISSILE_CAPACITY = 6;
    public static final int END_SCENE_POS_Y = MAX_Y/2;

}